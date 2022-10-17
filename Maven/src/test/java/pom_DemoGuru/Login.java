package pom_DemoGuru;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Login {

	WebDriver d;
	
	public Login(WebDriver d)
	{
		this.d=d;
	}
	
	
	By user=By.name("userName");
	By pass=By.name("password");
	By butt=By.name("submit");
	
	public void loginCheck(String a, String b)
	{
		
		d.findElement(user).sendKeys(a);
		d.findElement(pass).sendKeys(b);
		d.findElement(butt).click();
	}
	
	
	By fname=By.cssSelector("input[name='firstName']");
	By lname=By.cssSelector("input[name='lastName']");
	By phone=By.cssSelector("input[name='phone']");
	By user1=By.cssSelector("input[name='userName']");
	By add1=By.cssSelector("input[name='address1']");
	By city=By.cssSelector("input[name='city']");
	By state=By.cssSelector("input[name='state']");
	By post=By.cssSelector("input[name='postalCode']");
	By country=By.name("country");
	By email=By.cssSelector("input[name='email']");
	By password=By.cssSelector("input[name='password']");
	By confirm=By.cssSelector("input[name='confirmPassword']");
	By submit=By.name("submit");
	
	public void register(String a,String b,String c,String d1,String e,String f,String g,String h,String i,String j,String k,String l)
	{
		d.findElement(fname).sendKeys(a);
		d.findElement(lname).sendKeys(b);
		d.findElement(phone).sendKeys(c);
		d.findElement(user1).sendKeys(d1);
		d.findElement(add1).sendKeys(e);
		d.findElement(city).sendKeys(f);
		d.findElement(state).sendKeys(g);
		d.findElement(post).sendKeys(h);
		d.findElement(country).sendKeys(l);
		d.findElement(email).sendKeys(i);
		d.findElement(password).sendKeys(j);
		d.findElement(confirm).sendKeys(k);
		d.findElement(submit).click();
	}
	
	
	By round=By.cssSelector("input[value='oneway']");
	By passcount=By.name("passCount");
	
	By from =By.name("fromPort");
	By fmonth=By.name("fromMonth");
	By fDay=By.name("fromDay");
	By to=By.name("toPort");
	By toMonth=By.name("toMonth");
	By toDay=By.name("toDay");
	By service=By.name("servClass");
	By airline=By.name("airline");
	By book=By.name("findFlights");
	
	public void flight() throws IOException
	{
		FileInputStream fi=new FileInputStream("F:\\Java\\SeleniumJar\\ScreenShots\\Flight.txt");
		
		Properties pr=new Properties();
		pr.load(fi);
		d.findElement(round).click();
		WebElement findElement = d.findElement(passcount);
		Select s1=new Select(findElement);
		s1.selectByVisibleText(pr.getProperty("passanger"));
		WebElement findElement2 = d.findElement(from);
		Select s2=new Select(findElement2);
		s2.selectByVisibleText(pr.getProperty("from"));
		
		WebElement findElement3 = d.findElement(fmonth);
		Select s3=new Select(findElement3);
		s3.selectByVisibleText(pr.getProperty("fmonth"));
		
		WebElement findElement4 = d.findElement(fDay);
		Select s4=new Select(findElement4);
		s4.selectByVisibleText(pr.getProperty("fday"));
		
		WebElement findElement5 = d.findElement(to);
		Select s5=new Select(findElement5);
		s5.selectByVisibleText(pr.getProperty("to"));
		
		WebElement findElement6 = d.findElement(toMonth);
		Select s6=new Select(findElement6);
		s6.selectByVisibleText(pr.getProperty("toMonth"));
		
		WebElement findElement7 = d.findElement(toDay);
		Select s7=new Select(findElement7);
		s7.selectByVisibleText(pr.getProperty("toDay"));
		
		d.findElement(service).click();
		WebElement findElement8 = d.findElement(airline);
		Select s8=new Select(findElement8);
		s8.selectByVisibleText(pr.getProperty("airline"));
		
		d.findElement(book).click();
	}
	
	By booking=By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > table > tbody > tr:nth-child(1) > td > p > font > b > font:nth-child(1)");
	
	
	public String m1()
	{
		String text = d.findElement(booking).getText();
		return text;
	}
}
