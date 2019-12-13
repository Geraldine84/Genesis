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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Special Offers']")));
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
		getDriver().findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/"
				+ "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/"
				+ "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/"
				+ "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/"
				+ "android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Featured Meal']")));
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Featured Meal']", "Featured Meal");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Menu']", "Menu");
		Thread.sleep(500);
		
		// Click on back arrow
		getDriver().findElement(By.xpath("//android.widget.Button[@content-desc='Go back']")).click(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome!']")));
		Thread.sleep(500);
		
		// Hotel Menu
		String wel1 = "Hotel Menu";
		Markup d = MarkupHelper.createLabel(wel1, ExtentColor.BLUE);
		testInfo.get().info(d);
		getDriver().findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/"
				+ "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/"
				+ "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/"
				+ "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/"
				+ "android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Our Hotels']")));
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Our Hotels']", "Our Hotels");
		Thread.sleep(500);

		// Click on back arrow
		getDriver().findElement(By.xpath("//android.widget.Button[@content-desc='Go back']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome!']")));
		Thread.sleep(500);

		// Cinemas Menu
		String wel2 = "Cinemas Menu";
		Markup v = MarkupHelper.createLabel(wel2, ExtentColor.BLUE);
		testInfo.get().info(v);
		getDriver().findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/"
				+ "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/"
				+ "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/"
				+ "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/"
				+ "android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Movies']")));
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Movies']", "Movies");
		TestUtils.assertSearchText("XPATH", "//android.widget.TextView[@text='Now Showing']", "Now Showing");
		Thread.sleep(500);
		
		// Click on back arrow
		getDriver().findElement(By.xpath("//android.widget.Button[@content-desc='Go back']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome!']")));
		Thread.sleep(500);
	}
}
