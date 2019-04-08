package com.qait.acs.keywords;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.hamcrest.core.Is;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class ArticleLandingPageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "articlePage";
	Random random = new Random();

	public ArticleLandingPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	String[] QVTabList = { "Figures", "References", "Support Info", "Primary Data" };

	public String verifyNavigationToArticleLandingPage(String title) {
		isStringMatching(element("h1_ArticleTitle").getText(), title);
		logMessage("Verified: Navigation to article landing page");
		String ArticleURl = getCurrentURL();

		return ArticleURl;
	}

	public void verifyElementsOnArticleLandingPage(String Collection) {
		String ButtonName = "";

		// if (checkIfElementIsThere("btn_Navigation", "Previous"))
		// logMessage("Verified: Previous Navigation is present on Article
		// Page.");
		//
		// if (checkIfElementIsThere("btn_Navigation", "Next"))
		// logMessage("Verified: Next Navigation is present on Article Page.");

		if (Collection.equals("Most Read")) {
			if (checkIfElementIsThere("btn_Navigation", "Return to Articles ASAP"))
				logMessage("Verified: 'Return to Articles ASAP' navigation button is present on Article Page");
			else if (checkIfElementIsThere("btn_Navigation", "Return to Issue"))
				logMessage("Verified: 'Return to Issue' navigation button is present on Article Page");
			else
				Assert.fail("Assertion failed: 'Return to XXX' navigation button is not displayed.");
		} else {
			if (Collection.equals("Articles ASAP"))
				ButtonName = "Return to Articles ASAP";
			else
				ButtonName = "Return to Issue";
			isElementDisplayed(true, "btn_Navigation", ButtonName);
			logMessage("Verified: 'Return to XXX' navigation button is present on Article Page");
		}

		isElementDisplayed(true, "h1_ArticleTitle");
		logMessage("Verified: Article title is displayed.");
		Assert.assertTrue(element("h1_ArticleTitle").getText() != null, "Assertion failed: Title is empty string.");

		isElementDisplayed(true, "img_CoverArt");
		logMessage("Verified: Cover art displayed.");

		isElementDisplayed(true, "list_author");
		logMessage("Info: Authors displayed: ");
		// for (WebElement auth : elements("txt_author"))
		// logMessage(auth.getText());

		isElementDisplayed(true, "txt_DOI", "doi");
		logMessage("Verified: DOI is present on Article Page.");
		isElementDisplayed(true, "txt_DOI", "copyright");
		logMessage("Verified: Copyright is present on Article Page.");
		isElementDisplayed(true, "link_RightsPermissions");
		logMessage("Verified: Rights and Permissions link is present on page.");

		// String txtPublication = "Publication Date (Web):";
		// assertTrue(element("txt_PubDate").getText().trim().equalsIgnoreCase(txtPublication),
		// "Assertion Failed: " + txtPublication + " is missing on TOC Page");
		// isElementDisplayed(true, "txt_PubDateValue");
		// String validPatternForPublicationDate =
		// "(January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{1,2},\\s\\d{4}";
		// Assert.assertTrue(verifyFormat(element("txt_PubDateValue").getText(),
		// validPatternForPublicationDate),
		// "Assertion Failed: Publication Date not displayed in [Month DD, YYYY]
		// format");
		// logMessage("Verified: " + txtPublication + " is present on Article
		// Page.");

		int count = elements("metrics_Article").size();
		String metrics[] = { "Article Views", "Altmetric", "Citations" };
		for (int i = 0; i < count; i++) {
			isStringMatching(elements("metrics_Article").get(i).getText(), metrics[i]);
			Assert.assertTrue(elements("value_metrics").get(i).getText() != null,
					"Assertion failed: Null value in metrics");
		}
		logMessage("Verified: Metrics is present on Article page.");

		isElementDisplayed(true, "Icon_share");
		logMessage("Verified: Share icon is present on Article Page.");

		isElementDisplayed(true, "btn_Pdf");
		logMessage("Verified: Button PDF is present on Article Page");

		if (Collection.equals("Editors’ Choice")) {
			isElementDisplayed(true, "Img_AuthorsChoice");
			logMessage("Verified: ACS Editors’ Choice image is displayed for Editor's Choice article.");
		}

		// isElementDisplayed(true, "btn_eAlerts");
		// logMessage("Verified: e-Alerts button is displayed on article
		// page.");

	}

	public void clickOnNextPreviousLinksAndVerify() {
		String Title1 = getArticleTitle();
		String position1 = getTopPositionToCheckAutoScroll("Next");
		clickOnNavigationButton("Next");
		String position2 = getTopPositionToCheckAutoScroll("Next");
		verifyPageDoesNotAutoScrollUponClickingArticleButton("Next", position1, position2);
		String Title2 = getArticleTitle();
		verifyFunctionalityOfArticleButton("Next", Title1, Title2);

		position1 = getTopPositionToCheckAutoScroll("Previous");
		clickOnNavigationButton("Previous");
		position2 = getTopPositionToCheckAutoScroll("Previous");
		verifyPageDoesNotAutoScrollUponClickingArticleButton("Previous", position1, position2);
		Title2 = getArticleTitle();
		Assert.assertTrue(Title1.equals(Title2), "Assertion Failed: 'Previous' chapter button did not work.");
		logMessage("Verified: 'Previous' article button works as expected.");

	}

	public String getArticleTitle() {
		String title = element("h1_ArticleTitle").getText();
		return title;
	}

	public String getTopPositionToCheckAutoScroll(String buttonType) {
		String Top = verifyScrolledPosition(element("btn_Navigation", "Next"), buttonType + " button");
		return Top;
	}

	public void clickOnNavigationButton(String buttonType) {
		element("btn_Navigation", buttonType).click();
		logMessage("User clicked on " + buttonType + "  button");
		wait.waitForPageToLoadCompletely();
	}

	public void verifyPageDoesNotAutoScrollUponClickingArticleButton(String buttonType, String position1,
			String position2) {
		Assert.assertTrue(position1.equals(position2),
				"Assertion Failed: The page scrolls down automatically when user clicks on " + buttonType
						+ " article link.");
		logMessage("Verified: The page does not scroll down automatically when user clicks on " + buttonType
				+ " article link.");
	}

	public void verifyFunctionalityOfArticleButton(String buttonType, String title1, String title2) {
		logMessage("Title before clicking " + buttonType + " article button: " + title1);
		logMessage("Title after clicking " + buttonType + " article button: " + title2);
		Assert.assertFalse(title1.equals(title2), "Assertion Failed: " + buttonType + " article button did not work.");
		logMessage("Verified: " + buttonType + " article button works as expected.");
	}

	public void clickOnShareIconAndVerify(String[] ShareOptions) {
		clickOnElement("Icon_share");
		Assert.assertTrue(element("Icon_share").getAttribute("aria-expanded").equals("true"),
				"Assertion failed: Share icon is not expanded.");
		logMessage("Verified: Share icon is expanded.");

		for (String option : ShareOptions) {
			isElementDisplayed(true, "Options_share", option.toLowerCase());
			logMessage("Info: share option '" + option + "' is displayed.");
		}

		verifyNavigationOfSharingOptionLinks(ShareOptions);

	}

	public void verifyNavigationOfSharingOptionLinks(String[] ShareOptions) {
		String option;
		for (int i = 0; i < ShareOptions.length; i++) {
			boolean tabClosed = false;
			option = ShareOptions[i].toLowerCase();
			clickOnElement("Options_share", option);
			hardWait(2);

			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));

			if (option.contains("google"))
				option = option.split("_")[0];
			System.out.println(getPageTitle().toLowerCase() + "----" + option);
			try {
				Assert.assertTrue(getPageTitle().toLowerCase().contains(option),
						"Assertion Failed: Link '" + option + "' not redirecting to correct page");
				closeCurrentWindow();
				driver.switchTo().window(tabs2.get(0));
				logMessage("Assertion Passed :: Clicking on Sharing link " + option + " works fine.");
				tabClosed = true;
				if (i < ShareOptions.length) {
					clickOnElement("Icon_share");
					hardWait(1);
				}
			} finally {
				System.out.println("Inside finally");
				if (!tabClosed) {
					closeCurrentWindow();
					driver.switchTo().window(tabs2.get(0));
				}
			}
		}
	}

	public void clickOnPDFLinkAndVerifyNavigation() {
		String doi = element("txt_DOI", "doi").getText().trim().split("org/")[1];
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
	}

	public String clickOnReturnToTypeLinkAndVerify(String collectionName) {
		String ButtonName = "";

		if (collectionName.equals("Most Read")) {
			if (checkIfElementIsThere("btn_Navigation", "Return to Articles ASAP")) {
				ButtonName = "Return to Articles ASAP";
				collectionName = "ASAP";
				System.out.println("Return to Articles ASAP");
			} else if (checkIfElementIsThere("btn_Navigation", "Return to Issue")) {
				ButtonName = "Return to Issue";
				collectionName = "Issue";
				System.out.println("Return to Issue");
			}
		}

		else if (collectionName.equals("Articles ASAP")) {
			ButtonName = "Return to Articles ASAP";
			collectionName = "ASAP";
		} else {
			ButtonName = "Return to Issue";
			collectionName = "Issue";
		}

		element("btn_Navigation", ButtonName).click();
		logMessage("Info: Clicked on :" + ButtonName);
		hardWait(1);
		return collectionName;
	}

	public void verifyeAlertsButtonAndItsNavigation() {
		clickOnElement("btn_eAlerts");
		verifyNavigationToLoginPromptPage();
	}

	private void verifyNavigationToLoginPromptPage() {
		Assert.assertTrue(getCurrentURL().contains("action/doUpdateAlertSettings"));
		isElementDisplayed(true, "link_LoginTest");
		logMessage("Verified: Navigation to Login Prompt page.");
		navigateToPreviousPage();
	}

	public void verifyCiteThisInfoOnArticlePage(String page) {
		String validPatternForCiteInfo = "";
		String formatPattern = "";
		String CiteText = "Cite This:";
		isElementDisplayed(true, "text_CiteThis");
		Assert.assertTrue(element("text_CiteThis").getText().equals(CiteText),
				"Assertion Failed: Text not correct in Cite this section.");
		logMessage("Verified: Cite this: label is displayed.");

		String jourName = elements("hyperLink_CiteThisInfo").get(0).getText().trim();
		String year = elements("hyperLink_CiteThisInfo").get(1).getText().trim();
		String vol = elements("hyperLink_CiteThisInfo").get(2).getText().trim();
		String issue = elements("hyperLink_CiteThisInfo").get(3).getText().trim();
		String pageRange = elements("hyperLink_CiteThisInfo").get(4).getText().trim();

		String foundPatternForCiteInfo = jourName + " " + year + ", " + vol + ", " + issue + ", " + pageRange;
		System.out.println("---------   " + foundPatternForCiteInfo);

		if (page.equals("Current Issue")) {
			formatPattern = "[(Journal name acronym.) Year, Volume, Issue, PageX-pageY]";
			validPatternForCiteInfo = "[A-Za-z. ]*\\s\\d{4}\\,\\s\\d{1,4}\\,\\s\\d{1,4}\\,\\s\\d{1,4}\\-\\d{1,4}";
		}

		if (page.equals("Paginated ASAP")) {
			formatPattern = "[(Journal name acronym.) Year, XXXX, XXX, pageX-pageY]";
			validPatternForCiteInfo = "[A-Za-z. ]*\\s\\d{4}\\,\\sXXXX\\,\\sXXX\\,\\s\\d{1,4}\\-\\d{1,4}";
		}

		if (page.equals("Just Accepted")) {
			formatPattern = "[(Journal name acronym.) Year, XXXX, XXX, XXX-XXX]";
			validPatternForCiteInfo = "[A-Za-z. ]*\\s\\d{4}\\,\\sXXXX\\,\\sXXX\\,\\sXXX\\-XXX";
		}

		if (page.equals("Articles ASAP")) {
			formatPattern = "[(Journal name acronym.) Year, Volume, XXX, pageX-pageY]";
			validPatternForCiteInfo = "[A-Za-z. ]*\\s\\d{4},\\s[0-9 X]*,\\sXXX,\\s[0-9 X]*-[0-9 X]*";
			// validPatternForCiteInfo = "[A-Za-z.
			// ]*\\s\\d{4}\\,\\sXXXX\\,\\sXXX\\,\\sXXX-XXX";
		}

		Assert.assertTrue(verifyFormat(foundPatternForCiteInfo, validPatternForCiteInfo),
				"Assertion Failed: Cite Info hyperlink text not displayed in the correct format.");
		logMessage("Verified: Cite Label hyperlink text displayed in " + formatPattern + " format.");
	}

	public void clickOnCiteThisHyperlinkAndVerify() {
		element("link_CiteThis").click();
		logMessage("User clicked on the Cite This hyperlink text.");
		wait.waitForPageToLoadCompletely();
		isElementDisplayed(true, "h3_DownloadCitations");
		isElementDisplayed(true, "button_RisCiteDownload");
		logMessage("Verified: clicking on Cite Label hyperlink text takes user to Download Citation page.");
	}

	public int verifyQuickViewTabListOnRightSideOfArticlePage() {
		Assert.assertTrue(elements("TabList_QV").size() >= 2,
				"Assertion failed: 2 or more tabs are not displayed in the QV navigation tablist.");
		int tabListSize = elements("TabList_QV").size();
		logMessage("Verified: " + tabListSize + " Tabs are displayed in the QV navigation tablist.");
		isElementDisplayed(true, "Icon_QVClose");

		int i;
		// for(String TabName: QVTabList){
		// isStringMatching(elements("TabList_QV").get(i).getAttribute("title"),
		// TabName);
		// i++;
		// }
		Boolean flag = false;
		for (i = 0; i < tabListSize; i++) {
			for (String TabExpected : QVTabList) {
				if (elements("TabList_QV").get(i).getAttribute("title").equals(TabExpected)) {
					logMessage("Info: QV tab- " + TabExpected + " is displayed.");
					flag = true;
				}
			}
			Assert.assertTrue(flag, "Assertion failed: Tab at index " + i + 1 + " is not a valid Tabname");
		}
		logMessage("Verified: Tabs in QV Tablist.");
		return tabListSize;
	}

	public void clickOnEachTabAndVerifyData(int tabSize) {
		String tabName = "";
		int i = 1;
		scrollDown(element("text_Abstract"));

		Assert.assertTrue(elements("Tab_DefaultSelected").get(0).getAttribute("class").contains("active"),
				"Assertion failed: " + tabName + " is not selected by default.");
		tabName = elements("TabList_QV").get(0).getAttribute("title");
		logMessage("Verified: Tab: " + tabName + " is selected by default.");

		while (i < tabSize) {
			if (tabName.equals("Figures")) {
				Assert.assertTrue(elements("Imgs_FigureTab").size() >= 1,
						"Assertion failed: Figures are not displayed under QV tab");
				logMessage("Verified: " + elements("Imgs_FigureTab").size() + " figures are displayed under " + tabName
						+ " tab.");
			}

			elements("TabList_QV").get(i).click();
			tabName = elements("TabList_QV").get(i).getAttribute("title");
			logMessage("Info: Clicked on: " + tabName + " tab.");
			Assert.assertTrue(elements("TabList_QV").get(i).getAttribute("aria-expanded").equals("true"),
					"Assertion failed: " + tabName + " is not selected.");
			logMessage("Verified: Tab: " + tabName + " is selected.");

			if (tabName.equals("References")) {
				Assert.assertTrue(elementsinDom("References_Tab").size() >= 1,
						"Assertion failed: References are not displayed under QV tab");
				logMessage("Verified: " + elementsinDom("References_Tab").size() + " refrences are displayed under "
						+ tabName + " tab.");
			}

			if (tabName.equals("Support Info")) {
				Assert.assertTrue(element("text_SupportingInfo").isDisplayed(),
						"Assertion failed: Supporting Info is not displayed under QV tab");
				logMessage("Verified: Supporting Information is displayed under " + tabName + " tab.");
			}

			if (tabName.equals("Primary Data")) {
				Assert.assertTrue(elements("links_PrimaryData").size() >= 1,
						"Assertion failed: Accession codes primary data are not displayed under QV tab");
				logMessage("Verified: Accession codes are displayed under " + tabName + " tab.");
			}

			i++;
		}

	}

	public void clickOnCloseIconAndVerifyQVShortcutIsDisplayed(int tabSize) {
		element("Icon_QVClose").click();
		logMessage("Info: Clicked on Close icon of QV tablist.");
		hardWait(1);
		Assert.assertTrue(elements("shortcut_QVTabs").size() == tabSize,
				"Assertion failed: Tablist shortcut is not activated.");
		logMessage("Verified: Shortcut for QV tablist is activated upon clicking on (x) icon.");
		scrollUp();

	}

	public void scrollPastAbstractSectionAndVerifyStickyHeader() {
		scrollToTop();
		hardWait(2);
		logMessage("Info: Before scroll:");
		Assert.assertTrue(element("stickyHeader").getAttribute("class").contains("inactive"),
				"Assertion failed: Sticky header is displayed.");
		logMessage("Verified: Sticky header is not displayed (As expected)");
		scrollDown();
		logMessage("Info: Scrolled past the Abstract section.");
		hardWait(1);
		Assert.assertFalse(element("stickyHeader").getAttribute("class").contains("inactive"),
				"Assertion failed: Sticky header is not displayed.");
		logMessage("Verified: Sticky header is now displayed after scrolling past 'Abstract' section. (As expected)");
	}

	public void verifyElementsOnStickyHeader() {
		isElementDisplayed(true, "h4_StickyHeaderTitle");
		isStringMatching(element("h4_StickyHeaderTitle").getText(), element("h1_ArticleTitle").getText());

		isElementDisplayed(true, "QuickSearch_StickyHeader");
		isElementDisplayed(true, "HamburgerMenu_StickyHeader");
		isElementDisplayed(true, "JournalLogo_StickyHeader");

		if (checkIfElementIsThere("NvgtnArrows_StickyHeader", "Previous"))
			logMessage("Previous button is displayed on the sticky header.");
		if (checkIfElementIsThere("NvgtnArrows_StickyHeader", "Next"))
			logMessage("Next button is displayed on the sticky header.");
		logMessage("Verified: Elements on Sticky header.");
	}

	public void verifyFunctionalityOfNextPreviousButtonsOnStickyHeader() {
		logMessage("Info: Verifying functionality of 'Next and 'Previous' buttons on sticky header");
		String TitleBefore = element("h4_StickyHeaderTitle").getText();

		Boolean flag = checkIfElementIsThere("NvgtnArrows_StickyHeader", "Next");
		if (flag) {
			clickOnElement("NvgtnArrows_StickyHeader", "Next");
			hardWait(2);
			scrollDown();
			hardWait(1);
			Assert.assertFalse(TitleBefore.equals(element("h4_StickyHeaderTitle").getText()),
					"Assertion failed: Next button on sticky header did not work.");
			logMessage("Verified: 'Next' button on sticky header works as expected.");
		}

		if (checkIfElementIsThere("NvgtnArrows_StickyHeader", "Previous")) {
			clickOnElement("NvgtnArrows_StickyHeader", "Previous");
			hardWait(2);
			scrollDown();
			hardWait(1);
			if (flag)
				Assert.assertTrue(TitleBefore.equals(element("h4_StickyHeaderTitle").getText()),
						"Assertion failed: Previous button on sticky header did not work.");
			else
				Assert.assertFalse(TitleBefore.equals(element("h4_StickyHeaderTitle").getText()),
						"Assertion failed: Previous button on sticky header did not work.");

			logMessage("Verified: 'Previous' button on sticky header works as expected.");
		}

	}

	public void clickOnJournalLogoOnStickyHeader() {
		logMessage("Info: Verifying functionality of Journal logo on sticky header");
		clickOnElement("JournalLogo_StickyHeader");
		hardWait(2);
	}

	public void clickOnQuickSearchIconOnStickyHeader() {
		logMessage("Info: Verifying functionality of Quick Search icon on sticky header");
		clickOnElement("QuickSearch_StickyHeader");
	}

	public void clickOnHamburgerIconOnStickyHeader() {
		logMessage("Info: Verifying functionality of Hamburger icon on sticky header");
		clickOnElement("HamburgerMenu_StickyHeader");
	}

	public void clickOnCoverArtImageAndVerifyNavigation() {
		clickOnElement("img_CoverArt");
		hardWait(2);
		Assert.assertTrue(getCurrentURL().contains("toc"),
				"Assertion failed: Cover image on article page doesnt navigate to the issue page it came from.");
		logMessage("Verified: Cover image on article page navigates to the issue page it came from.");
	}

	public void verifyFunctionalityOfHoverOnAuthornames() {
		int i = 1;
		for (WebElement auth : elements("txt_author")) {
			logMessage("Author: " + auth.getText());
			hover(auth);
			hardWait(1);
			Assert.assertTrue(element("PopUp_AuthorHover", Integer.toString(i), "name").isDisplayed());
			logMessage("Info: Author name is displayed on the popup.");
			Assert.assertTrue(element("AuthorPopup_Affiliations", Integer.toString(i)).getText() != null,
					"Assertion failed: Author affiliations not displayed.");
			logMessage("Info: Affiliations are displayed on the popup.");
			Assert.assertTrue(element("AuthorPopup_MoreBy", Integer.toString(i)).getText() != null,
					"Assertion failed: More By author link not displayed.");
			logMessage("Info: More By author link is displayed on the popup.");
			i++;
		}
	}

	public String verifyThatArticleIs(String ArticleType) {
		String LineExpectedColor = "rgba(253, 200, 47, 1)";
		String Type = "", NoticeDesc = "", TitleText = "";

		if (ArticleType.equals("Correction")) {
			Type = "original";
			NoticeDesc = "This notice is a correction";
			TitleText = "Correction to";
		} else {
			Type = "retraction";
			NoticeDesc = "This notice is a retraction.";
			TitleText = "Retraction of";
			isElementDisplayed(true, "link_DOIRetraction");
			logMessage("Verified: DOI link to original article is displayed for " + ArticleType + " article.");
		}

		isElementDisplayed(true, "icon_asterisk", Type);

		isStringMatching(element("texts_ArticleType", Type, "type").getText(), "ORIGINAL ARTICLE");
		isStringMatching(element("texts_ArticleType", Type, "desc").getText(), NoticeDesc);

		String ArticleTitle = element("h1_ArticleTitle").getText();

		Assert.assertTrue(ArticleTitle.contains(TitleText),
				"Assertion failed: Article title doesnt start with '" + TitleText + "'");
		logMessage("Verified: Article title starts with '" + TitleText + "'");

		System.out.println(element("span_ArticleTypeLine").getCssValue("background-color"));
		Assert.assertTrue(element("span_ArticleTypeLine").getCssValue("background-color").equals(LineExpectedColor),
				"Assertion failed: " + ArticleType + " msg is not displayed in correct color.");
		logMessage("Verified: " + ArticleType + " text is displayed in correct color (Yellow).");

		return ArticleTitle;

	}

	public void clickOnOriginalArticleLink(String ArticleType) {
		String Type = "";
		if (ArticleType.equals("Correction"))
			Type = "original";
		else
			Type = "retraction";

		element("link_ArticleType", Type).click();
		logMessage("Info: Clicked on 'ORIGINAL ARTICLE' link for " + ArticleType + " article.");
	}

	public void verifyArticleHasBeen(String ArticleType, String ArticleTitle) {
		String Type = "", ArticleRelationType = "", NoticeDesc = "";
		String LineExpectedColor = "rgba(223, 42, 46, 1)";

		if (ArticleType.equals("Corrected")) {
			Type = "correction";
			ArticleRelationType = "ADDITION / CORRECTION";
			NoticeDesc = "This article has been corrected. View the notice.";
		} else {
			Type = "retracted-original";
			ArticleRelationType = "RETRACTION";
			NoticeDesc = "This article has been retracted. View the notice.";
		}

		Assert.assertTrue(ArticleTitle.contains(element("h1_ArticleTitle").getText()),
				"Assertion failed: Navigated to a different article page.");

		isElementDisplayed(true, "icon_asterisk", Type);

		isStringMatching(element("texts_ArticleType", Type, "type").getText(), ArticleRelationType);
		isStringMatching(element("texts_ArticleType", Type, "desc").getText(), NoticeDesc);

		System.out.println(element("span_ArticleTypeLine").getCssValue("background-color"));
		Assert.assertTrue(element("span_ArticleTypeLine").getCssValue("background-color").equals(LineExpectedColor),
				"Assertion failed: " + ArticleType + " msg is not displayed in correct color.");
		logMessage("Verified: " + ArticleType + " text is displayed in correct color (Red).");
	}

	public boolean clickAndVerifyRefernceTab() {
		scrollDown(element("text_Abstract"));
		if (checkIfElementIsThere("tab_references")) {
			wait.hardWait(2);
			clickOnElement("tab_references");
			logMessage("Info: User clicked on References link");
			return true;
		} else {
			logMessage("References not present for this article.");
			return false;
		}
		// scrollDown(element("text_Abstract"));
		// isElementDisplayed(true, "tab_references");
		// clickOnElement("tab_references");
		// logMessage("INFO: Clicked On References tab");
	}

	public int clickRandomlyOnReferneceNumber() {
		int randomIndex;
		// if (elements("link_referencesNum").size()!=0){
		// randomIndex = random.nextInt(3);
		// scrollDown(elements("link_referencesNum").get(randomIndex));
		// }else
		if (elements("link_referencesNum").size() != 0)
			elements("link_referencesNum").get(0).click();
		logMessage("INFO: Clicked On Reference Number: " + 0);
		return 1;
	}

	public int totalReferencesUnderReferencesTab() {
		isElementDisplayed(true, "References_Tab");
		int count = elements("References_Tab").size();
		return count;
	}

	public void clickOnNextLink() {
		String Title1 = getArticleTitle();
		clickOnNavigationButton("Next");
		String Title2 = getArticleTitle();
		Assert.assertFalse(Title1.equals(Title2));
		logMessage("Info: User clicked on 'Next' link on article page.");
	}

	public void verifyAccessDenialMsgDisplayed() {
		isElementDisplayed(true, "Msg_AccessDenial");
		logMessage("Verified: Access denial msg is displayed for the article: Article is not freely available.");
	}

	public void verifyPDFDisplayed() {
		isElementDisplayed(true, "btn_Pdf");
	}

	public void clickOnCiteThisLink() {
		scrollToTop();
		element("link_CiteThis").click();
		logMessage("User clicked on the Cite This hyperlink text.");
		wait.waitForPageToLoadCompletely();
	}

	public void clickOnExportRISIcon() {
		element("Icon_ExportRIS").click();
		logMessage("Info: Clicked on Export RIS icon.");
		hardWait(1);
		Assert.assertTrue(element("Icon_ExportRIS").getAttribute("aria-expanded").equals("true"),
				"Assertion failed: Export RIS icon not expanded.");
		logMessage("Info: Export RIS icon is expanded.");
	}

	public void verifyOptionsDisplayedUnderExportRIS() {
		Assert.assertTrue(elements("options_ExportRIS").size() == 4,
				"Assertion failed: 4 options not displayed under Export RIS icon.");
		logMessage("Verified: 4 options displayed under Export RIS dropdown");
	}

	public void clickOnMoreOptionsUnderExportRIS() {
		element("link_ExportRISOption", "More Options").click();
		logMessage("Info: User clicked on 'More Options' link under Export RIS dropdown");
		hardWait(2);
	}

	public ArrayList<String> GetArticleMetadata() {
		int i = 1;
		ArrayList<String> metadata = new ArrayList<String>();
		metadata.add(element("h1_ArticleTitle").getText());
		metadata.add(elementsinDom("meta_Article", "journal_title").get(0).getAttribute("content"));
		System.out.println(
				"Journal name:" + elementsinDom("meta_Article", "journal_title").get(0).getAttribute("content"));

		while (i <= 4) {
			System.out.println(elements("hyperLink_CiteThisInfo").get(i).getText().trim());
			metadata.add(elements("hyperLink_CiteThisInfo").get(i).getText().trim());
			i++;
		}

		// Metadata title, journalName, Year, Volume, Issue and page range
		return metadata;
	}

	public Boolean CheckIfAbstractPresent() {
		if (!checkIfElementIsThere("text_Abstract")) {
			isElementDisplayed(true, "header_SpecialIssue");
			logMessage("Info: Abstract is not displayed for the article :: Special Issue article.");
			return false;
		} else {
			logMessage("Info: Abstract displayed for the article.");
			return true;
		}
	}

	// public boolean clickOnReferencesLink() {
	//
	// return false;
	// }

	// public void clickAndVerifyQuickViewTab(int tabSize) {
	// String tabName = "";
	// int i = 1;
	// if (checkIfElementIsThere("text_Abstract"))
	// scrollDown(element("text_Abstract"));
	// else {
	// if (checkIfElementIsThere("btn_Navigation", "Next"))
	// clickOnElement("btn_Navigation", "Next");
	// else
	// clickOnElement("btn_Navigation", "Previous");
	// }
	// Assert.assertTrue(elements("Tab_DefaultSelected").get(0).getAttribute("class").contains("active"),
	// "Assertion failed: " + tabName + " is not selected by default.");
	// tabName = elements("TabList_QV").get(0).getAttribute("title");
	// logMessage("Verified: Tab: " + tabName + " is selected by default.");
	//
	// while (i < tabSize) {
	// if (tabName.equals("Figures")) {
	// Assert.assertTrue(elements("Imgs_FigureTab").size() >= 1,
	// "Assertion failed: Figures are not displayed under QV tab");
	// logMessage("Verified: " + elements("Imgs_FigureTab").size() + " figures
	// are displayed under " + tabName
	// + " tab.");
	// }
	//
	// elements("TabList_QV").get(i).click();
	// tabName = elements("TabList_QV").get(i).getAttribute("title");
	// logMessage("Info: Clicked on: " + tabName + " tab.");
	// Assert.assertTrue(elements("TabList_QV").get(i).getAttribute("aria-expanded").equals("true"),
	// "Assertion failed: " + tabName + " is not selected.");
	// logMessage("Verified: Tab: " + tabName + " is selected.");
	//
	// if (tabName.equals("References")) {
	// Assert.assertTrue(elementsinDom("References_Tab").size() >= 1,
	// "Assertion failed: References are not displayed under QV tab");
	// logMessage("Verified: " + elementsinDom("References_Tab").size() + "
	// refrences are displayed under "
	// + tabName + " tab.");
	// }
	//
	// if (tabName.equals("Support Info")) {
	// Assert.assertTrue(element("text_SupportingInfo").isDisplayed(),
	// "Assertion failed: Supporting Info is not displayed under QV tab");
	// logMessage("Verified: Supporting Information is displayed under " +
	// tabName + " tab.");
	// }
	//
	// if (tabName.equals("Primary Data")) {
	// Assert.assertTrue(elements("links_PrimaryData").size() >= 1,
	// "Assertion failed: Accession codes primary data are not displayed under
	// QV tab");
	// logMessage("Verified: Accession codes are displayed under " + tabName + "
	// tab.");
	// }
	//
	// i++;
	// }
	//
	// }

	public void verifyNoReferenceLinkText() {
		assertTrue(element("txt_noReference").getText().equalsIgnoreCase("This publication has no References."),
				"Assertion failed: Articlepage containing no reference message is not displayed correctly");
		logMessage("Assertion passed: Articlepage containing no reference message is displayed correctly");
	}

	public int clickOnReferneceNumber(int number) {
		if (elements("link_referencesNum").size() != 0)
			elements("link_referencesNum").get(number).click();
		logMessage("INFO: Clicked On Reference Number: " + 0);
		return 1;
	}

	public String titleOfFirstReference() {
		return element("title_firstRef").getText().trim();
	}

	public void verifyReferenceSection() {
		
//		scrollDownRefstag();		
		assertTrue(((String) executeJavascript("return document.querySelector('.refs-header-label h2').innerText"))
				.contains("References"), "Assertion Failed: Reference section is missing");
		logMessage("Assertion Passed: Reference section is present");
	}

	public int clickOnRandomReferenceSectionNumber() {
		hardWait(1);
		int randomIndex = 0;
		if (elements("link_refrnceSectionNum").size() != 0) {
			randomIndex = random.nextInt(3);
			// scrollDown(elements("link_refrnceSectionNum").get(randomIndex));
			elements("link_refrnceSectionNum").get(randomIndex).click();
			logMessage("INFO: Clicked On Reference Number: " + randomIndex);
			return randomIndex + 1;
		} else {
			elements("link_refrnceSectionNum").get(0).click();
			logMessage("INFO: Clicked On Reference Number: " + 0);
			return randomIndex + 1;
		}
	}

	public int totalReferencesUnderReferencesSection() {
		isElementDisplayed(true, "link_refrnceSectionNum");
		int count = elements("link_refrnceSectionNum").size();
		System.out.println("Total references count is: " + count);
		return count;
	}

	public String titleOfFirstReferenceSection() {
		return element("title_referenceSection").getText().trim();
	}

	public int clickOnSelectedReferneceNumber(int number) {
		if (elements("link_refrnceSectionNum").size() != 0)
			elements("link_refrnceSectionNum").get(number).click();
		logMessage("INFO: Clicked On Reference Number: " + 0);
		return 1;
	}

	public HashMap<String, String> getReferencesDetails() {
		HashMap<String, String> reference = new HashMap();
		String journalName, year, Vl, Sp;
		int i = 0;
		List<WebElement> authors = elements("Details_Reference1", "string-name");
		for (WebElement elem : authors) {
			// logMessage("reference authors:" + elem.getText());
			reference.put("AU" + i, elem.getText());
			i++;
		}
		journalName = element("JournalName_Reference1").getText();
		reference.put("JO", journalName);
		year = element("Details_Reference1", "year").getText();
		reference.put("Y1", year);
		Vl = element("Details_Reference1", "volume").getText();
		reference.put("VL", Vl);
		Sp = element("Details_Reference1", "fpage").getText();
		reference.put("SP", Sp);
		System.out.println("Reference Details:");
		System.out.println(reference);
		return reference;

	}

	public String getAbstractData() {
		String AbsText;
		if (checkIfElementIsThere("Text_ArticleAbstract")) {
			logMessage("Info: Fetching Abstract text..");
			AbsText = element("Text_ArticleAbstract").getText();
			return AbsText;
		} else {
			logMessage("Info: Abstract Text is not displayed for the article.");
			return null;
		}
	}

	public void verifyCiteBySection() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.END).build().perform();
		isElementDisplayed(true, "block_CiteBy");
		logMessage("INFO: CiteBy Section is present");
	}

	public int totalCiteByDataCount() {
		int totalList = 0;
		if (checkIfElementIsThere("list_citeByContent"))
			totalList = elements("list_citeByContent").size();
		return totalList;

	}

	public void verifyTextForNoCiteByContent(int countCite) {
		String text = "This article has not yet been cited by other publications.";
		if (countCite == 0) {
			assertTrue(element("txt_noCiteByContent").getText().trim().equalsIgnoreCase(text),
					"Assertion Failed: CiteBy no content message is missing");
			logMessage("Assertion Passed: CiteBy no content message is present");
		} else
			logMessage("INFO: CiteBy content message is present");
	}

	public void verifyReferencesCountOnPubsAndStag(int countRef) {
		//scrollDownRefpubs();
		assertTrue(((String) executeJavascript(
				"return document.querySelector('.NLM_notes~ul[class=\"anchors\"]+h2').innerText"))
						.contains("References"),
				"Assertion Failed: Reference section is missing");
		logMessage("Assertion Passed: Reference section is present");
		isElementDisplayed(true, "link_refrnceSectionNum");
		int count = elements("link_refrnceSectionNum").size();
		assertTrue(countRef == count, "Assertion Failed: Total Reference Count on Stag and Pubs is Mismatched");
		logMessage("Assertion Passed: Total Reference Count on Stag and Pubs are same");
		System.out.println("Total references count is: " + count);
	}

	public ArrayList<String> getIssueDOIs() throws IOException{
		int i=0;
		ArrayList<String> DOI = new ArrayList<>();
		for(WebElement issue: elements("doi_articles")){
			URL url = new URL(issue.getAttribute("href"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println("------------" + code);
			System.out.println(issue.getAttribute("href"));
			if (code <404 && i<5){
				DOI.add(issue.getAttribute("href"));
				i++;
			}
			if(i==5)
				break;
			} return DOI;
		}
}
