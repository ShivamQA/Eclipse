package com.doi.References;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Referencespubs {
	JavascriptExecutor js;
	WebDriver driver;
	public Referencespubs(String a) {
		
		System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://pubs.acs.org/doi/"+a);
		driver.manage().window().maximize();
		
		
	}
	
	public String pubsName() {

		js = (JavascriptExecutor)driver;
		
		js.executeScript("document.querySelector('.NLM_notes~ul[class=\"anchors\"]+h2').scrollIntoView();");
		String ref = (String) js.executeScript("return document.querySelector('.NLM_notes~ul[class=\"anchors\"]+h2').innerHTML");
		return ref;
		
	}
	public Long pubsLength() {
		
		Long length = (Long) js.executeScript("return document.getElementById('references').getElementsByTagName('li').length");
		driver.close();
		return length;
		
	}
	

}
