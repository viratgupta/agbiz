/**
 * 
 */
package com.agilebiz.Sales;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Sales.SalesInvoice;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class SalesInvoiceScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);

	}

	@Test(dataProvider = "SalesInvoiceData",priority=1)
	public void salesInvoice(String transno,
			String branch,
			String customer,
			String sono,
			String duedate,
			String payment,
			String challanNo,
			String goodsValue,
			String discountValue,
			String taxvalue,
			String expenseValue,
			String itctax,
			String invoicevalue
			) throws Exception {
		
		
		test.getTest().setName("Creating Sales Invoice " + transno);
		SalesInvoice si= new SalesInvoice();
	
		Assert.assertEquals(si.VerifySalesInvoicePage("Sales Invoice"), "Sales Invoice");
		test.log(LogStatus.PASS, "Verifying the Sales Invoice page", "Sales Invoice");
		
		Assert.assertEquals(si.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to branch name text field", branch);
		
		Assert.assertEquals(si.selectInvoicedate(), true, "Oops! Invoice date cannot enter.");
		test.log(LogStatus.PASS, "selecting Invoice date");
		
		Assert.assertEquals(si.addValueToCustomerTextBox(customer), true, "Oops! customer name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to customer name text field", customer);
		
		Assert.assertEquals(si.addValueToSOnoTextBox(sono), true, "Oops! Sales Order Number cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Sales Order Number text field", sono);
		
		Assert.assertEquals(si.enterduedate(duedate), true, "Oops! due date cannot enter.");
		test.log(LogStatus.PASS, "Entering value to due date field", duedate);
		
		Assert.assertEquals(si.addValueToPaymentTextBox(payment), true, "Oops! Payment Term cannot enter.");
		test.log(LogStatus.PASS, "Entering value to Payment Term field", payment);
		
		Assert.assertEquals(si.addValueToDelChallanTextBox(challanNo), true, "Oops! Delivery Challan Number cannot enter.");
		test.log(LogStatus.PASS, "Entering value to Delivery Challan field", challanNo);
		
		//Summary Validation	
		getWebElement("SI_summarytab").click();
		Assert.assertEquals(summaryvalidation("SI_totalgoodsval"), goodsValue);
		test.log(LogStatus.PASS, "Validating Total Goods Value", goodsValue);
		
		Assert.assertEquals(summaryvalidation("SI_discountval"), discountValue);
		test.log(LogStatus.PASS, "Validating Total Discount Value", discountValue);
		
		Assert.assertEquals(summaryvalidation("SI_totaltaxval"), taxvalue);
		test.log(LogStatus.PASS, "Validating Total Tax Value", taxvalue);
		
		Assert.assertEquals(summaryvalidation("SI_totalexpensechr"), expenseValue);
		test.log(LogStatus.PASS, "Validating Total Expense Charges", expenseValue);
		
		Assert.assertEquals(summaryvalidation("SI_itctax"), itctax);
		test.log(LogStatus.PASS, "Validating Total ITC Tax", itctax);
		
		Assert.assertEquals(summaryvalidation("SI_invoiceamt"), invoicevalue);
		test.log(LogStatus.PASS, "Validating Total Invoice value", invoicevalue);
		
		
		Assert.assertEquals(si.saveTstruct(), true, "Oops! Sales Invoice cannot be saved.");
		test.log(LogStatus.PASS, "Saving Sales Invoice");

		//Sales Invoice Saved (Invoice No.-SIe-City19000011)
		String successMessage = "Sales Invoice Saved";
		Assert.assertEquals(si.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "SalesInvoiceData")
	public Object[][] getData() {
		Xls_Reader xls_reader_si = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_si, "SalesInvoice");
	}



}
