package admin;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utils.TestBase;
import utils.TestUtils;

public class Restaurant  extends TestBase{
	
	@Test
	public static void navigateToRestaurantPage() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		String wel = "Navigate to Restaurant Page";
		Markup u = MarkupHelper.createLabel(wel, ExtentColor.BLUE);
		testInfo.get().info(u);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome!']")));
		Thread.sleep(500);
		getDriver().findElement(By.xpath("//android.view.ViewGroup[@index='2']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Featured Meal']")));
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Featured Meal']", "Featured Meal");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Menu']", "Menu");
		Thread.sleep(1000);
	}
	
	@Parameters({"dataEnv"})
	@Test
	public static void viewFeaturedMeal(String dataEnv) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
//		File path = null;
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		if(dataEnv.equalsIgnoreCase("debugData")) {
//			path = new File(classpathRoot, "debugData/test.conf.json");
//		}else {
//			path = new File(classpathRoot, "prodData/test.conf.json");
//		}
		
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(dataEnv + "/test.conf.json"));
		
//		JSONParser parser = new JSONParser();
//		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("Restaurant");

		String searchText = (String) envs.get("searchText");
        //String pw = (String) envs.get("pw");
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.ProgressBar")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Fried Rice']")));

		String wel = "To ensure a user is able to see featured meal with the name and price.";
		Markup u = MarkupHelper.createLabel(wel, ExtentColor.BLUE);
		testInfo.get().info(u);
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Fried Rice']", "Fried Rice");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='₦700']", "₦700");
		Thread.sleep(500);
		
		String wel1 = "To ensure a user is able to click on a featured meal and land on the dedicated food page";
		Markup d = MarkupHelper.createLabel(wel1, ExtentColor.BLUE);
		testInfo.get().info(d);
		getDriver().findElement(By.xpath("//android.widget.TextView[@text='Fried Rice']")).click();
		newDevice();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Set Delivery Location']")));
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Fried Rice']", "Fried Rice");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Set Delivery Location']", "Set Delivery Location");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Price: ₦700']", "Price: ₦700");
		Thread.sleep(500);
		
		String wel2 = "To ensure a user is able set a delivery location";
		Markup a = MarkupHelper.createLabel(wel2, ExtentColor.BLUE);
		testInfo.get().info(a);
		getDriver().findElement(By.xpath("//android.widget.TextView[@text='Your Location']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='Search here...']")));
		getDriver().findElement(By.xpath("//android.widget.EditText[@text='Search here...']")).sendKeys(searchText);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='Searching for " + searchText + "']")));
		Thread.sleep(500);   
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Agungi']", "Agungi");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Agungi Lekki, Lagos']", "Agungi Lekki, Lagos");
		getDriver().findElement(By.xpath("//android.widget.TextView[@text='Done']")).click();
		Thread.sleep(500);
		
		// Click on back arrow
		getDriver().findElement(By.xpath("//android.widget.Button[@content-desc=', back']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Menu']")));
		Thread.sleep(500);
		
		String wel3 = "To ensure all food categories are displayed.";
		Markup v = MarkupHelper.createLabel(wel3, ExtentColor.BLUE);
		testInfo.get().info(v);
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='All Products']", "All Products");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Bread']", "Bread");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Drinks']", "Drinks");
		Thread.sleep(500);
		HomeScreen.viewSpecialOrders();
		
	}

	@Test
	public static void newDevice() throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		try {
		if(getDriver().findElement(By.xpath("//android.widget.FrameLayout")).isDisplayed()) {
			TestUtils.assertSearchText("ID", "com.android.packageinstaller:id/permission_message", "Allow Genesis to access this device's location?");
			getDriver().findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Set Delivery Location']")));
		}
		} catch (Exception e) {
			
		}
	}
}
