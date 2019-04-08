package com.qait.acs.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qait.automation.utils.DateUtil;

@Listeners({ com.qait.acs.tests.CustomReportCreator.class })
public class AdvancedOptionSearchTest extends BaseTest {
	String searchTerm = "cell";

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
	public void Step03_ASF_01_Verify_The_Application_Displays_Following_Element_On_Advanced_Option_Tab() {
		test.searchResultsPage.verifyRefineSearchButtonAndArrow();
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.verifyAvailableOptionsInDropdown();
		test.searchResultsPage.verifyPublishedInTextBox();
		test.searchResultsPage.verifyComponentsUnderAccessTypeSection();
		test.searchResultsPage.verifyComponentsUnderPublicationDateSection();
		test.searchResultsPage.verifyComponentsUnderCenArchivesSection();
		test.searchResultsPage.verifySearchButtonUnderAdvancedOptionTab();
	}

	// @Test
	// public void Step04_ASF_02_Verifies_Addition_And_Deletion_Of_SearchFields()
	// {
	// test.searchResultsPage.verifyAdditionOfSearchField(getYamlValue("homePage.advancedSearchHomePage.tooltipTextToAddSearchTerm"));
	// test.searchResultsPage.verifyDeletionOfSearchField(getYamlValue("homePage.advancedSearchHomePage.tooltipTextToRemoveSearchTerm"));
	//
	// }

	// update the clear data

//	@Test
	public void Step05_ASF_03_Verifies_Search_Button_is_Disabled() {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.clearTheSearchFieldDropdown(0);
		test.searchResultsPage.verifySearchButtonDisabled();
	}

//	@Test
	public void Step06_ASF_04_Verifies_Access_Types_Exists_Under_The_Access_Type_Section_And_Functionality_Works_As_Expected() {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterTitleOfArticleForSearchCriteria(searchTerm);
		test.searchResultsPage.verifyComponentsAndTheirFunctionalityUnderAccessTypeSection();
	}

//	@Test
	public void Step07_ASF_05_Verifies_Search_Criteria_Displays_3000_Articles_On_Advanced_Search() {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.selectCriteriaFromAdvanceSearchDropdown("1", "Title");
		test.searchResultsPage.enterTitleOfArticleForSearchCriteria("\"Table of Contents\"");
		test.searchResultsPage.clickOnAllContentUnderAccessTypeSection();
		test.searchResultsPage.selectCENArchivesOptions("Include Tables of Contents in search results");
		test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifySearchCriteriaDisplays3000PlusArticlesOnSearch();
	}

//	@Test
	public void Step08_ASF_06_Verifies_Advertisements_Titled_Accordingly_Appears_In_Advanced_Search() {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.selectCriteriaFromAdvanceSearchDropdown("1", "Title");
		test.searchResultsPage.enterTitleOfArticleForSearchCriteria("\"Dream Green\"");
		test.searchResultsPage.selectCENArchivesOptions("Include full-page advertisements in search results");
		test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyAdvertisementsTitledAccordinglyAppearsInAdvancedSearch();
	}

	// Failing due to not range for dates

	// @Test
	public void Step09_ASF_07_Verifies_Behavior_Of_Advanced_Search_Based_On_Web_Publication_Date() {
		String author = "Smith";
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.deselectCENArchivesOptions();
		test.searchResultsPage.selectCriteriaFromAdvanceSearchDropdown("1", "Author");
		test.searchResultsPage.enterTitleOfArticleForSearchCriteria(author);

		// Case1: All Dates
		test.searchResultsPage.selectAllDatesOptionUnderPublicationDate();
		test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyResultsAreDisplayedForGivenAuthor(author);

		// Case2: Last Date Range
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.selectStaticRangeOptionUnderPublicationDate("6 months");
		test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyResultsGetDisplayedAsPerPublicationDate("Last 6 Months");

		// Case3: Custom Date Range
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.selectCustomRangeOptionForPubDate();

		String month = new DateUtil().getPreviousMonth().substring(0, 3);
		test.searchResultsPage.selectStartMonthAndYear(month, new DateUtil().getPreviousYear());
		test.searchResultsPage.selectEndMonthAndYear(month, new DateUtil().getCurrentYear());
		test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyResultsGetDisplayedAsPerPublicationDate(new DateUtil().getPreviousYear(),
				new DateUtil().getCurrentYear());
	}

//	@Test
	public void Step10_ASF_08_Verify_That_Clicking_On_Refine_Search_Link_Expands_Box_And_Displays_Its_Tabs()
			throws ParseException {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Search History");
		test.searchResultsPage.verifySearchHistoryTabColumns();
		test.searchResultsPage.verifyRowValueSearchHistory(searchTerm);
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Saved Searches");
	}

