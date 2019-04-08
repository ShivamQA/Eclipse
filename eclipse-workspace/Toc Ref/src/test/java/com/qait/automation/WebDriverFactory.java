/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

	private static String browser;
	private static final DesiredCapabilities capabilities = new DesiredCapabilities();

	public WebDriver getDriver(Map<String, String> seleniumconfig) {
		browser = seleniumconfig.get("browser");

		switch (seleniumconfig.get("seleniumserver").toLowerCase()) {
		case "local":
			switch (browser.toLowerCase()) {
			case "chrome":
				return getChromeDriver(seleniumconfig.get("driverpathChrome"));
			case "chromeproxy":
				return ProxyUsingChromeDrivers(seleniumconfig.get("driverpathChrome"));
			case "safari":
				return getSafariDriver();
			case "ie":
				return getInternetExplorerDriver(seleniumconfig.get("driverpathIE"));
			case "internetexplorer":
				return getInternetExplorerDriver(seleniumconfig.get("driverpathIE"));
			case "internet explorer":
				return getInternetExplorerDriver(seleniumconfig.get("driverpathIE"));
			default:
				return getChromeDriver(seleniumconfig.get("driverpathChrome"));
			}
		case "remote":
			return setRemoteDriver(seleniumconfig);
		}
		return getChromeDriver(seleniumconfig.get("driverpathChrome"));
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = null;
	    browser = selConfig.get("browser");
	    if (browser.equalsIgnoreCase("firefox")) {
	      cap = DesiredCapabilities.firefox();
	    } else if (browser.equalsIgnoreCase("chrome")) {
	      cap = DesiredCapabilities.chrome();
	    } else if (browser.equalsIgnoreCase("chromeProxy")) {
	      cap = DesiredCapabilities.chrome();
	      Proxy proxy = new Proxy();
	      proxy.setHttpProxy("stag-lnx-133.acs.org:38888");
	      cap.setCapability("proxy", proxy);
	    } else if (browser.equalsIgnoreCase("Safari")) {
	      cap = DesiredCapabilities.safari();
	    } else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer")) || (browser.equalsIgnoreCase("internet explorer"))) {
	      cap = DesiredCapabilities.internetExplorer();
	    }
	    String seleniuhubaddress = selConfig.get("seleniumserverhost");
	    URL selserverhost = null;
	    try {
	      selserverhost = new URL(seleniuhubaddress);
	    } catch (MalformedURLException e) {
	      e.printStackTrace();
	    }
	    cap.setJavascriptEnabled(true);
	    return new RemoteWebDriver(selserverhost, cap);
		
	}

	private static WebDriver getChromeDriver(String driverpath) {
		System.setProperty("webdriver.chrome.driver", driverpath);
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(cap);

	}

	private WebDriver getChromeDriverWithProxy(String driverpath) {
		java.net.InetAddress localMachine;
		String curdir, downloadFilePath, currentPath = null, machineName = null, userName = null;
		try {
			localMachine = java.net.InetAddress.getLocalHost();
			machineName = localMachine.getHostName();
			userName = System.getProperty("user.name");
			currentPath = System.getProperty("user.dir");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		curdir = "\\\\" + machineName + "\\" + userName + currentPath.split(userName)[1];
		downloadFilePath = curdir + "\\src\\test\\resources\\testdata";
		// System.out.println("download path is:" + downloadFilePath);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		System.setProperty("webdriver.chrome.driver", driverpath);

		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		options.addArguments("--always-authorize-plugins=true");
		Proxy proxy = new Proxy();
		System.out.println("Setting proxy");
		proxy.setHttpProxy("stag-lnx-133:38888");
		cap.setCapability("proxy", proxy);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);
		options.setExperimentalOption("prefs", chromePrefs);

		return new ChromeDriver(cap);
	}
	
	public WebDriver ProxyUsingChromeDriver(String driverpath)
    {
        //Set the location of the ChromeDriver
        System.setProperty("webdriver.chrome.driver", driverpath);
//        //Create a new desired capability
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        // Create a new proxy object and set the proxy
//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("stag-lnx-133:38888");
//        //Add the proxy to our capabilities 
//        capabilities.setCapability("proxy", proxy);
//        //Start a new ChromeDriver using the capabilities object we created and added the proxy to
//        ChromeDriver Driver = new ChromeDriver(capabilities);
      System.out.println("In proxy");
      
      ChromeOptions options = new ChromeOptions();
      Proxy proxy = new Proxy();
    proxy.setHttpProxy("stag-lnx-133:38888");
    //Add the proxy to our capabilities 
    options.setCapability("proxy", proxy);
      ChromeDriver Driver = new ChromeDriver(options);
      
      
        return Driver;
		
//		java.net.InetAddress localMachine;
//	    String curdir, downloadFilePath, currentPath = null, machineName = null, userName = null;
//	    try {
//	      localMachine = java.net.InetAddress.getLocalHost();
//	      machineName = localMachine.getHostName();
//	      userName = System.getProperty("user.name");
//	      currentPath = System.getProperty("user.dir");
//	    } catch (UnknownHostException e) {
//	      e.printStackTrace();
//	    }
//	    curdir = "\\\\" + machineName + "\\" + userName + currentPath.split(userName)[1];
//	    downloadFilePath = curdir + "\\src\\test\\resources\\testdata";
//	    // System.out.println("download path is:" + downloadFilePath);
//	    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//
//	    System.setProperty("webdriver.chrome.driver", driverpath);
//
//	    ChromeOptions options = new ChromeOptions();
//	    DesiredCapabilities cap = DesiredCapabilities.chrome();
//	    options.addArguments("--always-authorize-plugins=true");
//	    Proxy proxy = new Proxy();
//	    proxy.setHttpProxy("stag-lnx-133.acs.org:38888");
//	    cap.setCapability("proxy", proxy);
//	    cap.setCapability(ChromeOptions.CAPABILITY, options);
//
//	    chromePrefs.put("profile.default_content_settings.popups", 0);
//	    chromePrefs.put("download.default_directory", downloadFilePath);
//	    options.setExperimentalOption("prefs", chromePrefs);
//	    System.out.println("Inside Proxy block.");
//	    return new ChromeDriver(cap);
    }
	
	
	public WebDriver ProxyUsingChromeDrivers(String driverpath)
    {
		System.setProperty("webdriver.chrome.driver", driverpath);
	ChromeOptions options = new ChromeOptions();
	  // options.setExperimentalOption("excludeSwitches",
	  // Collections.singletonList("enable-automation"));
	  options.addArguments("--disable-extensions");
	  options.addArguments("chrome.switches", "--disable-extensions");
	  options.addArguments("--always-authorize-plugins");
	  options.addArguments("test-type");
	  options.addArguments("--dns-prefetch-disable");
	  options.addArguments("--explicitly-allowed-ports=8085");
	  DesiredCapabilities cap = DesiredCapabilities.chrome();
	  cap.setCapability(ChromeOptions.CAPABILITY, options);
	  String PROXY = "stag-lnx-133.acs.org:38888";
	  Proxy proxy = new Proxy();
	  proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
	  cap.setCapability(CapabilityType.PROXY, proxy);
	  ChromeDriver driver = new ChromeDriver(cap);
	  return driver;
	
    }
	
	
	private static WebDriver getInternetExplorerDriver(String driverpath) {
		System.setProperty("webdriver.ie.driver", driverpath);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("ignoreZoomLevel", true);
		capabilities.setJavascriptEnabled(true);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}

}
