package org.Base1;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass {
	public static WebDriver driver;
	public static WebDriver  launchBrowser(String browserName ) {
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		} else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}else {
			System.out.println("browser not valid");
		}
		return driver;}
//			switch(browserName) {
//	case "chrome":
//		WebDriverManager.chromedriver().setup();
//		driver=new ChromeDriver();
//	break;
//	case "firefox":
//		WebDriverManager.firefoxdriver().setup();
//		driver=new FirefoxDriver();
//		break;
//	case "edge":
//		WebDriverManager.edgedriver().setup();
//		driver=new EdgeDriver();
//		break;
//			}
//	return driver;
//		}
	public static void launchUrl(String Url) {
		driver.get(Url);

	}
	//windowMaximize
	public static void windowMaximize() {
		driver.manage().window().maximize();
	}	
	//implictWait
	public static void implictWait(long wait ) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}//threadSleep
	public static void threadSleep(int time) throws InterruptedException {
		Thread.sleep(time);
	}//getCurrentUrl
	public static String getCurrentUrl( ) {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}//getTitle
	public static String getTitle( ) {
		String title = driver.getTitle();
		return title;
	}//browserQuit
	public static void browserQuit() {
		driver.quit();
	}
	public static void clear(WebElement e) {
		e.clear();
	}//close
	public static void windowClose() {
driver.close();
	}
	public static String getAttribute(WebElement e) {
		String attribute = e.getAttribute("value");
		return attribute;
	}
	public static String getText (WebElement e) {
		String text = e.getText();
		return text;
	}
	public static void sendKeys (WebElement e ,String target) {
		e.sendKeys(target);
	}
	public static void btnclick(WebElement e) {
		e.click();
	}
	public static WebElement findElements(String typename,String locatorValue ) {
		WebElement value=null;
		if(typename.equals("id")) {

			value=driver.findElement(By.id(locatorValue));
		}
		else if(typename.equals("name")) {
			value=driver.findElement(By.name(locatorValue));
		}
		else if(typename.equals("Xpath")) {
			value=driver.findElement(By.xpath(locatorValue));
		}
		else if(typename.equals("tagName")) {
			value=driver.findElement(By.tagName(locatorValue));
		}
		else if(typename.equals("className")) {
			value=driver.findElement(By.className(locatorValue));
		}	return value;
	}
	//---------------get Excel cell Type---------------------------------
	public static String getExcellData(String FileName,String sheetName,int rowNo,int cellNo)throws IOException {
		File loc=new File("C:\\Users\\AJITH MECH\\eclipse-workspace\\0MavenTest\\src\\test\\resources\\"+FileName+".xlsx");
		FileInputStream fi=new FileInputStream(loc);
		Workbook w=new XSSFWorkbook(fi);
		Sheet s = w.getSheet(sheetName);
		Row r= s.getRow(rowNo);
		Cell cell = r.getCell(cellNo);
		int type = cell.getCellType();
		//1---- STRING
		//0--Number,DATE
		String value=null;
		if(type==1) {
			 value = cell.getStringCellValue();
		}else{
			if(DateUtil.isCellDateFormatted(cell)) {
			Date d = cell.getDateCellValue();
			SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
			 value = sd.format(d);}
			else {
				double db = cell.getNumericCellValue();
				long l=(long)db;
				 value = String.valueOf(l);
			}}
		
		return value;
	}
	//------------------Write Exceldata------------------------
	public static void setValueExcel(String fileName,String SheetName,int RowNo,int CellNO,String CellValue) throws IOException {
		File f=new File("C:\\Users\\AJITH MECH\\eclipse-workspace\\0MavenTest\\src\\test\\resources\\"+fileName+".xlsx");
		Workbook w=new XSSFWorkbook();
		Sheet s = w.createSheet(SheetName);
		Row r = s.createRow(RowNo);
		Cell c = r.createCell(CellNO);
		c.setCellValue(CellValue);
		FileOutputStream fo=new FileOutputStream(f);
		w.write(fo);
	}
	public static void setaddValueExcel(String fileName,String SheetName,int RowNo,int CellNO,String CellValue) throws IOException {
		File f=new File("C:\\Users\\AJITH MECH\\eclipse-workspace\\0MavenTest\\src\\test\\resources\\"+fileName+".xlsx");
		Workbook w=new XSSFWorkbook();
		Sheet s = w.createSheet(SheetName);
		Row r = s.createRow(RowNo);
		Cell c = r.createCell(CellNO);
		c.setCellValue(CellValue);
		
	}
	//---------------------existing file---------------------------
	public static void existValueExcel(String fileName,String SheetName,int RowNo,int CellNO,String CellValue) throws IOException {
		File f=new File("C:\\Users\\AJITH MECH\\eclipse-workspace\\0MavenTest\\src\\test\\resources\\"+fileName+".xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook w=new XSSFWorkbook(fi);
		Sheet s = w.getSheet(SheetName);
		Row r = s.getRow(RowNo);
		Cell c = r.createCell(CellNO);
		c.setCellValue(CellValue);
		FileOutputStream fo=new FileOutputStream(f);
		w.write(fo);
	}
	
	//-------------------JavaScript----------------------------------------
	//insert
	public static void jsInsertValue(WebElement target ,String txtvalue) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','"+txtvalue+"')",target );
	}
	//get attribute value
	public static String jsGetAttributevalue(WebElement target,String value) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		Object executeScript = js.executeScript("return arguments[0].getAttribute('"+value+"')",target );
		String outValue = executeScript.toString();
		return outValue;
	}
	//scroll up
	public static void jsScrollUp(WebElement target) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",target );
	}
	//scroll Down
	public static void jsScrollDown(WebElement target) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)",target );
	}
	//click
	public static void jsClick(WebElement target) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",target );
	}
	//------------------screenshot--------------------------------------------------
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
	//--------------------Actions--------------------------------------------------
	//MovetoElements---------
	public static void moveToElements(WebElement target){
		Actions a=new Actions(driver);
		a.moveToElement(target).perform();
	}
	//Sendkeys
	public static void SendKeysAction(WebElement target,String value) {
		Actions a=new Actions(driver);
		a.sendKeys(target, value).perform();
	}
	//click---------------
	public static void clickAction(WebElement target) {
		Actions a=new Actions(driver);
		a.click(target).perform();
	}
	//drag and drop---------------------------
	public static void dragAndDrop(WebElement src,WebElement des) {
		Actions a=new Actions(driver);
		a.dragAndDrop(src, des);
	}
	//contextclick---------------------------
	public static void contextClick(WebElement target) {
		Actions a=new Actions(driver);
		a.contextClick(target);
	}
	//DoubleClick-------------------------
	public static void doubleclick(WebElement target) {
		Actions a=new Actions(driver);
		a.doubleClick(target).perform();
	}
	//clickAndHold-------------------------
	public static void clickAndHoldRelease(WebElement src, WebElement des) {
		Actions a=new Actions(driver);
		a.clickAndHold(src).release(des).perform();

	}
	//---------------NAVIGATION-----------------------------------------------------
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
	//------------------------------Alert----------------------------------------------------
	//	confirmAlert
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
	//------Frames-------------------------------------------------
	//switchToFrame
	public static void  switchToFrame(WebElement target) {
		driver.switchTo().frame(target);
	}
	//defaultcontentframe
	public static void switchToDefaultContentFrame() {
		driver.switchTo().defaultContent();
	}//parentFrame
	public static void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}
	//-------------------------Select-DropDown----------------------------------------------------
	//is Multiple
	public static boolean isMultiple(WebElement e) {
		Select s=new Select(e);
		boolean multiple = s.isMultiple();
		return multiple;
	}
	//select by index----------
	public static void selectByIndex(WebElement e,int index) {
		Select a=new Select(e);
		a.selectByIndex(index);
	}
	//select by value----------
	public static void selectByValue(WebElement e,String value) {
		Select a=new Select(e);
		a.selectByValue(value);
	}
	//select by VisibleText
	public static void selectByVisibleText(WebElement e,String name) {
		Select a=new Select(e);
		a.selectByVisibleText(name);
	}
	//----------------------------deSelect-DropDown---------------------------
	//deSelect by index
	public static void UnselectByIndex(WebElement e,int index) {
		Select a=new Select(e);
		a.deselectByIndex(index);
	}
	//deSelectByValue
	public static void deSelectByValue(WebElement e,String value) {
		Select a=new Select(e);
		a.deselectByValue(value);
	}
	//deSelectByVisibleText
	public static  void deSelectByVisibleText(WebElement e,String value) {
		Select a=new Select(e);
		a.deselectByVisibleText(value);
	}
	//deselectAll
	public static void deSelectAll(WebElement e) {
		Select a=new Select(e);
		a.deselectAll();
	}
	//getOptions
	public static List<WebElement>  getOptions(WebElement e) {
		Select a=new Select(e);
		List<WebElement> options = a.getOptions();
//		for(WebElement x:options) {
//String text = x.getText();
		return options;
}
	//getAllSelectedOptions
	public static  List<WebElement> getSelectOptions(WebElement e) {
	Select s=new Select(e);
	List<WebElement> allSelectedOptions = s.getAllSelectedOptions();
	return allSelectedOptions;
	}
//firstSelectedoption	
	public static WebElement firstSelectedoption(WebElement e) {
		Select s=new Select(e);
		WebElement firstSelectedOption = s.getFirstSelectedOption();
		return firstSelectedOption;
		}
	//get all selected options
	public static  List<WebElement> getAllSelectedOptions(WebElement e) {
		Select s=new Select(e);
		List<WebElement> allSelectedOpts = s.getAllSelectedOptions();
		return allSelectedOpts;
	}

}






