package com.qait.automation.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import static com.qait.automation.utils.YamlReader.getYamlValue;
public class SampleDataProvider {

  @DataProvider()
  public static Iterator<Object[]> fileDataProvider() {
    String currentDir = System.getProperty("user.dir");
    String inputFile = "";
    YamlReader.setYamlFilePath();
    
    List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

    try {
      if (System.getProperty("os.name").contains("Windows")) {
        inputFile = currentDir + "\\DOI.csv";
      } else {
        inputFile = currentDir + "/DOI.csv";
      }
      List<String> testData = getFileContentList(inputFile);

      for (String userData : testData) {
        dataToBeReturned.add(new Object[] {userData});
      }
    } catch (Exception ex) {
      if (System.getProperty("os.name").contains("Windows")) {
        inputFile = currentDir + "\\src\\test\\resources\\testdata\\DOI.csv";
      } else {
        inputFile = currentDir + "/src/test/resources/testdata/DOI.csv";
      }
      List<String> testData = getFileContentList(inputFile);

      for (String userData : testData) {
        dataToBeReturned.add(new Object[] {userData});
      }
    }
    return dataToBeReturned.iterator();

  }

  public static List<String> getFileContentList(String filenamePath) {
    InputStream is;
    List<String> lines = new ArrayList<String>();
    int count = 1;
    try {
      is = new FileInputStream(new File(filenamePath));
      DataInputStream in = new DataInputStream(is);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String strLine;
        while ((strLine = br.readLine()) != null) {
          lines.add(strLine);
          count++;
      }
      in.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }
}
