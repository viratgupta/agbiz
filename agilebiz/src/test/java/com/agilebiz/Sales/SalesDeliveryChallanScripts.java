/**
 * 
 */
package com.agilebiz.Sales;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Sales.SalesDeliveryChallan;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class SalesDeliveryChallanScripts extends TestBase{
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);

	}

	@Test(dataProvider = "DeliveryChallanData",priority=1)
	public void salesDeliveryChallan(String transno,
			String branch,
			String stocklocation,
			String customer,
			String sono,
			String duedate,
			String goodsvalue,
			String discountvalue,
			String totaltaxvalue,
			String totalexpensecharge,
			String itctax,
			String totalinvoicevalue
			) throws Exception {
		
		
		test.getTest().setName("Creating Sales Delivery Challan " + transno);
		SalesDeliveryChallan sdc= new SalesDeliveryChallan();
		
		Assert.assertEquals(sdc.VerifySalesDeliveryChalanPage("Sales Delivery Challan"), "Sales Delivery Challan");
		test.log(LogStatus.PASS, "Verifying the Sales Delivery Challan page", "Sales Delivery Challan");
		
		Assert.assertEquals(sdc.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to branch name text field", branch);
		
		Assert.assertEquals(sdc.addValueToStockLocationTextBox(stocklocation), true, "Oops! stock location cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Stock Location text field", stocklocation);
		
		Assert.assertEquals(sdc.selectDCdate(), true, "Oops! delivery challan date cannot enter.");
		test.log(LogStatus.PASS, "selecting delivery challan date");
		
		Assert.assertEquals(sdc.addValueToCustomerTextBox(customer), true, "Oops! customer name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to customer name text field", customer);
		
		Assert.assertEquals(sdc.addValueToSOnoTextBox(sono), true, "Oops! Sales Order Number cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Sales Order Number text field", sono);
		
		Assert.assertEquals(sdc.enterduedate(duedate), true, "Oops! due date cannot enter.");
		test.log(LogStatus.PASS, "Entering value to due date field", duedate);
		
		Assert.assertEquals(sdc.itemDetailsfromFillgrid(), true,"Oops! Item details cannot be enter from fillgrid.");
		test.log(LogStatus.PASS, "Filling  Item Details based on the Sales order number");
		
		String itemsheetname = "SDC_itemDetails";
		Assert.assertEquals(sdc.validateandsetFillgridItem(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be entered and validated.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");
		
		// Summary Validation
		getWebElement("SDC_summarytab").click();
		Assert.assertEquals(summaryvalidation("SDC_totalgoodsval"), goodsvalue);
		test.log(LogStatus.PASS, "Validating Total Goods Value", goodsvalue);

		Assert.assertEquals(summaryvalidation("SDC_discountval"), discountvalue);
		test.log(LogStatus.PASS, "Validating Total Discount Value", discountvalue);

		Assert.assertEquals(summaryvalidation("SDC_totaltaxval"), totaltaxvalue);
		test.log(LogStatus.PASS, "Validating Total Tax Value", totaltaxvalue);

		Assert.assertEquals(summaryvalidation("SDC_totalexpensechr"), totalexpensecharge);
		test.log(LogStatus.PASS, "Validating Total Expense Charges", totalexpensecharge);

		Assert.assertEquals(summaryvalidation("SDC_itctax"), itctax);
		test.log(LogStatus.PASS, "Validating Total ITC Tax", itctax);

		Assert.assertEquals(summaryvalidation("SDC_invoiceamt"), totalinvoicevalue);
		test.log(LogStatus.PASS, "Validating Total Invoice value", totalinvoicevalue);
		
		Assert.assertEquals(sdc.saveTstruct(), true, "Oops! Sales Delivery Challan cannot be saved.");
		test.log(LogStatus.PASS, "Saving Sales Delivery Challan");

		//Sales Delivery Challan Saved (Delivery Challan No.-DSHSR19000173) []
		String successMessage = "Sales Delivery Challan Saved";
		Assert.assertEquals(sdc.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "DeliveryChallanData")
	public Object[][] getData() {
		Xls_Reader xls_reader_sdh = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_sdh, "SalesDeliveryChallan");
	}

}
