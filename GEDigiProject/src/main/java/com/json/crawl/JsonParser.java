package com.json.crawl;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser 
{
public void readFromJsonFile()
{
	 JSONParser parser = new JSONParser();
	 Set<String> addressLinks = new HashSet<>();
	 Set<String> successLinks = new HashSet<>();
	 Set<String> allLinks = new HashSet<>();
	 Set<String> skippedLinks = new HashSet<>();
	 Set<String> errorLinks = new HashSet<>();
	 
	 try 
	 {
		 for(int ii = 1 ; ii<4 ; ii++)
		 {
			 Object obj = parser.parse(new FileReader("jsonFiles/internet_"+ii+".json"));
		 
		 JSONObject jsonObject = (JSONObject) obj;
		 JSONArray  pageList = (JSONArray) jsonObject.get("pages");
		 JSONObject jsonObject1 = null;
		 JSONArray  jsonLinks = null;
		 String address, tempLink;
		 
		 System.out.println("Started parsing Json file: "+ii+"...");
		 
		 for(int i = 0; i<pageList.size();i++)
		 {
			 jsonObject1 = (JSONObject) pageList.get(i);
			 address = (String) jsonObject1.get("address");
			 addressLinks.add(address);
			 successLinks.add(address);
			 jsonLinks = (JSONArray) jsonObject1.get("links");
			 for(int j = 0; j<jsonLinks.size();j++)
			 {
				 tempLink = (String) jsonLinks.get(j);
				 allLinks.add(tempLink);
				 
				 if(addressLinks.contains(tempLink))
				 {
				 skippedLinks.add(tempLink);
				 }
	
			 }

		 }
		 for(int i = 0; i<pageList.size();i++)
		 {
			 jsonObject1 = (JSONObject) pageList.get(i);
			 jsonLinks = (JSONArray) jsonObject1.get("links");
			 for(int j = 0; j<jsonLinks.size();j++)
			 {
				 tempLink = (String) jsonLinks.get(j);
				 if(!addressLinks.contains(tempLink))
				 {
				 errorLinks.add(tempLink);
				 }
			 }
		 }
		 
		
		 
		 for(String str : addressLinks)
		 {
			 if(!allLinks.contains(str))
			 {
				successLinks.remove(str);
			 }
		 }
		 
		 
		 
		 System.out.println("Parsing of JSON file :"+ii+" is completed and results are..");
		 System.out.println("Success");
		 System.out.println(successLinks);
		 System.out.println("Skipped");
		 System.out.println(skippedLinks);
		 System.out.println("Error");
		 System.out.println(errorLinks);
		 System.out.println("");
		
		 }
		 
	 }
	 catch(Exception e) 
	 {
         e.printStackTrace();
	 }
}
}
