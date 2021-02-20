/**
 * 
 */
package com.agilebiz.Pages.purchase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.Xls_Reader;

/**
 * @author virat
 *
 */
public class PurchaseRequisition extends TestBase {

	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");

	public String VerifyPurchaseRequisitionPage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Purchase Requisition")) {
				throw new SkipException("Purchase Requisition Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PR_header").getText();
	}

	// ************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PR_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch);
			webElementWait(getWebElement("autcompletedd"));
			branchTextBoxWebEelement.sendKeys(Keys.TAB);
			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToLocationTextBox(String location) {
		try {
			WebElement locationTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PR_Location")));
			locationTextBoxWebEelement.clear();
			locationTextBoxWebEelement.sendKeys(location);
			webElementWait(getWebElement("autcompletedd"));
			locationTextBoxWebEelement.sendKeys(Keys.TAB);
			Thread.sleep(2000);
		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToDepartmentTextBox(String department) {
		try {
			WebElement departmentTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PR_department")));
			departmentTextBoxWebEelement.clear();
			departmentTextBoxWebEelement.sendKeys(department);
			webElementWait(getWebElement("autcompletedd"));
			departmentTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToInventoryTypeTextBox(String inventoryType) {
		try {
			WebElement inventoryTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PR_InventoryType")));
			inventoryTextBoxWebEelement.clear();
			inventoryTextBoxWebEelement.sendKeys(inventoryType, Keys.ENTER, Keys.TAB);
			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean selectforeclosecheckbox(String foreclose) {
		try {
			WebElement forecloseWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PR_foreclose")));
			if (foreclose.equalsIgnoreCase("T")) {
				forecloseWebEelement.click();
			} else if (foreclose.equalsIgnoreCase("F")) {
				return true;
			}

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean itemDetailsGrid(Xls_Reader xls_reader, String dcSheetname, String transno, String gridaddbtnele,
			String gridtableid) throws Exception {
		try {
			driverwait(2);
			addrecordingrid(xls_reader, dcSheetname, transno, gridtableid,"FALSE");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ************************************** SAVE_FORM
	// *******************************************

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
				//xls_reader.setCellData("GRNDetails", "POnumber", transval + 1, POnumber);
				driverwait(4);

			}
		} catch (Exception ex) {
			return false;

		}
		return true;

	}

}
