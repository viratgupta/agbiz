package com.agilebiz.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author virat
 *
 */
public class GenericMethods {

	public static WebDriver driver;
	public static Properties Repository = new Properties();
	public static ExtentReports extent;
	public ExtentTest test;
	public ITestResult result;
	public static ResultSet rs;
	public static Connection con;
	public static Row row;
	public static Cell cell;
	public static HSSFWorkbook workbook;
	public static HSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	static String SheetName = "Sheet1";
	public String Res;
	public int DataSet = -1;
	By dimmer_xpath = By.xpath("//*[@class='loadingoverlay']");
	Actions action;

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports("G:/Agilebiz_App/agilebiz/Reports/ExtentReport "
				/* + formater.format(calendar.getTime()) */ + ".html", true);
	}

	public static String AddNoToCurrentDate(String CurrentDate, int IncrementedDays) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(CurrentDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Incrementing the date by 1 day
		c.add(Calendar.DAY_OF_MONTH, IncrementedDays);
		String newDate = sdf.format(c.getTime());
		System.out.println("Date Incremented by One: ;" + newDate);
		return newDate;
	}

	public WebDriver selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Browser is FIREFOX");
			/*
			 * String GeckoDriverPath= System.getProperty("user.dir")+
			 * "/src/main/resources/BrowsersDrivers/geckodriver.exe";
			 * System.setProperty("webdriver.gecko.driver", GeckoDriverPath);
			 */
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Browser is CHROME");
			String ChromeDriverPath = System.getProperty("user.dir")
					+ "/src/main/resources/BrowserDrivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("test-type");
			 * options.addArguments("start-maximized");
			 * options.addArguments("disable-infobars");
			 * options.addArguments("--disable-extensions"); driver = new
			 * ChromeDriver(options);
			 */

			return driver;
		} else if (browser.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			return driver;
		}
		return null;

	}

	public boolean isElementPresent(String Element) {
		try {
			implicitWait(2);
			List<WebElement> elementWebE = getWebElements(Element);
			if (elementWebE.size() != 0) {
				return true;
			} else
				return false;
		} catch (Exception ex) {
			return false;
		} finally {
			implicitWait(3);
		}

	}

	public void selectDropDownValue(WebElement element, String dropDownValue) {
		implicitWait(5);
		Select select = new Select(element);
		Reporter.log("selecting " + dropDownValue + " value from dropdown");
		select.selectByValue(dropDownValue);
	}

	public void selectDropDownValueUsingIndex(WebElement element1, int index) {
		Select select1 = new Select(element1);
		Reporter.log("selecting " + index + " value from dropdown");
		select1.selectByIndex(index);
	}

	public void selectByText(WebElement ddelement, String dropdownText) {
		implicitWait(10);
		Select dropdown = new Select(ddelement);
		dropdown.selectByVisibleText(dropdownText);
	}

	public void selectListofRecords(WebElement dropdownElement) {
		Select dropdown = new Select(dropdownElement);
		// Get all options
		List<WebElement> dd = dropdown.getOptions();

		// Get the length
		// System.out.println(dd.size());
		int AllValues = dd.size() - 1;
		String tp = String.valueOf(AllValues);
		Reporter.log("Total Records" + tp);

		if (AllValues > 0) {
			// print the list of records.
			for (int j = 1; j < dd.size(); j++) {
				Reporter.log(dd.get(j).getText());
			}
		} else {
			Reporter.log("No Record Found");
			Assert.fail();
		}

	}

	public void SidebarMenu(WebElement Fst_element, WebElement child_element) throws Exception {
		implicitWait(15);
		Actions action = new Actions(driver);
		action.moveToElement(Fst_element).moveToElement(child_element).click().perform();
	}

	public void SwitchFrame(WebElement frameName) throws InterruptedException {

		driver.switchTo().frame(frameName);
		Thread.sleep(2000);
	}

	public void implicitWait(int timeInsec) {
		// Reporter.log("waiting for page to load...");
		driver.manage().timeouts().implicitlyWait(timeInsec, TimeUnit.SECONDS);

	}

	public void driverwait(int timeToWaitInSec) throws InterruptedException {
		// Reporter.log("waiting for "+timeToWaitInSec+" seconds...");
		Thread.sleep(timeToWaitInSec * 1000);
	}

	public static void explicitWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void WaitForAlertBox() {
		WebDriverWait wait = new WebDriverWait(driver, 15, 100);
		wait.until(ExpectedConditions.alertIsPresent());

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public String getTextFromAlertBox() {
		Alert alert = driver.switchTo().alert();
		String alertmsg = alert.getText();
		return alertmsg;
	}

	public void AcceptAlertBox() throws InterruptedException {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driverwait(2);
	}

	public void dismissAlertBox() throws InterruptedException {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		driverwait(2);
	}

	public void scrollUp() throws InterruptedException {
		driverwait(3);

		((JavascriptExecutor)

		driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");

	}

	public void scrollDown() throws InterruptedException {
		driverwait(3);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 250)");
	}

	public void ChildWindowHandle() {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

	public void ScrollDownUsingAction(WebElement targetelement) throws InterruptedException {
		Actions sdown = new Actions(driver);
		sdown.moveToElement(targetelement);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(document.body.scrollHeight,-100)");
		driverwait(2);
	}

	public boolean retryingFindClick(WebElement webelementtoWait) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 5) {
			try {
				webelementtoWait.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public void validateTooltip(String ER, String tooltipElement) throws Exception {
		String ExpectedResult = ER;
		String ActualResult = getWebElement(tooltipElement).getAttribute("title");
		if (ExpectedResult.equalsIgnoreCase(ActualResult)) {
			Reporter.log("ActualToolTip is " + ActualResult);
			Reporter.log("Testcase PASS");
		} else {
			Reporter.log("Testcase FAIL");
			Assert.fail();
		}
	}

	public void validatecondition(String ExpectedResult, String ActualElement) throws Exception {
		String Actual = getWebElement(ActualElement).getText();
		System.out.println("Actual Heading " + Actual);
		if (ExpectedResult.equalsIgnoreCase(Actual)) {
			Reporter.log("ExpectedResult= " + ExpectedResult + " " + "ActualResult= " + Actual);
			Reporter.log("Testcase PASS");
		} else {
			Reporter.log("Testcase FAIL");
			Assert.fail();
		}
	}

	public void validateconditionBetweenElements(String ExpectedResult, String ActualElement) throws Exception {
		String Actual = getWebElement(ActualElement).getText();
		String Expected = getWebElement(ExpectedResult).getText();
		System.out.println("Actual Heading " + Actual);
		if (Expected.equalsIgnoreCase(Actual)) {
			Reporter.log("ExpectedResult= " + Expected + " " + "ActualResult= " + Actual);
			Reporter.log("Testcase PASS");
		} else {
			Reporter.log("Testcase FAIL");
			Assert.fail();
		}
	}

	public void ValidateElementContainsText(String Element, String ContainText) throws Exception {
		if (getWebElement(Element).getText().contains(Repository.getProperty(ContainText))) {
			Reporter.log("Testcase PASS");
		} else {
			Reporter.log("Testcase FAIL");
		}
	}

	public void validateElementIsDisplayed(String ElementPresence) throws Exception {
		implicitWait(5);
		if (getWebElement(ElementPresence).isDisplayed()) {
			Reporter.log("Testcase PASS");
		} else {
			Reporter.log("Test case FAIL");
			Assert.fail();
		}
	}

	public void validateElementIsDisplayedWithElement(WebElement Elementname) throws Exception {
		implicitWait(5);
		if (Elementname.isDisplayed()) {
			Reporter.log("Testcase PASS");
		} else {
			Reporter.log("Test case FAIL");
			Assert.fail();
		}
	}

	public void validateElementsAreDisplayed(String Elementname) throws Exception {
		implicitWait(5);
		for (WebElement we : getWebElements(Elementname)) {
			if (we.isDisplayed()) {
				Reporter.log("Testcase PASS");
			} else {
				Reporter.log("Test case FAIL");
				Assert.fail();
			}

		}

	}

	public void validateElementIsSelected(String ElementPresence) throws Exception {
		driverwait(2);
		if (getWebElement(ElementPresence).isSelected()) {
			Reporter.log("Element is Selected");
			// Reporter.log("Testcase PASS");
		} else {
			Reporter.log("element is not Selected");
			// Reporter.log("Test case FAIL");
		}
	}

	public void doubleClick(WebElement doubleclickElement) {
		Actions action = new Actions(driver).doubleClick(doubleclickElement);
		action.build().perform();
		implicitWait(2);
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {

		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().contains(fileName)) {
				// File has been found, it can now be deleted
				/*
				 * Reporter.log("Test case PASS"); dirContents[i].delete();
				 */
				return true;
			}
		}
		/*
		 * Reporter.log("Testcase FAIL"); Assert.fail();
		 */
		return false;

	}

	public void isFileDownloaded_Ext(String dirPath, String ext) {
		// boolean flag=false;
		File dir = new File(Repository.getProperty(dirPath));
		File[] files = dir.listFiles();
		System.out.println(files.length + files.toString());
		if (files == null || files.length == 0) {
			// flag = false;
			Reporter.log("File Not Found");
			Assert.fail();
		}

		for (int i = 1; i < files.length; i++) {
			if (files[i].getName().contains(Repository.getProperty("IRivwname") + ext)) {
				// flag=true;
				Reporter.log("FileName is: " + files[i].getName());
				Reporter.log("Test Case PASS");
			}
		}
		// return flag;
	}

	public void getLatestFilefromDirWithExt(String dirPath, String ext) {
		// boolean flag=false;
		File dir = new File(Repository.getProperty(dirPath));
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			// flag = false;
			Reporter.log("File not Present");
			Assert.fail();
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
				String filename = lastModifiedFile.getName();
				System.out.println(filename);
				if (lastModifiedFile.getName().contains(Repository.getProperty(ext))) {
					Reporter.log("File name is: " + lastModifiedFile.getName());
					Reporter.log("TestCase PASS");
				}
			}

		}
		// return flag;
	}

	public void ValidateElementIsPresent(String ElementName) throws Exception {
		WebElement deleteElement = null;
		try {
			deleteElement = getWebElement(ElementName);
		} catch (NoSuchElementException e) {

		}
		Assert.assertTrue(deleteElement != null, "Testcase PASS");
	}

	public Boolean AfterdeleteIsElementPresent(String deletedElement) throws Exception {
		try {
			implicitWait(0);
			getWebElement(deletedElement);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		} finally {
			implicitWait(30);
		}

	}

	public void ValidatedeletedElement(String elemnttoFound) throws Exception {
		List<WebElement> deleteLinks = getWebElements(elemnttoFound);
		if (deleteLinks.isEmpty()) {
			Reporter.log("Test case PASS");
		} else {
			Reporter.log("Test case FAIL");
			Assert.fail();
		}
	}

	public void FileToBeUpload(String chooseFileLocator, String AutoITFilePath) throws Exception {
		getWebElement(chooseFileLocator).click();
		driverwait(2);
		Runtime.getRuntime().exec(Repository.getProperty(AutoITFilePath));
		Reporter.log("File is attached or selected");
		implicitWait(3);
	}

	public void GetAlertMessageInAxpert(String AlertMessageElement) throws Exception {
		explicitWait(getWebElement(AlertMessageElement), 7);
		String AlertMsg = getWebElement(AlertMessageElement).getText();
		Reporter.log("Alert Message '" + AlertMsg + "'");
	}

	public String ReturnAlertMessageInAxpert(String AlertMessageElement) throws Exception {
		explicitWait(getWebElement(AlertMessageElement), 7);
		String AlertMsg = getWebElement(AlertMessageElement).getText();
		System.out.println(AlertMsg);
		return AlertMsg;
	}

	public void AscendingOrderSorting(String columnElement, String sortbtn) throws Exception {
		ArrayList<String> obtainedList = new ArrayList<String>();
		List<WebElement> elementList = getWebElements(columnElement);
		for (WebElement we : elementList) {
			obtainedList.add(we.getText());
			// System.out.println(we.getText());
		}
		// getWebElement(sortbtn).click();
		ArrayList<String> sortedList = new ArrayList<String>();
		for (String s : obtainedList) {
			sortedList.add(s);

		}
		Collections.sort(sortedList);
		System.out.println("====" + sortedList);
		getWebElement(sortbtn).click();

		ArrayList<String> aftersort = new ArrayList<String>();
		List<WebElement> sortelement = getWebElements(columnElement);
		for (WebElement se : sortelement) {
			aftersort.add(se.getText());
			System.out.println(se.getText());
		}
		System.out.println(sortedList + "**" + aftersort);
		Assert.assertTrue(sortedList.equals(aftersort), "Test case PASS");

	}

	public static void ReadExcelData() throws IOException {
		FileInputStream fis = new FileInputStream(
				"G:/AxpertX Automation/Axpert10/src/main/java/hybrid/Data/TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		// adding test data in the cell A1 as
		// Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as
		// 0.
		row = sheet.getRow(0);
		cell = row.getCell(0);
		System.out.println(cell);
		System.out.println(sheet.getRow(0).getCell(0));
		// String cellval = cell.getStringCellValue();
		// System.out.println(cellval);
	}

	@DataProvider
	public static Object[][] ReadVariant() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(
				"G:/AxpertX Automation/Axpert10/src/main/java/hybrid/Data/TestData.xlsx");
		workbook = new HSSFWorkbook(fileInputStream); // get my workbook
		worksheet = workbook.getSheet(SheetName);// get my sheet from workbook
		HSSFRow Row = worksheet.getRow(0); // get my Row which start from 0

		int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of
															// Rows
		int ColNum = Row.getLastCellNum(); // get last ColNum

		Object Data[][] = new Object[RowNum - 1][ColNum]; // pass my count data
															// in array

		for (int i = 0; i < RowNum - 1; i++) // Loop work for Rows
		{
			HSSFRow row = worksheet.getRow(i + 1);

			for (int j = 0; j < ColNum; j++) // Loop work for colNum
			{
				if (row == null)
					Data[i][j] = "";
				else {
					HSSFCell cell = row.getCell(j);
					if (cell == null)
						Data[i][j] = ""; // if it get Null value it pass no data
					else {
						String value = formatter.formatCellValue(cell);
						Data[i][j] = value; // This formatter get my all values
											// as string i.e integer, float all
											// type data value
					}
				}
			}
		}

		return Data;
	}

	public static WebElement getLocator(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));

		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));

		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));

		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.tagName(locatorValue));

		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));

		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));

		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));

		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));

		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	public static List<WebElement> getLocators(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));

		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));

		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));

		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));

		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));

		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));

		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));

		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	public WebElement getWebElement(String locator) throws Exception {
		return getLocator(Repository.getProperty(locator));
	}

	public List<WebElement> getWebElements(String locator) throws Exception {
		return getLocators(Repository.getProperty(locator));
	}

	public String getScreenShot(String imageName) throws IOException {

		if (imageName.equals("")) {
			imageName = "blank";
		}
		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = "G:/Agilebiz_App/agilebiz/Reports/FailureScreenshots/";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imagelocation + imageName + "_" + formater.format(calendar.getTime()) + ".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);
		return actualImageName;
	}

	public void selectAndDeleteTextViaKeyboard() {
		selectTextViaKeyboard();
		deleteViaKeyboard();
	}

	public void deleteViaKeyboard() {
		action = new Actions(driver);
		action.sendKeys(Keys.DELETE).build().perform();
	}

	public void selectTextViaKeyboard() {
		action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();

	}

	public boolean isAttribtuePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	// Adding values and Validating Grid
	public void addrecordingrid(Xls_Reader xls_reader, String dcSheetname, String transno, String gridtableid,
			String fillgrid) throws Exception {

		implicitWait(10);
		int rowCount = xls_reader.getRowCount(dcSheetname);
		int columnCount = xls_reader.getColumnCount(dcSheetname);
		excelloop: for (int exrowval = 4; exrowval <= rowCount;) {

			String exrow1 = xls_reader.getCellData(dcSheetname, 0, exrowval);

			if (!exrow1.equalsIgnoreCase(transno)) {

				exrowval++;

			} else {
				// need to read the data starting from 1 row all columns
				int gridrownum = 1;
				for (int rowNum = exrowval; rowNum <= rowCount; rowNum++) {

					String data0 = xls_reader.getCellData(dcSheetname, 0, rowNum);
					String nexttransval = xls_reader.getCellData(dcSheetname, 0, rowNum + 1);
					if (data0.equalsIgnoreCase(transno)) {

						String gridedit = "//table[@id='gridHd2']/tbody/tr[" + (gridrownum) + "]/td[1]/button[1]";
						driver.findElement(By.xpath(gridedit)).click();

						for (int columnNum = 1; columnNum < columnCount; columnNum++) {

							String gridfieldvalue = xls_reader.getCellData(dcSheetname, columnNum, 2);
							String[] colheader = gridfieldvalue.split("1");

							String excval = xls_reader.getCellData(dcSheetname, columnNum, 3);
							if (columnNum != columnCount) {
								// action class
								action = new Actions(driver);
								String data = xls_reader.getCellData(dcSheetname, columnNum, rowNum);
								System.out.println(data);
								String gridfieldlocator = "//*[@id='" + colheader[0] + gridrownum + colheader[1] + "']";
								WebElement gridelement = driver.findElement(By.xpath(gridfieldlocator));
								webElementWait(gridelement);
								gridelement.click();

								if (excval.equalsIgnoreCase("SET")) {

									if ((gridelement.getTagName()).equalsIgnoreCase("select")) {
										selectByText(gridelement, data);
										driverwait(2);
									} else if ((isAttribtuePresent(gridelement, "data-type"))
											&& (gridelement.getAttribute("data-type").contains("autocomplete"))) {
										if (!data.equalsIgnoreCase("")) {
											selectAndDeleteTextViaKeyboard();
											webElementWait(getWebElement("autcompletedd"));
										}
										action.sendKeys(data).build().perform();
										webElementWait(getWebElement("autcompletedd"));
										//action.sendKeys(Keys.TAB).perform();
										//driverwait(2);
									} else {
										if (!data.equalsIgnoreCase("")) {
											selectAndDeleteTextViaKeyboard();
											driverwait(2);

										}
										action.sendKeys(data).build().perform();

									}

								}

								else if (excval.equalsIgnoreCase("GET")) {

									// numeric field validation
									if (gridelement.getTagName().equalsIgnoreCase("input")
											&& gridelement.getAttribute("style").contains("text-align: right;")) {
										String getval1 = gridelement.getAttribute("value").replaceAll(",", "");
										if (getval1.contains(".0")) {
											int indea = getval1.indexOf(".");
											String extstr = getval1.substring(0, indea);
											System.out.println("GET VALUE " + extstr);
											Assert.assertEquals(extstr, data, "Values cannot be matched");
										}

										else if (!getval1.contains(".")) {
											Assert.assertEquals(getval1, data, "Values cannot be matched");
										}

										else {
											int indea = getval1.indexOf(".");
											String extstr = getval1.substring(0, indea + 3);
											System.out.println("GET VALUE " + extstr);
											Assert.assertEquals(extstr, data, "Values cannot be matched");
										}

									}
									// input field validation
									else if (gridelement.getTagName().equalsIgnoreCase("input")) {
										String getval1 = gridelement.getAttribute("value");
										Assert.assertEquals(getval1, data, "value cannot be matched");
									}

									// Auto-complete field validation
									else if (isAttribtuePresent(gridelement, "data-type")
											&& gridelement.getAttribute("data-type").contains("autocomplete")) {
										String getval2 = gridelement.getAttribute("value");
										System.out.println("GET VALUE " + getval2);
										Assert.assertEquals(getval2, data, "Value cannot be matched");
									}

								} else {
									System.out.println("Value not required or value is getting changed dynamically");
								}

								try {
									if (columnNum != columnCount - 1) {
										String nxtgridfieldvalue = xls_reader.getCellData(dcSheetname, columnNum + 1,
												2);
										String[] nxtcolheader = nxtgridfieldvalue.split("1");
										String nxtgridfieldlocator = "//*[@id='" + nxtcolheader[0] + gridrownum
												+ nxtcolheader[1] + "']";
										WebElement nxtgridelement = driver.findElement(By.xpath(nxtgridfieldlocator));
										retryingFindClick(nxtgridelement);
										driverwait(2);
										if (isAlertPresent()) {
											dismissAlertBox();
										}
									}
								} catch (Exception e) {

								}

								if (!nexttransval.equalsIgnoreCase(data0) && (columnNum == columnCount - 1)) {
									break;
								}
								if (columnNum == columnCount - 1 && fillgrid.equalsIgnoreCase("FALSE")) {
									action.moveToElement(getWebElement("addgriddd")).perform();
									driverwait(2);
									getWebElement("gridaddrow").click();
									driverwait(2);
								}
								/*
								 * gridtable.sendKeys(Keys.TAB); driverwait(2);
								 */
							}
						}
						gridrownum++;

					} else {
						break excelloop;
					}

				}
				break excelloop;
			}
		}

	}


	public void entervalueinglobalsearch(String PageName) throws Exception {

		if (isElementPresent("pagedimmer") == true) {
			new WebDriverWait(driver, 100).until(ExpectedConditions.invisibilityOfElementLocated(dimmer_xpath));
		}
		driver.switchTo().defaultContent();
		if (isAlertPresent()) {
			dismissAlertBox();
		}

		getWebElement("gsearchbutton").click();
		WebElement globalserchkeyword = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.visibilityOf(getWebElement("searchfilter")));
		globalserchkeyword.click();
		getWebElement("searchstartwith").click();
		driverwait(2);
		getWebElement("globalsearch").clear();
		getWebElement("globalsearch").sendKeys(PageName);
		webElementWait(getWebElement("globalsearch_dropdown"));
		getWebElement("globalsearch").sendKeys(Keys.ENTER);
		if (isAlertPresent()) {
			AcceptAlertBox();
		}
		if (isElementPresent("pagedimmer") == true) {
			new WebDriverWait(driver, 100).until(ExpectedConditions.invisibilityOfElementLocated(dimmer_xpath));
		}
		driver.switchTo().frame(getWebElement("axpmiddleframe"));
		webElementWait(getWebElement("PageHeader"));

	}

	public void addItemDetails_MultiSelectFillgrid() throws Exception {

		try {
			driverwait(2);
			action = new Actions(driver);
			action.moveToElement(getWebElement("addgriddd")).perform();
			getWebElement("fillitems_fillgrid").click();
			if (isElementPresent("fillgridpopup")) {
				getWebElement("fillgridpopup").click();
			}
			if (isElementPresent("fillgrid_nodata")) {
				getWebElement("fillgrid_OKbtn").click();
				Reporter.log("No Record Found In Fillgrid");
			} else {
				WebElement fillgridcheckall = new WebDriverWait(driver, 100)
						.until(ExpectedConditions.visibilityOf(getWebElement("fillgrid_checkall")));
				fillgridcheckall.click();
				getWebElement("fillgrid_OKbtn").click();
				driverwait(2);
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public WebElement webElementWait(WebElement visibleElement) {
		try {
			WebElement globalsearch_dd = new WebDriverWait(driver, 60)
					.until(ExpectedConditions.visibilityOf(visibleElement));
			return globalsearch_dd;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String summaryvalidation(String actualelementstr) {
		try {
			String totlval = getWebElement(actualelementstr).getAttribute("value").replaceAll(",", "");

			if (totlval.equalsIgnoreCase("")) {
				return totlval;
			} else if (totlval.contains(".0")) {
				int indexval = totlval.indexOf(".");
				String totlvalue = totlval.substring(0, indexval);
				return totlvalue;
			} else {
				int indexval = totlval.indexOf(".");
				String totlvalue = totlval.substring(0, indexval + 3);
				return totlvalue;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void OracleConnection(String query) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbUrl = "jdbc:oracle:thin:@103.120.178.47:1521:oracledb11g";
		String schemaname = "axpertdemo";
		String password = "log";
		con = DriverManager.getConnection(dbUrl, schemaname, password);
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(query);
	}

	// ***********************************************************************************

	// **********************************************************************************

	public String randomnumber() {
		int random = (int) (Math.random() * 500 + 1);
		String randomstringnum = Integer.toString(random);
		return randomstringnum;
	}

	public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " TESTCASE PASS");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " TEST IS SKIP, BECAUSE OF:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + " TESTCASE FAIL" + result.getThrowable());
			String screen = getScreenShot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} /*
			 * else if (result.getStatus() == ITestResult.STARTED) {
			 * test.log(LogStatus.INFO, result.getName() + " TEST IS STARTED");
			 * }
			 */
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		// test.log(LogStatus.INFO, result.getName() + " Test STARTED");
	}

	@AfterSuite(alwaysRun = true)
	public void endTest() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
	}

}
