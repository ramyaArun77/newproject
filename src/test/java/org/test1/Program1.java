package org.test1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.test.ProductSearch;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Program1 extends BaseClass {
	@BeforeClass
	private void browsers() {
		launchBrowser("chrome");
		launchURL("https://www.amazon.in",10);
		impliwait(10);	
	}
	
		
	@Test
	private void productSrch() throws IOException {
		System.out.println("Product search in amazon");
		ProductSearch ps=new ProductSearch();
		sendkeys(ps.getProductname(), "oneplus 8t");
		btnclick(ps.getProductsearch());
		List<WebElement> l = ps.getProductlist();
		System.out.println(ps.getProductlist().size());
		for(int i=0;i<l.size();i++) {
			WebElement e = l.get(i);
			String text = e.getText();
			System.out.println(text);
		}
	
		}
	}

