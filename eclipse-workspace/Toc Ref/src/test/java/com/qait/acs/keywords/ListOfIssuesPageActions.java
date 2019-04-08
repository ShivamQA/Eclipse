package com.qait.acs.keywords;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qait.automation.getpageobjects.GetPage;
import org.testng.Assert;

public class ListOfIssuesPageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "loiPage";

	public ListOfIssuesPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}
	
	String tickerTapeColor= "rgb(253, 200, 47)";
	
    public void verifyNavigationToLOISearchResults(String journalName){
    	isStringMatching(element("img_header").getAttribute("title"), journalName);
    	logMessage("Verified: Navigation to correct journal LOI page.");
    	
    	verifyNavigationToLOIPage();
    	
    }

    public void verifyNavigationToLOIPage(){
    	isElementDisplayed(true, "div_LoiWidget");
    	logMessage("Verified: LOI widget is displayed.");
    	
    	int decadeTabsCount= elements("Tabs_decadeList").size();
    	Assert.assertTrue(decadeTabsCount>=1,"Assertion failed: Decade tablist is not displayed.");
    	logMessage("Info: "+decadeTabsCount+" decade tabs are present in the LOI widget.");
    	
    	int YearsTabsCount= elements("Tabs_YearsList").size();
    	Assert.assertTrue(YearsTabsCount>=1, "Assertion failed: Year tablist is not displayed.");
    	logMessage("Info: "+YearsTabsCount+" years tabs are present under active decade tab in the LOI widget.");
    	
    	int IssuesDisplayedCount= elements("IssueList").size();
    	Assert.assertTrue(IssuesDisplayedCount>=1);
    	logMessage("Info: "+IssuesDisplayedCount+" issues are displayed as search results.");
    	
    }


	public void verifyElementsOfLOIWidget() {
		String defaultDecade="2010s";
		String defaultYear="2018";
		
		int decadeTabsCount= elements("Tabs_decadeList").size();
		if(decadeTabsCount>11){
			logMessage("Info: Since decade tab count is > 11, the list is scrollable.");
			isElementDisplayed(true, "btn_ScrollLeft");
			isElementDisplayed(true, "btn_ScrollRight");
		}
		
		Assert.assertTrue(element("link_Tab",defaultDecade).getAttribute("aria-expanded").equals("true"), "Assertion failed: "+defaultDecade+" is not selected by default");
		Assert.assertTrue(element("link_Tab",defaultDecade).getCssValue("border-bottom").contains(tickerTapeColor));
		logMessage("Verified: "+defaultDecade+" is selected by default and indicator (the underline) is yellow.");
		
		Assert.assertTrue(element("link_Tab",defaultYear).getAttribute("aria-expanded").equals("true"), "Assertion failed: "+defaultYear+" is not selected by default");
		Assert.assertTrue(element("link_Tab",defaultYear).getCssValue("border-bottom").contains(tickerTapeColor));
		logMessage("Verified: "+defaultYear+" is selected by default and indicator (the underline) is yellow.");
		
	}


	public void SelectAnyDecadeAndVerify(String decade) {
		clickOnElement("link_Tab",decade);
		Assert.assertTrue(element("link_Tab",decade).getAttribute("aria-expanded").equals("true"), "Assertion failed: "+decade+" is not selected.");
		Assert.assertTrue(element("link_Tab",decade).getCssValue("border-bottom").contains(tickerTapeColor));
		logMessage("Verified: Decade "+decade+" is selected.");
		
		int YearsTabsCount= elements("Tabs_YearsList").size();
    	Assert.assertTrue(YearsTabsCount==10, "Assertion failed: 10 Year tabs are not displayed in tablist.");
    	logMessage("Verified: 10 years tabs are present under active decade tab in the LOI widget.");
    	
    	String DecadeStartYear = decade.replaceAll("[^0-9]","");
    	int DecadeEndYear= Integer.parseInt(DecadeStartYear)+9;
    	
    	for(WebElement Years: elements("txt_YearsList")){
    		System.out.println(Years.getText()+"...."+Integer.toString(DecadeEndYear));
    		Assert.assertTrue(Years.getText().equals(Integer.toString(DecadeEndYear)), "Assertion failed: "+DecadeEndYear+" not displayed in the year list.");
    		DecadeEndYear--;
    	}
    	logMessage("Verified: Year list is populated with 10 tabs for the selected decade.");

	}

	public void SelectAnyYearAndVerify(String year) {
		clickOnElement("link_Tab",year);
		Assert.assertTrue(element("link_Tab",year).getAttribute("aria-expanded").equals("true"), "Assertion failed: "+year+" is not selected.");
		Assert.assertTrue(element("link_Tab",year).getCssValue("border-bottom").contains(tickerTapeColor));
		logMessage("Verified: Year "+year+" is selected.");
		
		int IssuesDisplayedCount= elements("IssueList").size();
    	Assert.assertTrue(IssuesDisplayedCount>=1);
    	logMessage("Info: "+IssuesDisplayedCount+" issues are displayed as search results.");
    	
    	Assert.assertTrue(element("Issues_ArchivesHeader").getText().contains(year), "Assertion failed: Issues not displayed for the year:"+year);
    	logMessage("Verified: '"+element("Issues_ArchivesHeader").getText()+"' issues header.");
    	
    	String validPatternForPublicationDate = "(January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{1,2},\\s"+year;
    	
    	Assert.assertTrue(elements("txt_YearIssues").size()==IssuesDisplayedCount, "Assertion failed: Not all issues have Publication Date.");
    	logMessage("Verified: Publication date is displayed for every issue.");
    	
    	for(int i=0;i<IssuesDisplayedCount;i++){
    		Assert.assertTrue(verifyFormat(elements("txt_YearIssues").get(i).getText(), validPatternForPublicationDate),
    				"Assertion Failed: Publication Date not displayed in [Month DD, YYYY] format");
    	}
		logMessage("Verified: Publication Date displayed in [Month DD, YYYY] format for all issues.");
    	
    	Assert.assertTrue(elements("Img_Issues").size()==IssuesDisplayedCount, "Assertion failed: Not all issues have Cover image.");
    	logMessage("Verified: Cover Image is displayed for every issue.");
    	
    	Assert.assertTrue(elements("txt_pageRangeIssue").size()==IssuesDisplayedCount, "Assertion failed: Not all issues have Page range.");
    	logMessage("Verified: Page range is displayed for every issue.");
    	
    	Assert.assertTrue(elements("txt_VolumeIssue").size()==IssuesDisplayedCount, "Assertion failed: Not all issues have Volume# and Issue#.");
    	logMessage("Verified: Volume# and Issue# is displayed for every issue.");
	
	}

	public String[] clickOnAnyIssueLinkAndVerifyNavigation() {
		int IssuesDisplayedCount= elements("IssueList").size();
		Random random = new Random();
		int randomIndex = random.nextInt(IssuesDisplayedCount);
		String coverDate= elements("txt_YearIssues").get(randomIndex).getText();
		String pageRange= elements("txt_pageRangeIssue").get(randomIndex).getText().replaceAll("pp.", "Pages");
		String VolumeIssue= elements("txt_VolumeIssue").get(randomIndex).getText().replaceAll("Vol.", "Volume").replaceAll("Issue", ", Issue");
		String[] Metadata={coverDate,VolumeIssue,pageRange};
		
		elements("txt_VolumeIssue").get(randomIndex).click();
		logMessage("Info: Clicked on issue number "+randomIndex);
		return Metadata;
	}

	public void verifyNavigationToCorrectVolumeLOIResult(String Volume) {
		int IssuesDisplayedCount= elements("IssueList").size();

		for(int i=0;i<IssuesDisplayedCount;i++){
    		Assert.assertTrue(elements("txt_VolumeIssue").get(i).getText().contains("Vol. "+Volume), "Assertion Failed: Not navigated to Volume: "+Volume+" LOI result.");
    	}
		logMessage("Verified: All LOI results are displayed for Volume number:"+Volume);
	}
























}

