package faceBook;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignUp {
	WebDriver d;
	@BeforeSuite
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Java\\SeleniumJar\\Driver Jar Files\\chromedriver.exe");
		d=new ChromeDriver();
	}
	@Parameters({"url"})
	@BeforeTest
	public void openUrl(String url)
	{
		d.get(url);
	}

	@BeforeMethod
	public void windowSize()
	{
		d.manage().window().maximize();
	}
	@Parameters({"Fname","Lname","email","Confirm","password"})
	@Test
	public void signUp(String a,String b,String c,String e,String f) throws InterruptedException
	{
		d.findElement(By.xpath("//div[@class='_6ltg'][2]/child ::a")).click();
		d.findElement(By.xpath("//input[@name='firstname']")).sendKeys(a);
		d.findElement(By.xpath("//input[@name='lastname']")).sendKeys(b);
		d.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys(c);
		Thread.sleep(2000);
		d.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys(e);
		d.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys(f);
		WebElement year = d.findElement(By.xpath("//@select[@name='birthday_year']"));
		Select s1=new Select(year);
		s1.selectByIndex(23);
		
		d.findElement(By.xpath("//span[@data-name='gender_wrapper']/child ::span[2]/child ::input")).click();
		
	}
	
}
