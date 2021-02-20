/**
 * 
 */
package com.agilebiz.Pages.purchase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;

import com.agilebiz.Pages.LoginToApplication.TestingMethod;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.Xls_Reader;

/**
 * @author virat
 *
 */
public class PurchaseOrder extends TestBase {

	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");

	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	public String VerifyPurchaseOrderPage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Purchase Order")) {
				throw new SkipException("Purchase Order Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PO_header").getText();
	}

	// ************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch);
			webElementWait(getWebElement("autcompletedd"));
			branchTextBoxWebEelement.sendKeys(Keys.TAB);
			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean selectvaluetoType(String type) {
		try {
			WebElement selecttypeWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_type")));
			selectByText(selecttypeWebEelement, type);
			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToSupplierTextBox(String supplier) {
		try {
			WebElement supplierTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_supplier")));
			supplierTextBoxWebEelement.clear();
			supplierTextBoxWebEelement.sendKeys(supplier);
			webElementWait(getWebElement("autcompletedd"));
			supplierTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToDueDate(String duedate) {
		try {
			WebElement duedateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_duedate")));
			duedateTextBoxWebEelement.clear();
			duedateTextBoxWebEelement.sendKeys(duedate);
			//duedateTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToDepartmentTextBox(String department) {
		try {
			WebElement departmentTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_department")));
			departmentTextBoxWebEelement.clear();
			departmentTextBoxWebEelement.sendKeys(department);
			webElementWait(getWebElement("autcompletedd"));
			departmentTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean selectforeclosecheckbox(String foreclose) {
		try {
			WebElement forecloseWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_foreclose")));
			if (foreclose.equalsIgnoreCase("T")) {
				forecloseWebEelement.click();
			} else if (foreclose.equalsIgnoreCase("F")) {
				return true;
			}

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean expenseDetailsGrid(Xls_Reader xls_reader, String dcSheetname, String transno,String gridtableid) throws Exception {
		try {
			driverwait(2);
			addrecordingrid(xls_reader, dcSheetname, transno, gridtableid,"FALSE");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ********************************** Item Details
	// **************************************************88

	public boolean itemDetailsGrid(Xls_Reader xls_reader, String dcSheetname, String transno, String gridtableid) throws Exception {
		try {
			driverwait(2);
			addrecordingrid(xls_reader, dcSheetname, transno, gridtableid,"FALSE");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean itemDetailsfromFillgrid(String prid) throws Exception {

		try {
			String prid1 = prid.substring(35, 48);
			getWebElement("PO_itemtab").click();
			driverwait(2);
			getWebElement("PO_fillfromPR").click();
			if (isElementPresent("PO_fillgridnodata")) {
				getWebElement("PO_OKbtn").click();
				Reporter.log("No Record Found In Fillgrid");
				return false;
			} else {
				WebElement fillgidsearch = new WebDriverWait(driver, 100)
						.until(ExpectedConditions.visibilityOf(getWebElement("PO_fillgridsearch")));
				fillgidsearch.clear();
				fillgidsearch.sendKeys(prid1);
				driverwait(2);
				getWebElement("PO_fillgridselectall").click();
				getWebElement("PO_OKbtn").click();
				driverwait(2);
				return true;
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
			return false;
		}
	}

	public boolean getandSetFOBvalue() {
		try {
			getWebElement("PO_summarytab").click();
			WebElement totalFOBTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_sumtotalfobvalue")));
			String FOBvalue = totalFOBTextBoxWebEelement.getAttribute("value");
			// String Billvalue =
			// getWebElement("DP_gettotalbillvalue").getAttribute("value");
			implicitWait(2);

			// now entering the total FOB and Total Bill value

			getWebElement("PO_totalfobvalue").clear();
			getWebElement("PO_totalfobvalue").sendKeys(FOBvalue);
			implicitWait(2);
			/*
			 * getWebElement("DP_invoiceamt").clear();
			 * getWebElement("DP_invoiceamt").sendKeys(Billvalue);
			 */
		} catch (Exception ex) {

		}
		return true;
	}
	// *********************************** Expense Details
	// ******************************************

	public boolean addFrieghtValue(String FrieghtValue) {
		try {
			WebElement frieghtTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_frieghtvalue")));
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
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_insurencevalue")));
			insurenceTextBoxWebEelement.clear();
			driverwait(2);
			insurenceTextBoxWebEelement.sendKeys(InsurenceValue);
			insurenceTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean selectITCFrieghtcheckbox(String ITCforFrieght) {
		try {
			WebElement itcfrieghtWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_itcfrieght")));
			if (ITCforFrieght.equalsIgnoreCase("T")) {
				itcfrieghtWebEelement.click();
			} else if (ITCforFrieght.equalsIgnoreCase("F") || ITCforFrieght.equalsIgnoreCase("")) {
				return true;
			}

		} catch (Exception ex) {

		}
		return true;
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
				String POnumber = actualMsgFromApp.substring(i1+1, i2);
				// write data in excel
				String transvalue = transno.substring(5); 
				int transval = Integer.parseInt(transvalue);
				xls_reader.setCellData("GRNDetails", "POnumber", transval + 1, POnumber);
				xls_reader.setCellData("PurchaseBillDetails", "POnumber", transval + 1, POnumber);
				driverwait(4);

			}

		} catch (Exception ex) {
			return false;

		}
		return true;

	}

}
