package demoWebShop;

	import java.io.File;
	import java.io.IOException;

	import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.Point;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

import project.DemoShop1;

	public class Register {
		static Logger log=Logger.getLogger(DemoShop1.class.getName());

		WebDriver d;
		@BeforeSuite
		public void openBrowser()
		{
			System.setProperty("webdriver.chrome.driver", "F:\\Java\\SeleniumJar\\Driver Jar Files\\chromedriver.exe");
			d=new ChromeDriver();
			log.debug("open browser");
		}
		
		@Parameters({"url"})
		@BeforeTest
		public void webPage(String url)
		{
			d.get(url);
		}
		@BeforeClass
		public void max()
		{
			Point p=new Point(100, 100);
			d.manage().window().setPosition(p);
		}
		@Parameters({"Fname","Lname","email","pass","Cpass"})
		@Test
		public void register(String a, String b, String c,String e, String f)
		{
			d.findElement(By.xpath("//div[@class='header-links']/child ::ul/child ::li[1]/child ::a")).click();
			d.findElement(By.cssSelector("input#FirstName")).sendKeys(a);
			d.findElement(By.cssSelector("input#LastName")).sendKeys(b);
			d.findElement(By.cssSelector("input#Email")).sendKeys(c);
			d.findElement(By.cssSelector("input#Password")).sendKeys(e);
			d.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(f);
			
		}
		
		@AfterMethod
		public void screenShot() throws IOException	
		{
			File screenshotAs = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			FileUtils.copyDirectory(screenshotAs, new File("F:\\Java\\SeleniumJar\\ScreenShots"));
		}
	

}
