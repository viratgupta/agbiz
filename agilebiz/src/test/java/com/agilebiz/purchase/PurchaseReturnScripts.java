/**
 * 
 */
package com.agilebiz.purchase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.purchase.PurchaseReturn;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class PurchaseReturnScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}

	@Test(dataProvider = "PurchaseReturnData",priority=1)
	public void salesReturn(String transno,
			String branch,
			String supplier,
			String purchasebill,
			String totalgoodvalue,
			String discountValue,
			String taxableval,
			String taxvalue,
			String returnval
			) throws Exception {
		
		
		test.getTest().setName("Purchase Return " + transno);
		PurchaseReturn pret= new PurchaseReturn();
	
		Assert.assertEquals(pret.VerifyPurchaseReturnPage("Purchase Return"), "Purchase Return");
		test.log(LogStatus.PASS, "Verifying the Purchase Return page", "Purchase Return");
		
		Assert.assertEquals(pret.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to branch name text field", branch);
		
		Assert.assertEquals(pret.selectPurchaseReturndate(), true, "Oops! Purchase Return date cannot enter.");
		test.log(LogStatus.PASS, "selecting Sales Return date");
		
		Assert.assertEquals(pret.addValueToSupplierTextBox(supplier), true, "Oops! supplier name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to supplier name text field", supplier);
		
		Assert.assertEquals(pret.addValueTopurchasebillTextBox(purchasebill), true, "Oops! purchase bill cannot be enter.");
		test.log(LogStatus.PASS, "Adding value to purchase bill text field", purchasebill);
		
		Assert.assertEquals(pret.itemDetailsfromFillgrid(), true, "Oops! Values cannot be selected from Fillgrid");
		test.log(LogStatus.PASS, "Filling Values in grid based on Purchase Bill");
		
		String itemSheetname= "PurchaseReturn_itemdetails";
		Assert.assertEquals(pret.validateandsetFillgridItem(xls_reader, itemSheetname, transno, "gridHd2"), true, "Oops! Values cannot be validated/entered from Fillgrid");
		test.log(LogStatus.PASS, "Validating and selecting values in fillgrid");
		
		// Summary Validation
		getWebElement("PRET_summarytab").click();
		Assert.assertEquals(summaryvalidation("PRET_goodsvalue"), totalgoodvalue);
		test.log(LogStatus.PASS, "Validating Total Goods Value", totalgoodvalue);

		Assert.assertEquals(summaryvalidation("PRET_discountvalue"), discountValue);
		test.log(LogStatus.PASS, "Validating Total Discount Value", discountValue);

		Assert.assertEquals(summaryvalidation("PRET_taxablevalue"), taxableval);
		test.log(LogStatus.PASS, "Validating Total Taxable Value", taxableval);

		Assert.assertEquals(summaryvalidation("PRET_taxvalue"), taxvalue);
		test.log(LogStatus.PASS, "Validating Total Tax Value", taxvalue);

		Assert.assertEquals(summaryvalidation("PRET_returnvalue"), returnval);
		test.log(LogStatus.PASS, "Validating Total Return Value", returnval);
		
		
		Assert.assertEquals(pret.saveTstruct(), true, "Oops! Purchase Return cannot be saved.");
		test.log(LogStatus.PASS, "Saving Purchase Return");

		//Sales Return Saved (Return No.-SRHSR19000036)
		String successMessage = "Purchase Return Saved";
		Assert.assertEquals(pret.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "PurchaseReturnData")
	public Object[][] getData() {
		Xls_Reader xls_reader_preturns = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_preturns, "PurchaseReturn");
	}



}
