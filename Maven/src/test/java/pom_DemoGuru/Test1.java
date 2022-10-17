package pom_DemoGuru;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 {
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
		d.get("https://demo.guru99.com/test/newtours/register.php");
	}
	
	@BeforeClass
	public void max()
	{
		d.manage().window().maximize();
	}
	
	@Test(priority = 1,dataProvider = "regElement")
	public void reg(String a,String b,String c,String d1,String e,String f,String g,String h,String i,String j,String k,String l)
	{
		Login l1=new Login(d);
		l1.register(a, b, c, d1, e, f, g, h, i, j, k,l);
	}
	
	@Parameters({"login"})
	@Test(priority = 2, dataProvider = "data")
	public void login(String a, String b)
	{
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		d.navigate().to("https://demo.guru99.com/test/newtours/login.php");
		Login l=new Login(d);
		
		l.loginCheck(a,b);
	}
	
	@Parameters({"flightBooking"})
	@Test(priority = 3)
	public void flight() throws IOException
	{	
		d.navigate().to("https://demo.guru99.com/test/newtours/reservation.php");
		Login l=new Login(d);
		l.flight();
		
		String confirm = l.m1();
		
		Assert.assertEquals(confirm, "After flight finder - No Seats Avaialble");
		
	}
	
	
	@DataProvider
	public String[][] data() throws IOException
	{
		String[][] s=new String[1][2];
		
		XSSFWorkbook wb=new XSSFWorkbook("F:\\Java\\SeleniumJar\\ScreenShots\\demo.xlsx");
		XSSFSheet sh = wb.getSheetAt(1);
		int rowNum = sh.getLastRowNum();
		for(int i=0;i<=rowNum;i++)
		{
			XSSFRow row = sh.getRow(i);
			int col = row.getPhysicalNumberOfCells();
			for(int j=0;j<col;j++)
			{
				XSSFCell cell = row.getCell(j);
				
				String s1 = cell.getStringCellValue();
				s[i][j]=s1;
			}
			
		}
		return s;
	}
	
	@DataProvider
	public String[][] regElement() throws IOException
	{
		String[][] s=new String[1][12];
		
		XSSFWorkbook wb=new XSSFWorkbook("F:\\Java\\SeleniumJar\\ScreenShots\\demo.xlsx");
		XSSFSheet sh = wb.getSheetAt(2);
		int rowNum = sh.getLastRowNum();
		for(int i=0;i<=rowNum;i++)
		{
			XSSFRow row = sh.getRow(i);
			int col = row.getPhysicalNumberOfCells();
			for(int j=0;j<col;j++)
			{
				XSSFCell cell = row.getCell(j);
				
				String s1 = cell.getStringCellValue();
				s[i][j]=s1;
			}
			
		}
		return s;
	}
	
}
