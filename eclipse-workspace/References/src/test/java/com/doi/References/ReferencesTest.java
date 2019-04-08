package com.doi.References;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReferencesTest {

	Boolean a = true;
	@Test
	public void reference() throws IOException {
		File file = new File("Resources/test.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String readline;
		ArrayList<String> line = new ArrayList<String>();
		while ((readline = br.readLine()) != null)   {
			 
			line.add(readline);
			
		}
		for(int i = 0;i<line.size();i++) {
			
		
		Referencespubs pb = new Referencespubs(line.get(i));
		ReferencesStags sg = new ReferencesStags(line.get(i));
		String pubsName = pb.pubsName();
		String stagsName = sg.stagName();
		Boolean b;
		if(pubsName.equals(stagsName)) {	
			b = true;
			Assert.assertEquals(b, a);
		}
		Long pubsLength = pb.pubsLength();
		Long stagsLength = sg.stagLength();
		if(pubsLength == stagsLength) {	
			b = true; 
			Assert.assertEquals(b, a);	
		}
		}
	}
}
		
    
