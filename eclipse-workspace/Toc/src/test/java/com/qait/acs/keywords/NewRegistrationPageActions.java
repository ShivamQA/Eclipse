package com.qait.acs.keywords;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class NewRegistrationPageActions extends GetPage {

	WebDriver driver;
	String[] footers = { "Terms of Use", "Security", "Privacy", "Accessibility", "Help", "Top ^" };

	public NewRegistrationPageActions(WebDriver driver) {
		super(driver, "registrationPage");
		this.driver = driver;
	}

	public void verifyAllMandatoryTextFields(String[] formManadtoryfields) {
		isElementDisplayed(true,"txt_FieldsMandatory");
		int i = 0;
		String asterisk = "*";
		while (i < formManadtoryfields.length) {
			for (WebElement field : elements("txt_FieldsMandatory")) {
				boolean value = (formManadtoryfields[i] + asterisk).equalsIgnoreCase(field.getText());
				if (value) {
					assertTrue(value, "Assertion Failed: Mandatory field with asterisk is not available : "
							+ formManadtoryfields[i]);
					logMessage("Verified: Mandatory field name with asterisk is available: " + formManadtoryfields[i]);

					break;
				}
			}
			i++;
		}

		isElementDisplayed(true,"btn_createNewUser");
		element("btn_createNewUser").click();
		int j = 0;
		String required = " is Required.";
		while (j < formManadtoryfields.length) {
			for (WebElement field : elements("txt_fieldRequired")) {
				boolean value = (formManadtoryfields[j] + required).equalsIgnoreCase(field.getText());
				if (value) {
					System.out.println(field.getText());
					assertTrue(value, "Assertion Failed: Mandatory field is required message not available : "
							+ formManadtoryfields[j] + required);
					logMessage("Verified: Mandatory field is required message available: " + formManadtoryfields[j]);
					break;
				}
			}
			j++;
		}
	}

	public void enterUserAccountdetails(String[] formDetails) {
		int i = 0;
		for (WebElement ele : elements("txt_newaccount")) {
			ele.clear();
			ele.sendKeys(formDetails[i]);
			logMessage("User entered details for " + ele.getAttribute("name") + " is: " + formDetails[i]);
			i++;
		}

		isElementDisplayed(true,"form_CreatePage");
		element("form_CreatePage").click();
		isElementDisplayed(true,"btn_createNewUser");
		element("btn_createNewUser").click();
		logMessage("Verified: User clicked on create new user button");
	}

	public void verifyUseremailcreated(String email) {
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeVisible(element("txt_emailCreated"));
		Assert.assertTrue(element("txt_emailCreated").getText().contains(email),
				"Assertion Failed: Not containing correct email");
		logMessage("Verified: Containing correct email");
	}

	public void usercreatesORCIDID() {
		isElementDisplayed(true,"txt_ORCIDpage");
		isElementDisplayed(true,"link_Registernow");
		element("link_Registernow").click();

	}

	public void verifyCancelButton() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		isElementDisplayed(true,"btn_cancel");
		logMessage("Verified: Cancel Button is available on create new ACS Id page");
	}

	public void clickOnCancelButton() {
		verifyCancelButton();
		element("btn_cancel").click();
		logMessage("User clicked on Cancel Button available on create new ACS Id page");
	}

	public void verifyPasswordStrength(String[] passwords, String[] passwordStrengthFields) {
		isElementDisplayed(true,"txt_passwordField");
		logMessage("Verified: Password text field is available");
		int i = 0;
		while (i < passwordStrengthFields.length) {
			element("txt_passwordField").clear();
			element("txt_passwordField").sendKeys(passwords[i]);
			logMessage("User entered " + passwords[i] + " inside password field.");
			isElementDisplayed(true,"txt_passwordStrength");
			assertEquals(passwordStrengthFields[i], element("txt_passwordStrength").getText(),
					"Assertion Failed: Expected password strength is not shown");
			logMessage("Assertion Passed: Expected password strength is shown");
			i++;
		}
	}

	public void verifyTextACSProductAndServices() {
		String message = "Access the full breadth of ACS products and services with your ACS IDWebsites, including ACS Publications, C&EN, American Association of Chemistry Teachers (AACT), ACS Network, and ACS.org.Note:Some products/services require membership or paymentManage your email preferences.Create and update your ACS, ACS Network, and ACS Publications profiles.Pay your ACS member renewal online when an invoice is available.";
		isElementDisplayed(true,"txt_acsIdAndProduct");
		System.out.println(element("txt_acsIdAndProduct").getText().replaceAll("[\r\n]", ""));
		assertTrue(element("txt_acsIdAndProduct").getText().replaceAll("[\r\n]", "").contains(message),
				"Assertion Failed: ACS Product And Services info text is not available");
		logMessage("Assertion Passed: ACS Product And Services info text is available");

	}

	public void verifyCheckBoxMessage() {
		String checkboxAlert = "Yes, I want to participate in the ACS Network. I agree to the Privacy Policy and User Agreement for the ACS Network.";
		assertTrue(element("txt_checkBox").getText().trim().contains(checkboxAlert),
				"Assertion Failed: Checkbox message is not available");
		logMessage("Assertion Passed: Checkbox message is available");
		boolean checkbox = executeJavascriptReturnsBoolean(
				"return $('.checkbox [name=\"acsnetworkFlag\"]').is(\":checked\")");
		assertEquals(checkbox, true, "Assertion Failed: Checkbox is not selected");
		logMessage("Verified: User information checkbox is selected");

	}

	public void verifycheckboxLinkNavigation() {
		for (WebElement link : elements("link_checkbox")) {
			String url = getCurrentURL();
			String newURL = link.getAttribute("href");
			driver.get(newURL);
			wait.waitForPageToLoadCompletely();
			logMessage("User click on link: " + link);
			isElementDisplayed(true,"img_ACSProduction");
			logMessage("Verified: User is navigated to ACS production site upon clicking the ACS Publication logo.");
			driver.get(url);
		}
	}

	public void verifyFooterLogoIsPresent() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		isElementDisplayed(true,"img_footerLogos");
		logMessage("Verified: footer logos are present");
	}

	public void verifyfooters() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		int i = 0;
		for (WebElement footer : elements("link_footers")) {
			assertTrue(footer.getText().contains(footers[i]), "");
			i++;
		}
	}

	public void clickOnFootersAndVerifyNavigation() {
		String url = getCurrentURL();
		for (int i = 1; i < 6; i++) {
			WebElement footer = element("link_footerNavigation", Integer.toString(i));
			String footetxtr = footer.getText();
			String newURL = footer.getAttribute("href");

			logMessage("User clicked on footer link: " + footetxtr);
			footer.click();

			wait.waitForPageToLoadCompletely();
			isElementDisplayed(true,"txt_currentCrumb");
			String url2 = getCurrentURL();
			System.out.println("1 url is " + url2);
			System.out.println("2 url is " + newURL);
			assertTrue((url2.contains(newURL)) && element("txt_currentCrumb").getText().contains(footetxtr),
					"Assertion Failed: User not navigated to " + footetxtr);
			logMessage("Verified: User navigated to " + footetxtr);
			driver.navigate().back();
			wait.waitForPageToLoadCompletely();
		}
	}

	public void verifyEmailconfirmationtxt(String email) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed(true,"txt_emailconfirmation");
		System.out.println(element("txt_emailconfirmation").getText());
		String emailTxt = "An email confirmation was sent to: " + email + " Thank you for registering for an ACS ID";
		assertTrue(element("txt_emailconfirmation").getText().contains(email),
				"Assertion Failed: Not containing correct email");
		logMessage("Verified: Containing correct email");

	}

	public void verifyReviewerLabTestButton() {
		isElementDisplayed(true,"btn_reviewerLabTest");
		logMessage("Verified: Reviewer Lab Test Button is available");
	}

	public void clickOnReviewerLabTestButton() {
		verifyReviewerLabTestButton();
		element("btn_reviewerLabTest").click();
	}

	public void verifyORCIDlinkingPageDisplayed() {
		hardWait(3);
		isElementDisplayed(true,"logo_ORCID");
		logMessage("ORCID page displayed.");

	}

	public void EnterExistingORCIDID(String id, String password) {
		element("userid_orcid").clear();
		element("userid_orcid").sendKeys(id);
		hardWait(2);
		element("password_orcid").clear();
		element("password_orcid").sendKeys(password);
		logMessage("User entered existing ORCID id.");
		hardWait(2);
		element("button_SignInOrcid").click();
		logMessage("User clicked on 'Sign Into ORCID' button.");
	}
	
	public void verifyConnectORCIDText(){
		String heading = "Connect your ORCID account";
		isElementDisplayed(true,"txt_headingORCID");
		assertTrue(element("txt_headingORCID").getText().replaceAll("[\r\n]", "").contains(heading),"Assertion Failed: Connect ORCID text heading is missing");
		logMessage("Verified: Connect ORCID text heading is displayed");		
	}
	
	public void verifyButtonLinkAccount(){
		isElementDisplayed(true,"btn_linkAccount");
		logMessage("Verified: Link account button is present");
	}
	
	public void clickOnRegisterLinkAndVerifyNavigation(){
		String text = "REGISTER";

		logMessage("User clicked on register link: " + text);
		element("link_Register").click();

		wait.waitForPageToLoadCompletely();
		isElementDisplayed(true,"txt_headingRegister");
		String url2 = getCurrentURL();
//		System.out.println("1 url is " + url2);
//		System.out.println("2 url is " + newURL);
		assertTrue((url2.contains("orcid.org")) && element("txt_headingRegister").getText().contains(text),
				"Assertion Failed: User not navigated to " + text);
		logMessage("Verified: User navigated to " + text);
		driver.navigate().back();
		wait.waitForPageToLoadCompletely();
	}
	
}
