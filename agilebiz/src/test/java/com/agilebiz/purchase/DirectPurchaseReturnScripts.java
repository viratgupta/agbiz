/**
 * 
 */
package com.agilebiz.purchase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.purchase.DirectPurchaseReturn;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class DirectPurchaseReturnScripts extends TestBase {

	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority = 0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(), true);
	}

	@Test(dataProvider = "DirectPurchaseReturnData", priority = 1)
	public void directPurchaseReturn(String transno,
			String branch,
			String supplier,
			String pbillno,
			String goodsValue,
			String taxableval,
			String taxvalue,
			String returnval) throws Exception {

		test.getTest().setName("Direct Purchase Return " + transno);
		DirectPurchaseReturn dpr = new DirectPurchaseReturn();

		Assert.assertEquals(dpr.VerifyPurchaseReturnPage("Direct Purchase Return"), "Direct Purchase Return");
		test.log(LogStatus.PASS, "Verifying the Direct Purchase Return page", "Direct Purchase Return");

		Assert.assertEquals(dpr.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to branch name text field", branch);

		Assert.assertEquals(dpr.addValueTosupplierTextBox(supplier), true, "Oops! supplier name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to supplier name text field", supplier);

		Assert.assertEquals(dpr.addValueTopblillnoTextBox(pbillno), true, "Oops! Purchase Bill number cannot be enter.");
		test.log(LogStatus.PASS, "Adding value to Purchase Bill Number text field", pbillno);

		Assert.assertEquals(dpr.itemDetailsfromFillgrid(), true, "Oops! Values cannot be selected from Fillgrid");
		test.log(LogStatus.PASS, "Filling Values in grid based on invoice number");

		String itemSheetname = "DPR_itemdetails";
		Assert.assertEquals(dpr.validateandsetFillgridItem(xls_reader, itemSheetname, transno, "gridHd2"), true,
				"Oops! Values cannot be validated/entered from Fillgrid");
		test.log(LogStatus.PASS, "Validating and selecting values in fillgrid");

		// Summary Validation
		getWebElement("PRET_summarytab").click();
		Assert.assertEquals(summaryvalidation("PRET_goodsvalue"), goodsValue);
		test.log(LogStatus.PASS, "Validating Total Goods Value", goodsValue);

		Assert.assertEquals(summaryvalidation("PRET_taxablevalue"), taxableval);
		test.log(LogStatus.PASS, "Validating Total Taxable Value", taxableval);

		Assert.assertEquals(summaryvalidation("PRET_taxvalue"), taxvalue);
		test.log(LogStatus.PASS, "Validating Total Tax Value", taxvalue);

		Assert.assertEquals(summaryvalidation("PRET_returnvalue"), returnval);
		test.log(LogStatus.PASS, "Validating Total Return Value", returnval);

		Assert.assertEquals(dpr.saveTstruct(), true, "Oops! Direct Purchase Return cannot be saved.");
		test.log(LogStatus.PASS, "Saving Purchase Return");

		// Sales Return Saved (Return No.-SRHSR19000036)
		String successMessage = "Direct Purchase Return Saved";
		Assert.assertEquals(dpr.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}

	@DataProvider(name = "DirectPurchaseReturnData")
	public Object[][] getData() {
		Xls_Reader xls_reader_dpreturns = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_dpreturns, "directPurchaseReturn");
	}

}
