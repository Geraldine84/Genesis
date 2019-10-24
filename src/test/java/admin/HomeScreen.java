package admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utils.TestBase;
import utils.TestUtils;

public class HomeScreen extends TestBase{
	
	@Test
	public static void assertWelcomePage() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		String wel = "Assert Welcome Page";
		Markup u = MarkupHelper.createLabel(wel, ExtentColor.BLUE);
		testInfo.get().info(u);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome!']")));
		Thread.sleep(500);
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Welcome!']", "Welcome!");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Special Offers']", "Special Offers");
		Thread.sleep(1000);
	}
	
	@Test
	public static void viewSpecialOrders() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		 
		String wel1 = "View Special Orders";
		Markup d = MarkupHelper.createLabel(wel1, ExtentColor.BLUE);
		testInfo.get().info(d);
		getDriver().findElement(By.xpath("//android.widget.TextView[@text='See All>>']")).click(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Special Offers!']")));
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Special Offers']", "Special Offers");
		Thread.sleep(500);
		
		// Click on back arrow
		getDriver().findElement(By.xpath("//android.widget.Button[@content-desc='Go back']")).click(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome!']")));
		Thread.sleep(500);
	}
	
	@Test
	public static void viewVariousMenus() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		
		// Restaurant Menu
		String wel = "Restaurant Menu";
		Markup u = MarkupHelper.createLabel(wel, ExtentColor.BLUE);
		testInfo.get().info(u);
		
		getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='2']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Featured Meal']")));
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Featured Meal']", "Featured Meal");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Menu']", "Menu");
		Thread.sleep(500);
		
		// Click on back arrow
		getDriver().findElement(By.xpath("//android.widget.Button[@content-desc='Go back']")).click(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome!']")));
		Thread.sleep(500);
		
	}
}
