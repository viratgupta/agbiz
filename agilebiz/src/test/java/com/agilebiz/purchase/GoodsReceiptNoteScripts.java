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
import com.agilebiz.Pages.purchase.PurchaseOrder;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class GoodsReceiptNoteScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}
	
	@Test(dataProvider = "GRNData", priority=1)
	public void GoodsReceiptNote(String transno,
			String branch,
			String supplier,
			String PONumber,
			String invoiceno) throws Exception {
		
		test.getTest().setName("Creating Goods Receipt Note based on Purchase Order) " + transno);
		GoodsReceiptNote grn= new GoodsReceiptNote();
		// step:1
		Assert.assertEquals(grn.VerifyGoodsReceiptNotePage("Goods Receipt Note"), "Goods Receipt Note");
		test.log(LogStatus.PASS, "Verifying the Goods Receipt Note page", "Goods Receipt Note");
		// step:2
		Assert.assertEquals(grn.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Branch field", branch);
		
		Assert.assertEquals(grn.addValueTogrndate(), true, "Oops! grn date cannot enter.");
		test.log(LogStatus.PASS, "Adding value to grn date field");
		
		Assert.assertEquals(grn.addValueTosupplierTextBox(supplier), true, "Oops! supplier cannot enter.");
		test.log(LogStatus.PASS, "Adding value to supplier field", supplier);
		
		Assert.assertEquals(grn.selectPONumber(PONumber), true, "Oops! PO Number cannot be selected.");
		test.log(LogStatus.PASS, "Selecting PO number", PONumber);
		
		Assert.assertEquals(grn.addvaluetoInvoiceno(invoiceno), true, "Oops! Invoicce No cannot be entered");
		test.log(LogStatus.PASS, "Adding Invoice number", invoiceno);
		
		Assert.assertEquals(grn.addvaluetoInvoicedate(), true, "Oops! Invoicce date cannot be entered");
		test.log(LogStatus.PASS, "Entering invoice date");
		
		Assert.assertEquals(grn.itemDetailsfromFillgrid(), true, "Oops! Data is not filled from fillgrid");
		test.log(LogStatus.PASS, "Filling Data from Fillgrid");
		
		String itemsheetname = "GRN_ItemDetails";
		Assert.assertEquals(grn.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be Entered");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values", transno);
		
		Assert.assertEquals(grn.saveTstruct(), true, "Oops! Goods Receipt Note cannot be saved.");
		test.log(LogStatus.PASS, "Saving Goods Receipt Note");

		//Goods Receipt Note Saved (GRN #-GRNHSR19000205)
		String successMessage = "Goods Receipt Note Saved";
		Assert.assertEquals(grn.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save", successMessage);
	
	}
	
	
	
	@DataProvider(name = "GRNData")
	public Object[][] getData() {
		Xls_Reader xls_reader1 = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader1, "GRNDetails");
	}

}
