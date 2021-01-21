package com.upload.image.SearchByUploadImage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUI {
	
	public WebDriver driver;
	public void invokeBrowser(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/home/kishore/eclipse-workspace/SearchByUploadImage/src/test/resources/drivers/chromedriver");
			driver = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "/home/kishore/eclipse-workspace/SearchByUploadImage/src/test/resources/drivers/geckodriver");
			driver = new FirefoxDriver();
		}
	}
	
	public void openURL(String websiteURL)
	{
		driver.get(websiteURL);
	}
	
	public void tearDown()
	{
		driver.close();
	}
	
	public void quitBrowser()
	{
		driver.quit();
	}
	public void elementClick(String xpath)
	{
		driver.findElement(By.xpath(xpath)).click();
	}
	public void uploadImage(String name,String data)
	{
		driver.findElement(By.name(name)).sendKeys(data);
	}
	
	public String getTitle() throws InterruptedException
	{	Thread.sleep(2000);
		return driver.getTitle();
	}
	
	public String aboutMessage(String id) throws InterruptedException
	{
		Thread.sleep(3000);
		return driver.findElement(By.id(id)).getText();
	}
	public void screenShot() throws InterruptedException 
	{
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    
	    try
	    {
	        FileHandler.copy(screenshot, new File("/home/kishore/eclipse-workspace/SearchByUploadImage/ScreenShots/searchResult.png"));
	    } 
	    catch (IOException e) 
	    {
	        System.out.println(e.getMessage());
	    }
	}
	
	public List<WebElement> linkCount(String tagName) throws InterruptedException
	{
		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(tagName)));
		return driver.findElements(By.tagName(tagName));
		
		
	}


}
