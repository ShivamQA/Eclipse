package com.qait.acs.keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class CollectionPageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "collectionPage";
	
	String shareOptions[]= {"Facebook","Twitter","LinkedIn","Google_Plus","Reddit","Email"};

	public CollectionPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyNavigationToCollectionLandingPage(String CollectionName){
		if(CollectionName.contains("ASAP"))
			CollectionName="ASAP";
		
		if(CollectionName.contains("Issue"))
			Assert.assertTrue(getCurrentURL().contains("toc"), "Assertion failed: Not navigated to Issue page.");
		else
			isElementDisplayed(true, "h1_Collection",CollectionName);
		
		logMessage("Verified: Navigated to '"+CollectionName+"' landing page.");
	}

	public void verifyElementsOnHeader(String CollectionName) {
		isElementDisplayed(true, "h1_Collection",CollectionName);
		isElementDisplayed(true, "txt_Description");
		logMessage("Verified: Heading and Description is displayed for Collection: '"+CollectionName+"'.");
		if(!CollectionName.equals("Most Read")){
			VerifyOtherElementsOnHeader(CollectionName);
		}
	}

	private void VerifyOtherElementsOnHeader(String Collection) {
    	String validPatternForPublicationDate = "(January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{1,2},\\s\\d{4}";
		isElementDisplayed(true, "Img_Header");
    	isElementDisplayed(true, "btn_eAlerts");
		Assert.assertTrue(elements("txt_MetaData").get(0).getText().equals("Latest Article"));
		Assert.assertTrue(verifyFormat(elements("txt_MetaData").get(1).getText(), validPatternForPublicationDate),
				"Assertion Failed: Publication Date not displayed in [Month DD, YYYY] format");
		logMessage("Verified: 'Latest Article Month XX, XXXX' displayed under alerts button.");
		
		isElementDisplayed(true, "Icon_Share");
		
		if(Collection.equals("Editors’ Choice"))
			isElementDisplayed(true, "link_EditorsChoiceAbout");
		
		if(Collection.contains("ASAP") || Collection.contains("Just Accepted Manuscripts")){
			isElementDisplayed(true, "link_ViewAllIssues");
			isElementDisplayed(true, "link_CurrentIssue");
			if(Collection.contains("Just Accepted Manuscripts")){
				isElementDisplayed(true, "link_JamFAQ");
				isElementDisplayed(true, "link_ASAPs");
			}
		}
		
	}

	public void verifyTimeFrameOptionsAvailableOnMostReadPage() {
		Assert.assertTrue(elements("link_TimeframeOptions").size()==2, "Assertion failed: 2 timeframe options are not displayed.");
		logMessage("Verified: 2 timeframe options are displayed on Most Read page.");
		
		isStringMatching(elements("link_TimeframeOptions").get(0).getAttribute("title"), "1 Month");
		isStringMatching(elements("link_TimeframeOptions").get(1).getAttribute("title"), "12 Months");
		
		verifyTimeFrameOptionSelected("1 Month");
		logMessage("Verified: 1 Month is selected by default.");
		
		}

	
	private void verifyTimeFrameOptionSelected(String option) {
		Assert.assertTrue(element("Option_TimeframeSelected").getAttribute("title").equals(option), "Assertion failed: Timeframe option"+ option+" is not selected");
		logMessage("Info: Time frame option: "+option+ " is selected.");
	}

	public void verifyDescriptionFor1MonthTimeframe(String DescriptionText) {
		Assert.assertTrue(element("txt_Description").getText().replaceAll("’", "").equals(DescriptionText.replaceAll("â€™", "")),"Assertion failed: Description text for 1 month not correct.");
		logMessage("Verified: Description text for 1 month timeframe option.");
		
	}

	public void verify20ArticlesAreDisplayedOnMostReadPage(String Option) {
		Assert.assertTrue(elements("Container_Articles").size()==20, "Assertion failed: 20 articles are not displayed on Most Read page for time frame option: "+Option);
		logMessage("Verified: 20 articles displayed on Most Read page for time frame option: "+Option);
		
	}

	public void clickOnTimeframeOption(String Option) {
		int i;
		if(Option.equals("1 Month"))
				i=0;
		else
			i=1;
		elements("link_TimeframeOptions").get(i).click();
		logMessage("Info: Clicked on time frame option: "+Option);
		
		verifyTimeFrameOptionSelected(Option);
	}

	public List<String> fetchArticleList() {
		logMessage("Info: Fetching Article List under selected option... ");
		List<String> ArticleList = new ArrayList<String>();
		for (WebElement ArticlesTitle : elements("title_Article")) {
			String ArticlesTitles = ArticlesTitle.getText().trim();
			ArticleList.add(ArticlesTitles);
		}
		return ArticleList;
	}

	public void VerifyArticleListsFor1MonthAnd12MonthsOptionDiffers(List<String> ArticleList1) {
		logMessage("Info: Fetching Article List under 1 month tab... ");
		List<String> ArticleList2 = new ArrayList<String>();
		for (WebElement ArticlesTitle : elements("title_Article")) {
			String ArticlesTitles = ArticlesTitle.getText().trim();
			ArticleList2.add(ArticlesTitles);
		}
		Assert.assertNotEquals(ArticleList1, ArticleList2, "Assertion Failed: Article list for 1 month and 12 months do not differ.");
		logMessage("Verified: Article list differs for 1 Month and 12 Months option.");
	}


	public void clickOnCurrentIssueLink() {
		clickOnElement("link_CurrentIssue");
		hardWait(2);
		
	}

	public void clickOnViewAllIssueLink() {
		clickOnElement("link_ViewAllIssues");
		hardWait(2);
		
	}
	
	
	public void verifyNavigationOfeALertsButton() {
		clickOnElement("btn_eAlerts");
		verifyNavigationToLoginPromptPage();
	}

	private void verifyNavigationToLoginPromptPage() {
		Assert.assertTrue(getCurrentURL().contains("action/showLogin"));
		logMessage("Verified: Navigation to Login Prompt page.");
		navigateToPreviousPage();
	}
	
	public void verifyShareIconAndItsFunctionality(String[] ShareOptions) {
		clickOnElement("Icon_Share");
		Assert.assertTrue(element("Icon_Share").getAttribute("aria-expanded").equals("true"), "Assertion failed: Share icon is not expanded.");
		logMessage("Verified: Share icon is expanded.");
		
		for(String option: ShareOptions){
			isElementDisplayed(true, "Options_share",option.toLowerCase());
			logMessage("Info: share option '"+option+"' is displayed.");
		}
	}

	public void clickOnASAPsLink() {
		clickOnElement("link_ASAPs");
		hardWait(2);
	}

	public void clickOnJAMSFAQLinkAndVerify() {
		clickOnElement("link_JamFAQ");
		hardWait(2);
		/*
		 * Not navigating to anything. Update when fixed.
		 */
	}

	public void clickOnAboutEditorsChoiceLinkAndVerify() {
		clickOnElement("link_EditorsChoiceAbout");
		hardWait(2);
		Assert.assertTrue(getCurrentURL().contains("policy/editorchoice"), "Assertion failed: Not navigated to correct page.");
		logMessage("Verified: About ACS Editors link is navigating to correct page.");
		/*
		 * Blank page is displaying. Update when fixed.
		 */
		navigateToPreviousPage();
	}

	public void verifyElementsInArticleBlock(String Collection) {
		int articlesCount= elements("Container_Articles").size();
		logMessage("Info: Verified that "+articlesCount+" articles are displayed on the page.");
		
		logMessage("Info: Verifying metadata for articles..");
		
		// ** Title **
		Assert.assertTrue(elements("title_Article").size()==articlesCount, "Assertion failed: Not all articles have titles.");
		logMessage("Verified: Title is displayed for each article.");
		
		// ** Authors **
		if(Collection.equals("Current Issue"))
			Assert.assertTrue(elements("Authors_Article").size()==(articlesCount-2), "Assertion failed: Not all articles have authors.");
		else
			Assert.assertTrue(elements("Authors_Article").size()==articlesCount, "Assertion failed: Not all articles have authors.");
		logMessage("Verified: Author names are displayed for each article.");
		
		// ** Citation String **
		Assert.assertTrue(elements("CiteInfo_JournalName").size()==articlesCount, "Assertion failed: Not all articles have Journal name in Cite this info.");
		if(!(Collection.equals("Most Read") || Collection.equals("Editors’ Choice")))
			Assert.assertTrue(elements("CiteInfo_Year").size()==articlesCount, "Assertion failed: Not all articles have Year in Cite this info.");
		Assert.assertTrue(elements("CiteInfo_Vol").size()==articlesCount, "Assertion failed: Not all articles have Volume in Cite this info.");
		//Assert.assertTrue(elements("CiteInfo_Issue").size()==articlesCount, "Assertion failed: Not all articles have Issue number in Cite this info.");
		if(Collection.equals("Most Read") || Collection.equals("Editors’ Choice"))
			logMessage("Info: "+elements("CiteInfo_PageRange").size()+" articles have Page Range in cite this info.");
		else
			Assert.assertTrue(elements("CiteInfo_PageRange").size()==articlesCount, "Assertion failed: Not all articles have page range in Cite this info.");
		Assert.assertTrue(elements("CiteInfo_Type").size()==articlesCount, "Assertion failed: Not all articles have Type in Cite this info.");
		logMessage("Verified: Cite This info '<Journal Name> Year, Volume#, Issue#, pp XXXX-XXXX (Type)' is displayed for every article");
		
		// ** Pub date **
		Assert.assertTrue(elements("Pubdate_Article").size()==articlesCount, "Assertion failed: Not all articles have Publication date.");
		Assert.assertTrue(elements("Pubdate_Value").size()==articlesCount, "Assertion failed: Not all articles have Publication date (value)");
		logMessage("Verified: Publication date is displayed for each article.");
		
		// ** Editors Choice Date **
		if(Collection.equals("Editors’ Choice")){
			Assert.assertTrue(elements("Date_EditorsChoice").size()==articlesCount, "Assertion failed: Not all articles have Editors Choice date.");
			Assert.assertTrue(elements("Date_EditorsChoiceValue").size()==articlesCount, "Assertion failed: Not all articles have Editors Choice date (value)");
			logMessage("Verified: Editors Choice date is displayed for each article in Editors Choice collection.");
		}
		
		// ** Article block Buttons **
		if(Collection.equals("Current Issue"))
			Assert.assertTrue(elements("Abstract_Article").size()==articlesCount-2, "Assertion failed: Not all articles have Abstract link.");
		else
			Assert.assertTrue(elements("Abstract_Article").size()==articlesCount, "Assertion failed: Not all articles have Abstract link.");
		logMessage("Verified: Abstract link is displayed for each article.");
			
		Assert.assertTrue(elements("PDF_Article").size()==articlesCount, "Assertion failed: Not all articles have PDF link.");
		logMessage("Verified: PDF link is displayed for each article.");
		
		Assert.assertTrue(elements("AccessSpecifier_Article").size()==articlesCount, "Assertion failed: Not all articles have Access specifier");
		logMessage("Verified: Access specifier is displayed for each article.");
		
		// ** Abstract angle down icon **
		if(!Collection.equals("Most Read")){
			if(Collection.equals("Current Issue"))
				Assert.assertTrue(elements("Icon_AbstractAngleDown").size()==articlesCount-2, "Assertion failed: Not all articles have Abstract angle down icon.");
			else
				Assert.assertTrue(elements("Icon_AbstractAngleDown").size()==articlesCount, "Assertion failed: Not all articles have Abstract angle down icon.");
			logMessage("Verified: Abstract angle down icon is displayed for each article.");
		}
		
		// ** Full Text link **
		if(checkIfElementIsThere("FullText_Article")){
			logMessage("Info: "+elements("FullText_Article").size()+" articles have Full Text link.");
		}
		
		// ** Article Image **
		if(checkIfElementIsThere("Img_Article")){
			logMessage("Info: "+elements("Img_Article").size()+" articles have Article Image.");
		}

	}

	public String ClickOnArticleTitleFromArticleBlock() {
		int articlesCount= elements("Container_Articles").size();
		Random random = new Random();
		int randomIndex = random.nextInt(articlesCount);
		
		if(randomIndex>=2)
		scrollDown(elements("title_Article").get(randomIndex-1));
		String title= elements("title_Article").get(randomIndex).getText();
		elements("title_Article").get(randomIndex).click();
		logMessage("Info: Clicked on article titled: "+title);
		return title;
	}

	public void verifyFunctionalityOfAbstractFullTextPDFLinks(String Collection) {
		int articlesCount= elements("Abstract_Article").size();
		Random random = new Random();
		int randomIndex = random.nextInt(articlesCount-1);
		
		if(randomIndex<1)
			scrollDown(elements("title_Article").get(randomIndex));
		else
			scrollDown(elements("title_Article").get(randomIndex-1));
		
		elements("Abstract_Article").get(randomIndex).click();
		Assert.assertTrue(getCurrentURL().contains("doi/abs"), "Assertion failed: Not navigated to article abstract page.");
		logMessage("Verified: Navigation to article abstract page");
		hardWait(2);
		navigateToPreviousPage();
		elements("PDF_Article").get(randomIndex).click();
		Assert.assertTrue(getCurrentURL().contains("doi/pdf"), "Assertion failed: Not navigated to article pdf page.");
		logMessage("Verified: Navigation to article pdf page");
		hardWait(2);
		navigateToPreviousPage();
		if(!Collection.equals("Just Accepted Manuscripts")){
			if(checkIfElementIsThere("FullText_Indexed",Integer.toString(randomIndex))){
				elements("FullText_Article").get(randomIndex).click();
				Assert.assertTrue(getCurrentURL().contains("doi/full"), "Assertion failed: Not navigated to article pdf page.");
				logMessage("Verified: Navigation to article full text page");
				hardWait(2);
				navigateToPreviousPage();
			}
		}
	}

	public void verifyFunctionalityOfAbstractAngleDownIcon() {
		int articlesCount= elements("Icon_AbstractAngleDown").size();
		Random random = new Random();
		int randomIndex = random.nextInt(articlesCount);
		
		scrollDown(elements("title_Article").get(randomIndex));
		
		elements("Icon_AbstractAngleDown").get(randomIndex).click();
		logMessage("Clicked on Abstract angle down icon.");
		Assert.assertTrue(elements("Icon_AbstractAngleDown").get(randomIndex).getAttribute("aria-expanded").equals("true"), "Assertion failed: Abstract angle down not expanded");
		logMessage("Info: Abstract angle down icon expanded.");
		isElementDisplayed(true, "text_ExpandedAbstract");
		logMessage("Verified: Clicking on Abstract angle down icon expands and displays abstract text.");
	}

	public void verifySortByDropdownOnEditorsChoicePage() {
		refreshPage();
		String[] Options = {"Editors' Choice Date","Most Read","Most Cited"};
		
		isElementDisplayed(true, "DropDown_ECSortBy");
//		scrollDown(element("link_EditorsChoiceAbout"));
		scrollVertical(150);
		hardWait(1);
		clickOnElement("DropDown_ECSortBy");
		hardWait(1);
		Assert.assertTrue(elements("links_ECPublications").size()==3, "Assertion failed: 3 Editors Choice sort by options are not displayed.");
		logMessage("Verified: 3 Editor's Choice Sory By dropdown options are displayed.");
		
		int i=0;
		for(String optionName: Options){
			isStringMatching(elements("links_ECPublications").get(i).getText(), optionName);
			i++;
		}
		logMessage("Verified: Options in Editor's Choice Sort By dropdown.");
	}

	public void verifyPublicationsDropdownOnEditorsChoicePage() {
		isElementDisplayed(true, "DropDown_ECPublication");
		clickOnElement("DropDown_ECPublication");
		hardWait(1);
		Assert.assertTrue(elements("links_ECPublications").size()==56, "Assertion failed: 56 publications under Publications dropdown are not displayed.");
		logMessage("Verified: 56 Publications are displayed under Publications dropdown on EC page.");
//		refreshPage();
	}

	public void verifySelectingOptionsOnSortByDropdownOnECPageChangesResults() {
		refreshPage();
		scrollToTop();
		scrollVertical(150);
		hardWait(2);
		clickOnElement("DropDown_ECSortBy");
		Assert.assertTrue(elements("links_ECSortBy").get(0).getAttribute("class").contains("selected"), "Assertion failed: "+elements("links_ECSortBy").get(0).getText()+" is not selected by default.");
		logMessage("Verified: "+elements("links_ECSortBy").get(0).getText()+" is selected by default.");
		List<String> ArticleList1 = fetchArticleList();
		elements("links_ECSortBy").get(1).click();
		hardWait(5);
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeClickable(element("DropDown_ECSortBy"));
		clickOnElement("DropDown_ECSortBy");
		Assert.assertTrue(elements("links_ECSortBy").get(1).getAttribute("class").contains("selected"), "Assertion failed: "+elements("links_ECSortBy").get(1).getText()+" is not selected.");
		logMessage("Info: "+elements("links_ECSortBy").get(1).getText()+" is selected.");
		List<String> ArticleList2 = fetchArticleList();
		VerifyArticleListsForOptionsDiffers(ArticleList1,ArticleList2);
		elements("links_ECSortBy").get(2).click();
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeClickable(element("DropDown_ECSortBy"));
		hardWait(5);
		clickOnElement("DropDown_ECSortBy");
		Assert.assertTrue(elements("links_ECSortBy").get(2).getAttribute("class").contains("selected"), "Assertion failed: "+elements("links_ECSortBy").get(2).getText()+" is not selected.");
		logMessage("Info: "+elements("links_ECSortBy").get(2).getText()+" is selected.");
		List<String> ArticleList3 = fetchArticleList();
		VerifyArticleListsForOptionsDiffers(ArticleList3,ArticleList2);
	}

	public void VerifyArticleListsForOptionsDiffers(List<String> ArticleList1, List<String> ArticleList2) {
		logMessage("Info: Comparing article lists... ");
		Assert.assertNotEquals(ArticleList1, ArticleList2, "Assertion Failed: Article list for two options do not differ.");
		logMessage("Verified: Article list differs for the two options.");
	}

	public void verifySelectingOptionsFromPublicationsDropdownOnECPageChangesResults() {
		refreshPage();
		scrollToTop();
		scrollVertical(150);
		hardWait(1);
		clickOnElement("DropDown_ECPublication");
		Assert.assertTrue(elements("links_ECPublications").get(0).getAttribute("class").contains("selected"), "Assertion failed: "+elements("links_ECPublications").get(0).getText()+" is not selected by default.");
		logMessage("Verified: "+elements("links_ECSortBy").get(0).getText()+" is selected by default.");
		List<String> ArticleList1 = fetchArticleList();
		elements("links_ECPublications").get(1).click();
		hardWait(5);
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeClickable(element("DropDown_ECPublication"));
		clickOnElement("DropDown_ECPublication");
		Assert.assertTrue(elements("links_ECPublications").get(1).getAttribute("class").contains("selected"), "Assertion failed: "+elements("links_ECPublications").get(1).getText()+" is not selected.");
		logMessage("Info: "+elements("links_ECPublications").get(1).getText()+" is selected.");
		List<String> ArticleList2 = fetchArticleList();
		VerifyArticleListsForOptionsDiffers(ArticleList1,ArticleList2);
		elements("links_ECPublications").get(2).click();
		hardWait(5);
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeClickable(element("DropDown_ECPublication"));
		clickOnElement("DropDown_ECPublication");
		Assert.assertTrue(elements("links_ECPublications").get(2).getAttribute("class").contains("selected"), "Assertion failed: "+elements("links_ECPublications").get(2).getText()+" is not selected.");
		logMessage("Info: "+elements("links_ECPublications").get(2).getText()+" is selected.");
		List<String> ArticleList3 = fetchArticleList();
		VerifyArticleListsForOptionsDiffers(ArticleList3,ArticleList2);

	}

	
	
	
	
	
	
	
	
	


}
