package com.qait.acs.keywords;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.Random;
import java.util.Set;

import com.qait.automation.getpageobjects.GetPage;

public class chapterPageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "chapterPage";
	Set<String> meta = new HashSet<>(Arrays.asList("Received", "Accepted", "Published online", "Published in issue","Revised","Published in print"));

	public chapterPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyChapterHeaderTitle(String title) {
		wait.waitForPageToLoadCompletely();
		isStringMatching(element("header_chapterTOC").getText(), title);
	}

	public void verifyChapterURL(String page) {
		String currentUrL = getCurrentURL();
		assertTrue(currentUrL.contains(page), "Assertion Failed: Current url doesn't contain " + page + " in its url");
		logMessage("Assertion Passed: Current url contain " + page + " in its url");
	}

	public void verifyChapterPageContent(String eBook) {
		if (checkIfElementIsThere("btn_NavigationeBookTOC", "Previous"))
			logMessage("Assertion Passed: Previous Navigation is present on TOC Page");

		if (checkIfElementIsThere("btn_NavigationeBookTOC", "Next"))
			logMessage("Assertion Passed: Next Navigation is present on TOC Page");

		if (checkIfElementIsThere("img_CoverArt"))
			logMessage("Verified: Cover art displayed.");

		if (checkIfElementIsThere("list_author")) {
			for (WebElement auth : elements("txt_author"))
				logMessage("Message: Univerity name appearing for affilation is:- " + auth.getText());
		}

		isElementDisplayed(true, "txt_DOICodeTOC", "doi");
		logMessage("Assertion Passed: DOI is present on TOC Page");

		isElementDisplayed(true, "txt_DOICodeTOC", "copyright");
		logMessage("Assertion Passed: Copyright is present on TOC Page");

		String txtPublication = "Publication Date";

		assertTrue(element("txt_PubeBookTOC").getText().trim().contains(txtPublication),
				"Assertion Failed: " + txtPublication + " is missing on TOC Page");
		logMessage("Assertion Passed: " + txtPublication + " is present on TOC Page");

		verifyPublicationDropBlock();

		int count = elements("mtrix_Articletoc").size();
		String metrix[] = { "Article Views", "Altmetric", "Citations" };
		for (int i = 0; i < count; i++) {
			isStringMatching(elements("mtrix_Articletoc").get(i).getText(), metrix[i]);
			Assert.assertTrue(elements("value_metrix").get(i).getText() != null,
					"Assertion failed: Null value in metrix");
			logMessage("'" + metrix[i] + "' metrix is displayed.");
		}

		assertTrue(isElementDisplayed(true, "lnk_iconShareTOC"), "Assertion Failed: Share icon is missing on TOC Page");
		logMessage("Assertion Passed: Share icon is present on TOC Page");

		assertTrue(isElementDisplayed(true, "lnk_fixedTOC", "book-title"),
				"Assertion Failed: book-title link is missing on TOC Page");
		logMessage("Assertion Passed: book-title link is present on TOC Page");

		assertTrue(isElementDisplayed(true, "txt_DOICodeTOC", "chapter"),
				"Assertion Failed: TOC Page contains: chapter is missing on TOC Page");
		logMessage("Assertion Passed: TOC Page contains: chapter");

		assertTrue(isElementDisplayed(true, "txt_DOICodeTOC", "isbn"),
				"Assertion Failed: TOC Page contains: isbn is missing on TOC Page");
		logMessage("Assertion Passed: TOC Page contains: isbn");

		if (!eBook.contains("Reagent")) {
			assertTrue(element("txt_DOICodeTOC", "isbn").getText().contains("ISBN13:"),
					"Assertion Failed: TOC Page contains: ISBN13: is missing on TOC Page");
			logMessage("Assertion Passed: TOC Page contains: ISBN13:");
		}

		assertTrue(element("txt_DOICodeTOC", "isbn").getText().contains("eISBN:"),
				"Assertion Failed: TOC Page contains: eISBN: is missing on TOC Page");
		logMessage("Assertion Passed: TOC Page contains: eISBN:");

		assertTrue(isElementDisplayed(true, "txt_DOICodeTOC", "copyright"),
				"Assertion Failed: Copyright is missing on TOC Page");
		logMessage("Assertion Passed: Copyright is present on TOC Page");

		assertTrue(isElementDisplayed(true, "btn_Pdf"), "Assertion Failed: Button PDF is missing on TOC Page");
		logMessage("Assertion Passed: Button PDF is present on TOC Page");

		if (isElementDisplayed(true, "btn_NavigationeBookTOC", "Return to Book"))
			logMessage("Assertion Passed: Return to Book Navigation is present on TOC Page");
	}

	public void verifyspecificSponsorDivision(String text) {
		assertTrue(element("txt_sponsorDivision").getText().toLowerCase().contains(text.toLowerCase()),
				"Assertion Failed: Sponsoring Division " + text + " is present");
		logMessage("Assertion Passed: Sponsoring Division " + text + " is present");
	}

	public void clickOnNextPreviousLinksAndVerify() {
		String Title1 = getChapterTitle();
		String position1, position2;

		Boolean flag = checkIfElementIsThere("btn_NavigationeBookTOC", "Next");
		if (flag) {
			position1 = getTopPositionToCheckAutoScroll();
			clickOnElement("btn_NavigationeBookTOC", "Next");
			hardWait(1);
			position2 = getTopPositionToCheckAutoScroll();
			verifyPageDoesNotAutoScrollUponClickingChapterButton("Next", position1, position2);
			String Title2 = getChapterTitle();
			Assert.assertFalse(Title1.equals(Title2), "Assertion failed: Next button did not work.");

		}

		if (checkIfElementIsThere("btn_NavigationeBookTOC", "Previous")) {
			position1 = getTopPositionToCheckAutoScroll();
			clickOnElement("btn_NavigationeBookTOC", "Previous");
			hardWait(2);
			position2 = getTopPositionToCheckAutoScroll();
			verifyPageDoesNotAutoScrollUponClickingChapterButton("Previous", position1, position2);
			String Title2 = getChapterTitle();
			if (flag)
				Assert.assertTrue(Title1.equals(Title2), "Assertion failed: Previous button did not work.");
			else
				Assert.assertFalse(Title1.equals(Title2), "Assertion failed: Previous button did not work.");

			logMessage("Verified: 'Previous' button on sticky header works as expected.");
		}

	}

	public String getChapterTitle() {
		String title = element("header_chapterTOC").getText();
		return title;
	}

	public String getTopPositionToCheckAutoScroll() {
		String Top = verifyScrolledPosition(element("header_chapterTOC"), "Chapter header");
		return Top;
	}

	public void verifyPageDoesNotAutoScrollUponClickingChapterButton(String buttonType, String position1,
			String position2) {
		Assert.assertTrue(position1.equals(position2),
				"Assertion Failed: The page scrolls down automatically when user clicks on " + buttonType
						+ " chapter link.");
		logMessage("Verified: The page does not scroll down automatically when user clicks on " + buttonType
				+ " chapter link.");
	}

	public void verifyFunctionalityOfChapterButton(String buttonType, String title1, String title2) {
		logMessage("Title before clicking " + buttonType + " chapter button: " + title1);
		logMessage("Title after clicking " + buttonType + " chapter button: " + title2);
		Assert.assertFalse(title1.equals(title2), "Assertion Failed: " + buttonType + " chapter button did not work.");
		logMessage("Verified: " + buttonType + " chapter button works as expected.");
	}

	public void clickOnNavigationButton(String buttonType) {
		if (buttonType.equals("Next"))
			element("btn_NavigationeBookTOC", "Next").click();
		else
			element("btn_NavigationeBookTOC", "Previous").click();

		logMessage("User clicked on " + buttonType + "  button");
		wait.waitForPageToLoadCompletely();
	}

	public void verifyFunctionalityOfReturnToBookLink(String bookSeries) {
		String url = getCurrentURL();
		element("btn_NavigationeBookTOC", "Return to Book").click();
		logMessage("Info: Clicked on 'Return to Book' link.");
		wait.waitForPageToLoadCompletely();

		if (bookSeries.contains("Reagent")) {
			Assert.assertTrue(getPageTitle().contains(bookSeries),
					"Assertion failed: Not navigated to Reagent Chemicals eBook page.");
		}

		logMessage("Verified: Navigation to '" + bookSeries + "' eBook page.");
		driver.get(url);
	}

	public void clickOnBookSeriesLinkAndVerify(String bookSeries) {
		String url = getCurrentURL();
		String book = element("lnk_fixedTOC", "book-title").getText();

		element("lnk_fixedTOC", "book-title").click();
		logMessage("Clicked on link '" + book + "'");
		wait.waitForPageToLoadCompletely();

		if (book.equals(bookSeries)) {
			Assert.assertTrue(getPageTitle().contains(bookSeries),
					"Assertion failed: Not navigated to Reagent Chemicals eBook page.");
		}

		logMessage("Verified: Book series link navigates to " + bookSeries + " page.");
		driver.get(url);

	}

	public void clickOnShareIconAndVerify(String[] ShareOptions) {
		clickOnElement("lnk_iconShareTOC");
		Assert.assertTrue(element("lnk_iconShareTOC").getAttribute("aria-expanded").equals("true"),
				"Assertion failed: Share icon is not expanded.");
		logMessage("Verified: Share icon is expanded.");

		for (String option : ShareOptions) {
			System.out.println("---" + option.toLowerCase());
			isElementDisplayed(true, "Options_share", option.toLowerCase());
			logMessage("Info: share option '" + option + "' is displayed.");
		}

		verifyNavigationOfSharingOptionLinks(ShareOptions);

	}

	public void verifyNavigationOfSharingOptionLinks(String[] ShareOptions) {
		String option;
		for (int i = 0; i < ShareOptions.length; i++) {
			option = ShareOptions[i].toLowerCase();
			clickOnElement("Options_share", option);
			hardWait(2);

			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));

			System.out.println(getPageTitle().toLowerCase() + "----" + option);
			Assert.assertTrue(getPageTitle().toLowerCase().contains(option),
					"Assertion Failed: Link '" + option + "' not redirecting to correct page");
			closeCurrentWindow();
			driver.switchTo().window(tabs2.get(0));
			logMessage("Assertion Passed :: Clicking on Sharing link " + option + " works fine.");
			clickOnElement("lnk_iconShareTOC");
			Assert.assertTrue(element("lnk_iconShareTOC").getAttribute("aria-expanded").equals("true"),
					"Assertion failed: Share icon is not expanded.");
			logMessage("Verified: Share icon is expanded.");
		}
		clickOnElement("lnk_iconShareTOC");
	}

	public void clickOnPDFLinkAndVerifyNavigation() {
		String doi = element("txt_DOICodeTOC", "doi").getText().trim().split("DOI:")[1];
		if (checkIfElementIsThere("btn_Pdf")) {
			clickOnElement("btn_Pdf");
			changeWindow(1);
			Assert.assertTrue(getCurrentURL().contains("doi/pdf/" + doi),
					"Assertion failed: Not navigated to PDF page");
			logMessage("Verified: Navigated to PDF page.");
			closeCurrentWindow();
			changeWindow(0);
		} else
			logMessage("Info: PDF button is not present.");
		hardWait(1);
	}

	public void clickOnReturnToBookAndVerify(String book) {
		System.out.println("book: " + book);
		isElementDisplayed(true, "btn_NavigationeBookTOC", "Return to Book");
		String btn_returnToBook = "document.querySelector('.content-navigation__btn--return').click();";
		executeJavascript(btn_returnToBook);
		logMessage("Info: Clicked on :" + "Return to Book");
		hardWait(1);
		System.out.println(element("header_eBookTOC").getText());
		assertTrue(element("header_eBookTOC").getText().contains(book),
				"Assertion Failed: Return to Book functionality is not working correctly");
		logMessage("Assertion Passed: Return to Book functionality working correctly");
	}

	public void clickOnCoverArtImageAndVerifyNavigation() {
		clickOnElement("img_CoverArt");
		hardWait(2);
		Assert.assertTrue(getCurrentURL().contains("toc"),
				"Assertion failed: Cover image on chapter page doesnt navigate to the issue page it came from.");
		logMessage("Verified: Cover image on chapter page navigates to the issue page it came from.");
	}

	public void verifyFunctionalityOfHoverOnAuthornames() {
		int i = 1;
		for (WebElement auth : elements("txt_author")) {
			logMessage("Author: " + auth.getText());
			hover(auth);
			Assert.assertTrue(element("PopUp_AuthorHover", Integer.toString(i), "name").isDisplayed());
			logMessage("Info: Author name is displayed on the popup.");
			Assert.assertTrue(element("PopUp_AuthorHover", Integer.toString(i), "affiliations").isDisplayed());
			logMessage("Info: Affiliations are displayed on the popup.");
			Assert.assertTrue(element("PopUp_AuthorHover", Integer.toString(i), "scifinder").isDisplayed());
			logMessage("Info: Scifinder logo is displayed on the popup.");
			i++;
		}
	}

	public void verifyPublicationDropBlock() {
		isElementDisplayed(true, "icon_articlePubHistory");
		clickOnElement("icon_articlePubHistory");
		if (element("icon_articlePubHistory").getAttribute("aria-expanded").equalsIgnoreCase("false"))
			clickOnElement("icon_articlePubHistory");
		assertTrue(element("block_dropBlockHolder").getAttribute("class").contains("js--open"),
				"Assertion Failed: Publication drop block holder is not opened");
		logMessage("Assertion Passed: Publication drop block holder is opened");
		verifyDropBlockChapterList();
		clickOnElement("icon_articlePubHistory");
		assertTrue(element("icon_articlePubHistory").getAttribute("aria-expanded").equalsIgnoreCase("false"),
				"Assertion Failed: Publication History DropBlock is still opened");
		logMessage("Assertion Passed: Publication History DropBlock is closed");
	}

	public void verifyDropBlockChapterList() {
		assertTrue(element("title_dropBlockHolder").getText().contains("Publication History"),
				"Assertion Failed: Publication History title for drop Block Holder is missing");
		logMessage("Assertion Passed: Publication History title for drop Block Holder is present");

		logMessage("INFO: Number of Publication History list present is: " + elements("list_articleHistory").size());
		verifyPublicationLabelAndDateFormat();

	}

	public void verifyPublicationLabelAndDateFormat() {
		int list = 0;
		for (WebElement pubList : elements("list_articleHistory")) {
			String label[] = pubList.getText().split("(?=\\d)");
			assertTrue(meta.contains(label[0].trim()), "Assertion Failed: Article history label is not correct");
			logMessage("Assertion Passed: Article history label is correct: " + label[0]);

			assertTrue(isValidDateFormatePublication(pubList.getText().substring(pubList.getText().indexOf(label[1]))),
					"Assertion Failed: List of article history is in incorrect formate");
			logMessage("Assertion Passed: List of article history is in correct formate");
			list++;
		}
	}
	
	public void verifyPublicationDate(){
		String txtPublication = "Publication Date";

		assertTrue(element("txt_PubeBookTOC").getText().trim().contains(txtPublication),
				"Assertion Failed: " + txtPublication + " is missing on TOC Page");
		logMessage("Assertion Passed: " + txtPublication + " is present on TOC Page");
		
		assertTrue(isValidDateFormateChaptersTOC("txt_PubValueTOC"),
				"Assertion Failed: Ppublication date is in incorrect formate on Chapter page");
		logMessage("Assertion Passed: Publication date is correct on Chapter page");
	}
}
