package com.qait.acs.keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class JournalHomePageActions extends GetPage {

	WebDriver driver;
	private static String pagename = "journalPage";

	public JournalHomePageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}
	
	String[] Collection={"Articles ASAP" ,"Current Issue", "Most Read", "Editors’ Choice"};
	Random random = new Random();

	public String verifyNavigationToCorrectJournalHomePage(String journal) {
		String homePageURL= getCurrentURL();
		Assert.assertTrue(homePageURL.contains("journal"));
		Assert.assertTrue(element("img_JournalLogo").getAttribute("title").equals(journal));
		logMessage("Verified: Navigated to correct journal home page.");
		return homePageURL;
	}

	public void verifySubmitManuscriptButtonIsDisplayed() {
		isElementDisplayed(true, "btn_SubmitManuscript");
	}

	public void verifyMetricsSection() {
		String foundPatternCitationYear= elements("text_Metrics").get(0).getText();
		String validPatternCitationYear= "\\d{4}\\sTotal\\sCitations";
		Assert.assertTrue(verifyFormat(foundPatternCitationYear, validPatternCitationYear),
				"Assertion Failed: Citation Year not displayed in correct format.");
		logMessage("Verified: Citation Year displayed in correct format.");
		
		String foundPatternCitationValue=elements("text_Metrics").get(1).getText();
		
		Assert.assertTrue(verifyFormat(foundPatternCitationValue,"[0-9,]+"),"Assertion failed: Citation value not displayed correctly.");
		logMessage("Verified: Citation value displayed correctly.");
		
		String foundPatternImpactFactorYear= elements("text_Metrics").get(2).getText();
		String validPatternImpactFactorYear= "Impact\\sFactor\\s\\d{4}";
		Assert.assertTrue(verifyFormat(foundPatternImpactFactorYear, validPatternImpactFactorYear),
				"Assertion Failed: Impact factor Year not displayed in correct format.");
		logMessage("Verified: Impact factor Year displayed in correct format.");
		
		String foundPatternImpactFactorValue=elements("text_Metrics").get(3).getText();
		
		Assert.assertTrue(verifyFormat(foundPatternImpactFactorValue,"[0-9.]+"),"Assertion failed: Impact factor value not displayed correctly.");
		logMessage("Verified: Impact factor value displayed correctly.");
	}

	public void verifyActionLinksSection() {
		isElementDisplayed(true, "Links_HeaderLeft","Recommend this Journal");
		isElementDisplayed(true, "Links_HeaderLeft","Subscription information");
	}

	public void verifyShareIconAndItsFunctionality(String[] ShareOptions) {
		
		isElementDisplayed(true, "Icon_Share");
		clickOnElement("Icon_Share");
		Assert.assertTrue(element("Icon_Share").getAttribute("aria-expanded").equals("true"), "Assertion failed: Share icon is not expanded.");
		logMessage("Verified: Share icon is expanded.");
		
		for(String option: ShareOptions){
			isElementDisplayed(true, "Options_share",option.toLowerCase());
			logMessage("Info: share option '"+option+"' is displayed.");
		}
	}

	public void clickOnSubmitManuscriptButtonAndVerifyNavigation() {
		scrollToTop();
		clickOnElement("btn_SubmitManuscript");
		Assert.assertTrue(getCurrentURL().contains("acsparagonplus"));
//		clickOnElement("btn_ContinueParagon");
		isElementDisplayed(true, "alert_loginParagon");
		isElementDisplayed(true, "loginForm_Paragon");
		logMessage("Verified: Clicking on Submit Manuscript button navigates to ACS Paragon Plus page.");
		navigateToPreviousPage();
	}

	public void clickOnRecommendThisJournalButtonAndVerifyNavigation() {
		clickOnElement("Links_HeaderLeft","Recommend this Journal");
		Assert.assertTrue(getCurrentURL().contains("recommendation"));
		isElementDisplayed(true, "dropDown_Institution");
		Assert.assertTrue(elements("input_FormFields").size()==5, "Assertion failed: All Input form fields are not displayed.");
		logMessage("Verified: 5 input recommend form fields are displayed");
		
		Assert.assertTrue(elements("CheckBox_RecommendResons").size()==4, "Assertion failed: All Recommend reasons checkboxes are not displayed.");
		logMessage("Verified: 4 Recommend reasons checkboxes are displayed");
		
		logMessage("Verified: Clicking on Recommend This Journal link navigates to Journal Recommendation page.");
		navigateToPreviousPage();
	}

	public void clickOnSubscriptionInformationButtonAndVerifyNavigation() {
		clickOnElement("Links_HeaderLeft","Subscription information");
		Assert.assertTrue(getCurrentURL().contains("showSubscriptions"));
		isElementDisplayed(true, "loginBox_Subscription");
		
		logMessage("Verified: Clicking on 'Subscription Information' link navigates user to user login page");
		navigateToPreviousPage();
		
	}

	public void clickOnShareIconAndVerifyNavigationOfOptions(String[] ShareOptions) {
		String option;
		
		for(int i=0;i<ShareOptions.length;i++) {
			boolean tabClosed = false;
			clickOnElement("Icon_Share");
			hardWait(1);
			option=ShareOptions[i].toLowerCase();
			clickOnElement("Options_share", option);	
			hardWait(2);
			
			if(option.equals("google_plus"))
				option="social bookmarking sharing button widget";
					
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));

			System.out.println(getPageTitle().toLowerCase() + "----"+option);
			try{
				Assert.assertTrue(getPageTitle().toLowerCase().contains(option),"Assertion Failed: Link '" + ShareOptions[i] + "' not redirecting to correct page");
				closeCurrentWindow();
				driver.switchTo().window(tabs2.get(0));
				logMessage("Assertion Passed :: Clicking on Sharing link "+ShareOptions[i]+" works fine.");
				tabClosed= true;
			}
			finally {
				System.out.println("Inside finally");
				if (!tabClosed) {
					closeCurrentWindow();
					driver.switchTo().window(tabs2.get(0));
				}

			}
		}
	}

		
	

	public void verifyEditorsInfoDisplayed() {
		Assert.assertTrue(element("text_EditorsInfo").getText().contains("Editor-in-Chief:"));
		isElementDisplayed(true, "link_EditorName");
		logMessage("Verified: Editor Info is displayed in the centre of journal header.");
	}

	public void verifyEditorEditorialBoardLinkIsDisplayed() {
		isElementDisplayed(true, "link_EditorialBoard");
		logMessage("Verified: 'Editors & Editorial Board' link is displayed in the centre of journal header.");
		
	}

	public void clickOnEditorNameLinkAndVerifyNavigation() {
		String editorName= element("link_EditorName").getText();
		clickOnElement("link_EditorName");
		verifyNavigationToEditorProfilePage(editorName);
		
	}

	private void verifyNavigationToEditorProfilePage(String editorName) {
		String[] Headings= {"Editor-in-Chief","Current Research","Biography"};
		Assert.assertTrue(getCurrentURL().contains("profile.html"));
		isStringMatching(element("txt_EditorNameProfilePage").getText(), editorName);
		int i=0;
		for(WebElement headings: elements("h2_EditorProfilePage")){
			isStringMatching(headings.getText(), Headings[i]);
			i++;
		}
		isStringMatching(element("h3_EditorProfilePage").getText().trim(), "Contact");
		logMessage("Verified: Navigation to Editor Profile page.");
		navigateToPreviousPage();
		
	}

	public void clickOnEditorsEditorialBoardLinkAndVerifyNavigation() {
		clickOnElement("link_EditorialBoard");
		verifyNavigationToEditorialBoardPage();
	}

	private void verifyNavigationToEditorialBoardPage() {
		Assert.assertTrue(getCurrentURL().contains("editors.html"));
		isElementDisplayed(true, "h1_EditorialBoard");
		isElementDisplayed(true, "EditorInfo_EditorialBoard");
		Assert.assertTrue(elements("h2_EditorialBoard").size()==8);
		logMessage("Info: 8 sub-headings are displayed on Editorial Board page.");
		logMessage("Verified: Navigation to Editorial board page.");
		
	}

	public void clickOnJournalLogoAndVerify() {
		scrollUp();
		String PrevURL= getCurrentURL();
		clickOnElement("img_JournalLogo");
		String NewURL = getCurrentURL();
		Assert.assertTrue(PrevURL.equals(NewURL), "Assertion failed: The page doesnt refresh upon clicking on journal logo.");
		logMessage("Verified: Clicking on Journal logo refreshes the page.");
	}

	public void verifyAndClickOnJournalCoverImage() {
		isElementDisplayed(true, "Img_JournalCover");
		clickOnElement("Img_JournalCover");
		hardWait(2);
		
	}

	public String[] verifyVolumeAndIssueNumberMetaDataIsDisplayed() {
		String VolumePattern="Volume\\s\\d{1,4}";
		String IssuePattern ="Issue\\s\\d{1,4}";
		
		Assert.assertTrue(verifyFormat(elements("MetaData_JournalIssue").get(0).getText(),VolumePattern),"Assertion failed: Volume metadata not displayed correctly.");
		logMessage("Verified: Volume meta data displayed correctly.");
		Assert.assertTrue(verifyFormat(elements("MetaData_JournalIssue").get(1).getText(),IssuePattern),"Assertion failed: Issue metadata not displayed correctly.");
		logMessage("Verified: Issue meta data displayed correctly.");
		
		String metaData=elements("MetaData_JournalIssue").get(0).getText()+", "+elements("MetaData_JournalIssue").get(1).getText();
		String[] Metadata={metaData};
		return Metadata;
				
	}

	public void verifyeAlertsButtonAndItsNavigation() {
		scrollUp();
		isElementDisplayed(true, "btn_eAlerts");
		clickOnElement("btn_eAlerts");
		verifyNavigationToLoginPromptPage();
	}

	private void verifyNavigationToLoginPromptPage() {
		Assert.assertTrue(getCurrentURL().contains("action/showLogin"));
		isElementDisplayed(true, "link_LoginTest");
		logMessage("Verified: Navigation to Login Prompt page.");
		navigateToPreviousPage();
	}

	public void verifyLinksAvailableOnBlueNavigationBar(Map<String, Object> links) {
		Assert.assertTrue(elements("links_BlueNavigationBar").size()==links.size(), "Assertion failed: All links are not displayed on blue navigation bar.");
		logMessage("Verified: "+links.size()+" links are displayed on the blue navigation bar on journal home page.");
		int i=0;
		for(String expectedLink: links.keySet()){
			isStringMatching(elements("links_BlueNavigationBar").get(i).getAttribute("title"), links.get(expectedLink).toString());
			i++;
		}
		
		logMessage("Verified: All links are displayed on blue navigation bar on journal home page. ");
	}

	public void clickOnListOfIssuesLink() {
		elements("links_BlueNavigationBar").get(0).click();
		logMessage("Info: User clicked on 'List of Issues' link");
	}

	public void clickOnSubmissionReviewLinkAndVerifyNavigation() {
		elements("links_BlueNavigationBar").get(1).click();
		logMessage("Info: User clicked on 'Submission & Review' link");
		verifyNavigationOfClickingOnSubmissionReviewLink();
	}

	private void verifyNavigationOfClickingOnSubmissionReviewLink() {
		isStringMatching(element("h1_PublishingCenter").getText().trim(), "ACS Publishing Center");
		isElementDisplayed(true, "btn_LoginPublishingCenter");
		logMessage("Verified: Navigation to 'ACS Publishing Center' page.");
		navigateToPreviousPage();
	}

	public void clickOnOpenAccessLinkAndVerifyNavigation() {
		elements("links_BlueNavigationBar").get(2).click();
		logMessage("Info: User clicked on 'Open Access' link");
		verifyNavigationOfClickingOnOpenAccessLink();
	}

	private void verifyNavigationOfClickingOnOpenAccessLink() {
	    String url= getCurrentURL();
	    Assert.assertTrue(url.contains("openaccess"), "Assertion Failed: User is not on Open Access page!!!" );
	    Assert.assertTrue(elements("links_OpenAccess").size()==5, "Assertion failed: 5 mouseover links not present on the page.");
	    logMessage("Verified: 5 mouseover links present on the page.");
	    logMessage("Verified:Navigation to Open Access page.");
	    navigateToPreviousPage();
	  }

	public void clickOnAboutTheJournalLinkAndVerifyNavigation(String journal) {
		elements("links_BlueNavigationBar").get(3).click();
		logMessage("Info: User clicked on 'About the Journal' link");
		verifyNavigationOfClickingOnAboutJournalLink(journal);
	}

	private void verifyNavigationOfClickingOnAboutJournalLink(String journal) {
		isElementDisplayed(true, "h1_AboutJournal");
		isElementDisplayed(true, "Img_AboutJournalCover");
		Assert.assertTrue(elements("text_AboutJournal").size()==2);
		logMessage("Info: 2 journal description paragraphs are displayed.");
		Assert.assertTrue(elements("text_AboutJournal").get(0).getText().equals(journal) && elements("text_AboutJournal").get(1).getText().equals(journal));
		logMessage("Verified: Navigated to correct journal's 'About the Journal' page.");
		navigateToPreviousPage();
	}

	public String verifyElementsInCollectionSectionOnHomePage() {
		int randomIndex = random.nextInt(Collection.length);
		String CollectionName=Collection[randomIndex];
		
		logMessage("Info: Verifying elements displayed for Collection: '"+CollectionName+"'....");
		isElementDisplayed(true, "header_Collections",CollectionName);
		isElementDisplayed(true, "txt_DescriptionCollection",CollectionName);
		isElementDisplayed(true, "link_SeeMore",CollectionName);
		
		int ArticlesCount= elementsinDom("Articles_Filmstrip",CollectionName).size()-1;
		
		Assert.assertTrue(ArticlesCount>=5, "Assertion failed: Articles not displayed in collection: '"+CollectionName+"'.");
		logMessage("Verified: "+ArticlesCount+" articles displayed in collection: '"+CollectionName+"'.");
		
		isElementDisplayed(true, "btn_NavigationFilmstrip",CollectionName,"previous");
		isElementDisplayed(true, "btn_NavigationFilmstrip",CollectionName,"next");
		logMessage("Verified: Filmstrip 'Next' and 'Previous' navigation buttons are displayed.");
		
		logMessage("Info: Verifying Metadata for articles displayed in filmstrip....");
		Assert.assertTrue(elementsinDom("title_ArticleFilmstrip",CollectionName).size()==ArticlesCount, "Assertion failed: Title not displayed for every article.");
		logMessage("Verified: Title is displayed for every article.");
		Assert.assertTrue(elementsinDom("txt_AuthorsFilmstrip",CollectionName).size()==ArticlesCount, "Assertion failed: Author names not displayed for every article.");
		logMessage("Verified: Author names are displayed for every article.");
		Assert.assertTrue(elementsinDom("img_AuthorsFilmstrip",CollectionName).size()==ArticlesCount, "Assertion failed: Article Images not displayed for every article.");
		logMessage("Verified: Article images are displayed for every article.");
		Assert.assertTrue(elementsinDom("PubDate_Filmstrip",CollectionName).size()==ArticlesCount, "Assertion failed: Publication date not displayed for every article.");
		logMessage("Verified: Publication date is displayed for every article.");
		Assert.assertTrue(elementsinDom("link_MoreFromCollection",CollectionName).size()==1, "Assertion failed: 'More from Collection' is not displayed at the end of filmstrip for collection: "+CollectionName);
		logMessage("Verified: 'More from Collection' is displayed at the end of filmstrip for collection: "+CollectionName);
		if(CollectionName.equals("Editors’ Choice"))
			ArticlesCount++;
		Assert.assertTrue(elementsinDom("Btns_NavigationBalls",CollectionName).size()==ArticlesCount, "Assertion failed: Navigation balls not displayed for collection filmstrip.");
		logMessage("Verified: Navigation balls are displayed for collection filmstrip.");
		
		return CollectionName;
		
	}

	public void VerifyFunctionalityOfNavigationArrowsInFilmstripSection(String CollectionName) {
		logMessage("Verifying for collection: '"+ CollectionName+"'...");
		
		scrollDown(element("header_Collections",CollectionName));
		
		int max=3;
		if(CollectionName.equals("Editors’ Choice"))
			max=2;
		
		for(int i=0; i<max; i++){
			Assert.assertTrue(elementsinDom("Articles_Filmstrip",CollectionName).get(i).getAttribute("class").contains("active"));
		}
		logMessage("Info : First "+max+" articles are visible before navigation.");
		
		element("btn_NavigationFilmstrip",CollectionName,"next").click();
		logMessage("Info: Clicked on 'Next' navigation arrow.");
		hardWait(1);
		
		Assert.assertFalse(elementsinDom("Articles_Filmstrip",CollectionName).get(0).getAttribute("class").contains("active"));
		logMessage("Verified: First article is not visible after navigation to next slide.");
		
		for(int i=1; i<max+1; i++){
			Assert.assertTrue(elementsinDom("Articles_Filmstrip",CollectionName).get(i).getAttribute("class").contains("active"));
		}
		logMessage("Verified : Next "+max+" articles are visible after navigation to next slide.");
		
		element("btn_NavigationFilmstrip",CollectionName,"previous").click();
		logMessage("Info: Clicked on 'Previous' navigation arrow.");
		hardWait(1);
		
		for(int i=0; i<max; i++){
			Assert.assertTrue(elementsinDom("Articles_Filmstrip",CollectionName).get(i).getAttribute("class").contains("active"));
		}
		logMessage("Verified : First "+max+" articles are visible again after navigation.");
		
		
	}

	public void VerifyFunctionalityOfNavigationGreyBallsInFilmstripSection(String CollectionName) {
		int NavigationBallSize= elements("Btns_NavigationBalls",CollectionName).size();
		int randomIndex = random.nextInt(NavigationBallSize)+1;
		
		List<String> ActiveArticleList = new ArrayList<String>();
		for (WebElement ArticlesNames : elements("Titles_ActiveArticles",CollectionName)) {
			String ArticleNamesList = ArticlesNames.getText().trim();
			ActiveArticleList.add(ArticleNamesList);
		}
		
		elements("Btns_NavigationBalls",CollectionName).get(randomIndex).click();
		hardWait(1);
		logMessage("Info: Clicked on grey navigation ball: "+randomIndex);
		Assert.assertTrue(elements("Btns_NavigationBalls",CollectionName).get(randomIndex).getAttribute("class").contains("active"));
		logMessage("Verified: Grey ball succesfully clicked.");
		
		logMessage("STEP: Verifying if the slide changes after clicking on navigation grey ball.....");
		List<String> ActiveArticleList2 = new ArrayList<String>();
		for (WebElement ArticlesNames : elements("Titles_ActiveArticles",CollectionName)) {
			String ArticleNamesList2 = ArticlesNames.getText().trim();
			ActiveArticleList2.add(ArticleNamesList2);
		}
		Assert.assertNotEquals(ActiveArticleList, ActiveArticleList2, "Assertion Failed: Article slide did not change after clicking on navigation grey ball.");
		logMessage("Verified: Article slide changed after clicking on navigation grey ball.");
		
		elements("Btns_NavigationBalls",CollectionName).get(0).click();
		hardWait(1);
	}
	
	public void clickOnMoreFromCollectionLink(String CollectionName){
		int NavigationBallSize= elements("Btns_NavigationBalls",CollectionName).size();
		elements("Btns_NavigationBalls",CollectionName).get(NavigationBallSize-1).click();
		
		element("link_MoreFromCollection",CollectionName).click();
		logMessage("Info: Clicked on 'See More from Collection' link at the end of the filmstrip.");
		hardWait(2);

	}

	public String VerifyNavigationOfArticleFromFilmstripSection(String CollectionName) {
		logMessage("Info: Article in Collection: "+CollectionName);
		scrollDown(element("header_Collections",CollectionName));
		hardWait(2);
		scrollVertical(-150);
		String title = elementsinDom("title_ArticleFilmstrip",CollectionName).get(0).getText();
		elementsinDom("title_ArticleFilmstrip",CollectionName).get(0).click();
		logMessage("Info: User clicked on Article titled: '"+title+"'.");
		return title;
	}

	public void NavigateToCollectionPage(String CollectionName) {
		scrollDown(element("header_Collections",CollectionName));
		element("link_SeeMore",CollectionName).click();
		logMessage("Info: Clicked on 'See More' link for Collection: "+CollectionName);
		
	}

	public String selectRandomCollection() {
//		int randomIndex = random.nextInt(Collection.length);
//		String CollectionName=Collection[randomIndex];
//		return CollectionName;
		return "Most Read";
	}

	












}
