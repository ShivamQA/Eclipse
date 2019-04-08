package com.qait.acs.keywords;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class SavedSearchesHomePageActions extends GetPage {

	WebDriver driver;

	String unSavedMsg = "You do not have any saved searches";
	String searchKywrd = "cell";

	public SavedSearchesHomePageActions(WebDriver driver) {
		super(driver, "SavedSearchesHomePage");
		this.driver = driver;
	}

	public void verifyNoSavedMsg() {
		isElementDisplayed(true, "txt_noSavedSearchMessage");
		assertTrue(element("txt_noSavedSearchMessage").getText().contains(unSavedMsg),
				"Assertion Failed: Unsaved search text message is not correct");
		logMessage("Assertion Passed: Unsaved search text message is correct");
	}

	public void clickOnRunSearchButton(String savedSearch) {
		int index = 0;
		for (WebElement el : elements("lst_savedSearches")) {
			index++;
			if (el.getText().equals(savedSearch)) {
				element("btn_deleteOrRunSearch", "Run", String.valueOf(index)).click();
				logMessage("User clicked on Run Search Button for search term: " + savedSearch);
			}
		}
	}

	public void clickOnDeleteSearchButton(String savedSearch) {
		int index = 0;
		for (WebElement el : elements("lst_savedSearches")) {
			index++;
			if (el.getText().equals(savedSearch)) {
				element("btn_deleteOrRunSearch", "Delete", String.valueOf(index)).click();
				logMessage("User clicked on DELETE Search Button for search term: " + savedSearch);
			}
		}
	}

	public void verifySavedSearchTermIsDeleted(String savedSearch) {
		if (checkIfElementIsThere("txt_noSavedSearchMessage"))
			logMessage("Assertion Passed: Search Saved Term is not there");
		else {
			for (WebElement el : elements("lst_savedSearches")) {
				if (el.getText().equals(savedSearch)) {
					logMessage("Assertion Failed: Search Saved Term is there");
					break;
				}

			}
		}

		logMessage("Assertion Passed: Search Saved Term is not there");
	}

	public void verifySavedSearchTerm(String savedSearch) {
		for (WebElement el : elements("lst_savedSearches")) {
			if (el.getText().equals(savedSearch)) {
				logMessage("Assertion Passed: Search Saved Term is there");
				break;
			}
		}

	}
}