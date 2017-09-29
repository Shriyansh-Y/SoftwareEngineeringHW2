package selenium.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class WebTest
{
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp() throws Exception 
	{
		//driver = new HtmlUnitDriver();
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public static void  tearDown() throws Exception
	{
		driver.close();
		driver.quit();
	}

	
	@Test
	public void googleExists() throws Exception
	{
		driver.get("http://www.google.com");
        assertEquals("Google", driver.getTitle());		
	}
	

	@Test
	public void Closed() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
		
		// http://geekswithblogs.net/Aligned/archive/2014/10/16/selenium-and-timing-issues.aspx
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='CLOSED']")));
		List<WebElement> spans = driver.findElements(By.xpath("//a[@class='status']/span[.='CLOSED']"));

		assertNotNull(spans);
		assertEquals(5, spans.size());
	}
	
	
	
	
	
	@Test
	//The participant count of "Frustration of Software Developers" is 55
	public void one() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Frustration of Software Developers']/../../following-sibling::div//span[@class='backers']")));
		WebElement x = driver.findElement(By.xpath("//span[.='Frustration of Software Developers']/../../following-sibling::div//span[@class='backers']"));

		assertEquals("55", x.getText());
	}
	
	@Test
	//The total number of studies closed is 5
	public void two() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='CLOSED']")));
		List<WebElement> x = driver.findElements(By.xpath("//a[@class='status']/span[.='CLOSED']"));
		
		assertNotNull(x);
		
		int count = x.size();
		assertEquals(5, count);
	}
	
	@Test
	//If a status of a study is open, you can click on a "Participate" button.
	public void three() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='OPEN']/../following-sibling::div/button")));
		List<WebElement> x = driver.findElements(By.xpath("//a[@class='status']/span[.='OPEN']/../following-sibling::div/button"));
		
		assertNotNull(x);
		for(int i=0;i<x.size();i++)
		{
			assertTrue(x.get(i).isEnabled());
		    x.get(i).click();
		}
	}
	
	
	@Test
	//Check if the "Software Changes Survey" has a Amazon reward image.
	public void four() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dynamicStudies\"]/div[14]/div[1]/div[1]/div[2]/span/img")));
		WebElement x = driver.findElement(By.xpath("//*[@id=\"dynamicStudies\"]/div[14]/div[1]/div[1]/div[2]/span/img"));
		
		
		assertNotNull(x);
		String image = "http://www.checkbox.io/media/amazongc-micro.jpg";
		boolean isPresent=x.getAttribute("src").equals(image);
		assertTrue(isPresent);
		
	}
	
	

}
