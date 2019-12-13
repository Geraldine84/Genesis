package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {


    public static ThreadLocal<AndroidDriver> driver = new ThreadLocal<AndroidDriver>();
    public static ExtentReports reports;
    public static ExtentHtmlReporter htmlReporter;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    public static ThreadLocal<ExtentTest> testInfo = new ThreadLocal<ExtentTest>();
    public static String toAddress;

    public static String userName = "USERNAME";
    public static String accessKey = "ACCESS_KEY";
    public String local = "local";

    public static AndroidDriver getDriver() {
        return driver.get();
    }


    String devices;
    static String[] udid;

    @Parameters({"dataEnv"})
    public static String myUrl(String dataEnv) throws IOException, ParseException {
    	File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
        JSONObject envs = (JSONObject) config.get("LandingPage_Build");

        String debugBuild = (String) envs.get("debugBuild");
        String prodBuild = (String) envs.get("prodBuild");

        String myUrl = null;
        if (dataEnv.equalsIgnoreCase("debugData")) {
            myUrl = System.getProperty("LandingPage_Build", debugBuild);
        } else {
            myUrl = System.getProperty("LandingPage_Build", prodBuild);
        }
        return myUrl;
    }

    @BeforeSuite
    @Parameters({"groupReport", "dataEnv"})
    public void setUp(String groupReport, String dataEnv) throws Exception {

        {
            try {
                devices = TestUtils.executeAdbCommand("adb devices");
                devices = devices.replaceAll("List of devices attached", " ");
                devices = devices.replaceAll("device", " ").trim();
                udid = devices.split(" ");
            } catch (IOException e) {
                System.out.println("No devices found: " + e.toString());

            }
        }

        htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + groupReport));
        // htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/resources/extent-config.xml"));
         reports = new ExtentReports();
         reports.attachReporter(htmlReporter);
         reports.setSystemInfo("Test Environment", myUrl(dataEnv));

    }

    @BeforeMethod(description = "fetch test cases name")
    @Parameters("deviceName")
    public void register(Method method, String deviceName) {

        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        ExtentTest child = parentTest.get().createNode(method.getName());
        testInfo.set(child);
        testInfo.get().assignCategory("Sanity");
        String deviceVersion = getDriver().getCapabilities().getCapability("platformVersion").toString();
        String os = "Device name: " + deviceName + " Android: " + deviceVersion;
        testInfo.get().getModel().setDescription(os);
    }
    @AfterMethod(description = "to display the result after each test method")
    public void captureStatus(ITestResult result) throws IOException {
        for (String group : result.getMethod().getGroups())
            testInfo.get().assignCategory(group);
        if (result.getStatus() == ITestResult.FAILURE) {
        	 String screenshotPath = TestUtils.addScreenshot();
             testInfo.get().addScreenCaptureFromBase64String(screenshotPath);
            testInfo.get().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP)
            testInfo.get().skip(result.getThrowable());
        else
            testInfo.get().pass(result.getName() + " Test passed");

        reports.flush();
    }

    @AfterClass
    public void closeApp() {

        getDriver().quit();
    }

    @AfterSuite
    @Parameters("toMails")
    public void cleanup(String toMails) {
        toAddress = toMails;
        SendMail.ComposeGmail("Genesis Droid Report <seamfix.test.report@gmail.com>", toAddress);
        getDriver().quit();
    }


    @BeforeClass
    @Parameters({"systemPort", "deviceNo", "server", "dataEnv"})
    public void startApp(String systemPort, int deviceNo, String server, String dataEnv) throws IOException, ParseException {

        if (server.equals(local)) {

            deviceNo = deviceNo - 1;
            while (deviceNo >= udid.length) {
                deviceNo = deviceNo - 1;
            }
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("autoGrantPermissions", true);
                capabilities.setCapability("unicodeKeyboard", true);
                capabilities.setCapability("resetKeyboard", true);
                capabilities.setCapability("noReset", true);
                capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
                capabilities.setCapability(MobileCapabilityType.UDID, udid[deviceNo].trim());
                capabilities.setCapability("deviceName", "Galaxy J5");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("appPackage", "com.genesis");
                capabilities.setCapability("appActivity", "com.genesis.MainActivity");
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

                driver.set(new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities));
                System.out.println("++++++++++UIAUTOMATOR 2 DRIVER INSTANCE RUNNING++++++++++++");


            } catch (WebDriverException e) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(MobileCapabilityType.UDID, udid[deviceNo].trim());
                capabilities.setCapability("autoGrantPermissions", true);
                capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
                capabilities.setCapability("unicodeKeyboard", true);
                capabilities.setCapability("resetKeyboard", true);
                capabilities.setCapability("noReset", true);
                capabilities.setCapability("deviceName", "Galaxy J5");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("appPackage", "com.genesis");
                capabilities.setCapability("appActivity", "com.genesis.MainActivity");

                driver.set(new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities));
                System.out.println("++++++++++UIAUTOMATOR DRIVER INSTANCE RUNNING++++++++++++");
            }
        }
        ExtentTest parent = reports.createTest(getClass().getName());
        parentTest.set(parent);
    }
}