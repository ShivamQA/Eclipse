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

public class EMailAlertsHomePageActions extends GetPage {

	WebDriver driver;

	String alertsSentEmailMessage = "Email alerts will be sent to t1542190217890@fake.com. To update your email address, edit your Account Information";
	String alertsSentEmailMessage1 = "Email alerts will be sent to sunenasingh12345@acs.org. To update your email address, edit your Account Information";

	String alertsEmailUpdatedMsg = "Any edits made to the journal alerts will not be saved until you click \"Save Preferences\" button that appears beneath the \"Select E-mail format\" section.";
	String noJournalSelectedMsg = "You do not have any alerts configured.Click on the \"Add a Journal Alert\" button to add a new alert.";

//	String[] journalAlertOptions = { alertsSentEmailMessage, alertsEmailUpdatedMsg };
	String[] journalAlertOptions = { alertsSentEmailMessage1, alertsEmailUpdatedMsg };

	String[] tableJournalContent = { "Title", "Issue alerts", "Article alerts" };
	String[] emailFormate = { "HTML", "Plain text" };

	public EMailAlertsHomePageActions(WebDriver driver) {
		super(driver, "EMailAlertsHomePage");
		this.driver = driver;
	}
	
	public void verifyNavigationToEmailAlertsPage() {
		isElementDisplayed(true, "h1_EmailALerts");
		Assert.assertTrue(element("link_EmailAlerts").getAttribute("class").contains("active"));
		isElementDisplayed(true, "btn_AddJournal");
		logMessage("Verified: Navigation to e-Mail alerts page.");
	}


	public void verifyVariousOptionsAvailableOfEmailAlertsSection(String[] journalAlertOptions,
			String alertsInstructionMsg) {
		// isElementDisplayed(true,"txt_alertsInstructionMsg");
		// Assert.assertEquals(element("txt_alertsInstructionMsg").getText(),
		// alertsInstructionMsg,"Assertion Failed:: " +alertsInstructionMsg+" is
		// not displayed as Alerts instruction message");
		// logMessage("Assertion Passed:: " +alertsInstructionMsg+" is displayed
		// as Alerts instruction message");
		// navigateToPreviousPage();
		isElementDisplayed(true, "txt_journalAlerts");
		isElementDisplayed(true, "txt_journalAlertOptions", journalAlertOptions[0]);
		isElementDisplayed(true, "txt_journalAlertOptions", journalAlertOptions[1]);
		isElementDisplayed(true, "txt_journalAlertOptions", journalAlertOptions[2]);
		logMessage("Journal alerts with " + journalAlertOptions[0] + "," + journalAlertOptions[1] + "and "
				+ journalAlertOptions[2] + " preferences is displayed");
		isElementDisplayed(true, "txt_citationAlert");
		logMessage("Citation Alerts is dispalyed");
	}

	public void clickAndVerifytabsOnEmailAlert(String tab) {
		isElementDisplayed(true, "tab_emailAlertPage", tab);
		logMessage("Assertion Passed: Tab " + tab + " on Email Alert is present");
		clickOnElement("tab_emailAlertPage", tab);
	}

