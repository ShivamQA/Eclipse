package com.qait.acs.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.qait.automation.getpageobjects.GetPage;
import static com.qait.automation.utils.YamlReader.getYamlValue;

public class MendeleyModalActions extends GetPage {

	WebDriver driver;
	private static String pagename = "mendeleyModal";

	public MendeleyModalActions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyPresenceOfMendeleyIcon() {
		isElementDisplayed(true, "btn_Mendeley","Add to");
		isElementDisplayed(true, "txt_AddTo","addTo");
		logMessage("Verified: 'Add to Mendeley' icon with text 'Add to' displayed above it.");
	}

	public void clickOnMendeleyIcon() {
		element("btn_Mendeley","Add to").click();
		logMessage("Info: User clicked on Mendeley icon.");
		hardWait(2);
	}

	public void verifyMendeleyPairingScreenModalIsDisplayed() {
		isElementDisplayed(true, "modal_Mendeley");
		logMessage("Verified: A Mendeley pairing screen is displayed upon clicking mendeley button.");
	}


	public void verifyPairingScreen(String Screen) {
		String state = Screen.split(" ")[1];
		String text1= "Pair your accounts.";
		String text2= "Export articles to Mendeley";
		String text3= "Get article recommendations from ACS based on references in your Mendeley library.";
		String textScreen3= "You’ve supercharged your research process with ACS and Mendeley!";
		String textBottom= "Please note: If you switch to a different device, you may be asked to login again with only your ACS ID.";
		
		if(!state.equals("3")){
			Assert.assertTrue(elements("texts_PairingScreen",state).get(0).getText().equals(text1), "Assertion failed: Text on Pairing screen is incorrect");
			Assert.assertTrue(elements("texts_PairingScreen",state).get(1).getText().equals(text2), "Assertion failed: Text on Pairing screen is incorrect");
			Assert.assertTrue(elements("texts_PairingScreen",state).get(2).getText().equals(text3), "Assertion failed: Text on Pairing screen is incorrect");
		}
		else
		{
			Assert.assertTrue(element("texts_PairingScreen",state).getText().equals(textScreen3), "Assertion failed: Text on Pairing screen is incorrect");
			logMessage("Info: Text displayed on screen: "+textScreen3);
		}
		Assert.assertTrue(element("text_ScreenBottom",state).getText().equals(textBottom), "Assertion failed: Text at bottom of Pairing screen is incorrect");
		logMessage("Verified: All the texts at top and bottom of the pairing screen.");
		
		isElementDisplayed(true, "btn_Login","STEP 1:","acsLogin");
		isElementDisplayed(true, "text_CreateACS");
		isElementDisplayed(true, "btn_Login","STEP 2:","mendeley_login");
		isElementDisplayed(true, "link_CreateMendeley");
		logMessage("Verified: Login buttons and specific links for ACS and Mendeley are displayed on the pairing screen.");
		
		isElementDisplayed(true, "StepsBridgeArrow");
		
		verifyScenarioSpecificDetails(Screen);
		
		isElementDisplayed(true, "icon_Modalclose");
		
		
	}

	private void verifyScenarioSpecificDetails(String Screen) {
		String colorACSLogin="", colorMendeleyLogin="rgba(172, 28, 34, 1)";
		String MsgACSLogin="", MsgMendeleyLogin="Login with Mendeley";
		String colorNameACS="", colorNameMendeley="Red";

		if(Screen.contains("1")){
			MsgACSLogin= "Login with ACS ID";
			colorACSLogin = "rgba(0, 85, 166, 1)";
			colorNameACS= "Blue";
		}
		
		if(Screen.contains("2")){
			MsgACSLogin= "Logged in Success";
			colorACSLogin = "rgba(84, 187, 101, 1)";
			colorNameACS= "Green";
		}
		
		if(Screen.contains("3")){
			MsgACSLogin= MsgMendeleyLogin = "Logged in Success";
			colorACSLogin = colorMendeleyLogin=  "rgba(84, 187, 101, 1)";
			colorNameACS = colorNameMendeley= "Green";
			isElementDisplayed(true, "btn_Continue");
		}		
		
		System.out.println(executeJavascript("return arguments[0].innerText", element("btn_Login","STEP 1:","acsLogin")));
		Assert.assertTrue(executeJavascript("return arguments[0].innerText", element("btn_Login","STEP 1:","acsLogin")).equals(MsgACSLogin), "Assertion failed: Incorrect Msg on ACS Login button");
		logMessage("Step 1: "+MsgACSLogin);
		Assert.assertTrue(element("btn_Login","STEP 1:","acsLogin").getCssValue("background-color").equals(colorACSLogin), "Assertion failed: ACS Login button is not displayed in correct color.");
		logMessage("Verified: ACS Login button is displayed in "+colorNameACS+" color.");

		System.out.println(executeJavascript("return arguments[0].innerText", element("btn_Login","STEP 2:","mendeley_login")));
		Assert.assertTrue(executeJavascript("return arguments[0].innerText", element("btn_Login","STEP 2:","mendeley_login")).equals(MsgMendeleyLogin), "Assertion failed: Incorrect Msg Mendeley button");
		logMessage("Step 2: "+MsgMendeleyLogin);
		Assert.assertTrue(element("btn_Login","STEP 2:","mendeley_login").getCssValue("background-color").equals(colorMendeleyLogin), "Assertion failed: Mendeley Login button is not displayed in correct color.");
		logMessage("Verified: Mendeley Login button is displayed in "+colorNameMendeley+" color.");

	}

