package com.doi.References;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReferencesStags {
	JavascriptExecutor js;
	WebDriver driver;
	public ReferencesStags(String a) {
		
		System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://achs-stag.literatumonline.com/doi/"+a);
		driver.manage().window().maximize();
		
		
	}
	
	public String stagName() {
		js = (JavascriptExecutor)driver;
		String element = "document.querySelector('.refs-header-label h2')";
		js.executeScript(element+".scrollIntoView();");
		String ref = (String) js.executeScript("return document.querySelector('.refs-header-label h2').innerHTML;");
		return ref;
		
	}
	public Long stagLength() {
		
		Long length = (Long) js.executeScript("return document.getElementById('references').getElementsByTagName('li').length");
		driver.close();
		return length;
		
	}
	

}
