package com.search.by.image.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.upload.image.SearchByUploadImage.BaseUI;


public class uploadSearch extends BaseUI {
	
	
	@BeforeTest
	//Open the Browser&URL
	public void setup() throws InterruptedException 
	{
		invokeBrowser();
		openURL("websiteURL");
	}

	@Test
	public void verifyTitle() throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(driver, 25);
		String title = getTitle();
		wait.until(ExpectedConditions.titleContains(title));		
		Assert.assertEquals(title, "Google");
	}
	
	@Test(dependsOnMethods = "verifyTitle")
	public void searchImage() throws InterruptedException 
	{
		elementClick("imgButn_xpath");
		elementClick("imgIcon_xpath");
		elementClick("uploadImgButn_xpath");
		uploadImage("chooseFileButn", "uploadImagePath");
	}

	@Test(dependsOnMethods = "verifyTitle")
	public void validateMessage() throws InterruptedException 
	{
		String msg = aboutMessage("result-stats");
		Reporter.log(msg);
		screenShot();
	}

	@Test(dependsOnMethods = "validateMessage")
	public void countLink() throws InterruptedException 
	{

		List<WebElement> count = linkCount("a");
		int val = count.size();
		Reporter.log("Total No.of Links" + val);

	}
	
	@AfterTest
	//close the Browser
	public void closeBroswer()
	{
		 tearDown();
		
	}

}
