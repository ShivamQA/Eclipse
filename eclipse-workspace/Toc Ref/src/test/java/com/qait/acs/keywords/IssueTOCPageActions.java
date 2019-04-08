package com.qait.acs.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class IssueTOCPageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "issueTOCPage";

	public IssueTOCPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyNavigationToCorrectIssueTOCPage(String[] metadata) {
		Assert.assertTrue(getCurrentURL().contains("toc"), "Assertion failed: Not navigated to Issue TOC page");
		logMessage("Verifying Issue TOC page...");
		int i=0;

		if(metadata.length==1)
			isStringMatching(elements("txt_Metadata").get(1).getText(), metadata[0]);
		
		else{
			while(i<metadata.length){
				isStringMatching(elements("txt_Metadata").get(i).getText(), metadata[i]);
				i++;
			}
		}
		logMessage("Verified: Navigated to correct Issue TOC page");
	}


	












}
