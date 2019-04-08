package com.qait.acs.tests;

import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.getYamlValues;

import com.qait.automation.TestSessionInitiator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class ACSJournals_Test extends BaseTest {	
	
	String journal;
	String shareOptionsJournal[]= {"Facebook","Twitter","LinkedIn","Google_Plus","Reddit","Email"};
	String shareOptions[]= {"Facebook","Twitter","Wechat","LinkedIn","Reddit","Email"};
	String CollectionName;
	String JournalHomePageURL="";

	@DataProvider(name = "Collection")
	  public static Object[][] Collections() {
	        return new Object[][] {{"Articles ASAP"} ,{"Current Issue"}, {"Most Read"}, {"Editors’ Choice"}};
	  }
	
	
	@Test
	public void Step01_AJ_01_Verify_Correct_Journal_Page_Loads_Upon_Selecting_Journal_From_Publications_Modal() {
		journal= getYamlValue("Publications.Journals.Link1");
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Journals");
		test.homePage.selectJournal(journal);
		JournalHomePageURL=test.journalPage.verifyNavigationToCorrectJournalHomePage(journal);
	}

	
	@Test
	public void Step02_AJ_02_Verify_Left_Column_Of_Journal_Header_On_Journal_HomePage() {
		test.journalPage.verifySubmitManuscriptButtonIsDisplayed();
		test.journalPage.verifyMetricsSection();
		test.journalPage.verifyActionLinksSection();
		test.journalPage.verifyShareIconAndItsFunctionality(shareOptionsJournal);
	}
	
	@Test
	public void Step03_AJ_03_Verify_Navigation_of_Links_In_Left_Column_Of_Journal_Header() {
		test.journalPage.clickOnSubmitManuscriptButtonAndVerifyNavigation();
		test.journalPage.clickOnRecommendThisJournalButtonAndVerifyNavigation();
		test.journalPage.clickOnSubscriptionInformationButtonAndVerifyNavigation();
		test.journalPage.clickOnShareIconAndVerifyNavigationOfOptions(shareOptionsJournal);
	}
	
	@Test
	public void Step04_AJ_04_Verify_Center_Column_Of_Journal_Header_On_Journal_HomePage() {
		test.journalPage.verifyEditorsInfoDisplayed();
		test.journalPage.verifyEditorEditorialBoardLinkIsDisplayed();
	}
	
	@Test
	public void Step05_AJ_05_Verify_Navigation_Of_Links_In_Center_Column_Of_Journal_Header() {
		test.journalPage.clickOnJournalLogoAndVerify();
		test.journalPage.clickOnEditorNameLinkAndVerifyNavigation();
		test.journalPage.clickOnEditorsEditorialBoardLinkAndVerifyNavigation();
	}

	// LITBETA-1930- e-Alerts link navigating to Test login page
	@Test
	public void Step06_AJ_06_Verify_Existance_And_Navigation_Of_Elements_In_Right_Column_Of_Journal_Header() {
		test.openUrl(JournalHomePageURL);
		String[] Metadata = test.journalPage.verifyVolumeAndIssueNumberMetaDataIsDisplayed();
		test.journalPage.verifyAndClickOnJournalCoverImage();
		test.issueTOCPage.verifyNavigationToCorrectIssueTOCPage(Metadata);
		test.navigateBack();
		test.journalPage.verifyeAlertsButtonAndItsNavigation();
	}
	
	@Test
	public void Step07_AJ_07_Verify_Existance_And_Navigation_Of_Blue_Navigation_Bar_Links() {
		Map<String, Object> links = getYamlValues("JournalHomePage.BlueNavigationBar");
		test.openUrl(JournalHomePageURL);
		test.journalPage.verifyLinksAvailableOnBlueNavigationBar(links);
		test.journalPage.clickOnListOfIssuesLink();
		test.loiPage.verifyNavigationToLOIPage();
		test.journalPage.clickOnSubmissionReviewLinkAndVerifyNavigation();
		test.journalPage.clickOnOpenAccessLinkAndVerifyNavigation();
		test.journalPage.clickOnAboutTheJournalLinkAndVerifyNavigation(journal);
	}
	
	@Test
	public void Step08_AJ_08_Verify_LOI_Widget_On_Journal_HomePage() {
		test.openUrl(JournalHomePageURL);
		test.journalPage.clickOnListOfIssuesLink();
		test.loiPage.verifyNavigationToLOIPage();
		test.loiPage.verifyElementsOfLOIWidget();
	}
	
	@Test
	public void Step09_AJ_09_Verify_Functionality_Of_LOI_Widget_On_Journal_HomePage() {
		String Decade="1990s", Year="1993";
		test.journalPage.clickOnListOfIssuesLink();
		test.loiPage.SelectAnyDecadeAndVerify(Decade);
		test.loiPage.SelectAnyYearAndVerify(Year);
	}

	@Test
	public void Step10_AJ_10_Verify_Navigation_Of_Issue_Title_Link_From_LOI() {
		test.journalPage.clickOnListOfIssuesLink();
		String[] Metadata = test.loiPage.clickOnAnyIssueLinkAndVerifyNavigation();
		test.issueTOCPage.verifyNavigationToCorrectIssueTOCPage(Metadata);
	}
	
	@Test
	public void Step11_AJ_11_Verify_Elements_On_ASAP_Page(){
		test.openUrl(JournalHomePageURL);
		test.journalPage.NavigateToCollectionPage("Articles ASAP");
		test.collectionPage.verifyElementsOnHeader("ASAP");
		test.collectionPage.clickOnCurrentIssueLink();
		test.currentIssuePage.verifyNavigationToCurrentIssuePage(journal);
		test.navigateBack();
		test.collectionPage.clickOnViewAllIssueLink();
		test.loiPage.verifyNavigationToLOIPage();
		test.navigateBack();
		test.collectionPage.verifyNavigationOfeALertsButton();
		test.collectionPage.verifyShareIconAndItsFunctionality(shareOptions);
	}
	
	@Test
	public void Step12_AJ_12_Verify_Elements_On_JAMS_Page(){
		journal= getYamlValue("Publications.Journals.jourJACS");
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Journals");
		test.homePage.selectJournal(journal);
		test.journalPage.verifyNavigationToCorrectJournalHomePage(journal);
		test.journalPage.NavigateToCollectionPage("Current Issue");
		test.currentIssuePage.verifyNavigationToCurrentIssuePage(journal);
		Boolean present=test.currentIssuePage.clickOnJAMSLink();
		if(present){
			test.collectionPage.verifyNavigationToCollectionLandingPage("Just Accepted Manuscripts");
		test.collectionPage.verifyElementsOnHeader("Just Accepted Manuscripts");
		test.collectionPage.clickOnCurrentIssueLink();
		test.currentIssuePage.verifyNavigationToCurrentIssuePage(journal);
		test.navigateBack();
		test.collectionPage.clickOnViewAllIssueLink();
		test.loiPage.verifyNavigationToLOIPage();
		test.navigateBack();
		test.collectionPage.clickOnASAPsLink();
		test.collectionPage.verifyNavigationToCollectionLandingPage("ASAP");
		test.navigateBack();
		test.collectionPage.clickOnJAMSFAQLinkAndVerify();
		test.collectionPage.verifyNavigationOfeALertsButton();
		test.collectionPage.verifyShareIconAndItsFunctionality(shareOptions);
		test.collectionPage.verifyElementsInArticleBlock("Just Accepted Manuscripts");
		String ArticleTitle = test.collectionPage.ClickOnArticleTitleFromArticleBlock();
		test.articlePage.verifyNavigationToArticleLandingPage(ArticleTitle);
		test.navigateBack();
		test.collectionPage.verifyFunctionalityOfAbstractFullTextPDFLinks("Just Accepted Manuscripts");
		test.collectionPage.verifyFunctionalityOfAbstractAngleDownIcon();
		}
	}
	
	@Test
	public void Step13_AJ_13_Verify_Elements_On_ACS_Editors_Choice_Page(){
		test.openUrl(JournalHomePageURL);
		test.journalPage.NavigateToCollectionPage("Editors’ Choice");
		test.collectionPage.verifyElementsOnHeader("Editors’ Choice");
		test.collectionPage.clickOnAboutEditorsChoiceLinkAndVerify();
		test.collectionPage.verifyNavigationOfeALertsButton();
		test.collectionPage.verifyShareIconAndItsFunctionality(shareOptions);
		test.collectionPage.verifySortByDropdownOnEditorsChoicePage();
		test.collectionPage.verifyPublicationsDropdownOnEditorsChoicePage();
	}
	
	@Test
	public void Step14_AJ_14_Verify_Functionality_Of_SortOptions_On_Editors_Choice_Page(){
		test.collectionPage.verifySelectingOptionsOnSortByDropdownOnECPageChangesResults();
		test.collectionPage.verifySelectingOptionsFromPublicationsDropdownOnECPageChangesResults();
	}
	
	@Test
	public void Step15_AJ_15_Verify_Elements_In_Collection_On_Journal_HomePage() {
		test.openUrl(JournalHomePageURL);
		CollectionName = test.journalPage.verifyElementsInCollectionSectionOnHomePage();
	}
	
	@Test
	public void Step16_AJ_16_Verify_User_Is_Able_To_Perform_Actions_On_Filmstrip_Section() {
		test.journalPage.VerifyFunctionalityOfNavigationArrowsInFilmstripSection(CollectionName);
		test.journalPage.VerifyFunctionalityOfNavigationGreyBallsInFilmstripSection(CollectionName);
		test.journalPage.clickOnMoreFromCollectionLink(CollectionName);
		test.collectionPage.verifyNavigationToCollectionLandingPage(CollectionName);
		test.navigateBack();
		String ArticleTitle = test.journalPage.VerifyNavigationOfArticleFromFilmstripSection(CollectionName);
		test.articlePage.verifyNavigationToArticleLandingPage(ArticleTitle);
	}
	
	
	// LITBETA-1928 - Citation String
	// Issue number in Most read and Editors choice is not showing correctly in DOM. Commented as of now
	@Test(dataProvider = "Collection")
	public void Step17_AJ_17_Verify_Article_Block_For_Collection_Page(String CollectionTitle) {
		test.openUrl(JournalHomePageURL);
		test.journalPage.NavigateToCollectionPage(CollectionTitle);
		test.collectionPage.verifyElementsInArticleBlock(CollectionTitle);
		String ArticleTitle = test.collectionPage.ClickOnArticleTitleFromArticleBlock();
		test.articlePage.verifyNavigationToArticleLandingPage(ArticleTitle);
		test.navigateBack();
		test.collectionPage.verifyFunctionalityOfAbstractFullTextPDFLinks(CollectionTitle);
		test.collectionPage.verifyFunctionalityOfAbstractAngleDownIcon();
	}
	
	
	@Test
	public void Step18_AJ_18_Verify_Elements_On_Most_Read_Page(){
		test.openUrl(JournalHomePageURL);
		test.journalPage.NavigateToCollectionPage("Most Read");
		test.collectionPage.verifyElementsOnHeader("Most Read");
		test.collectionPage.verifyTimeFrameOptionsAvailableOnMostReadPage();
		test.collectionPage.verifyDescriptionFor1MonthTimeframe(getYamlValue("Collections.MostRead.Text1Month"));
		test.collectionPage.verify20ArticlesAreDisplayedOnMostReadPage("1 Month");
		test.collectionPage.clickOnTimeframeOption("12 Months");
		test.collectionPage.verifyDescriptionFor1MonthTimeframe(getYamlValue("Collections.MostRead.Text12Month"));
		test.collectionPage.verify20ArticlesAreDisplayedOnMostReadPage("12 Months");
	}
	
	@Test
	public void Step19_AJ_19_Verify_Article_List_For_MostRead_1Month_And_12Months_Differs(){
		List<String> ArticleList=test.collectionPage.fetchArticleList();
		test.collectionPage.clickOnTimeframeOption("1 Month");
		test.collectionPage.VerifyArticleListsFor1MonthAnd12MonthsOptionDiffers(ArticleList);
	}
	
	@Test
	public void Step20_AJ_20_Verify_Elements_On_Journal_TOC_Page() {
		journal= getYamlValue("Publications.Journals.Link1");
		test.openUrl(JournalHomePageURL);
		test.journalPage.NavigateToCollectionPage("Current Issue");
		test.currentIssuePage.verifyNavigationToCurrentIssuePage(journal);
		test.currentIssuePage.verifyElementsOnJournalTOCPage();
		test.currentIssuePage.verifyFunctionalityOfClickingOnLinksUnderInThisIssueSectionOnJournalTOCPage();
		test.journalPage.verifyShareIconAndItsFunctionality(shareOptions);
		test.currentIssuePage.clickOnJournalLogoAndVerify();
		test.currentIssuePage.clickOnNextPreviousIssueLinksAndVerify();
		test.currentIssuePage.clickOnViewAllIssueLink();
		test.loiPage.verifyNavigationToLOIPage();
		test.navigateBack();
		test.currentIssuePage.clickOnASAPLink();
		test.collectionPage.verifyNavigationToCollectionLandingPage("ASAP");
		test.navigateBack();
		Boolean present=test.currentIssuePage.clickOnJAMSLink();
		if(present){
			test.collectionPage.verifyNavigationToCollectionLandingPage("Just Accepted Manuscripts");
			}
		test.navigateBack();
		test.currentIssuePage.verifyNavigationOfeALertsButton();
	}
	
	@Test
	public void Step21_AJ_21_Verify_Article_Block_Options_On_Journal_TOC_Page() {
		test.currentIssuePage.verifySortByDropDownDisplayedOnTOCPage();
		test.currentIssuePage.verifyDropDownOptions("Page","Date");
		test.currentIssuePage.verifyOptionIsSelected("Page");
		test.currentIssuePage.verifySortByPageForArticleBlock();
		test.currentIssuePage.clickOnSortByOption("Date");
		test.currentIssuePage.verifySortingByDateForArticleBlock();
	}

	// LITBETA-1936 - Cover image on article landing page tool tip text to be updated
	@Test
	public void Step29_AJ_29_Verify_Functionality_Of_Elements_On_Article_Landing_Page() {
		test.openUrl(JournalHomePageURL);
		String CollectionName= test.journalPage.selectRandomCollection();
		String ArticleTitle = test.journalPage.VerifyNavigationOfArticleFromFilmstripSection(CollectionName);
		test.articlePage.verifyNavigationToArticleLandingPage(ArticleTitle);
		test.articlePage.verifyElementsOnArticleLandingPage(CollectionName);
		test.articlePage.clickOnNextPreviousLinksAndVerify();
		test.articlePage.clickOnPDFLinkAndVerifyNavigation();
		String CollName=test.articlePage.clickOnReturnToTypeLinkAndVerify(CollectionName);
		test.collectionPage.verifyNavigationToCollectionLandingPage(CollName);
		test.navigateBack();
		test.articlePage.verifyeAlertsButtonAndItsNavigation();
		test.articlePage.clickOnCoverArtImageAndVerifyNavigation();
		test.navigateBack();
		test.articlePage.verifyFunctionalityOfHoverOnAuthornames();
		test.articlePage.clickOnShareIconAndVerify(shareOptions);		
	}
	
	@Test
	public void Step30_AJ_30_Verify_Functionality_Of_QuickView_Tablist_On_Article_Landing_Page() {
		int tabSize=test.articlePage.verifyQuickViewTabListOnRightSideOfArticlePage();
		test.articlePage.clickOnEachTabAndVerifyData(tabSize);
		test.articlePage.clickOnCloseIconAndVerifyQVShortcutIsDisplayed(tabSize);
	}
	
	@Test
	public void Step31_AJ_31_Verify_Sticky_Header_Available_On_Article_Page() {
		journal= getYamlValue("Publications.Journals.Link1");
		test.articlePage.scrollPastAbstractSectionAndVerifyStickyHeader();
		test.articlePage.verifyElementsOnStickyHeader();
		test.articlePage.verifyFunctionalityOfNextPreviousButtonsOnStickyHeader();
		test.articlePage.clickOnJournalLogoOnStickyHeader();
		test.journalPage.verifyNavigationToCorrectJournalHomePage(journal);
		test.navigateBack();
		test.articlePage.clickOnQuickSearchIconOnStickyHeader();
		test.homePage.verifyElementsOnSearchBox();
		test.articlePage.clickOnHamburgerIconOnStickyHeader();
		test.homePage.verifyMenuOptionsDisplayedForHamburgerOnStickyHeader();
	}
	
	@Test
	public void Step32_AJ_32_Verify_Correction_Retraction_Articles() {
		String ArticleTitle;
		test.launchApplication(getYamlValue("Publications.Journals.URLCorrection"));
		ArticleTitle=test.articlePage.verifyThatArticleIs("Correction");
		test.articlePage.clickOnOriginalArticleLink("Correction");
		test.articlePage.verifyArticleHasBeen("Corrected", ArticleTitle);
		test.launchApplication(getYamlValue("Publications.Journals.URLRetraction"));
		ArticleTitle=test.articlePage.verifyThatArticleIs("Retraction");
		test.articlePage.clickOnOriginalArticleLink("Retraction");
		test.articlePage.verifyArticleHasBeen("Retracted", ArticleTitle);
	}
		
	
	// Cite this info link is not text- verify and update name
	@Test
	public void Step33_AJ_33_Verify_Cite_This_Info_For_Current_Issue_Articles() {
		test.openUrl(JournalHomePageURL);
		String Page="Current Issue";
		test.journalPage.VerifyNavigationOfArticleFromFilmstripSection(Page);
		test.articlePage.verifyCiteThisInfoOnArticlePage(Page);
		test.articlePage.clickOnCiteThisHyperlinkAndVerify();
	}
	
	@Test
	public void Step34_AJ_34_Verify_Cite_This_Info_For_Articles_Under_Articles_ASAP_tab() {
		test.openUrl(JournalHomePageURL);
		String Page="Articles ASAP";
		test.journalPage.VerifyNavigationOfArticleFromFilmstripSection(Page);
		test.articlePage.verifyCiteThisInfoOnArticlePage(Page);
		test.articlePage.clickOnCiteThisHyperlinkAndVerify();
	}
	
	@Test
	public void Step35_AJ_35_Verify_Cite_This_Info_For_Articles_Under_Just_Accepted_tab(){
		String Page="Just Accepted";
		journal= getYamlValue("Publications.Journals.jourJACS");
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Journals");
		test.homePage.selectJournal(journal);
		test.journalPage.verifyNavigationToCorrectJournalHomePage(journal);
		test.journalPage.NavigateToCollectionPage("Current Issue");
		test.currentIssuePage.verifyNavigationToCurrentIssuePage(journal);
		Boolean present=test.currentIssuePage.clickOnJAMSLink();
		if(present){
			test.collectionPage.verifyNavigationToCollectionLandingPage("Just Accepted Manuscripts");
		test.collectionPage.ClickOnArticleTitleFromArticleBlock();
		test.articlePage.verifyCiteThisInfoOnArticlePage(Page);
		test.articlePage.clickOnCiteThisHyperlinkAndVerify();
		}
	}
	
	@Test
	public void Step36_AJ_36_Verify_Cite_This_Info_For_Paginated_ASAP_Articles(){
		String Page="Paginated ASAP";
		journal= getYamlValue("Publications.Journals.jourCatalysis");
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Journals");
		test.homePage.selectJournal(journal);
		test.journalPage.verifyNavigationToCorrectJournalHomePage(journal);
		test.journalPage.VerifyNavigationOfArticleFromFilmstripSection("Articles ASAP");
		test.articlePage.verifyCiteThisInfoOnArticlePage(Page);
		test.articlePage.clickOnCiteThisHyperlinkAndVerify();
	}
	

}