	@Test
	public void Step11_ASF_09_Verifies_Behaviour_Of_Search_Results_Are_Displaying_Appropriate_Title_Results() {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.selectCriteriaFromAdvanceSearchDropdown("1", "Title");
		test.searchResultsPage.enterTitleOfArticleForSearchCriteria("\"imaging\"");
		test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyBehaviourOfSearchResultsContainsAppropriateTitleResults("imaging");
	}

	// failing due to dates

//	@Test
	public void Step12_ASF_10_Verifies_Filters_Work_As_Expected_Under_Search_Results_Section() {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.selectCriteriaFromAdvanceSearchDropdown("1", "Figure/Table Caption");
		test.searchResultsPage.enterTitleOfArticleForSearchCriteria("cell");
		test.searchResultsPage.selectCustomRangeOptionForPubDate();
		String month = new DateUtil().getPreviousMonth().substring(0, 3);
		test.searchResultsPage.selectStartMonthAndYear(month, "2014");
		test.searchResultsPage.selectEndMonthAndYear(month, "2012");
		test.searchResultsPage.clickOnSearchButtonUnderAdvancedTab();
		test.searchResultsPage.verifyResultsGetDisplayedAsPerPublicationDate("2014", "2012");
		test.searchResultsPage.clickOnRemoveFilterButton();
		test.searchResultsPage.verifySingleTheNarrowResultSelectedOption(searchTerm);
	}

	 @Test
	public void Step13_ASF_11_Verify_Saved_Searches_Result_Page() {
//		 Step01_Verify_Navigation_To_UserProfile_Page();
//			Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
		test.searchResultsPage.clickOnSaveSearchButtonForThisSearchBox();
		test.searchResultsPage.clickOnSaveSearchPopUpLoginButton();
//		test.searchResultsPage.VerifyAndClickOnLoginLink();
//		test.loginPage.EnterCredentials("acstest_sunena", "password");
		test.loginPage.EnterCredentials(getYamlValue("users.user2.username"), getYamlValue("users.user2.password"));
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
		test.searchResultsPage.clickOnSaveSearchButtonForThisSearchBox();
		String optionsForAlertMeDropDown[] = { "Never", "Daily", "Weekly", "Monthly" };
		test.searchResultsPage.verifySaveButtonUnderSaveThisSearchBoxIsClicked("Alert me to new results:",
				optionsForAlertMeDropDown);
		test.searchResultsPage.saveASearch(searchTerm, "Daily");
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Saved Searches");
		test.searchResultsPage.verifyRowValueSavedSearches(searchTerm, "Daily");
	}

	 @Test
	public void Step14_ASF_12_Verify_That_Search_Page_Saved_By_User_Loads_Upon_Selecting_Run_Search() {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Saved Searches");
		test.searchResultsPage.verifyRowValueSavedSearches(searchTerm, "Daily");
		test.searchResultsPage.clickOnUserActionSavedSearchButton(searchTerm, "Run");
		test.searchResultsPage.verifySearchPageIsLoadedForSavedSearch(searchTerm);
	}

	@Test
	public void Step15_ASF_13_Verify_That_User_Can_Delete_A_Saved_Search_Successfully() {
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Saved Searches");
		test.searchResultsPage.clickOnUserActionSavedSearchButton(searchTerm, "Delete");
		test.savedSearchesHomePage.verifySavedSearchTermIsDeleted(searchTerm);
	}

	@Test
	public void Step16_ASF_14_Verify_Searched_Results_Are_Displayed_According_For_Entered_Text_In_Search_Criteria() {
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.selectCriteriaFromAdvanceSearchDropdown("1", "Anywhere");
		test.searchResultsPage.enterTitleOfArticleForSearchCriteria("\"spectroscopy\"");
		test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyBehaviourOfSearchResultsContainsAppropriateTitleResults("spectroscopy");
	}

	// Step 17 is failing Sort By Date is displaying error page

	@Test
	public void Step17_ASF_15_Verifies_SortBy_Previous_Next_Functionality() {
		Step01_Verify_Navigation_To_UserProfile_Page();
		Step02_SF_01_Verify_The_Existence_And_Functionality_For_Search_Bar();
		test.searchResultsPage.verifyTwoSortByOptionAvailable();
		test.searchResultsPage.selectRespectiveValueFromSortBy("Date");
		test.searchResultsPage.verifyResultsSortedBasedOnPubDates();
		int page = 50;
		test.searchResultsPage.verifyPerPageOptionAvailability();
		test.searchResultsPage.clickOnPerPageOption(page);
		test.searchResultsPage.verifyTheSelectedPerPage(page);
		test.searchResultsPage.verifySearchResultCount(page);
		test.searchResultsPage.clickAndVerifyPreviousNextPageNavigaionButton(page, "Next");
		test.searchResultsPage.clickAndVerifyPreviousNextPageNavigaionButton(page, "Previous");
		test.searchResultsPage.clickAndVerifyOnAnyPageNavigation("4");
	}
}
