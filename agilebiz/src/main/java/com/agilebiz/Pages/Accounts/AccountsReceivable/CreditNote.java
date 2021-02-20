/**
 * 
 */
package com.agilebiz.Pages.Accounts.AccountsReceivable;

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
public class CreditNote extends TestBase {
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	public String VerifyCreditNotePage(String PageName) throws Exception {
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
			if (!headerValue.equals("Credit Note")) {
				throw new SkipException("Credit Note Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_CN_branch")));
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
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_CN_date")));
			docdateWebEelement.sendKeys(Keys.ENTER);
		driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueTocustomerTextBox(String customer) {
		try {
			WebElement custTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_CN_customer")));
			custTextBoxWebEelement.clear();
			custTextBoxWebEelement.sendKeys(customer);
			webElementWait(getWebElement("autcompletedd"));
			custTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToRefNoTextBox(String refno) {
		try {
			WebElement refnoTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_CN_refno")));
			refnoTextBoxWebEelement.clear();
			refnoTextBoxWebEelement.sendKeys(refno);
			refnoTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToRefdateTextBox(String refdate) {
		try {
			WebElement refdateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("AR_CN_refdate")));
			refdateTextBoxWebEelement.clear();
			refdateTextBoxWebEelement.sendKeys(refdate);
			refdateTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	
	//*********************************** Item Details ******************************************
	
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
