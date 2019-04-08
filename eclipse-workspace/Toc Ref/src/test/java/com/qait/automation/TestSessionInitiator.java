package com.qait.automation;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;
import static com.qait.automation.getpageobjects.ObjectFileReader.setTier;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.qait.acs.keywords.ArticleLandingPageActions;
import com.qait.acs.keywords.CollectionPageActions;
import com.qait.acs.keywords.CurrentIssuePageActions;
import com.qait.acs.keywords.EMailAlertsHomePageActions;
import com.qait.acs.keywords.FavouriteContentHomePageActions;
import com.qait.acs.keywords.JournalHomePageActions;
import com.qait.acs.keywords.NewRegistrationPageActions;
import com.qait.acs.keywords.IssueTOCPageActions;
import com.qait.acs.keywords.ReagentChemicalsPageActions;
import com.qait.acs.keywords.ReferenceQuickViewActions;
import com.qait.acs.keywords.SavedSearchesHomePageActions;
import com.qait.acs.keywords.SearchResultsPageActions;
import com.qait.acs.keywords.UserProfileHomePageActions;
import com.qait.acs.keywords.chapterPageActions;
import com.qait.acs.keywords.eBooksPageActions;
import com.qait.acs.keywords.homePageActions;
import com.qait.acs.keywords.loginPageActions;
import com.qait.acs.keywords.ListOfIssuesPageActions;
import com.qait.acs.keywords.MendeleyModalActions;
import com.qait.acs.keywords.NewRegistrationPageActions;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.TakeScreenshot;

public class TestSessionInitiator {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	/**
	 * Initiating the page objects _initPage
	 */
	public TakeScreenshot takescreenshot;
	public eBooksPageActions eBooksPage;
	public homePageActions homePage;
	public loginPageActions loginPage;
	public SearchResultsPageActions searchResultsPage;
	public ArticleLandingPageActions articlePage;
	public ReagentChemicalsPageActions reagentChemicalsPage;
	public chapterPageActions chapterPage;
	public ListOfIssuesPageActions loiPage;
	public JournalHomePageActions journalPage;
	public IssueTOCPageActions issueTOCPage;
	public NewRegistrationPageActions registrationPage;
	public UserProfileHomePageActions userProfileHomePage;
	public EMailAlertsHomePageActions eMailAlertsHomePage;
	public FavouriteContentHomePageActions favouriteContentHomePage;
	public CurrentIssuePageActions currentIssuePage;
	public CollectionPageActions collectionPage;
	public SavedSearchesHomePageActions savedSearchesHomePage;
	public ReferenceQuickViewActions refernceQuickView;
	public MendeleyModalActions mendeleyModal;

	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {
		homePage = new homePageActions(driver);
		eBooksPage = new eBooksPageActions(driver);
		loginPage = new loginPageActions(driver);
		searchResultsPage = new SearchResultsPageActions(driver);
		articlePage = new ArticleLandingPageActions(driver);
		reagentChemicalsPage = new ReagentChemicalsPageActions(driver);
		chapterPage = new chapterPageActions(driver);
		loiPage = new ListOfIssuesPageActions(driver);
		journalPage = new JournalHomePageActions(driver);
		issueTOCPage = new IssueTOCPageActions(driver);
		registrationPage = new NewRegistrationPageActions(driver);
		userProfileHomePage = new UserProfileHomePageActions(driver);
		eMailAlertsHomePage = new EMailAlertsHomePageActions(driver);
		favouriteContentHomePage = new FavouriteContentHomePageActions(driver);
		currentIssuePage = new CurrentIssuePageActions(driver);
		collectionPage = new CollectionPageActions(driver);
		savedSearchesHomePage = new SavedSearchesHomePageActions(driver);
		refernceQuickView = new ReferenceQuickViewActions(driver);
		mendeleyModal = new MendeleyModalActions(driver);
	}

	/**
	 * Page object Initiation done
	 * 
	 */
	public TestSessionInitiator(String testname) {
		wdfactory = new WebDriverFactory();
		testInitiator(testname);
	}

	private void testInitiator(String testname) {
		setTier();
		setYamlFilePath();
		_configureBrowser();
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);

	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver", "seleniumserverhost", "timeout", "driverpathIE",
				"driverpathChrome" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("baseUrl"));
	}

	public void launchApplication(String baseurl) {
		Reporter.log("The application url is :- " + baseurl, true);
		Reporter.log("The test browser is :- " + _getSessionConfig().get("browser") + "\n", true);
		
		driver.get(baseurl);
	}
	public void clearCookies() {
		
		driver.manage().deleteAllCookies();
		
	}
	
	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST STEP:- " + testStepName.toUpperCase() + " *****", true);
		Reporter.log(" ", true);
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void closeBrowserSession() {
		Reporter.log("Browser selected from 'Config.properties' file: " + _getSessionConfig().get("browser"));

		/*
		 * if(_getSessionConfig().get("browser").equalsIgnoreCase("firefox")){
		 * Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
		 * driver.close(); } driver.close();
		 */
		driver.quit();
	}

	public void pageRefresh() {
		driver.navigate().refresh();
	}

	public String getpageTitle(){
		return driver.getTitle();
	}
}
