package com.qait.acs.tests;

import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.getYamlValues;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.ConfigPropertyReader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class MendeleyFunctionality_Test extends BaseTest {
	String ArticlePageURL="";
	String ArticleTitle="";
	
	public void navigateToArticlePage(){
		String journal= getYamlValue("Publications.Journals.Link1");
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Journals");
		test.homePage.selectJournal(journal);
//		String CollectionName=test.journalPage.selectRandomCollection();
		ArticleTitle = test.journalPage.VerifyNavigationOfArticleFromFilmstripSection("Current Issue");
		ArticlePageURL = test.articlePage.verifyNavigationToArticleLandingPage(ArticleTitle);
	}

	@Test
	public void Step01_MF_01_Verify_Existance_Of_Mendeley_Button_On_Article_And_DownloadCitations_Page() {
		navigateToArticlePage();
		test.mendeleyModal.verifyPresenceOfMendeleyIcon();
		test.articlePage.clickOnCiteThisHyperlinkAndVerify();
		test.mendeleyModal.verifyPresenceOfMendeleyIcon();
	}
	
	@Test
	public void Step02_MF_02_Verify_ACS_ID_Yet_Not_Paired_With_Mendeley_Account() {
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.EnterCredentials(getYamlValue("users.user2.username"),getYamlValue("users.user2.password"));
		test.homePage.verifyUserLoggedIn(getYamlValue("users.user2.FirstName"));		
		test.homePage.userclickedOnLoggedIn();
		test.userProfileHomePage.verifyUserIsOnUserProfileHomePage();
		test.userProfileHomePage.verifyMendeleyPaired(false);
	}
	
	//  Scenario #1 Not logged in, No Mendeley pairing
	@Test
	public void Step03_MF_03_Verify_Mendeley_Pairing_Screen_Displayed_When_Not_Logged_In_Mendeley_Not_Paired() {
		test.homePage.clickOnLogoutLink();
		test.openUrl(ArticlePageURL);
		test.mendeleyModal.clickOnMendeleyIcon();
		test.mendeleyModal.verifyMendeleyPairingScreenModalIsDisplayed();
		test.mendeleyModal.verifyPairingScreen("Screen 1");
	}
	
	@Test
	public void Step04_MF_04_Verify_ErrorMsg_Displayed_Upon_Clicking_Login_With_Mendeley_Button_On_Pairing_Screen() {
		test.mendeleyModal.clickOnLoginButton("Mendeley");
		test.mendeleyModal.verifyErrorMessageDisplayed();
	}
	
	@Test
	public void Step05_MF_05_Verify_Click_On_Login_With_ACS_ID_Button_On_Error_Message_Popup() {
		test.mendeleyModal.clickOnLoginWithACSIDButtonOnErrorPopup();
		test.loginPage.verifyNavigationToLoginPage();	
		test.loginPage.EnterCredentials(getYamlValue("users.user2.username"),getYamlValue("users.user2.password"));
		test.mendeleyModal.verifyMendeleyPairingScreenModalIsDisplayed();
		test.mendeleyModal.verifyPairingScreen("Screen 2");
		test.mendeleyModal.closePairingScreen();
	}
	
	@Test
	public void Step06_MF_06_Verify_Click_On_Login_With_ACS_ID_Button_On_Pairing_Screen() {
		test.homePage.clickOnLogoutLink();
		test.openUrl(ArticlePageURL);
		test.mendeleyModal.clickOnMendeleyIcon();
		test.mendeleyModal.verifyMendeleyPairingScreenModalIsDisplayed();
		test.mendeleyModal.clickOnLoginButton("ACS");
		test.loginPage.verifyNavigationToLoginPage();	
		test.loginPage.EnterCredentials(getYamlValue("users.user2.username"),getYamlValue("users.user2.password"));
		test.mendeleyModal.verifyMendeleyPairingScreenModalIsDisplayed();
		test.mendeleyModal.verifyPairingScreen("Screen 2");
		test.mendeleyModal.closePairingScreen();
	}
	
	//  Scenario #2 User logged in, No Mendeley pairing
	@Test
	public void Step07_MF_07_Verify_Mendeley_Pairing_Screen_Displayed_When_Logged_In_Mendeley_Not_Paired() {
		test.openUrl(ArticlePageURL);
		test.mendeleyModal.clickOnMendeleyIcon();
		test.mendeleyModal.verifyMendeleyPairingScreenModalIsDisplayed();
		test.mendeleyModal.verifyPairingScreen("Screen 2");
	}
	
	@Test
	public void Step08_MF_08_Verify_Click_On_Create_A_Mendeley_Account_Link_On_Pairing_Screen() {
		test.mendeleyModal.clickOnCreateMendeleyAccountLinkOnPairingScreen();
		test.mendeleyModal.verifyNavigationToElsevierPage();
		test.mendeleyModal.enterMendeleyEmailInField(getYamlValue("users.mendeley.randomEmail"));
		test.mendeleyModal.verifyElsevierModalDisplayed("Not Mendeley");
		test.navigateBack();
		test.mendeleyModal.enterMendeleyEmailInField(getYamlValue("users.mendeley.email"));
		test.mendeleyModal.verifyElsevierModalDisplayed("Mendeley");
	}
	
	
	@Test
	public void Step09_MF_09_Verify_Click_On_Login_With_Mendeley_Button_On_Pairing_Screen() {
		test.openUrl(ArticlePageURL);
		test.mendeleyModal.clickOnMendeleyIcon();
		test.mendeleyModal.verifyMendeleyPairingScreenModalIsDisplayed();
		test.mendeleyModal.clickOnLoginButton("Mendeley");
		test.mendeleyModal.verifyNavigationToMendeleyAuthorizationPage();
		test.mendeleyModal.clickOnForgotPasswordLinkAndVerifyNavigation();
	}
	
	@Test
	public void Step10_MF_10_Enter_Mendeley_Credentials_And_Verify_Pairing_Screen() {
		test.mendeleyModal.enterMendeleyCredentials(getYamlValue("users.mendeley.email"),getYamlValue("users.mendeley.password"));
		test.mendeleyModal.verifyPairingScreen("Screen 3");
		test.mendeleyModal.closePairingScreen();
	}
	
	// Scenario #3:  Mendeley paired
	@Test
	public void Step11_MF_11_Verify_ACS_And_Mendeley_Pairing_Has_Occured() {
		test.homePage.userclickedOnLoggedIn();
		test.userProfileHomePage.verifyUserIsOnUserProfileHomePage();
		test.userProfileHomePage.verifyMendeleyPaired(true);
		
	}
	
	@Test
	public void Step12_MF_12_Verify_Mendeley_Pairing_Screen_Displayed_Upon_Login_With_Mendeley_Paired_ID() {
		test.homePage.clickOnLogoutLink();
		test.openUrl(ArticlePageURL);
		test.mendeleyModal.clickOnMendeleyIcon();
		test.mendeleyModal.verifyMendeleyPairingScreenModalIsDisplayed();
		test.mendeleyModal.clickOnLoginButton("ACS");
		test.loginPage.verifyNavigationToLoginPage();	
		test.loginPage.EnterCredentials(getYamlValue("users.user2.username"),getYamlValue("users.user2.password"));
		test.mendeleyModal.verifyMendeleyPairingScreenModalIsDisplayed();
		test.mendeleyModal.verifyPairingScreen("Screen 3");
	}

	@Test
	public void Step13_MF_13_Verify_Functionality_Of_Clicking_Continue_On_Pairing_Screen() {
		test.mendeleyModal.clickOnContinueButtonOnPairingScreen();
		test.mendeleyModal.verifyMendeleyIconHasChangedState();
	}

	@Test
	public void Step14_MF_14_Verify_State_Of_Mendeley_Icon_Changes_Upon_Click_When_Already_Login_With_Mendeley_Paired_ID() {
		test.articlePage.clickOnNextLink();
		test.mendeleyModal.verifyPresenceOfMendeleyIcon();
		test.mendeleyModal.clickOnMendeleyIcon();
		test.mendeleyModal.verifyMendeleyIconHasChangedState();
	}

	@Test
	public void Step15_MF_15_Click_On_View_In_Mendeley_Button_And_Verify_Article_Added_In_Mendeley_Library() {
		ArticleTitle= test.articlePage.getArticleTitle();
		test.mendeleyModal.clickOnViewInMendeleyIcon();
		test.mendeleyModal.verifyNavigationToMendeleyLibrary();
		test.mendeleyModal.verifyArticleAddedInTheLibrary(ArticleTitle);
	}
	
	@Test
	public void Step16_MF_16_Verify_Details_For_Articles_Displayed_In_Mendeley_Library_After_Being_Added() {
		test.mendeleyModal.verifyDetailsDisplayedForArticleInMendeleyLibrary("Free Article");
		test.mendeleyModal.clickOnArticleTitleAndVerifyDetailsTabDisplayedOnRight();
		test.mendeleyModal.verifyElementsInDetailsTab(ArticleTitle, "Free Article");
	}
	
	@Test
	public void Step17_MF_17_Verify_Details_For_Non_Free_Article_Displayed_In_Mendeley_Library_After_Being_Added() {
		test.openUrl(getYamlValue("Publications.Journals.URLNonFree"));
		test.articlePage.verifyAccessDenialMsgDisplayed();
		test.articlePage.verifyPDFDisplayed();
		test.mendeleyModal.clickOnMendeleyIcon();
		test.mendeleyModal.verifyMendeleyIconHasChangedState();
		ArticleTitle= test.articlePage.getArticleTitle();
		test.mendeleyModal.clickOnViewInMendeleyIcon();
		test.mendeleyModal.verifyNavigationToMendeleyLibrary();
		test.mendeleyModal.verifyArticleAddedInTheLibrary(ArticleTitle);
		test.mendeleyModal.verifyDetailsDisplayedForArticleInMendeleyLibrary("Non Free Article");
		test.mendeleyModal.clickOnArticleTitleAndVerifyDetailsTabDisplayedOnRight();
		test.mendeleyModal.verifyElementsInDetailsTab(ArticleTitle, "Non Free Article");
	}
	
	@Test
	public void Step18_MF_18_Verify_User_Is_Able_To_Remove_Mendeley_Pairing_From_ACS_Account() {
		test.homePage.userclickedOnLoggedIn();
		test.userProfileHomePage.verifyUserIsOnUserProfileHomePage();
		test.userProfileHomePage.removeMendeleyPairing();
		test.openUrl(ArticlePageURL);
		test.mendeleyModal.verifyMendeleyPairingRemoved();
	}
		

	
	
	
	
	
	
}