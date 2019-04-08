package com.qait.acs.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;

public class BooleanSearch_Test extends BaseTest {
	
	
	String SearchTerm1="", SearchTerm2="", AuthorName1="", AuthorName2=""; 
	
	public void NavigateToAdvanceOptions(){
		test.launchApplication(getYamlValue("baseUrl"));
		test.homePage.verifySearchBoxIsDisplayed();
		test.homePage.enterSearchTermAndClickOnSearchIcon("Cell");
		test.searchResultsPage.verifyRefineSearchButtonAndArrow();
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
	}
	
	@Test
	public void Step01_BS_01_Verify_Search_With_Percentage_Operator_In_Title() {
		NavigateToAdvanceOptions();
	    test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title", "","Cell%", "");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
	    test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Title", "","Cell%", "wild");
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title", "","Cell%", "quotes");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Title","","Cell%", "wild");
	}
	
	@Test
	public void Step02_BS_02_Verify_Advanced_Search_With_AND_Operator() {
		SearchTerm1=getYamlValue("Search.booleanSearchTerm1");
		SearchTerm2=getYamlValue("Search.booleanSearchTerm2");
		AuthorName1=getYamlValue("Search.authorName1");
		AuthorName2=getYamlValue("Search.authorName2");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Anywhere",	SearchTerm1,SearchTerm2, "AND");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title",SearchTerm1,SearchTerm2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Title",SearchTerm1,SearchTerm2,"AND");

		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Author",	AuthorName1, AuthorName2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Author",AuthorName1, AuthorName2, "AND");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Abstract",SearchTerm1,SearchTerm2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Abstract",	SearchTerm1,SearchTerm1, "AND");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Figure/Table Caption",SearchTerm1,SearchTerm2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Figure/Table Caption",	SearchTerm1,SearchTerm2, "AND");

	}
	
	@Test
	public void Step03_BS_03_Verify_Advanced_Search_With_Ampersand_Operator() {
		SearchTerm1=getYamlValue("Search.booleanSearchTerm1");
		SearchTerm2=getYamlValue("Search.booleanSearchTerm2");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, "&");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Anywhere",	SearchTerm1,SearchTerm2, "AND");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title",SearchTerm1,SearchTerm2, "&");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Title",SearchTerm1,SearchTerm2,"AND");

		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Abstract",SearchTerm1,SearchTerm2, "&");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Abstract",	SearchTerm1,SearchTerm1, "AND");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Figure/Table Caption",SearchTerm1,SearchTerm2, "&");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Figure/Table Caption",	SearchTerm1,SearchTerm2, "AND");
	}

	@Test
	public void Step04_BS_04_Verify_Advanced_Search_With_OR_Operator() {
		SearchTerm1=getYamlValue("Search.booleanSearchTerm1");
		SearchTerm2=getYamlValue("Search.booleanSearchTerm2");
		AuthorName1=getYamlValue("Search.authorName1");
		AuthorName2=getYamlValue("Search.authorName2");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, "OR");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Anywhere",	SearchTerm1,SearchTerm2, "OR");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title",SearchTerm1,SearchTerm2, "OR");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Title",SearchTerm1,SearchTerm2,"OR");

		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Author",	AuthorName1, AuthorName2, "OR");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Author",AuthorName1, AuthorName2, "OR");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Abstract",SearchTerm1,SearchTerm2, "OR");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Abstract",	SearchTerm1,SearchTerm1, "OR");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Figure/Table Caption",SearchTerm1,SearchTerm2, "OR");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Figure/Table Caption",	SearchTerm1,SearchTerm2, "OR");
	}

	
	@Test
	public void Step05_BS_05_Verify_Advanced_Search_With_Plus_Operator() {
		SearchTerm1=getYamlValue("Search.booleanSearchTerm1");
		SearchTerm2=getYamlValue("Search.booleanSearchTerm2");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, "+");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Anywhere",	SearchTerm1,SearchTerm2, "OR");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title",SearchTerm1,SearchTerm2, "+");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Title",SearchTerm1,SearchTerm2,"OR");

		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Abstract",SearchTerm1,SearchTerm2, "+");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Abstract",	SearchTerm1,SearchTerm1, "OR");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Figure/Table Caption",SearchTerm1,SearchTerm2, "+");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Figure/Table Caption",	SearchTerm1,SearchTerm2, "OR");
	}
	
	@Test
	public void Step06_BS_06_Verify_Same_Result_for_NOT_and_MINUS_Boolean_Operator_Between_Two_Search_Terms() {
		SearchTerm1=getYamlValue("Search.booleanSearchTerm1");
		SearchTerm2=getYamlValue("Search.booleanSearchTerm2");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, "NOT");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Anywhere",	SearchTerm1,SearchTerm2, "NOT");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, "-");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Anywhere",	SearchTerm1,SearchTerm2, "NOT");
	}


	@Test
	public void Step07_BS_07_Verify_Result_On_Searching_Multiple_Terms_Without_Boolean_Operator() {
		SearchTerm1=getYamlValue("Search.booleanSearchTerm1");
		SearchTerm2=getYamlValue("Search.booleanSearchTerm2");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, " ");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Anywhere",	SearchTerm1,SearchTerm2, " ");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title",SearchTerm1,SearchTerm2, " ");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Title",SearchTerm1,SearchTerm2," ");

		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Abstract",SearchTerm1,SearchTerm2, " ");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Abstract",	SearchTerm1,SearchTerm1, " ");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Figure/Table Caption",SearchTerm1,SearchTerm2, " ");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Figure/Table Caption",	SearchTerm1,SearchTerm2, " ");
	}

	@Test
	public void Step08_BS_08_Verify_Advanced_Search_When_Search_Terms_Wrapped_In_Quotes() {
		SearchTerm1=getYamlValue("Search.booleanSearchTerm3");
		SearchTerm2=getYamlValue("Search.booleanSearchTerm4");
		AuthorName1=getYamlValue("Search.authorName3");
		AuthorName2=getYamlValue("Search.authorName7");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, "quotes");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Anywhere",	SearchTerm1,SearchTerm2, "quotes");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title",SearchTerm1,SearchTerm2, "quotes");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Title",SearchTerm1,SearchTerm2,"quotes");

		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Author",	AuthorName1, AuthorName2, "quotes");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Author",AuthorName1, AuthorName2, "quotes");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Abstract",SearchTerm1,SearchTerm2, "quotes");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Abstract",	SearchTerm1,SearchTerm1, "quotes");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Figure/Table Caption",SearchTerm1,SearchTerm2, "quotes");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.assertThatValidSearchResultsAreDisplayed("Figure/Table Caption",	SearchTerm1,SearchTerm2, "quotes");
	}

	
	@Test
	public void Step09_S09_Verify_Advanced_Search_Results_Have_Snippets_In_Bold_After_Search_With_Quotes() {
		SearchTerm1=getYamlValue("Search.booleanSearchTerm1");
		SearchTerm2=getYamlValue("Search.booleanSearchTerm2");
		AuthorName1=getYamlValue("Search.authorName1");
		AuthorName2=getYamlValue("Search.authorName2");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Anywhere",SearchTerm1,SearchTerm2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyThatRelevantSearchTermIsHighlightedInField("Anywhere",SearchTerm1,SearchTerm2, "AND");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Title",SearchTerm1,SearchTerm2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyThatRelevantSearchTermIsHighlightedInField("Title",SearchTerm1,SearchTerm2, "AND");

		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Author",AuthorName1,AuthorName2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyThatRelevantSearchTermIsHighlightedInField("Author",AuthorName1,AuthorName2, "AND");
		
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Figure/Table Caption",SearchTerm1,SearchTerm2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyThatRelevantSearchTermIsHighlightedInField("Figure/Table Caption",SearchTerm1,SearchTerm2, "AND");
	
		test.searchResultsPage.clickAndVerifyTabUnderOnRefineSearch("Advanced Options");
		test.searchResultsPage.enterBooleanSearchTermInSpecifiedFieldOnAdvancedSearch("Abstract",SearchTerm1,SearchTerm2, "AND");
	    test.searchResultsPage.clickOnSearchButtonUnderAdvancedOptionTab();
		test.searchResultsPage.verifyThatRelevantSearchTermIsHighlightedInField("Abstract",SearchTerm1,SearchTerm2, "AND");
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}