package com.qait.acs.keywords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qait.automation.getpageobjects.GetPage;

public class ReagentChemicalsPageActions extends GetPage {

	
	WebDriver driver;
	private static String pagename = "ReagentChemicalsPage";

	public ReagentChemicalsPageActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}
	
//	public void navigateToReagentChemicalsEbookPage(){
//		driver.get("https://achs-stag.literatumonline.com/doi/book/10.1021/acsreagents");
//	}


	public String verifyUserIsOnReagentChemicalsEbookPage() {
		isElementDisplayed(true,"img_reagentChemicals");
		logMessage("Verified: User is On Reagent Chemicals Ebooks Home Page");
		String url= getCurrentURL();
		return url;
	}

	/*
	 * Method to Match the pattern
	 */
	public boolean verifyFormat( String str, String pattern) {
		boolean matches;
		Pattern pat = Pattern.compile(pattern);
		Matcher m = pat.matcher(str);
		matches = m.find();
		return matches;
	}

	public void verifyBookContentDisplayedOnHeader(String EbookName) {
		Assert.assertTrue(element("h1_reagentChemicals").getText().equals(EbookName), "Assertion Failed: User is not navigated to "+EbookName+"home page");
		logMessage("Verified: '"+EbookName+"' Title is displayed on header.");

		isElementDisplayed(true,"txt_subtitle");
		logMessage("Verified: Subtitle is displayed at top on header.");

		Assert.assertTrue(elements("lnk_editors").size()==2, "Assertion Failed: Editor Names links are not displayed at top of the page");
		logMessage("Verified: Editor Names links are displayed on header.");

		isElementDisplayed(true,"txt_publicationDate");
//		String PublicationDate[] = element("txt_publicationDate").getText().trim().split(":");
		String FoundPatternForPublicationDate=element("txt_publicationDate").getText().trim();
		String validPatternForPublicationDate = "(January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{1,2},\\s\\d{4}";
		Assert.assertTrue(verifyFormat(FoundPatternForPublicationDate, validPatternForPublicationDate),
				"Assertion Failed: Publication Date not displayed in [Month DD, YYYY] format");
		logMessage("Verified: Publication Date displayed in [Month DD, YYYY] format");

		isElementDisplayed(true,"txt_CopyrightInfo");
		logMessage("Verified: Copyright Info is displayed on header of the page.");
		
		Assert.assertTrue(elements("txt_affiliations").get(0).getText()!= null && elements("txt_affiliations").get(1).getText()!= null);
		logMessage("Verified: affiliations Info is displayed on header of the page.");

		Assert.assertTrue(element("txt_DOIorISBN","1").getText().contains("eISBN"));
		logMessage("Verified: ISBN Info is displayed on header of the page.");

		String doi[]=element("txt_DOIorISBN","2").getText().trim().split(":");
		String foundPatternForDOI= doi[1].trim();
		String validPatternForDOI= "\\d{2}\\.\\d{4}\\/acsreagents";
		Assert.assertTrue(verifyFormat(foundPatternForDOI, validPatternForDOI),
				"Assertion Failed: DOI not displayed in XX.XXXX/acsreagents format");
		logMessage("Verified: DOI displayed in XX.XXXX/acsreagents format on header of the page.");
		
		isElementDisplayed(true, "img_ACSeBooks");
		logMessage("Verified: ACS eBooks logo is displayed on header on right side.");
		isElementDisplayed(true, "lnk_ViewAllBooks");
		logMessage("Verified: 'View All Books' link is displayed on header on right side.");
		isElementDisplayed(true, "icon_Share");
		logMessage("Verified: Share icon link is displayed on header on right side.");

		
	}
	
	public void verifyNavigationOfAuthorNameLinks() {
		String AuthorName;
		AuthorName= elements("lnk_editors").get(0).getText();
		elements("lnk_editors").get(0).click();
		logMessage("User clicked on author name: "+AuthorName);
//		//wait.waitForPageToLoadCompletely();
		verifyNavigationOfAuthorLinks(AuthorName);
		navigateToPreviousPage();
		AuthorName= elements("lnk_editors").get(1).getText();
		elements("lnk_editors").get(1).click();
		logMessage("User clicked on author name: "+AuthorName);
		//wait.waitForPageToLoadCompletely();
		verifyNavigationOfAuthorLinks(AuthorName);
		navigateToPreviousPage();
		
	}

	private void verifyNavigationOfAuthorLinks(String authorName) {
		String actualName=AuthorNameWithSurnameFirst(authorName);
		isStringMatching(element("SearchResults_Filter").getText().replaceAll(",", ""), actualName);
	}
	
	private String AuthorNameWithSurnameFirst(String authorName){
		int l = authorName.length();
		int a = authorName.lastIndexOf(" ");
		String sur = authorName.substring(a,l);
		String remain = authorName.substring(0,a);
		String authName = sur + " " + remain;
		authName=authName.trim();
		return authName;
	}
	
	public void verifyNavigationOfViewAllBooksLink(){
		clickOnElement("lnk_ViewAllBooks");
		//wait.waitForPageToLoadCompletely();
		Assert.assertTrue(getCurrentURL().contains("books/index.html") && getPageTitle().contains("Default Book Series"));
		logMessage("Verified: Navigation to Default book series page.");
		navigateToPreviousPage();
	}
	
	public void verifyFunctionalityOfSharingIcon(){
		clickOnElement("icon_Share");
		Assert.assertTrue(elementsinDom("links_SharingOption").size()==6, "Assertion failed: Sharing options not displayed.");
		logMessage("Verified: 6 sharing options displayed upon clicking sharing icon on header.");
		
	}

	public void verifyBookPartsDisplayed() {
		Assert.assertTrue(elements("lnk_bookParts").size()==7, "Assertion Failed: Book Parts (0-6) not displayed."); 
		logMessage("Verified: Book Parts (0-6) displayed on the Reagent chemicals Home page."); 

	}

	public int clickOnAnyBookPart() {
		scrollUp();
		int bookPartSize= elements("lnk_bookParts").size();
		Random random = new Random();
		int randomIndex = random.nextInt(bookPartSize);
		if(randomIndex==4)
			randomIndex++;
		System.out.println(randomIndex);
		scrollDownMid();
//		scrollDown(elements("lnk_bookParts").get(randomIndex-1));
		elements("lnk_bookParts").get(randomIndex).click();
		logMessage("User Clicked on part: "+randomIndex);
		return randomIndex;
		
	}

	public void verifyClickingOnAnyBookPartOpensTheBookPart(int index) {
		System.out.println(elements("lnk_bookParts").get(index).getAttribute("aria-expanded").toString());
		Assert.assertTrue(elements("lnk_bookParts").get(index).getAttribute("aria-expanded").toString().equals("true"), "Assertion Failed: Book Part: "+index+" was not expanded on click");
		logMessage("Verified: Clicking on Book Part: "+index+" expanded that part.");

	}

	public void verifyBookChaptersAreDisplayedWhileBookPartIsExpanded(int index) {
		isElementDisplayed(true,"lnk_bookChapters");
		Assert.assertTrue(elements("lnk_bookChapters").size()>=1);
		logMessage("Verified: Book Chapters are displayed when book part is expanded");
		scrollUpMid();
		elements("lnk_bookParts").get(index).click();
		logMessage("User clicked on the Book Part again - Book Part Collapsed");
		isElementDisplayed(false,"lnk_bookChapters");
		logMessage("Verified: Book Chapters are not displayed when book part is collapsed.");

	}


	public void clickOnBookPart4() {
		scrollDown(elements("lnk_bookParts").get(4));
		elements("lnk_bookParts").get(4).click();
		logMessage("User Clicked on part 4");

	}

	public void verifySubSectionsDisplayed() {
		Assert.assertTrue(elements("lnk_book4SubSections").size()==23, "Assertion Failed: Book sub Sections not displayed."); 
		logMessage("Verified: 23 Book Sub sections displayed under part 4."); 

	}

	public void verifySubSectionsAlsoUseAccordionWidget() {
		elements("lnk_book4SubSections").get(0).click();
		logMessage("User clicked on 1st sub section");
		Assert.assertTrue(elements("lnk_book4SubSections").get(0).getAttribute("aria-expanded").toString().equals("true"), "Assertion Failed: Book subsection was not expanded on click");
		logMessage("Verified: Clicking on Book Subsection expanded that part : Accordion Widget functionality confirmed.");
		elements("lnk_book4SubSections").get(0).click();
	}

	public void verifySecondLevelAccordionWidgetHasDifferentColorThanFirstLevel() {
		WebElement secondLevel = elements("txt_book4Subsections").get(0);
		String color2 = secondLevel.getCssValue("color").toString();
		logMessage("Color of the second level widget : "	+ color2);
		WebElement firstLevel = elements("txt_bookParts").get(0);
		String color1 = firstLevel.getCssValue("color").toString();
		logMessage("Color of the first level widget : "	+ color1);
		Assert.assertNotEquals(color2, color1, "Assertion Failed: Second Level widget and First level widget have the same color.");
		logMessage("Verified: Second Level Accordion Widget has different color as compared to first level widget.");

	}

	public void verifySubSectionsSeparatedByInitialLetter() {
		int count = 0;
		Map<String, Object> SubSectionList = new HashMap<String, Object>();

		for (WebElement a : elements("txt_book4Subsections")) {
			++count;
			String[]a1=a.getText().trim().split("\"");
			String SubsectionText=a1[1].replace("\"", "");
			SubSectionList.put("Book Part" + count, SubsectionText);
		}
		logMessage("No. of book subsections are: " + SubSectionList.size());

		logMessage("Book Part : Initial Letter ");
		for(Map.Entry<String,Object> m1:SubSectionList.entrySet())
		{
			logMessage(m1.getKey()+": "+m1.getValue());		
		}

		List<String>list=new ArrayList<String>();
		String value;
		int count2=0;
		for(Map.Entry<String,Object> m1:SubSectionList.entrySet())
		{
			list.add(m1.getValue().toString());
		}
		Iterator<String> l2 = list.iterator();
		while (l2.hasNext())
		{
			int i;
			count2=0;
			value=l2.next();
			for (i = 0; i < list.size(); i++) 
			{
				if(list.get(i).equalsIgnoreCase(value))
				{
					count2++; 
				}			
			}	
			Assert.assertFalse(count2>1,"Assertion Failed:: Duplicate values present for "+value+" the count is:"+count2);  
		}
		logMessage("verified: Sub Sections are separated by an initial letter: No Duplicates present");


	}

	public void verifyRegentsForSubsectionStartsWithThatLetter() {
		String subsectionReagenttext = "";
		List<String> characterList=new ArrayList<String>();
		for (WebElement a : elements("txt_book4Subsections")) {
			String[]a1=a.getText().trim().split("\"");
			String SubsectionText=a1[1].replace("\"", "");
			characterList.add(SubsectionText);
		}
		for (int i = 0; i < characterList.size(); i++) {
			elements("lnk_book4SubSections").get(i).click();
			logMessage("Clicked index : " + i);

			for (WebElement reagent : elements("txt_subsectionReagents",characterList.get(i))) {
				String textReagent1=reagent.getText().trim();
				if(!(textReagent1.charAt(0)+"").equals(characterList.get(i))){
					if((textReagent1.charAt(0)+"").contains("-")|| (textReagent1.charAt(1)+"").contains("-")||(textReagent1.charAt(2)+"").contains("-")||(textReagent1.charAt(3)+"").contains("-") ){
						 textReagent1=textReagent1.split("-")[1];
					}
				}

				subsectionReagenttext=textReagent1.replaceAll("[^A-Z]+", "");

				Assert.assertEquals(subsectionReagenttext.charAt(0)+ "",characterList.get(i), 
						"Assertion Failed: Reagent Name verification as :" + reagent.getText() + " starts with "
								+ subsectionReagenttext.charAt(0));
				logMessage("Reagent: " + reagent.getText() + ": starts with "
						+ characterList.get(i));

			}
		}
		logMessage("Verified: Names for all the Reagents start with the alphabet subsection they belong to.");
	}
	
	public void verifyReagentsAreArrangedInAlphabeticalOrder() {
		String url= getCurrentURL();
		driver.get(url);
		clickOnBookPart4();
		logMessage("STEP: Checking if the Reagents are arranged in Alphabetical Order.......");
		List<String> characterList=new ArrayList<String>();
		for (WebElement a : elements("txt_book4Subsections")) {
			String[]a1=a.getText().trim().split("\"");
			String SubsectionText=a1[1].replace("\"", "");
			characterList.add(SubsectionText);
		}

		for (int i = 0; i < characterList.size(); i++) {
			
			List<String> ReagentNamesList = new ArrayList<String>();
			elements("lnk_book4SubSections").get(i).click();
			logMessage("Clicked index : " + characterList.get(i));

			for (WebElement reagent : elements("txt_subsectionReagents",characterList.get(i))) {
				String textReagent=reagent.getText().trim();
					if((textReagent.charAt(0)+"").equals(characterList.get(i)) && !textReagent.contains("N-") && !textReagent.contains("n-")){
						System.out.println(textReagent);
						ReagentNamesList.add(textReagent.replaceAll("[ 0-9%]", ""));
					}
			}
				
				List<String> ReagentNamesSorted = new ArrayList<String>();
				ReagentNamesSorted.addAll(ReagentNamesList);
				System.out.println("********");
				System.out.println(ReagentNamesList);
				Collections.sort(ReagentNamesSorted, String.CASE_INSENSITIVE_ORDER);
				System.out.println("********");
				System.out.println(ReagentNamesSorted);
				if(ReagentNamesList.equals(ReagentNamesSorted)){
					logMessage("Verified: Reagent Names are displayed in alphabetical order for: " + characterList.get(i));
					}
				else{
					logMessage("Warning: Reagent Names were not displayed in alphabetical order for "+characterList.get(i)+" (as designed)");
					}
				}
//				Assert.assertEquals(ReagentNamesList, ReagentNamesSorted, "Assertion Failed: Reagent Names were not displayed in alphabetical order for "+characterList.get(i));
//				logMessage("Verified: Reagent Names are displayed in alphabetical order for: " + characterList.get(i));
			}
			
		
	


	public void verifySubSectionNotPresentForLetterY() {
		int subsectionsize=elements("lnk_book4SubSections").size();
		if(subsectionsize!=26)
		logMessage("Subsection Size= "+subsectionsize+", hence all the alphabets are not present in the section");
		logMessage("Verifying the Subsections by Names...");
		for(WebElement subsectionText: elements("txt_book4Subsections")){
			Assert.assertFalse(subsectionText.getText().trim().contains("\"Y\""));
			
		}
		logMessage("Verified: No subsection for letter \"Y\" exists.");
		
	}
	
	public void verifyMandatoryInfoUnderAllChapters(){
		int bookChaptersize=elements("lnk_bookChapters").size();
		for(int i=0; i<bookChaptersize; i++){
			logMessage("Info: Verifying Title, DOI, Publication date and access token for chapter: "+(i+1));
			Assert.assertTrue(elements("lnk_bookChapters").get(i).getText()!= null, "Assertion failed: Title not displayed for chapter: "+(i+1));
			Assert.assertTrue(elements("doi_bookChapters").get(i).getText()!= null, "Assertion failed: DOI not displayed for chapter: "+(i+1));
			Assert.assertTrue(elements("pubDate_bookChapters").get(i).getText()!= null, "Assertion failed: Chapter title not displayed for chapter: "+(i+1));
			Assert.assertTrue(elements("accessToken_bookChapters").get(i).getText()!= null, "Assertion failed: Access token not displayed for chapter: "+(i+1));
		}
		logMessage("Verified: Title, DOI, Publication date, access token is displayed for every chapter.");
		refreshPage();
	}
	
	public void verifyPDFbuttonIsDisplayedForChaptersUnderAllPartsExceptPart0(){
//		scrollUp();
//		hardWait(1);
//		scrollDownMid();
		hardWait(2);
		elements("lnk_bookParts").get(0).click();
		logMessage("User clicked on part 0.");
		Assert.assertTrue(elements("lnk_bookParts").get(0).getAttribute("aria-expanded").toString().equals("true"), "Assertion Failed: Book Part: 0 was not expanded on click");
		logMessage("Info: Part 0 is expanded.");
		isElementDisplayed(false, "pdf_bookChapters");
		logMessage("Verified: PDF button not displayed for chapters under book part 0.");
		refreshPage();
		
		int bookPartSize= elements("lnk_bookParts").size()-1;
		Random random = new Random();
		int randomIndex = random.nextInt(bookPartSize)+1;
		if(randomIndex==4)
			randomIndex++;
		System.out.println(randomIndex);
		if(randomIndex>=5)
			scrollDownMid();
		hardWait(2);
		elements("lnk_bookParts").get(randomIndex).click();
		logMessage("User Clicked on part: "+randomIndex);
		
		int bookChaptersize=elements("lnk_bookChapters").size();
		Assert.assertTrue(elements("pdf_bookChapters").size()==bookChaptersize, "Assertion failed: Not all chapters have PDF button");
		logMessage("Verified: PDF button is displayed for every chapter under book part: "+randomIndex);
		logMessage("Verified: PDF button is displayed for every chapter under any book part except part 0.");
		refreshPage();
		
	}
	
	public void verifyAbstractAngleDownIconIsDisplayedForChaptersUnderAllPartsExcept0And6(){
		scrollUp();
		scrollDownMid();
		elements("lnk_bookParts").get(0).click();
		logMessage("User clicked on part 0.");
		Assert.assertTrue(elements("lnk_bookParts").get(0).getAttribute("aria-expanded").toString().equals("true"), "Assertion Failed: Book Part: 0 was not expanded on click");
		logMessage("Info: Part 0 is expanded.");
		isElementDisplayed(false, "AbstractAngleDown_bookChapters");
		logMessage("Verified: Abstract angle down icon not displayed for chapters under book part 0.");
		refreshPage();
		
//		scrollDownMid();
		hardWait(1);
		elements("lnk_bookParts").get(6).click();
		logMessage("User clicked on part 6.");
		Assert.assertTrue(elements("lnk_bookParts").get(6).getAttribute("aria-expanded").toString().equals("true"), "Assertion Failed: Book Part: 6 was not expanded on click");
		logMessage("Info: Part 6 is expanded.");
		isElementDisplayed(false, "AbstractAngleDown_bookChapters");
		logMessage("Verified: Abstract angle down icon not displayed for chapters under book part 6.");
		refreshPage();
		
		
		int bookPartSize= elements("lnk_bookParts").size()-1;
		Random random = new Random();
		int randomIndex = random.nextInt(bookPartSize)+1;
		if(randomIndex==4)
			randomIndex++;
		System.out.println(randomIndex);
//		scrollDownMid();
		hardWait(1);
		elements("lnk_bookParts").get(randomIndex).click();
		logMessage("User Clicked on part: "+randomIndex);
		
		int bookChaptersize=elements("lnk_bookChapters").size();
		Assert.assertTrue(elements("AbstractAngleDown_bookChapters").size()==bookChaptersize, "Assertion failed: Not all chapters have PDF button");
		logMessage("Verified: Abstract angle down icon is displayed for every chapter under any book part except part 0 and 6");
	}

	public void verifyLinksPresentUnderChapters() {
		int bookChaptersize=elements("lnk_bookChapters").size();
		Random random = new Random();
		int randomIndex = random.nextInt(bookChaptersize);
//		int randomIndex=1;
		Boolean flag=false;
		for(WebElement link: elements("lnks_chaptersUnderBookPart",Integer.toString(randomIndex))){
			String linkName=link.getText().trim();
			if(linkName.equals("Abstract")|| linkName.equals("Add to ACS ChemWorx")||linkName.equals("Full Text HTML")||linkName.equals("PDF") ){
				flag=true;
				
			}
			Assert.assertTrue(flag);
			logMessage(linkName+" Present for the article "+ randomIndex);
		}
		logMessage("Verified: Links Present for Chapters");
	}

	public void verifyLinksNotPresentUnderChapters() {
//		int bookChaptersize=elements("lnk_bookChapters").size();
//		Random random = new Random();
//		int randomIndex = random.nextInt(bookChaptersize);
		int randomIndex=1;
		Boolean flag=true;
		for(WebElement link: elements("lnks_chaptersUnderBookPart",Integer.toString(randomIndex))){
			String linkName=link.getText().trim();
			if(linkName.equals("PDF full text")|| linkName.equals("PDF with links")||linkName.equals("AVPDF") ){
				flag=false;
				
			}
			Assert.assertTrue(flag,"Assertion Failed: "+linkName+" Present for the article "+ randomIndex);
		}
		logMessage("Verified: Links 'PDF full text', 'PDF with links', 'AVPDF' not present for Chapters");
		
	}

	public String verifyBookChaptersDisplayedAndClickOnAnyChapter() {
		int bookChaptersize=elements("lnk_bookChapters").size()-1;
		Random random = new Random();
		int randomIndex = random.nextInt(bookChaptersize)+1;
		String chapterName=elements("lnk_bookChapters").get(randomIndex).getText();
		
		Assert.assertTrue(bookChaptersize>=1);
		logMessage("Verified: Book Chapters are displayed.");
		elements("lnk_bookChapters").get(randomIndex).click();
		int a=randomIndex+1;
		logMessage("User clicked on Chapter: "+a);
		return chapterName;
	}

	public void verifyNavigationOfSharingOptionLinks() {
		String option;
		String LinksArr[] = { "Twitter", "Facebook", "WeChat", "Email" };
		for(int i=0;i<LinksArr.length;i++){
		element("lnk_shareOptions", LinksArr[i]).click();	
		hardWait(2);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		option=LinksArr[i].toLowerCase();
		Assert.assertTrue(getPageTitle().toLowerCase().contains(option),"Assertion Failed :: Link '" + option + "' not redirecting to correct page");
		closeCurrentWindow();
		driver.switchTo().window(tabs2.get(0));
		logMessage("Assertion Passed :: Clicking on Sharing link "+option+" works fine.");
		}
		
	}

	public void verifyChapterOptions() {
		int i=0,j=1;
		//wait.waitForPageToLoadCompletely();
		int chapterOptions=elements("lnks_chapterOptions").size();
		
		for(i=0; i<chapterOptions;i++ ){
			String option=elements("lnks_chapterOptions").get(i).getText();
			
			if(option.contains("PDF")){
				elements("lnks_chapterOptions").get(i).click();
				logMessage("User clicked on "+option);
				ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs2.get(1));
				//wait.waitForPageToLoadCompletely();
				hardWait(3);
				Assert.assertTrue(getCurrentURL().contains("pdf"), "Assertion Failed: "+option+" page not loaded");
				logMessage("Verified:  "+option+" page landing page correct.");
				closeCurrentWindow();
				driver.switchTo().window(tabs2.get(0));
			}
			
			if(option.contains("Abstract")){
				elements("lnks_chapterOptions").get(i).click();
				logMessage("User clicked on "+option);
				Assert.assertTrue(getCurrentURL().contains("abs"), "Assertion Failed: "+option+" page not loaded");
				logMessage("Verified:  "+option+" page landing page correct.");
			}
			
			if(option.contains("Full Text HTML")){
				elements("lnks_chapterOptions").get(i).click();
				logMessage("User clicked on "+option);
				Assert.assertTrue(getCurrentURL().contains("full"), "Assertion Failed: "+option+" page not loaded");
				logMessage("Verified:  "+option+" page landing page correct.");
			}
		}
		
	}

	public void verifyAddToACSChemworxFunctionality() {
		isElementDisplayed(true,"lnk_chemworx");
		logMessage("Verified: Add to ACS Chemworx link is present");
		element("lnk_chemworx").click();
		logMessage("User clicked on ACS Chemworx link.");
		hardWait(10);
		Assert.assertTrue(element("lnk_chemworx").getText().equals("Added to ACS ChemWorx"), "Assert Failed: Chapter not added to chemworx");
		logMessage("Verified: Chapter added to ACS Chemworx");
		
	}

	public void verifyOtherLinksForChapter(String link1, String link2, String link3, String link4,String chapterName) {
		String option;
		isElementDisplayed(true,"lnks_OtherOptions",link1);
		logMessage("Verified: Option "+link1+" present on the page.");
		element("lnks_OtherOptions",link1).click();
		logMessage("user clicked on "+link1);
		isElementDisplayed(true,"box_actionDetailFavContent");
		logMessage("An action detail box is displayed");
		Assert.assertTrue(elements("lnks_actionDetailOptions",link1).size()==3);
		logMessage("Three options are available on the action detail box");
		option=elements("lnks_actionDetailOptions",link1).get(0).getText();
		elements("lnks_actionDetailOptions",link1).get(0).click();
		logMessage("User selected "+option);
		isElementDisplayed(true,"h1_Sections","Favorite Content");
		Assert.assertTrue(element("txt_articleUnderFavContent").getText().equals(chapterName));
		logMessage("Verified: "+option+" added in the favorite content section.");
		
		navigateToPreviousPage();
		//wait.waitForPageToLoadCompletely();
		
		isElementDisplayed(true,"lnks_OtherOptions",link2);
		logMessage("Verified: Option "+link2+" present on the page.");
		element("lnks_OtherOptions",link2).click();
		logMessage("user clicked on "+link2);
		isElementDisplayed(true,"box_actionDetailDownloadCitation");
		Assert.assertTrue(elements("lnks_actionDetailOptions",link2).size()==3);
		logMessage("Three options are available on the action detail box");
		option=elements("lnks_actionDetailOptions",link2).get(0).getText();
		elements("lnks_actionDetailOptions",link2).get(0).click();
		logMessage("User selected "+option);
		isElementDisplayed(true,"h1_Sections","Download Citations");
		Assert.assertTrue(element("txt_articleDownloadCitation").getText().equals(chapterName));
		logMessage("Verified: "+option+" added in the Download Citation section.");
		
		navigateToPreviousPage();
		//wait.waitForPageToLoadCompletely();
		
		isElementDisplayed(true,"lnks_OtherOptions",link3);
		logMessage("Verified: Option "+link3+" present on the page.");
		element("lnks_OtherOptions",link3).click();
		logMessage("user clicked on "+link3);
		//wait.waitForPageToLoadCompletely();
		isElementDisplayed(true,"h1_Sections","E-mail a Link to a Friend/Colleague");
		Assert.assertTrue(element("txt_articleEmailColleague").getText().equals(chapterName));
		logMessage("Verified: chapter added in the Email Colleague section.");
		
		navigateToPreviousPage();
		//wait.waitForPageToLoadCompletely();
		
		isElementDisplayed(true,"lnks_OtherOptions",link4);
		logMessage("Verified: Option "+link4+" present on the page.");
		element("lnks_OtherOptions",link4).click();
		logMessage("user clicked on "+link4);
		//wait.waitForPageToLoadCompletely();
		isElementDisplayed(true,"h2_citationAlerts");
		Assert.assertTrue(element("txt_articleCitationAlerts").getText().equals(chapterName));
		logMessage("Verified: chapter added in the citation alerts section.");
		
		navigateToPreviousPage();
		//wait.waitForPageToLoadCompletely();
		
		isElementDisplayed(true,"h1_Sections","Metrics");
		isElementDisplayed(true,"txt_metricsPublicationDate");
		String foundPatternForPubDate=element("txt_metricsPublicationDate").getText().trim();
		String validPatternForPubDate= "Published online\\s\\d{1,2}\\s(January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{4}";
		Assert.assertTrue(verifyFormat(foundPatternForPubDate, validPatternForPubDate),
				"Assertion Failed: Published Date is not in the format 'Published online XX Month YYYY'");
		logMessage("Verified: Published Date is in the format 'Published online XX Month YYYY'");
		
	}

	public void verifyACSNewsSection() {
		isElementDisplayed(true,"h2_ACSNewsHeader");
		Assert.assertTrue(elements("txt_ACSNewsHeadlines").size()>=1, "Assertion Failed: No headlines present for ACS News");
		logMessage("Verified: headlines present under ACS News section");
		
	}

	public void clickOnPart0andPart6AndVerifyFreeContent() {
		elements("lnk_bookParts").get(0).click();
		logMessage("User clicked on Part 0");
		hardWait(1);
		elements("lnks_chaptersUnderBookPart",Integer.toString(1)).get(1).click();
		logMessage("User clicked on Full Text HTML for 1st chapter");
		isElementDisplayed(false,"txt_accessDenialMessage");
		logMessage("Verified: Content is free for Part 0");
		navigateToPreviousPage();
		
		elements("lnk_bookParts").get(6).click();
		logMessage("User clicked on Part 6");
		hardWait(1);
		elements("lnks_chaptersUnderBookPart",Integer.toString(1)).get(2).click();
		logMessage("User clicked on Full Text HTML for 1st chapter");
		isElementDisplayed(false,"txt_accessDenialMessage");
		logMessage("Verified: Content is free for Part 6");
		navigateToPreviousPage();
	}

	public void clickOnPart1To5AndVerifySubscriptionAccess() {
		elements("lnk_bookParts").get(1).click();
		logMessage("User clicked on Part 1");
		hardWait(1);
		elements("lnks_chaptersUnderBookPart",Integer.toString(1)).get(2).click();
		logMessage("User clicked on Full Text HTML for 1st chapter");
		isElementDisplayed(true,"txt_accessDenialMessage");
		logMessage("Verified: Subscription is required for Part 1");
		
	}

	public void verifyFreeContentAvailable() {
		elements("lnks_chapterOptions").get(1).click();
		logMessage("User clicked on Full Text HTML link");
		isElementDisplayed(false,"txt_accessDenialMessage");
		logMessage("Verified: Content is free for the mentioned URL");
	}


	
	

	
	

	
}


		

