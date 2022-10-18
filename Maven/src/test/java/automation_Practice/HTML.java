package automation_Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HTML {

	 WebDriver d;
	@BeforeSuite
	public void browser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Java\\SeleniumJar\\Driver Jar Files\\chromedriver.exe");
		d=new ChromeDriver();
	}
	
	@BeforeTest
	public void getUrl()
	{
		d.get("file:///F:/dashboard.component.html");
	}
	
	@BeforeClass
	public void max()
	{
		d.manage().window().maximize();
		
	}
	
	@Test(dataProvider = "getData")
	public void logIn(String a,String b,String c,String d1,String e,String f,String h)
	{
		d.findElement(By.cssSelector("input#form3Example1m")).sendKeys(a);
		d.findElement(By.cssSelector("input#form3Example1n")).sendKeys(b);
		d.findElement(By.cssSelector("input#form3Example1m1")).sendKeys(c);
		d.findElement(By.cssSelector("input#form3Example1n1")).sendKeys(d1);
		d.findElement(By.xpath("//*[@id=\"form3Example8\"]")).sendKeys(e);
		d.findElement(By.cssSelector("input[type='number']")).sendKeys(f);
		d.findElement(By.xpath("//*[@id=\"form3Example8\"]")).sendKeys(h);
		d.findElement(By.tagName("button")).click();
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws FileNotFoundException, IOException
	{
		Object[][] o=new Object[2][7];
		
		XSSFWorkbook wb=new XSSFWorkbook(new FileInputStream("F:\\Java\\SeleniumJar\\ScreenShots\\email.xlsx"));
		XSSFSheet sheet = wb.getSheet("HTML");
		int rowNum = sheet.getLastRowNum();
		
		for(int i=0;i<=rowNum;i++)
		{
			XSSFRow row = sheet.getRow(i);
			int col = row.getPhysicalNumberOfCells();
			for(int j=0;j<col;j++)
			{
				XSSFCell cell = row.getCell(j);
				String value = cell.getStringCellValue();
				o[i][j]=value;
				
			}
		}
		
		return o;
		
	}
}