	public void clickOnLoginButton(String ButtonName) {
		if(ButtonName.equals("ACS"))
			element("btn_Login","STEP 1:","acsLogin").click();
		else
			element("btn_Login","STEP 2:","mendeley_login").click();
		logMessage("Info: Clicked on Login with "+ButtonName+" button.");
		
		hardWait(2);
	}

	public void verifyErrorMessageDisplayed() {
		String ErrorMsg= "You have to login with your ACS ID befor you can login with your Mendeley account.";
		
		isElementDisplayed(true, "btnACSLogin_ErrorMsg");
		logMessage("Verified: Login with ACS ID button displayed on alert popup.");
		Assert.assertTrue(element("txt_ErrorMsg").getText().equals(ErrorMsg), "Assertion failed: Text on Pairing screen is incorrect");
		logMessage("Verified: Text displayed: "+ErrorMsg);
		
		isElementDisplayed(true, "IconClose_ErrorMsg");
		logMessage("Verified: Error message displayed upon clicking on 'Login with Mendeley' button");
		
	}

	public void clickOnLoginWithACSIDButtonOnErrorPopup() {
		element("btnACSLogin_ErrorMsg").click();
		logMessage("Info: Clicked on 'Login with ACS ID' button on error popup.");
		hardWait(2);
	}
	
	public void closePairingScreen(){
		element("icon_Modalclose").click();
		hardWait(2);
		logMessage("Info: Closed pairing screen modal.");
	}

	public void verifyNavigationToMendeleyAuthorizationPage() {
		String ModalText="SSO Staging is requesting the ability to access and update data from your Mendeley account.";
		isElementDisplayed(true, "img_AuthorizationPage");
		Assert.assertTrue(element("text_MendeleyloginModal").getText().equals(ModalText));
		
		isElementDisplayed(true, "text_Loginfield", "username");
		isElementDisplayed(true, "text_Loginfield", "password");
		isElementDisplayed(true, "input_Loginfield", "username");
		isElementDisplayed(true, "input_Loginfield", "password");
		isElementDisplayed(true, "btn_Authorize");
		isElementDisplayed(true, "btn_ForgotYourPassword");
		logMessage("Verified: Elements on 'Mendeley Authorize' page.");
	}

	public void clickOnForgotPasswordLinkAndVerifyNavigation() {
		element("btn_ForgotYourPassword").click();
		logMessage("Info: Clicked on 'Forgot Password' link.");
		changeWindow(1);
		isElementDisplayed(true, "h1_PasswordReset");
		isElementDisplayed(true, "input_ForgotPassEmail");
		logMessage("Verified: Navigation to 'Forgot Password' Page");
		closeCurrentWindow();
		changeWindow(0);
	}

	public void enterMendeleyCredentials(String email, String password) {
		element("input_Loginfield", "username").clear();
		element("input_Loginfield", "username").sendKeys(email);
		element("input_Loginfield", "password").clear();
		element("input_Loginfield", "password").sendKeys(password);
		logMessage("Info: User entered valid Mendeley credentials.");
		element("btn_Authorize").click();
		logMessage("Info: User clicked on 'Authorize' button");
		hardWait(2);
	}

