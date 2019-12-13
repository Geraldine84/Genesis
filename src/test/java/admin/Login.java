package admin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utils.TestBase;
import utils.TestUtils;

public class Login extends  TestBase {
	
	@Test
	public static void navigateToLoginTest() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		// Navigate to Login Page
		String user = "Navigate to Login Page";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView")));
		getDriver().findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView")).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Log In']")));
		getDriver().findElement(By.xpath("//android.widget.TextView[@text='Log In']")).click();
		Thread.sleep(500);
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Log In']", "Log In");
		Thread.sleep(500);
		
	}
	
	@Test
	public void emptyEmailEmptyPassword() throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		// Login with empty email and empty password
		String user = "Login with empty email and empty password fields";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/snackbar_text")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/snackbar_text", "Please fill all input fields");
		Thread.sleep(500);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public void validEmailEmptyPassword(String dataEnv) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("valid_Email_Empty_Password");

		String username = (String) envs.get("username");
        String pw = (String) envs.get("pw");
		
		// Login with valid username and empty password
		String user = "Login with valid email: (" + username + ") and empty password: (" + pw + ")";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys(username);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/snackbar_text")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/snackbar_text", "Please fill all input fields");
		Thread.sleep(500);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public void validEmailInvalidPassword(String dataEnv) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("valid_Email_Invalid_Password");

		String username = (String) envs.get("username");
        String pw = (String) envs.get("pw");
		
		// Login with valid email and invalid password
    	String user = "Login with valid email: (" + username + ") and invalid password: (" + pw + ")";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys(username);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/snackbar_text")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/snackbar_text", "Invalid username or password");
		Thread.sleep(500);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public void emptyEmailValidPassword(String dataEnv) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("Empty_Email_Valid_Password");

		String username = (String) envs.get("username");
        String pw = (String) envs.get("pw");
		
		// Login with empty email and valid password
		String user = "Login with empty email: (" + username + ") and valid password: (" + pw + ")";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys(username);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/snackbar_text")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/snackbar_text", "Please fill all input fields");
		Thread.sleep(500);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public void invalidEmailValidPassword(String dataEnv) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("Invalid_Email_Valid_Password");

		String username = (String) envs.get("username");
        String pw = (String) envs.get("pw");
		
		// Login with invalid email and valid password
		String user = "Login with invalid email: (" + username + ") and valid password: (" + pw + ")";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys(username);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/snackbar_text")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/snackbar_text", "User not found");
		Thread.sleep(500);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public void inCorrectDetailsFormatTest(String dataEnv) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("incorrect_details_format");

		String username = (String) envs.get("username");
        String pw = (String) envs.get("pw");
		
		// Login with incorrect details format
		String user = "Login with incorrect email format: (" + username + ") and password: (" + pw + ")";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys(username);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/snackbar_text")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/snackbar_text", "Invalid input was provided");
		Thread.sleep(500);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public void nonExistingDetailsTest(String dataEnv) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("Non_Existing_Details");

		String username = (String) envs.get("username");
        String pw = (String) envs.get("pw");
		
		// Login with non existing details
		String user = "Login with non-existing email: (" + username + ") and non-existing password: (" + pw + ")";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys(username);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/snackbar_text")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/snackbar_text", "User not found");
		Thread.sleep(500);
		
		// Press back button
		getDriver().pressKeyCode(4);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public void validEmailValidPassword(String dataEnv, String testVal) throws Exception {
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get(testVal);

		String username = (String) envs.get("username");
        String pw = (String) envs.get("pw");
		
		// Login with valid email and valid password
		String user = "Login with valid email: (" + username + ") and valid password: (" + pw + ")";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys(username);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		Thread.sleep(500);
	}
	
	@Parameters({ "dataEnv" })
	@Test
	public void passwordVisibilityButtonTest(String dataEnv) throws InterruptedException, IOException, ParseException {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("Invalid_Email_Valid_Password");

		String pw = (String) envs.get("pw");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/text_input_password_toggle")));
		Thread.sleep(500);
		String error = "Password Visibility Test. Password Value: (" + pw + ")";
		Markup e = MarkupHelper.createLabel(error, ExtentColor.GREEN);
		testInfo.get().info(e);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		Thread.sleep(500);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/text_input_password_toggle")).click();
		Thread.sleep(1000);
		String visiblePassword = getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).getText();
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/passwordEditText", pw);
		Assert.assertEquals(visiblePassword, pw);
		Thread.sleep(1000);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public void keepMeLoginTest(String dataEnv) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		if(dataEnv.equalsIgnoreCase("debugData")) {
			path = new File(classpathRoot, "debugData/test.conf.json");
		}else {
			path = new File(classpathRoot, "prodData/test.conf.json");
		}
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("login_Operator");

		String username = (String) envs.get("username");
        String pw = (String) envs.get("pw");
		
		// Login with valid email and valid password
		String user = "Login with valid email: (" + username + ") and valid password: (" + pw + ")";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.BLUE);
		testInfo.get().info(u);

		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).sendKeys(username);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).clear();
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/passwordEditText")).sendKeys(pw);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/checkBox")).click();
		Thread.sleep(500);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/signinButton")).click();
		Thread.sleep(500);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/textView8")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/textView8", "Dashboard");
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/textView25", "Capture");
		Thread.sleep(500);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/menu_account")).click();
		Thread.sleep(500);
		
		// Close app
		logOutTest();
		
		// Launch app
		String user1 = "Launch app after clicking the 'Remember me' button";
		Markup l = MarkupHelper.createLabel(user1, ExtentColor.ORANGE);
		testInfo.get().info(l);
		
		Thread.sleep(500);
		String user2 = "Assert Login details";
		Markup b = MarkupHelper.createLabel(user2, ExtentColor.BLUE);
		testInfo.get().info(b);
		String email = getDriver().findElement(By.id("com.seamfix.nimc_apk:id/emailEditText")).getText();
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/emailEditText", email);
		Thread.sleep(500);
		
	}
	
	@Parameters({ "dataEnv" })
	@Test
	public void whitespaceSensitivityTest(String dataEnv) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		// Login with white spaced email
		String whiteSpace = "Whitespace Sensitivity Test";
		Markup ws = MarkupHelper.createLabel(whiteSpace, ExtentColor.ORANGE);
		testInfo.get().info(ws);
		
		validEmailValidPassword(dataEnv, "Case_Sensitive_Details");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/textView8")));
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/textView8", "Dashboard");
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/textView25", "Capture");
		Thread.sleep(500);
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/menu_account")).click();
		Thread.sleep(500);
		logOutTest();
	}
	
	public static void logOutTest() throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		String user = "Log Out Test";
		Markup u = MarkupHelper.createLabel(user, ExtentColor.ORANGE);
		testInfo.get().info(u);
		
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/sign_outTV")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		TestUtils.assertSearchText("ID", "android:id/alertTitle", "Sign out");
		TestUtils.assertSearchText("ID", "android:id/message", "Proceed to sign out?");
		TestUtils.assertSearchText("ID", "android:id/button2", "No");
		TestUtils.assertSearchText("ID", "android:id/button1", "Yes");
		
		// Click on 'NO' Button test
		String noButton = "Click on 'NO' Button Test";
		Markup nb = MarkupHelper.createLabel(noButton, ExtentColor.GREEN);
		testInfo.get().info(nb);
		getDriver().findElement(By.id("android:id/button2")).click();
		Thread.sleep(500);
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/changePasswordTV", "Change password");
		TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/aboutTV", "About");
		getDriver().findElement(By.id("com.seamfix.nimc_apk:id/sign_outTV")).click();
		Thread.sleep(1000);
		
		// Click on 'YES' Button test
		String yesButton = "Click on 'YES' Button Test";
        Markup yb = MarkupHelper.createLabel(yesButton, ExtentColor.GREEN);
        testInfo.get().info(yb);
        getDriver().findElement(By.id("android:id/button1")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.seamfix.nimc_apk:id/imageViewPadlock")));
        Thread.sleep(500);
        TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/textView3", "Sign in with");
        TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/textView5", "Password");
        TestUtils.assertSearchText("ID", "com.seamfix.nimc_apk:id/textView6", "Fingerprint");
        Thread.sleep(1000);
	}
}
