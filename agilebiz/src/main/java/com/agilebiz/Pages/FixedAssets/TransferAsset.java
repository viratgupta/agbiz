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
public class TransferAsset extends TestBase {
	
	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	
	public String VerifyTransferAssetPage(String PageName) throws Exception {
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
			if (!headerValue.equals("Transfer Asset")) {
				throw new SkipException("Transfer Asset Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("PageHeader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("TA_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch, Keys.ENTER, Keys.TAB);

			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean entertransferdate(){
		try {
			WebElement dateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("TA_transferdate")));
			dateTextBoxWebEelement.click();
			driverwait(2);
			dateTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueToSerialTextBox(String serial) {
		try {
			WebElement serialTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("TA_serial")));
			serialTextBoxWebEelement.clear();
			serialTextBoxWebEelement.sendKeys(serial);
			webElementWait(getWebElement("autcompletedd"));
			serialTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTotrans_branchTextBox(String transferBranch) {
		try {
			WebElement transbranchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("TA_trans_branch")));
			transbranchTextBoxWebEelement.clear();
			transbranchTextBoxWebEelement.sendKeys(transferBranch);
			webElementWait(getWebElement("autcompletedd"));
			transbranchTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTotrans_assetlocTextBox(String transferAssetLoc) {
		try {
			WebElement transassetlocTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("TA_trans_assertlocation")));
			transassetlocTextBoxWebEelement.clear();
			transassetlocTextBoxWebEelement.sendKeys(transferAssetLoc);
			webElementWait(getWebElement("autcompletedd"));
			transassetlocTextBoxWebEelement.sendKeys(Keys.TAB);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTotrans_custoTextBox(String transferCusto) {
		try {
			WebElement transcusTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("TA_trans_custodian")));
			transcusTextBoxWebEelement.clear();
			driverwait(2);
			transcusTextBoxWebEelement.sendKeys(transferCusto);
			transcusTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addValueTotrans_statusTextBox(String transferStatus) {
		try {
			WebElement transStatusTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("TA_trans_stausDD")));
			selectByText(transStatusTextBoxWebEelement, transferStatus);

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
