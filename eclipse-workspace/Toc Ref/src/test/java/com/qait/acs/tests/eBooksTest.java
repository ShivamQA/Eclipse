package com.qait.acs.tests;

import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import com.qait.automation.TestSessionInitiator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.Test;

@Listeners({ com.qait.acs.tests.CustomReportCreator.class })
public class eBooksTest extends BaseTest {

	@Test()
	public void Step01_Verify_User_LogIn_And_Clicked_On_eBook() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Books and Reference");
	}

	@Test
	public void Step02_AE_01_Verify_The_Existence_Of_Following_Elements_And_Links_For_Header_On_About_ACS_eBooks_Page() {
//		Step01_Verify_User_LogIn_And_Clicked_On_eBook();
		test.homePage.clickOnPubModelContent("ACS Symposium Series");
		test.eBooksPage.verifyHeaderOnAboutACSeBooksPage("ACS Symposium Series");
		test.eBooksPage.clickOnBooksRightHomeHeaderList("About ACS eBooks");
		test.eBooksPage.identifiers("For Authors, Reviewers & Editors");
		test.eBooksPage.identifiers("For Librarians & Account Managers");
	}

	@Test
	public void Step03_AE_03_Verify_The_Existenc_Of_ACS_Symposium_Series_Section_And_Its_Links_On_About_ACS_eBooks_Page() {
		String block = "ACS Symposium Series";
		String[] blockList = { "Subscription Information for Librarians", "Recommend to your library" };
		test.eBooksPage.verifyBookMainBlock(block);
		test.eBooksPage.verifyLinksUnderBookMainBlock(block, blockList);
	}

	// On Hold Navigation pages not working
//	@Test
	public void Step04_AE_04_Verify_The_Navigation_Of_Links_Under_ACS_Symposium_Series_Section_On_About_ACS_eBooks_Page() {
		String block = "ACS Symposium Series";
		test.eBooksPage.clickOnLinksUnderBookMainBlock(block, "Subscription Information for Librarians");
		test.eBooksPage.verifySubscriptionsPage();
		test.eBooksPage.navigateToPreviousPage();
		test.eBooksPage.clickOnLinksUnderBookMainBlock(block, "Recommend to your library");
		test.eBooksPage.verifyRecommendedToYourLibrarianPage();
		test.eBooksPage.navigateToPreviousPage();
		test.eBooksPage.clickOnLinksUnderBookMainBlock(block, "Browse & Search the Series");
		test.eBooksPage.verifyHeaderOnAboutACSeBooksPage("ACS Symposium Series");
		test.eBooksPage.verifyBooksRightHomeHeaderList("About ACS eBooks");
		test.eBooksPage.navigateToPreviousPage();
	}

	@Test
	public void Step05_AE_05_Verify_The_Existenc_Of_Advances_In_Chemistry_Section_And_Its_Links_On_About_ACS_eBooks_Page() {
		String block = "Advances in Chemistry";
		String[] blockList = { "Subscription Information for Librarians", "Recommend to your library" };
		test.eBooksPage.verifyBookMainBlock(block);
		test.eBooksPage.verifyLinksUnderBookMainBlock(block, blockList);
	}

	// On Hold Navigation pages not working
//	@Test
	public void Step06_AE_06_Verify_The_Navigation_Of_Links_Under_Advances_In_Chemistry_Section_On_About_ACS_eBooks_Page() {
		String block = "Advances in Chemistry";
		test.eBooksPage.clickOnLinksUnderBookMainBlock(block, "Subscription Information for Librarians");
		test.eBooksPage.verifySubscriptionsPage();
		test.eBooksPage.navigateToPreviousPage();
		test.eBooksPage.clickOnLinksUnderBookMainBlock(block, "Recommend to your library");
		test.eBooksPage.verifyRecommendedToYourLibrarianPage();
		test.eBooksPage.navigateToPreviousPage();
		test.eBooksPage.clickOnLinksUnderBookMainBlock(block, "Browse & Search the Series");
		test.eBooksPage.verifyHeaderOnAboutACSeBooksPage("Advances in Chemistry");
		test.eBooksPage.verifyBooksRightHomeHeaderList("About ACS eBooks");
		test.eBooksPage.navigateToPreviousPage();

	}

	@Test
	public void Step07_AE_07_Verify_The_Existenc_Of_Other_ACS_Publications_And_Its_Links_On_About_ACS_eBooks_Page() {
		String title = "Other ACS Publications";
		String[] titleList = { "ACS Reagent Chemicals", "ACS Divisional Proceedings", "ACS Style Guide" };
		test.eBooksPage.verifyBookHomeTitle(title);
		test.eBooksPage.verifyLinksUnderOtherACSPublicationsBlock(title, titleList);
	}

	// On Hold Navigation pages not working
//	@Test
	public void Step08_AE_08_Verify_The_Navigation_Of_Links_Under_Other_ACS_Publications_On_About_ACS_eBooks_Page() {
		String title = "Other ACS Publications";
		// test.eBooksPage.clickLinksUnderOtherACSPublicationsBlock(title,"ACS
		// Reagent Chemicals");
		test.eBooksPage.clickLinksUnderOtherACSPublicationsBlock(title, "ACS Divisional Proceedings");
		test.eBooksPage.verifyNavigationToACSDivisionalProceedings();
		test.eBooksPage.clickLinksUnderOtherACSPublicationsBlock(title, "ACS Style Guide");
		test.eBooksPage.verifyACSGuideTocPage();
		test.eBooksPage.navigateToPreviousPage();
	}
}