	public void clickOnCreateMendeleyAccountLinkOnPairingScreen() {
		element("link_CreateMendeley").click();
		logMessage("Info: Clicked on 'Create a Mendeley account' link.");
		hardWait(2);
	}

	public void verifyNavigationToElsevierPage() {
		String text1="Welcome";
		String text2="Enter your email to continue with Mendeley";
		
		Assert.assertTrue(getCurrentURL().contains("elsevier"), "Assertion failed: Not navigated to Elsevier Page.");
		isElementDisplayed(true, "Img_Elsevier");
		Assert.assertTrue(element("text_Elsevier","1").getText().equals(text1), "Assertion failed: Correct text not displayed on Elsevier page.");
		Assert.assertTrue(element("text_Elsevier","2").getText().equals(text2), "Assertion failed: Correct text not displayed on Elsevier page.");
		logMessage("Verified: Texts on Elsevier Page modal.");
		
		isElementDisplayed(true, "input_Email");
		isElementDisplayed(true, "btn_Elsevier","emailContinue");
	}
	
	public void verifyElsevierModalDisplayed(String EmailType){
		String text1="",text2="";
		
		// Case 1
		if(EmailType.contains("Not")){
			logMessage("CASE 1 : User entered a new email ID (Not an existing Mendeley account)");
			text1="Register";
			text2= "Create password to register";
			isElementDisplayed(true, "InputField_Elsevier","givenName");
			isElementDisplayed(true, "InputField_Elsevier","familyName");
			isElementDisplayed(true, "btn_Elsevier","register");
			isElementDisplayed(true, "link_AlreadyAccount");
		}
		
		// Case 2
		else{
			logMessage("CASE 2 : User entered an existing Mendeley account email ID.");
			text1="Sign in";
			text2= "Enter your password to sign in to Mendeley";
			isStringMatching(element("text_Elsevier","1").getText(), text1);
			isStringMatching(element("text_Elsevier","2").getText(), text2);
			isElementDisplayed(true, "btn_SignIn");
			isElementDisplayed(true, "link_differentAccount");
			isElementDisplayed(true, "link_ForgotPassword");
		}
		
		isElementDisplayed(true, "email_SignInElsevier");
		isElementDisplayed(true, "InputField_Elsevier","password");

		if(EmailType.contains("Not")){
			logMessage("Verified: Navigation to 'Register' page.");
			}
		else
			logMessage("Verified: Navigation to 'Sign In' page.");
				
	}

	public void enterMendeleyEmailInField(String email) {
		logMessage("---------------------------------------------");
		element("input_Email").clear();
		hardWait(1);
		element("input_Email").sendKeys(email);
		logMessage("Info: User entered email in the 'Email' field.");
		
		element("btn_Elsevier","emailContinue").click();
		logMessage("Info: User clicked on 'Continue' button.");
		hardWait(2);
	}

	public void clickOnContinueButtonOnPairingScreen() {
		element("btn_Continue").click();
		logMessage("Info: Clicked on 'Continue' button on pairing screen.");
		hardWait(2);

	}

	public void verifyMendeleyIconHasChangedState() {
		wait.waitForElementToBeVisible(element("btn_Mendeley","View in"));
		hardWait(3);
		isElementDisplayed(true, "btn_Mendeley","View in");
		isElementDisplayed(true, "txt_AddTo","viewIn");
		logMessage("Verified: 'View in' Mendeley' icon with text 'View In' displayed above it.");
		logMessage("Verified: The mendeley icon changes state from 'Add to' -->  'View In'");
	}


	public void clickOnViewInMendeleyIcon() {
		element("btn_Mendeley","View in").click();
		hardWait(2);
		logMessage("Info: Clicked on 'View In Mendeley' icon.");
	}

	private void enterPasswordOnSignInPage() {
		String password= getYamlValue("users.mendeley.password");
		element("InputField_Elsevier","password").clear();
		hardWait(1);
		element("InputField_Elsevier","password").sendKeys(password);
		logMessage("Info: Entered password.");
		hardWait(2);
	}
	
	
	public void verifyNavigationToMendeleyLibrary() {
		changeWindow(1);
		
		if(checkIfElementIsThere("Img_Elsevier")){
			logMessage("Info: Mendeley Library propmpts for authorization on Elsevier page.");
			enterMendeleyEmailInField(getYamlValue("users.mendeley.email"));
			verifyElsevierModalDisplayed("Mendeley");
			enterPasswordOnSignInPage();
			element("btn_SignIn").click();
			logMessage("Info: Clicked on 'Sign In' button.");
			if(checkIfElementIsThere("icon_CloseMendeley1")){
				element("icon_CloseMendeley1").click();
				hardWait(1);
				element("icon_CloseMendeley2").click();
				hardWait(1);
			}
		}
		verifyMendeleyLibraryIsDisplayed();
	}

	
	
