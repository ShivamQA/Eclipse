package com.qait.acs.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.qait.acs.tests.CustomReportCreator.class })
public class searchFunctionalityTest extends BaseTest {
	String searchTerm = "cell";
	String quotedSearchTerm = "\"cell\"";

	 @Test
	public void Step01_Verify_Navigation_To_UserProfile_Page() {
		test.launchApplication(getYamlValue("baseUrl"));
	}

	 @Test
	public void Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar() {
		test.homePage.verifySearchBoxIsDisplayed();
		test.homePage.enterSearchTermAndClickOnSearchIcon(searchTerm);
		test.searchResultsPage.verifySearchPageIsLoadedForSavedSearch(searchTerm);
	}

	 @Test
	public void Step03_SF_02_Verify_The_Formatting_For_Result_Page_Upon_Searching_Any_Value_With_Quotation_Marks() {
		String journal = getYamlValue("Publications.Journals.Link1");
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Journals");
		test.homePage.selectJournal(journal);
		test.journalPage.verifyNavigationToCorrectJournalHomePage(journal);
		test.homePage.verifySearchBoxIsDisplayed();
		test.homePage.clickOnInputBoxOfSearchIcon();
		// test.searchResultsPage.verifyRadioButtonSelectedForChoice(getYamlValue("Publications.Journals.Code1"));
		test.homePage.enterSearchTermAndClickOnSearchIcon(quotedSearchTerm);
		test.searchResultsPage.verifyNavigationToSearchResultsPage(quotedSearchTerm);
		test.searchResultsPage.verifySearchPageIsLoadedForSavedSearch(searchTerm);
	}

	 @Test
	public void Step04_SF_03_Verify_The_Existence_Of_Following_Elements_On_Search_Result_Page_And_Functionality_Works_Fine() {
		Step01_Verify_Navigation_To_UserProfile_Page();
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
		test.searchResultsPage.verifyFollowResultsOptions();
		test.searchResultsPage.verifyTwoSortByOptionAvailable();
		test.searchResultsPage.verifyRefineSearchButtonAndArrow();
		test.searchResultsPage.verifyPerPageOptionAvailability();
		test.searchResultsPage.verifyNarrowResultsTitleOptions();
		test.searchResultsPage.verifyPreviousNextPageNavigaionButton("Next");
	}

	 @Test
	public void Step05_SF_04_Verify_The_Existence_And_Navigation_Of_Following_Under_Save_This_Search_Box() {
		Step01_Verify_Navigation_To_UserProfile_Page();
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();

		test.searchResultsPage.clickOnSaveSearchButtonForThisSearchBox();
		test.searchResultsPage.verifySavedSearchPopUpIsClosed();
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
//		test.loginPage.EnterCredentials("acstest_sunena", "password");
		test.loginPage.EnterCredentials(getYamlValue("users.user2.username"), getYamlValue("users.user2.password"));
		test.searchResultsPage.clickOnSaveSearchButtonForThisSearchBox();

		String optionsForAlertMeDropDown[] = { "Never", "Daily", "Weekly", "Monthly" };
		test.searchResultsPage.verifySaveButtonUnderSaveThisSearchBoxIsClicked("Alert me to new results:",
				optionsForAlertMeDropDown);
		test.searchResultsPage.saveASearch(searchTerm, "Daily");
		test.searchResultsPage.clickAndVerifyEditSavedSearchButton();
		test.userProfileHomePage.verifyUserIsOnUserProfileHomePage();
		test.savedSearchesHomePage.verifySavedSearchTerm(searchTerm);
		test.savedSearchesHomePage.clickOnDeleteSearchButton(searchTerm);
		test.savedSearchesHomePage.verifySavedSearchTermIsDeleted(searchTerm);
		test.homePage.clickOnLogoutLink();
	}

