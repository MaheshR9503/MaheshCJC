package automation_Practice;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
	public void getUrl(String url)
	{
		d.get(url);
		d.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	}
	
	@BeforeClass
	public void max()
	{
		d.manage().window().maximize();
	}
	
	@BeforeMethod
	public void cookies()
	{
		Set<Cookie> cookies = d.manage().getCookies();
		int size = cookies.size();
		System.out.println("total number of cookies= "+size);
	}
	
	@Parameters({"em","Fname","Lname","email","password","day","month","year","first","last","company","address1","address2","city","state","postcode","country","other","phone","mobile","alias"})
	@Test
	public void signUp(String a,String b,String c,String e,String f,String g,String h,String i,String j,String k,String l,String m,String n,String o,String p,String q,String r,String other,String t,String u, String v)
	{
		String title = d.getTitle();
		System.out.println(title);
		
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(title, "My store");
		
		d.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		d.findElement(By.cssSelector("a[class='login']")).click();
		d.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		d.findElement(By.cssSelector("input[name='email_create']")).sendKeys(a);
		d.findElement(By.cssSelector("button[name='SubmitCreate']")).click();
		d.findElement(By.cssSelector("input#id_gender1")).click();
		d.findElement(By.cssSelector("input#customer_firstname")).sendKeys(b);
		d.findElement(By.cssSelector("input#customer_lastname")).sendKeys(c);
		d.findElement(By.cssSelector("input#email")).sendKeys(e);
		d.findElement(By.cssSelector("input#passwd")).sendKeys(f);
		WebElement day = d.findElement(By.cssSelector("select#days"));
		Select s=new Select(day);
		s.selectByValue(g);
		
		WebElement month = d.findElement(By.cssSelector("select#months"));
		Select s1=new Select(month);
		s1.selectByVisibleText(h);
		
		WebElement year = d.findElement(By.cssSelector("select#years"));
		Select s2=new Select(year);
		s2.selectByVisibleText(i);
		
		d.findElement(By.cssSelector("input#optin")).click();
		d.findElement(By.cssSelector("input#firstname")).sendKeys(j);
		d.findElement(By.cssSelector("input#lastname")).sendKeys(k);
		d.findElement(By.cssSelector("input#company")).sendKeys(l);
		d.findElement(By.cssSelector("input#address1")).sendKeys(m);
		d.findElement(By.cssSelector("input#address2")).sendKeys(n);
		d.findElement(By.cssSelector("input#city")).sendKeys(o);
		WebElement state = d.findElement(By.cssSelector("select#id_state"));
		Select s3=new Select(state);
		s3.selectByVisibleText(p);
		
		d.findElement(By.cssSelector("input#postcode")).sendKeys(q);
		
		WebElement country = d.findElement(By.cssSelector("select#id_country"));
		Select s4=new Select(country);
		s4.selectByVisibleText(r);
		
		d.findElement(By.cssSelector("textarea#other")).sendKeys(other);
		d.findElement(By.cssSelector("input#phone")).sendKeys(t);
		d.findElement(By.cssSelector("input#phone_mobile")).sendKeys(u);
		d.findElement(By.cssSelector("input#alias")).sendKeys(v);
		d.findElement(By.cssSelector("button#submitAccount")).click();
		
	}
	
	@AfterMethod
	public void screenShot() throws IOException
	{
		File screenshotAs = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("F:\\Java\\SeleniumJar\\ScreenShots\\abd.jpg"));
	}
	
	@AfterClass
	public void asserts()
	{
		String title = d.getTitle();
		
		System.out.println(title);
		
		Assert.assertEquals(title, "Login my store");
	}
	
	@AfterSuite
	public void end()
	{
		System.out.println("----------End of Testing---------------");
	}
}
