package com.qait.acs.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;

public class loginPageTest extends BaseTest {

	@Test
	public void Step01_RL_01_Verify_User_Navigated_TO_Login_Page_Upon_Launching_The_Application() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
	}

	 @Test
	public void Step02_RL_02_Verify_the_information_available_on_the_Login_page() {
		test.loginPage.verifyACSlogo();
		test.loginPage.verifyAmericanChemicalSocietylink();
		test.loginPage.verifyCreateFreeAccountLink();
		test.loginPage.verifyJoinACSTodayLink();
		test.loginPage.verifyRegisteringIsEasyButton();
		test.loginPage.verifyLoginPanel();
		test.loginPage.verifyForgotUsernameorPasswordlink();
		test.loginPage.verifyHelplink();
	}

	@Test
	public void Step03_RL_03_Verify_the_functionality_of_clicking_on_the_following_links_from_left_side_of_Login_page() {
		test.loginPage.clickOnACSlogo();
		test.loginPage.verifyNavigateToTestServerOfACSWebsite();
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
		test.loginPage.clickOnAmericanChemicalSocietylink();
		test.loginPage.verifyNavigateToTestServerOfACSWebsite();
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
		test.loginPage.clickOnCreateFreeAccountLink();
		test.loginPage.verifyNavigateToAccountRegistrationPage();
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
		test.loginPage.clickOnJoinACSTodayLink();
		test.loginPage.verifyNavigateToJoinACSPage();
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
		test.loginPage.clickOnRegisteringIsEasyButton();
		test.loginPage.verifyNavigateToAccountRegistrationPage();
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
	}

	@Test
	public void Step04_RL_04_Verify_the_functionality_of_valid_username_and_invalid_password() {
		test.loginPage.EnterCredentials(getYamlValue("users.user1.username"),
				getYamlValue("users.invaliduser.password"));
		test.loginPage.verifyInvalidPasswordMessage();
	}

	@Test
	public void Step05_RL_05_Verify_the_functionality_of_invalid_username_and_valid_password() {
		test.loginPage.EnterCredentials(getYamlValue("users.invaliduser.username"),
				getYamlValue("users.user1.password"));
		test.loginPage.verifyInvalidPasswordMessage();
	}

	@Test
	public void Step06_RL_06_Verify_the_functionality_of_invalid_username_and_invalid_password() {
		test.loginPage.EnterCredentials(getYamlValue("users.invaliduser.username"),
				getYamlValue("users.invaliduser.password"));
		test.loginPage.verifyInvalidCredentialsMessage();
	}

	@Test
	public void Step07_RL_07_Verify_the_functionality_of_valid_username_and_valid_password() {
		test.loginPage.EnterCredentials(getYamlValue("users.user1.username"), getYamlValue("users.user1.password"));
		test.homePage.verifyUserIsOnACSPubHomePage();
		test.homePage.clickOnLogoutLink();
	}

	@Test
	public void Step08_LR_08_Verify_the_functionality_of_Forgot_Username_or_Password_link() {
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
		test.loginPage.clickOnForgotUsernameorPasswordlink();
		test.loginPage.verifyNavigationToResetYourPasswordPage();
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
	}

	@Test
	public void Step09_LR_09_Verify_the_functionality_of_Help_link() {
		test.loginPage.clickOnHelpLink();
		test.loginPage.verifyNavigationToACSHelpPage();
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
	}

	@Test
	public void Step10_LR_10_Verify_The_Functionality_Of_User_Is_Logged_Out_The_System_Successfully() {
		Step07_RL_07_Verify_the_functionality_of_valid_username_and_valid_password();
		test.homePage.verifyUserIsOnACSPubHomePage();
	}

}