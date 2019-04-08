package com.qait.automation.getpageobjects;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class reads the PageObjectRepository text files. Uses buffer reader.
 *
 * @author sunena
 *
 */
public class ObjectFileReader {

	public static String tier;
	static String tierInputName;
	static String filepath = "src/test/resources/PageObjectRepository/";
	static String page;

	

	public static String[] getELementFromFile(String pageName, String elementName) {
		try {
			FileReader specFile = new FileReader(filepath + tier + pageName + ".spec");
			return getElement(specFile, elementName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Check if Element exist in Common Tier

	public static boolean isFindFileAndElementPresent(String pageName, String elementName) {
		boolean flag = false;
		try {
			FileReader specFile = new FileReader(
					filepath + File.separator + "Common" + File.separator + pageName + ".spec");
			ArrayList<String> elementLines = getSpecSection(specFile);
			for (String elementLine : elementLines) {
				if (elementLine.startsWith(elementName)) {
					flag = true;
				}
			}
		} catch (Exception e) {
			System.out.println("FileReader Exception " + e);
		}
		return flag;
	}

	public static String getPageTitleFromFile(String pageName) {
		setTier();
		BufferedReader br = null;
		String returnElement = "";
		try {
			br = new BufferedReader(new FileReader(filepath + tier + pageName + ".spec"));
			String line = br.readLine();

			while (line != null && !line.startsWith("========")) {
				String titleId = line.split(":", 3)[0];
				if (titleId.equalsIgnoreCase("pagetitle") || titleId.equalsIgnoreCase("title")
						|| titleId.equalsIgnoreCase("page title")) {
					returnElement = line;
					break;
				}
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return returnElement.split(":", 2)[1].trim();
	}

	private static String[] getElement(FileReader specFile, String elementName) throws Exception {

		String[] elementLineSplit;
		ArrayList<String> elementLines = getSpecSection(specFile);

		for (String elementLine : elementLines) {
			elementLineSplit = elementLine.split(" ");
			if (elementLineSplit[0].trim().equalsIgnoreCase(elementName)) {
				return elementLine.split(" ", 4);
			} else {
				continue;
			}
		}

		return null;
		// throw new Exception();
	}

	private static ArrayList<String> getSpecSection(FileReader specfile) {
		String readBuff = null;
		ArrayList<String> elementLines = new ArrayList<String>();
		try {
			BufferedReader buff = new BufferedReader(specfile);
			try {
				boolean flag = false;
				readBuff = buff.readLine();
				while ((readBuff = buff.readLine()) != null) {
					if (readBuff.startsWith("========")) {
						flag = !flag;
					}
					if (flag) {
						elementLines.add(readBuff.trim().replaceAll("[ \t]+", " "));
					}
					if (!elementLines.isEmpty() && !flag) {
						break;
					}
				}
			} finally {
				buff.close();
				if (elementLines.get(0).startsWith("========")) {
					elementLines.remove(0);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Spec File not found at location :- " + filepath);
		} catch (IOException e) {
			System.out.println("exceptional case");
		}
		return elementLines;
	}

	public static void setTier() {
		try {
			if (System.getProperty("tier").contains("defaultTier") || System.getProperty("tier").isEmpty())
				tierInputName = getProperty("tier").toString();
			else
				tierInputName = System.getProperty("tier");
		} catch (NullPointerException e) {
			tierInputName = getProperty("tier").toString();
		}
//		String envi =getProperty("tier").substring(0,3).toLowerCase();
		switch (getProperty("tier")) {
		case "production":
		case "PROD":
		case "PRODUCTION":
		case "Production":
		case "prod":
			tier = "PROD/";
			break;
		case "stag":
		case "staging":
		case "Staging":
		case "STAGING":
		case "preProd":
		case "PREPROD":
		case "preprod":
			tier = "STAGING/";
			break;
		case "Dev":
		case "DEV":
		case "dev":
			tier = "DEV/";
			break;
		}
	}

}
