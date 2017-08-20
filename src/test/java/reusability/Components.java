package reusability;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Components {
	
	public static String getProperties(String keys) throws IOException
	{
		File file = new File(System.getProperty("user.dir")+"/"+"config.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(keys);
	}

	public static String screenCapture(WebDriver driver,String testcasename) throws IOException
	{
		TakesScreenshot scr = (TakesScreenshot)driver;
		File scrFile = scr.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy hh_mm_ss a");
		String cal_date = sdf.format(new Date());
		String finalPath = System.getProperty("user.dir")+"\\"+Components.getProperties("screenprints")+"\\"+testcasename+"\\"+cal_date+"."+"jpeg";
		File destFile = new File(finalPath);
		FileUtils.copyFile(scrFile, destFile);
		return finalPath;
	}
	
	public static String pathforReport() throws IOException
	{
		return System.getProperty("user.dir")+"\\"+Components.getProperties("testReport")+"\\"+"Mobile_test_report.html";
	}
	
	public static boolean verifyAccountExistence(String accountEmail, List<WebElement> accountText) throws IOException
	{
		boolean flag = false;
		for(int i=0;i<accountText.size();i++)
		{
		if(accountText.get(i).getText().contains(accountEmail))
		{
			flag=true;
			break;
		}
		}
		return flag;
	}
	
	public static boolean verifyElementExistence(List<WebElement> element) throws InterruptedException
	{
		boolean flag = false;
		try
		{
		if(element.size()!=0)
		{
			flag = true;
		}
		else
		{
			flag = false;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
