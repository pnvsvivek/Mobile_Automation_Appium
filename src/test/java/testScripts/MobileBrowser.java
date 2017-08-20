package testScripts;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * This class is for illustrating the writing of test scripts which includes setup for Appium for android browser applications on mobile
 * @author p.nvs.vivek
 *
 */
public class MobileBrowser {
	
	AppiumDriver<WebElement> driver;
	DesiredCapabilities capabilities;
	
	@BeforeMethod
	public void setUpAppium() throws MalformedURLException
	{
		String platform = "Android";
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "ZX1D64B73P");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("noReset",true);
		String url = "http://0.0.0.0:4723/wd/hub";
		
		if(platform.equalsIgnoreCase("android"))
		{
			driver = new AndroidDriver<WebElement>(new URL(url), capabilities);
		}
	}
	
	@AfterMethod
	public void tearAppium()
	{
		driver.quit();
	}
	
	@Test
	public void handleAlert() throws InterruptedException
	{
		driver.get("http://demo.guru99.com/selenium/delete_customer.php");
		Thread.sleep(2000);
		//Below elements are found by remotely debugging device from development machine
		driver.findElement(By.name("cusid")).sendKeys("53920");
		driver.findElement(By.name("submit")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("Alert is handled");
	}
}
