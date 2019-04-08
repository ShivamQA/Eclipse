package com.qait.acs.keywords;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class FavouriteContentHomePageActions extends GetPage {

	WebDriver driver;
	String noFavouriteAddMsg = "You do not have any Favorites.";

	public FavouriteContentHomePageActions(WebDriver driver) {
		super(driver, "FavouriteContentHomePage");
		this.driver = driver;
	}

	public void clickAndVerifytabsOnFavouriteContentPage(String tab) {
		isElementDisplayed(true, "tab_favouriteContentPage", tab);
		logMessage("Assertion Passed: Tab " + tab + " on Favourite Content is present");
		clickOnElement("tab_favouriteContentPage", tab);
	}

	public void verifyNoFavouriteContentAddedArticleTabMsg() {
		hardWait(2);
		isElementDisplayed(true, "txt_noFavContenttab1");
		assertTrue(element("txt_noFavContenttab1").getText().contains(noFavouriteAddMsg),
				"Assertion Failed: Unsaved Favourite Content text message is not correct");
		logMessage("Assertion Passed: Unsaved Favourite Content text message is correct");
	}
	
	public void verifyNoFavouriteContentAddedPublishTabMsg() {
		hardWait(2);
		isElementDisplayed(true, "txt_noFavContenttab2");
		assertTrue(element("txt_noFavContenttab2").getText().contains(noFavouriteAddMsg),
				"Assertion Failed: Unsaved Favourite Content text message is not correct");
		logMessage("Assertion Passed: Unsaved Favourite Content text message is correct");
	}
}
