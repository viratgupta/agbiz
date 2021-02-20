package com.agilebiz.Pages.LoginToApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Utilities.GenericMethods;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;
public class TestingMethod extends TestBase {
	
	static Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	/*public static void main(String[] args) {

		GenericMethods gm= new GenericMethods();
		
		System.out.println("4567"+gm.randomnumber());
		*/
	
	public static void main(String arg[]){
		TestUtil.getData(xls_reader, "testsheet");
		xls_reader.setCellData("testsheet", "test2", 2, "value");
		TestUtil.getData(xls_reader, "testsheet");
	}
	
	
	getWebElement("fillgridpopup").click();
	public void addItemDetails_MultiSelectFillgrid() throws Exception {

		try {
			driverwait(2);
			Actions action= new Actions(driver);
			action.moveToElement(getWebElement("addgriddd")).perform();
			getWebElement("fillitems_fillgrid").click();
			if (!isElementPresent("fillgridpopup")) {
				if (!isElementPresent("fillgrid_nodata")) {
					WebElement fillgridcheckall = new WebDriverWait(driver, 100)
							.until(ExpectedConditions.visibilityOf(getWebElement("fillgrid_checkall")));
					fillgridcheckall.click();
					getWebElement("fillgrid_OKbtn").click();
					driverwait(2);
				} else {
					getWebElement("fillgrid_OKbtn").click();
					Reporter.log("No Record Found In Fillgrid");	
				}
			}
			
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	public void addrecordingrid(Xls_Reader xls_reader, String dcSheetname, String transno, String gridtableid) throws Exception {

		implicitWait(10);
		int rowCount = xls_reader.getRowCount(dcSheetname);
		int columnCount = xls_reader.getColumnCount(dcSheetname);
		excelloop: for (int exrowval = 4; exrowval <= rowCount;) {

			String exrow1 = xls_reader.getCellData(dcSheetname, 0, exrowval);

			if (!exrow1.equalsIgnoreCase(transno)) {

				exrowval++;

			} else {
				/*
				 * getWebElement(gridaddbtnele).click(); driverwait(2);
				 */

				// need to read the data starting from 1 row all columns
				int gridrownum = 1;
				for (int rowNum = exrowval; rowNum <= rowCount; rowNum++) {

					String data0 = xls_reader.getCellData(dcSheetname, 0, rowNum);
					String nexttransval = xls_reader.getCellData(dcSheetname, 0, rowNum + 1);
					if (data0.equalsIgnoreCase(transno)) { 

						String gridedit = "//table[@id='gridHd2']/tbody/tr[" + (gridrownum) + "]/td[1]/button[1]";
						driver.findElement(By.xpath(gridedit)).click();

						for (int columnNum = 1; columnNum < columnCount; columnNum++) {
							
							//prno001F2
							String gridfieldvalue = xls_reader.getCellData(dcSheetname, columnNum, 2);
							String[] colheader=gridfieldvalue.split("1");
							
							String excval = xls_reader.getCellData(dcSheetname, columnNum, 3);
							if (columnNum != columnCount) {
								
								Actions action = new Actions(driver);
								
								String data = xls_reader.getCellData(dcSheetname, columnNum, rowNum);
								// xpath for grid row column::::
								// //table[@id='gridHd2']/tbody/tr[1]/td[@data-focus][2]/div/input
								System.out.println(data);

								// table[@id='gridHd3']/tbody/tr[1]/td[@class][20]/div
								
								String gridfieldlocator= "//*[@id='"+colheader[0]+gridrownum+colheader[1]+"']";
								WebElement gridelement = driver.findElement(By.xpath(gridfieldlocator));
								webElementWait(gridelement);

								if (excval.equalsIgnoreCase("SET")) {
									
									if ((gridelement.getTagName()).equalsIgnoreCase("select")) {
										selectByText(gridelement, data);
										driverwait(2);
									} else if ((isAttribtuePresent(gridelement, "data-type"))&& (gridelement.getAttribute("data-type").contains("autocomplete"))) {
										if (!data.equalsIgnoreCase("")) {
											selectAndDeleteTextViaKeyboard();
											webElementWait(getWebElement("autcompletedd"));
										}
										action.sendKeys(data).build().perform();
										webElementWait(getWebElement("autcompletedd"));
										// action.sendKeys(Keys.TAB).perform();
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
									
									//numeric field validation
									if (gridelement.getTagName().equalsIgnoreCase("input")&& gridelement.getAttribute("style").contains("text-align: right;")) {
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
									//input field validation
									else if (gridelement.getTagName().equalsIgnoreCase("input")) {
										String getval1 = gridelement.getAttribute("value");
										Assert.assertEquals(getval1, data, "value cannot be matched");
									}
									
									//Auto-complete field validation
									else if (isAttribtuePresent(gridelement, "data-type")&& gridelement.getAttribute("data-type").contains("autocomplete")) {
										String getval2 = gridelement.getAttribute("value");
										System.out.println("GET VALUE " + getval2);
										Assert.assertEquals(getval2, data, "Value cannot be matched");
									}

								} else {
									System.out.println("value is getting changed dynamically like stock value");
								}

								try {
									
									String nxtgridfieldvalue = xls_reader.getCellData(dcSheetname, columnNum+1, 2);
									String[] nxtcolheader=nxtgridfieldvalue.split("1");
									String nxtgridfieldlocator= "//*[@id='"+nxtcolheader[0]+gridrownum+nxtcolheader[1]+"']";
									if (columnNum != columnCount - 1) {
										WebElement nxtgridelement = driver.findElement(By.xpath(nxtgridfieldlocator));
										retryingFindClick(nxtgridelement);
										driverwait(2);
										if(isAlertPresent()){
											dismissAlertBox();
										}
									}
								} catch (Exception e) {

									/*columnNum++;
									String nextelempath = "//table[@id='" + gridtableid + "']/tbody/tr[" + (gridrownum)
											+ "]/td[@class!='none' or @class!=''][" + (columnNum + 1) + "]/div";
									if (columnNum != columnCount - 1) {
										WebElement nextelement = driver.findElement(By.xpath(nextelempath));
										// webElementWait(nextelement);
										retryingFindClick(nextelement);
										// nextelement.click();
										driverwait(2);
										continue;

									}*/
								}

								if (!nexttransval.equalsIgnoreCase(data0) && (columnNum == columnCount - 1)) {
									break;
								}
								if (columnNum == columnCount - 1) {

									getWebElement("addgriddd").click();
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
	/*@Test
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		loginPage.loginToAxpertX_userbased("appdetails", "Email_id", "password");
		//Assert.assertEquals(loginPage.getSuccessfulMessage(), "Welcome");
		driverwait(3);
		getWebElement("globalsearch").sendKeys("gridtest");
		getWebElement("DP_pagefrommenu").click();
		driver.switchTo().frame(getWebElement("DP_middleframe"));
	}
	
	//****************************** GENERIC FIELDS METHODS **************************************************
	public String VerfyPage(String PageName) throws Exception {
		try {
			driverwait(2);
			driver.switchTo().defaultContent();
			globalserchKeyword();
			getWebElement("globalsearch").clear();
			getWebElement("globalsearch").sendKeys(PageName);
			webElementWait(getWebElement("globalsearch_dropdown"));
			getWebElement("globalsearch").sendKeys(Keys.ENTER);
			//getWebElement("DP_pagefrommenu").click();
			driverwait(2);
			
			if(isAlertPresent())
			{
				AcceptAlertBox();
			}
			
			driver.switchTo().frame(getWebElement("middleframe"));
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals(PageName)) {
				throw new SkipException(PageName+" Page couldn't open.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return getWebElement("PageHeader").getText();
	}
	
	public boolean autoCompleteField(String elementstring, String entertext) {
		try {
			WebElement TextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement(elementstring)));
			TextBoxWebEelement.clear();
			TextBoxWebEelement.sendKeys(entertext);
			webElementWait(getWebElement("autcompletedd"));
			TextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
		return true;
	}

	public boolean input_numeric_dateField(String elementString, String entertext) {
		try {
			WebElement TextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement(elementString)));
			TextBoxWebEelement.clear();
			TextBoxWebEelement.sendKeys(entertext);
			TextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
		return true;
	}
	
	public boolean enterCurrentDate(String elementString) {
		try {
			WebElement dateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement(elementString)));
			dateWebEelement.sendKeys(Keys.ENTER);
		driverwait(2);

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
		return true;
	}
	
	//*****************************************************************************************************************************8
	
	
	public void GetandSetFillgridData(Xls_Reader xls_reader, String dcSheetname, String transno, String gridtableid)
			throws Exception {

		implicitWait(10);
		int rowCount = xls_reader.getRowCount(dcSheetname);
		int columnCount = xls_reader.getColumnCount(dcSheetname);
		excelloop: for (int exrowval = 3; exrowval <= rowCount;) {

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

						String gridedit = "//table[@id='gridHd2']/tbody/tr[" + (rowNum - 2) + "]/td[1]/button[1]";
						driver.findElement(By.xpath(gridedit)).click();

						for (int columnNum = 1; columnNum < columnCount; columnNum++) {

							String excval = xls_reader.getCellData(dcSheetname, columnNum, 2);

							if (columnNum != columnCount) {
								String data = xls_reader.getCellData(dcSheetname, columnNum, rowNum);
								// xpath for grid row column::::
								// //table[@id='gridHd2']/tbody/tr[1]/td[@data-focus][2]/div/input
								System.out.println(data);

								String elempath = "//table[@id='" + gridtableid + "']/tbody/tr[" + (gridrownum)
										+ "]/td[@class!='none'][" + (columnNum) + "]/div";
								WebElement gridtable = driver.findElement(By.xpath(elempath));
								webElementWait(gridtable);

								if (excval.equalsIgnoreCase("SET")) {
									Actions action = new Actions(driver);

									if (gridtable.getAttribute("data-type").equalsIgnoreCase("select")) {
										WebElement selele = driver.findElement(By.xpath(elempath + "/select"));
										selectByText(selele, data);
										selele.sendKeys(Keys.TAB);
										driverwait(2);
									} else if (gridtable.getAttribute("data-type").contains("autocomplete")) {
										if (!data.equalsIgnoreCase("")) {
											action.sendKeys(Keys.chord(Keys.CONTROL, "a"));
											action.sendKeys(Keys.chord(Keys.CONTROL, Keys.BACK_SPACE)).build()
													.perform();
											driverwait(2);
										}
										action.sendKeys(data).build().perform();
										webElementWait(getWebElement("autcompletedd"));
										action.sendKeys(Keys.TAB).perform();
										driverwait(2);
									} else {
										if (!data.equalsIgnoreCase("")) {
											action.sendKeys(Keys.chord(Keys.CONTROL, "a"));
											action.sendKeys(Keys.chord(Keys.CONTROL, Keys.BACK_SPACE)).build()
													.perform();
										}
										action.sendKeys(data).build().perform();
										String nextelempath = "//table[@id='" + gridtableid + "']/tbody/tr["
												+ (gridrownum) + "]/td[@class!='none'][" + (columnNum + 1) + "]/div";
										if (columnNum != columnCount - 1) {
											WebElement nextelement = driver.findElement(By.xpath(nextelempath));
											webElementWait(nextelement);
											nextelement.click();
											driverwait(2);
										}

									}

								}

								else if (excval.equalsIgnoreCase("GET")) {
									String getxpathauto = elempath + "/div/input";
									String getxpathothers = elempath + "/input";

									if (gridtable.getAttribute("data-type").equalsIgnoreCase("input")) {
										String getval1 = driver.findElement(By.xpath(getxpathothers))
												.getAttribute("value");
										// System.out.println(getval1 + " equals
										// " + data);
										Assert.assertEquals(getval1, data, "value cannot be matched");
									}

									else if (gridtable.getAttribute("data-type").equalsIgnoreCase("numeric")) {
										String getval1 = driver.findElement(By.xpath(getxpathothers))
												.getAttribute("value").replaceAll(",", "");
										if (getval1.contains(".0")) {
											int indea = getval1.indexOf(".");
											String extstr = getval1.substring(0, indea);
											System.out.println("GET VALUE " + extstr);
											Assert.assertEquals(extstr, data, "Values cannot be matched");
										}

										else {
											int indea = getval1.indexOf(".");
											String extstr = getval1.substring(0, indea + 2);
											System.out.println("GET VALUE " + extstr);
											Assert.assertEquals(extstr, data, "Values cannot be matched");
										}

									} else if (gridtable.getAttribute("data-type").contains("autocomplete")) {
										String getval2 = driver.findElement(By.xpath(getxpathauto))
												.getAttribute("value");
										System.out.println("GET VALUE " + getval2);
										
										 * if (getval2.equalsIgnoreCase(data)) {
										 * System.out.println(
										 * "testcases is PASS " + getval2 +
										 * " equals " + data); }
										 
										Assert.assertEquals(getval2, data, "Value cannot be matched");
									}

								}
								String nextelempath = "//table[@id='" + gridtableid + "']/tbody/tr[" + (gridrownum)
										+ "]/td[@class!='none'][" + (columnNum + 1) + "]/div";

								if (columnNum != columnCount - 1) {
									WebElement nextelement = driver.findElement(By.xpath(nextelempath));
									webElementWait(nextelement);
									retryingFindClick(nextelement);
									// nextelement.click();
									driverwait(2);
								}

								if (!nexttransval.equalsIgnoreCase(data0) && (columnNum == columnCount - 1)) {
									break;
								}
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
	
	
	
	
	
			
	@DataProvider(name = "testdata")
	public Object[][] getData() {
		return TestUtil.getData(xls_reader, "sheet3");
	}
	
*/
	}
