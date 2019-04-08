package com.qait.acs.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;

public class NewRegistrationTest {
	TestSessionInitiator test;
	String mandatoryUserInfo[] = { "Email", "First Name", "Last Name", "Username", "Password", "Confirm Password" };
	String passwords[] = { "", "12345", "pass123", "pass1234", "pass1234567", "pass@12word34q" };
	String passwordStrengthFields[] = { "Too Short", "Very Weak", "Weak", "Good", "Strong", "Very Strong" };
	String email = "T" + System.currentTimeMillis() + "@fake.com";
	String userName;
protected int counterForTests;
	
	@BeforeClass
	public void Start_Test_Session() {
		counterForTests = 0;
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		c.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("HHssms");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		userName = "acstest_" + sdf.format(c.getTime());
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}

	@Test
	public void Step01_Verify_Functionality_Of_User_Login() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
	}

	@Test
	public void Step02_Verify_the_information_available_on_the_Login_page() {
		test.loginPage.verifyACSlogo();
		test.loginPage.verifyAmericanChemicalSocietylink();
		test.loginPage.verifyCreateFreeAccountLink();
		test.loginPage.verifyJoinACSTodayLink();
		test.loginPage.verifyRegisteringIsEasyButton();
	}

	@Test
	public void Step03_Verify_User_Navigated_to_Create_Your_ACS_ID_page() {
		test.loginPage.clickOnCreateFreeAccountLink();
		test.loginPage.verifyNavigateToAccountRegistrationPage();
	}

	@Test
	public void Step04_RL_11_Verify_Cancel_Button_Functionality_On_Create_New_ACS_ID_Page() {
		test.registrationPage.verifyCancelButton();
		test.registrationPage.clickOnCancelButton();
		test.homePage.verifyUserIsOnACSPubHomePage();
	}

	@Test
	public void Step05_RL_12_Verify_Password_Strength_Functionality_On_Create_New_ACS_ID_Page() {
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.clickOnCreateFreeAccountLink();
		test.loginPage.verifyNavigateToAccountRegistrationPage();
		test.registrationPage.verifyPasswordStrength(passwords, passwordStrengthFields);
	}

	@Test
	public void Step06_RL_13_Verify_Acs_Id_Product_And_Service_Text_Message_On_Create_New_ACS_ID_Page() {
		test.registrationPage.verifyTextACSProductAndServices();
	}

	@Test
	public void Step07_RL_14_Verify_Checkbox_Is_Selected_And_Appropriate_Message_For_It_And_Links_Are_Navigated_To_Correct_URLs_On_Create_New_ACS_ID_Page() {
		test.registrationPage.verifyCheckBoxMessage();
	}

	@Test
	public void Step08_RL_15_Verify_The_Footers_Logo_On_Create_New_ACS_ID_Page() {
		test.registrationPage.verifyFooterLogoIsPresent();
	}

	@Test
	public void Step09_RL_16_Verify_The_Footers_Links_And_There_Navigation_On_Create_New_ACS_ID_Page() {
		test.registrationPage.verifyfooters();
		test.registrationPage.clickOnFootersAndVerifyNavigation();
	}

	@Test
	public void Step10_RL_17_Verify_That_User_Is_Successfully_Able_To_Register_By_Entering_Valid_Information_In_Mandatory_Fields() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.clickOnCreateFreeAccountLink();
		test.registrationPage.verifyAllMandatoryTextFields(mandatoryUserInfo);
		String password = "password12345";
		String[] formDetails = { email, "test", "test", "", userName, password, password };
		test.registrationPage.enterUserAccountdetails(formDetails);
		test.registrationPage.verifyUseremailcreated(email);
		test.registrationPage.verifyEmailconfirmationtxt(email);
		test.registrationPage.verifyConnectORCIDText();
		test.registrationPage.verifyButtonLinkAccount();
		test.registrationPage.clickOnRegisterLinkAndVerifyNavigation();

	}

	@Test
	public void Step11_RL_18_Verify_Login_With_Newly_Created_Account_Into_The_Application() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.verifyUserIsOnACSPubHomePage();
		test.homePage.clickOnLogoutLink();
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		String password = "password12345";
		test.loginPage.EnterCredentials("test", password);
		test.homePage.verifyUserLoggedIn("test");
	}

	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result, Method method) {
		counterForTests = test.takescreenshot.incrementcounterForTests(counterForTests, result);
		test.takescreenshot.takeScreenShotOnException(result, counterForTests, method.getName());
	}

	@AfterClass
	public void close_Test_Session() throws IOException {
		test.closeBrowserSession();
	}
}
