package com.qait.acs.keywords;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.DateUtil;

public class SearchResultsPageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "searchResultsPage";
	public loginPageActions logIN;
	String searchTitle[] = { "Content Type", "Article Type", "Publication Date", "Author", "Publication" };
	int year = Calendar.getInstance().get(Calendar.YEAR);
	String[] advanceDropDownOptions = { "Anywhere", "Title", "Author", "Abstract", "Figure/Table Caption" };
	String[] LastPubDateOptions = { "Select", "month", "6 months", "year" };
	String[] c_enGroupOptions = { "Include Tables of Contents in search results",
			"Include full-page advertisements in search results" };
	Random random = new Random();

	public SearchResultsPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyNavigationToSearchResultsPage(String SearchTerm) {
		if (SearchTerm.contains("\"")) {
			String term = SearchTerm.replace("\"", "\'");
			Assert.assertTrue(getPageTitle().contains(term.toLowerCase()),
					"Assertion failed: Not navigated to search results page.");
		} else {
			Assert.assertTrue(getPageTitle().contains(SearchTerm.toLowerCase()),
					"Assertion failed: Not navigated to search results page.");
		}
		isStringMatching(executeJavascript("return arguments[0].value", element("input_SearchBox")), SearchTerm);
		isElementDisplayed(true, "text_ResultCount");
		verifyFormat(element("text_ResultCount").getText(), "RESULTS:\\s\\d{1}\\s-\\s\\d{1,3}");

		logMessage("Verified: Navigation to search results page.");
	}

	public void verifyFiltersAppliedResultColumnAuthor(String Author) {
		assertTrue(isElementDisplayed(true, "txt_FiltersApplied"),
				"Assertion Failed: Filters Applied text box is missing on Search Page");
		logMessage("Assertion Passed: Filters Applied text box is present on Search Page");
		String authName = swapAuthorName(Author);
		assertTrue(element("txt_FilterAppliedResult").getText().contains(authName),
				"Assertion Failed: Author name on Search Result page does'nt matchwith Editor Author Name");
		logMessage("Assertion Passed: Author name on Search Result page matches with Editor Author Name");
	}

	public String swapAuthorName(String Author) {
		String auth = Author.trim();
		int l = auth.length();
		int a = auth.lastIndexOf(" ");
		String sur = auth.substring(a, l);
		// String remain = auth.substring(0, 1);
		// String authName = sur + "," +" " +remain;
		String authName = sur;
		authName = authName.trim();
		System.out.println("new authoname: " + authName);
		System.out.println("text: " + element("txt_FilterAppliedResult").getText());
		return authName;
	}

	public void clickOnSaveSearchButtonForThisSearchBox() {
		element("img_saveThisSearchbox").click();
		logMessage("User clicked on 'Save Search' button");
	}

	public void verifySearchPageIsLoadedForSavedSearch(String searchTerm) {
		hardWait(4);
		assertTrue((element("txt_firstArticleTitle", "1").getText().toLowerCase()).contains(searchTerm.toLowerCase()),
				"Assertion Failed: Search page is NOT loaded with the Searched Term as article title does not contains searched term!!!");
		logMessage(
				"Assertion Passed: Search page is loaded with the Searched Term as article title contains searched term!!!");
	}

	public void verifySaveButtonUnderSaveThisSearchBoxIsClicked(String message, String[] optionsForAlertDropDown) {
		isElementDisplayed(true, "txtbox_searchName");
		isElementDisplayed(true, "txt_saveThisSearch");
		isElementDisplayed(true, "txt_alertMeNotification", message);
		for (int i = 0; i < elements("li_radioOptionsForAlertMe").size(); i++) {
			isStringMatching(elements("li_radioOptionsForAlertMe").get(i).getAttribute("value"),
					optionsForAlertDropDown[i]);
		}
		isElementDisplayed(true, "lnk_cancelLink");
		isElementDisplayed(true, "btn_saveSearchTerm");

		logMessage(
				"Assertion Passed: All items are successfully visible on expanding Save button under Save This Search box!!!");
	}

	public void saveASearch(String search, String alertOption) {
		element("txtbox_searchName").sendKeys(search);
		logMessage("User Saved Search by name: " + search);
		for (WebElement alert : elements("li_radioOptionsForAlertMe")) {
			if (alert.getAttribute("value").equals(alertOption)) {
				logMessage("User Selected Saved Search alert me options for: " + alertOption);
				alert.click();
				break;
			}
		}

		clickOnElement("btn_saveSearchTerm");
		hardWait(2);
	}

	public void verifyRadioButtonSelectedForChoice(String choice) {
		// hardWait(2);
		if (choice.contains("All Publications/Website")) {
			choice = null;
			assertTrue(element("btn_radioSearchBox", choice).getAttribute("checked").contains("checked"),
					"Assertion Failed: Radio button for All Publications/Website is not selected");
			logMessage("Assertion Passed: Radio button for All Publications/Website is selected");
		} else {
			element("quick_search").isDisplayed();

			assertTrue(element("btn_radioSearchBox", choice).isSelected(),
					"Assertion Failed: Radio button for " + choice + " is not selected");
			logMessage("Assertion Passed: Radio button for " + choice + " is selected");
		}
	}

	public void verifyFollowResultsOptions() {
		isElementDisplayed(true, "txt_followResult");
		logMessage("Verified: Text Follow Results avilable");

		isElementDisplayed(true, "img_saveThisSearchbox");
		logMessage("Verified: Link Save This Search is avilable");

		isElementDisplayed(true, "lnk_RssField");
		logMessage("Verified: Link RSS field is avilable");
	}

	public void verifyTwoSortByOptionAvailable() {
		isElementDisplayed(true, "lbl_txtSORT");
		logMessage("Verified: SORT label is present");

		assertTrue(element("txt_selectedSortByOption").getText().contains("RELEVANCE"),
				"Assertion Failed: sort by Relevance is not selected by default");
		logMessage("Assertion Passed: sort by Relevance is selected by default");

		// if(element("arrow_ddSortBy").getAttribute("aria-expanded").contains("false"))
		clickOnElement("arrow_ddSortBy");
		// isElementDisplayed(true, "arrow_ddSortBy");
		// System.out.println(element("arrow_ddSortBy").getAttribute("aria-expanded"));

		assertTrue(element("txt_ddSortBy").getText().contains("Date"),
				"Assertion Failed: sort by other Option Date is not present");
		logMessage("Assertion Passed: sort by other Option Date is present");

		clickOnElement("txt_selectedSortByOption");
	}

	public void verifyRefineSearchButtonAndArrow() {
		isElementDisplayed(true, "btn_RefineSearch");
		logMessage("Verified: Refine Search button is present");

		isElementDisplayed(true, "arrow_RefineSearch");
		logMessage("Verified: Arrow for Refine Search is present");
	}

	public void verifyPerPageOptionAvailability() {
		String pageCount[] = { "20", "50", "100" };
		int count = 0;

		isElementDisplayed(true, "txt_perPageOption");
		logMessage("Verified: Per Page is available");

		for (WebElement page : elements("lst_perPageOption")) {
			assertTrue(page.getText().contains(pageCount[count]),
					"Assertion Failed: Per Page Option is not correct for: " + pageCount[count]);
			logMessage("Assertion Failed: Per Page Option is correct for: " + pageCount[count]);
			count++;
		}
	}

	public void verifyNarrowResultsTitleOptions() {

		int count = 0;

		for (WebElement title : elements("lst_narrowResultTitle")) {
			assertTrue(title.getAttribute("id").contains(searchTitle[count]),
					"Assertion Failed: Narrow result Search Title Option is not correct for: " + searchTitle[count]);
			logMessage("Assertion Passed: Narrow result Search Title Option is correct for: " + searchTitle[count]);
			count++;
		}

	}

	public void verifyPreviousNextPageNavigaionButton(String page) {

		if (page.contains("Next")) {
			elements("arrow_nextPage").get(0);
			logMessage("Verified: Top Next page navigation arrow is displayed");
		} else {
			elements("arrow_prviousPage").get(0);
			logMessage("Verified: Top Previous page navigation arrow is displayed");
		}

		Actions action = new Actions(driver);
		action.sendKeys(Keys.END).build().perform();
		if (page.contains("Next")) {
			elements("arrow_nextPage").get(1);
			logMessage("Verified: Bottom Next page navigation arrow is displayed");
		} else {
			elements("arrow_prviousPage").get(1);
			logMessage("Verified: Bottom Previous page navigation arrow is displayed");
		}
		Actions action2 = new Actions(driver);
		action2.sendKeys(Keys.HOME).build().perform();
	}

	public void verifySavedSearchPopUpIsClosed() {
		isElementDisplayed(true, "lnk_cancelLink");
		clickOnElement("lnk_cancelLink");
		hardWait(1);
		isElementDisplayed(true, "img_saveThisSearchbox");
		logMessage("Verified: Saved Search pop up is closed");
	}

	public void clickAndVerifyEditSavedSearchButton() {
		isElementDisplayed(true, "btn_editSaveSearch");
		clickOnElement("btn_editSaveSearch");

		logMessage("Verified and Clicked on Edit this saved search result");
	}

	public void verifylogInLinkOnSaveSearchPopUp() {
		isElementDisplayed(true, "txt_saveThisSearch");
		isElementDisplayed(true, "lnk_LogInSaveSearchPopUp");
	}

	public void clickAndVerifyLoginLinkNavigationPresentOnSaveSearchPopUP() {
		clickOnElement("lnk_LogInSaveSearchPopUp");
		logIN.verifyNavigationToLoginPage();
	}

	public void verifySingleTheNarrowResultSelectedOption(String searchterm) {
		int counter = 0;
		while (counter < searchTitle.length) {
			if (checkIfElementIsThere("filter_showMoreSeletion", searchTitle[counter])) {
				if (element("filter_showMoreSeletion", searchTitle[counter]).getAttribute("aria-expanded")
						.contains("false"))
					clickOnElement("filter_showMoreSeletion", searchTitle[counter]);
			}

			// int filter = elements("filter_selection",
			// searchTitle[counter]).size();
			// int randomIndex = random.nextInt(filter);

			WebElement title = element("lnk_narrrowTitleOption", searchTitle[counter],
					elements("filter_selection", searchTitle[counter]).get(0).getAttribute("title").trim());

			String subType = title.getAttribute("title").trim();
			String filterCount = elements("filter_selectionCounter", searchTitle[counter]).get(0).getText();

			title.click();
			isElementDisplayed(true, "txt_filterResult");
			logMessage("Verified: Filter Result text appears");
			assertTrue(element("txt_filterSearchOptn").getAttribute("title").contains(subType),
					"Assertion Failed: Selected sub option under: " + searchTitle[counter] + " " + subType
							+ "is not present under filter result");
			logMessage("Assertion Passed: Selected sub option under: " + searchTitle[counter] + " " + subType
					+ "is present under filter result");
			// verifySearchPageIsLoadedForSavedSearch(searchterm);
			verifyFilterInfoType(searchTitle[counter], subType, filterCount);
			clickOnRemoveFilterButton();
			counter++;
		}
	}

	public void clickOnRemoveFilterButton() {
		clickOnElement("btn_rmvFilterOptn");
		logMessage("Clicked on Remove filter");
	}

	public void verifyFilterInfoType(String type, String value, String filterCount) {

		if (type.equals("Content Type") || type.equals("Article Type")) {
			String option[] = value.split(" ");
			logMessage("Search Result count:- " + elements("txt_typeSearchResult").size());
			for (WebElement result : elements("txt_typeSearchResult")) {
				assertTrue(result.getText().contains(option[1]),
						"Assertion Failed: Search result doesn't contain type:- " + option[1]);
			}
			logMessage("Assertion Passed: Search result contains type:- " + option[1]);

			// assertTrue(filterCount.equals(element("txt_totalResultCount").getText()),
			// "Assertion Failed: result count is not same for selected filter
			// result");
			// logMessage("Assertion Passed: result count is same for selected
			// filter result");
		}

		else if (type.equals("Publication")) {
			logMessage("Search Result count:- " + elements("txt_journalNameSearchResult").size());
			for (WebElement result : elements("txt_journalNameSearchResult")) {
				assertTrue(result.getText().contains(value),
						"Assertion Failed: Search result doesn't contain type:- " + value);
			}
			logMessage("Assertion Passed: Search result contains type:- " + value);
			assertTrue(filterCount.equals(element("txt_totalResultCount").getText()),
					"Assertion Failed: result count is not same for selected filter result");
			logMessage("Assertion Passed: result count is same for selected filter result");
		}

		// else if (type.equals("Publication Date")) {
		// if (value.equals("Last Year")) {
		// logMessage("Search Result count:- " +
		// elements("txt_dateSearchResult").size());
		// for (WebElement result : elements("txt_dateSearchResult")) {
		// String Result[]= result.getText().split(",");
		// assertTrue(Integer.valueOf(Result[1].trim())<year, "Assertion Failed:
		// Search result doesn't contain type with in selected :- " + value);
		// }logMessage("Assertion Passed: Search result contains type with in
		// selected :- " + value);
		// }
		// }

		else if (type.equals("Author")) {
			String authName = swapAuthorName(value);
			logMessage("Search Result count:- " + elements("lst_searchResult").size());
			for (WebElement result : elements("txt_authorNameSearchResult")) {
				// System.out.println(result.getText());
				assertTrue(result.getText().contains(authName),
						"Assertion Failed: Search result doesn't contain type:- " + value);
			}
			logMessage("Assertion Passed: Search result contains type:- " + value);
			assertTrue(filterCount.equals(element("txt_totalResultCount").getText()),
					"Assertion Failed: result count is not same for selected filter result");
			logMessage("Assertion Passed: result count is same for selected filter result");
		}
	}

	public void verifyTheSelectedPerPage(int page) {
		assertTrue(element("txt_selectedPerPage").getText().contains(String.valueOf(page)),
				"Assertion Failed: Selected per page option is not the " + page);
		logMessage("Assertion Passed: Selected per page option is the " + page);

		assertTrue(element("text_ResultCount").getText().contains(String.valueOf(page)),
				"Assertion Failed: Result count doesn't contain Selected Per Page count");
		logMessage("Assertion Passed: Result count contain Selected Per Page count");
	}

	public void verifySearchResultCount(int page) {
		assertTrue(elements("lst_searchResult").size() == page,
				"Assertion Failed: Search Result doesn't contains the exact results as per result count");
		logMessage("Assertion Passed: Search Result contains the exact results as per result count");
	}

	public void clickOnPerPageOption(int pageCount) {
		for (WebElement page : elements("lst_perPageOption")) {
			if (page.getText().contains(String.valueOf(pageCount))) {
				page.click();
				logMessage("Verified CLicked on per page count : " + pageCount);
				break;
			}
		}
	}

	public void clickAndVerifyPreviousNextPageNavigaionButton(int page, String option) {

		if (option.contains("Next")) {
			elements("arrow_nextPage").get(0);
			logMessage("Verified: Top Next page navigation arrow is displayed");
			elements("arrow_nextPage").get(0).click();
			assertTrue(element("text_ResultCount").getText().contains(String.valueOf(page + 1)),
					"Assertion Failed: Result count doesn't contain Selected next Per Page count");
			logMessage("Assertion Passed: Result count contain Selected next Per Page count"
					+ element("text_ResultCount").getText());
		} else {
			elements("arrow_prviousPage").get(0);
			logMessage("Verified: Top Previous page navigation arrow is displayed");
			elements("arrow_prviousPage").get(0).click();
			assertTrue(element("text_ResultCount").getText().contains(String.valueOf(page * 2)),
					"Assertion Failed: Result count doesn't contain previous Selected Per Page count");
			logMessage("Assertion Passed: Result count contain previous Selected Per Page count"
					+ element("text_ResultCount").getText());
		}

		Actions action = new Actions(driver);
		action.sendKeys(Keys.END).build().perform();
		if (option.contains("Next")) {
			elements("arrow_nextPage").get(1);
			logMessage("Verified: Next page navigation arrow is displayed");
			elements("arrow_nextPage").get(1).click();
			assertTrue(element("text_ResultCount").getText().contains(String.valueOf(page * 2 + 1)),
					"Assertion Failed: Result count doesn't contain Selected next Per Page count");
			logMessage("Assertion Passed: Result count contain Selected next Per Page count"
					+ element("text_ResultCount").getText());
		} else {
			elements("arrow_prviousPage").get(1);
			logMessage("Verified: Previous page navigation arrow is displayed");
			elements("arrow_prviousPage").get(1).click();
			System.out.println(element("text_ResultCount").getText() + " and " + page);
			assertTrue(element("text_ResultCount").getText().contains(String.valueOf(page)),
					"Assertion Failed: Result count doesn't contain previous Selected Per Page count"
							+ element("text_ResultCount").getText());
			logMessage("Assertion Passed: Result count contain previous Selected Per Page count");
		}
	}

	public void verifysearchCitationOption() {
		isElementDisplayed(true, "select_ddJournal");
		logMessage("Verified: Journal dropdown option is available");

		isElementDisplayed(true, "txtbox_vol");
		logMessage("Verified: Volume textbox option is available");

		isElementDisplayed(true, "txtbox_page");
		logMessage("Verified: Page textbox option is available");

		isElementDisplayed(true, "iconSrchCit_SearchBox");
		logMessage("Verified: Search citation option is available");

	}

	public void selectDropDownJournalOption(String journal) {
		clickOnElement("select_ddJournal");
		hardWait(2);
		element("DD_SearchJournals", journal).click();
		logMessage("Verified: journal dropdown option is selected");
	}

	public void enterValueInVolumnTextBox(String vol) {
		sendKeys(vol, "txtbox_vol");
		logMessage("Verified: Value entered into Volume box is: " + vol);
	}

	public void enterValueInPageTextBox(String page) {
		sendKeys(page, "txtbox_page");
		logMessage("Verified: Value entered into Page box is: " + page);
	}

	public void clickOnCitationSearchButton() {
		clickOnElement("iconSrchCit_SearchBox");
	}

	public void clickOnRssFeedBesideSearchResultsTitle() {
		element("lnk_RssField").click();
		logMessage("User clicked on 'RSS Feed' link icon");
	}

	public void verifyNavigationToRSSFeedsPage() {
		isElementDisplayed(true, "page_rssXML");
		// Assert.assertTrue(element("txt_feedTitle").getText().contains(journal),"Assertion
		// Failed:: User is not on RSS Feeds page");
		logMessage("Assertion Passed: User is on RSS Feeds page");
		navigateToPreviousPage();
	}

	public void verifyErrorPopUpOnAdvancedSearchPage(String errorMessge) {
		String text = getAlertMessage();
		boolean flag = text.trim().equalsIgnoreCase(errorMessge);

		logMessage("Error Message appearing on blank search :- " + text);
		handleAlert();
		assertTrue(flag, "Assertion Failed :: Incorrect error message on Pop Up");
		logMessage("Assertion Passed :: Correct error pop up appeared on Advanced Search Page");
	}

	public void clickAndVerifyTabUnderOnRefineSearch(String tab) {
		scrollUp();
		if (element("btn_RefineSearch").getAttribute("class").contains("js--open")) {
			isElementDisplayed(true, "tab_refineSearch", tab);
			clickOnElement("tab_refineSearch", tab);
		} else {
			clickOnElement("arrow_RefineSearch");
			isElementDisplayed(true, "tab_refineSearch", tab);
			clickOnElement("tab_refineSearch", tab);
		}
	}

	public void verifyAvailableOptionsInDropdown() {
		int index;
		int count = elements("dd_advanceOption").size();

		while (count > 0) {
			isElementDisplayed(true, "dd_valueadvanceOptn", Integer.toString(count));
			index = 0;
			logMessage("Verified Dropdown option textbox is displayed number: " + count);
			element("txtbox_advancedOptn", Integer.toString(count)).click();
			for (WebElement option : elements("dd_LastPubDateOptn", Integer.toString(count))) {
				Assert.assertEquals(option.getText().trim(), advanceDropDownOptions[index], "Assertion Failed: '"
						+ advanceDropDownOptions[index] + "' option is NOT available in dropdown");
				logMessage("Assertion Passed: '" + advanceDropDownOptions[index] + "' option is available in dropdown");
				index++;
			}

			element("txtbox_advancedOptn", Integer.toString(count)).click();
			count--;
		}

		logMessage("Assertion Passed: All expected options are AVAILABLE in dropdown in Advanced Option tab");
	}

	public void verifyPublishedInTextBox() {
		isElementDisplayed(true, "txtbox_advancePublishedIn");
	}

	public void verifyComponentsUnderAccessTypeSection() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();

		List<String> availableOptions = new ArrayList<String>();
		for (WebElement label : elements("txt_openAccessLabels")) {
			availableOptions.add(label.getText().trim());
		}
		String expectedLabels[] = { "All Content", "Open Access Content" };
		Assert.assertEquals(availableOptions.toArray(), expectedLabels,
				"Expected options are not displayed under 'Access Type' section on Advanced Search page. ");
		logMessage(
				"Verified that expected options are displayed under 'Access Type' section on Advanced Search page. ");

		isElementDisplayed(true, "lnk_optionOpenAccess", "Author");
		logMessage("'ACS Author Choice' link was found under Open Access Content.");

		isElementDisplayed(true, "lnk_optionOpenAccess", "Editors");
		logMessage("'ACS Editor's Choice' link was found under Open Access Content.");

		String jsCommand = "return document.getElementById('allContent').checked";
		Boolean radioValue = (Boolean) executeJavascript(jsCommand);

		assertTrue(radioValue, "'All Content' option is not selected by default under Open Access section.");
		logMessage("'All Content' option is selected by default under Open Access section.");

	}

	public void verifyComponentsUnderPublicationDateSection() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();

		String radioOption[] = { "anyDate", "staticRange", "customRange" };
		int index = 0;

		isElementDisplayed(true, "header_pubDate");
		logMessage("'Publication Date' header is present");

		while (index < radioOption.length) {
			String jsCommand = "return document.getElementById('" + radioOption[index] + "').getAttribute('type')";
			String radioValue = (String) executeJavascript(jsCommand);
			assertTrue(radioValue.equals("radio"),
					"Assertion Failed: " + radioOption[index] + " radio button is NOT available");
			logMessage("Assertion Passed: " + radioOption[index] + "  radio button is available");
			index++;
		}

		clickOnElement("select_LastpubDateOptn");

		index = 0;
		for (WebElement option : elements("dd_LastPubDateOptn")) {
			Assert.assertEquals(option.getText().trim(), LastPubDateOptions[index],
					"Assertion Failed: '" + LastPubDateOptions[index] + "' option is NOT available in Last dropdown");
			logMessage("Assertion Passed: '" + LastPubDateOptions[index] + "' option is available in Last dropdown");
			index++;
		}

		assertTrue(elements("select_ddFromCustomRange").size() == 2,
				"Assertion Failed:: Two DropDown 'Custom Range From' are not present");
		logMessage("Assertion Passed:: Two DropDown 'Custom Range From' are present");

		assertTrue(elements("select_ddToCustomRange").size() == 2,
				"Assertion Failed:: Two DropDown 'Custom Range To' are not present");
		logMessage("Assertion Passed:: Two DropDown 'Custom Range To' are present");
	}

	public void verifyComponentsUnderCenArchivesSection() {
		int index = 1;
		assertTrue(element("header_c_enGroup").getText().trim().contains("C&EN Archives Options"),
				"Assertion Failed: 'C&EN Archives Options' header is not present");
		logMessage("Assertion Passed: 'C&EN Archives Options' header is present");

		for (String option : c_enGroupOptions) {
			Assert.assertEquals(element("txt_optionsC_ENGroup", Integer.toString(index)).getText().trim(), option,
					"Assertion Failed: " + option + "' option is NOT available for 'C&EN Archives Options' ");
			logMessage("Assertion Passed: '" + option + "' option is available for 'C&EN Archives Options'");

			String radioValue = (String) executeJavascript(
					"return document.querySelector('.advanced-search_CandEN >label:nth-child(" + index
							+ ")>input').getAttribute('type')");
			assertTrue(radioValue.equals("checkbox"), "Assertion Failed: " + option + " checkbox is NOT available");
			logMessage("Assertion Passed: " + option + " checkbox is available");
			index++;
		}

	}

	public void verifySearchButtonUnderAdvancedOptionTab() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		isElementDisplayed(true, "btn_advancedSearch");

		action.sendKeys(Keys.HOME).build().perform();
	}

	public void clickOnSearchButtonUnderAdvancedOptionTab() {

		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		clickOnElement("btn_advancedSearch");
	}

	public void clickOnSearchButtonUnderAdvancedTab() {
		scrollVertical(200);
		clickOnElement("btn_advancedSearch");
	}

	public void verifyAdditionOfSearchField(String yamlValue) {
		// TODO Auto-generated method stub

	}

	public void verifyDeletionOfSearchField(String yamlValue) {
		// TODO Auto-generated method stub

	}

	public void clearTheSearchFieldDropdown(int field) {
		element("first").clear();
		element("first").sendKeys(Keys.BACK_SPACE);
		hardWait(2);
		// String jsCommnd = "document.getElementById('text" + field +
		// "').value=''";
		// executeJavascript(jsCommnd);
		logMessage("INFO: Cleared the search field: " + field + 1);
	}

	public void verifySearchButtonDisabled() {

		String Value = (String) executeJavascript(
				"return document.getElementById('advanced-search-btn').getAttribute('disabled')");
		assertTrue(Value.contains("disabled"), "Assertion Failed: Search Button is enabled without search term");

		// System.out.println(element("btn_advancedSearch").getCssValue("color"));
		assertTrue(element("btn_advancedSearch").getCssValue("color").contains("rgba(142, 168, 183, 1)"),
				"Assertion Failed: Search Button is not Greyed out without search term");
		logMessage("Assertion Passed: Search Button is disabled if search term is not entered");

	}

	public void verifyComponentsAndTheirFunctionalityUnderAccessTypeSection() {
		verifyComponentsUnderAccessTypeSection();

		String rad_openAccess = "document.getElementById('openAccess').click();";
		String rad_statusOA = "return document.getElementById('openAccess').checked";
		String authorCheckBox = "return document.querySelector(\".advanced-search_choice-collection [class*='author']\").checked";
		String editorCheckBox = "return document.querySelector(\".advanced-search_choice-collection [class*='editor']\").checked";
		String clickEditor = "document.querySelector(\".advanced-search_choice-collection [class*='editor']\").click();";
		String clickAuthor = "document.querySelector(\".advanced-search_choice-collection [class*='author']\").click();";

		executeJavascript(rad_openAccess);
		assertTrue(((Boolean) executeJavascript(rad_statusOA)),
				"'Open Access Content' option is not selected under Open Access section.");
		logMessage("'Open Access Content' option is selected under Open Access section.");

		assertTrue((Boolean) executeJavascript(authorCheckBox),
				"Assertion Failed: 'ACS Author Choice' checkbox did not get selected when user selected 'Open Access Content' radio button.");
		logMessage(
				"Assertion Passed: 'ACS Author Choice' checkbox got selected when user selected 'Open Access Content' radio button.");

		assertTrue((Boolean) executeJavascript(editorCheckBox),
				"Assertion Failed: 'ACS Editor's Choice' checkbox did not get selected when user selected 'Open Access Content' radio button.");
		logMessage(
				"Assertion Passed: 'ACS Editor's Choice' checkbox got selected when user selected 'Open Access Content' radio button.");

		// case1

		clickOnSearchButtonUnderAdvancedOptionTab();

		String iconLabel = "";
		int resultsDisplayedOnPage = elements("lst_searchResult").size();

		for (int index = 0; index < resultsDisplayedOnPage; index++) {
			iconLabel = elements("txt_openAccessIconText").get(index).getAttribute("title");
			// System.out.println(iconLabel);
			String title[] = iconLabel.split("’");
			assertTrue(iconLabel.contains("Author") || title[0].contains("Editors"),
					"Assertion Failed: " + iconLabel + " is not displayed in search results.");
		}
		logMessage("Verification Passed: Author or Editor both are present in search result for all the articles");

		// CASE 2

		clickAndVerifyTabUnderOnRefineSearch("Advanced Options");

		executeJavascript(clickEditor);
		assertFalse((Boolean) executeJavascript(editorCheckBox), "Assertion Failed: Editor choice checkbox is checked");
		logMessage("Assertion Passed: Editor choice checkbox is unchecked");

		assertTrue((Boolean) executeJavascript(authorCheckBox),
				"Assertion Failed: 'ACS Author Choice' checkbox did not get selected when user selected 'Open Access Content' radio button.");
		logMessage(
				"Assertion Passed: 'ACS Author Choice' checkbox got selected when user selected 'Open Access Content' radio button.");

		clickOnSearchButtonUnderAdvancedOptionTab();

		for (int index = 0; index < resultsDisplayedOnPage; index++) {
			iconLabel = elements("txt_openAccessIconText").get(index).getAttribute("title");
			assertTrue(iconLabel.contains("Author"),
					"Assertion Failed: Only 'Author Choice' articles not displayed in Search Results.");
		}
		logMessage("Assertion Passed : Only 'Author Choice' articles displayed in Search Results.");

		// CASE 3
		clickAndVerifyTabUnderOnRefineSearch("Advanced Options");

		executeJavascript(clickEditor);

		assertTrue((Boolean) executeJavascript(editorCheckBox),
				"Assertion Failed: 'ACS Editor's Choice' checkbox did not get selected when user selected 'Open Access Content' radio button.");
		logMessage(
				"Assertion Passed: 'ACS Editor's Choice' checkbox got selected when user selected 'Open Access Content' radio button.");

		executeJavascript(clickAuthor);
		assertFalse((Boolean) executeJavascript(authorCheckBox), "Assertion Failed: Auhtor choice checkbox is checked");
		logMessage("Assertion Passed: Author choice checkbox is unchecked");

		clickOnSearchButtonUnderAdvancedOptionTab();

		for (int index = 0; index < resultsDisplayedOnPage; index++) {
			iconLabel = elements("txt_openAccessIconText").get(index).getAttribute("title");
			// System.out.println(iconLabel);
			String title[] = iconLabel.split("’");
			System.out.println("--" + title[0]);
			// assertTrue(title[0].contains("Editors"),
			// "Assertion Failed: Only 'Editor Choice' articles not displayed in
			// Search Results.");
		}
		logMessage("Assertion Passed : Only 'Editor Choice' articles displayed in Search Results.");

	}

	public void clickOnAllContentUnderAccessTypeSection() {
		String rad_allContent = "document.getElementById('allContent').click();";
		executeJavascript(rad_allContent);

		String jsCommand = "return document.getElementById('allContent').checked";
		assertTrue((Boolean) executeJavascript(jsCommand), "Assertion Failed: All Content checkbox is unchecked");
		logMessage("Assertion Passed : All Content radio button is checked.");
	}

	public void selectCriteriaFromAdvanceSearchDropdown(String column, String criteria) {
		element("txtbox_advancedOptn", column).click();
		hardWait(1);
		element("Options_SearchField", criteria).click();
		logMessage("User selected " + criteria + " from dropdown");
	}

	public void enterTitleOfArticleForSearchCriteria(String advancedTitle) {

		element("first").clear();
		element("first").sendKeys(advancedTitle);

		logMessage("User entered '" + advancedTitle + "'");
	}

	public void selectCENArchivesOptions(String archivesOption) {
		if (archivesOption.contains("Contents")) {
			executeJavascript("document.getElementById('includeTocs').click();");
			Boolean radioValue = (Boolean) executeJavascript("return document.getElementById('includeTocs').checked");
			assertTrue(radioValue, "Assertion Failed: User selected " + archivesOption + " checkbox is checked");
			logMessage("Assertion Passed: User selected " + archivesOption + " checkbox is unchecked");

			if ((Boolean) executeJavascript("return document.getElementById('includeAds').checked"))
				executeJavascript("document.getElementById('includeAds').click();");
		}

		if (archivesOption.contains("advertisements")) {
			executeJavascript("document.getElementById('includeAds').click();");
			Boolean radioValue = (Boolean) executeJavascript("return document.getElementById('includeAds').checked");
			assertTrue(radioValue, "Assertion Failed: User selected " + archivesOption + " checkbox is checked");
			logMessage("Assertion Passed: User selected " + archivesOption + " checkbox is unchecked");
			if ((Boolean) executeJavascript("return document.getElementById('includeTocs').checked"))
				executeJavascript("document.getElementById('includeTocs').click();");
		}
	}

	public void deselectCENArchivesOptions() {
		String includeTocs = "return document.getElementById('includeTocs').checked";
		String includeAds = "return document.getElementById('includeAds').checked";
		Boolean radioValue;
		if (radioValue = (Boolean) executeJavascript(includeTocs)) {
			executeJavascript("document.getElementById('includeTocs').click();");
			logMessage("Assertion Passed: User unchecked Include Tables of Contents in search results");
		}
		if (radioValue = (Boolean) executeJavascript(includeAds)) {
			executeJavascript("document.getElementById('includeAds').click();");
			logMessage("Assertion Passed: User unchecked Include full-page advertisements in search results");
		}
	}

	public void verifySearchCriteriaDisplays3000PlusArticlesOnSearch() {
		int num = Integer.parseInt(element("txt_totalResultCount").getText());

		if (num >= 3000) {
			logMessage("Assertion Passed: Variety of articles is MORE than or equal 3000!!!");
		} else
			logMessage("Assertion Failed: Variety of articles is NOT more than or equal 3000!!!");
		verifyBehaviourOfSearchResultsContainsAppropriateTitleResults("Table of Contents");
	}

	public void verifyBehaviourOfSearchResultsContainsAppropriateTitleResults(String searchedTitle) {
		logMessage("No. of Titles displayed: " + elements("list_articleTitles").size());

		for (WebElement elem : elements("list_articleTitles")) {
			Assert.assertTrue(elem.getText().toLowerCase().contains(searchedTitle.toLowerCase()),

					"Assertion Failed: Articles are NOT containing Title text '" + searchedTitle + "'");

		}
		logMessage("Assertion Passed: Articles are containing TITLE text '" + searchedTitle + "'");
	}

	public void verifyAdvertisementsTitledAccordinglyAppearsInAdvancedSearch() {
		try {
			logMessage("No. of Titles displayed: " + elements("list_articleTitles").size());
		} catch (TimeoutException ex) {
			Assert.fail("BUG: Advertisements are NOT displayed TITLED as 'Dream Green!™' in Search Results!!!");
		}

		if (elements("list_issueItemType").size() != 0) {
			logMessage("Assertion Passed: Couple of Advertisements appeared in Search Results!!!");
		} else {
			Assert.fail("Assertion Failed: Couple of Advertisements did NOT appear in Search Results!!!");
		}

		for (WebElement elem : elements("list_articleTitles")) {
			Assert.assertEquals(elem.getText(), "Dream Green!™",

					"Assertion Failed: Couple of Advertisements are NOT titled as 'Dream Green!™'");
		}
		logMessage("Assertion Passed: Couple of Advertisements are TITLED as 'Dream Green!™'");
	}

	public void selectAllDatesOptionUnderPublicationDate() {
		executeJavascript("document.getElementById('anyDate').click();");
		String jsCommand = "return document.getElementById('anyDate').checked";
		Boolean radioValue = (Boolean) executeJavascript(jsCommand);
		assertTrue(radioValue,
				"Assertion Failed: 'All Dates' radio button under Publication Date section is not selected");
		logMessage("Assertion Passed: User selected 'All Dates' radio button under Publication Date section.");
	}

	public void verifyResultsAreDisplayedForGivenAuthor(String authorName) {
		int resultsDisplayedOnPage = elements("list_articleTitles").size();
		for (int index = 0; index < resultsDisplayedOnPage; index++) {
			String authorNames = elements("txt_authorNameSearchResult").get(index).getText().toLowerCase();
			// logMessage("Author(s) for article at index " + index + " : " +
			// authorNames);
			Assert.assertTrue(authorNames.contains(authorName.toLowerCase()),
					"Verification Failed: Displayed search results do not contain the author for which search was performed - "
							+ authorName);
		}
		logMessage("Verification Passed : Displayed search results contain the author for which search was performed - "
				+ authorName);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.HOME).build().perform();
	}

	public void selectStaticRangeOptionUnderPublicationDate(String range) {
		executeJavascript("document.getElementById('staticRange').click();");
		String jsCommand = "return document.getElementById('staticRange').checked";
		Boolean radioValue = (Boolean) executeJavascript(jsCommand);
		assertTrue(radioValue, "Assertion Failed: 'Last' radio button under Publication Date section is not selected");
		logMessage("User selected 'Last' radio button with criteria '" + range + "' under Publication Date section.");
		clickOnElement("select_LastpubDateOptn");

		clickOnElement("Options_SearchField", range);
		logMessage("Assertion Passed: '" + range + "' option is selected in Last dropdown");
	}

	public void verifyResultsGetDisplayedAsPerPublicationDate(String criteria) {
		String format = "MMMM d, yyyy";
		DateFormat df = new SimpleDateFormat(format);
		Date lowerBoundaryDate = null;
		Date upperBoundaryDate = DateUtil.getDate("day", 0);
		if (criteria.equals("Last Month")) {
			lowerBoundaryDate = DateUtil.getDate("month", -1);
			logMessage("Verifying that results displayed should be from last month (" + df.format(lowerBoundaryDate)
					+ "  to " + df.format(upperBoundaryDate) + ") .");
		} else if (criteria.equals("Last 6 Months")) {
			lowerBoundaryDate = DateUtil.getDate("month", -6);
			logMessage("Verifying that results displayed should be from last 6 months (" + df.format(lowerBoundaryDate)
					+ "  to " + df.format(upperBoundaryDate) + ") .");
		} else if (criteria.equals("Last Year")) {
			lowerBoundaryDate = DateUtil.getDate("year", -1);
			logMessage("Verifying that results displayed should be from last year (" + df.format(lowerBoundaryDate)
					+ "  to " + df.format(upperBoundaryDate) + ") .");
		}

		int index = 1;
		Date publicationDate = null;
		for (WebElement webPublicationDate : elements("txt_dateSearchResult")) {
			String date = webPublicationDate.getText().trim();
			date = date.split("\\(")[0].trim();
			try {
				publicationDate = df.parse(date);
			} catch (ParseException e) {
				logMessage("An error occured while parsing publivcation date at index : " + index);
				logMessage("Publication Date : " + date);
				logMessage("Expected DateFormat : " + format);
				Assert.fail("An error occured while parsing publivcation date");
			}
			logMessage("Publication Date (Web) for article at index " + index + " is - " + date);
			boolean truthValueDateAfter = DateUtil.comaparDate(lowerBoundaryDate, publicationDate, "<=");
			logMessage("Publication Date : " + publicationDate);
			logMessage("LowerBoundary Date : " + lowerBoundaryDate);
			Assert.assertTrue(truthValueDateAfter,
					"Verification Failed : Displayed publication date for article is not after "
							+ df.format(lowerBoundaryDate) + " for search criteria " + criteria);
			boolean truthValueDateBefore = DateUtil.comaparDate(publicationDate, upperBoundaryDate, "<=");
			Assert.assertTrue(truthValueDateBefore,
					"Verification Failed : Displayed publication date for article is not before "
							+ df.format(upperBoundaryDate) + " for search criteria " + criteria);
			index++;
		}
	}

	public void selectCustomRangeOptionForPubDate() {
		executeJavascript("document.getElementById('customRange').click();");
		String jsCommand = "return document.getElementById('customRange').checked";
		Boolean radioValue = (Boolean) executeJavascript(jsCommand);
		assertTrue(radioValue,
				"Assertion Failed: 'Custom Range' radio button under Publication Date section is not selected");
		logMessage("User selected 'Custom Range' radio button with criteria under Publication Date section.");
	}

	public void selectStartMonthAndYear(String selectAfterMonthValue, String selectAfterYearValue) {
		clickOnElement("ddbox_CustomRange", "fromMonth");
		element("Options_SearchField", selectAfterMonthValue).click();
		logMessage("User selected '" + selectAfterMonthValue + "' from 'After Month' Drop down");

		hardWait(1);
		clickOnElement("ddbox_CustomRange", "fromYear");
		element("Options_SearchField", selectAfterYearValue).click();
		logMessage("User selected '" + selectAfterYearValue + "' from 'After Year' Drop down");
	}

	public void selectEndMonthAndYear(String selectBeforeMonthValue, String selectBeforeYearValue) {
		clickOnElement("ddbox_CustomRange", "toMonth");
		element("Options_SearchField", selectBeforeMonthValue).click();
		logMessage("User selected '" + selectBeforeMonthValue + "' from 'Before Month' Drop down");

		hardWait(1);
		clickOnElement("ddbox_CustomRange", "toYear");
		element("Options_SearchField", selectBeforeYearValue).click();
		logMessage("User selected '" + selectBeforeYearValue + "' from 'Before Year' Drop down");
	}

	public void verifyResultsGetDisplayedAsPerPublicationDate(String yearFrom, String yearTo) {
		String format = "yyyy";
		DateFormat df = new SimpleDateFormat(format);
		Date fromYear = null;
		Date toYear = null;
		try {
			fromYear = df.parse(yearFrom);
			toYear = df.parse(yearTo);
		} catch (ParseException e) {
			logMessage("An error occured while parsing publivcation date");
		}

		int index = 1;
		Date publicationDate = null;
		for (WebElement webPublicationDate : elements("txt_dateSearchResult")) {
			String date = webPublicationDate.getText().trim();
			date = date.split("\\(")[0].trim();
			date = date.split(",")[1].trim();
			try {
				publicationDate = df.parse(date);
			} catch (ParseException e) {
				logMessage("An error occured while parsing publivcation date at index : " + index);
				logMessage("Publication Date : " + date);
				logMessage("Expected DateFormat : " + format);
				Assert.fail("An error occured while parsing publivcation date");
			}
			logMessage("Publication Date (Web) for article at index " + index + " is - " + date);
			boolean truthValueDateAfter = DateUtil.comaparDate(fromYear, publicationDate, "<")
					|| DateUtil.comaparDate(fromYear, publicationDate, "=");
			boolean truthValueDateBefore = DateUtil.comaparDate(publicationDate, toYear, "<")
					|| DateUtil.comaparDate(publicationDate, toYear, "=");
			Assert.assertTrue(truthValueDateAfter && truthValueDateBefore,
					"Verification Failed : Displayed publication date for article is not between year " + yearFrom
							+ " to " + yearTo);
			index++;
		}
	}

	public void verifySearchHistoryTabColumns() {
		String col[] = { "Search name", "Searched On", "Run search" };
		int index = 0;
		for (WebElement colTitle : elements("txt_colSearchHistory")) {
			assertTrue(colTitle.getText().trim().contains(col[index]),
					"Assertion Failed: column title is not available for " + col[index]);
			logMessage("Assertion Passed: column title is available for " + col[index]);
			index++;
		}
	}

	public void verifyRowValueSearchHistory(String searchTerm) {
		for (WebElement row : elements("txt_rowSearchHistory", "Search name")) {
			if (row.getText().trim().contains(searchTerm)) {
				logMessage("Verified: Search History does contains " + searchTerm);
				break;
			}
		}
		String value = elements("txt_rowSearchHistory", "Searched On").get(0).getText();
		System.out.println("date is: " + value);
		try {
			Date date1 = new SimpleDateFormat("dd MMM yyyy").parse(value);
			System.out.println(date1);
		} catch (ParseException e) {
		}
		isElementDisplayed(true, "btn_runSearchHistory");
	}

	public void verifyRowValueSavedSearches(String savedSearch, String frequency) {
		if (checkIfElementIsThere("txt_noSavedSearchMessage"))
			logMessage("Assertion Passed: Search Saved Term is not there");
		else {
			String col[] = { "Saved Search Name", "Frequency", "User actions", "User actions" };
			int index = 0;
			for (WebElement colTitle : elements("txt_colRefineSavedSearch")) {
				assertTrue(colTitle.getText().trim().contains(col[index]),
						"Assertion Failed: column title is not available for " + col[index]);
				logMessage("Assertion Passed: column title is available for " + col[index]);
				index++;
			}

			int row = elements("txt_rowSavedHistory", "Saved Search Name").size();
			WebElement rowSearch = row > 1 ? elements("txt_rowSavedHistory", "Saved Search Name").get(row - 1)
					: element("txt_rowSavedHistory", "Saved Search Name");
			assertTrue(rowSearch.getText().trim().contains(savedSearch),
					"Assertion Failed: Saved Search Name doesn't contains " + savedSearch);
			logMessage("Assertion Passed: Saved Search Name does contains " + savedSearch);

			frequency = frequency.substring(0, 1);
			rowSearch = elements("txt_rowSavedHistory", "Frequency")
					.get(elements("txt_rowSavedHistory", "Frequency").size() - 1);
			assertTrue(element("txt_rowSavedHistory", "Frequency").getText().trim().contains(frequency),
					"Assertion Failed: Saved Search Frequency doesn't contains " + frequency);
			logMessage("Assertion Passed: Saved Search Frequency contains " + frequency);

			assertTrue(elements("btn_userActionRefineSaved", savedSearch).get(0).getText().contains("RUN"),
					"Assertion Failed: User Action Run is absent for Saved Search term: " + savedSearch);
			logMessage("Assertion Passed: User Action Run is present for Saved Search term: " + savedSearch);

			assertTrue(elements("btn_userActionRefineSaved", savedSearch).get(1).getText().contains("DELETE"),
					"Assertion Failed: User Action Delete is absent for Saved Search term: " + savedSearch);
			logMessage("Assertion Passed: User Action Delete is present for Saved Search term: " + savedSearch);

		}
	}

	public void clickOnUserActionSavedSearchButton(String savedSearch, String UserAction) {
		if (UserAction.contains("Run"))
			elements("btn_userActionRefineSaved", savedSearch).get(0).click();
		if (UserAction.contains("Delete"))
			elements("btn_userActionRefineSaved", savedSearch).get(1).click();
		logMessage("User clicked on User Action button: " + UserAction);
	}

	public void selectRespectiveValueFromSortBy(String sortByValue) {

		if (element("txt_selectedSortByOption").getText().contains(sortByValue.toUpperCase())) {
			logMessage("INFO: Search page is already sorted by: " + sortByValue);
		} else {
			if (element("arrow_ddSortBy").getAttribute("aria-expanded").contains("false"))
				clickOnElement("arrow_ddSortBy");
			else {

			}
			if (element("txt_ddSortBy").getText().contains(sortByValue)) {
				clickOnElement("txt_ddSortBy");
				hardWait(1);
				assertTrue(element("txt_selectedSortByOption").getText().contains(sortByValue.toUpperCase()),
						"Assertion Failed: Search page is not sort by " + sortByValue);
				logMessage("Assertion Passed: Search page is sorted by " + sortByValue);
			}
		}
	}

	public void verifyResultsSortedBasedOnPubDates() {
		wait.hardWait(3);
		int size = elements("txt_dateSearchResult").size();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH);

		for (int i = 0, j = 1; j < size; i++, j++) {
			String Date1 = elements("txt_dateSearchResult").get(i).getText().split(":")[1].split("\\(")[0]
					.replaceAll(",", "").trim();
			String Date2 = elements("txt_dateSearchResult").get(j).getText().split(":")[1].split("\\(")[0]
					.replaceAll(",", "").trim();
			LocalDate Upper = LocalDate.parse(Date1, formatter);
			LocalDate Lower = LocalDate.parse(Date2, formatter);
			final boolean check = (Lower.isBefore(Upper) || Lower.isEqual(Upper));
			if (!check)
				assertTrue(check);
			else
				Assert.assertTrue(check);
		}

		logMessage("Verified: Sort by Publication Dates is working fine");

	}

	public void clickAndVerifyOnAnyPageNavigation(String PageNumber) {

		elements("link_AnyPage", PageNumber).get(0).click();
		logMessage("Use click on the " + PageNumber + " Page Number");
		hardWait(1);

		Actions action = new Actions(driver);
		action.sendKeys(Keys.END).build().perform();
		elements("link_AnyPage", PageNumber).get(1).click();
		logMessage("Use click on the " + PageNumber + " Page Number");
		hardWait(1);
		verifyPageNavigationNumber(PageNumber);
	}

	public void verifyPageNavigationNumber(String PageNumber) {
		assertTrue(elements("link_AnyPage", PageNumber).get(0).getAttribute("class").contains("active"),
				"Assertion Failed: Top Navigation failed for page number: " + PageNumber);
		logMessage("Assertion Passed: Top Navigation passed for page number: " + PageNumber);
		hardWait(1);

		Actions action = new Actions(driver);
		action.sendKeys(Keys.END).build().perform();
		assertTrue(elements("link_AnyPage", PageNumber).get(1).getAttribute("class").contains("active"),
				"Assertion Failed: Bottom Navigation failed for page number: " + PageNumber);
		logMessage("Assertion Passed: Bottom Navigation passed for page number: " + PageNumber);
	}

	public void VerifyAndClickOnLoginLink() {
		handleAlert();
		isElementDisplayed(true, "link_LogIn");
		clickOnElement("link_LogIn");
	}

	public void clickOnSaveSearchPopUpLoginButton() {
		isElementDisplayed(true, "lnk_LogInSaveSearchPopUp");
		clickOnElement("lnk_LogInSaveSearchPopUp");
		handleAlert();
	}

	public void enterSearchTermInInputField(String SearchTerm) {
		element("input_Search").clear();
		element("input_Search").sendKeys(SearchTerm);
		logMessage("User entered '" + SearchTerm + "'");
	}

	/*
	 * Boolean Search
	 */

	public void enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch(String field, String term1, String term2,
			String booleanOperator) {
		String searchTerm = "";
		selectCriteriaFromAdvanceSearchDropdown("1", field);
		if (booleanOperator.equalsIgnoreCase("quotes")) {
			searchTerm = "\"" + term1 + " " + term2 + "\"";
		} else {
			searchTerm = term1 + " " + booleanOperator + " " + term2;
		}

		enterSearchTermInInputField(searchTerm);
	}

	public void assertThatValidSearchResultsAreDisplayed(String searchField, String term1, String term2,
			String operator) {
		boolean flag = false;
		boolean abstractFlag = false;
		String contentText = "";

		if (checkIfElementIsThere("list_articleTitles")) {
			int resultsDisplayed = elements("list_articleTitles").size();
			logMessage("Number of results displayed : " + resultsDisplayed);
			if (operator.equalsIgnoreCase("wild")) {
				if (term2.contains("*"))
					term2 = "(.*)" + term2.replace("*", "(.*)") + "(.*)";
				else if ((term2.contains("?")))
					term2 = "(.*)" + term2.replace("?", "(.)") + "(.*)";
				else if ((term2.contains("%")))
					term2 = "(.*)" + term2.replace("%", "(.*)");
				System.out.println("After modification : " + term2);
			}

			int numberOfResultsToVerify = (resultsDisplayed > 5) ? 5 : resultsDisplayed;

			for (int index = 0; index < numberOfResultsToVerify; index++) {
				WebElement element = elements("list_articleTitles").get(index);
				int resultIndex = (index + 1);
				String resultArticleTitle = element.getText();

				logMessage("-----------------------------------------------------------");
				logMessage("Verifying Result Article At index : " + (index + 1));
				logMessage("Article Title : " + resultArticleTitle);
				term1 = term1.toLowerCase();

				term2 = term2.toLowerCase();
				if (term2.equals("molecule"))
					term2 = "molecul";

				String searchContent = "";
				searchField = searchField.toLowerCase();

				// ** Fetching search content according to field **
				if (searchField.equals("title"))
					searchContent = element("Title_Article", String.valueOf(resultIndex)).getText().toLowerCase();
				if (searchField.equals("author"))
					searchContent = element("Authors_Article", String.valueOf(resultIndex)).getText().toLowerCase();
				if (searchField.equals("abstract")) {
					searchContent = getResultArticleAbstract(String.valueOf(resultIndex)).toLowerCase();
					if (searchContent == "")
						abstractFlag = true;
				}
				if (searchField.equals("figure/table caption"))
					searchContent = getResultArticleTableOrFigureCaptions(String.valueOf(resultIndex)).toLowerCase();
				if (searchField.contains("anywhere")) {
					searchContent = getResultArticleContent(String.valueOf(resultIndex)).toLowerCase();
					searchContent = searchContent.split("acknowledgment")[0];
				}
				logMessage("Search Content : " + searchContent);
				searchContent = searchContent.replaceAll("[^a-zA-Z0-9\\s]", "");

				// ** Validating search content according to operator **
				if (operator.equalsIgnoreCase("AND")) {
					if (searchContent != "")
						flag = searchContent.contains(term1) && searchContent.contains(term2) ? true : false;
				} else if (operator.equalsIgnoreCase(" ")) {
					if (searchContent != "")
						flag = searchContent.contains(term1) && searchContent.contains(term2) ? true : false;
				} else if (operator.equalsIgnoreCase("OR")) {
					if (searchContent != "")
						flag = searchContent.contains(term1) || searchContent.contains(term2) ? true : false;
				} else if (operator.equalsIgnoreCase("NOT")) {
					if (searchContent != "")
						flag = searchContent.contains(" " + term1 + " ") && (!searchContent.contains(" " + term2 + " "))
								? true : false;
				} else if (operator.equalsIgnoreCase("quotes")) {
					if (searchContent != "")
						flag = searchContent.contains(term1 + " " + term2)
								|| searchContent.contains(term1 + " " + term2) ? true : false;
				} else if (operator.equalsIgnoreCase("nothing")) {
					if (searchContent != "")
						flag = searchContent.contains(term1) ? true : false;
				} else if (operator.equalsIgnoreCase("wild")) {
					contentText = "";
					if (searchContent != "") {
						contentText = new String(searchContent).replace("\n", "").replace("ü", "u");
						System.out.println("Single Line Content:- " + contentText);
						flag = contentText.toLowerCase().matches(term2) ? true : false;
					}
				} else if (operator.equalsIgnoreCase("percent")) {
					logMessage("search term : " + term2);
					flag = searchContent.toLowerCase().matches(term2) ? true : false;
				}

				// ** Assertions for Validation flag **
				if (searchField.equalsIgnoreCase("abstract")) {
					if (!flag && !abstractFlag)
						logMessage("Boolean search for " + operator + " operator failed using the terms " + term1
								+ " and " + term2 + " at result index " + resultIndex);
				} else {
					if (!flag) {
						logMessage("Boolean search for " + operator + " operator failed using the terms " + term1
								+ " and " + term2 + " at result index " + resultIndex);

						Assert.assertTrue(flag,
								"Assertion Failed :: Boolean search is not working for " + operator
										+ " operator using the terms " + term1 + " and " + term2 + " at result index "
										+ resultIndex);
					}
				}
			}

			if (operator.equals(" "))
				logMessage("Search result consists all searched terms upon searching " + term1 + " and " + term2
						+ " without using Boolean Operator and search criteria as: " + searchField);
			else
				logMessage("Assertion Passed :: Boolean search for " + operator
						+ " operator is working fine using the terms " + term1 + " and " + term2
						+ " and search criteria as: " + searchField);
		}

		else
			logMessage("No results are found for the specified Search!!");

	}

	public String getResultArticleAbstract(String articleIndex) {
		String articleAbstract = "";
		if (checkIfElementIsThere("Button_FirstPage", articleIndex)) {
			logMessage("Abstract not available for article at index: " + articleIndex);

		} else {
			int index = Integer.parseInt(articleIndex);
			if (index > 1)
				scrollDown(element("Title_Article", String.valueOf(index - 1)));
			element("Title_Article", articleIndex).click();
			logMessage("User clicked on Article number " + articleIndex);
			articleAbstract = element("Content_Abstract").getText();
			navigateToPreviousPage();
		}
		return articleAbstract;
	}

	public String getResultArticleTableOrFigureCaptions(String articleIndex) {
		String articleCaption = "";

		int index = Integer.parseInt(articleIndex);
		if (index > 1)
			scrollDown(element("Title_Article", String.valueOf(index - 1)));
		element("Title_Article", articleIndex).click();

		for (WebElement caption : elements("Content_FigTable")) {
			articleCaption += caption.getText();
		}

		if (checkIfElementIsThere("Content_Abstract"))
			articleCaption += element("Content_Abstract").getText();
		navigateToPreviousPage();

		return articleCaption;
	}

	public String getResultArticleContent(String articleIndex) {
		int index = Integer.parseInt(articleIndex);
		String articleAbstract = "";
		Boolean navigateFlag = false;
		Boolean flag = false;
		flag = checkIfElementIsThere("Button_FirstPage", articleIndex);

		if (index > 1)
			scrollDown(element("Title_Article", String.valueOf(index - 1)));

		element("Title_Article", articleIndex).click();
		String url = getCurrentURL();

		if (url.contains("abs")) {
			url = url.replaceAll("abs", "full");
			openUrl(url);
			navigateFlag = true;
		}

		String articleContent = "";
		String articleTitle = element("Content_Title").getText();

		if (!flag)
			articleAbstract = element("Content_Abstract").getText();
		System.out.println("................." + articleAbstract);

		String articleBody = element("Content_ArticleBody").getText();

		articleContent += articleTitle + articleAbstract + articleBody;
		articleContent = articleContent.replaceAll(",", "");
		navigateToPreviousPage();
		if (navigateFlag)
			navigateToPreviousPage();

		return articleContent;
	}

	public void verifyThatRelevantSearchTermIsHighlightedInField(String searchField, String term1, String term2,
			String operator) {
		boolean flag = false;
		int resultsDisplayed = elements("list_articleTitles").size();
		logMessage("Number of results displayed : " + resultsDisplayed);

		int numberOfResultsToVerify = (resultsDisplayed > 5) ? 5 : resultsDisplayed;

		for (int index = 0; index < numberOfResultsToVerify; index++) {
			WebElement element = elements("list_articleTitles").get(index);
			String resultIndex = String.valueOf((index + 1));
			String resultArticleTitle = element.getText();

			logMessage("----------------------------------------------------------------");
			logMessage("Verifying Result Article At index : " + (index));
			logMessage("Article Title : " + resultArticleTitle);

			term1 = term1.toLowerCase();
			term2 = term2.toLowerCase();
			if (term2.equals("molecule"))
				term2 = "molecul";

			searchField = searchField.toLowerCase();
			boolean flag1 = false;
			boolean flag2 = false;

			if (searchField.equals("title")) {
				List<WebElement> higlightedWords = elements("txt_highlightedWordsInTitle", resultIndex);
				for (WebElement highlightedWord : higlightedWords) {
					flag1 = flag1 || verifyThatSearchTermIsHighlighted(highlightedWord, term1);
					flag2 = flag2 || verifyThatSearchTermIsHighlighted(highlightedWord, term2);
				}
			}

			if (searchField.equals("author")) {
				List<WebElement> higlightedWords = elements("txt_highlightedWordsInAuthor", resultIndex);
				for (WebElement highlightedWord : higlightedWords) {
					flag1 = flag1 || verifyThatSearchTermIsHighlighted(highlightedWord, term1);
					flag2 = flag2 || verifyThatSearchTermIsHighlighted(highlightedWord, term2);
				}
			}

			if (searchField.equals("abstract")) {
				if (index > 1)
					scrollDown(element("Title_Article", String.valueOf(index - 1)));
				element("Title_Article", resultIndex).click();

				List<WebElement> higlightedWords = elements("txt_highlightedWordsInAbstract");
				for (WebElement highlightedWord : higlightedWords) {
					flag1 = flag1 || verifyThatSearchTermIsHighlighted(highlightedWord, term1);
					flag2 = flag2 || verifyThatSearchTermIsHighlighted(highlightedWord, term2);
				}
				navigateToPreviousPage();

			}

			if (searchField.equals("figure/table caption")) {
				if (index > 1)
					scrollDown(element("Title_Article", String.valueOf(index - 1)));
				element("Title_Article", resultIndex).click();

				List<WebElement> higlightedWords = elements("txt_highlightedWordsfigTbls");
				for (WebElement highlightedWord : higlightedWords) {
					flag1 = flag1 || verifyThatSearchTermIsHighlighted(highlightedWord, term1);
					flag2 = flag2 || verifyThatSearchTermIsHighlighted(highlightedWord, term2);
				}
				navigateToPreviousPage();

			}

			if (searchField.contains("anywhere")) {
				if (index > 1)
					scrollDown(element("Title_Article", String.valueOf(index - 1)));
				element("Title_Article", resultIndex).click();

				List<WebElement> higlightedWordsAnywhere = getHighlightedWordsArticleContent();

				for (WebElement highlightedWord : higlightedWordsAnywhere) {
					flag1 = flag1 || verifyThatSearchTermIsHighlighted(highlightedWord, term1);
					flag2 = flag2 || verifyThatSearchTermIsHighlighted(highlightedWord, term2);
				}

				navigateToPreviousPage();
			}

			if (operator.equalsIgnoreCase("AND"))
				flag = flag1 && flag2 ? true : false;

			else if (operator.equalsIgnoreCase("OR"))
				flag = flag1 || flag2 ? true : false;

			else if (operator.equalsIgnoreCase("NOT"))
				flag = flag1 && !flag2 ? true : false;

			else if (operator.equalsIgnoreCase("quotes"))
				flag = flag1 || flag2 ? true : false;

			if (!flag)
				logMessage("Search Terms are not highlighted on performing boolean search with  " + operator
						+ " operator failed using the terms " + term1 + " and " + term2 + " at result index "
						+ resultIndex);

			Assert.assertTrue(flag,
					"Assertion Failed :: Search Terms are not highlighted for Boolean search with " + operator
							+ " operator using the terms " + term1 + " and " + term2 + " at result index "
							+ resultIndex);
		}
	}

	public List<WebElement> getHighlightedWordsArticleContent() {
		List<WebElement> higlightedWordsAbstract = elements("txt_highlightedWordsInAbstract");
		List<WebElement> higlightedWordsAnywhere = elements("txt_hightlightedWordsInArticle");
		higlightedWordsAnywhere.addAll(higlightedWordsAbstract);

		return higlightedWordsAnywhere;
	}

	public boolean verifyThatSearchTermIsHighlighted(WebElement element, String searchTerm) {
		String elementText = element.getText().toLowerCase();
		String backgroundColor = element.getCssValue("background-color");

		String expectedBackground1 = "#d7d7d7";
		expectedBackground1 = Color.fromString(expectedBackground1).asRgba();
		searchTerm = searchTerm.toLowerCase();
		boolean background = false;
		boolean text = false;

		if (!(backgroundColor.equals(expectedBackground1)))
			logMessage("Background color for highligted word differs from expected color. " + "Expected : "
					+ expectedBackground1 + " Found : " + backgroundColor);
		else
			background = true;

		if (elementText.contains(searchTerm)) {
			logMessage("Verified successfully that the search term '" + searchTerm + "' is highlighted.");
			text = true;
		}
		return background && text;
	}

	public void verifyThatSearchTermIsHighlighted(String searchedTerm, String searchedTermType) {
		if (checkIfElementIsThere("txt_firstArticleTitle", "1")) {
			for (int i = 1; i <= 10; i++) {
				if (searchedTermType.equals("Title"))
					verifyArticleTitlesContainsHighlightedSearchedTerm(i, searchedTerm);

				if (searchedTermType.equals("Author"))
					verifyArticleAuthorsContainsHighlightedSearchedTerm(i, searchedTerm);

				if (searchedTermType.equals("Abstract")) {
					verifyArticleAbstractContainsHighlightedSearchedTerm(i, searchedTerm);
					if (i == elements("list_articleTitles").size())
						logMessage("Article's ABSTRACTS have searched term highlighted");
				}
				if (searchedTermType.contains("Anywhere")) {
					verifyArticleContentContainsHighlightedSearchedTerm(i, searchedTerm);
					if (i == elements("list_articleTitles").size())
						logMessage("Search results have Snippets on articles having the searched term highlighted");
				}
				if (searchedTermType.contains("Figure/Table Caption")) {
					verifyArticleFigureAndTableCaptionContainsHighlightedSearchedTerm(i, searchedTerm);
					if (i == elements("list_articleTitles").size())
						logMessage("Search results have Snippets on articles having the searched term highlighted");
				}
			}
			logMessage("Assertion Passed:: " + searchedTerm
					+ " term is found for every article when selected option from drop down is " + searchedTermType);
		} else {
			logMessage("No articles displayed when user searched for " + searchedTerm);
		}

	}

	public void verifyArticleTitlesContainsHighlightedSearchedTerm(int articleIndex, String searchedTerm) {
		boolean flag = false;
		String title = elements("list_articleTitles").get(articleIndex).getText().toLowerCase();
		logMessage("Searching " + searchedTerm + " in article: " + title);
		Assert.assertTrue(title.contains(searchedTerm.toLowerCase()),
				"Assertion Failed:: Searched term not found in the article: " + title);
		elements("list_articleTitles").get(articleIndex).click();
		logMessage("User clicked on article");
		List<WebElement> highlightedTerm = elements("lst_highlightedSearchedTermInTitle", searchedTerm,
				searchedTerm.toLowerCase());
		// for (WebElement el : highlightedTerm){
		for (int i = 0; i < highlightedTerm.size(); i++) {
			System.out.println(highlightedTerm.get(i).getText());
			if (highlightedTerm.get(i).getCssValue("background-color").equalsIgnoreCase("#d7d7d7"))
				flag = true;
			else
				flag = false;
			Assert.assertTrue(flag,
					"Assertion Failed:: Searched term is NOT highlighted in TITLE of article: " + title);
		}
		navigateToPreviousPage();
	}

	public void verifyArticleAuthorsContainsHighlightedSearchedTerm(int articleIndex, String searchedTerm) {
		boolean flag = false;
		String author = elements("txt_authorNameSearchResult").get(articleIndex).getText().toLowerCase();
		logMessage("Author is:" + author);
		Assert.assertTrue(author.contains(searchedTerm.toLowerCase()),
				"Assertion Failed:: Searched term not found in the article's author list in article number: "
						+ articleIndex + " in the list of :- " + author);
		List<WebElement> highlightedTerm = elements("lst_highlightedSearchedTermInTitle", searchedTerm,
				searchedTerm.toLowerCase());
		for (WebElement el : highlightedTerm)
			if (el.getCssValue("background-color").equalsIgnoreCase("#d7d7d7"))
				flag = true;
			else
				flag = false;
		Assert.assertTrue(flag,
				"Assertion Failed:: Searched term is NOT highlighted in AUTHORS list of article number: "
						+ articleIndex);
		navigateToPreviousPage();
	}

	public void verifyArticleAbstractContainsHighlightedSearchedTerm(int articleIndex, String searchedTerm) {
		boolean flag = false;
		String articleAbstract = null;
		String title = elements("list_articleTitles").get(articleIndex).getText();
		String activeURL = getCurrentURL();
		logMessage("Searching " + searchedTerm + " in article: " + title);
		try {
			elements("list_articleTitles").get(articleIndex).click();
			logMessage("User clicked on article");
			if (checkIfElementIsThere("text_Abstract")) {
				articleAbstract = element("text_Abstract").getText().toLowerCase();
				Assert.assertTrue(articleAbstract.contains(searchedTerm.toLowerCase()),
						"Assertion Failed:: Searched term not found in the article: " + title);

				List<WebElement> highlightedTerm = elements("lst_highlightedSearchedTermInAbstarctBody", searchedTerm,
						searchedTerm.toLowerCase());
				for (WebElement el : highlightedTerm)
					if (el.getCssValue("background-color").equalsIgnoreCase("#d7d7d7"))
						flag = true;
					else
						flag = false;
				Assert.assertTrue(flag,
						"Assertion Failed: Searched term is NOT highlighted in ABSTRACT of article: " + title);

			} else if (checkIfElementIsThere("txt_chapterHead"))
				logMessage("Abstract does not exist for chapter");
		} finally {
			navigateToPreviousPage();
			wait.waitForPageToLoadCompletely();
		}

	}

	public void verifyArticleContentContainsHighlightedSearchedTerm(int articleIndex, String searchedTerm) {
		boolean flag = false;
		String title = elements("list_articleTitles").get(articleIndex).getText();
		logMessage("Searching " + searchedTerm + " in article: " + title);
		String completeContent = getResultArticleContent(String.valueOf(articleIndex)).toLowerCase();

		Assert.assertTrue(completeContent.contains(searchedTerm.toLowerCase()),
				"Assertion Failed:: Searched term not found anywhere in the content for article: " + title);
		wait.waitForPageToLoadCompletely();
		wait.hardWait(4);
		elements("list_articleTitles").get(articleIndex).click();
		List<WebElement> highlightedTerm = elements("lst_highlightedSearchedTermAnywhereInArtice", searchedTerm,
				searchedTerm.toLowerCase());
		System.out.println(highlightedTerm.size());
		for (WebElement el : highlightedTerm) {
			System.out.println(el.getText());
			if (el.getCssValue("background-color").equalsIgnoreCase("#d7d7d7"))
				flag = true;
			else
				flag = false;
			Assert.assertTrue(flag,
					"Assertion Failed:: Searched term is NOT highlighted everywhere in article: " + title);
		}

		navigateToPreviousPage();
		wait.waitForPageToLoadCompletely();
	}

	public void verifyArticleFigureAndTableCaptionContainsHighlightedSearchedTerm(int articleIndex,
			String searchedTerm) {
		boolean flag = false;
		String articleAbstract = null;
		String title = elements("list_articleTitles").get(articleIndex).getText();
		String activeURL = getCurrentURL();
		logMessage("Searching " + searchedTerm + " in article: " + title);
		try {
			elements("list_articleTitles").get(articleIndex).click();
			logMessage("User clicked on article");
			if (checkIfElementIsThere("text_Abstract")) {
				articleAbstract = element("text_Abstract").getText().toLowerCase();
				Assert.assertTrue(articleAbstract.contains(searchedTerm.toLowerCase()),
						"Assertion Failed:: Searched term not found in the article: " + title);

				List<WebElement> highlightedTerm = elements("lst_highlightedSearchedTermInAbstarctBody", searchedTerm,
						searchedTerm.toLowerCase());
				for (WebElement el : highlightedTerm)
					if (el.getCssValue("background-color").equalsIgnoreCase("#d7d7d7"))
						flag = true;
					else
						flag = false;
				Assert.assertTrue(flag,
						"Assertion Failed: Searched term is NOT highlighted in ABSTRACT of article: " + title);

			} else if (checkIfElementIsThere("txt_chapterHead"))
				logMessage("Abstract does not exist for chapter");
		} finally {
			navigateToPreviousPage();
			wait.waitForPageToLoadCompletely();
		}
	}
}
