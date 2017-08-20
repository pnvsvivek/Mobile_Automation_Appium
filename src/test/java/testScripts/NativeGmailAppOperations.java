package testScripts;

import java.io.IOException; 
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import reusability.Components;

public class NativeGmailAppOperations {
	
	static ExtentReports report = null;
	ExtentTest test;
	DesiredCapabilities capabilities;
	AppiumDriver<WebElement> driver;
	
	@BeforeSuite
	public void initializeReport() throws IOException
	{
		report = new ExtentReports(Components.pathforReport());
	}
	
	@AfterSuite
	public void flushReport()
	{
		report.flush();
		report.close();
	}
	@BeforeMethod
	public void setupAppium() throws MalformedURLException
	{
		String packagename = "com.google.android.gm";
		String activityName = "com.google.android.gm.GmailActivity";
		String platform = "Android";
		String url = "http://0.0.0.0:4723/wd/hub";
		
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "ZX1D64B73P");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", packagename);
		capabilities.setCapability("appActivity",activityName );
		capabilities.setCapability("appWaitActivity", activityName);
		capabilities.setCapability("noReset", true);

		
		if(platform.equalsIgnoreCase("android"))
		{
			driver = new AndroidDriver<WebElement>(new URL(url), capabilities);
		}
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	/*
	 * This method is for adding personal account in gmail application
	 */
	
	@Test
	public void addingAccountinGoogle() throws IOException
	{
		String tcName = "addingAccountinGoogle";
		try
		{
			test = report.startTest(tcName);
			test.log(LogStatus.INFO, "google application launched",test.addScreenCapture(Components.screenCapture(driver, tcName)));
			WebElement el = driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']"));
			if(el.isEnabled())
			{
				System.out.println("Element is present");
				el.click();
			}
			else
			{
				System.out.println("Element is not present");
			}
		driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
		driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("com.google.android.gm:id/account_list_button")).click();
		List<WebElement> accountText = driver.findElements(By.id("com.google.android.gm:id/account_address"));
		boolean flag = Components.verifyAccountExistence("hotstartest16@outlook.com", accountText);
		if(flag==true)
		{
			System.out.println("Account already added");
			test.log(LogStatus.INFO, "Account already added",test.addScreenCapture(Components.screenCapture(driver, tcName)));
		}
		else
		{
		System.out.println("Account is yet to be added");
		test.log(LogStatus.FAIL, "Account is yet to be added",test.addScreenCapture(Components.screenCapture(driver, tcName)));		
		driver.findElement(By.id("com.google.android.gm:id/add_account_text")).click();
		driver.findElements(By.id("com.google.android.gm:id/radio_button")).get(1).click();
		driver.findElement(By.id("com.google.android.gm:id/suw_navbar_next")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("com.google.android.gm:id/account_email")).sendKeys("hotstartest16@outlook.com");
		driver.findElement(By.id("com.google.android.gm:id/suw_navbar_next")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("i0118")).sendKeys("vivek123");
		test.log(LogStatus.INFO, "Email and password are entered",test.addScreenCapture(Components.screenCapture(driver, tcName)));
		Thread.sleep(5000);
		driver.findElement(By.id("idSIButton9")).click();
		Thread.sleep(20000);
		driver.findElement(By.id("com.google.android.gm:id/suw_navbar_next")).click();
		driver.findElement(By.id("com.google.android.gm:id/suw_navbar_next")).click();
		boolean accountFlag = Components.verifyAccountExistence("hotstartest14@yahoo.com", accountText);
		if(accountFlag==true)
		{
			System.out.println("Account successfully added");
			test.log(LogStatus.PASS, "Account successfully added",test.addScreenCapture(Components.screenCapture(driver, tcName)));
		}
		else
		{
			System.out.println("Account is not added");
			test.log(LogStatus.FAIL, "Account is not added",test.addScreenCapture(Components.screenCapture(driver, tcName)));
		}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		report.endTest(test);
	}
	
	/*
	 * This method is for swiping for gmail message from bottom to top
	 */
	@Test
	public void gmailswipeAction() throws IOException
	{
		String tcName = "gmailswipeAction";
		test = report.startTest(tcName);
		test.log(LogStatus.INFO, "google application launched",test.addScreenCapture(Components.screenCapture(driver, tcName)));
		Dimension dimension = driver.manage().window().getSize();
		int endy = (int) (dimension.height*0.2);
		int starty = (int) (dimension.height*0.7);
		driver.swipe(0, starty, 0, endy, 1000);
		test.log(LogStatus.PASS, " Swiped gmail messages ",test.addScreenCapture(Components.screenCapture(driver, tcName)));
		report.endTest(test);
	}
	
	/*
	 * This method is for removing account from gmail app
	 */
	@Test
	public void removeAccountingoogle()
	{
		String tcName = "removeAccountingoogle";
		try
		{
			test = report.startTest(tcName);
			test.log(LogStatus.INFO, " Swiped gmail messages ",test.addScreenCapture(Components.screenCapture(driver, tcName)));
			WebElement el = driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']"));
			if(el.isEnabled())
			{
				System.out.println("Element is present");
				el.click();
			}
			else
			{
				System.out.println("Element is not present");
			}
		driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
		driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("com.google.android.gm:id/account_list_button")).click();
		driver.findElement(By.id("com.google.android.gm:id/manage_accounts_text")).click();
		List<WebElement> personalEmail = driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'Personal (IMAP)')]"));
		if(Components.verifyElementExistence(personalEmail)==true)
		{
		test.log(LogStatus.PASS, " Other accounts apart from original account are present  ",test.addScreenCapture(Components.screenCapture(driver, tcName)));	
		personalEmail.get(0).click();
		List<WebElement> accountText = driver.findElements(By.id("android:id/title"));
		boolean flag = Components.verifyAccountExistence("hotstartest16@outlook.com", accountText);
		if(flag==true)
		{
		driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='More options']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Remove account')]")).click();
		test.log(LogStatus.PASS, " Account removed ",test.addScreenCapture(Components.screenCapture(driver, tcName)));
		driver.findElement(By.id("android:id/button1")).click();
		}
		else
		{
			System.out.println("Account is not there to remove");
			test.log(LogStatus.INFO, " Account is not there to remove ",test.addScreenCapture(Components.screenCapture(driver, tcName)));
		}
		}
		else
		{
			test.log(LogStatus.FAIL, " Other accounts apart from original account not present  ",test.addScreenCapture(Components.screenCapture(driver, tcName)));	
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		report.endTest(test);
	}
}
