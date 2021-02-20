/**
 * 
 */
package com.agilebiz.Pages.Manufacturing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;

import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.Xls_Reader;

/**
 * @author virat
 *
 */
public class ProductionMaterialReturn extends TestBase {
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");

	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	public String VerifyProductionMaterialReturnPage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Production Material Return")) {
				throw new SkipException("Production Material Return Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	// ************************************Details****************************************

	public boolean enterproductionReturnDate() {
		try {
			WebElement prodreturndateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PMR_returndate")));
			prodreturndateWebEelement.sendKeys(Keys.ENTER);
			prodreturndateWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToProdOrderNoTextBox(String Prodno) {
		try {
			WebElement prodorderTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PMR_prodorderno")));
			prodorderTextBoxWebEelement.clear();
			prodorderTextBoxWebEelement.sendKeys(Prodno);
			webElementWait(getWebElement("autcompletedd"));
			prodorderTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}	

	// ***************************************************** ITEM DETAILS ************************************************************
	
	public boolean itemDetailsGrid(Xls_Reader xls_reader, String dcSheetname, String transno, String gridtableid) throws Exception {
		try {
			driverwait(2);
			addrecordingrid(xls_reader, dcSheetname, transno, gridtableid,"TRUE");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// ******************************************************SAVE_FORM********************************************************************

	public boolean saveTstruct() {
		try {

			WebElement saveWebEelement = new WebDriverWait(driver, 100)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_savebtn")));
			saveWebEelement.click();
			driverwait(2);

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean getsavedMessage(String successMessage, String transno) {
		try {
			WebElement SaveWebEelement = new WebDriverWait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated(savemessage_xpath));
			String actualMsgFromApp = SaveWebEelement.getText();
			if (actualMsgFromApp.contains(successMessage)) {
				int i1= actualMsgFromApp.indexOf("-");
				int i2= actualMsgFromApp.indexOf(")");
				String prodmatreturn = actualMsgFromApp.substring(i1+1, i2);
				// write data in excel
				String transvalue = transno.substring(5); 
				int transval = Integer.parseInt(transvalue);
				//xls_reader.setCellData("ProdMaterialIssue", "productionOrderNumber",transval+1, prodorder);
				driverwait(4);
			}

		} catch (Exception ex) {
			return false;

		}
		return true;

	}



}
