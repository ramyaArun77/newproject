package org.test;

import java.io.IOException;
import java.util.Date;

import org.base.BaseClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.LaunchSuite;

public class Pgm1 extends BaseClass {

	@BeforeClass
	private void browsers() {
		
		impliwait(10);	
		System.out.println("Before Class- Launched ");
	}
	
	@BeforeMethod
	private void starttime() {
		Date d=new Date();
		System.out.println("Test Starting Time  :"+ d);
	}
	
	@AfterMethod
	private void endtime() {
		Date d=new Date();
		System.out.println("Test Ending Time  :"+ d);
	}
	
	@AfterClass
	private void closebrowser() {
		quit();
		System.out.println("After Class- Closed  ");
	}
	
	
	@Test
	private void logintest() throws IOException {
		
		System.out.println("Login page Tested");
		
	
	}
	
	
	
	}