	public void verifyMendeleyLibraryIsDisplayed() {
		Assert.assertTrue(getCurrentURL().contains("mendeley.com/library"), "Assertion failed: Not navigated to Mendeley library.");
		Assert.assertTrue(elements("list_DocumentsLibrary").size()>=1);
		logMessage("Verified: Navigated to Mendeley library.");
	}

	public void verifyArticleAddedInTheLibrary(String ArticleTitle) {
		isStringMatching(element("Title_ArticleLibrary").getText(), ArticleTitle);
		logMessage("Verified: Article added in the Mendeley library.");
	}

	public void verifyDetailsDisplayedForArticleInMendeleyLibrary(String ArticleType) {
		Boolean flag=true;
		if(ArticleType.contains("Non Free"))
			flag=false;
		
		if(checkIfElementIsThere("icon_CloseMendeley1")){
			element("icon_CloseMendeley1").click();
			hardWait(1);
			element("icon_CloseMendeley2").click();
			hardWait(1);
		}
		
		isElementDisplayed(true, "checkBox_ArticleLibrary");
		isElementDisplayed(true, "icon_FavArticleLibrary");
		String authors = element("Metadata_ArticleLibrary").getText().split(" in ")[0];
		Assert.assertTrue(authors!=null, "Assertion failed: Authors names not displayed for article in Mendeley library.");
		logMessage("Verfied: Author names displayed for article in Mendeley library.");
		String JournalName = element("Metadata_ArticleLibrary").getText().split(" in ")[1];
		Assert.assertTrue(JournalName!= null, "Assertion failed: Journal names not displayed for article in Mendeley library.");
		logMessage("Verfied: Journal name is displayed for article in Mendeley library.");

		isElementDisplayed(flag, "icon_ReadArticleLibrary");
		isElementDisplayed(flag, "icon_PDFArticleLibrary");
		isElementDisplayed(true, "timeAdded_ArticleLibrary");
		
		if(ArticleType.contains("Non Free")){
			logMessage("Info: PDF is not displayed in the Mendeley table.");
		}
		
	}

	public void clickOnArticleTitleAndVerifyDetailsTabDisplayedOnRight() {
		element("Title_ArticleLibrary").click();
		hardWait(3);
		wait.waitForElementToBeVisible(element("Tab_DetailsLibrary"));
		isElementDisplayed(true, "Tab_DetailsLibrary");
	}

	public void verifyElementsInDetailsTab(String ArticleTitle, String ArticleType) {
		try
		{Boolean flag=true;
		if(ArticleType.contains("Non Free"))
			flag=false;

		isElementDisplayed(true, "Type_LibraryDetailsTab");
		isStringMatching(element("Title_LibraryDetailsTab").getText(), ArticleTitle);
		Assert.assertTrue(elements("Authors_LibraryDetailsTab").size()>=1, "Assertion failed: Authors are not displayed in details tab.");
		logMessage("Verified: Authors are displayed in details tab.");
		isElementDisplayed(true, "Journal_LibraryDetailsTab");
		isElementDisplayed(true, "Details_LibraryDetailsTab");
		isElementDisplayed(true, "Abstract_LibraryDetailsTab");
		isElementDisplayed(true, "DOI_LibraryDetailsTab");

		isElementDisplayed(flag, "pdf_LibraryDetailsTab", "icon");
		isElementDisplayed(flag, "pdf_LibraryDetailsTab", "name");
		logMessage("Verified: Elements in Details tab on right.");

		if(ArticleType.contains("Non Free")){
			logMessage("Info: PDF is not uploaded in the Mendeley library for non-free articles.");
		}
		}
		finally{
			closeCurrentWindow();
			changeWindow(0);}
	}

	public void verifyMendeleyPairingRemoved() {
		clickOnMendeleyIcon();
		verifyMendeleyPairingScreenModalIsDisplayed();
		verifyPairingScreen("Screen 2");
		logMessage("Verified: The ACS-Mendeley pairing has been removed succesfully!!");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
