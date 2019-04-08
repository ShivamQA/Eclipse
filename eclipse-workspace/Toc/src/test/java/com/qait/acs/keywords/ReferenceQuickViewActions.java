package com.qait.acs.keywords;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ResourceLoader;

public class ReferenceQuickViewActions extends GetPage {
	WebDriver driver;
	Random random = new Random();
	ArticleLandingPageActions article;

	public ReferenceQuickViewActions(WebDriver driver) {
		super(driver, "ReferenceQuickView");
		this.driver = driver;
		article = new ArticleLandingPageActions(driver);
	}

	/**
	 * Method which verifies appearance of Reference quick View box
	 * 
	 */
	public void verifyAppearanceOfRefernceQuickViewBox() {
		isElementDisplayed(true, "window_RQVPopUp");
		isElementDisplayed(true, "header_RQVPopUp");
		logMessage("Reference Quick View box has appeared");
	}

	public void verifyDivisionOfRQVBoxintoTwohalves() {
		isElementDisplayed(true, "block_rqvTopHalf");
		isElementDisplayed(true, "block_lst_rqvList");
		logMessage("INFO: Verified Reference Quick View box is divided into two halves");
	}

	public void verifyFirstReferenceHighlightedInBottomHalfOfRQVBox(int number) {
		// System.out.println(String.valueOf(number + 1));
		scrollDown(element("block_lst_rqvList"));
		System.out.println(element("highlighted_reference", String.valueOf(number)).getAttribute("class"));
		Assert.assertTrue(
				element("highlighted_reference", String.valueOf(number)).getAttribute("class").contains("current"),
				"Assertion failed: First reference is not highlighted in the bottom half of reference quick view box.");
		logMessage("Assertion Passed: First reference is highlighted in the bottom half of reference quick view box. ");
	}

	public void verifyFirstReferenceDetailsDisplayedInTopHalfOfRQVBox() {
		isElementDisplayed(true, "txt_firstReferenceDetails");
		logMessage("INFO: First reference details displayed in the top half of reference quick view box.");
	}

	public void verifyAbstarctAndBibliographicInfIsDisplayed() {
		isElementDisplayed(true, "txt_bibliographicInf");
		isElementDisplayed(true, "txt_abstract");
		logMessage("INFO: Abstract and bibliographic information is displayed.");
	}

	public void verifyScifinderLink() {
		isElementDisplayed(true, "lnk_scifinder");
		Assert.assertTrue(element("lnk_scifinder").getText().trim().contains("More from SciFinder ®"),
				"Assertion Failed: ScifinderLink is not displayed. ");
		logMessage("Assertion Passed: ScifinderLink is displayed.");
	}

	public void verifyExistenceOfVariousElementsOnRightOnRQVBox() {
		isElementDisplayed(true, "lnk_close");
		logMessage("INFO: Close button exists on Reference Quick View box");
		isElementDisplayed(true, "lnk_PrintReference");
		logMessage("INFO: Print selected reference link is displayed.");
		isElementDisplayed(true, "lnk_FullTextOption");
		logMessage("INFO: View Full Text Options link is displayed.");
	}

	public void verifySeeAllReferencesLink() {
		isElementDisplayed(true, "lnk_seeAllReferences");
		logMessage("Info: See All references link is displayed.");
	}

	public void clickOnSeeAllReferencesLink() {
		clickOnElement("lnk_seeAllReferences");
		logMessage("INFO: User clicked on See All references link");
	}

	public void verifySameTotalCountOfReferencesCountUnderTabAndRQVBox(int countTabRef) {
		assertTrue(countTabRef == elements("list_references").size(),
				"Assertion Failed: Total references count under references tab and RQV box not equal");
		logMessage("Assertion Passed: Total references count under references tab and RQV box are equal");
	}

	public void verifyExistenceOfPreviousNextNavigationLinks(int index) {
		if (index == 1) {
			isElementDisplayed(true, "lnk_nextPrevious", "next");
			Assert.assertFalse(element("lnk_nextPrevious", "next").getAttribute("class").contains("disabled"),
					"Assertion Failed: Next button is disabled");
			logMessage("Assertion Passed: Next button is enabled");
			Assert.assertFalse(checkIfElementIsThere("lnk_nextPrevious", "prev"),
					"Assertion Failed: Previous button is enabled");
			logMessage("Assertion Passed: Previous button is disabled");
		} else {
			isElementDisplayed(true, "lnk_nextPrevious", "next");
			Assert.assertFalse(element("lnk_nextPrevious", "next").getAttribute("class").contains("disabled"),
					"Assertion Failed: Next button is disabled");
			logMessage("Assertion Passed: Next button is enabled");
			isElementDisplayed(true, "lnk_nextPrevious", "prev");
			Assert.assertFalse(element("lnk_nextPrevious", "prev").getAttribute("class").contains("disabled"),
					"Assertion Failed: Next button is disabled");
			logMessage("Assertion Passed: Next button is enabled");
		}
	}

