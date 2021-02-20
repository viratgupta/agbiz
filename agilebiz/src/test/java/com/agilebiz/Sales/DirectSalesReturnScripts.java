/**
 * 
 */
package com.agilebiz.Sales;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Sales.DirectSalesReturn;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class DirectSalesReturnScripts extends TestBase {

	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority = 0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(), true);
	}

	@Test(dataProvider = "DirectSalesReturnData", priority = 1)
	public void directSalesReturn(String transno, String branch, String customer, String invno, String basicValue,
			String discountValue, String taxableval, String taxvalue, String returnval) throws Exception {

		test.getTest().setName("Direct Sales Return " + transno);
		DirectSalesReturn dsr = new DirectSalesReturn();

		Assert.assertEquals(dsr.VerifySalesReturnPage("Direct Sales Return"), "Direct Sales Return");
		test.log(LogStatus.PASS, "Verifying the Direct Sales Return page", "Direct Sales Return");

		Assert.assertEquals(dsr.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to branch name text field", branch);

		Assert.assertEquals(dsr.selectReturndate(), true, "Oops! Sales Return date cannot enter.");
		test.log(LogStatus.PASS, "selecting Sales Return date");

		Assert.assertEquals(dsr.addValueToCustomerTextBox(customer), true, "Oops! customer name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to customer name text field", customer);

		Assert.assertEquals(dsr.addValueToinvoicenoTextBox(invno), true, "Oops! invoice number cannot be enter.");
		test.log(LogStatus.PASS, "Adding value to Sales Invoice Number text field", invno);

		Assert.assertEquals(dsr.itemDetailsfromFillgrid(), true, "Oops! Values cannot be selected from Fillgrid");
		test.log(LogStatus.PASS, "Filling Values in grid based on invoice number");

		String itemSheetname = "DirectSalesReturnDetails";
		Assert.assertEquals(dsr.validateandsetFillgridItem(xls_reader, itemSheetname, transno, "gridHd2"), true,
				"Oops! Values cannot be validated/entered from Fillgrid");
		test.log(LogStatus.PASS, "Validating and selecting values in fillgrid");

		// Summary Validation
		getWebElement("SR_summarytab").click();
		Assert.assertEquals(summaryvalidation("SR_totbasicval"), basicValue);
		test.log(LogStatus.PASS, "Validating Total Basic Value", basicValue);

		Assert.assertEquals(summaryvalidation("SR_totdiscount"), discountValue);
		test.log(LogStatus.PASS, "Validating Total Discount Value", discountValue);

		Assert.assertEquals(summaryvalidation("SR_tottaxablevval"), taxableval);
		test.log(LogStatus.PASS, "Validating Total Taxable Value", taxableval);

		Assert.assertEquals(summaryvalidation("SR_tottaxval"), taxvalue);
		test.log(LogStatus.PASS, "Validating Total Tax Value", taxvalue);

		Assert.assertEquals(summaryvalidation("SR_totreturnval"), returnval);
		test.log(LogStatus.PASS, "Validating Total Return Value", returnval);

		Assert.assertEquals(dsr.saveTstruct(), true, "Oops! Direct Sales Return cannot be saved.");
		test.log(LogStatus.PASS, "Saving Sales Return");

		// Sales Return Saved (Return No.-SRHSR19000036)
		String successMessage = "Direct Sales Return Saved";
		Assert.assertEquals(dsr.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}

	@DataProvider(name = "DirectSalesReturnData")
	public Object[][] getData() {
		Xls_Reader xls_reader_dsreturns = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_dsreturns, "DirectSalesReturn");
	}

}
