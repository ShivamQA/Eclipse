package com.qait.acs.tests;

import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import com.qait.automation.TestSessionInitiator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.Test;

@Listeners({ com.qait.acs.tests.CustomReportCreator.class })
public class ProfileFunctionalityTest extends BaseTest {
	String searchTerm = "cell";

	@Test
	public void Step01_Verify_Navigation_To_UserProfile_Page() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		
//		test.loginPage.EnterCredentials("acstest_10221022", "password12345");
		test.loginPage.EnterCredentials("acstest_prod22", "password");
		// test.homePage.verifyUserLoggedIn("Sunena");
		// test.homePage.userclickedOnLoggedIn();

		// test.launchApp(getYamlValue("baseUrl"));
		test.homePage.verifyUserLoggedIn("Sunena");
		test.homePage.userclickedOnLoggedIn();
		test.userProfileHomePage.verifyUserIsOnUserProfileHomePage();
	}

	 @Test
	public void Step02_PF_01_Verify_The_Content_On_My_Profile_Homepage() {
		test.userProfileHomePage.verifyWelcomeUserTxtAndItsDescription("Sunena");
		test.userProfileHomePage.verifyExistenceOfLinksInNavigationPanel();
		test.userProfileHomePage.verifyDisplayOfTextAssociatedWithLinks();
		test.userProfileHomePage.verifyFunctionalityOfLinks();
	}

	 @Test
	public void Step03_PF_02_Verify_Functionality_Of_Links_On_MembershipBenefits_Page() {
		test.userProfileHomePage.clickAndverifyUserIsOnMemberBenefitsPage("Member Benefits");
		test.userProfileHomePage.verifyFunctionalityOfLinksOnMembershipBenefitsPage();
	}

	 @Test
	public void Step04_PF_03_Verify_Various_Options_Of_Email_Alerts_Section() {
		test.userProfileHomePage.clickAndverifyUserIsOnEMailAlertsPage("E-Mail Alerts");
		test.eMailAlertsHomePage.clickAndVerifytabsOnEmailAlert("New content alerts");
		test.eMailAlertsHomePage.verifyVariousOptionsAvailableOnNewContentAlertsEmailAlertsSection();
		test.eMailAlertsHomePage.clickAndVerifytabsOnEmailAlert("Citations alerts");
		test.eMailAlertsHomePage.verifyVariousOptionsAvailableOnCitationsAlertsEmailAlertsSection();
		// test.eMailAlertsHomePage.verifyFunctionalityOfSavePreferencesButton(getYamlValue("emailAlertsPage.alertsPreferencesUpdatedMsg"));
	}

	 @Test
	public void Step05_PF_04_Verify_That_A_Pop_Up_Dialog_Displaying_The_Names_Of_All_Available_Journals_Is_Displayed_When_User_Clicks_On_Add_A_Journal_Button_Under_Journal_Alerts_section() {
		test.userProfileHomePage.clickAndverifyUserIsOnEMailAlertsPage("E-Mail Alerts");
		test.eMailAlertsHomePage.clickAndVerifytabsOnEmailAlert("New content alerts");
		test.eMailAlertsHomePage.clickOnAddJournalButton();
		test.eMailAlertsHomePage.verifyAllJournalsCount();
		test.eMailAlertsHomePage.closeAllJournalsPopUP();
	}

	 @Test
	public void Step06_PF_05_Verify_That_Journal_Selection_Functionality() {
		test.userProfileHomePage.clickAndverifyUserIsOnEMailAlertsPage("E-Mail Alerts");
		test.eMailAlertsHomePage.clickOnAddJournalButton();
		test.eMailAlertsHomePage.selectJournal(1);
		test.eMailAlertsHomePage.verifyAllJournalsPOPUPIsOpenOrClosed();
		test.eMailAlertsHomePage.clickOnAddJournalButton();
		test.eMailAlertsHomePage.verifySelectedJournalColorinPOPUP(1);
		test.eMailAlertsHomePage.closeAllJournalsPopUP();
		test.eMailAlertsHomePage.clickOnAddJournalButton();
		test.eMailAlertsHomePage.selectJournal(1);
		test.eMailAlertsHomePage.clickOnAddJournalButton();
		test.eMailAlertsHomePage.verifyNonSelectedJournalColorinPOPUP(1);
		test.eMailAlertsHomePage.closeAllJournalsPopUP();
	}

	 @Test
	public void Step07_PF_06_Verify_That_Issue_Alert_Check_Box_Is_Not_Checked_When_User_Selected_ACS_Omega_Journal_From_Pop_Up_Dialog() {
		test.userProfileHomePage.clickAndverifyUserIsOnEMailAlertsPage("E-Mail Alerts");
		test.eMailAlertsHomePage.clickOnAddJournalButton();
		String Journal = test.eMailAlertsHomePage.selectOmegaJournal();
		test.eMailAlertsHomePage.verifyAllJournalsPOPUPIsOpenOrClosed();
		test.eMailAlertsHomePage.verifyCheckedBoxIssueAlertCheckedOrUnchecked(Journal);
	}

	 @Test
	public void Step08_PF_07_Verify_That_The_Pop_Up_Window_Is_Closed_And_The_Selected_Journal_Information_Is_Displayed_As_Soon_As_User_Selects_A_Journal() {
		test.userProfileHomePage.clickAndverifyUserIsOnEMailAlertsPage("E-Mail Alerts");
		test.eMailAlertsHomePage.clickOnAddJournalButton();
		String journal = test.eMailAlertsHomePage.selectJournal(1);
		test.eMailAlertsHomePage.verifyAllJournalsPOPUPIsOpenOrClosed();
		test.eMailAlertsHomePage.verifySelectedJournalsInformationInTable(journal);
	}

	 @Test
	public void Step09_PF_08_Verify_That_Journal_Alerts_Are_Activated_When_User_Selects_Issue_Alert_And_Article_Alert_Frequency_Options_And_Clicks_On_Save_Preferences_Button() {
		test.userProfileHomePage.clickAndverifyUserIsOnEMailAlertsPage("E-Mail Alerts");
		test.eMailAlertsHomePage.clickOnAddJournalButton();
		String journal = test.eMailAlertsHomePage.selectJournal(1);
		test.eMailAlertsHomePage.verifyAllJournalsPOPUPIsOpenOrClosed();
		test.eMailAlertsHomePage.verifySelectedJournalsInformationInTable(journal);
		test.eMailAlertsHomePage.selectArticleAlertOption(journal, "Daily");
		test.eMailAlertsHomePage.clickOnSavePreferenceButton();
		test.eMailAlertsHomePage.verifyEmailAlertUpdatedMsg();
		test.eMailAlertsHomePage.selectOrDeselectCheckedBoxIssueAlert("unchecked", journal);
		test.eMailAlertsHomePage.selectArticleAlertOption(journal, "Never");
		test.eMailAlertsHomePage.clickOnSavePreferenceButton();
		test.eMailAlertsHomePage.verifyNoJournalSelected();
	}

	 @Test
	public void Step10_PF_09_Verify_Saved_Searches_Result_Messages() {
		test.userProfileHomePage.clickAndverifyUserIsOnSavedSearchesPage("Saved Searches");
		test.savedSearchesHomePage.verifyNoSavedMsg();
	}

	@Test
	public void Step11_PF_10_Verify_That_Search_Page_That_Saved_By_User_Loads_Upon_Selecting_Run_User_Action() {
		test.homePage.verifySearchBoxIsDisplayed();
		test.homePage.enterSearchTermAndClickOnSearchIcon(searchTerm);
		test.searchResultsPage.clickOnSaveSearchButtonForThisSearchBox();
		String optionsForAlertMeDropDown[] = { "Never", "Daily", "Weekly", "Monthly" };
		test.searchResultsPage.verifySaveButtonUnderSaveThisSearchBoxIsClicked("Alert me to new results:",
				optionsForAlertMeDropDown);
		test.searchResultsPage.saveASearch(searchTerm, "Daily");
		test.homePage.verifyUserLoggedIn("Sunena");
		test.homePage.userclickedOnLoggedIn();
		test.userProfileHomePage.verifyUserIsOnUserProfileHomePage();
		test.userProfileHomePage.clickAndverifyUserIsOnSavedSearchesPage("Saved Searches");
		test.savedSearchesHomePage.clickOnRunSearchButton(searchTerm);
		test.searchResultsPage.verifySearchPageIsLoadedForSavedSearch(searchTerm);
	}

	@Test
	public void Step12_PF_11_Verify_That_User_Can_Delete_A_Saved_Search_Successfully() {
		test.homePage.verifyUserLoggedIn("Sunena");
		test.homePage.userclickedOnLoggedIn();
		test.userProfileHomePage.verifyUserIsOnUserProfileHomePage();
		test.userProfileHomePage.clickAndverifyUserIsOnSavedSearchesPage("Saved Searches");
		test.savedSearchesHomePage.clickOnDeleteSearchButton(searchTerm);
		test.savedSearchesHomePage.verifySavedSearchTermIsDeleted(searchTerm);
	}

	 @Test
	public void Step13_PF_12_Verify_That_Application_Displays_Favorite_Content_Section_Upon_Clicking_Favorite_Content_link() {
		test.userProfileHomePage.clickAndverifyUserIsOnFavoriteContentPage("Favorite Content");
		test.favouriteContentHomePage.clickAndVerifytabsOnFavouriteContentPage("Article Shortlist");
		test.favouriteContentHomePage.verifyNoFavouriteContentAddedArticleTabMsg();
		test.favouriteContentHomePage.clickAndVerifytabsOnFavouriteContentPage("Publication Shortlist");
		test.favouriteContentHomePage.verifyNoFavouriteContentAddedPublishTabMsg();
	}

	 @Test
	public void Step14_PF_13_Verify_That_Application_Displays_Activate_A_Token_Page_And_Its_Content_Upon_Clicking_Respective_Link() {
		test.userProfileHomePage.clickAndverifyUserIsOnActivateTokenPage("Activate a Token");
		test.userProfileHomePage.verifyFunctionalityAvailableOnActivateATokenPage();
	}
}