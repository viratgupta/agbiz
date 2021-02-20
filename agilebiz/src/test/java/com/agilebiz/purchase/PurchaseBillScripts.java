/**
 * 
 */
package com.agilebiz.purchase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.purchase.GoodsReceiptNote;
import com.agilebiz.Pages.purchase.PurchaseBill;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class PurchaseBillScripts extends TestBase{
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}
	
	@Test(dataProvider = "PurchaseBillData",priority=1)
	public void PurchaseBill(String transno,
			String branch,
			String supplier,
			String POnumber,
			String GRNnumber,
			String invoicegrossn,
			String fobvaluen,
			String invoicevaln,
			String fOBValue,
			String totalgrossValue,
			String taxableVal,
			String totaltax,
			String totalExpcharge,
			String itcTax,
			String tdsval,
			String totalinvoiceValue) throws Exception {
		
		test.getTest().setName("Creating Purchase Bill based on Purchase Order and GRN Number) " + transno);
		PurchaseBill pbill = new PurchaseBill();
		// step:1
		Assert.assertEquals(pbill.VerifyPurchaseBillPage("Purchase Bill"), "Purchase Bill");
		test.log(LogStatus.PASS, "Verifying the Purchase Bill page", "Purchase Bill");
		// step:2
		Assert.assertEquals(pbill.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Branch field", branch);
		
		Assert.assertEquals(pbill.addValueTobilldate(), true,"Oops! bill date cannot enter.");
		test.log(LogStatus.PASS, "Adding value to bill date field");
		
		Assert.assertEquals(pbill.addValueTosupplierTextBox(supplier), true, "Oops! supplier cannot enter.");
		test.log(LogStatus.PASS, "Adding value to supplier field", supplier);
		
		Assert.assertEquals(pbill.selectPONumber(POnumber), true, "Oops! PO Number cannot be selected.");
		test.log(LogStatus.PASS, "Selecting PO number", POnumber);
		
		Assert.assertEquals(pbill.selectGRNNumber(GRNnumber), true, "Oops! GRN Number cannot be selected");
		test.log(LogStatus.PASS, "Selecting GRN number", GRNnumber);
		
		Assert.assertEquals(pbill.addinvoicegross(invoicegrossn), true, "Oops! Invoice Gross value cannot be entered");
		test.log(LogStatus.PASS, "setting Invoice Gross Value");
		
		Assert.assertEquals(pbill.SetFOBvalue(fobvaluen), true, "Oops! FOB values cannot be entered");
		test.log(LogStatus.PASS, "setting FOB value");
		
		Assert.assertEquals(pbill.setinvoicevalue(invoicevaln), true, "Oops! Total Invoice values cannot be entered");
		test.log(LogStatus.PASS, "setting total invoice value");
		
		String itemsheetname = "PB_ItemDetails";
		Assert.assertEquals(pbill.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be Entered");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values", transno);
		
		// validating FOB value
		getWebElement("PB_summarytab").click();
		Assert.assertEquals(summaryvalidation("PB_smytotFOBvalue"), fOBValue, "FOB Values are not correct");
		test.log(LogStatus.PASS, "Validating FOB value");
		
		// validating total gross value
		Assert.assertEquals(summaryvalidation("PB_smytotgrossvalue"), totalgrossValue, "Total Gross Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Gross value");
		
		// Validating Taxable value
		Assert.assertEquals(summaryvalidation("PB_smytottaxablevalue"), taxableVal, "Total Taxable Values are not correct");
		test.log(LogStatus.PASS, "Validating taxable Value");

		// validating tax value
		Assert.assertEquals(summaryvalidation("PB_smytaxvalue"), totaltax, "Total Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Tax Value");

		// validating expense value
		Assert.assertEquals(summaryvalidation("PB_smyexpensecharge"), totalExpcharge, "Total Expense Value are not correct");
		test.log(LogStatus.PASS, "Validating Total Expense Value");

		// validating ITC Tax value
		Assert.assertEquals(summaryvalidation("PB_smyITCtax"), itcTax, "ITC Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating ITC Tax Value");

		// validating tds value
		Assert.assertEquals(summaryvalidation("PB_smytotTDSvalue"), tdsval, "Total TDS Values are not correct");
		test.log(LogStatus.PASS, "Validating TDS Value");

		// validating total bill value
		Assert.assertEquals(summaryvalidation("PB_smytotinvoicevalue"), totalinvoiceValue, "Total Invoice Values is not correct");
		test.log(LogStatus.PASS, "Validating Total Invoice Value");
		
		Assert.assertEquals(pbill.saveTstruct(), true, "Oops! Purchase Bill cannot be saved.");
		test.log(LogStatus.PASS, "Saving Purchase Bill");

		//Purchase Bill Saved (Bill No-PBHSR19000083)
		String successMessage = "Purchase Bill Saved";
		Assert.assertEquals(pbill.getsavedMessage(successMessage,transno), true);
		test.log(LogStatus.PASS, "Success message after save", successMessage);
	
	}
	
	
	
	@DataProvider(name = "PurchaseBillData")
	public Object[][] getData() {
		Xls_Reader xls_reader_pb = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_pb, "PurchaseBillDetails");
	}

}
