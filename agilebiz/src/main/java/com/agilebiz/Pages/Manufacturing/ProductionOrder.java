/**
 * 
 */
package com.agilebiz.Pages.Manufacturing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.agilebiz.Pages.LoginToApplication.TestingMethod;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.Xls_Reader;

/**
 * @author virat
 *
 */
public class ProductionOrder extends TestBase {

	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");

	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	public String VerifyProductionOrderPage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Production Order")) {
				throw new SkipException("Production Order Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	// ************************************Details****************************************

	public boolean enterproductionorderdate() {
		try {
			WebElement prodorderWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_proddate")));
			prodorderWebEelement.sendKeys(Keys.ENTER);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToProductTextBox(String product) {
		try {
			WebElement productTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_product")));
			productTextBoxWebEelement.clear();
			productTextBoxWebEelement.sendKeys(product);
			webElementWait(getWebElement("autcompletedd"));
			productTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToBOMIDTextBox(String bomid) {
		try {
			WebElement bomidTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_BOMid")));
			bomidTextBoxWebEelement.clear();
			bomidTextBoxWebEelement.sendKeys(bomid);
			webElementWait(getWebElement("autcompletedd"));
			bomidTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addProductionQty(String ProdQty) {
		try {
			WebElement prodqtyWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_productioqty")));
			prodqtyWebEelement.clear();
			prodqtyWebEelement.sendKeys(ProdQty);
			prodqtyWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToprodlocationTextBox(String produlocation) {
		try {
			WebElement bomidTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_prodbranch")));
			bomidTextBoxWebEelement.clear();
			bomidTextBoxWebEelement.sendKeys(produlocation);
			webElementWait(getWebElement("autcompletedd"));
			bomidTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTofglocationTextBox(String fglocation) {
		try {
			WebElement bomidTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("PO_fglocation")));
			bomidTextBoxWebEelement.clear();
			bomidTextBoxWebEelement.sendKeys(fglocation);
			webElementWait(getWebElement("autcompletedd"));
			bomidTextBoxWebEelement.sendKeys(Keys.TAB);
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
				String prodorder = actualMsgFromApp.substring(i1+1, i2);
				// write data in excel
				String transvalue = transno.substring(5); 
				int transval = Integer.parseInt(transvalue);
				xls_reader.setCellData("ProdMaterialIssue", "productionOrderNumber",transval+1, prodorder);
				xls_reader.setCellData("ProductionEntry", "productionOrder",transval+1, prodorder);
				driverwait(4);
			}

		} catch (Exception ex) {
			return false;

		}
		return true;

	}
}
