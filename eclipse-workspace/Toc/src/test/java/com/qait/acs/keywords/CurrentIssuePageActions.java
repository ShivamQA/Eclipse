package com.qait.acs.keywords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.DateComparator;

public class CurrentIssuePageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "currentIssuePage";

	public CurrentIssuePageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyNavigationToCurrentIssuePage(String journal) {
		Assert.assertTrue(element("logo_Header").getAttribute("alt").equals(journal),"Assertion failed: Not navigated to Current Issue page.");
		Assert.assertTrue(getCurrentURL().contains("toc") && getCurrentURL().contains("current"), "Assertion failed: Not navigated to Current Issue page.");
		logMessage("Verified: Navigated to journal Current Issue/TOC page.");
	}

	public void verifyElementsOnJournalTOCPage() {
		isElementDisplayed(true, "Img_JournalCover");
//		Assert.assertTrue(elements("Imgs_SmallJournalCovers").size()==4, "Assertion failed: 4 small journal covers not displayed.");
//		logMessage("Verified: Large cover issue with 4 smaller issue cover images are displayed.");
		
		Assert.assertTrue(elements("txt_Metadata").size()==3);
		
		String validPatternForPublicationDate = "(January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{1,2},\\s\\d{4}";
		String validPatternForVolumeIssue= "Volume\\s\\d{1,5}\\,\\sIssue\\s\\d{1,5}";
		String validPatternForPages= "Pages\\s\\d{1,8}\\-\\d{1,8}";

		Assert.assertTrue(verifyFormat(elements("txt_Metadata").get(0).getText(), validPatternForPublicationDate),"Assertion Failed: Publication Date not displayed in [Month DD, YYYY] format");
		logMessage("Verified: Publication Date displayed in [Month DD, YYYY] format");
		
		Assert.assertTrue(verifyFormat(elements("txt_Metadata").get(1).getText(), validPatternForVolumeIssue),"Assertion Failed: Volume Issue not displayed in [Volume XX, Issue XX] format");
		logMessage("Verified: Volume Issue is displayed in [Volume XX, Issue XX] format");
		
		Assert.assertTrue(verifyFormat(elements("txt_Metadata").get(2).getText(), validPatternForPages),"Assertion Failed: Volume Issue not displayed in [Pages XXXX-XXXX] format");
		logMessage("Verified: Volume Issue is displayed in [Pages XXXX-XXXX] format");
		
		isElementDisplayed(true, "txt_AboutJournal");
		isElementDisplayed(true, "link_ViewArticle");
		isElementDisplayed(true, "link_DownloadCover");
		
		Assert.assertTrue(elements("links_InThisIssueSections").size()>=1, "Assertion failed: Sections not displayed under 'In this Issue' section.");
		logMessage("Verified: "+elements("links_InThisIssueSections").size()+" section links displayed under 'In this Issue'.");
		
		if(checkIfElementIsThere("link_NextIssue"))
			logMessage("Verified: 'NEXT ISSUE' link is displayed. ");
		if(checkIfElementIsThere("link_PreviousIssue"))
			logMessage("Verified: 'PREVIOUS ISSUE' link is displayed.");
		
		isElementDisplayed(true, "link_ViewAllIssues");
		isElementDisplayed(true, "link_ASAPs");
		
		if(checkIfElementIsThere("link_JAMs"))
			logMessage("Verified: 'JAMs' link is displayed.");
		
		isElementDisplayed(true, "btn_GeteAlerts");
		isElementDisplayed(true, "btn_SubmitManuscripts");
		
		isElementDisplayed(true, "icon_Share");
	}

	public void verifyFunctionalityOfClickingOnLinksUnderInThisIssueSectionOnJournalTOCPage() {
		int differenceTop=0;
		int availableLinks= elements("links_InThisIssueSections").size();
		scrollUp();
		String top = verifyScrolledPosition(element("Img_SiteHeaderLogo"), "Site header logo").split("\\.")[0];
		System.out.println(top);
		
		for (int i = 0; i < availableLinks; i++) {
			String link = elements("links_InThisIssueSections").get(i).getAttribute("href").split("#")[1];
			elements("links_InThisIssueSections").get(i).click();
			logMessage("Info: Clicked on : '" + link + "' Link");
			if(i==0){
				differenceTop = verifyPagePositionbyId(link,Integer.parseInt(top));
				logMessage("Clicking on link " + link+ " available under 'In this issue' scrolled down the page to relevant section.");
			}
			else{
				Assert.assertTrue(Math.abs(verifyPagePositionbyId(link,Integer.parseInt(top))-differenceTop)<=2, "Clicking on link " +link+ " available under 'In this issue' did not scroll down the page to relevant section.");
				logMessage("Clicking on link "+link+ " available under 'In this issue' scrolled down the page to relevant section.");
			}
		}
	}


	private int verifyPagePositionbyId(String link, int top ) {
		String topValue = (verifyScrolledPosition(element("headings_Sections", link), link)).split("\\.")[0];
		System.out.println(topValue);
		int difference= Integer.parseInt(topValue)-top;
		System.out.println(difference);
		scrollUp();
		return difference;

	}

	public void clickOnJournalLogoAndVerify() {
		String PrevURL= getCurrentURL();
		String journal=PrevURL.split("/")[4];
		clickOnElement("logo_Header");
		String NewURL = getCurrentURL();
		Assert.assertTrue(NewURL.contains("journal/"+journal), "Assertion failed: Clicking on journal logo doesnt navigate to journal home page.");
		logMessage("Verified: Clicking on Journal logo navigates to journal homepage.");
		driver.get(PrevURL);
		hardWait(2);
	}

	public void clickOnNextPreviousIssueLinksAndVerify() {
		String url= getCurrentURL();
		String AboutText= element("txt_AboutJournal").getText();
		if(checkIfElementIsThere("link_NextIssue")){
			clickOnElement("link_NextIssue");
			Assert.assertFalse(element("txt_AboutJournal").getText().equals(AboutText));
			logMessage("Verified: Clicking on 'Next Issue' link navigated to next journal issue.");
			
			
		}
		if(checkIfElementIsThere("link_PreviousIssue")){
			clickOnElement("link_PreviousIssue");
			Assert.assertFalse(element("txt_AboutJournal").getText().equals(AboutText));
			logMessage("Verified: Clicking on 'Previous Issue' link navigated to previous journal issue.");
		}
		driver.get(url);
		
	}

	public void clickOnViewAllIssueLink() {
		clickOnElement("link_ViewAllIssues");
		hardWait(2);
	}

	public void clickOnASAPLink() {
		clickOnElement("link_ASAPs");
		hardWait(2);
	}

	public Boolean clickOnJAMSLink() {
		if(checkIfElementIsThere("link_JAMs")){
			clickOnElement("link_JAMs");
			hardWait(2);
			return true;
		}
		else{
			logMessage("Info: JAMS link is not present for the journal.");
			return false;
			}
	}

	public void verifyNavigationOfeALertsButton() {
		clickOnElement("btn_GeteAlerts");
		verifyNavigationToLoginPromptPage();
	}

	private void verifyNavigationToLoginPromptPage() {
		Assert.assertTrue(getCurrentURL().contains("action/showLogin"));
		logMessage("Verified: Navigation to Login Prompt page.");
		navigateToPreviousPage();
	}
	
	public void clickOnSubmitManuscriptButtonAndVerifyNavigation() {
		clickOnElement("btn_SubmitManuscripts");
		Assert.assertTrue(getCurrentURL().contains("acsparagonplus"));
		logMessage("Verified: Clicking on Submit Manuscript button navigates to ACS Paragon Plus page.");
		navigateToPreviousPage();
	}

	public void verifySortByDropDownDisplayedOnTOCPage() {
		isElementDisplayed(true, "dropDown_SortBy");
		Assert.assertTrue(elements("txt_SortByOption").size()==2, "Assertion failed: 2 Sort By dropdown options are not displayed.");
		logMessage("Verified: 2 Sort By dropdown options are displayed.");
		
	}

	public void verifyDropDownOptions(String option1, String option2) {
		isStringMatching(elements("txt_SortByOption").get(0).getText().trim(), option1);
		isStringMatching(elements("txt_SortByOption").get(1).getText().trim(), option2);

	}

	public void verifyOptionIsSelected(String Option) {
		Assert.assertTrue(element("option_SelectedSortBy").getText().trim().equals(Option), "Assertion failed:"+Option+" not selected.");
		logMessage("Verified: '"+Option+"' option is selected in the 'Sort By' dropdown.");
	}

	public void clickOnSortByOption(String Option) {
		scrollDown(element("link_ViewArticle"));
		element("dropDown_SortBy").click();
		
		if(Option.equals("Date"))
			elements("Options_SortBy").get(1).click();
		else
			elements("Options_SortBy").get(0).click();

		logMessage("Info: Selected option: "+Option);
		verifyOptionIsSelected(Option);
		
		
	}

	public void verifySortingByDateForArticleBlock() {
		List<String> pubDates = new ArrayList<String>();
	    List<String> sortedPubDates = new ArrayList<String>();

	    for (WebElement Dateheading : elements("headings_SortedByDate")) {
	      pubDates.add(Dateheading.getText().trim());
	    }

	    sortedPubDates.addAll(pubDates);
	    DateComparator c = new DateComparator();
	    Collections.sort(sortedPubDates, c);
	    
	    Assert.assertEquals(pubDates, sortedPubDates, "Assertion Failed :: Sort by Dates failed!!! ");
	    logMessage("Verified :: Sort by 'Date' is working fine on TOC article block.");

	}

	public void verifySortByPageForArticleBlock() {
		int i;
		int sizeSections = elements("links_InThisIssueSections").size();
		Assert.assertTrue(elements("headings_SortedByPage").size()==sizeSections, "Assertion failed: Not all sections are displayed.");
		for(i=0;i<sizeSections;i++){
			isStringMatching(elements("headings_SortedByPage").get(i).getText().toLowerCase(),elements("links_InThisIssueSections").get(i).getText().toLowerCase());
		}
		logMessage("Verified: Sorting by 'Page' works fine on TOC article block.");
		
	}






}
