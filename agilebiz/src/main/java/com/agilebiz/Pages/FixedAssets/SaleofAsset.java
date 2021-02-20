/**
 * 
 */
package com.agilebiz.Pages.FixedAssets;

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
public class SaleofAsset extends TestBase {
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	public String VerifySaleofAssetPage(String PageName) throws Exception {
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
			if (!headerValue.equals("Sale of Asset")) {
				throw new SkipException("Sale of Asset Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SOA_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch, Keys.ENTER, Keys.TAB);

			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean enterinvoicedate(){
		try {
			WebElement dateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SOA_invoicedate")));
			dateTextBoxWebEelement.click();
			driverwait(2);
			dateTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectwalkIn(String walkin){
		try {
			WebElement walkinBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SOA_walkin_select")));
			walkinBoxWebEelement.clear();
			driverwait(2);
			selectByText(walkinBoxWebEelement, walkin);
			walkinBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean entervaluetosaletofield(String saleto){
		try {
			WebElement saletofieldWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SOA_saleto")));
			saletofieldWebEelement.clear();
			saletofieldWebEelement.sendKeys(saleto);
			saletofieldWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTocashactTextBox(String cashact) {
		try {
			WebElement cashactTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SOA_cashbankac")));
			cashactTextBoxWebEelement.clear();
			cashactTextBoxWebEelement.sendKeys(cashact);
			webElementWait(getWebElement("autcompletedd"));
			cashactTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToSerialTextBox(String serial) {
		try {
			WebElement serialTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SOA_serial")));
			serialTextBoxWebEelement.clear();
			serialTextBoxWebEelement.sendKeys(serial);
			webElementWait(getWebElement("autcompletedd"));
			serialTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean entervaluetosaleValuefield(String saleValue){
		try {
			WebElement salevaluefieldWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("SOA_salevalue")));
			salevaluefieldWebEelement.clear();
			salevaluefieldWebEelement.sendKeys(saleValue);
			salevaluefieldWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
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
				
				//String POnumber= actualMsgFromApp.substring(27,40);
				driverwait(3);
				// write data in excel 
				/*String transvalue= transno.substring(5);
				int transval= Integer.parseInt(transvalue);
				xls_reader.setCellData("GRNDetails", "POnumber", transval+1, POnumber);
				xls_reader.setCellData("PurchaseBillDetails", "POnumber", transval+1, POnumber);*/
				
				if (actualMsgFromApp.contains(successMessage)) {
					return true;
				}

			} catch (Exception ex) {
				

			}
			return false;

		}


}
