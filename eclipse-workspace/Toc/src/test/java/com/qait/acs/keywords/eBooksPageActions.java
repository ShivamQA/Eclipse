package com.qait.acs.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qait.automation.getpageobjects.GetPage;
import static com.qait.automation.utils.YamlReader.*;

public class eBooksPageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "eBookPage";
	String regex = "\\s\\d{4}\\-\\d{4}";
	List<String> books1 = new ArrayList<String>();
	int year = Calendar.getInstance().get(Calendar.YEAR);
	public chapterPageActions chapter;
	public SearchResultsPageActions search;
	String[] list_Icon = { "Facebook", "Twitter", "LinkedIn", "Google+", "Reddit", "Email" };
	Random random = new Random();

	public eBooksPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
		search = new SearchResultsPageActions(driver);
		chapter = new chapterPageActions(driver);
	}

	public void clickOnBooksRightHomeHeaderList(String elinks) {
		wait.waitForPageToLoadCompletely();
		clickOnElement("lnk_booksHomeHeaderList", elinks);
	}

	public void verifyBooksRightHomeHeaderList(String elinks) {
		assertTrue(isElementDisplayed(true, "lnk_booksHomeHeaderList", elinks),
				"Assertion Failed: " + elinks + " link is missing");
		logMessage("Assertion Passed: " + elinks + " link is present");
	}

	public void verifyHeaderOnAboutACSeBooksPage(String ebook1) {
		wait.waitForPageToLoadCompletely();
		assertEquals(element("logo_ebook").getAttribute("alt"), ebook1,
				"Assertion Failed: ebook logo with " + ebook1 + " is displayed");
		logMessage("Assertion Passed: ebook logo with " + ebook1 + " is not displayed");

		isElementDisplayed(true, "txt_ebookDescription", ebook1);
		logMessage("Assertion Passed: eBook description is present");

		for (WebElement identi : elements("identifiers")) {
			String[] identifier = identi.getText().split(":");
			if (identifier[0].equalsIgnoreCase("eISSN")) {
				logMessage("Assertion Passed: Identifier eISSN is present");
			} else {
				assertTrue(identifier[0].equalsIgnoreCase("ISSN"), "Assertion Failed: Identifier ISSN is missing");
				logMessage("Assertion Passed: Identifier ISSN is present");
			}
			System.out.println("identifier[1]- " + identifier[0]);
			assertTrue(verifyFormat(identifier[1], regex),
					"Assert Failed: eBook identifier: " + identifier[0] + " is missing");
			logMessage("Assert Passed: eBook identifier: " + identifier[0] + " is present");
		}

	}

	public void identifiers(String title) {
		int i = 1;
		for (WebElement ele : elements("txt_headerTitle")) {
			if (ele.getText().equalsIgnoreCase(title)) {
				logMessage("Book header title is present");
				for (WebElement list : elements("lnk_headerlist", title)) {
					// System.out.println("listgetAttribute(title)"+
					// list.getAttribute("title"));
					// System.out.println("Yaml value:
					// "+getYamlValue("BookHeaderListTitle." + title +
					// ".subtitle" + i));
					assertEquals(list.getAttribute("title"),
							getYamlValue("BookHeaderListTitle." + title + ".subtitle" + i),
							"Assertion Failed: subtitle is missing: "
									+ getYamlValue("BookHeaderListTitle." + title + ".subtitle" + i));
					logMessage("Assertion Passed: subtitle is present: "
							+ getYamlValue("BookHeaderListTitle." + title + ".subtitle" + i));
					i++;
				}
			}
		}
	}

	public void verifyBookMainBlock(String block) {
		isElementDisplayed(true, "block_booksMain", block);
		logMessage("Assertion Passed: Book Block in main page is present for: " + block);
	}

	public void verifyLinksUnderBookMainBlock(String block, String[] blockList) {
		int i = 0;
		while (i < blockList.length) {
			for (WebElement link : elements("lnk_blockBooksMain", block)) {
				if (link.getText().equalsIgnoreCase(blockList[i]))
					logMessage("Assertion Passed: link inside main block is present for: " + blockList[i]);
			}
			i++;
		}

		assertTrue(isElementDisplayed(true, "btn_blockBooksMain", block),
				"Assertion Failed: Button Browse & Search the Series is missing under block: " + block);
		logMessage("Assertion Passed: Button Browse & Search the Series is present under block: " + block);
	}

	public void clickOnLinksUnderBookMainBlock(String block, String blockList) {
		for (WebElement link : elements("lnk_blockBooksMain", block)) {
			if (link.getText().equalsIgnoreCase(blockList)) {
				link.click();
				logMessage("Assertion Passed: link inside main block is clicked for: " + blockList);
				break;
			}
		}

		if (blockList.equalsIgnoreCase("Browse & Search the Series")) {
			clickOnElement("btn_blockBooksMain", block);
			logMessage("Assertion Passed: Button Browse & Search the Series is clicked under block: " + block);
		}
	}

	public void verifyBookHomeTitle(String title) {
		assertTrue(element("txt_booksHomeTitle").getText().equalsIgnoreCase(title),
				"Assertion Failed: Book Home Title in main page is missing for: " + title);
		logMessage("Assertion Passed: Book Home Title in main page is present for: " + title);

	}

	public void verifyLinksUnderOtherACSPublicationsBlock(String title, String[] titleList) {
		int i = 0;
		while (i < titleList.length) {
			for (WebElement link : elements("lnk_bookHomeBlockSecondary", title)) {
				if (link.getText().equalsIgnoreCase(titleList[i]))
					logMessage(
							"Assertion Passed: link inside Secondary Book Home Title is present for: " + titleList[i]);
			}
			i++;
		}
	}

	public void clickLinksUnderOtherACSPublicationsBlock(String title, String titleList) {
		for (WebElement link : elements("lnk_bookHomeBlockSecondary", title)) {
			if (link.getText().equalsIgnoreCase(titleList)) {
				link.click();
				logMessage("Assertion Passed: link inside Secondary Book Home Title is present for: " + titleList);
				break;
			}
		}
	}

	public void verifyNavigationToACSDivisionalProceedings() {
		assertTrue(getCurrentURL().contains("meetingpreprints"),
				"Assertion Failed : URL doesn't contain the correct string on subscribe page");
		logMessage("Assertion Passed: URL contain the correct string on subscribe page");
		navigateToPreviousPage();
	}

	public void verifyPageNavigationToDefaultBookSeries() {
		wait.waitForPageToLoadCompletely();
		assertTrue(getPageTitle().trim().equalsIgnoreCase("Default Book Series :"),
				"Assertion Failed: Page is not Navigaed to Default Book Series");
		logMessage("Assertion Passed: Page is Navigaed to Default Book Series");
	}

	public void verifyTextBoxOnebookPage(String txtBox) {
		isElementDisplayed(true, "txtbox_homePage", txtBox);
		logMessage("Assertion Passed: TextBoxon page is present for:: " + txtBox);
	}

	public void verifySubscriptionsPage() {
		assertTrue(getCurrentURL().contains("subscribe"),
				"Assertion Failed : URL doesn't contain the correct string on subscribe page");
		logMessage("Assertion Passed: URL contain the correct string on subscribe page");
		// assertTrue(element("txt_headerSubscribe").getText().contains("subscriptions"),
		// "Assertion Failed : Page header doesn't contain subscriptions");
		// logMessage("Assertion Passed: Page header contain subscriptions");
	}

	public void verifyRecommendedToYourLibrarianPage() {
		assertTrue(getCurrentURL().contains("recommendation"),
				"Assertion Failed : URL doesn't contain the correct string on recommendation page");
		logMessage("Assertion Passed: URL contain the correct string on recommendation page");
		assertTrue(element("txt_headerRecommendation").getText().contains("library"),
				"Assertion Failed : Page header doesn't contain library");
		logMessage("Assertion Passed: Page header contain library");
	}

	public void verifyPresentationTabList(String tabList) {
		wait.waitForPageToLoadCompletely();
		scrollDown(element("lnk_presentationTitle", tabList));
		isElementDisplayed(true, "lnk_presentationTitle", tabList);
		logMessage("Assertion Passed: Presentation tablist is present for: " + tabList);
	}

	public void clickPresentationTabList(String tabList) {
		scrollDown(element("lnk_booksHomeHeaderList", "Recommend to Your Librarian"));
		clickOnElement("lnk_presentationTitle", tabList);
		logMessage("Assertion Passed: Clicked on Presentation tablist is present for: " + tabList);
	}

	public void enterTextInSearchBoxFindABook(String text, String TxtBox) {
		sendKeys(text, "txtbox_homePage", TxtBox);
	}

	public void clickOnSearchFindABookButton() {
		clickOnElement("btn_SearchFindABook");
	}

	public void verifySearchResultMessageForFindABook(String searchTxt) {
		String text = element("txt_SearchResultFindABook").getText();
		String regexResult = "\\d{1,5}\\sbooks\\smatching\\s\"" + searchTxt + "\"";
		assertTrue(verifyFormat(text, regexResult), "Assertion Failed: Mismatch result statement displayed");
		logMessage("Assertion Passed: Correct result statement displayed");
	}

	public void verifySearchBooksResultForFindABook(String searchTxt) {
		searchTxt = searchTxt.substring(0, 3);
		for (WebElement book : elements("SearchBooksResultFindABook")) {
			assertTrue(book.getText().toLowerCase().contains(searchTxt.toLowerCase()),
					"Assertion Failed: Searched Books result doesn't contains " + searchTxt + " in its title");
			books1.add(book.getText());
		}
		logMessage("Assertion Passed: All searched Books result contains " + searchTxt + " in its title");
	}

	public void verifyAndClickOnNextOrPreviousButtonOnFindABookSearchResultPage(String Btn) {
		if (checkIfElementIsThere("btn_SearchResultNavigation", Btn)) {
			clickOnElement("btn_SearchResultNavigation", Btn);
		} else {
			logMessage("Navigation Button Not found on Search Result Page");
		}
	}

	public void verifyNextPageSearchBooksResultForFindABook(String searchTxt) {
		searchTxt = searchTxt.substring(0, 3);
		List<String> books2 = new ArrayList<String>();
		int i = 0;
		for (WebElement book : elements("SearchBooksResultFindABook")) {
			assertTrue(book.getText().toLowerCase().contains(searchTxt.toLowerCase()),
					"Assertion Failed: Searched Books result doesn't contains " + searchTxt + " in its title");
			books2.add(book.getText());
		}
		logMessage("Assertion Passed: All searched Books result contains " + searchTxt + " in its title");

		while (i < books2.size()) {
			assertFalse(books1.get(i).equals(books2.get(i)),
					"Assertion Failed: Search Result for Books is same on previous and current page");
			i++;
		}
		logMessage("Assertion Passed: Search Result for Books is not same on previous and current page");
	}

	public void verifySelectedFindABookSelctionIsSelected(String section) {
		hardWait(2);
		scrollDown(element("btn_SearchFindABook"));
		assertTrue(element("lnk_presentationTitle", section).getAttribute("aria-expanded").equalsIgnoreCase("true"),
				"Assertion Failed: Selected Section is not reflected: " + section);
		logMessage("Assertion Passed: Selected Section is reflected: " + section);
	}

	public void verifyByDefaultCurrentYear() {
		assertTrue(element("txt_selectedYearDropDown").getText().contains(Integer.toString(year)),
				"Assertion Failed: By Default Current Year is not selected is:- " + year);
		logMessage("Assertion Passed: By Default Current Year selected is:- " + year);
	}

	public void selectSpecificDropDownYear(String text) {
		scrollDown(element("btn_SearchFindABook"));
		clickOnElement("dd_selectYear");
		element("dd_selectYearOption", text).click();
		assertTrue(element("txt_selectedYearDropDown").getText().contains(text),
				"Assertion Failed: Selected year is not reflected:- " + text);
		logMessage("Assertion Passed: Selected Year is reflected:- " + text);
	}

	public void verifyTextShowingResultCount() {
		String text = element("txt_MessageCountForYear").getText();
		String regexResult = "\\(Showing\\sresults\\s\\d{1}-\\d{1,20}\\sof\\s\\d{1,20}\\)";
		;
		assertTrue(verifyFormat(text, regexResult), "Assertion Failed: Mismatch result statement displayed");
		logMessage("Assertion Passed: Correct result statement displayed");
	}

	public void verifySearchBooksResultForByYear(String YEAR) {
		for (WebElement book : elements("SearchBooksYearResult")) {
			String txt[] = book.getText().split("-");
			assertTrue(txt[1].equalsIgnoreCase(YEAR),
					"Assertion Failed: Searched Books result doesn't contains " + YEAR + " in its Year");
			books1.add(book.getText());
		}
		logMessage("Assertion Passed: All searched Books result contains " + YEAR + " in its YEAR");
	}

	public String clickOnAnyBookUsingIndexValue() {
		int title = elements("SearchBooksResultFindABook").size();
		int randomIndex = random.nextInt(title);

		String ebook = elements("SearchBooksResultFindABook").get(randomIndex).getText();
		logMessage("Assertion Passed: Header is present on Chapters TOC Page is - "
				+ elements("SearchBooksResultFindABook").get(randomIndex).getText());

		elements("SearchBooksResultFindABook").get(randomIndex).click();
		logMessage("Clicked On Book number: " + randomIndex);
		return ebook;
	}

	public void verifyeBookTOCPageContent() {
		isElementDisplayed(true, "coverPage_eBook");
		logMessage("Assertion Passed: Cover Page image is present on TOC Page");

		isElementDisplayed(true, "header_eBookTOC");
		logMessage("Assertion Passed: Header is present on TOC Page");

		if (checkIfElementIsThere("txt_EditoreBookTOC"))
		logMessage("Assertion Passed: Editor text is present on TOC Page");

		for (WebElement edi : elements("lnk_EditorAuthoreBookTOC")) {
			logMessage("Editor Name present on TOC Page is: " + edi.getText());
		}

		String volRegex = "\\Volume\\s\\d{1,4}";
		assertTrue(verifyFormat(element("txt_fixedeBookTOC", "Volume").getText(), volRegex),
				"Assertion Failed: Volume and its pattern is missing on TOC Page");
		logMessage("Assertion Passed: Volume and its pattern is present on TOC Page");

		// String txtPublication = "Publication Date (Web):";
		// if(eBook.equalsIgnoreCase("Advances in Chemistry"))
		// txtPublication="Publication Date (Print):";
		String txtPublication = "Publication Date";

		assertTrue(element("txt_PubeBookTOC").getText().trim().contains(txtPublication),
				"Assertion Failed: " + txtPublication + " is missing on TOC Page");
		logMessage("Assertion Passed: " + txtPublication + " is present on TOC Page");

		assertTrue(isValidDateFormate("txt_PubValueebookTOC"),
				"Assertion Failed: Publication Date formate is incorrect on TOC Page");
		logMessage("Assertion Passed: Publication Date formate is correct on TOC Page");

		isElementDisplayed(true, "txt_fixedeBookTOC", "Copyright");
		logMessage("Assertion Passed: Copyright is present on TOC Page");

		if (checkIfElementIsThere("lst_affliations")){
		for (WebElement uni : elements("txt_affiliationseBookTOC")) 
			logMessage("Message: Univerity name appearing for affilation is:- " + uni.getText());
		}

		String txtSponsoring = "Sponsoring Divisions:";
		assertTrue(element("txt_SponsoringDivisioneBookTOC").getText().trim().equalsIgnoreCase(txtSponsoring),
				"Assertion Failed: " + txtSponsoring + " is missing on TOC Page");
		logMessage("Assertion Passed: " + txtSponsoring + " is present on TOC Page");

		isElementDisplayed(true, "lnk_SeeAllDivisioneBookTOC");
		logMessage("Assertion Passed: See All Technical Divisions link is present on TOC Page");

		isElementDisplayed(true, "txt_SSNCodeDOIeBookTOC", "ISBN13");
		logMessage("Assertion Passed: TOC Page contains: ISBN13");

		isElementDisplayed(true, "txt_SSNCodeDOIeBookTOC", "eISBN");
		logMessage("Assertion Passed TOC Page contains: eISBN");

		isElementDisplayed(true, "txt_SSNCodeDOIeBookTOC", "DOI");
		logMessage("Assertion Passed TOC Page contains: DOI");

		isElementDisplayed(true, "lnk_iconShareeBookTOC");
		logMessage("Assertion Passed: Share icon is present on TOC Page");

		isElementDisplayed(true, "btn_NavigationeBookTOC", "Previous book");
		logMessage("Assertion Passed: Previous book is present on TOC Page");

		if (checkIfElementIsThere("btn_NavigationeBookTOC", "Previous book"))
			logMessage("Assertion Passed: Previous book is present on TOC Page");

		if (checkIfElementIsThere("btn_NavigationeBookTOC", "Next book"))
			logMessage("Assertion Passed: Next book is present on TOC Page");

		if (checkIfElementIsThere("btn_NavigationeBookTOC", "View all books"))
			logMessage("Assertion Passed: View all books is present on TOC Page");
	}

	public void verifyDesiredeBookChaptersTOCPage() {
		int chapter = elements("header_tocBook").size();
		int randomIndex = random.nextInt(chapter);

		String title = elements("header_tocBook").get(randomIndex).getText();
		System.out.println("title: " + title);
		logMessage("Assertion Passed: Header is present on Chapters TOC Page is - "
				+ title);

		assertTrue(isElementDisplayed(true, "css_ChapterSeperator"),
				"Assertion Failed: Chapter Seperator is missing on Chapters TOC Page");
		logMessage("Assertion Passed: Chapter Seperator is present on Chapters TOC Page");

		if (checkIfElementIsThere("authorNametocBook", title)) {
			logMessage("Assertion Passed: " + title + " chapter title contains authors on present on TOC Page");
			for (WebElement name : elements("list_authorNametocBook", title)) {
				logMessage("Assertion Passed: " + title + " chapter title contains author- " + name.getText());
			}
		}

		if (checkIfElementIsThere("txt_ChPageDOItocBook", title, "chapter"))
			logMessage("Assertion Passed: chapter is present for " + title + " TOC Page");

		if (checkIfElementIsThere("txt_ChPageDOItocBook", title, "page"))
			logMessage("Assertion Passed: page is present for " + title + " TOC Page");

		if (isElementDisplayed(true, "txt_ChPageDOItocBook", title, "doi"))
			logMessage("Assertion Passed: DOI is present for " + title + " TOC Page");

		// if(eBook.equalsIgnoreCase("ACS Symposium Series") ||
		// eBook.equalsIgnoreCase("ACS Style Guide")){
		// String pub = "Publication Date (Web):";
		// assertTrue(element("txt_PubtocBook",
		// title).getText().trim().equalsIgnoreCase(pub),
		// "Assertion Failed: " + pub + " is missing on Chapters TOC Page");
		// logMessage("Assertion Passed: " + pub + " is present on Chapters TOC
		// Page");
		// }
		//
		// if(eBook.equalsIgnoreCase("Advances in Chemistry")){
		// String pub = "Publication Date (Print):";
		// assertTrue(element("txt_PubAdvancedTocBook",
		// title).getText().trim().equalsIgnoreCase(pub),
		// "Assertion Failed: " + pub + " is missing on Chapters TOC Page");
		// logMessage("Assertion Passed: " + pub + " is present on Chapters TOC
		// Page");
		// }

		String pub = "Publication Date";
		assertTrue(element("txt_PubAdvancedTocBook", title).getText().trim().contains(pub),
				"Assertion Failed: " + pub + " is missing on Chapters TOC Page");
		logMessage("Assertion Passed: " + pub + " is present on Chapters TOC Page");

		assertTrue(isValidDateFormateChaptersTOC("txt_PubValuetocBook", title),
				"Assertion Failed: Publication Date formate is incorrect on Chapters TOC Page");
		logMessage("Assertion Passed: Publication Date formate is correct on Chapters TOC Page");

		isElementDisplayed(true, "lnk_freeAccesstocBook", title);
		logMessage("Assertion Passed: free Access is present on Chapters TOC Page");

		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "First Page"))
			logMessage("Assertion Passed: First Page is present for " + title + " TOC Page");

		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "PDF"))
			logMessage("Assertion Passed: PDF is present for " + title + " TOC Page");

		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "Abstract"))
			logMessage("Assertion Passed: Abstract is present for " + title + " TOC Page");

		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "Full text"))
			logMessage("Assertion Passed: Full text is present for " + title + " TOC Page");

		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "Preview Abstract"))
			logMessage("Assertion Passed: Abstract Button is present for " + title + " TOC Page");
	}

	public String clickOnChaptertocEbookTitle() {
		int title = elements("header_tocBook").size();
		int randomIndex = random.nextInt(title);

		scrollDown(elements("header_tocBook").get(randomIndex-1));
		String chapter = elements("header_tocBook").get(randomIndex).getText();
		logMessage("Assertion Passed: Header is present on Chapters TOC Page is - "
				+ elements("header_tocBook").get(randomIndex).getText());

		hardWait(1);
		elements("header_tocBook").get(randomIndex).click();
		return chapter;
	}

	public String getChapterURL() {
		String currentUrL = getCurrentURL();
		return currentUrL;
	}

	public String clickOnIssueItemButtonsList(String title, String item) {
		isElementDisplayed(true, "lnk_issueitemtocBook", title, item);
		clickOnElement("lnk_issueitemtocBook", title, item);
		return element("lnk_issueitemtocBook", title, item).getAttribute("href");
	}

	public void verifyIssueItemButtonsList() {
		int list = elements("header_tocBook").size();
		int randomIndex = random.nextInt(list);

		String title = elements("header_tocBook").get(randomIndex).getText();
		
		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "First Page")) {
			logMessage("Assertion Passed: First Page is present for " + title + " TOC Page");
			String url = getChapterURL();
			String prevurl = element("lnk_issueitemtocBook", title, "First Page").getAttribute("href");
			clickOnElement("lnk_issueitemtocBook", title, "First Page");
			chapter.verifyChapterURL(prevurl);
			// openUrl(url);
			navigateToPreviousPage();
		}
		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "PDF")) {
			logMessage("Assertion Passed: PDF is present for " + title + " TOC Page");
			String url = getChapterURL();
			String prevurl = element("lnk_issueitemtocBook", title, "PDF").getAttribute("href");
			System.out.println("URL=" + prevurl);
			clickOnElement("lnk_issueitemtocBook", title, "PDF");
			chapter.verifyChapterURL(prevurl);
			// openUrl(url);
			navigateToPreviousPage();
		}
		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "Abstract")) {
			logMessage("Assertion Passed: Abstract is present for " + title + " TOC Page");
			String url = getChapterURL();
			String prevurl = element("lnk_issueitemtocBook", title, "Abstract").getAttribute("href");
			clickOnElement("lnk_issueitemtocBook", title, "Abstract");
			chapter.verifyChapterURL(prevurl);
			// openUrl(url);
			navigateToPreviousPage();
		}

		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "Full text")) {
			logMessage("Assertion Passed: Full text is present for " + title + " TOC Page");
			String url = getChapterURL();
			String prevurl = element("lnk_issueitemtocBook", title, "Full text").getAttribute("href");
			clickOnElement("lnk_issueitemtocBook", title, "Full text");
			chapter.verifyChapterURL(prevurl);
			// openUrl(url);
			navigateToPreviousPage();
		}

		if (checkIfElementIsThere("lnk_issueitemtocBook", title, "Preview Abstract")) {
			logMessage("Assertion Passed: Abstract Button is present for " + title + " TOC Page");
			clickOnElement("lnk_issueitemtocBook", title, "Preview Abstract");
			assertTrue(element("lnk_issueitemtocBook", title, "Preview Abstract").getAttribute("aria-expanded")
					.equals("true"), "Assertion Failed: Abstract button is not expanded");
			logMessage("Assertion Passed: Abstract button is expanded");
			clickOnElement("lnk_issueitemtocBook", title, "Preview Abstract");
			assertTrue(element("lnk_issueitemtocBook", title, "Preview Abstract").getAttribute("aria-expanded")
					.equals("false"), "Assertion Failed: Abstract button is expanded");
			logMessage("Assertion Passed: Abstract button is not expanded");
		}
	}

	public void verifyACSGuideTocPage() {
		isElementDisplayed(true, "coverPage_eBook");
		logMessage("Assertion Passed: Cover Page image is present on TOC Page");

		isElementDisplayed(true, "headerTxt_GuideBook");
		logMessage("Assertion Passed: Header is present on TOC Page");

	}

	public void verifyACSGuideToc() {
		isElementDisplayed(true, "coverPage_eBook");
		logMessage("Assertion Passed: Cover Page image is present on TOC Page");

		isElementDisplayed(true, "headerTxt_GuideBook");
		logMessage("Assertion Passed: Header is present on TOC Page");

		isElementDisplayed(true, "txt_EditoreBookTOC");
		logMessage("Assertion Passed: Editor text is present on TOC Page");

		for (WebElement edi : elements("lnk_EditorAuthoreBookTOC")) {
			logMessage("Editor Name present on TOC Page is: " + edi.getText());
		}

		String txtPublication = "Publication Date";
		assertTrue(element("txt_PubeBookTOC").getText().trim().contains(txtPublication),
				"Assertion Failed: " + txtPublication + " is missing on TOC Page");
		logMessage("Assertion Passed: " + txtPublication + " is present on TOC Page");

		assertTrue(isValidDateFormate("txt_PubValueebookTOC"),
				"Assertion Failed: Publication Date formate is incorrect on TOC Page");
		logMessage("Assertion Passed: Publication Date formate is correct on TOC Page");

		isElementDisplayed(true, "txt_fixedeBookTOC", "Copyright");
		logMessage("Assertion Passed: Copyright is present on TOC Page");

		isElementDisplayed(true, "txt_SSNCodeDOIeBookTOC", "ISBN13");
		logMessage("Assertion Passed: TOC Page contains: ISBN13");

		isElementDisplayed(true, "txt_SSNCodeDOIeBookTOC", "eISBN");
		logMessage("Assertion Passed TOC Page contains: eISBN");

		isElementDisplayed(true, "txt_SSNCodeDOIeBookTOC", "DOI");
		logMessage("Assertion Passed TOC Page contains: DOI");

		isElementDisplayed(true, "lnk_iconShareeBookTOC");
		logMessage("Assertion Passed: Share icon is present on TOC Page");

		if (checkIfElementIsThere("btn_NavigationeBookTOC", "View all books"))
			logMessage("Assertion Passed: View all books is present on TOC Page");
	}

	public void clickAndVerifyOnEditorsAuthorNames() {
		int i = 0;
		if(checkIfElementIsThere("txt_EditoreBookTOC")){
		while (i < elements("lnk_EditorAuthoreBookTOC").size()) {
			String author = elements("lnk_EditorAuthoreBookTOC").get(i).getText();
			System.out.println(author);
			elements("lnk_EditorAuthoreBookTOC").get(i).click();
			search.verifyFiltersAppliedResultColumnAuthor(author);
			navigateToPreviousPage();
			i++;
		}}
	}

	public void verifyAdvancedChemistryByDefaultCurrentYear() {
		assertTrue(element("txt_selectedYearDropDown").getText().contains("1998"),
				"Assertion Failed: By Default Current Year is not selected is:- " + year);
		logMessage("Assertion Passed: By Default Current Year selected is:- " + year);
	}

	public void verifyDefaultElementsDisplayedUnderSponsorDivision() {
		String lable = "Select a Sponsoring Division:";
		assertTrue(element("lable_selectsponsoring").getText().trim().equalsIgnoreCase(lable),
				"Assertion Failed: " + lable + " is missing for Sponsoring Division");
		logMessage("Assertion Passed: " + lable + " is present for Sponsoring Division");

		isElementDisplayed(true, "dd_sponsorDivision");
		logMessage("Assertion Passed: dropdown option is available for Sponsoring Division");

		isElementDisplayed(true, "btn_GoSponsorDivision");
		logMessage("Assertion Passed: Go Button is available for Sponsoring Division");

		if (checkIfElementIsThere("btn_SearchResultNavigation", "Next"))
			logMessage("Verified: Next button is available");

		if (checkIfElementIsThere("btn_SearchResultNavigation", "Previous"))
			logMessage("Verified: Next button is available");
	}

	public void clickOnSponsorDivisionGoButton() {
		clickOnElement("btn_GoSponsorDivision");
		logMessage("Verified: Clicked on Go Button for Sponsoring Division");
	}

	public void selectSpecificDropDownSponsorDivision(String text) {
		scrollDown(element("btn_SearchFindABook"));
		clickOnElement("dd_selectsponsorDivision");
		element("dd_selectsponsorDivisionOption", text).click();
		logMessage("Selected: " + text + " from dropdown option");
		assertTrue(element("txt_selectedsponsorDropDown").getText().contains(text),
				"Assertion Failed: Selected year is not reflected:- " + text);
		logMessage("Assertion Passed: Selected Year is reflected:- " + text);
	}

	public void verifySelectedSponsorDivisionOption(String text) {
		assertTrue(element("dd_selectsponsorDivision").getText().contains(text),
				"Assertion Failed: Selected year is not reflected:- " + text);
		logMessage("Assertion Passed: Selected Year is reflected:- " + text);

	}

	public void verifyeBooksContainSelectedSponsorDivisionOption(String text) {
		System.out.println("Size: " + elements("SearchBooksResultFindABook").size());
		int count = 0, j = 0;
		int booksCount = 5;
		if (elements("SearchBooksResultFindABook").size() <= 5)
			;
		booksCount = elements("SearchBooksResultFindABook").size();
		for (; count < booksCount; count++, j++) {
			if (j == 0)
				scrollDown(element("dd_selectsponsorDivision"));
			else
				scrollDown(elements("header_bookAdvancedTOC").get(j - 1));
			System.out.println("Header -" + elements("header_bookAdvancedTOC").get(count).getText());
			hardWait(1);
			elements("header_bookAdvancedTOC").get(count).click();
			chapter.verifyspecificSponsorDivision(text);
			navigateToPreviousPage();
		}
	}

	public void verifyDistincteBooksUnderSelectedSponsorDivisionOption(String string) {
		int count = 0, j = 0;
		HashSet<String> set = new HashSet<String>();
		int booksCount = 5;
		if (elements("SearchBooksResultFindABook").size() <= 5)
			;
		booksCount = elements("SearchBooksResultFindABook").size();

		for (; count < booksCount; count++, j++) {
			if (j == 0)
				scrollDown(element("dd_selectsponsorDivision"));
			else
				scrollDown(elements("header_bookAdvancedTOC").get(j - 1));
			System.out.println("Header -" + elements("header_bookAdvancedTOC").get(count).getText());
			hardWait(1);
			set.add(elements("header_bookAdvancedTOC").get(count).getText());
		}

		assertTrue(set.size() == elements("header_bookAdvancedTOC").size(),
				"Assertion Failed: List of books are not distinct for Sponsoring division");
		logMessage("Assertion Passed: List of books are distinct for Sponsoring division");
	}

	public void verifyDistinctDropDownOptionsForSponsoringDivision() {
		HashSet<String> set = new HashSet<String>();
		scrollToTop();
		scrollVertical(550);
		hardWait(2);
		clickOnElement("dd_selectsponsorDivision");
		for (int count = 0; count < elements("header_bookAdvancedTOC").size(); count++)
			set.add(elements("header_bookAdvancedTOC").get(count).getText());
		assertTrue(set.size() == elements("header_bookAdvancedTOC").size(),
				"Assertion Failed: Dropdown List are not distinct for Sponsoring division");
		logMessage("Assertion Passed: Dropdown List are distinct for Sponsoring division");
		clickOnElement("dd_selectsponsorDivision");
	}

	public void clickAndVerifySeeAllTechnicalDivisionsOnEbook() {
		clickOnElement("lnk_SeeAllDivisioneBookTOC");
		assertTrue(element("title_selectAllDivisionPage").getText().trim().contains("Technical Division List"),
				"Assertion Failed: Navigation page for See All Technical Divisions does contain correct title page");
		logMessage("Assertion Passed: Navigation page for See All Technical Divisions does contain correct title page");
		// assertTrue(elements("container_selectAllDvsnPg").size() > 0,
		// "Assertion Failed: Content inside Navigation page for See All
		// Technical Divisions is empty");
		// logMessage("Assertion Passed: Content inside Navigation page for See
		// All Technical Divisions is empty");
		navigateToPreviousPage();
	}

	public void clickAndVerifySharingBookIconList() {
		clickOnElement("lnk_iconShareeBookTOC");
		int count = 0;
		for (WebElement icon : elements("list_iconShare")) {
			assertTrue(icon.getAttribute("title").equalsIgnoreCase(list_Icon[count]),
					"Assertion Passed: Sharing Icon contains title: " + list_Icon[count]);
			logMessage("Assertion Passed: Sharing Icon contains title: " + list_Icon[count]);
			count++;
		}
		clickOnElement("btn_closeShareIcon");
	}

	public void verifyPreviousOrNextOrViewAllBookNavigation(String book) {
		if (checkIfElementIsThere("btn_NavigationeBookTOC", book)) {
			logMessage("Assertion Passed: " + book + " is present on TOC Page");
			String currentURL = getCurrentURL();
			String URLSection = element("btn_NavigationeBookTOC", book).getAttribute("href");
			clickOnElement("btn_NavigationeBookTOC", book);
			assertTrue(getCurrentURL().contains(URLSection), "Assertion Failed : Not Navigated to correct Page");
			logMessage("Assertion Passed : Navigated to correct Page for: " + book);
			openUrl(currentURL);
		}

	}
	
	public String eBookTitle(){
		isElementDisplayed(true, "header_eBookTOC");
		String title = element("header_eBookTOC").getText();
		return title;
	}

}