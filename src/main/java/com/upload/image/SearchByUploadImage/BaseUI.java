package com.upload.image.SearchByUploadImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
	public Properties prop;
	
	/**
	 * @Author : Kishore Kumar S
	 * Date :26/01/2021
	 * Description: This function implements the multiple broswers
	 * get the value from testing.xml file as paramaters
	 */
	public void invokeBrowser() 
	{
		
		//String name=prop.getProperty("bName");
		String name="chrome";
		//System.out.println(prop.getProperty("bName"));
		System.out.println(prop.getProperty("websiteURL"));
		if (name=="chrome") 
		{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
			driver = new ChromeDriver();
		}
		
//		} else if (prop.getProperty(browserName).equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver",
//					System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
//			driver = new FirefoxDriver();
//		}

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/ObjectRepo/config.properties");
				prop.load(file);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public void openURL(String websiteURLKey) {
		driver.get(prop.getProperty(websiteURLKey));
	}

	public void elementClick(String xpathKey) {
		driver.findElement(By.xpath(prop.getProperty(xpathKey))).click();
	}

	public void uploadImage(String nameKey, String dataKey) {
		driver.findElement(By.name(prop.getProperty(nameKey))).sendKeys(prop.getProperty(dataKey));
	}

	
	public String getTitle() throws InterruptedException {

		return driver.getTitle();
	}

	public String aboutMessage(String id) throws InterruptedException {
	
		return driver.findElement(By.id(id)).getText();
	}

	/**
	 * @Author : Kishore Kumar S
	 * Date :26/01/2021
	 * Description: This function capture the screenshot of webpage
	 */
	public void screenShot() throws InterruptedException {

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(screenshot,
					new File("/home/kishore/eclipse-workspace/SearchByUploadImage/ScreenShots/searchResult.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<WebElement> linkCount(String tagName) throws InterruptedException {
	
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(tagName)));
		return driver.findElements(By.tagName(tagName));

	}
	
	public void tearDown() 
	{
		driver.close();
	}

}
