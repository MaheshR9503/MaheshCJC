package demoWebShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Book_purchase {

	WebDriver d;
	@BeforeSuite
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Java\\SeleniumJar\\Driver Jar Files\\chromedriver.exe");
		d=new ChromeDriver();
	}
	
	@Parameters({"url"})
	@BeforeTest
	public void getUrl(String url)
	{
		d.get(url);
	}
	
	@BeforeTest
	public void maxWindow()
	{
		d.manage().window().maximize();
	}
	
	@Parameters({"Fname","Lname","email","pass","Cpass"})
	@Test(priority = 1)
	public void register(String a,String b,String c,String e,String f)
	{
		d.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")).click();
		d.findElement(By.cssSelector("input#FirstName")).sendKeys(a);
		d.findElement(By.cssSelector("input#LastName")).sendKeys(b);
		d.findElement(By.cssSelector("input#Email")).sendKeys(c);
		d.findElement(By.cssSelector("input#Password")).sendKeys(e);
		d.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(f);
	}
	
	
	
	@Parameters({"id","password"})
	@Test(priority = 2)
	public void logIn(String id, String password)
	{	d.findElement(By.xpath("//div[@class='header-links']/child ::ul/child ::li[2]/child ::a")).click();
		d.findElement(By.xpath("//input[@class='email']")).sendKeys(id);
		d.findElement(By.xpath("//input[@class='password']")).sendKeys(password);
		d.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
//		d.findElement(By.xpath("//a[@class='ico-logout']")).click();
	}
	
	@Test(priority = 3)
	public void addCart()
	{
		d.findElement(By.cssSelector("li a[href*='boo']")).click();
		WebElement findElement = d.findElement(By.cssSelector("div select#products-orderby"));
		Select s=new Select(findElement);
		s.selectByIndex(1);
		
		WebElement findElement2 = d.findElement(By.cssSelector("Select[name*='page']"));
		Select s1=new Select(findElement2);
		s1.selectByIndex(0);
		
		WebElement findElement3 = d.findElement(By.cssSelector("Select[name$='ode']"));
		Select s2=new Select(findElement3);
		s2.selectByIndex(0);
		
		d.findElement(By.cssSelector("div.buttons input[value='Add to cart']")).click();
	}
}
