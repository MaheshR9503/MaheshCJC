package irctc;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Register {
	WebDriver d;

	@BeforeSuite
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Java\\SeleniumJar\\Driver Jar Files\\chromedriver.exe");
		d=new ChromeDriver();
		
	}
	
	@BeforeTest
	public void getUrl()
	{
		d.get("https://www.irctc.co.in/nget/train-search");
	}
	
	@BeforeClass
	public void max()
	{
		d.manage().window().maximize();
	}
	
	
	@Test
	public void registere() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement from = d.findElement(By.cssSelector("#origin > span > input"));
		from.sendKeys("pune");
		String click = Keys.chord(Keys.DOWN,Keys.ENTER);
		from.sendKeys(click);
		
		WebElement to = d.findElement(By.cssSelector("#destination > span > input"));
		to.sendKeys("latur");
		String click1 = Keys.chord(Keys.DOWN,Keys.ENTER);
		to.sendKeys(click1);
		
		d.findElement(By.cssSelector("#journeyQuota > div > div.ui-dropdown-trigger.ui-state-default.ui-corner-right.ng-tns-c65-12")).click();
		d.findElement(By.cssSelector("#journeyQuota > div > div.ng-trigger.ng-trigger-overlayAnimation.ng-tns-c65-12.ui-dropdown-panel.ui-widget.ui-widget-content.ui-corner-all.ui-shadow.ng-star-inserted > div > ul > p-dropdownitem:nth-child(4) > li")).click();
		Thread.sleep(3000);
		
		d.findElement(By.cssSelector("#jDate > span > input")).click();
		while(!d.findElement(By.cssSelector("#jDate > span > div > div > div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all.ng-tns-c58-10 > div")).getText().contains("November"))
		{
			d.findElement(By.cssSelector("#jDate > span > div > div > div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all.ng-tns-c58-10 > a.ui-datepicker-next.ui-corner-all.ng-tns-c58-10.ng-star-inserted > span")).click();
		}
		
		List<WebElement> list = d.findElements(By.cssSelector("#jDate > span > div > div > div.ui-datepicker-calendar-container.ng-tns-c58-10.ng-star-inserted > table > tbody > tr:nth-child(3) > td:nth-child(5) > a"));
		int days = list.size();
		
		for(int i=0;i<days;i++)
		{
			String dy = d.findElements(By.cssSelector("#jDate > span > div > div > div.ui-datepicker-calendar-container.ng-tns-c58-10.ng-star-inserted > table > tbody > tr:nth-child(3) > td:nth-child(5) > a")).get(i).getText();
			
			if(dy.equalsIgnoreCase("20")) {
				
				d.findElements(By.cssSelector("#jDate > span > div > div > div.ui-datepicker-calendar-container.ng-tns-c58-10.ng-star-inserted > table > tbody > tr:nth-child(3) > td:nth-child(5) > a")).get(i).click();
			}
		}
	}
	
	/*
	@Test
	public void windowHandle()
	{
		List<WebElement> links = d.findElements(By.tagName("a"));
		int lcount = links.size();
		System.out.println("Total links= "+lcount);
		
		WebElement footer = d.findElement(By.xpath("//div[@class='footer_dv'] /child ::button[1]"));
		footer.click();
		List<WebElement> al = footer.findElements(By.tagName("a"));
		int flcount = al.size();
		System.out.println("Footer links= "+flcount);
	}
	*/
}
