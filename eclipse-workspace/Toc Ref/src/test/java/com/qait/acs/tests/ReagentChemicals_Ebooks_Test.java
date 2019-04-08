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

public class ReagentChemicals_Ebooks_Test extends BaseTest {

	String ReagentChemicalUrl= "";
	String shareOptions[]= {"Facebook","Twitter","Wechat","LinkedIn","Reddit","Email"};

	public void navigateToReagentChemicalsEbookPage(){
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.clickOnPublicationLink();
		test.homePage.clickOnPubModalLink("Books and Reference");
		test.homePage.clickOnPubModelContent("ACS Reagent Chemicals");
	}
	
	
    @Test
	public void Step01_RC_01_Verify_Navigation_To_ReagentChemicalsEbooks_Page(){
		navigateToReagentChemicalsEbookPage();
		ReagentChemicalUrl=test.reagentChemicalsPage.verifyUserIsOnReagentChemicalsEbookPage();
	}
	
	@Test
	public void Step02_RC_02_Verify_Book_Content_Displayed_On_Header_Of_HomePage(){
		test.reagentChemicalsPage.verifyBookContentDisplayedOnHeader("ACS Reagent Chemicals");
	}
	
	@Test
	public void Step03_RC_03_Verify_Navigation_Of_Links_Displayed_On_Header_Of_HomePage(){
		test.reagentChemicalsPage.verifyNavigationOfAuthorNameLinks();
		test.reagentChemicalsPage.verifyNavigationOfViewAllBooksLink();
		test.reagentChemicalsPage.verifyFunctionalityOfSharingIcon();
	}
	

	@Test
	public void Step04_RC_04_Verify_BookParts_Open_And_Close_Independently_And_Shows_Hides_Chapters(){
		test.reagentChemicalsPage.verifyBookPartsDisplayed();
		int index=test.reagentChemicalsPage.clickOnAnyBookPart();
		test.reagentChemicalsPage.verifyClickingOnAnyBookPartOpensTheBookPart(index);
		test.reagentChemicalsPage.verifyBookChaptersAreDisplayedWhileBookPartIsExpanded(index);
	}

	

	@Test
	public void Step05_RC_05_Verify_Part4_Divided_Into_SubSections(){
		test.openUrl(ReagentChemicalUrl);
		test.reagentChemicalsPage.verifyBookPartsDisplayed();
		test.reagentChemicalsPage.clickOnBookPart4();
		test.reagentChemicalsPage.verifySubSectionsDisplayed();
		test.reagentChemicalsPage.verifySubSectionsAlsoUseAccordionWidget();
		test.reagentChemicalsPage.verifySecondLevelAccordionWidgetHasDifferentColorThanFirstLevel();
	}

	@Test
	public void Step06_RC_06_Verify_SubSections_Of_Part4_Separated_By_Initial_Letter(){
		test.openUrl(ReagentChemicalUrl);
		test.reagentChemicalsPage.verifyBookPartsDisplayed();
		test.reagentChemicalsPage.clickOnBookPart4();
		test.reagentChemicalsPage.verifySubSectionsDisplayed();
		test.reagentChemicalsPage.verifySubSectionsSeparatedByInitialLetter();
		test.reagentChemicalsPage.verifyRegentsForSubsectionStartsWithThatLetter();
		test.reagentChemicalsPage.verifyReagentsAreArrangedInAlphabeticalOrder();
	}
	

	@Test
	public void Step07_RC_07_Verify_Subsection_Not_Present_For_Alphabet_With_No_Articles(){
		test.openUrl(ReagentChemicalUrl);
		test.reagentChemicalsPage.verifyBookPartsDisplayed();
		test.reagentChemicalsPage.clickOnBookPart4();
		test.reagentChemicalsPage.verifySubSectionsDisplayed();
		test.reagentChemicalsPage.verifySubSectionNotPresentForLetterY();
	}

	@Test
	public void Step08_RC_08_Verify_Links_Present_And_Links_Not_Present_Depending_On_The_Book_Part(){
		test.openUrl(ReagentChemicalUrl);
		test.reagentChemicalsPage.verifyBookPartsDisplayed();
		test.reagentChemicalsPage.clickOnAnyBookPart();
		test.reagentChemicalsPage.verifyMandatoryInfoUnderAllChapters();
		test.reagentChemicalsPage.verifyPDFbuttonIsDisplayedForChaptersUnderAllPartsExceptPart0();
		test.reagentChemicalsPage.verifyAbstractAngleDownIconIsDisplayedForChaptersUnderAllPartsExcept0And6();
	}
	
	
	@Test
	public void Step_10_RC_10_Verify_Existance_Of_Elements_On_Chapter_Landing_Page(){
		test.openUrl(ReagentChemicalUrl);
		test.reagentChemicalsPage.verifyBookPartsDisplayed();
		test.reagentChemicalsPage.clickOnAnyBookPart();
		String Chaptername=test.reagentChemicalsPage.verifyBookChaptersDisplayedAndClickOnAnyChapter();
		test.chapterPage.verifyChapterHeaderTitle(Chaptername);
		test.chapterPage.verifyChapterPageContent("Reagent Chemicals");
	}
	
	@Test
	public void Step_11_RC_11_Verify_Navigation_Of_Elements_On_Chapter_Landing_Page(){
		test.openUrl(ReagentChemicalUrl);
		test.reagentChemicalsPage.clickOnAnyBookPart();
		String Chaptername=test.reagentChemicalsPage.verifyBookChaptersDisplayedAndClickOnAnyChapter();
		test.chapterPage.verifyChapterHeaderTitle(Chaptername);
		test.chapterPage.clickOnNextPreviousLinksAndVerify();
		test.chapterPage.verifyFunctionalityOfReturnToBookLink("ACS Reagent Chemicals");
		test.chapterPage.clickOnBookSeriesLinkAndVerify("ACS Reagent Chemicals");
		test.chapterPage.clickOnShareIconAndVerify(shareOptions);
		test.chapterPage.clickOnPDFLinkAndVerifyNavigation();
	}


}