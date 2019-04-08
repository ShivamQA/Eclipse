package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import Pages.CookieHandlingPage;
import Pages.DragDropPage;

import Pages.FramePage;
import Pages.GridGatePage;
import Pages.WindowPopupPage;

public class TatocPages {
	
	WebDriver driver;
	GridGatePage grid;
	FramePage frame;
	DragDropPage drag;
	WindowPopupPage window;
	CookieHandlingPage cookie;

	public TatocPages() {
		
		System.setProperty("webdriver.chrome.driver", "resource/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	public void init() {
		
		grid = new GridGatePage(driver);
		frame = new FramePage(driver);
		drag = new DragDropPage(driver);
		window = new WindowPopupPage(driver);
		cookie = new CookieHandlingPage(driver);
		
	}
	
	@BeforeClass
	public void setup() {
		
		driver.get("http://10.0.1.86/tatoc/basic/grid/gate");
		driver.manage().window().maximize();
		init();
	}
	
	@AfterClass
	public void closeSetup() {
		
		driver.quit();
		
	}
	

}
