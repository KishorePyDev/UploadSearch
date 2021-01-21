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
		openURL("https://www.google.com");
		elementClick("//a[text()='Images']");
	
		elementClick("//*[@id=\'sbtc\']/div/div[3]/div[2]");
		elementClick("//*[@id=\"dRSWfb\"]/div/a");
		uploadImage("encoded_image", "/home/kishore/Pictures/ronaldo.jpg");
	}
	
	@Test
	public void verifyTitle() throws InterruptedException
	{
	
		WebDriverWait wait = new WebDriverWait(driver,25);
		String title = getTitle();
		wait.until(ExpectedConditions.titleContains(title));
	    Assert.assertEquals(title, "Google Search");
	}
	
	@Test
	public void validateMessage() throws InterruptedException
	{
		String msg=aboutMessage("result-stats");
		System.out.println(msg);
		screenShot();
		List<WebElement> count=linkCount("a");
		System.out.println(count.size());
	}
	



}
