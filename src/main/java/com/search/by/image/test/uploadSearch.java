package com.search.by.image.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.upload.image.SearchByUploadImage.BaseUI;

public class uploadSearch extends BaseUI{

	@Test
	public void searchImage() throws InterruptedException
	{
		invokeBrowser("firefox");
		openURL("websiteURL");
		elementClick("imgButn_xpath");
	
		elementClick("imgIcon_xpath");
		elementClick("uploadImgButn_xpath");
		uploadImage("chooseFileButn", "uploadImagePath");
	}
	
	@Test(dependsOnMethods="searchImage")
	public void verifyTitle() throws InterruptedException
	{
	
		WebDriverWait wait = new WebDriverWait(driver,25);
		String title = getTitle();
		wait.until(ExpectedConditions.titleContains(title));
	    Assert.assertEquals(title, "Google Images");
	}
	
	@Test(dependsOnMethods="verifyTitle")
	public void validateMessage() throws InterruptedException
	{
		String msg=aboutMessage("result-stats");
		Reporter.log(msg);
		screenShot();
	}
	
	@Test(dependsOnMethods="validateMessage")
	public void countLink() throws InterruptedException
	{
		List<WebElement> count=linkCount("a");
		int val = count.size();
		Reporter.log("Total No.of Links"+val);
	}
	



}