	//Bug
	
//	@Test
//	public void Step06_SF_05_Verify_That_User_Is_Able_To_Perform_Following_Actions_On_User_Saved_Searches() {
//		
//	}
//	
	 @Test
	public void Step07_SF_06_Verify_The_Functionality_Of_Save_Search_When_A_User_Is_Not_Logged_Into_Application() {
		Step01_Verify_Navigation_To_UserProfile_Page();
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();

		test.searchResultsPage.clickOnSaveSearchButtonForThisSearchBox();
		test.searchResultsPage.verifylogInLinkOnSaveSearchPopUp();
		test.searchResultsPage.clickOnSaveSearchPopUpLoginButton();
//		test.searchResultsPage.VerifyAndClickOnLoginLink();
//		test.loginPage.EnterCredentials("acstest_sunena", "password");
		test.loginPage.EnterCredentials(getYamlValue("users.user2.username"), getYamlValue("users.user2.password"));
		test.homePage.userclickedOnLoggedIn();
		test.userProfileHomePage.verifyUserIsOnUserProfileHomePage();
		test.homePage.clickOnLogoutLink();
		
//		test.userProfileHomePage.clickAndverifyUserIsOnSavedSearchesPage("Saved Searches");
//		test.savedSearchesHomePage.clickOnDeleteSearchButton(searchTerm);
//		test.savedSearchesHomePage.verifySavedSearchTermIsDeleted(searchTerm);
		
	}

	 @Test
	public void Step08_SF_07_Verify_The_Filters_Works_As_Expected_Under_The_Narrow_Result_Section() {
		Step01_Verify_Navigation_To_UserProfile_Page();
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
		
		//Result count of selected filter dismatch
		test.searchResultsPage.verifySingleTheNarrowResultSelectedOption(searchTerm);
	}

	 @Test
	public void Step09_SF_08_Verify_The_Search_Results_Displays_The_Correct_Number_Of_Results() {
		 Step01_Verify_Navigation_To_UserProfile_Page();
			Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
		 int page = 20;
		test.searchResultsPage.verifyPerPageOptionAvailability();
		test.searchResultsPage.clickOnPerPageOption(page);
		test.searchResultsPage.verifyTheSelectedPerPage(page);
		test.searchResultsPage.verifySearchResultCount(page);
		test.searchResultsPage.clickAndVerifyPreviousNextPageNavigaionButton(page, "Next");
		test.searchResultsPage.clickAndVerifyPreviousNextPageNavigaionButton(page, "Previous");
	}
	 
	 //Failing due to mismatched volumn pages

//	 @Test
	public void Step10_SF_09_Verify_That_User_Is_Able_To_Find_Specified_Article_Using_Select_A_Journal_Or_Book_Series_Combo_Box_And_Volume_Or_Page_Number_Of_Journal() {
		Step01_Verify_Navigation_To_UserProfile_Page();
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();

		test.homePage.clickOnInputBoxOfSearchIcon();
		test.searchResultsPage.verifysearchCitationOption();
		test.searchResultsPage.selectDropDownJournalOption(getYamlValue("Publications.Journals.Link1"));
		test.searchResultsPage.enterValueInVolumnTextBox("49");
		test.searchResultsPage.enterValueInPageTextBox("20");
		test.searchResultsPage.clickOnCitationSearchButton();
		test.journalPage.verifyNavigationToCorrectJournalHomePage(getYamlValue("Publications.Journals.Code1"));
		// test.journalPage.verifyVolume();
	}

	 
	 // Failing due to highlights are not working 
//	 @Test
		public void Step11_S10_Verifies_Search_Results_Have_Snippets_On_Articles_Having_Searched_Word_In_Highlighted_Or_Bolded_Form() {
		 Step01_Verify_Navigation_To_UserProfile_Page();
			Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
			test.searchResultsPage.selectCriteriaFromAdvanceSearchDropdown("1", "Anywhere");
			test.searchResultsPage.enterTitleOfArticleForSearchCriteria("\"spectroscopy\"");
			test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
//			test.searchResultsPage.verifyThatSearchTermIsHighlighted(getYamlValue("\"spectroscopy\""), "Anywhere");

	 }

	@Test
	public void Step12_SF_11_Verify_That_RSS_Feed_For_Search_Works_Fine() {
		Step01_Verify_Navigation_To_UserProfile_Page();
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
		test.searchResultsPage.clickOnRssFeedBesideSearchResultsTitle();
		test.searchResultsPage.verifyNavigationToRSSFeedsPage();
	}

	@Test
	public void Step13_SF_12_Verify_That_Application_Displays_An_Error_Message_Pop_Up_Upon_Leaving_Search_Field_As_Blank() {
		Step01_Verify_Navigation_To_UserProfile_Page();
		test.homePage.clickOnSearchIcon();
		test.searchResultsPage.verifyErrorPopUpOnAdvancedSearchPage(getYamlValue("advancedSearchPage.errorOnBlankSubmit"));
	}
}
