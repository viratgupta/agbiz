/**
 * 
 */
package com.agilebiz.Sales;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Sales.DirectSales;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class DirectSalesScripts extends TestBase {

	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority = 0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(), true);

	}

	@Test(dataProvider = "directsalesdata", priority = 1)
	public void directSales(String transno, String customer, String duedate, String payment, String salesperson,
			String frieght, String insurance, String itcfrieght, String goodsvalue, String discount, String taxvalue,
			String expensecharge, String itctax, String invoicevalue) throws Exception {

		test.getTest().setName("Creating Direct Sales " + transno);
		DirectSales ds = new DirectSales();

		Assert.assertEquals(ds.VerifyDirectSalesPage("Direct Sales"), "Direct Sales");
		test.log(LogStatus.PASS, "Verifying Direct Sales page", "Direct Sales");

		Assert.assertEquals(ds.selectinvoicedate(), true, "Oops! Invoice date cannot enter.");
		test.log(LogStatus.PASS, "selecting invoice date");

		Assert.assertEquals(ds.addValueTocustomerTextBox(customer), true, "Oops! customer name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to customer name text field", customer);

		Assert.assertEquals(ds.enterduedate(duedate), true, "Oops! due date cannot enter.");
		test.log(LogStatus.PASS, "Entering value to due date field", duedate);

		Assert.assertEquals(ds.addValueTopaymenttermTextBox(payment), true, "Oops! Payment term cannot enter.");
		test.log(LogStatus.PASS, "Adding value to payment term text field", payment);

		Assert.assertEquals(ds.addValueToSalesPersonTextBox(salesperson), true, "Oops! Sales Person cannot enter.");
		test.log(LogStatus.PASS, "Adding value to sales person text field", salesperson);

		Assert.assertEquals(ds.addFrieghtValue(frieght), true, "Oops! Frieght value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to frieght text field", frieght);

		Assert.assertEquals(ds.addInsurenceValue(insurance), true, "Oops! Insurance value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to insurance text field", insurance);

		Assert.assertEquals(ds.selectITCFrieghtcheckbox(itcfrieght), true, "Oops! ITC for Frieght cannot be selected.");
		test.log(LogStatus.PASS, "selecting ITC for frieght checkbox", itcfrieght);

		String itemsheetname = "DirectSales_itemDetails";
		Assert.assertEquals(ds.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,
				"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");

		// validate Total basic value
		getWebElement("DS_summarytab").click();
		Assert.assertEquals(summaryvalidation("DS_totalgoodvalue"), goodsvalue, "Total Goods Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Goods value");

		Assert.assertEquals(summaryvalidation("DS_discountvalue"), discount, "Total Discount Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Discount value");

		Assert.assertEquals(summaryvalidation("DS_taxvalue"), taxvalue, "Total Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Tax value");

		Assert.assertEquals(summaryvalidation("DS_expenseharges"), expensecharge,
				"Total Expense Charges are not correct");
		test.log(LogStatus.PASS, "Validating Total Expense Charges");

		Assert.assertEquals(summaryvalidation("DS_itctax"), itctax, "Total ITC Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating Total ITC Tax values");

		Assert.assertEquals(summaryvalidation("DS_invoicevalue"), invoicevalue, "Total Invoice Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Invoice values");

		Assert.assertEquals(ds.saveTstruct(), true, "Oops! Sales Order cannot be saved.");
		test.log(LogStatus.PASS, "Saving Sales Order");

		// Sales Order Saved (SO No.-SOe-City19000014), [26,42]
		String successMessage = "Direct Sales Saved";
		Assert.assertEquals(ds.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}

	@DataProvider(name = "directsalesdata")
	public Object[][] getData() {
		Xls_Reader xls_reader_ds = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_ds, "DirectSales");
	}

}
