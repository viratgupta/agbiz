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
public class DirectSalesReturn extends TestBase {
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader
			(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	public String VerifySalesReturnPage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Direct Sales Return")) {
				throw new SkipException("Direct Sales Return Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SR_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch, Keys.ENTER, Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectReturndate() {
		try {
			WebElement returndateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SR_returndate")));
			returndateWebEelement.sendKeys(Keys.ENTER);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToCustomerTextBox(String customer) {
		try {
			WebElement custTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SR_customer")));
			custTextBoxWebEelement.clear();
			custTextBoxWebEelement.sendKeys(customer, Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToinvoicenoTextBox(String invoiceno) {
		try {
			WebElement invnoTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SR_invoiceno")));
			invnoTextBoxWebEelement.clear();
			invnoTextBoxWebEelement.sendKeys(invoiceno);
			driverwait(2);
			invnoTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	//*********************************** Item Details ******************************************
	
	public boolean itemDetailsfromFillgrid() throws Exception {

		try {
			addItemDetails_MultiSelectFillgrid();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean validateandsetFillgridItem(Xls_Reader xls_reader1, String sheetname, String transno,
			String tableid) {

		try {
			addrecordingrid(xls_reader1, sheetname, transno, tableid, "TRUE");
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
					String Salesreturn = actualMsgFromApp.substring(i1 + 1, i2);
					// write data in excel
					String transvalue = transno.substring(5);
					int transval = Integer.parseInt(transvalue);
					//xls_reader.setCellData("SalesReturn", "invoiceno", transval + 1, Salesreturn);
					driverwait(4);
					return true;
				}

				}catch (Exception ex) {

			}
			return false;

		}



}
