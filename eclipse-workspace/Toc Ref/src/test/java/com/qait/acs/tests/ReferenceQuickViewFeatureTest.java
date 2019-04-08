package com.qait.acs.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;

import org.testng.annotations.Test;

public class ReferenceQuickViewFeatureTest extends BaseTest {
	String journal, JournalHomePageURL;
	int indexRef, countTabRef, countRQVRef;

	@Test
	public void Step01_Verify_Navigation_To_Any_Article_Page() {
		journal = getYamlValue("Publications.Journals.Link1");
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Journals");
		test.homePage.selectJournal(journal);
		JournalHomePageURL = test.journalPage.verifyNavigationToCorrectJournalHomePage(journal);
		test.openUrl(JournalHomePageURL);
		String CollectionName = test.journalPage.selectRandomCollection();
		String ArticleTitle = test.journalPage.VerifyNavigationOfArticleFromFilmstripSection(CollectionName);
		test.articlePage.verifyNavigationToArticleLandingPage(ArticleTitle);
	}

	@Test
	public void Step02_RQV_01_Verify_Reference_Quick_View_PopUp_Clicking_On_Referneces_Quick_View_Tab() {
		// int tabSize =
		// test.articlePage.verifyQuickViewTabListOnRightSideOfArticlePage();
		// test.articlePage.clickAndVerifyQuickViewTab(tabSize);

		// test.launchApplication("https://achs-stag.literatumonline.com/doi/10.1021/acs.accounts.8b00090");
		test.articlePage.clickAndVerifyRefernceTab();
		countTabRef = test.articlePage.totalReferencesUnderReferencesTab();
		indexRef = test.articlePage.clickRandomlyOnReferneceNumber();
		test.refernceQuickView.verifyAppearanceOfRefernceQuickViewBox();
	}

	@Test
	public void Step03_RQV_02_Verify_Reference_QuickViewBox_Elements_Display_And_Functionality() {
		test.refernceQuickView.verifyDivisionOfRQVBoxintoTwohalves();
		test.refernceQuickView.verifyFirstReferenceDetailsDisplayedInTopHalfOfRQVBox();
		test.refernceQuickView.verifyFirstReferenceHighlightedInBottomHalfOfRQVBox(indexRef);
		test.refernceQuickView.verifyAbstarctAndBibliographicInfIsDisplayed();
		test.refernceQuickView.verifyScifinderLink();
		test.refernceQuickView.verifyExistenceOfVariousElementsOnRightOnRQVBox();
		test.refernceQuickView.verifySeeAllReferencesLink();
		test.refernceQuickView.clickOnSeeAllReferencesLink();
		test.refernceQuickView.verifySameTotalCountOfReferencesCountUnderTabAndRQVBox(countTabRef);
		test.refernceQuickView.verifyExistenceOfPreviousNextNavigationLinks(indexRef);

		// Navigated to pubs
		// test.refernceQuickView.verifyNavigationToFullArticleViaCASOnClickOfViewfullTextOptions();

	}

	@Test
	public void Step04_RQV_03_Verify_On_Clicking_Cancel_Button_RQV_Pop_Up_Closes() {
		test.refernceQuickView.clickOnRQVPopUpCloseButton();
	}

	// ACS Full Text is navigated to Pubs page
	@Test
	public void Step05_RQV_04_Verify_Citation_Links_Navigations_On_RQV_Pop_Up() throws IOException {
		Step02_RQV_01_Verify_Reference_Quick_View_PopUp_Clicking_On_Referneces_Quick_View_Tab();
		test.refernceQuickView.verifylinksAndNavigationOfCitationLinks();
	}

	@Test
	public void Step06_RQV_07_Verify_Associated_Text_For_Publication_Has_No_References() {
		test.launchApplication("https://achs-stag.literatumonline.com/doi/10.1021/acs.accounts.8b00525");
		test.articlePage.clickAndVerifyRefernceTab();
		test.articlePage.verifyNoReferenceLinkText();
	}

	@Test
	public void Step07_RQV_08_Verify_PrinterView_Is_Displayed_With_Copyright_On_Selecting_Reference()
			throws IOException {
		Step01_Verify_Navigation_To_Any_Article_Page();
		Step02_RQV_01_Verify_Reference_Quick_View_PopUp_Clicking_On_Referneces_Quick_View_Tab();
		test.refernceQuickView.clickAndVerifyPrinterViewPageWithCitationNavigation();
	}

