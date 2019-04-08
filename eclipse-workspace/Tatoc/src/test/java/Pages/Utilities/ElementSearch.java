package Pages.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementSearch {
	
	WebDriver driver;
	static String fileName;
	static ArrayList<String> line = new ArrayList<String>();
	static String Selector;
	public ElementSearch(WebDriver driver, String file) {
		
		this.driver = driver;
		ElementSearch.fileName = "resource/SpecFiles/"+file+".spec";

	}
	
	public ElementSearch() {
		
	}

	public void file() throws IOException {
		
		File file = new File(ElementSearch.fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String readline;
		
		while ((readline = br.readLine()) != null)   {
			 
			ElementSearch.line.add(readline);
			
		}
		br.close();
		
	}
	
	public String findPath(String name) {
		
		for(int i = 0; i < ElementSearch.line.size(); i++) {
			
			if(ElementSearch.line.get(i).contains(name)) {
				
				String[] ar = ElementSearch.line.get(i).split("\\s+",3);
				ElementSearch.Selector = ar[1];
				String path = ar[2];
				return path;
			}
				
			
		}
		return null;
		
	}
	public WebElement findElement(String name) throws IOException {
		 
		 
	    	ElementSearch es = new ElementSearch();
	    	es.file();
		    String path = es.findPath(name);
	    	WebElement web = null;
	    	if(ElementSearch.Selector.contains("xpath")) {
	    		
	    		web = driver.findElement(By.xpath(path));
	    		return web;
	    		
	    	}
	    	if(ElementSearch.Selector.contains("id")) {
	    		
	    		web = driver.findElement(By.id(path));
	    		return web;
	    		
	    	}
	    	if(ElementSearch.Selector.contains("className")) {
	    		
	    		web = driver.findElement(By.className(path));
	    		return web;
	    		
	    	}
	    	
	    	return web;
	    	
	    }
	
}
