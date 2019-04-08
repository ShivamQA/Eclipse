package com.qait.acs.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import static com.qait.automation.utils.YamlReader.getYamlValue;

@Listeners({ com.qait.acs.tests.CustomReportCreator.class })
public class eBooksSymposiumSeriesTest extends BaseTest {

	String searchTxt = "Chemistry";
	String eBook = "ACS Symposium Series";
	String selectedeBook;
	String shareOptions[] = { "Facebook", "Twitter", "LinkedIn", "Wechat", "Reddit", "Email" };

	@Test
	public void Step01_AE_01_Verify_User_LogIn_Into_Application() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Books and Reference");
		test.homePage.clickOnPubModelContent(eBook);

	}

	@Test
	public void Step02_AE_09_Verify_The_Presence_And_Navigation_Of_Following_Elements_On_ACS_Symposium_Series_eBook_Page() {
		test.eBooksPage.verifyHeaderOnAboutACSeBooksPage(eBook);
		test.eBooksPage.verifyBooksRightHomeHeaderList("About ACS eBooks");
		test.eBooksPage.verifyBooksRightHomeHeaderList("Subscriptions");
		test.eBooksPage.verifyBooksRightHomeHeaderList("Recommend to Your Librarian");
		test.eBooksPage.verifyTextBoxOnebookPage("Find a Book");
		test.eBooksPage.clickOnBooksRightHomeHeaderList("About ACS eBooks");
		test.eBooksPage.verifyPageNavigationToDefaultBookSeries();
		test.eBooksPage.navigateToPreviousPage();
		test.eBooksPage.clickOnBooksRightHomeHeaderList("Subscriptions");
		test.eBooksPage.verifySubscriptionsPage();
		test.eBooksPage.navigateToPreviousPage();
		test.eBooksPage.clickOnBooksRightHomeHeaderList("Recommend to Your Librarian");
		test.eBooksPage.verifyRecommendedToYourLibrarianPage();
		test.eBooksPage.navigateToPreviousPage();
	}

	@Test
	public void Step03_AE_10_Verify_Find_A_Book_Section_On_ACS_Symposium_Series_eBooks_Page_With_Its_Other_Tabs() {
		test.eBooksPage.verifyTextBoxOnebookPage("Find a Book");
		test.eBooksPage.verifyPresentationTabList("By Year");
		test.eBooksPage.verifyPresentationTabList("By Sponsoring Divisions");
		test.eBooksPage.verifyPresentationTabList("Browse by Subject");
	}

	@Test
	public void Step04_AE_11_Verify_Search_In_Find_A_Book_Section_On_ACS_Symposium_Series_eBooks() {
		test.eBooksPage.verifyTextBoxOnebookPage("Find a Book");
		test.eBooksPage.enterTextInSearchBoxFindABook(searchTxt, "Find a Book");
		test.eBooksPage.clickOnSearchFindABookButton();
		test.eBooksPage.verifySearchResultMessageForFindABook(searchTxt);
		test.eBooksPage.verifySearchBooksResultForFindABook(searchTxt);
		test.eBooksPage.verifyAndClickOnNextOrPreviousButtonOnFindABookSearchResultPage("NEXT");
		test.eBooksPage.verifyNextPageSearchBooksResultForFindABook(searchTxt);
		test.eBooksPage.navigateToPreviousPage();
		test.eBooksPage.navigateToPreviousPage();
	}

	// Failing due to default current year is not 2019 inside the dropdown
	// @Test
	public void Step05_AE_12_Verify_User_Is_Able_To_Search_For_A_Book_Title_Using_By_Year_Tab_Under_Find_A_Book_Section() {
		test.eBooksPage.verifySelectedFindABookSelctionIsSelected("By Year");
		test.eBooksPage.verifyByDefaultCurrentYear();
		test.eBooksPage.verifyTextShowingResultCount();
		test.eBooksPage.verifySearchBooksResultForByYear("2018");
		test.eBooksPage.selectSpecificDropDownYear("2016");
	}

	@Test
	public void Step06_AE_13_Verify_User_Is_Able_To_Search_For_A_Book_Title_Using_By_Sponsoring_Division_Under_Find_A_Book_Section() {
		test.eBooksPage.verifyPresentationTabList("By Sponsoring Divisions");
		test.eBooksPage.clickPresentationTabList("By Sponsoring Divisions");
		test.eBooksPage.verifySelectedFindABookSelctionIsSelected("By Sponsoring Divisions");
	}

	@Test
	public void Step07_AE_14_Verify_User_Is_Able_To_Search_For_A_Book_Title_Using_By_Sponsoring_Division_Under_Find_A_Book_Section() {
		test.eBooksPage.selectSpecificDropDownSponsorDivision("American Chemical Society");
		test.eBooksPage.verifyPresentationTabList("By Sponsoring Divisions");
		test.eBooksPage.clickOnSponsorDivisionGoButton();
		test.eBooksPage.verifySelectedSponsorDivisionOption("American Chemical Society");
		test.eBooksPage.verifyDistincteBooksUnderSelectedSponsorDivisionOption("American Chemical Society");
		test.eBooksPage.verifyeBooksContainSelectedSponsorDivisionOption("American Chemical Society");
	}

	@Test
	public void Step08_AE_15_Verify_That_Application_Doesnot_Display_Duplicate_Division_Under_Sponsoring_Division_Drop_Down_For_ACS_Symposium_Series_eBooks() {
		test.eBooksPage.verifyDistinctDropDownOptionsForSponsoringDivision();
	}

	@Test
	public void Step09_AE_17_Verify_The_Existence_Of_Elements_On_Table_Of_Content_Page_For_ebook() {
		selectedeBook = test.eBooksPage.clickOnAnyBookUsingIndexValue();
		test.eBooksPage.verifyeBookTOCPageContent();
	}

	// Failing due to search result page contains no data about author

	@Test
	public void Step10_AE_18_Verify_The_Navigation_Of_Following_Links_On_Table_Of_Content_Page_for_ebook() {
		// test.eBooksPage.clickAndVerifyOnEditorsAuthorNames();
		test.eBooksPage.clickAndVerifySeeAllTechnicalDivisionsOnEbook();
		test.eBooksPage.verifyPreviousOrNextOrViewAllBookNavigation("View all books");
		test.eBooksPage.verifyPreviousOrNextOrViewAllBookNavigation("Previous book");
		test.eBooksPage.verifyPreviousOrNextOrViewAllBookNavigation("Next book");
	}

	@Test
	public void Step11_AE_19_Verify_That_Following_Elements_Are_Present_Under_The_Desired_ebook_Chapters() {
		test.eBooksPage.verifyDesiredeBookChaptersTOCPage();
	}

	@Test
	public void Step12_AE_20_Verify_That_Links_Under_The_Desired_ebook_Chapter_Work_Properly() {
		test.eBooksPage.verifyIssueItemButtonsList();
	}

	@Test
	public void Step13_AE_21_Verify_That_Elements_Available_On_Chapter_Landing_Page() {
		test.launchApplication("https://achs-stag.literatumonline.com/doi/book/10.1021/bk-2018-1310");
		String chapter = test.eBooksPage.clickOnChaptertocEbookTitle();
		test.chapterPage.verifyChapterPageContent(eBook);
		test.chapterPage.clickOnNextPreviousLinksAndVerify();
		test.chapterPage.clickOnPDFLinkAndVerifyNavigation();
		test.chapterPage.clickOnReturnToBookAndVerify(selectedeBook);
		test.navigateBack();
		// test.chapterPage.verifyFunctionalityOfHoverOnAuthornames();
		test.chapterPage.clickOnShareIconAndVerify(shareOptions);

		test.chapterPage.verifyPublicationDropBlock();
	}

}