	@Test
	public void Step08_RQV_09_Verify_Result_On_Clicking_Reference_Without_Reference_Match() {
		test.articlePage.verifyReferenceSection();
		test.articlePage.clickRandomlyOnReferneceNumber();
		test.refernceQuickView.verifyAppearanceOfRefernceQuickViewBox();
		test.refernceQuickView.verifyResulOfReferenceWithoutReferenceMatch();
		test.refernceQuickView.clickOnRQVPopUpCloseButton();
	}

	@Test
	public void Step09_RQV_10_Verify_OnClick_Of_SequenceLink_Only_Numbered_References_AreDispalyed_In_RQVBox() {
		test.articlePage.verifyReferenceSection();
		countTabRef = test.articlePage.totalReferencesUnderReferencesTab();
		int refIndex = 0;
		String title = test.articlePage.titleOfFirstReference();
		test.articlePage.clickOnReferneceNumber(refIndex);
		test.refernceQuickView.clickOnSingleReferenceNumberLinkAndVerifyTheContentsOfRQVBox(title, refIndex);
		test.refernceQuickView.clickOnSeeAllReferencesLink();
		test.refernceQuickView.verifyThecontentsOfRQVBoxOnClickOfSeeAllReferencesLink(countTabRef);
		test.refernceQuickView.clickOnMultipleReferenceNumberLinkAndVerifyTheContentsOfRQVBox();
		test.refernceQuickView.clickOnRQVPopUpCloseButton();
	}

	@Test
	public void Step10_RQV_11_Verify_Reference_Quick_View_Box_Elements_Display_And_Functionality_From_Reference_Section() {
		// test.launchApplication("https://achs-stag.literatumonline.com/doi/10.1021/acs.accounts.8b00356");
		test.articlePage.verifyReferenceSection();
		int countRef = test.articlePage.totalReferencesUnderReferencesSection();
		int index = test.articlePage.clickOnRandomReferenceSectionNumber();
		index = test.refernceQuickView.clickOnReferenceWithDetails(index);
		test.refernceQuickView.verifyDivisionOfRQVBoxintoTwohalves();
		test.refernceQuickView.verifyFirstReferenceDetailsDisplayedInTopHalfOfRQVBox();
		test.refernceQuickView.verifyFirstReferenceHighlightedInBottomHalfOfRQVBox(index);
		test.refernceQuickView.verifyAbstarctAndBibliographicInfIsDisplayed();
		test.refernceQuickView.verifyScifinderLink();
		test.refernceQuickView.verifyExistenceOfVariousElementsOnRightOnRQVBox();
		test.refernceQuickView.verifySeeAllReferencesLink();
		test.refernceQuickView.clickOnSeeAllReferencesLink();
		test.refernceQuickView.verifySameTotalCountOfReferencesCountUnderTabAndRQVBox(countRef);
		test.refernceQuickView.verifyExistenceOfPreviousNextNavigationLinks(index);
		// test.refernceQuickView.clickOnRQVPopUpCloseButton();
	}

	@Test
	public void Step11_RQV_12_Verify_On_Clicking_Cancel_Button_RQV_Pop_Up_Closes_From_Reference_Section() {
		test.refernceQuickView.clickOnRQVPopUpCloseButton();
	}

	@Test
	public void Step12_RQV_13_Verify_On_Click_Of_SequenceLink_Only_Numbered_References_Are_Dispalyed_In_RQVBox_From_Reference_Section() {
		int countRef = test.articlePage.totalReferencesUnderReferencesSection();
		int refIndex = 0;
		String title = test.articlePage.titleOfFirstReferenceSection();
		test.articlePage.clickOnSelectedReferneceNumber(refIndex);
		test.refernceQuickView.clickOnSingleReferenceNumberLinkAndVerifyTheContentsOfRQVBox(title, refIndex);
		test.refernceQuickView.clickOnSeeAllReferencesLink();
		test.refernceQuickView.verifyThecontentsOfRQVBoxOnClickOfSeeAllReferencesLink(countRef);
		test.refernceQuickView.clickOnMultipleReferenceNumberLinkAndVerifyTheContentsOfRQVBox();
		test.refernceQuickView.clickOnRQVPopUpCloseButton();
	}

	@Test
	public void Step13_RQV_15_Verify_Citation_Links_Navigations_On_RQV_Pop_Up_From_Reference_Section()
			throws IOException {
		// test.launchApplication("https://achs-stag.literatumonline.com/doi/10.1021/acs.accounts.8b00356");
		// test.articlePage.verifyReferenceSection();
		int index = test.articlePage.clickOnRandomReferenceSectionNumber();
		index = test.refernceQuickView.clickOnReferenceWithDetails(index);
		test.refernceQuickView.verifyDivisionOfRQVBoxintoTwohalves();
		test.refernceQuickView.verifylinksAndNavigationOfCitationLinks();
	}
}
