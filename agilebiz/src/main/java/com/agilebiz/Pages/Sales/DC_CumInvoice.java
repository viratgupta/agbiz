/**
 * 
 */
package com.agilebiz.Pages.Sales;

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
public class DC_CumInvoice extends TestBase {
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	public String VerifyDC_CumInvoicePage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("DC Cum Invoice")) {
				throw new SkipException("DC Cum Invoice Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DCI_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch, Keys.ENTER, Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToStockLocTextBox(String stockloc) {
		try {
			WebElement stocklocTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DCI_stocklocation")));
			stocklocTextBoxWebEelement.clear();
			stocklocTextBoxWebEelement.sendKeys(stockloc, Keys.ENTER, Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectInvoicedate() {
		try {
			WebElement InvoicedateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DCI_invoicedate")));
			InvoicedateWebEelement.sendKeys(Keys.ENTER);
		driverwait(2);

		} catch (Exception ex) {	

		}
		return true;
	}
	
	public boolean addValueToCustomerTextBox(String customer) {
		try {
			WebElement custTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DCI_customer")));
			custTextBoxWebEelement.clear();
			custTextBoxWebEelement.sendKeys(customer,Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToSOnoTextBox(String sono) {
		try {
			WebElement sonoTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DCI_sono")));
			sonoTextBoxWebEelement.clear();
			sonoTextBoxWebEelement.sendKeys(sono);
			sonoTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean enterduedate(String duedate) {
		try {
			WebElement duedateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DCI_duedate")));
			duedateWebEelement.clear();
			duedateWebEelement.sendKeys(duedate);
			duedateWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	

	//*********************************** Item Details ******************************************
	
	public boolean itemDetailsfromFillgrid() throws Exception
	{
		
		try {
			driverwait(2);
			getWebElement("SDC_fillitembtn").click();
			if(isElementPresent("SDC_fillgridnodata"))
			{
				driverwait(2);
				getWebElement("SDC_fillgridOKbtn").click();
				Reporter.log("No Record Found In Fillgrid");
				return false;
			}
			else{
				WebElement fillgridcheckall = new WebDriverWait(driver, 100)
						.until(ExpectedConditions.visibilityOf(getWebElement("SDC_fillgridchkall")));
				fillgridcheckall.click();
				getWebElement("SDC_fillgridOKbtn").click();
				driverwait(2);
				return true;
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			return false;
		} 
	}
	
	public boolean validateandsetFillgridItem(Xls_Reader xls_reader1, String sheetname, String transno, String tableid){

		try {
			GetandSetFillgridData(xls_reader1, sheetname, transno, tableid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	// ************************************** SAVE_FORM*******************************************

		public boolean saveTstruct() {
			try {

				WebElement saveWebEelement = new WebDriverWait(driver, 100)
						.until(ExpectedConditions.visibilityOf(getWebElement("save")));
				saveWebEelement.click();
				driverwait(2);

				return true;
			} catch (Exception ex) {
				return false;
			}
		}

		public boolean getsavedMessage(String successMessage,String transno) {
			try {
				WebElement SaveWebEelement = new WebDriverWait(driver, 10)
						.until(ExpectedConditions.visibilityOfElementLocated(savemessage_xpath));

				String actualMsgFromApp = SaveWebEelement.getText();
				if (actualMsgFromApp.contains(successMessage)) {
					// Sales Delivery Challan Saved (SO No.-SOHSR19000456)
					int i1 = actualMsgFromApp.indexOf("-");
					int i2 = actualMsgFromApp.indexOf(")");
					String SDCnumber = actualMsgFromApp.substring(i1 + 1, i2);
					// write data in excel
					String transvalue = transno.substring(5);
					int transval = Integer.parseInt(transvalue);
					//xls_reader.setCellData("SalesInvoice", "challanNo", transval + 1, SDCnumber);
					driverwait(4);
					return true;
				}

			} catch (Exception ex) {

			}
			return false;

		}


}