	public void verifyVariousOptionsAvailableOnNewContentAlertsEmailAlertsSection() {
		int count = 0;
		isElementDisplayed(true, "txt_journalAlerts");
		assertTrue(element("txt_journalAlerts").getText().trim().equalsIgnoreCase("1. Journal Alerts"),
				"Assertion Failed: 1. Journal Alerts is missing under New content alerts tab");
		logMessage("Assertion Passed: 1. Journal Alerts is present under New content alerts tab");

		scrollDown(element("btn_addJournal"));
		while (count < journalAlertOptions.length) {
			assertTrue(elements("txt_journalAlertOptions").get(count).getText().contains(journalAlertOptions[count]),
					"Assertion Failed: Journal Alert text: " + journalAlertOptions[count] + " is missing");
			logMessage("Assertion Passed: Journal Alert text: " + journalAlertOptions[count] + " is present");
			count++;
		}
		System.out.println("Value of count: " + count);
		isElementDisplayed(true, "btn_addJournal");
		logMessage("Assertion Passed: Add Journal button is present");

		count = 0;
		for (WebElement col : elements("tbl_contentAlert")) {
			assertTrue(col.getText().replaceAll("[\r\n]", "").trim().contains(tableJournalContent[count]),
					"Assertion Failed: Journal table Content text: " + tableJournalContent[count] + " is missing");
			logMessage("Assertion Passed: Journal table Content text: " + tableJournalContent[count] + " is present");
			count++;
		}

		if (checkIfElementIsThere("txt_noJournalSelected")) {
			assertTrue(element("txt_noJournalSelected").getText().contains(noJournalSelectedMsg),
					"Assertion Failed: No Journal Selected Message is missing");
			logMessage("Assertion Passed: No Journal Selected Message is present");
		}

		assertTrue(element("txt_selectEmailFormateAlerts").getText().trim().equalsIgnoreCase("2. Select E-mail Format"),
				"Assertion Failed: 2. Select E-mail Format is missing under New content alerts tab");
		logMessage("Assertion Passed: 2. Select E-mail Format is present under New content alerts tab");

		count = 0;
		for (WebElement label : elements("label_emailFormate")) {
			assertTrue(label.getText().trim().contains(emailFormate[count]),
					"Assertion Failed: Journal email Formate type: " + emailFormate[count] + " is missing");
			logMessage("Assertion Passed: Journal email Formate type: " + emailFormate[count] + " is present");
			count++;
		}

		isElementDisplayed(true, "btn_savePreferences");
		logMessage("Assertion Passed: Save Preferences button is present on page");

		scrollUp();
	}

	public void verifyVariousOptionsAvailableOnCitationsAlertsEmailAlertsSection() {
		isElementDisplayed(true, "txt_citationAlert");
		assertTrue(element("txt_citationAlert").getText().trim().contains("Citation Alerts"),
				"Assertion Failed: Citation Alerts text is missing under Citations Alerts tab");
		logMessage("Assertion Passed: Citation Alerts text is present under Citations Alerts tab");

		String defaultMsg = "You are not yet tracking any articles.";

		isElementDisplayed(true, "txt_defaultCitationMsg");
		assertTrue(element("txt_defaultCitationMsg").getText().trim().contains(defaultMsg),
				"Assertion Failed: " + defaultMsg + " text is missing under Citations Alerts tab");
		logMessage("Assertion Passed: " + defaultMsg + " text is present under Citations Alerts tab");
	}

	public void clickOnAddJournalButton() {
		isElementDisplayed(true, "btn_addJournal");
		logMessage("Assertion Passed: Add Journal button is present");
		clickOnElement("btn_addJournal");
	}

	public void verifyAllJournalsCount() {
		isElementDisplayed(true, "popUp_allJournals");
		logMessage("Assertion Passed: POP UP window for All Journals is open");

		System.out.println("count: " + elements("count_allJournals").size());
		assertTrue(elements("count_allJournals").size() == 58,
				"Assertion Failed: All Journals are not present in POP UP");
		logMessage("Assertion Passed: All Journals are present in POP UP");
	}

	public void closeAllJournalsPopUP() {
		isElementDisplayed(true, "btn_closePopUp");
		logMessage("Assertion Passed: Close Button for POP UP is displayed");
		clickOnElement("btn_closePopUp");
	}

	public String selectJournal(int journal) {
		String JournalName = elements("count_allJournals").get(journal).getText();
		elements("count_allJournals").get(journal).click();
		logMessage("Assertion Passed: Journal Selected is " + journal + "and name of Journal is: " + JournalName);
		return JournalName;
	}

	public void verifyAllJournalsPOPUPIsOpenOrClosed() {
		if (checkIfElementIsThere("popUp_allJournals"))
			logMessage("Assertion Passed: POP UP window for All Journals is open");
		else
			logMessage("Assertion Passed: POP UP window for All Journals is closed");
	}

	public void verifySelectedJournalColorinPOPUP(int journal) {
		// for black : rgba(0, 0, 0, 1) and for blue is: rgba(0, 57, 166, 1)
		assertTrue(elements("count_allJournals").get(journal).getCssValue("color").equalsIgnoreCase("rgba(0, 0, 0, 1)"),
				"Assertion Failed: Selected css value for Journal is not Black");
		logMessage("Assertion PAssed: Selected css value for Journal is Black");
	}

	public void verifyNonSelectedJournalColorinPOPUP(int journal) {
		assertTrue(
				elements("count_allJournals").get(journal).getCssValue("color").equalsIgnoreCase("rgba(0, 57, 166, 1)"),
				"Assertion Failed: Selected css value for Journal is not Blue");
		logMessage("Assertion PAssed: Selected css value for Journal is Blue");
	}

