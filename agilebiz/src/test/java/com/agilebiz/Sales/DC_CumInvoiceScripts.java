/**
 * 
 */
package com.agilebiz.Sales;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Sales.DC_CumInvoice;
import com.agilebiz.Pages.Sales.SalesInvoice;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class DC_CumInvoiceScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		loginPage.loginToAxpertX_userbased("appdetails", "Email_id", "password");
		Assert.assertEquals(loginPage.getSuccessfulMessage(), "Welcome");

	}

	@Test(dataProvider = "DC_Cum_InvoiceData",priority=1)
	public void DC_CumSalesInvoice(String transno,
			String branch,
			String stockloc,
			String customer,
			String sono,
			String duedate,
			String goodsValue,
			String discountValue,
			String taxvalue,
			String expenseValue,
			String itctax,
			String invoicevalue
			) throws Exception {
		
		
		test.getTest().setName("DC Cum Sales Invoice " + transno);
		DC_CumInvoice dc= new DC_CumInvoice();
	
		Assert.assertEquals(dc.VerifyDC_CumInvoicePage("DC Cum Invoice"), "DC Cum Invoice");
		test.log(LogStatus.PASS, "Verifying the DC Cum Invoice page", "DC Cum Invoice");
		
		Assert.assertEquals(dc.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to branch name text field", branch);
		
		Assert.assertEquals(dc.addValueToStockLocTextBox(stockloc), true, "Oops! Stock Location cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Stock Location text field", stockloc);
		
		Assert.assertEquals(dc.selectInvoicedate(), true, "Oops! Invoice date cannot enter.");
		test.log(LogStatus.PASS, "selecting Invoice date");
		
		Assert.assertEquals(dc.addValueToCustomerTextBox(customer), true, "Oops! customer name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to customer name text field", customer);
		
		Assert.assertEquals(dc.addValueToSOnoTextBox(sono), true, "Oops! Sales Order Number cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Sales Order Number text field", sono);
		
		Assert.assertEquals(dc.enterduedate(duedate), true, "Oops! due date cannot enter.");
		test.log(LogStatus.PASS, "Entering value to due date field", duedate);
		
		// Summary Validation
		//getWebElement("SI_summarytab").click();
		Assert.assertEquals(summaryvalidation("DCI_totalgoodsval"), goodsValue);
		test.log(LogStatus.PASS, "Validating Total Goods Value", goodsValue);

		Assert.assertEquals(summaryvalidation("DCI_discountval"), discountValue);
		test.log(LogStatus.PASS, "Validating Total Discount Value", discountValue);

		Assert.assertEquals(summaryvalidation("DCI_totaltaxval"), taxvalue);
		test.log(LogStatus.PASS, "Validating Total Tax Value", taxvalue);

		Assert.assertEquals(summaryvalidation("DCI_totalexpensechr"), expenseValue);
		test.log(LogStatus.PASS, "Validating Total Expense Charges", expenseValue);

		Assert.assertEquals(summaryvalidation("DCI_itctax"), itctax);
		test.log(LogStatus.PASS, "Validating Total ITC Tax", itctax);

		Assert.assertEquals(summaryvalidation("DCI_invoiceamt"), invoicevalue);
		test.log(LogStatus.PASS, "Validating Total Invoice value", invoicevalue);
		
		Assert.assertEquals(dc.saveTstruct(), true, "Oops! DC Cum Invoice cannot be saved.");
		test.log(LogStatus.PASS, "Saving DC Cum Invoice");

		//DC Cum Invoice Saved (Invoice No.-DCHSR19000009)
		String successMessage = "DC Cum Invoice Saved";
		Assert.assertEquals(dc.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "DC_Cum_InvoiceData")
	public Object[][] getData() {
		Xls_Reader xls_reader_dcinv = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_dcinv, "DC_Cum_Invoice");
	}


}
