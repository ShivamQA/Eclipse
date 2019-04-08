package com.qait.acs.keywords;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.getYamlValues;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import com.qait.automation.utils.SeleniumWait;

public class homePageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "homePage";
	ArticleLandingPageActions article;

	public homePageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
		article = new ArticleLandingPageActions(driver);
	}

	public void clickOnPublicationLink() {
		scrollToTop();
		wait.hardWait(2);
		clickOnElement("btn_publication");
	}

	public void clickOnPubModalLink(String link) {
		hardWait(2);
		clickOnElement("lnk_pubmodal", link);
		verifyTypeIsSelected(link);

	}

	public void verifyPubModelContent(String title) {
		assertTrue(isElementDisplayed(true, "pubmodelContent", title),
				"Assertion passed: Title on PubModel content is displayed for: " + title);
		logMessage("Assertion Passed: Title on PubModel content is displayed for: " + title);
	}

	public void clickOnPubModelContent(String title) {
		verifyPubModelContent(title);
		clickOnElement("pubmodelContent", title);
		wait.waitForPageToLoadCompletely();
	}

	public void navigateToHomePage() {
		driver.get(getYamlValue("baseUrl"));
		wait.waitForPageToLoadCompletely();
	}

	public void verifyExistenceAndNavigationOfTopLeftCornerLinks(String[] links) {
		isElementDisplayed(true, "lnk_topLeftCornerLinks", links[0]);
		clickOnElement("lnk_topLeftCornerLinks", links[0]);
		logMessage("User clicked on ACS link");
		verifyUserIsOnACSPage();
		navigateToPreviousPage();

		isElementDisplayed(true, "lnk_topLeftCornerLinks", links[1]);
		clickOnElement("lnk_topLeftCornerLinks", links[1]);
		logMessage("User clicked on ACS Publications link");
		verifyUserIsOnACSPubHomePage();
		wait.waitForPageToLoadCompletely();

		isElementDisplayed(true, "lnk_topLeftCornerLinks", links[2]);
		clickOnElement("lnk_topLeftCornerLinks", links[2]);
		logMessage("User clicked on C&EN link");
		verifyUserIsOnCENPage();
		navigateToPreviousPage();

		isElementDisplayed(true, "lnk_topLeftCornerLinks", links[3]);
		clickOnElement("lnk_topLeftCornerLinks", links[3]);
		logMessage("User clicked on CAS link");
		verifyUserIsOnCASPage();

	}

	public void verifyUserIsOnACSPubHomePage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed(true, "Img_homePage");
		logMessage("Verified: User is on  Home page of the application!!!");

	}

	public void verifyUserIsOnACSPage() {
		hardWait(1);
		Assert.assertTrue(getCurrentURL().contains("acs.org"));
		isElementDisplayed(true, "logo_acs");
		logMessage("Verified: User is on ACS Page");

	}

	public void verifyUserIsOnCENPage() {
		hardWait(5);
		Assert.assertTrue(getCurrentURL().contains("cen.acs.org"), "Assertion failed: Not navigated to correct page.");
		scrollDownMid();
		isElementDisplayed(true, "logo_C&EN");
		logMessage("Verification Passed: User is on C&EN Page");
	}

	public void verifyUserIsOnCASPage() {
		isElementDisplayed(true, "logo_CAS");
		logMessage("Verification Passed: User is on CAS Page");

	}

	public void VerifyAndClickOnLoginLinkAtTopRight() {
		isElementDisplayed(true, "link_LogIn");
		clickOnElement("link_LogIn");
	}

	public void verifyMyActivityButtonIsDisplayed() {
		isElementDisplayed(true, "btn_MyActivity");
	}

	public void clickOnLogoutLink() {
		elements("links_loggedIn").get(1).click();
		logMessage("Info: User clicked on Logout link.");
		wait.waitForPageToLoadCompletely();
		isElementDisplayed(true, "link_LogIn");
		logMessage("Info: Successfully logged out.");

	}

	public void verifyUserLoggedIn(String Name) {
		String TextExpected = "Welcome:" + Name + "|Logout";

		isElementDisplayed(true, "Text_LoggedInfo");
		Assert.assertTrue(element("Text_LoggedInfo").getText().equals(TextExpected),
				"Assertion failed: Expected Welcome Text not displayed after login.");
		logMessage("Verified: Welcome text is displayed on top right after user has logged in.");

		Assert.assertTrue(elements("links_loggedIn").get(0).getText().equals(Name));
		Assert.assertTrue(elements("links_loggedIn").get(1).getText().equals("Logout"));

		logMessage("Verified: User is able to log in successfully.");
	}

	public void userclickedOnLoggedIn() {
		hardWait(1);
		elements("links_loggedIn").get(0).click();
		logMessage("Verified: User is able to log in successfully.");
	}

	public void verifyPubsLogoIsDisplayed() {
		isElementDisplayed(true, "Img_homePage");
		logMessage("Verified: Pubs Logo displayed on the main navigation panel.");
	}

	public void verifySearchBoxIsDisplayed() {
		isElementDisplayed(true, "box_search");
		logMessage("Verified: Search box is displayed on the main navigation panel.");
	}

	public void verifyPublicationsButtonIsDisplayed() {
		isElementDisplayed(true, "btn_publication");
		logMessage("Verified: Publications button is displayed on the main navigation panel.");
	}

	public void verifyHamburgerMenuIconIsDisplayed() {
		isElementDisplayed(true, "Icon_Hamburger");
		logMessage("Verified: Hamburger menu icon is displayed on the main navigation panel.");
	}

	public void clickOnPubsLogoAndVerify(String URL) {
		clickOnElement("Img_homePage");
		wait.waitForPageToLoadCompletely();
		Assert.assertTrue(getCurrentURL().equals(URL), "Assertion failed: Page is not refreshed.");
		isElementDisplayed(true, "Img_homePage");
		logMessage("Verfied: Clicking on Pubs logo refreshes the page (links to Pubs homepage)");

	}

	public void verifyElementsOnSearchBox() {
		isElementDisplayed(true, "input_SearchBox");
		logMessage("Verified: Search textbox is displayed for search box. ");
		isElementDisplayed(true, "iconSearch_SearchBox");
		logMessage("Verified: Search icon is displayed for search box.");
	}

	public void ClickAnywhereInSearchBox() {
		clickOnElement("input_SearchBox");
	}

	public void verifyCitationsSearchBoxIsNotYetVisible() {
		isElementDisplayed(false, "Searchbox_Citations");
		logMessage("Verified: Citations search box is not displayed. Only default search box is displayed.");
	}

	public void verifyExpandedCitationSearchBoxIsDisplayed() {
		isElementDisplayed(true, "Searchbox_Citations");
		logMessage("Verified: Clicking anywhere in the search show expanded citation search box.");
	}

	public void verifyOptionsOnExpandedCitationsSearchBox() {
		isStringMatching(element("header_Citations").getText(), "OR SEARCH CITATIONS");

		isElementDisplayed(true, "dropdown_JournalsSearchBox");
		Assert.assertEquals(element("dropdown_JournalsSearchBox").getText(), "Journals");
		logMessage("Verified: Journals dropdown is displayed on expanded citation search box.");

		isElementDisplayed(true, "input_OptionsSearchBox", "Vol");
		isElementDisplayed(true, "input_OptionsSearchBox", "Page");
		logMessage("Verified: Volume and Page textbox is displayed on expanded citation search box. ");

		isElementDisplayed(true, "iconSearch_Citation");
		logMessage("Verified: Citation search icon is displayed on expanded citation search box. ");

	}

	public void verifyPublicationsModalIsDisplayed() {
		isElementDisplayed(true, "modal_Publications");
		Assert.assertTrue(elements("ContentTypes_Modal").size() == 4,
				"Assertion Failed: 4 content types are not displayed.");
		logMessage("Verified: The Publications are divided into 4 types based on 'Content Types'");

	}

	public void clickOnHamburgerIcon() {
		clickOnElement("Icon_Hamburger");
	}

	public void verifyMenuOptionsDisplayedForHamburger(String[] menuOptions) {
		isElementDisplayed(true, "Menu_Hamburger");
		int OptionsCount = elements("MenuHeadings_Hamburger").size();
		Assert.assertTrue(OptionsCount == 4, "Assertion failed: 4 menu option headings.");
		logMessage("Verified: 4 menu option headings are displayed.");

		for (int i = 0; i < OptionsCount; i++) {
			isStringMatching(elements("MenuHeadings_Hamburger").get(i).getText(), menuOptions[i]);
		}
		logMessage("Verified: Clicking on hamburger menu icon displays the correct menu options");

	}

	public void verifyMenuOptionsDisplayedForHamburgerOnStickyHeader() {
		isElementDisplayed(true, "Menu_Hamburger");
		int OptionsCount = elements("MenuHeadings_Hamburger").size();
		Assert.assertTrue(OptionsCount == 4, "Assertion failed: 4 menu option headings.");
		logMessage("Verified: 4 menu option headings are displayed.");
		isElementDisplayed(true, "PubButton_Hamburger");
		logMessage("Verified: Publications button is displayed inside the Hamburger menu.");

	}

	public void verifyContentTypesDisplayedOnModal(String[] contentTypes) {
		int i = 0;
		verifyTypeIsSelected(contentTypes[0]);

		for (WebElement ContentTypeActual : elements("ContentTypes_Modal")) {
			isStringMatching(ContentTypeActual.getText(), contentTypes[i]);
			i++;
		}
		logMessage("Verified: Different content types are available on Publications modal");
	}

	public void verifyTypeIsSelected(String ContentType) {
		hardWait(2);
		Assert.assertTrue(element("ContentType_Selected", ContentType).getAttribute("class").contains("active"));
		logMessage("Verified: " + ContentType + " is selected.");
	}

	public void verifyAllTypesOfContentIsDisplayedUnderAllTypes() {
		int size = elements("links_PubsContent").size();
		logMessage("Number of links displayed for 'All Types': " + size);

		for (WebElement ContentDisplayed : elements("links_PubsContent")) {
			String Type = ContentDisplayed.getAttribute("class");
			Assert.assertTrue(Type.contains("journals") || Type.contains("books") || Type.contains("news"));
		}
		logMessage("Verified: Links displayed for 'All types' content type fall under journals, books or news");
	}

	public void verifyOnlyJournalsAreDisplayedUnderJournalsContentType() {
		int size = elements("links_PubsContent").size();
		logMessage("Number of links displayed for 'Journals' Type: " + size);

		for (WebElement ContentDisplayed : elements("links_PubsContent")) {
			String Type = ContentDisplayed.getAttribute("class");
			Assert.assertTrue(Type.contains("journals"));
		}
		logMessage("Verified: Links displayed for 'Journals' content type fall under Journals");
	}

	public void verifyOnlyBooksAreDisplayedUnderBooksContentType() {
		int size = elements("links_PubsContent").size();
		logMessage("Number of links displayed for 'Books' Type: " + size);

		for (WebElement ContentDisplayed : elements("links_PubsContent")) {
			String Type = ContentDisplayed.getAttribute("class");
			Assert.assertTrue(Type.contains("books"));
		}
		logMessage("Verified: Links displayed for 'Books' content type fall under Books");

	}

	public void verifyOnlyNewsAreDisplayedUnderNewsContentType() {
		int size = elements("links_PubsContent").size();
		logMessage("Number of links displayed for 'News' Type: " + size);

		for (WebElement ContentDisplayed : elements("links_PubsContent")) {
			String Type = ContentDisplayed.getAttribute("class");
			Assert.assertTrue(Type.contains("news"));
		}
		logMessage("Verified: Links displayed for 'News' content type fall under News");

	}

	public void ClickOnAnyLinkAndVerifyNavigationToCorrectPage() {
		int LinksSize = elements("links_PubsContent").size();
		Random random = new Random();
		int randomIndex = random.nextInt(LinksSize);

		String type = elements("links_PubsContent").get(randomIndex).getAttribute("class");
		String a = elements("links_PubsContent").get(randomIndex).getAttribute("href");
		String hrefComponent = a.substring(a.lastIndexOf('/') + 1);

		elements("links_PubsContent").get(randomIndex).click();
		if (type.contains("journals"))
			logMessage("Info: Clicked on a random link for 'Journal'");
		else if (type.contains("books"))
			logMessage("Info: Clicked on random link for 'Books'");
		else
			logMessage("Info: Clicked on random link for 'News'");

		wait.waitForPageToLoadCompletely();

		Assert.assertTrue(getCurrentURL().contains(hrefComponent));
		logMessage("Verified: Correct Navigation of clicking on any link");

	}

	public void verifySubjectsSectionDisplayed() {
		isElementDisplayed(true, "Section_Subjects");
		isElementDisplayed(true, "header_Subjects");
		isStringMatching(element("header_Subjects").getText(), "SUBJECTS");

		Assert.assertTrue(elements("CheckBoxes_Subjects").size() == 7);
		logMessage("Verified: 7 subjects present under Subject section.");
	}

	public void verifySubjectSectionIsNotDisplayedForBooksAndNewsType() {
		clickOnPubModalLink("Books and Reference");
		verifySubjectSectionIsNotDisplayed();
		clickOnPubModalLink("News");
		verifySubjectSectionIsNotDisplayed();
	}

	public void verifySubjectSectionIsNotDisplayed() {
		isElementDisplayed(false, "Section_Subjects");
	}

	public void verifyLetterHeadingsDisplayed() {

		logMessage("Info: " + elements("headings_Alphabets").size() + " letter headings are displayed.");
		for (WebElement letterHeadings : elements("headings_Alphabets")) {
			verifyFormat(letterHeadings.getText(), "[A-Z]");
		}
	}

	public void verifyLetterHeadingsNotDisplayedForBooksAndNewsType() {
		clickOnPubModalLink("Books and Reference");
		verifyLetterHeadingsAreNotDisplayed();
		clickOnPubModalLink("News");
		verifyLetterHeadingsAreNotDisplayed();
	}

	public void verifyLetterHeadingsAreNotDisplayed() {
		isElementDisplayed(false, "headings_Alphabets");
	}

	public void enterSearchTermAndClickOnSearchIcon(String SearchTerm) {
		sendKeys(SearchTerm, "input_SearchBox");
		clickOnElement("iconSearch_SearchBox");
		wait.waitForPageToLoadCompletely();
	}

	public String enterSearchDetailsInCitationSearchBox(String searchTerm, String journal, String vol, String page) {
		clickOnElement("dropdown_JournalsSearchBox");
		hardWait(2);
		element("DD_SearchJournals", journal).click();

		String selectedJournal = element("selectedJournal_Search").getText();
		System.out.println(selectedJournal);
		sendKeys(vol, "input_OptionsSearchBox", "Vol");
		sendKeys(page, "input_OptionsSearchBox", "Page");
		clickOnElement("iconSearch_Citation");
		return selectedJournal;

	}

	public void verifySublinksUnderOptions(String heading, Map<String, Object> links) {
		Assert.assertTrue(elements("subLinks_Hamburger", heading).size() == links.size());
		logMessage("Info: " + links.size() + " links displayed under " + heading.toUpperCase()
				+ " section on hamburger menu.");
		int i = 0;

		for (String expectedLink : links.keySet()) {
			isStringMatching(elements("subLinks_Hamburger", heading).get(i).getText(),
					links.get(expectedLink).toString());
			i++;
		}
		logMessage("Verified: All sublinks are displayed for " + heading + " option on hamburger.");

	}

	public void verifyEditorsChoiceSection() {
		isStringMatching(elements("texts_EditorsChoice").get(0).getText(), "ACS Editors’ Choice");
		isStringMatching(elements("texts_EditorsChoice").get(1).getText(), "View all 1,000+ Open Access Articles");

		Assert.assertTrue(elementsinDom("slides_EditorsChoice").size() == 7);
		logMessage("Info: 7 featured articles displayed in the ACS Editors' choice section.");

		Assert.assertTrue(elementsinDom("images_EditorsChoice").size() == 7);
		logMessage("Verified: 7 corresponding images present in DOM.");

		Assert.assertTrue(elementsinDom("JounalNames_EditorsChoice").size() == 7);
		logMessage("Verified: 7 corresponding journal names present in DOM.");

		Assert.assertTrue(elementsinDom("Titles_EditorsChoice").size() == 7);
		logMessage("Verified: 7 corresponding titles present in DOM.");

		Assert.assertTrue(elementsinDom("authors_EditorsChoice").size() == 7);
		logMessage("Verified: 7 corresponding author names present in DOM.");

		Assert.assertTrue(elementsinDom("radioButton_Slides").size() == 7);
		logMessage("Verified: 7 radio buttons for navigation are displayed.");

		Assert.assertTrue(elementsinDom("buttons_SlideNavigation").size() == 2);
		logMessage("Verified: 2 buttons for navigation are displayed.");

	}

	public void clickOnNavigationArrowAndVerifySlideHasChanged() {
		int CurrentSlide = getCurrentSlideNumber();
		logMessage("Current slide:" + CurrentSlide);
		navigateToNextSlide();
		int NewSelectedSlide = getCurrentSlideNumber();
		logMessage("Current slide after navigation:" + NewSelectedSlide);
		Assert.assertTrue(CurrentSlide != NewSelectedSlide);
		logMessage("Verified: Successful slide navigation by arrow button.");
	}

	public void navigateToNextSlide() {
		try {
			elements("buttons_SlideNavigation").get(1).click();
		} catch (StaleElementReferenceException e) {
			elements("buttons_SlideNavigation").get(1).click();
		}
		logMessage("Clicked on next arrow button.");
		wait.waitForPageToLoadCompletely();
	}

	public int getCurrentSlideNumber() {
		int highlightSlideNumber;
		highlightSlideNumber = Integer.parseInt(element("slide_selected").getAttribute("id").split("-")[1]);
		return (highlightSlideNumber + 1);
	}

	public void navigateToSlideAtIndex(Integer index) {
		--index;
		try {
			elements("radioButton_Slides").get(index).click();
		} catch (StaleElementReferenceException e) {
			elements("radioButton_Slides").get(index).click();
		}
		logMessage("Clicked 'Slide Navigation' button at index " + (index + 1));
	}

	public void clickOnRadioButtonAndVerifySlideHasChanged() {
		int CurrentSlide = getCurrentSlideNumber();
		logMessage("Current slide:" + CurrentSlide);
		Random random = new Random();
		int SlideSelected = random.nextInt(7);
		if (SlideSelected == 0)
			SlideSelected++;
		System.out.println("random: " + SlideSelected);
		navigateToSlideAtIndex(SlideSelected);
		int NewSelectedSlide = getCurrentSlideNumber();
		System.out.println("selected:" + NewSelectedSlide);
		Assert.assertTrue(SlideSelected == NewSelectedSlide);
		logMessage("Verified: Successful slide navigation by arrow button.");
	}

	public String clickOnSlideAndVerifyNavigation() {
		String ArticleTitle = element("title_selectedSlide").getAttribute("title");
		clickOnElement("slide_selected");
		wait.waitForPageToLoadCompletely();
		return ArticleTitle;
	}

	public void verifyInformationForSectionOnRight(Map<String, Object> links) {
		isElementDisplayed(true, "h2_Section", "Information For");
		Assert.assertTrue(elements("links_InformationSection").size() == links.size());
		logMessage("Info: " + links.size() + " links displayed under Information For section on right of homepage.");
		int i = 0;

		for (String expectedLink : links.keySet()) {
			isStringMatching(elements("links_InformationSection").get(i).getAttribute("title"),
					links.get(expectedLink).toString());
			i++;
		}
		logMessage("Verified: All links are displayed under Information For section.");

	}

	public void verifyNavigationOfLinksUnderInformationForSection() {
		scrollToTop();
		String linkName = "";
		int size = elements("links_InformationSection").size();
		for (int i = 0; i < size; i++) {
			linkName = elements("links_InformationSection").get(i).getAttribute("title");
			elements("links_InformationSection").get(i).click();
			logMessage("Info: User clicked on link: '" + linkName + "'.");
			VerifyLandingPageOfInformationLinks(linkName);
			navigateToPreviousPage();
		}

	}

	private void VerifyLandingPageOfInformationLinks(String linkName) {
		String PageUrl = getCurrentURL();
		Map<String, Object> links = getYamlValues("HomePage.InformationFor");

		if (linkName.equals(links.get("link1"))) {
			isElementDisplayed(true, "h1_AuthorReviewerPage");
			Assert.assertTrue(PageUrl.contains("publish"));
		}

		if (linkName.equals(links.get("link2"))) {
			isElementDisplayed(true, "img_LibrariansPage");
			isElementDisplayed(true, "login_LibrariansPage");
			Assert.assertTrue(PageUrl.contains("librarians"));
		}

		if (linkName.equals(links.get("link3"))) {
			isElementDisplayed(true, "tab_MembersPage");
			Assert.assertTrue(PageUrl.contains("member-benefits"));
		}

		logMessage("Verified: Navigation to '" + linkName + "' landing page.");

	}

	public void verifySignUpForEmailSectionOnRight() {
		isElementDisplayed(true, "h2_Section", "Sign Up for Email");
		isElementDisplayed(true, "description_Section", "Sign Up for Email");
		isElementDisplayed(true, "btn_eAlerts");
		logMessage("Verified: Sign Up for Email section.");
	}

	public void clickOnEAlertsButtonAndVerifyNavigation() {
		scrollToTop();
		scrollVertical(200);
		clickOnElement("btn_eAlerts");
		wait.waitForPageToLoadCompletely();
		isElementDisplayed(true, "h1_LoginPromptPage");
		isElementDisplayed(true, "btn_LoginOrRegister");
		logMessage("Verified: Navigation for eAlerts button.");
	}

	public void clickOnLoginOrRegisterButton() {
		clickOnElement("btn_LoginOrRegister");
		wait.waitForPageToLoadCompletely();

	}

	public void verifyAboutACSSectionOnRight() {
		isElementDisplayed(true, "h2_Section", "About ACS");
		isElementDisplayed(true, "description_Section", "About ACS");
		logMessage("Verified: About ACS section.");
	}

	public void verifyBrowsePublicationsSection(Map<String, Object> tabList) {
		int i = 0;
		String tabName;
		for (String expectedTab : tabList.keySet()) {
			tabName = tabList.get(expectedTab).toString();
			isElementDisplayed(true, "tabs_BrowsePublications", tabName);
			logMessage("Info: '" + tabName + "' displayed.");
			if (i == 0) {
				Assert.assertTrue(
						element("tabs_BrowsePublications", tabName).getAttribute("aria-expanded").equals("true"));
				logMessage("Info: '" + tabName + "' is selected by default.");
			}
			i++;
		}
		logMessage("Verified: All tabs are displayed under Browse Publications section.");

	}

	public void verifyGridViewSectionForJournals(String journalCount) {
		Assert.assertTrue(elementsinDom("imgs_JournalCoverArt").size() == Integer.parseInt(journalCount));
		logMessage("Verified: " + journalCount + " journal art covers displayed under Cover Art Section.");
	}

	public void clickOnJournalCoverArtAndVerifyNavigation(String journalCount) {
		Random random = new Random();
		int coverArtIndex = random.nextInt(Integer.parseInt(journalCount));

		String a = elements("imgs_JournalCoverArt").get(coverArtIndex).getAttribute("href");
		String hrefComponent = a.substring(a.lastIndexOf('/') + 1);

		elementsinDom("imgs_JournalCoverArt").get(coverArtIndex).click();
		logMessage("Info: User clicked on journal cover art at index: " + coverArtIndex);
		wait.waitForPageToLoadCompletely();

		Assert.assertTrue(getCurrentURL().contains(hrefComponent));
		logMessage("Verified: Navigation upon clicking on journal cover art.");

	}

	public void clickOnTabUnderBrowsePublicationsSection(String tabName) {
		scrollToTop();
		scrollVertical(650);
		clickOnElement("tabs_BrowsePublications", tabName);

	}

	public void verifyListViewSectionForJournals(String listCount) {
		Assert.assertTrue(elements("links_ListView").size() == Integer.parseInt(listCount));
		logMessage("Verified: " + listCount + " links displayed under List view Section.");
	}

	public void verifyLetterHeadingsDisplayedForListView() {
		logMessage("Info: " + elements("ListHeadings_Alphabets").size() + " letter headings are displayed.");
		for (WebElement letterHeadings : elements("ListHeadings_Alphabets")) {
			verifyFormat(letterHeadings.getText(), "[A-Z]");
		}
	}

	public void ClickOnAnyLinkUnderListViewAndVerifyNavigationToCorrectPage(String listCount) {
		Random random = new Random();
		int randomIndex = random.nextInt(Integer.parseInt(listCount));

		String type = elements("links_ListView").get(randomIndex).getAttribute("class");
		String a = elements("links_ListView").get(randomIndex).getAttribute("href");
		String hrefComponent = a.substring(a.lastIndexOf('/') + 1);

		elements("links_ListView").get(randomIndex).click();
		if (type.contains("journals"))
			logMessage("Info: Clicked on a random link for 'Journal'");
		else if (type.contains("books"))
			logMessage("Info: Clicked on random link for 'Books'");
		else
			logMessage("Info: Clicked on random link for 'News'");

		wait.waitForPageToLoadCompletely();

		Assert.assertTrue(getCurrentURL().contains(hrefComponent));
		logMessage("Verified: Correct Navigation of clicking on any link");
	}

	public void verifyCoverArtOverlaysUponHover() {
		scrollVertical(800);
		Point style = elements("imgs_JournalCoverArt").get(2).getLocation();
		System.out.println(style);
		hover(elements("imgs_JournalCoverArt").get(2));
		hardWait(2);
		style = elements("imgs_JournalCoverArt").get(2).getLocation();
		System.out.println(style);
	}

	public void verifyPartnersAndTheirNavigation(Map<String, Object> partners) {
		int i = 0;
		String PartnerName;
		for (String Partner : partners.keySet()) {
			PartnerName = partners.get(Partner).toString();
			Assert.assertTrue(elements("imgs_partners").get(i).getAttribute("src").contains(PartnerName.toLowerCase()));
			logMessage("Info: Partner '" + PartnerName + "' displayed.");

			String expectedURL = elements("links_partners").get(i).getAttribute("href").replaceAll("http", "https");
			if (expectedURL.contains("crosscheck"))
				expectedURL = "https://www.crossref.org/services/similarity-check/";
			if (expectedURL.contains("orcid"))
				expectedURL = expectedURL.split("www.")[1];
			System.out.println(expectedURL);

			scrollDown();
			elements("links_partners").get(i).click();
			logMessage("User clicked on partner:" + PartnerName + "' .");
			wait.waitForPageToLoadCompletely();

			Assert.assertTrue(getCurrentURL().contains(expectedURL),
					"Assertion failed: Incorrect landing page for partner: " + PartnerName);
			logMessage("Verified: Landing page for partner: " + PartnerName);
			navigateToPreviousPage();
			i++;
		}
		logMessage("Verified: All Partners are displayed in footer section and navigate to correct pages.");

	}

	public void verifyCopyrightSectionDisplayedAtFooter(String CopyRightText) {
		scrollDown();
		isElementDisplayed(true, "link_Copyright");
		isStringMatching(element("link_Copyright").getText().trim(), CopyRightText.replaceAll("Â", ""));
	}

	public void clickOnCopyrightLinkAndVerifyLandingPage() {
		clickOnElement("link_Copyright");
		wait.waitForPageToLoadCompletely();
		Assert.assertTrue(getCurrentURL().contains("copyright"), "Assertion failed: Incorrect landing page.");
		isStringMatching(element("h1_Copyright").getText(), "ACS Copyright Information");
		logMessage("Verified: Navigation of copyright link at footer.");
	}

	public void VerifyPolicyLinkAtFooter() {
		scrollDown();
		isElementDisplayed(true, "link_PrivacyPolicy");
	}

	public void CLickOnPrivacyPolicyLinkAndVerifyLandingPage() {
		clickOnElement("link_PrivacyPolicy");
		wait.waitForPageToLoadCompletely();
		Assert.assertTrue(getCurrentURL().contains("privacy"),
				"Assertion failed: Not navigated to Privacy policy page.");
		isElementDisplayed(true, "h1_PrivacyPolicy");
		logMessage("Verified: Navigated to Privacy Policy page.");

	}

	public void verifyFollowIconsDisplayedAtFooter(Map<String, Object> icons) {
		String IconName;
		scrollDown();
		for (String index : icons.keySet()) {
			IconName = icons.get(index).toString();
			isElementDisplayed(true, "icons_Follow", IconName);
			logMessage("Info: '" + IconName + "' share icon is displayed.");
		}
		logMessage("Verified: All follow icons are displayed in footer.");

	}

	public void clickOnEachIconAndVerifyLandingPage() {
		/*
		 * Update when fixed.
		 */
	}

	public void ClickOnEachSubjectCheckBoxAndVerifyJournalListDisplayed() {
		List<String> JournalList1 = new ArrayList<String>();
		List<String> JournalList2 = new ArrayList<String>();

		elements("CheckBoxes_Subjects").get(0).click();
		hardWait(1);
		logMessage("Info: Clicked on checkbox for Subject: " + elements("text_Subjects").get(0).getText());
		logMessage("Info: " + elements("links_PubsContent").size()
				+ " journals are displayed corresponding to the selected subject.");

		for (WebElement JournalTitle : elements("links_PubsContent")) {
			String JournalsTitles = JournalTitle.getText().trim();
			JournalList1.add(JournalsTitles);
		}
		elements("CheckBoxes_Subjects").get(0).click();
		hardWait(1);

		for (int i = 1; i < elements("CheckBoxes_Subjects").size(); i++) {
			elements("CheckBoxes_Subjects").get(i).click();
			hardWait(1);
			logMessage("Info: Clicked on checkbox for Subject: " + elements("text_Subjects").get(i).getText());
			logMessage("Info: " + elements("links_PubsContent").size()
					+ " journals are displayed corresponding to the selected subject.");

			for (WebElement JournalTitle : elements("links_PubsContent")) {
				String JournalsTitles = JournalTitle.getText().trim();
				JournalList2.add(JournalsTitles);
			}

			Assert.assertNotEquals(JournalList1, JournalList2,
					"Assertion Failed: Journal list for two subjects do not differ.");
			logMessage("Verified: Journals list differs as compared to previous subject selected.");

			elements("CheckBoxes_Subjects").get(i).click();
			hardWait(1);
			JournalList1.clear();
			JournalList1.addAll(JournalList2);
		}

	}

	public void clickOnInputBoxOfSearchIcon() {
		clickOnElement("input_SearchBox");
	}

	public void clickOnSearchIcon() {
		clickOnElement("iconSearch_SearchBox");
	}

	public void selectJournal(String journalName) {
		element("PublicationsLink", journalName).click();
		logMessage("Info: Clicked on journal link:" + journalName);
	}

	public void clickOnEditorsChoiceHeadingLink() {
		elements("texts_EditorsChoice").get(0).click();
		logMessage("Info: Clicked on 'ACS Editors' Choice' heading link.");
		hardWait(2);
	}

	public void clickOnViewAll1000ArticlesLink() {
		elements("texts_EditorsChoice").get(1).click();
		logMessage("Info: Clicked on 'View all 1,000+ Open Access Articles' heading link.");
		hardWait(2);
	}

	public void verifyMyActivityButton() {
		isElementDisplayed(true, "btn_myActivity");
	}

	public void clickOnMyActivityButton() {
		clickOnElement("btn_myActivity");
	}

	public void verifyMyActivityTwoColumn() {
		isElementDisplayed(true, "box_myActivityContent");
		logMessage("INFO: My Activity Content box is dispalyed");

		isElementDisplayed(true, "col_recentlyViewed");
		logMessage("INFO: Recently View coulmn is dispalyed");

		isElementDisplayed(true, "col_recommendedDropzone");
		logMessage("INFO: recommended Drop zone column is dispalyed");
	}

	public void verifyDefaultRecentlyViewedText() {
		String msg = "You have not visited any articles yet, Please visit some articles to see contents here.";
		isElementDisplayed(true, "header_recentlyViewed");
		isElementDisplayed(true, "txt_emptyRecentlyViewedList");
		assertTrue(element("txt_emptyRecentlyViewedList").getText().trim().contains(msg),
				"Assertion Failed: Default message for empty Recently Viewed List is incorrect");
		logMessage("Assertion Passed: Default message for empty Recently Viewed List is correct");
	}

	public void verifyDefaultRecommendedTextLoggedIn() {
		String msg = "Read more content to get recommendations. Please check again later.";
		isElementDisplayed(true, "header_recommendation");
		isElementDisplayed(true, "txt_showRecommended");
		assertTrue(element("txt_showRecommended").getText().trim().contains(msg),
				"Assertion Failed: Default message for empty Recommended List is incorrect");
		logMessage("Assertion Passed: Default message for empty Recommended List is correct");
	}

	public void verifyNoRecommendedWhenNotLoggedInText() {
		String msg = "Please login first to get recommendations";
		isElementDisplayed(true, "header_recommendation");
		isElementDisplayed(true, "txt_recommendedNotLogged");
		assertTrue(element("txt_recommendedNotLogged").getText().trim().contains(msg),
				"Assertion Failed: Text message for no Recommended When Not Logged In is incorrect");
		logMessage("Assertion Passed: Text message for no Recommended When Not Logged In is correct");
	}

	public void verifyRecentlyViewedCount(int articleSize) {
		int journalSize = elements("list_recentlySerialTitle").size();
		assertTrue(articleSize == journalSize, "Assertion Failed: Journal titles for the ");

		int articleCount = elements("list_recentlyViewedArticle").size();
		assertTrue(articleSize == articleCount, "Assertion Failed: Journal titles for the ");
	}

	public void verifyPreviouslyVisitedRecentlyViewedArticle(String article) {
		String viewedArticle = elements("list_recentlyViewedArticle").get(0).getText().trim();
		assertTrue(viewedArticle.equalsIgnoreCase(article),
				"Assertion Failed: Recently viewed article in the column list is not same as the prevoiusly viewed article");
		logMessage(
				"Assertion Passed: Recently viewed article in the column list is same as the prevoiusly viewed article");
	}

	public void verifyTotalRecentlyViewedList() {
		int articleCount = elements("list_recentlyViewedArticle").size();
		assertTrue(articleCount == 5,
				"Assertion Failed: Total/Maximum list of articles can be contained in recently viewed column is not 5");
		logMessage("Assertion Passed: Total/Maximum list of articles can be contained in recently viewed column is 5");
	}

	public void verifyRecentlyViewedListInQueueOrder() {
		Boolean present;
		int articleCount = elements("list_recentlyViewedArticle").size();
		String lastArticle = elements("list_recentlyViewedArticle").get(articleCount - 1).getText();
		clickOnMyActivityButton();
		article.clickOnNextLink();
		driver.navigate().refresh();
		clickOnMyActivityButton();

		present = returnRecentlyViewedListArticlesName(lastArticle);

		assertFalse(present, "Assertion Failed: Recently Viewed List In Queue Order is not working properly");
		logMessage("Assertion Passed: Recently Viewed List In Queue Order is working properly");
	}

	public Boolean returnRecentlyViewedListArticlesName(String lastArticle) {
		Boolean articlePresent = false;
		for (WebElement article : elements("list_recentlyViewedArticle")) {
			if (article.getText().trim().contains(lastArticle)) {
				articlePresent = true;
				break;
			}
		}
		return articlePresent;
	}

}
