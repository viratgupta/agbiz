/**
 * 
 */
package com.agilebiz.Sales;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Sales.SalesOrder;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class SalesOrderScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);

	}

	@Test(dataProvider = "salesOrder",priority=1)
	public void salesOrder(String transno,
			String duedate,
			String customer,
			String payment,
			String salesperson,
			String frieght,
			String insurance,
			String itcfrieght,
			String basicvalue,
			String discount,
			String tax,
			String expensecharge,
			String itctax,
			String amount
			) throws Exception {
		
		
		test.getTest().setName("Creating Sales Order " + transno);
		SalesOrder so= new SalesOrder();
		
		Assert.assertEquals(so.VerifySalesOrderPage("Sales Order"), "Sales Order");
		test.log(LogStatus.PASS, "Verifying the Sales Order page", "Sales Order");
		
		Assert.assertEquals(so.selectsodate(), true, "Oops! sales order date cannot enter.");
		test.log(LogStatus.PASS, "selecting date to sales order date");
		
		Assert.assertEquals(so.enterduedate(duedate), true, "Oops! due date cannot enter.");
		test.log(LogStatus.PASS, "Entering value to due date field", duedate);
		
		Assert.assertEquals(so.addValueTocustomerTextBox(customer), true, "Oops! customer name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to customer name text field", customer);
		
		Assert.assertEquals(so.addValueTopaymenttermTextBox(payment), true, "Oops! Payment term cannot enter.");
		test.log(LogStatus.PASS, "Adding value to payment term text field", payment);
		
		Assert.assertEquals(so.addValueToSalesPersonTextBox(salesperson), true, "Oops! Sales Person cannot enter.");
		test.log(LogStatus.PASS, "Adding value to sales person text field", salesperson);
		
		Assert.assertEquals(so.addFrieghtValue(frieght), true, "Oops! Frieght value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to frieght text field", frieght);
		
		Assert.assertEquals(so.addInsurenceValue(insurance), true, "Oops! Insurance value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to insurance text field", insurance);
		
		Assert.assertEquals(so.selectITCFrieghtcheckbox(itcfrieght), true, "Oops! ITC for Frieght cannot be selected.");
		test.log(LogStatus.PASS, "selecting ITC for frieght checkbox", itcfrieght);
		
		String itemsheetname = "SO_itemDetails";
		Assert.assertEquals(so.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");
		
		// validate Total basic value
		getWebElement("SO_summarytab").click();
		Assert.assertEquals(summaryvalidation("SO_sum_totbasicval"), basicvalue, "Total Basic Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Basic value");
		
		Assert.assertEquals(summaryvalidation("SO_sum_totdiscount"), discount, "Total Discount Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Discount value");
		
		Assert.assertEquals(summaryvalidation("SO_sum_tottaxval"), tax, "Total Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Tax value");
		
		Assert.assertEquals(summaryvalidation("SO_sum_totexpcharge"), expensecharge, "Total Expense Charges are not correct");
		test.log(LogStatus.PASS, "Validating Total Expense Charges");
		
		Assert.assertEquals(summaryvalidation("SO_sum_totitctax"), itctax, "Total ITC Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating Total ITC Tax values");
		
		Assert.assertEquals(summaryvalidation("SO_sum_totamt"), amount, "Total Amount Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Amount values");
		
		Assert.assertEquals(so.saveTstruct(), true, "Oops! Sales Order cannot be saved.");
		test.log(LogStatus.PASS, "Saving Sales Order");

		//Sales Order Saved (SO No.-SOe-City19000014), [26,42]
		String successMessage = "Sales Order Saved";
		Assert.assertEquals(so.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "salesOrder")
	public Object[][] getData() {
		Xls_Reader xls_reader_so = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_so, "SalesOrder");
	}



}
