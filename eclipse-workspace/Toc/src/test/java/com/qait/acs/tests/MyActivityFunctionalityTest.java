package com.qait.acs.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;

import org.testng.annotations.Test;

public class MyActivityFunctionalityTest extends BaseTest {

	String journal = "Accounts of Chemical Research";

	@Test
	public void Step01_Verify_Navigation_To_Any_Article_Page() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.verifyMyActivityButton();
	}

	@Test
	public void Step02_Verify_Two_Column_Open_After_Clicking_On_My_Activity_Button() {
		test.homePage.clickOnMyActivityButton();
		test.homePage.verifyMyActivityTwoColumn();
	}

	@Test
	public void Step03_Verify_Recently_Viewed_Column_Default_Message() {
		test.homePage.verifyDefaultRecentlyViewedText();
	}

	@Test
	public void Step04_Verify_RECOMMENDATIONS_Column_Default_Message() {
		test.homePage.verifyNoRecommendedWhenNotLoggedInText();
	}

	@Test
	public void Step05_Verify_Recently_Viewed_Article_Details_Under_Recently_Viewed_Column() {
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Journals");
		test.homePage.selectJournal(journal);
		String CollectionName = test.journalPage.selectRandomCollection();
		String ArticleTitle = test.journalPage.VerifyNavigationOfArticleFromFilmstripSection(CollectionName);
		test.articlePage.verifyNavigationToArticleLandingPage(ArticleTitle);
		test.pageRefresh();
		Step02_Verify_Two_Column_Open_After_Clicking_On_My_Activity_Button();
		test.homePage.verifyPreviouslyVisitedRecentlyViewedArticle(ArticleTitle);
	}

	@Test
	public void Step06_Verify_Recent_Viewed_Articles_Under_Recently_Viewed_Column() {
		test.homePage.clickOnMyActivityButton();
		test.articlePage.clickOnNextLink();
//		String articleName = test.articlePage.getArticleTitle();
		for (int i = 0; i < 3; i++)
			test.articlePage.clickOnNextLink();
		test.pageRefresh();
		test.homePage.clickOnMyActivityButton();
		test.homePage.verifyTotalRecentlyViewedList();
		test.homePage.clickOnMyActivityButton();
		for (int i = 0; i < 2; i++)
			test.articlePage.clickOnNextLink();
		test.pageRefresh();
		test.homePage.clickOnMyActivityButton();
		test.homePage.verifyTotalRecentlyViewedList();
		test.homePage.verifyRecentlyViewedListInQueueOrder();
	}
	
	@Test
	public void Step07_Verify_RECOMMENDATIONS_Column_Default_Message_User_LoggedIn(){
		test.homePage.VerifyAndClickOnLoginLinkAtTopRight();
		test.loginPage.verifyNavigationToLoginPage();
		test.loginPage.EnterCredentials(getYamlValue("users.user1.username"), getYamlValue("users.user1.password"));
		test.homePage.verifyUserIsOnACSPubHomePage();
		test.homePage.clickOnMyActivityButton();
		test.homePage.verifyDefaultRecommendedTextLoggedIn();
	}

}