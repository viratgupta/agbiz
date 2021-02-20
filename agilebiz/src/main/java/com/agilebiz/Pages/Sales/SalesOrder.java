/**
 * 
 */
package com.agilebiz.Pages.Sales;

import org.apache.xmlbeans.soap.SOAPArrayType;
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
public class SalesOrder extends TestBase {
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	public String VerifySalesOrderPage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Sales Order")) {
				throw new SkipException("Sales Order Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch, Keys.ENTER, Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectsodate() {
		try {
			WebElement sodateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_date")));
		sodateWebEelement.sendKeys(Keys.ENTER);
		driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean enterduedate(String duedate) {
		try {
			WebElement duedateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_duedate")));
			duedateWebEelement.clear();
			duedateWebEelement.sendKeys(duedate);
			duedateWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueTocustomerTextBox(String customer) {
		try {
			WebElement custTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_customername")));
			custTextBoxWebEelement.clear();
			custTextBoxWebEelement.sendKeys(customer);
			custTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTopaymenttermTextBox(String payemntTerm) {
		try {
			WebElement ptermTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_payment")));
			ptermTextBoxWebEelement.clear();
			ptermTextBoxWebEelement.sendKeys(payemntTerm);
			ptermTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToSalesPersonTextBox(String salesPerson) {
		try {
			WebElement salesperTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_salesperson")));
			salesperTextBoxWebEelement.clear();
			salesperTextBoxWebEelement.sendKeys(salesPerson);
			salesperTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	// expense details 
	
	public boolean addFrieghtValue(String FrieghtValue) {
		try {
			WebElement frieghtTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_frieght")));
			frieghtTextBoxWebEelement.clear();
			driverwait(2);
			frieghtTextBoxWebEelement.sendKeys(FrieghtValue);
			frieghtTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addInsurenceValue(String InsurenceValue) {
		try {
			WebElement insurenceTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_insurance")));
			insurenceTextBoxWebEelement.clear();
			driverwait(2);
			insurenceTextBoxWebEelement.sendKeys(InsurenceValue);
			insurenceTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addOtherchargesValue(String othercharges) {
		try {
			WebElement insurenceTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_othercharges")));
			insurenceTextBoxWebEelement.clear();
			driverwait(2);
			insurenceTextBoxWebEelement.sendKeys(othercharges);
			insurenceTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectITCFrieghtcheckbox(String ITCforFrieght) {
		try {
			WebElement itcfrieghtWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SO_itcheckbox")));
			if (ITCforFrieght.equalsIgnoreCase("T")) {
				itcfrieghtWebEelement.click();
			} else if (ITCforFrieght.equalsIgnoreCase("F") || ITCforFrieght.equalsIgnoreCase("")) {
				return true;
			}

		} catch (Exception ex) {

		}
		return true;
	}
	
	//*********************************** Item Details ******************************************
	
	public boolean itemDetailsGrid(Xls_Reader xls_reader, String dcSheetname, String transno,String gridtableid) throws Exception {
		try {
			driverwait(2);
			addrecordingrid(xls_reader, dcSheetname, transno, gridtableid,"FALSE");
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
					//Sales Order Saved (SO No.-SOHSR19000456)
					int i1= actualMsgFromApp.indexOf("-");
					int i2= actualMsgFromApp.indexOf(")");
					String SOnumber = actualMsgFromApp.substring(i1+1, i2);
					// write data in excel
					String transvalue = transno.substring(5); 
					int transval = Integer.parseInt(transvalue);
					xls_reader.setCellData("SalesDeliveryChallan", "soNo", transval + 1,SOnumber);
					xls_reader.setCellData("SalesInvoice", "sonumber", transval + 1, SOnumber);
					xls_reader.setCellData("DC_Cum_Invoice", "sono", transval + 1, SOnumber);
					driverwait(4);
					return true;
				}

			} catch (Exception ex) {

			}
			return false;

		}
		
		


}
