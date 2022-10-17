package DemoGuru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Register {
	WebDriver d;
	
	@BeforeSuite
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Java\\SeleniumJar\\Driver Jar Files\\chromedriver.exe");
		d=new ChromeDriver();
	}
	
	@Parameters({"url"})
	@BeforeTest
	public void openPage(String url)
	{
		d.get(url);
	}
	
	@BeforeClass
	public void max()
	{
		d.manage().window().maximize();
	}
	
	@BeforeMethod
	public void m1()
	{
		System.out.println("----------------------------------");
	}
	
	@Parameters({"Fname","Lname","phone","userName","address","City","State","postcode","email","password","confirm"})
	@Test
	public void reg(String Fname,String Lname, String phone,String username,String address, String city, String state,String postcode,String email, String password,String confirm)
	{
//		d.findElement(By.xpath("//tbody/child ::tr/child ::td[@class='mouseOut'][2]/child ::a")).click();
		d.findElement(By.cssSelector("input[name='firstName']")).sendKeys(Fname);
		d.findElement(By.cssSelector("input[name='lastName']")).sendKeys(Lname);
		d.findElement(By.cssSelector("input[name='phone']")).sendKeys(phone);
		d.findElement(By.cssSelector("input[name='userName']")).sendKeys(username);
		d.findElement(By.cssSelector("input[name='address1']")).sendKeys(address);
		d.findElement(By.cssSelector("input[name='city']")).sendKeys(city);
		d.findElement(By.cssSelector("input[name='state']")).sendKeys(state);
		d.findElement(By.cssSelector("input[name='postalCode']")).sendKeys(postcode);
		d.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
		d.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		d.findElement(By.cssSelector("input[name='confirmPassword']")).sendKeys(confirm);
	}
	
	@AfterMethod
	public void m2()
	{
		System.out.println("---------------------------");
	}
}
