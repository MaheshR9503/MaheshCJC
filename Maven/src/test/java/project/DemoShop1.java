package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoShop1 {
	static Logger log=Logger.getLogger(DemoShop1.class.getName());
	WebDriver d;
	@BeforeSuite
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Java\\SeleniumJar\\Driver Jar Files\\chromedriver.exe");
		d=new ChromeDriver();
		
		log.debug("Browser loaded");
	}

	@Parameters({"url"})
	@BeforeTest
	public void getUrl(String url)
	{
		d.get(url);
		
		log.debug("webpage loaded");
	}

	@BeforeTest
	public void maxWindow()
	{
		d.manage().window().maximize();
	}
	
	@Test(priority = 1, dataProvider = "getData")
	public void regis(String a,String b,String c,String e,String f)
	{
		d.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")).click();
		
		d.findElement(By.cssSelector("input#FirstName")).sendKeys(a);
		d.findElement(By.cssSelector("input#LastName")).sendKeys(b);
		d.findElement(By.cssSelector("input#Email")).sendKeys(c);
		d.findElement(By.cssSelector("input#Password")).sendKeys(e);
		d.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(f);
		d.findElement(By.id("register-button")).click();
		
		log.error("Email id already exsits");

	}
	
	@Parameters({"id","password"})
	@Test(priority = 2)
	public void logIn(String id,String password)
	{
		d.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		d.findElement(By.cssSelector("a[href='/login']")).click();
		d.findElement(By.xpath("//input[@class='email']")).sendKeys(id);
		d.findElement(By.xpath("//input[@class='password']")).sendKeys(password);
		d.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		log.error("If login failed please check enter valid email id and password");
	}
	
	@Test(priority = 3)
	public void addtoCart()
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
		
		
//		d.findElement(By.cssSelector("body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div.center-2 > div.page.category-page > div.page-body > div.product-grid > div:nth-child(1) > div > div.details > div.add-info > div.buttons > input")).click();
		d.findElement(By.cssSelector("body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div.center-2 > div.page.category-page > div.page-body > div.product-grid > div:nth-child(3) > div > div.details > div.add-info > div.buttons > input")).click();
		log.debug("Select items to add in cart");
	}
	
	@Test(priority = 4)
	public void cart()
	{
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		d.findElement(By.cssSelector("#topcartlink > a")).click();
		d.findElement(By.cssSelector("body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div > div > div.page-body > div > form > table > tbody > tr:nth-child(1) > td.remove-from-cart > input[type=checkbox]")).click();
		d.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/form/table/tbody/tr[2]/td[1]/input")).click();
		d.findElement(By.xpath("//input[@id='termsofservice']")).click();
		d.findElement(By.cssSelector("button#checkout")).click();
		
		log.info("Check that elemt select is you want to buy before completing payment");
	}
	
	@Test(priority = 5)
	public void shippingAddress() throws IOException
	{
		FileInputStream fi=new FileInputStream("F:\\Java\\SeleniumJar\\address.txt");
		Properties pr=new Properties();
		pr.load(fi);
		WebElement nw = d.findElement(By.cssSelector("select#billing-address-select"));
		Select s1=new Select(nw);
		s1.selectByVisibleText("New Address");
		
		WebElement country = d.findElement(By.cssSelector("select#BillingNewAddress_CountryId"));
		Select s=new Select(country);
		s.selectByVisibleText(pr.getProperty("country"));
		
		d.findElement(By.cssSelector("input#BillingNewAddress_City")).sendKeys(pr.getProperty("city"));
		d.findElement(By.cssSelector("input#BillingNewAddress_Address1")).sendKeys(pr.getProperty("Add1"));
		d.findElement(By.cssSelector("input#BillingNewAddress_Address2")).sendKeys(pr.getProperty("Add2"));
		d.findElement(By.cssSelector("input#BillingNewAddress_ZipPostalCode")).sendKeys("zip");
		d.findElement(By.cssSelector("input#BillingNewAddress_PhoneNumber")).sendKeys("phone");
		
		d.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/input")).click();
		d.findElement(By.cssSelector("input#PickUpInStore")).click();
		d.findElement(By.cssSelector("#shipping-buttons-container > input")).click();
		d.findElement(By.cssSelector("input[value='Payments.CashOnDelivery']")).click();
		d.findElement(By.cssSelector("#payment-method-buttons-container > input")).click();
		d.findElement(By.cssSelector("#payment-info-buttons-container > input")).click();
		d.findElement(By.cssSelector("input[value='Confirm']")).click();
		
		WebElement findElement = d.findElement(By.xpath("//div[@class='section order-completed']/child ::ul/child ::li[1]"));
		String text = findElement.getText();
		System.out.println("Order id= "+text);
		
		log.fatal("Thankx for purchasing books from our shop your order number is= "+text);
	}
	
	@DataProvider
	public String[][] getData() throws IOException
	{
		
		String[][] s=new String[1][5];
		
		XSSFWorkbook wb =new XSSFWorkbook("F:\\Java\\SeleniumJar\\ScreenShots\\demo.xlsx");
		XSSFSheet sheet = wb.getSheetAt(3);
		XSSFRow row = sheet.getRow(0);
		XSSFCell fname = row.getCell(0);
		XSSFCell lname = row.getCell(1);
		XSSFCell email = row.getCell(2);
		XSSFCell pass = row.getCell(3);
		XSSFCell confirm = row.getCell(4);
		
		s[0][0]=fname.getStringCellValue();
		s[0][1]=lname.getStringCellValue();
		s[0][2]=email.getStringCellValue();
		s[0][3]=pass.getStringCellValue();
		s[0][4]=confirm.getStringCellValue();
		
		return s;
	}
	
	@AfterMethod
	public void screenShot() throws IOException
	{
		File screenshotAs = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(screenshotAs, new File("F:\\Java\\SeleniumJar\\ScreenShots"));
	}

}
