package com.agilebiz.purchase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.purchase.DirectPurchase;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class DirectPurchaseTestScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}

	@Test(dataProvider = "DirectPurchaseData",priority=1)
	public void DirectPurchaseWithExpenseAndItem(String transno,
			String branch,
			String storelocation,
			String supplier,
			String supplierbillno,
			String frieghtValue,
			String insuranceValue,
			String itcforfright,
			String addFOB,
			String addInvoice,
			String fOBValue,
			String taxableVal,
			String totaltax,
			String totalExpcharge,
			String itcTax,
			String tdsval,
			String totalBillValue) throws Exception {
		test.getTest().setName("Creating Direct Purchase with Expenses and Item Particular) " + transno);
		DirectPurchase purchasetest = new DirectPurchase();

		// step:1
		Assert.assertEquals(purchasetest.VerifyDirectPurchasePage("Direct Purchase"), "Direct Purchase");
		test.log(LogStatus.PASS, "Verifying the direct purchase page", "Direct Purchase");
		// step:2
		Assert.assertEquals(purchasetest.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Branch field", branch);
		// step:3
		Assert.assertEquals(purchasetest.addValueToStoreTextBox(storelocation), true,
				"Oops! Strore Location cannot enter.");
		test.log(LogStatus.PASS, "Adding value to StoreLocation field", storelocation);
		
		Assert.assertEquals(purchasetest.selectbilldate(), true, "Oops! bill date cannot be entered");
		test.log(LogStatus.PASS, "Entering value to bill date field");
		
		// step:4
		Assert.assertEquals(purchasetest.addValueToSupplierTextBox(supplier), true, "Oops! supplier cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Supplier field", supplier);
		// step:5
		/*Assert.assertEquals(purchasetest.addValueToDepartmentTextBox(department), true,
				"Oops! department cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Department field", department);*/
		// step:6
		Assert.assertEquals(purchasetest.addValueToInvoiceNoTextBox(supplierbillno), true, "Oops! Supplier Bill No cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Supplier Bill Number field", supplierbillno);
		// step:7
		Assert.assertEquals(purchasetest.addValueToInvoicedateTextBox(), true, "Oops! Supplier Bill date cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Supplier Bill Date field");
		
		Assert.assertEquals(purchasetest.addFrieghtValue(frieghtValue), true, "Oops! Frieght Value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Frieght Value field", frieghtValue);
		
		Assert.assertEquals(purchasetest.addInsurenceValue(insuranceValue), true, "Oops! Insurence Value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Insurence Value field", insuranceValue);
		
		Assert.assertEquals(purchasetest.selectITCFrieghtcheckbox(itcforfright), true, "Oops! ITC for Frieght cannot be selected");
		test.log(LogStatus.PASS, "selecting value to ITC for Frieght field", itcforfright);
		
		Assert.assertEquals(purchasetest.addingFOBValue(addFOB), true, "Oops! FOB value cannot be entered");
		test.log(LogStatus.PASS, "Adding FOB value", addFOB);
		
		Assert.assertEquals(purchasetest.addinginvoiceValue(addInvoice), true, "Oops! Invoice value cannot be entered");
		test.log(LogStatus.PASS, "Adding Invoice value", addInvoice);

		String itemsheetname = "DP_ItemDetails";
		Assert.assertEquals(
				purchasetest.validateandsetFillgridItem(xls_reader, itemsheetname, transno, "gridHd2"), true,
				"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");
		
		//validating summary
		getWebElement("DP_summary").click();
		Assert.assertEquals(summaryvalidation("DP_gettotalFOBValue"), fOBValue,"FOB Values are not correct");
		test.log(LogStatus.PASS, "Validating FOB value");
		
		//Validating Taxable value
		Assert.assertEquals(summaryvalidation("DP_sumtottaxablevalue"), taxableVal,"Total Taxable Values are not correct");
		test.log(LogStatus.PASS, "Validating taxable Value");
		
		//validating tax value
		Assert.assertEquals(summaryvalidation("DP_sumtottaxvalue"), totaltax,"Total Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Tax Value");
		
		// validating expense value
		Assert.assertEquals(summaryvalidation("DP_sumtotexpensecharge"), totalExpcharge, "Total Expense Value are not correct");
		test.log(LogStatus.PASS, "Validating Total Expense Value");
		
		// validaitng ITC Tax value
		Assert.assertEquals(summaryvalidation("DP_sumitctax"), itcTax, "ITC Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating ITC Tax Value");
		
		//validating tds value
		Assert.assertEquals(summaryvalidation("DP_sumtottdsvalue"), tdsval,"Total TDS Values are not correct");
		test.log(LogStatus.PASS, "Validating TDS Value");
		
		//validating total bill value
		Assert.assertEquals(summaryvalidation("DP_gettotalbillvalue"), totalBillValue,"Total Bill Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Bill Value");

		Assert.assertEquals(purchasetest.saveTstruct(), true, "Oops! Direct purchase is not saved.");
		test.log(LogStatus.PASS, "Saving direct Purchase");

		String successMessage = "Direct Purchase Saved";
		Assert.assertEquals(purchasetest.getsavedMessage(successMessage,transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	
	
	@DataProvider(name = "DirectPurchaseData")
	public Object[][] getData() {
		Xls_Reader xls_reader1 = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader1, "DirectPurchase");
	}

}
