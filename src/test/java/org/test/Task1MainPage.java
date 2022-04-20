package org.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.LaunchSuite;

public class Task1MainPage extends BaseClass {

	@BeforeClass
	private void browsers() {
		launchBrowser("chrome");
		launchURL("https://www.amazon.in",10);
		impliwait(10);	
//		System.out.println("Before Class- Launched ");
	}
	
	@BeforeMethod
	private void starttime() {
		Date d=new Date();
//		System.out.println("Test Starting Time  :"+ d);
	}
	
	@AfterMethod
	private void endtime() {
		Date d=new Date();
//		System.out.println("Test Ending Time  :"+ d);
	}
	
	@AfterClass
	private void closebrowser() {
//		quit();
//		System.out.println("After Class- Closed  ");
	}
	
	
	
	@Test
	private void productSrch() throws IOException {
		System.out.println("Product search in amazon");
		ProductSearch ps=new ProductSearch();
		sendkeys(ps.getProductname(), "iphone 13 pro mobiles");
		btnclick(ps.getProductsearch());
//		List<WebElement> l = ps.getProductlist();
//		System.out.println(ps.getProductlist().size());
//		for(int i=0;i<l.size();i++) {
//			WebElement e = l.get(i);
//			String text = e.getText();
//			System.out.println(text);
//		}
		List<Integer>no=new ArrayList();
		List<WebElement> pp = ps.getProductprice();
		System.out.println(ps.getProductprice().size());
		for(int i=0;i<pp.size();i++) {
			WebElement ee = pp.get(i);
			String tt = ee.getText();
			String r = tt.replace(",", "");
			System.out.println(r);
			
			int n = Integer.parseInt(r);
			no.add(n);
			System.out.println("");
			
			
		}
		System.out.println(no);
		Integer max = Collections.max(no);
		System.out.println("Maximum amount is: "+max);
		System.out.println("");
		Integer min = Collections.min(no);
		System.out.println("Minimum amount is: "+min);
		System.out.println("");
		
		System.out.println("Alphabetical order of amount is : ");
		Collections.sort(no);
		System.out.println(no);
		System.out.println("");


	
	}
	
	}

