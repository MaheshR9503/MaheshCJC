package DemoGuru;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightBooking {
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
	
	@BeforeClass
	public void max()
	{
		d.manage().window().maximize();
		
	}
	
	@Test(priority = 1)
	@Parameters({"first","last","phone","email","add","city","state","post","email","pass","confirm"})
	public void register(String a,String b,String c,String e,String f,String g,String h,String i,String j,String k,String l)
	{
//		d.findElement(By.xpath("a[href='register.php']")).click();
		d.findElement(By.cssSelector("input[name='firstName']")).sendKeys(a);
		d.findElement(By.cssSelector("input[name='lastName']")).sendKeys(b);
		d.findElement(By.cssSelector("input[name='phone']")).sendKeys(c);
		d.findElement(By.cssSelector("input[name='userName']")).sendKeys(e);
		d.findElement(By.cssSelector("input[name='address1']")).sendKeys(f);
		d.findElement(By.cssSelector("input[name='city']")).sendKeys(g);
		d.findElement(By.cssSelector("input[name='state']")).sendKeys(h);
		d.findElement(By.cssSelector("input[name='postalCode']")).sendKeys(i);
		d.findElement(By.cssSelector("input[name='email']")).sendKeys(j);
		d.findElement(By.cssSelector("input[name='password']")).sendKeys(k);
		d.findElement(By.cssSelector("input[name='confirmPassword']")).sendKeys(l);
		d.findElement(By.cssSelector("input[name='submit']")).click();
	}
	
	@Parameters({"id","pw"})
	@Test(priority = 2)
	public void logIn(String un, String psw)
	{	d.findElement(By.xpath("//a[@href='login.php']")).click();
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		d.findElement(By.xpath("//input[@name='userName']")).sendKeys(un);
		d.findElement(By.xpath("//input[@name='password']")).sendKeys(psw);
		d.findElement(By.xpath("//input[@name='submit']")).click();
	}
}
