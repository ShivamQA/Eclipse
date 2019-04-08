package com.qait.acs.keywords;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class UserProfileHomePageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "UserProfileHomePage";
	String memberBenefits = getYamlValue("userProfile.membershipBenefitsLinkAssociatedText");
	String eMailAlerts = getYamlValue("userProfile.eMailAlertsLinkAssociatedText");
	String savedSearches = getYamlValue("userProfile.savedSearchesLinkAssociatedText");
	String favoriteContent = getYamlValue("userProfile.favoriteContentLinkAssociatedText");
	String accessTokens = getYamlValue("userProfile.accessTokensLinkAssociatedText");
	String editProfile = getYamlValue("userProfile.editProfileLinkAssociatedText");
	String linksText[] = { "Member Benefits", "E-Mail Alerts", "Saved Searches", "Favorite Content", "Activate a Token",
			"Edit Profile" };
	String[] linksTextDescription = { memberBenefits, eMailAlerts, savedSearches, favoriteContent, accessTokens,
			editProfile };
	String linksInNavigationPanel[] = { "Home", "Member Benefits", "E-Mail Alerts", "Saved Searches",
			"Favorite Content", "Activate a Token", "Edit Profile"};

	String activeTokenFormMsg = "Please enter your claim number.";

	Map<String, String> memberTabMap = new HashMap<String, String>();

	public UserProfileHomePageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyUserIsOnUserProfileHomePage() {
		String header = "Profile page header";

		isElementDisplayed(true, "txt_userProfileContentHeader");
		assertTrue(element("txt_userProfileContentHeader").getText().contains(header),
				"Assertion Failed: User is not on 'User Profile' Home page!!!");
		logMessage("Assertion Passed: User is on 'User Profile' Home page!!!");
	}

	public void verifyWelcomeUserTxtAndItsDescription(String Name) {
		String user = "Welcome " + Name;
		assertTrue(element("heading_welcomeuser").getText().replaceAll("[\r\n]", "").trim().contains(user),
				"Assertion Failed: Welcome User text is missing on page");
		logMessage("Assertion Passed: Welcome User text is present on page");
		String navigationTxt = "The options in the left-hand navigation allow you to manage your profile in the following ways:";
		assertTrue(element("txt_leftNavigation").getText().trim().contains(navigationTxt),
				"Assertion Failed: Left Navigation text is missing on page");
		logMessage("Assertion Passed: Left Navigation text is present on page");
	}

	public void verifyExistenceOfLinksInNavigationPanel() {
		boolean flag = false;
		List<String> linkTexts = new ArrayList<String>();
		List<WebElement> navigationLinks = elements("lst_navigationLinksText");
		for (WebElement el : navigationLinks) {
			linkTexts.add(el.getText());
		}
		for (String link : linksInNavigationPanel) {
			if (linkTexts.contains(link))
				flag = true;
			else {
				flag = false;
				break;
			}
		}

		Assert.assertTrue(flag, "Assertion Failed: Navigation panel does not contain all expected links.");
		logMessage("Assertion Passed: Navigation panel contains all expected links.");
	}

	public void verifyDisplayOfTextAssociatedWithLinks() {
		int size = 0;
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		while (size < linksText.length) {
			Assert.assertTrue(
					element("txt_associatedTextWithLinks", linksText[size]).getText()
							.equals(linksTextDescription[size]),
					"Assertion Failed: Text associated with " + linksText[size] + " is not displayed");
			logMessage("Assertion Passed: Text associated with " + linksText[size] + " is displayed");
			size++;
		}
	}

	public void verifyFunctionalityOfLinks() {
		scrollUpMid();
		logMessage("User is on User Profile Home page after clicking Home link");
		element("lnk_navigationPanel", linksInNavigationPanel[0]).click();
		verifyUserIsOnUserProfileHomePage();
		logMessage("User is on User Profile Home page after clicking Home link");

		clickAndverifyUserIsOnMemberBenefitsPage(linksInNavigationPanel[1]);
		clickAndverifyUserIsOnEMailAlertsPage(linksInNavigationPanel[2]);
		clickAndverifyUserIsOnSavedSearchesPage(linksInNavigationPanel[3]);
		clickAndverifyUserIsOnFavoriteContentPage(linksInNavigationPanel[4]);
		clickAndverifyUserIsOnActivateTokenPage(linksInNavigationPanel[5]);
		// verifyUserIsOnEditProfileHomePage(links[6]);
	}

	public void clickAndverifyUserIsOnMemberBenefitsPage(String memberBenefits) {
		element("lnk_navigationPanel", memberBenefits).click();
		wait.hardWait(1);
		isElementDisplayed(true, "heading_welcomeuser");
		assertTrue(element("heading_welcomeuser").getText().replaceAll("[\r\n]", "").trim().contains(memberBenefits),
				"Assertion Failed: " + memberBenefits + " is missing on page");
		logMessage("Assertion Passed: " + memberBenefits + " is present on page");

		Assert.assertTrue(getCurrentURL().contains("licenses"),
				"Assertion Failed: URL for Member benefits page is not correct");

		logMessage("Assertion Passed: User is on 'Member Benefits' page!!!");
	}

	/**
	 * Method which verifies that User is on 'User Profile' E-Mail Alerts page
	 * 
	 */
	public void clickAndverifyUserIsOnEMailAlertsPage(String eMailAlerts) {
		scrollUpMid();
		element("lnk_navigationPanel", eMailAlerts).click();
		wait.hardWait(1);
		isElementDisplayed(true, "heading_welcomeuser");
		assertTrue(element("heading_welcomeuser").getText().replaceAll("[\r\n]", "").trim().contains(eMailAlerts),
				"Assertion Failed: " + eMailAlerts + " is missing on page");
		logMessage("Assertion Passed: " + eMailAlerts + " is present on page");
		Assert.assertTrue(getCurrentURL().contains("Alerts"),
				"Assertion Failed: URL for EMail Alerts page is not correct");

		logMessage("Assertion Passed: User is on 'EMail Alerts' page!!!");
	}

	/**
	 * Method which verifies that User is on 'User Profile' Saved Searches page
	 * 
	 */
	public void clickAndverifyUserIsOnSavedSearchesPage(String savedSearches) {
		scrollUpMid();
		element("lnk_navigationPanel", savedSearches).click();
		wait.hardWait(1);
		isElementDisplayed(true, "heading_welcomeuser");
		assertTrue(element("heading_welcomeuser").getText().replaceAll("[\r\n]", "").trim().contains(savedSearches),
				"Assertion Failed: " + savedSearches + " is missing on page");
		logMessage("Assertion Passed: " + savedSearches + " is present on page");
		Assert.assertTrue((getCurrentURL().contains("search")) && (getCurrentURL().contains("saved")),
				"Assertion Failed: URL for Saved Searches page is not correct");

		logMessage("Assertion Passed: User is on 'Saved Searches' page!!!");
	}

	/**
	 * Method which verifies that User is on 'User Profile' Favorite Content
	 * page
	 * 
	 */
	public void clickAndverifyUserIsOnFavoriteContentPage(String favoriteContent) {
		wait.hardWait(1);
		scrollUpMid();
		element("lnk_navigationPanel", favoriteContent).click();
		wait.waitForPageToLoadCompletely();
		isElementDisplayed(true, "heading_welcomeuser");
		assertTrue(element("heading_welcomeuser").getText().replaceAll("[\r\n]", "").trim().contains(favoriteContent),
				"Assertion Failed: " + favoriteContent + " is missing on page");
		logMessage("Assertion Passed: " + favoriteContent + " is present on page");
		Assert.assertTrue((getCurrentURL().contains("favorites")),
				"Assertion Failed: URL for favorites page is not correct");
		logMessage("Assertion Passed: User is on 'Favorite Content' page!!!");
	}

	public void clickAndverifyUserIsOnActivateTokenPage(String activateToken) {
		scrollUpMid();
		clickOnElement("lnk_navigationPanel", activateToken);
		isElementDisplayed(true, "txtbox_token");
		isElementDisplayed(true, "heading_welcomeuser");
		assertTrue(element("heading_welcomeuser").getText().replaceAll("[\r\n]", "").trim().contains(activateToken),
				"Assertion Failed: " + activateToken + " is missing on page");
		logMessage("Assertion Passed: " + activateToken + " is present on page");
		Assert.assertTrue((getCurrentURL().contains("Access")),
				"Assertion Failed: URL for Activate Token page is not correct");
		logMessage("Assertion Passed: User is on 'Activate Token' page!!!");
	}

	/**
	 * Method which verifies that User is on 'User Profile' Activate Token page
	 * 
	 */
	public void verifyUserIsOnEditProfileHomePage(String editProfile) {
		clickOnElement("lnk_navigationPanel", editProfile);
		isElementDisplayed(true, "txt_userName");
		logMessage("User's name is displayed as:" + element("txt_userName").getText().trim());
		isElementDisplayed(true, "btn_changeEmail");
		isElementDisplayed(true, "btn_addAddress");
		logMessage("Buttons such as Change Email, Add Phone, Add Address to edit profile are displayed");
		logMessage("Assertion Passed: User is on 'Edit Profile' Home page!!!");
	}

	public void verifyFunctionalityOfLinksOnMembershipBenefitsPage() {
		memberTabMap.put("Access Types", getYamlValue("memberBenefits.accessTypes.noContentMessage"));
		memberTabMap.put("Access History", getYamlValue("memberBenefits.accessHistory.noContentMessage"));
		for (Map.Entry<String, String> tab : memberTabMap.entrySet()) {
			isElementDisplayed(true, "lnk_memberBenifitsTypes", tab.getKey());
			clickOnElement("lnk_memberBenifitsTypes", tab.getKey());
			logMessage("User clicked on " + tab.getKey() + " link");
			wait.hardWait(1);
			Assert.assertTrue(element("txt_accessTypesOrHistoryContent", tab.getKey()).getText().equals(tab.getValue()),
					"Assertion Failed:: No products available at this time message is not displayed on click of Access Types link when no content is available.");
			logMessage("Assertion Passed:: " + tab.getValue()
					+ " message is displayed on click of Access Types link when no content is available.");
			if (!element("txt_accessTypesOrHistoryContent", tab.getKey()).getText().equals(tab.getValue())
					&& element("txt_accessTypesContent").getText() != "")
				logMessage("Content is displayed on click of Access Types link.");
		}
	}

	public void verifyFunctionalityAvailableOnActivateATokenPage() {
		isElementDisplayed(true, "txt_activateTokenInstructionMsg");
		assertTrue(element("txt_activateTokenInstructionMsg").getText().contains(activeTokenFormMsg),
				"Assertion Failed: Active Token Instruction Message is incorrect");
		logMessage("Assertion Passed: Active Token Instruction Message is correct");

		isElementDisplayed(true, "txt_claimNumber");
		assertTrue(element("txt_claimNumber").getText().contains("Claim Number"),
				"Assertion Failed: Claim Number text is incorrect");
		logMessage("Assertion Passed: Claim Number text is correct");

		isElementDisplayed(true, "txtbox_claimNumber");

		isElementDisplayed(true, "btn_tokenSubmit");
		logMessage("Assertion Passed: Submit button is present");
	}

	public void verifyMendeleyPaired(Boolean Paired) {
		scrollDown();
		isElementDisplayed(Paired, "link_Mendeley");

		
		if(Paired)
			logMessage("Verified: Mendeley link is displayed : ACS ID - Mendeley pairing has occured succesfully.");
		else
			logMessage("Verified: Mendeley link is not displayed : ACS ID does not have a mendeley pairing.");

	}
	
	public void removeMendeleyPairing() {
		String MendeleyPairMsg="Your ACS ID is currently paired with a Mendeley account";
		scrollDown();
		hardWait(2);
		executeJavascript("document.querySelectorAll(\"li[style*='display']>img+a[href*='Mendeley']\")[0].click();");
		logMessage("Info: Clicked on Mendeley link.");
		hardWait(2);
		isStringMatching(element("Msg_MendeleyPairStatus").getText(), MendeleyPairMsg);
		element("btn_RevokeMendeley").click();
		isElementDisplayed(false, "link_Mendeley");
	}

	
}
