package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	
	public static WebDriver launchBrowser(String BrowserName) {

		switch (BrowserName) {
		case  "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case  "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		case  "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		}
		return driver;
	}
	
	public static void launchURL(String url, long time) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	public static void impliwait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		
	}
	
	public static void threadsleep(int time) throws InterruptedException {
		Thread.sleep(time);
	}

	public static String getcurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	
	public static String gettitle() {
		String gettitle = driver.getTitle();
		return gettitle;
	}
	
	public static void quit() {
		driver.quit();
	}
	
	public static void clear(WebElement e) {
		e.clear();
	}
	
	public static void close() {
		driver.close();
	}
	
	public static String gettext(WebElement e) {
		String text = e.getText();
		return text;
	}
	
	public static String getAttribute(WebElement e) {
		String attribute = e.getAttribute("value");
		return attribute;
	}
	
	public static WebElement findelement(String locatorname,String locatorvalue) {
		WebElement value=null;
		if (locatorname.equals("id")) {
			value=driver.findElement(By.id(locatorvalue));
		}
		else if (locatorname.equals("name")) {
			value=driver.findElement(By.name(locatorvalue));
		}
		else if (locatorname.equals("xpath")) {
			value= driver.findElement(By.xpath(locatorvalue));
		}
		else if (locatorname.equals("tagName")) {
			value= driver.findElement(By.tagName(locatorvalue));
		}
		else if (locatorname.equals("className")) {
			value= driver.findElement(By.className(locatorvalue));
		}
		return value;
   }
	
	public static void sendkeys(WebElement e,String value) {
		e.sendKeys(value);
	}

	public static void btnclick(WebElement e) {
		e.click();
	}

	//Actions
	public static void SendKeysAction(WebElement e,String value) {
		Actions a=new Actions(driver);
		a.sendKeys(e, value).perform();
	}
	
	public static void movetoelement(WebElement e) {
		Actions a=new Actions(driver);
		a.moveToElement(e).perform();
	}
	
	public static void clickAction(WebElement e) {
		Actions a=new Actions(driver);
		a.click(e).perform();
	}
	
	public static void doubleclk(WebElement e) {
		Actions a=new Actions(driver);
		a.doubleClick(e).perform();
	}
	
	public static void contextClick(WebElement e) {
		Actions a=new Actions(driver);
		a.contextClick(e);
	}
	
	public static void clickAndHoldRelease(WebElement src, WebElement des) {
		Actions a=new Actions(driver);
		a.clickAndHold(src).release(des).perform();

	}
	
	public static void draganddrop(WebElement src,WebElement des) {
		Actions a=new Actions(driver);
		a.dragAndDrop(src, des).perform();
	}	
	
	public static void pause(long time) {
		Actions a=new Actions(driver);
		a.pause(time).perform();
	}
	
	public static void lpause(long second) {
		Actions a=new Actions(driver);
		a.pause(Duration.ofSeconds(second)).perform();
	}
	
	//select
	public static void selectbyindex(WebElement e, int index) {
		Select s=new Select(e);
		s.selectByIndex(index);
	}
	
	public static void selectbyvalue(WebElement e, String value) {
		Select s=new Select(e);
		s.selectByValue(value);
	}
	
	public static void deselectbyindex(WebElement e, int index) {
		Select s=new Select(e);
		s.deselectByIndex(index);
	}
	
	public static void deselectbyvalue(WebElement e, String value) {
		Select s=new Select(e);
		s.deselectByValue(value);
	}
	
	public static void selectByVisibleText(WebElement e, String value) {
		Select s=new Select(e);
		s.selectByVisibleText(value);
	}
	
	public static void deselectByVisibleText(WebElement e, String value) {
		Select s=new Select(e);
		s.deselectByVisibleText(value);
	}
	
	public static void deSelectAll(WebElement e) {
		Select a=new Select(e);
		a.deselectAll();
	}
	
	public static boolean isMultiple(WebElement e) {
		Select s=new Select(e);
		boolean multiple = s.isMultiple();
		return multiple;
	}
	
	//options
	public static List<WebElement>  getOptions(WebElement e) {
		Select a=new Select(e);
		List<WebElement> options = a.getOptions();
		return options;
	}
	
	public static  List<WebElement> getSelectOptions(WebElement e) {
		Select s=new Select(e);
		List<WebElement> allSelectedOptions = s.getAllSelectedOptions();
		return allSelectedOptions;
	}
	
	public static WebElement firstSelectedoption(WebElement e) {
		Select s=new Select(e);
		WebElement firstSelectedOption = s.getFirstSelectedOption();
		return firstSelectedOption;
	}
	
	public static  List<WebElement> getAllSelectedOptions(WebElement e) {
		Select s=new Select(e);
		List<WebElement> allSelectedOpts = s.getAllSelectedOptions();
		return allSelectedOpts;
	}
	
	//JavascriptExecutor
	//insert
	public static void jsInsertValue(WebElement e ,String txtvalue) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','"+txtvalue+"')",e );
	}
	//get attribute value
	public static String jsGetAttributevalue(WebElement e,String value) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		Object executeScript = js.executeScript("return arguments[0].getAttribute('"+value+"')",e );
		String outValue = executeScript.toString();
		return outValue;
	}
	//scroll up
	public static void jsScrollUp(WebElement e) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",e );
	}
	//scroll Down
	public static void jsScrollDown(WebElement e) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)",e );
	}
	//click
	public static void jsClick(WebElement e) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",e );
	}
	
	//Screenshot
	public static void takeScreenShot(String destarget) throws IOException  {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des=new File(destarget);
		FileUtils.copyFile(src, des);
	}
	
	//date
	public static int datee() {
		Date d=new Date();
		int seconds = d.getSeconds();
		return seconds;
	}
	public static long time() {
		long timeMillis = System.currentTimeMillis();
		return timeMillis;
	}
	
	//Navigation
	
	//back
		public static void back() {
			driver.navigate().back();
		}
		//forward
		public static void forward() {
			driver.navigate().forward();
		}
		//refresh
		public static void refresh() {
			driver.navigate().refresh();
		}//navigateToUrl
		public static void navigateto(String url) {
			driver.navigate().to(url);
		}//windowHandling---------
		public static String WindowHandling() {
			String windowHandle = driver.getWindowHandle();
			return windowHandle;
		}
		//windowHandles-------------
		public static Set<String> WindowAllHandling() {
			Set<String> windowHandles = driver.getWindowHandles();
			return windowHandles;
		}
		//isselected
		public static boolean isSelected(WebElement e) {
			boolean selected = e.isSelected();
			return selected;
		}
		//isEnabled
		public static boolean isEnabled(WebElement e) {
			boolean selected = e.isEnabled();
			return selected;
		}
		//isDisplayed
		public static boolean isDisplayed(WebElement e) {
			boolean selected = e.isDisplayed();
			return selected;
		}
		
		//Alert
