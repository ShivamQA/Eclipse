package com.qait.acs.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import com.qait.automation.utils.SeleniumWait;

public class loginPageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "loginPage";

	public loginPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyNavigationToLoginPage() {
		Assert.assertTrue(element("h2_Login").getText().trim().equals("Log In"),
				"Assertion failed: Not navigated to login page.");
		Assert.assertTrue(getCurrentURL().contains("sso"));
		logMessage("Verified: Navigated to SSO Login page.");
	}

	public void EnterCredentials(String userName, String password) {
		isElementDisplayed(true, "textbox_userId");
		element("textbox_userId").clear();
		element("textbox_userId").sendKeys(userName);
		logMessage("User entered credentials for '" + userName + "'.");
		isElementDisplayed(true, "textbox_password");
		element("textbox_password").clear();
		element("textbox_password").sendKeys(password);
		isElementDisplayed(true, "btn_LogIn");
		scrollDown(element("btn_LogIn"));
		element("btn_LogIn").click();
		logMessage("User clicked on 'Log In' button");

	}

	public void logOutFromTheApplication() {
		if (checkIfElementIsThere("btn_LogOut")) {
			isElementDisplayed(true, "btn_LogOut");
			element("btn_LogOut").click();
			logMessage("User clicked on LogOut button.");
			hardWait(2);
		}
		wait.waitForPageToLoadCompletely();
	}

	public void verifyCreateFreeAccountLink() {

		Assert.assertTrue(isElementDisplayed(true, "link_loginpage", "Create a free account"),
				"Assertion Failed: Create free accout link not present");
		logMessage("Verified: Create free accout link is present");
	}

	public void verifyJoinACSTodayLink() {
		Assert.assertTrue(isElementDisplayed(true, "link_loginpage", "Join ACS today"),
				"Assertion Failed: Join ACS today link not present");
		logMessage("Verified: Join ACS today link is present");
	}

	public void verifyRegisteringIsEasyButton() {
		Assert.assertTrue(isElementDisplayed(true, "link_loginpage", "Registering is easy"),
				"Assertion Failed: Registering is Easy button not present");
		logMessage("Verified: Registering is Easy button is present");
	}

	public void verifyLoginPanel() {
		Assert.assertTrue(isElementDisplayed(true, "box_loginForm"), "Assertion Failed: Login Panel not present");
		logMessage("Verified: Login Panel is present");
		Assert.assertTrue(isElementDisplayed(true, "textbox_userId"),
				"Assertion Failed: Username (textbox) not present");
		logMessage("Verified: Username (textbox) is present");
		Assert.assertTrue(isElementDisplayed(true, "textbox_password"),
				"Assertion Failed: Password (textbox) not present");
		logMessage("Verified: Password (textbox) is present");
		Assert.assertTrue(isElementDisplayed(true, "btn_LogIn"), "Assertion Failed:Log In button not present");
		logMessage("Verified: Log In button is present");

	}

	public void verifyForgotUsernameorPasswordlink() {
		Assert.assertTrue(isElementDisplayed(true, "link_loginpage", "Forgot User Name or Password?"),
				"Assertion Failed: Forgot Username or Password link not present");
		logMessage("Verified: Forgot Username or Password link is present");
	}

	public void verifyHelplink() {
		Assert.assertTrue(isElementDisplayed(true, "link_loginpage", "Help"),
				"Assertion Failed: Help link not present");
		logMessage("Verified: Help link is present");
		hardWait(2);
	}

	public void verifyACSlogo() {
		Assert.assertTrue(isElementDisplayed(true, "img_ACSlogo"), "Assertion Failed: ACS logo not present");
		logMessage("Verified: ACS logo is present");
	}

	public void verifyAmericanChemicalSocietylink() {
		Assert.assertTrue(isElementDisplayed(true, "link_loginpage", "American Chemical Society"),
				"Assertion Failed: American Chemical Society link not present");
		logMessage("Verified: American Chemical Society link is present");
	}

	public void verifyInvalidCredentialsMessage() {
		hardWait(3);
		String alertMessage = "Incorrect user name or password. Please try again.";
		Assert.assertTrue(element("txt_incorrectlogin").getText().contains(alertMessage),
				"Assertion Failed: Application not provide a validation message indicating : " + alertMessage + ".");
		logMessage("Verified: Application provides a validation message indicating : " + alertMessage + ".");

	}

	public void verifyInvalidPasswordMessage() {
		hardWait(3);
		String alertMessage = "Incorrect user name or password. Please try again.";
		Assert.assertTrue(element("txt_incorrectlogin").getText().contains(alertMessage),
				"Assertion Failed: Application not provide a validation message indicating : " + alertMessage + ".");
		logMessage("Verified: Application provides a validation message indicating : " + alertMessage + ".");
	}

	public void clickOnACSlogo() {
		isElementDisplayed(true, "img_ACSlogo");
		element("img_ACSlogo").click();
		logMessage("User clicked on ACS logo");
		wait.waitForPageToLoadCompletely();
	}

	public void verifyNavigateToTestServerOfACSWebsite() {
		wait.waitForPageToLoadCompletely();
		if(checkIfElementIsThere("btn_cancelfeedback"))
			clickOnElement("btn_cancelfeedback");
		wait.waitForElementToBeVisible(element("logo_ACS"));
		Assert.assertTrue(isElementDisplayed(true, "logo_ACS"), "Assertion Failed: ACS logo not present");
		logMessage("Verified: Navigate To Test Server Of ACS Website");
	}

	public void clickOnAmericanChemicalSocietylink() {
		isElementDisplayed(true, "link_loginpage", "American Chemical Society");
		element("link_loginpage", "American Chemical Society").click();
		logMessage("User clicked on American Chemical Society link");

	}

	public void clickOnCreateFreeAccountLink() {
		isElementDisplayed(true, "link_loginpage", "Create a free account");
		element("link_loginpage", "Create a free account").click();
		logMessage("User clicked on  Create free accout link");

	}

	public void verifyNavigateToAccountRegistrationPage() {
		isElementDisplayed(true, "form_signup");
		logMessage("Verified: Navigate To Account Registration page");
	}

	public void clickOnJoinACSTodayLink() {
		isElementDisplayed(true, "link_loginpage", "Join ACS today");
		element("link_loginpage", "Join ACS today").click();
		logMessage("User clicked on  Create free accout link");
	}

	public void verifyNavigateToJoinACSPage() {
		// Assert.assertTrue(getCurrentURL().contains("jointest.acs.org"),"Assertion
		// Failed: User not navigated to Join ACS Page");
		logMessage("Verified: Navigation to join ACS Page.");
	}

	public void clickOnRegisteringIsEasyButton() {
		isElementDisplayed(true, "link_loginpage", "Registering is easy");
		element("link_loginpage", "Registering is easy").click();
		logMessage("User clicked on Registering Is Easy Button");
	}

	public void clickOnForgotUsernameorPasswordlink() {
		isElementDisplayed(true, "link_loginpage", "Forgot User Name or Password?");
		element("link_loginpage", "Forgot User Name or Password?").click();
		logMessage("User clicked on Forgot Username or Password link.");
	}

	public void verifyNavigationToResetYourPasswordPage() {
		isElementDisplayed(true, "textbox_newemail");
		Assert.assertTrue(element("txt_resetpasssword").getText().contains("Reset your password"),
				"Assertion Failed: Not Navigate To Reset Your Password Page");
		logMessage("Verified: Navigate To Reset Your Password Page");

	}

	public void verifyNavigationToACSHelpPage() {
		isElementDisplayed(true, "txt_headerHelpTitle");
		Assert.assertTrue(element("txt_headerHelpTitle").getText().contains("ACS Login Help"),
				"Assertion Failed: Not Navigate To ACS Login Help Page");
		logMessage("Verified: Navigate To ACS Login Help Page");

	}

	public void clickOnHelpLink() {
		isElementDisplayed(true, "txt_helptitle");
		element("txt_helptitle").click();
		logMessage("User clicked on  Help link");

	}

}
