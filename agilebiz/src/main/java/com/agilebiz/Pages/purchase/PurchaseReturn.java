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
public class PurchaseReturn extends TestBase {
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader
			(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	public String VerifyPurchaseReturnPage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Purchase Return")) {
				throw new SkipException("Purchase Return Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PRET_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch);
			webElementWait(getWebElement("autcompletedd"));
			branchTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectPurchaseReturndate() {
		try {
			WebElement returndateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PRET_prdate")));
			returndateWebEelement.sendKeys(Keys.ENTER);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToSupplierTextBox(String supplier) {
		try {
			WebElement suplTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PRET_supplier")));
			suplTextBoxWebEelement.clear();
			suplTextBoxWebEelement.sendKeys(supplier);
			webElementWait(getWebElement("autcompletedd"));
			suplTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTopurchasebillTextBox(String pbillno) {
		try {
			WebElement pbillnoTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PRET_pbill")));
			pbillnoTextBoxWebEelement.clear();
			pbillnoTextBoxWebEelement.sendKeys(pbillno);
			driverwait(2);
			pbillnoTextBoxWebEelement.sendKeys(Keys.TAB);
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