	public void verifyCheckedBoxIssueAlertCheckedOrUnchecked(String journal) {
		if (!journal.equalsIgnoreCase("ACS Omega")) {
			assertTrue(element("chkbx_issueAlert", journal).isSelected(),
					"Assertion Failed: Issue Alert checkbox is unchecked");
			logMessage("Assertion Passed : Issue Alert checkbox is checked");
		} else {
			if (!element("chkbx_issueAlert", journal).isSelected())
				logMessage("Assertion Passed: Issue Alert checkbox is unchecked");
		}
	}

	public void verifySelectedJournalsInformationInTable(String journal) {
		int count = 0;
		String articleAlertOptions[] = { "Never", "Daily", "Weekly", "Monthly" };
		isElementDisplayed(true, "txt_selectedTitle");
		assertTrue(element("txt_selectedTitle").getText().equalsIgnoreCase(journal),
				"Assertion Failed: Selected Journal title is not: " + journal);
		logMessage("Assertion Passed: Selected Journal title is correct: " + journal);

		verifyCheckedBoxIssueAlertCheckedOrUnchecked(journal);

		scrollDown(element("btn_addJournal"));

		clickOnElement("select_dd_articleAlert", journal);

		for (WebElement articleOption : elements("dd_articleAlert", journal)) {
			assertTrue(articleOption.getText().contains(articleAlertOptions[count]),
					"Assertion Failed: Alert Articles options is not available for: " + articleAlertOptions[count]);
			logMessage("Assertion Passed: Alert Articles options is available for: " + articleAlertOptions[count]);
			count++;
		}
	}

	public void selectArticleAlertOption(String journal, String option) {
		clickOnElement("select_dd_articleAlert", journal);

		for (WebElement articleOption : elements("dd_articleAlert", journal)) {
			if (articleOption.getText().contains(option)) {
				articleOption.click();
				logMessage("Assertion Passed: Alert Articles options is available for: " + option);
				break;
			}
		}

	}

	public void clickOnSavePreferenceButton() {
		scrollDown(element("btn_addJournal"));
		isElementDisplayed(true, "btn_savePreferences");
		logMessage("Assertion Passed: Save Preferences button is present on page");
		clickOnElement("btn_savePreferences");
	}

	public void verifyEmailAlertUpdatedMsg() {
		scrollUp();
		String updatedMsg = "Your email alert settings have been updated.";
		assertTrue(element("txt_emailAlertUpdate").getText().equalsIgnoreCase(updatedMsg),
				"Assertion Failed: Email Alert Msg: " + updatedMsg + " is not displayed");
		logMessage("Assertion Passed: Email Alert Msg: " + updatedMsg + " is displayed");
	}

	public void selectOrDeselectCheckedBoxIssueAlert(String check, String journal) {
		scrollDown(element("btn_addJournal"));

		if (check.equalsIgnoreCase("checked") || check.equalsIgnoreCase("Checked")) {
			if (element("chkbx_issueAlert", journal).isSelected())
				logMessage("Assertion Passed : Issue Alert checkbox is checked");
			else {
				element("chkbx_issueAlert", journal).click();
				logMessage("Assertion Passed : Issue Alert checkbox is checked");
			}
		}

		if (check.equalsIgnoreCase("unchecked") || check.equalsIgnoreCase("Unchecked")) {
			if (!element("chkbx_issueAlert", journal).isSelected())
				logMessage("Assertion Passed : Issue Alert checkbox is unchecked");
			else {
				element("chkbx_issueAlert", journal).click();
				logMessage("Assertion Passed : Issue Alert checkbox is unchecked");
			}
		}
	}
	
	public void verifyNoJournalSelected() {
		if (checkIfElementIsThere("txt_noJournalSelected")) {
			assertTrue(element("txt_noJournalSelected").getText().contains(noJournalSelectedMsg),
					"Assertion Failed: No Journal Selected Message is missing");
			logMessage("Assertion Passed: No Journal Selected Message is present");
		}
	}

	public String selectOmegaJournal() {
		String JournalName = element("txt_omegaJournal").getText();
		element("txt_omegaJournal").click();
		logMessage("Assertion Passed: Journal Selected is " + JournalName);
		return JournalName;
	}
}
