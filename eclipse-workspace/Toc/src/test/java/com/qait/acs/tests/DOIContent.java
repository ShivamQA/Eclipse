package com.qait.acs.tests;

import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.getYamlValues;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class DOIContent {
	private String DOI = "";
	protected TestSessionInitiator test;
	protected String urlForTemporaryStorage = null;
	protected int counterForTests;

	@Factory(dataProviderClass = com.qait.automation.utils.SampleDataProvider.class, dataProvider = "fileDataProvider")
	public DOIContent(String DOI) {
		this.DOI = DOI;
		System.out.println("DOI: " + DOI);
	}

	@BeforeClass
	public void Start_Test_Session() {
		counterForTests = 0;
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		// test.launchApplication(getYamlValue("baseUrl"));
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}

//	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result, Method method) {
		counterForTests = test.takescreenshot.incrementcounterForTests(counterForTests, result);
		test.takescreenshot.takeScreenShotOnException(result, counterForTests, method.getName());
	}

	@AfterClass
	public void close_Test_Session() throws IOException {
		test.closeBrowserSession();
	}

	@Test
	public void Step29_AJ_29_Verify_Functionality_Of_Elements_On_Article_Landing_Page() {
		test.launchApplication("https://achs-stag.literatumonline.com/doi/" + DOI);
		test.articlePage.verifyElementsOnArticleLandingPage("Current Issue");
		test.chapterPage.verifyPublicationDate();
		test.chapterPage.verifyPublicationDropBlock();
		String Page = "Current Issue";
		test.articlePage.verifyCiteThisInfoOnArticlePage(Page);
////		test.articlePage.verifyFunctionalityOfHoverOnAuthornames();
		
		
//		test.articlePage.verifyReferenceSection();
//		int countRef = test.articlePage.totalReferencesUnderReferencesSection();
//		test.articlePage.verifyCiteBySection();
//		int countCite = test.articlePage.totalCiteByDataCount();
//		test.articlePage.verifyTextForNoCiteByContent(countCite);
//		test.launchApplication("https://pubs.acs.org/doi/" + DOI);
//		test.articlePage.verifyReferencesCountOnPubsAndStag(countRef);
	}

}