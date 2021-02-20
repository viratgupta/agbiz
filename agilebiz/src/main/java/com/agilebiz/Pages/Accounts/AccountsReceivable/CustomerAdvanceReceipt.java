/**
 * 
 */
package com.agilebiz.Pages.Accounts.AccountsReceivable;

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
public class CustomerAdvanceReceipt{
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	public String VerifyBankReceiptAgainstInvoicePage(String PageName) throws Exception {
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
			if (!headerValue.equals("Bank Receipt Against Invoice")) {
				throw new SkipException("Bank Receipt Against Invoice Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch, Keys.ENTER, Keys.TAB);
			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectdocdate() {
		try {
			WebElement docdateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_docdate")));
			docdateWebEelement.sendKeys(Keys.ENTER);
		driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueTocustomerTextBox(String customer) {
		try {
			WebElement custTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_customer")));
			custTextBoxWebEelement.clear();
			custTextBoxWebEelement.sendKeys(customer);
			webElementWait(getWebElement("autcompletedd"));
			custTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTobankaccTextBox(String bankacc) {
		try {
			WebElement bankaccTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_bankac")));
			bankaccTextBoxWebEelement.clear();
			bankaccTextBoxWebEelement.sendKeys(bankacc);
			webElementWait(getWebElement("autcompletedd"));
			bankaccTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToReceiptAmtTextBox(String receiptamt) {
		try {
			WebElement recTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_receiptamt")));
			recTextBoxWebEelement.clear();
			recTextBoxWebEelement.sendKeys(receiptamt);
			recTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToTDSTextBox(String tdsvalue) {
		try {
			WebElement tdsTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_tdsamt")));
			tdsTextBoxWebEelement.clear();
			tdsTextBoxWebEelement.sendKeys(tdsvalue);
			tdsTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToModeofReceiptTextBox(String modeReceipt) {
		try {
			WebElement modeifrecpTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_modeofreq")));
			modeifrecpTextBoxWebEelement.clear();
			modeifrecpTextBoxWebEelement.sendKeys(modeReceipt);
			webElementWait(getWebElement("autcompletedd"));
			modeifrecpTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToRefNoTextBox(String refno) {
		try {
			WebElement refnoTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_refno")));
			refnoTextBoxWebEelement.clear();
			refnoTextBoxWebEelement.sendKeys(refno);
			refnoTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTochqDDbankTextBox(String chqddbank) {
		try {
			WebElement chqddbnkTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_chqddbank")));
			chqddbnkTextBoxWebEelement.clear();
			chqddbnkTextBoxWebEelement.sendKeys(chqddbank);
			chqddbnkTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTochqDDnoTextBox(String chqddno) {
		try {
			WebElement chqddnoTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_chqddno")));
			chqddnoTextBoxWebEelement.clear();
			chqddnoTextBoxWebEelement.sendKeys(chqddno);
			chqddnoTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectchqdd_date(String chqdate) {
		try {
			WebElement chqdd_dateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_chqdddate")));
			chqdd_dateWebEelement.clear();
			chqdd_dateWebEelement.sendKeys(chqdate);
			chqdd_dateWebEelement.sendKeys(Keys.TAB);
		driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTobankchargesacTextBox(String bankchargesac) {
		try {
			WebElement bankchacTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_bankchargeac")));
			bankchacTextBoxWebEelement.clear();
			bankchacTextBoxWebEelement.sendKeys(bankchargesac);
			webElementWait(getWebElement("autcompletedd"));
			bankchacTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTobankchTextBox(String bankch) {
		try {
			WebElement bankchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_BRAI_bankcharges")));
			bankchTextBoxWebEelement.clear();
			bankchTextBoxWebEelement.sendKeys(bankch);
			bankchTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	//*********************************** Item Details ******************************************
	
	public boolean itemDetailsfromFillgrid(String billno) throws Exception
	{
		
		try {
			driverwait(2);
			getWebElement("AR_BRAI_fillgridbtn").click();
			if(isElementPresent("FG_nodata"))
			{
				driverwait(2);
				getWebElement("FG_OKbtn").click();
				Reporter.log("No Record Found In Fillgrid");
				return false;
			}
			else{
				WebElement fillgridcheckall = new WebDriverWait(driver, 100)
						.until(ExpectedConditions.visibilityOf(getWebElement("FG_search")));
				fillgridcheckall.clear();
				fillgridcheckall.sendKeys(billno);
				driverwait(2);
				getWebElement("FG_chechkall").click();
				getWebElement("FG_OKbtn").click();
				driverwait(2);
				return true;
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			return false;
		} 
	}
	
	public boolean itemDetailsGrid(Xls_Reader xls_reader, String dcSheetname, String transno, String gridaddbtnele,
			String gridtableid) throws Exception {
		try {
			driverwait(2);
			addrecordingrid(xls_reader, dcSheetname, transno, gridaddbtnele, gridtableid);
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
				
				//String SO_number= actualMsgFromApp.substring();
				driverwait(3);
				// write data in excel 
				String transvalue= transno.substring(5);
				int transval= Integer.parseInt(transvalue);
				//xls_reader.setCellData("GRNDetails", "POnumber", transval+1, POnumber);
				//xls_reader.setCellData("PurchaseBillDetails", "POnumber", transval+1, POnumber);
				
				if (actualMsgFromApp.contains(successMessage)) {
					return true;
				}

			} catch (Exception ex) {

			}
			return false;

		}
		

	


}
