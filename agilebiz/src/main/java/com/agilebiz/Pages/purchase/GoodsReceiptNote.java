/**
 * 
 */
package com.agilebiz.Pages.purchase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;

import com.agilebiz.Utilities.ExcelSetValue;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.Xls_Reader;

/**
 * @author virat
 *
 */
public class GoodsReceiptNote extends TestBase {

	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");

	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
	

	public String VerifyGoodsReceiptNotePage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Goods Receipt Note")) {
				throw new SkipException("Goods Receipt Note Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("GRN_header").getText();
	}

	// ************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("GRN_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch);
			webElementWait(getWebElement("autcompletedd"));
			branchTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueTogrndate() {
		try {
			WebElement grndateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("GRN_date")));
			grndateTextBoxWebEelement.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueTosupplierTextBox(String supplier) {
		try {
			WebElement supplierTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("GRN_supplier")));
			supplierTextBoxWebEelement.clear();
			supplierTextBoxWebEelement.sendKeys(supplier);
			webElementWait(getWebElement("autcompletedd"));
			supplierTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean selectPONumber(String POid) {
		try {
			WebElement POnoWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("GRN_POnumber")));
			POnoWebEelement.clear();
			POnoWebEelement.sendKeys(POid);
			webElementWait(getWebElement("autcompletedd"));
			POnoWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addvaluetoInvoiceno(String invoiceno) {
		try {
			WebElement invoicenoelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("GRN_invoiceno")));
			invoicenoelement.clear();
			invoicenoelement.sendKeys(randomnumber() + invoiceno);
			invoicenoelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addvaluetoInvoicedate() {
		try {
			WebElement invoicedateelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("GRN_invoicedate")));
			/*
			 * invoicedateelement.clear();
			 * invoicedateelement.sendKeys(invoicedate);
			 */
			invoicedateelement.sendKeys(Keys.ENTER);
			invoicedateelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addvaluetoDCdate(String dcdate) {
		try {
			WebElement dcdateelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("GRN_dcdate")));
			dcdateelement.clear();
			dcdateelement.sendKeys(dcdate);
			dcdateelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean selectAgainstDispatchCheckbox(String dispatchvalue) {
		try {
			WebElement dispatchWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("GRN_againstdispatch")));
			if (dispatchvalue.equalsIgnoreCase("T")) {
				dispatchWebEelement.click();
			} else if (dispatchvalue.equalsIgnoreCase("F")) {
				return true;
			}

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addvaluetoFrieght(String frieght) {
		try {
			WebElement fieghtelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("")));
			fieghtelement.clear();
			fieghtelement.sendKeys(frieght);
			fieghtelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}

	// ***************************************************** ITEM DETAILS
	// ************************************************************
	
	public boolean itemDetailsfromFillgrid() throws Exception {

		try {
			addItemDetails_MultiSelectFillgrid();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
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
	
	// **************************************
	// SAVE_FORM*******************************************

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
				//Sales Order Saved (SO No.-SOHSR19000456)
				int i1= actualMsgFromApp.indexOf("-");
				int i2= actualMsgFromApp.indexOf(")");
				String GRNnumber = actualMsgFromApp.substring(i1+1, i2);
				// write data in excel
				String transvalue = transno.substring(5); 
				int transval = Integer.parseInt(transvalue);
				xls_reader.setCellData("PurchaseBillDetails", "GRNnumber", transval + 1, GRNnumber);
				driverwait(4);
				
			}

		} catch (Exception ex) {
			return false;

		}
		return true;

	}

}