	// public void
	// verifyNavigationToFullArticleViaCASOnClickOfViewfullTextOptions() {
	// clickOnElement("lnk_FullTextOption");
	// logMessage("INFO: User clicked on Full Text link.");
	// hardWait(2);
	// changeWindow(1);
	// Assert.assertTrue(getPageTitle().contains("CAS Full Text
	// Options"),"Assertion failed: Correct page is not loaded as it does
	// not have CAS Full Text options as its title.");
	// logMessage("Assertion Passed: Correct page is loaded having CAS Full
	// Text options as its title.");
	// isElementDisplayed("img_CASFulltextOptions");
	// logMessage("Assertion Passed: User is on CAS Full Text options page");
	//// changeWindow(0);
	// navigateToPreviousPage();}

	public void clickOnRQVPopUpCloseButton() {
		isElementDisplayed(true, "lnk_close");
		logMessage("INFO: Close button exists on Reference Quick View box");
		clickOnElement("lnk_close");
		logMessage("INFO: Clicked on cancel button");
		hardWait(1);
		if (checkIfElementIsThere("window_RQVPopUp"))
			logMessage("INFO: RQVPopUP window is open");
		else
			logMessage("INFO: RQVPopUP window is closed");
	}

	public void verifylinksAndNavigationOfCitationLinks() throws IOException {
		for (WebElement cit : elements("lnk_citations")) {
			URL url = new URL(cit.getAttribute("href"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println("------------" + code);
			String link = cit.getText();
			System.out.println("---------------" + link);
			if (link.contains("Crossref")) {
				Assert.assertTrue(code < 400 || code == 403,
						"Assertion Failed: The page doesn't naviagted to correct URL page for Crossref");
				logMessage("Assertion Passed: The page naviagted to correct URL page for Crossref");
			} else if (link.contains("PubMed")) {
				Assert.assertTrue(code < 400 || code == 403,
						"Assertion Failed: The page doesn't naviagted to correct URL page for PubMed");
				logMessage("Assertion Passed: The page naviagted to correct URL page for PubMed");
			} else if (link.contains("CAS")) {
				Assert.assertTrue(code < 400 || code == 403,
						"Assertion Failed: The page doesn't naviagted to correct URL page for CAS");
				logMessage("Assertion Passed: The page naviagted to correct URL page for CAS");
			} else if (link.contains("[Google Scholar]")) {
				Assert.assertTrue(code < 400 || code == 403,
						"Assertion Failed: The page doesn't naviagted to correct URL page for [Google Scholar]");
				logMessage("Assertion Passed: The page naviagted to correct URL page for [Google Scholar]");
			}
		}

	}

	public void clickAndVerifyPrinterViewPageWithCitationNavigation() throws IOException {
		isElementDisplayed(true, "lnk_PrintReference");
		clickOnElement("lnk_PrintReference");
		try {
			changeWindow(1);
			assertTrue(element("txt_printPage").getText().contains("Print Page"),
					"Assertion failed: Text Print Page is not displayed");
			logMessage("Assertion passed: Text Print Page is displayed");
			for (WebElement cit : elements("lnk_citationsPrintPg")) {
				URL url = new URL(cit.getAttribute("href"));
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.connect();
				int code = connection.getResponseCode();
				System.out.println("------------" + code);
				String link = cit.getText();
				System.out.println("---------------" + link);
				if (link.contains("Crossref")) {
					Assert.assertTrue(code < 400 || code == 403,
							"Assertion Failed: The page doesn't naviagted to correct URL page for Crossref");
					logMessage("Assertion Passed: The page naviagted to correct URL page for Crossref");
				} else if (link.contains("PubMed")) {
					Assert.assertTrue(code < 400 || code == 403,
							"Assertion Failed: The page doesn't naviagted to correct URL page for PubMed");
					logMessage("Assertion Passed: The page naviagted to correct URL page for PubMed");
				} else if (link.contains("CAS")) {
					Assert.assertTrue(code < 400 || code == 403,
							"Assertion Failed: The page doesn't naviagted to correct URL page for CAS");
					logMessage("Assertion Passed: The page naviagted to correct URL page for CAS");
				} else if (link.contains("[Google Scholar]")) {
					Assert.assertTrue(code < 400 || code == 403,
							"Assertion Failed: The page doesn't naviagted to correct URL page for [Google Scholar]");
					logMessage("Assertion Passed: The page naviagted to correct URL page for [Google Scholar]");
				}
			}
		} finally {
			closeCurrentWindow();
			changeWindow(0);
		}
	}

	public void verifyResulOfReferenceWithoutReferenceMatch() {
		clickOnSeeAllReferencesLink();
		do {
			if (element("txt_firstReferenceDetails").getText().trim()
					.equals("There is no corresponding record for this reference.")) {
				logMessage("Assertion Passed: RQV doesn't contain CAS information for the selected reference");
				isElementDisplayed(false, "lnk_PrintReference");
				isElementDisplayed(false, "lnk_FullTextOption");
				isElementDisplayed(false, "lnk_scifinder");
				logMessage(
						"Assertion Passed: Reference Without Reference Match doesn't contain print, full text and sci finder options");

				break;
			} else {
				isElementDisplayed(true, "lnk_nextPrevious", "next");
				clickOnElement("lnk_nextPrevious", "next");
			}
		} while (element("txt_firstReferenceDetails").getText().trim()
				.equals("There is no corresponding record for this reference."));

	}

	public void clickOnSingleReferenceNumberLinkAndVerifyTheContentsOfRQVBox(String title, int inderx) {
		verifyAppearanceOfRefernceQuickViewBox();
		hardWait(1);
		isElementDisplayed(true, "title_currentRef");
		System.out.println("title_currentRef" + element("title_currentRef").getText().trim());
		String refernceTitleOnRQVBox = element("title_currentRef").getText().trim();
		Assert.assertTrue(title.contains(refernceTitleOnRQVBox),
				"Assertion Failed: Clicked refernce title is not displayed in Reference Quick View box");
		logMessage("Assertion Passed: Clicked refernce title is displayed in Reference Quick View box");

		Assert.assertTrue(elements("lst_rfrnceDisplayed").size() == 1,
				"Assertion Failed: More than one refernces are displayed on Reference Quick View box");
		logMessage("Assertion Passed: Only clicked refernce is displayed in Reference Quick View box");
	}

	public void verifyThecontentsOfRQVBoxOnClickOfSeeAllReferencesLink(int referncesOnPage) {
		int referncesOnRQVBox = elements("lst_rfrnceDisplayed").size();
		Assert.assertTrue(referncesOnRQVBox == referncesOnPage,
				"Assertion Failed: All refernces are not displayed on click of See All References link");
		logMessage("Assertion Passed: All refernces are displayed on click of See All References link");
	}

	public void clickOnMultipleReferenceNumberLinkAndVerifyTheContentsOfRQVBox() {
		int randomIndex = elements("lst_rfrnceDisplayed").size();
		randomIndex = random.nextInt(randomIndex);
		elements("lst_rfrnceDisplayed").get(randomIndex).click();

		// if (checkIfElementIsThere("title_currentRef"))
		// logMessage("INFO: There is corresponding record for this
		// reference.");
		// else
		while (!checkIfElementIsThere("title_currentRef"))
			randomIndex++;
		System.out.println("randomIndex" + randomIndex);
		elements("lst_rfrnceDisplayed").get(randomIndex).click();
		String selectedREf = element("txt_titleNumrefernce").getAttribute("id").substring(3);

		Assert.assertTrue(element("txt_titleNumrfrnceRQVTop").getText().trim().contains(selectedREf),
				"Assertion Failed: Clicked references number is not displayed in Reference Quick View box");
		logMessage("Assertion Passed: Only clicked refernces number is displayed in Reference Quick View box");
	}

	public int clickOnReferenceWithDetails(int index) {
		hardWait(1);
		int ref = 0;
		if (element("txt_firstReferenceDetails").getText().trim()
				.equals("There is no corresponding record for this reference.")) {
			do {
				clickOnRQVPopUpCloseButton();
				hardWait(1);
				ref = article.clickOnRandomReferenceSectionNumber();
			} while (element("txt_firstReferenceDetails").getText().trim()
					.equals("There is no corresponding record for this reference."));
			return ref;
		}
		return index;
	}
	

}
