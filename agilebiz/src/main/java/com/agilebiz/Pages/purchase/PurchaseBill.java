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

import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.Xls_Reader;

/**
 * @author virat
 *
 */
public class PurchaseBill extends TestBase {

	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	public String VerifyPurchaseBillPage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue =getWebElement("PageHeader").getText();
			if (!headerValue.equals("Purchase Bill")) {
				throw new SkipException("Purchase Bill Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PB_header").getText();
	}

	// ************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PB_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch);
			webElementWait(getWebElement("autcompletedd"));
			branchTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueTobilldate() {
		try {
			WebElement billdateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PB_billdate")));
			billdateTextBoxWebEelement.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueTosupplierTextBox(String supplier) {
		try {
			WebElement supplierTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PB_supplier")));
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
					.until(ExpectedConditions.visibilityOf(getWebElement("PB_POnumber")));
			POnoWebEelement.clear();
			POnoWebEelement.sendKeys(POid);
			webElementWait(getWebElement("autcompletedd"));
			POnoWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean selectGRNNumber(String GRNno) {
		try {
			WebElement GRNnoWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PB_GRNnumber")));
			GRNnoWebEelement.clear();
			GRNnoWebEelement.sendKeys(GRNno);
			webElementWait(getWebElement("autcompletedd"));
			GRNnoWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addvaluetoInvoiceno(String invoiceno) {
		try {
			WebElement invoicenoelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PB_invoiceno")));
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
					.until(ExpectedConditions.visibilityOf(getWebElement("PB_invoicedate")));
			invoicedateelement.sendKeys(Keys.ENTER);
			invoicedateelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	// ***************************************************** ITEM DETAILS
	// ************************************************************

	public boolean grnDetailsfromFillgrid() throws Exception {

		try {
			driverwait(2);
			getWebElement("GRN_fillitem").click();
			if (isElementPresent("GRN_fillgridnodata")) {
				getWebElement("GRN_fillgridOK").click();
				Reporter.log("No Record Found In Fillgrid");
				return false;
			} else {
				WebElement fillgridcheckall = new WebDriverWait(driver, 100)
						.until(ExpectedConditions.visibilityOf(getWebElement("GRN_fllcheckall")));
				fillgridcheckall.click();
				getWebElement("GRN_fillgridOK").click();
				driverwait(2);
				return true;
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
			return false;
		}
	}

	public boolean SetFOBvalue(String fobvalue) {
		try {
			getWebElement("PB_FOBvalue").clear();
			getWebElement("PB_FOBvalue").sendKeys(fobvalue, Keys.TAB);
			driverwait(2);

			return true;
		} catch (Exception ex) {
			return false;

		}

	}

	public boolean addinvoicegross(String grossvalue) {
		try {
			getWebElement("PB_invoicegross").clear();
			getWebElement("PB_invoicegross").sendKeys(grossvalue, Keys.TAB);
			driverwait(2);

			return true;
		} catch (Exception ex) {
			return false;

		}

	}

	public boolean setinvoicevalue(String totalinvoiceval) {
		try {
			getWebElement("PB_invoicevalue").clear();
			getWebElement("PB_invoicevalue").sendKeys(totalinvoiceval, Keys.TAB);
			driverwait(2);

			return true;
		} catch (Exception ex) {
			return false;

		}

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
				String preturnnumber = actualMsgFromApp.substring(i1+1, i2);
				// write data in excel
				String transvalue = transno.substring(5); 
				int transval = Integer.parseInt(transvalue);
				xls_reader.setCellData("PurchaseReturn", "purchaseBill",transval+1, preturnnumber);
				driverwait(4);
			}

		} catch (Exception ex) {
			return false;

		}
		return true;

	}

}
