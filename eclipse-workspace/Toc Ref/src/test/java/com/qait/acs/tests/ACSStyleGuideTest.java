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
public class ACSStyleGuideTest extends BaseTest {

	String eBook = "ACS Style Guide";
	String shareOptions[]= {"Facebook","Twitter","LinkedIn","Wechat","Reddit","Email"};


	@Test
	public void Step01_AE_01_Verify_User_LogIn_Into_Application() {
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Books and Reference");
		test.homePage.clickOnPubModelContent("The ACS Style Guide");
	}

	@Test
	public void Step02_AE_35_Verify_The_Existenc_Of_Following_Elements_On_Header_Of_ACS_Style_Guide_ebook_Page() {
		test.eBooksPage.verifyACSGuideToc();
	}

	@Test
	public void Step03_AE_36_Verify_The_Navigation_Of_Following_Links_On_Following_Elements_On_Header_Of_ACS_Style_Guide_ebook_Page() {
		test.eBooksPage.clickAndVerifyOnEditorsAuthorNames();
	}

	@Test
	public void Step04_AE_37_Verify_The_Verify_That_Following_Elements_Are_Present_Under_The_Desired_ebook_Chapters() {
		 test.launchApplication(getYamlValue("baseUrl")+"isbn/9780841239999");
		test.eBooksPage.verifyDesiredeBookChaptersTOCPage();
	}

	@Test
	public void Step05_AE_38_Verify_That_Following_Elements_Under_The_Desired_ebook_Chapter_Work_Properly() {
		// test.launchApplication(getYamlValue("baseUrl")+"isbn/9780841239999");
		test.eBooksPage.verifyIssueItemButtonsList();
	}

	@Test
	public void Step06_AE_39_Verify_The_Functionality_Of_The_Following_Elements_Available_On_Chapter_Landing_Page() {
		String selectedeBook = test.eBooksPage.eBookTitle();
		test.eBooksPage.verifyACSGuideToc();
		String chapter= test.eBooksPage.clickOnChaptertocEbookTitle();
		test.chapterPage.verifyChapterPageContent(eBook);
		test.chapterPage.clickOnNextPreviousLinksAndVerify();
		test.chapterPage.clickOnPDFLinkAndVerifyNavigation();
		test.chapterPage.clickOnReturnToBookAndVerify(selectedeBook);
		test.navigateBack();
//		test.chapterPage.verifyFunctionalityOfHoverOnAuthornames();
		test.chapterPage.clickOnShareIconAndVerify(shareOptions);
	}
}