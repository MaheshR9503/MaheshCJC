package DemoGuru;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LogIn {
	
	WebDriver d;
	@BeforeSuite(groups = "a")
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Java\\SeleniumJar\\Driver Jar Files\\chromedriver.exe");
		d=new ChromeDriver();
	}
	
	@Parameters({"url"})
	@BeforeTest(groups = "a")
	public void openWeb(String url)
	{
		d.get(url);
	}
	
	@BeforeClass(groups = "a")
	public void maxWin()
	{
		d.manage().window().maximize();
	}
	
	@BeforeMethod
	public void m1()
	{
		System.out.println("----------------------------------");
	}
	
	@Parameters({"UN","pass"})
	@Test(groups = "a")
	public void logIn(String UN, String pass)
	{
		d.findElement(By.xpath("//input[@name='userName']")).sendKeys(UN);
		d.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		d.findElement(By.xpath("//input[@name='submit']")).click();
	}
	
	@AfterMethod
	public void screenShot() throws IOException
	{
		File screenshotAs = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("F:\\Java\\SeleniumJar\\ScreenShots\\d.jpg"));
	}
	
	@AfterSuite(groups = "a")
	public void end()
	{
		System.out.println("End of program");
	}

}
