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
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class issue extends BaseTest {
	private String DOI = "";
	protected TestSessionInitiator test;
	String currentDir = System.getProperty("user.dir");

	// test.launchApplication(getYamlValue("baseUrl"));

	@BeforeClass
	public void lau() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
	}

	@AfterClass
	public void close_Test_Session() throws IOException {
		test.closeBrowserSession();
	}

	@Test
	public void Step29_AJ_29_Verify_Functionality_Of_Elements_On_Article_Landing_Page() throws IOException {

		File file = new File(currentDir + "/IssueDOI.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		// BufferedWriter writer = new BufferedWriter(new
		// FileWriter("/home/qainfotech/test2.txt"));
		FileOutputStream fout = new FileOutputStream(currentDir + "/Issue.txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
		String readline;
		ArrayList<String> line = new ArrayList<String>();
		while ((readline = br.readLine()) != null) {

			line.add(readline);

		}

		System.out.println(line.size());
		for (int i = 0; i < line.size(); i++) {

			test.launchApplication("https://achs-stag.literatumonline.com" + line.get(i));
			if (test.getpageTitle().equals("Error (ACS Publications)")) {

				continue;

			}

			for (String doi : test.articlePage.getIssueDOIs()) {
				bw.write(doi);
				bw.newLine();
				bw.flush();
			}

		}
	}

}