//		confirmAlert
		public static void confirmAlert(String alertType,String ewq) {
			if(alertType.equals("simpleAlert")) {
				Alert a=driver.switchTo().alert();
				a.accept();
			}
			if(alertType.equals("confirmAlert")) {
				Alert a=driver.switchTo().alert();
				if(ewq.equals("accept")) {
					a.accept();
				}if(ewq.equals("dismiss")) {
					a.dismiss();
				}}
		}
		
		//Accept
		public static void acceptAlert( ) {
			Alert a=driver.switchTo().alert();
			a.accept();
		}
		
		//dismissAlert
		public static void dismissAlert() {
			Alert a=driver.switchTo().alert();
			a.dismiss();
		}
		
		//promptAlert
		public static void promptAlert(String action,String value) {
			if(action.equals("accept")){
				Alert a=driver.switchTo().alert();
				a.sendKeys(value);
				a.accept();}
			if(action.equals("dismiss")){
				Alert a=driver.switchTo().alert();
				a.sendKeys(value);
				a.dismiss();
			}
		}

		//Frames
		//switchToFrame
		public static void  switchToFrame(WebElement target) {
			driver.switchTo().frame(target);
		}
		//defaultcontentframe
		public static void switchToDefaultContentFrame() {
			driver.switchTo().defaultContent();
		}
		//parentFrame
		public static void switchToParentFrame() {
			driver.switchTo().parentFrame();
		}
		
		
	public static String getExcelData(String filename, String sheetname,int rowno, int cellno) throws IOException {
		File loc =new File("D:\\New folder\\MavenProject1\\src\\test\\resources\\"+filename +".xlsx");
		FileInputStream f=new FileInputStream(loc);
		Workbook w=new XSSFWorkbook(f);
		Sheet s = w.getSheet(sheetname);
		Row r = s.getRow(rowno);
		Cell cell = r.getCell(cellno);
		int type = cell.getCellType();
		String value=null;
		
		if(type==1) {
			value = cell.getStringCellValue();
		}
		else {
			if(DateUtil.isCellDateFormatted(cell)) {
//				Date d = cell.getDateCellValue();
//				SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
//				 value = sd.format(d);
				value=new SimpleDateFormat("dd-mmm-yyyy").format(cell.getDateCellValue());
			}
			else {
				value = String.valueOf((long) cell.getNumericCellValue());
			}
		}
		return value;
	}
 
	//write excel data
	public static void setValueExcel(String fileName,String SheetName,int RowNo,int CellNO,String CellValue) throws IOException {
		File f=new File("D:\\New folder\\MavenProject1\\src\\test\\resources\\"+fileName +".xlsx");
		Workbook w=new XSSFWorkbook();
		Sheet s = w.createSheet(SheetName);
		Row r = s.createRow(RowNo);
		Cell c = r.createCell(CellNO);
		c.setCellValue(CellValue);
		FileOutputStream fo=new FileOutputStream(f);
		w.write(fo);
	}
	public static void setaddValueExcel(String fileName,String SheetName,int RowNo,int CellNO,String CellValue) throws IOException {
		File f=new File("D:\\New folder\\MavenProject1\\src\\test\\resources\\"+fileName +".xlsx");
		Workbook w=new XSSFWorkbook();
		Sheet s = w.createSheet(SheetName);
		Row r = s.createRow(RowNo);
		Cell c = r.createCell(CellNO);
		c.setCellValue(CellValue);
		
	}
	//---------------------existing file---------------------------
	public static void existValueExcel(String fileName,String SheetName,int RowNo,int CellNO,String CellValue) throws IOException {
		File f=new File("D:\\New folder\\MavenProject1\\src\\test\\resources\\"+fileName +".xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook w=new XSSFWorkbook(fi);
		Sheet s = w.getSheet(SheetName);
		Row r = s.getRow(RowNo);
		Cell c = r.createCell(CellNO);
		c.setCellValue(CellValue);
		FileOutputStream fo=new FileOutputStream(f);
		w.write(fo);
	}







}
