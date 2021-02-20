/**
 * 
 */
package com.agilebiz.purchase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.purchase.PurchaseOrder;
import com.agilebiz.Pages.purchase.PurchaseRequisition;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class PurchaseOrderScripts extends TestBase{
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}

	@Test(dataProvider = "PurchaseOrderData",priority=1)
	public void PurchaseorderWithItemAndExpense(String transno,
			String branch,
			String supplier,
			String duedate,
			String frieghtValue,
			String insurenceValue,
			String itcforFrieght,
			String totalValue,
			String TotalDiscount,
			String TotalFOB,
			String TotalTaxableValue,
			String TotalTaxValue,
			String TotalExpenseCharges,
			String ITCTax,
			String TotalTDSAmt,
			String TotalPOValue) throws Exception {
		
		
		test.getTest().setName("Creating Purchase Order with Items And Expenses) " + transno);
		PurchaseOrder purchaseordertest = new PurchaseOrder();
		
		Assert.assertEquals(purchaseordertest.VerifyPurchaseOrderPage("Purchase Order"), "Purchase Order");
		test.log(LogStatus.PASS, "Verifying the purchase order page", "Purchase Order");
		
		Assert.assertEquals(purchaseordertest.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Branch field", branch);
		
		Assert.assertEquals(purchaseordertest.addValueToSupplierTextBox(supplier), true, "Oops! supplier cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Supplier field", supplier);
		
		Assert.assertEquals(purchaseordertest.addValueToDueDate(duedate), true, "Oops! DueDate cannot enter.");
		test.log(LogStatus.PASS, "Adding value to DueDate field", duedate);
		
		Assert.assertEquals(purchaseordertest.addFrieghtValue(frieghtValue), true, "Oops! Frieght Value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Frieght Value field", frieghtValue);
		
		Assert.assertEquals(purchaseordertest.addInsurenceValue(insurenceValue), true, "Oops! Insurence Value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Insurence Value field", insurenceValue);
		
		Assert.assertEquals(purchaseordertest.selectITCFrieghtcheckbox(itcforFrieght), true, "Oops! ITC for Frieght cannot be selected");
		test.log(LogStatus.PASS, "selecting value to ITC for Frieght field", itcforFrieght);
		
		// step:7
		/*Assert.assertEquals(purchaseordertest.selectforeclosecheckbox(foreclose), true, "Oops! foreclose checkbox cannot be selected.");
		test.log(LogStatus.PASS, "forclose value",foreclose);*/

		/*Assert.assertEquals(purchaseordertest.itemDetailsfromFillgrid(PurchaseRequisition.getPRvlaue()), true,"Oops! Item Details cannot be filled from fillgrid OR Data is not available for Fillgrid.");
		test.log(LogStatus.PASS, "Adding Item Details from Fillgid");*/
		
		//step:8
		String itemsheetname = "PO_ItemDetails";
		Assert.assertEquals(purchaseordertest.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be Entered");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values", transno);
		
		/*Assert.assertEquals(purchaseordertest.getandSetFOBvalue(), true, "Oops! Not able to get and set values.");
		test.log(LogStatus.PASS, "Adding value to FOB and BILL field");*/
		
		//validate Total value field
		getWebElement("PO_summarytabpurchaseO").click();
		Assert.assertEquals(summaryvalidation("PO_sumtotalval"), totalValue,"Total Values are not correct");
		test.log(LogStatus.PASS, "Validating Total value");
		
		//validate total discount value
		Assert.assertEquals(summaryvalidation("PO_sumtotaldiscount"), TotalDiscount,"Total Discount Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Discount value");
		
		//validate total FOB value
		Assert.assertEquals(summaryvalidation("PO_sumtotalfobvalue"), TotalFOB, "Total FOB Values are not correct");
		test.log(LogStatus.PASS, "Validating Total FOB value");
		
		//validate total taxable value	
		Assert.assertEquals(summaryvalidation("PO_sumtotaltaxableval"), TotalTaxableValue, "Total Taxable Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Taxable value");
		
		//validate total tax value
		Assert.assertEquals(summaryvalidation("PO_sumtotaltaxval"), TotalTaxValue, "Total Tax Values are not correct");
		test.log(LogStatus.PASS, "Validating Total Tax value");
		
		//validate total expense charges
		Assert.assertEquals(summaryvalidation("PO_sumtotexpensecharge"), TotalExpenseCharges, "Total Expense Charges are not correct");
		test.log(LogStatus.PASS, "Validating Total Expenese Charges value");
		
		//validate total ITC TAX charges 
		Assert.assertEquals(summaryvalidation("PO_itctax"), ITCTax, "Total ITC TAX are not correct");
		test.log(LogStatus.PASS, "Validating Total ITC TAX value");
		
		//validate total TDS amount
		Assert.assertEquals(summaryvalidation("PO_sumtottdsamt"), TotalTDSAmt, "Total TDS Amount are not correct");
		test.log(LogStatus.PASS, "Validating Total TDS Amount value");
		
		//validate total PO value
		Assert.assertEquals(summaryvalidation("PO_sumtotPOvalue"), TotalPOValue, "Total PO Value are not correct");
		test.log(LogStatus.PASS, "Validating Total PO Amount value");

		Assert.assertEquals(purchaseordertest.saveTstruct(), true, "Oops! Purchase Order is not saved.");
		test.log(LogStatus.PASS, "Saving Purchase Order");

		//Purchase Order Saved (PO #-POHSR19000222)
		String successMessage = "Purchase Order Saved"; 
		Assert.assertEquals(purchaseordertest.getsavedMessage(successMessage,transno), true);
		test.log(LogStatus.PASS, "Success message after save", successMessage);
	}

	
	//**********************************************************************************************
	
	
	
	/*
	@Test(dataProvider = "PurchaseOrderData",priority=2)
	public void PurchaseorderWithItemAndExpenseUsingFillGrid(String transno,
			String branch,
			String supplier,
			String duedate,
			String frieghtValue,
			String insurenceValue,
			String itcforFrieght,
			String totalValue,
			String TotalDiscount,
			String TotalFOB,
			String TotalTaxableValue,
			String TotalTaxValue,
			String TotalExpenseCharges,
			String ITCTax,
			String TotalTDSAmt,
			String TotalPOValue) throws Exception{
		
		test.getTest().setName("Creating Purchase Order with Items And Expenses using Fillgrid) " + transno);
		PurchaseOrder purchaseordertest = new PurchaseOrder();
		// step:1
		Assert.assertEquals(purchaseordertest.VerifyPurchaseOrderPage("Purchase Order"), "Purchase Order");
		test.log(LogStatus.PASS, "Verifying the purchase order page", "Purchase Order");
		// step:2
		Assert.assertEquals(purchaseordertest.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Branch field", branch);
		// step:3
		Assert.assertEquals(purchaseordertest.selectvaluetoType(type), true,
				"Oops! Type is not selected.");
		test.log(LogStatus.PASS, "Selecting value to Type field", type);
		// step:4
		Assert.assertEquals(purchaseordertest.addValueToSupplierTextBox(supplier), true, "Oops! supplier cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Supplier field", supplier);
		// step:5
		Assert.assertEquals(purchaseordertest.addValueToDueDate(duedate), true, "Oops! DueDate cannot enter.");
		test.log(LogStatus.PASS, "Adding value to DueDate field", duedate);
		// step:6
		Assert.assertEquals(purchaseordertest.addValueToDepartmentTextBox(department), true,"Oops! department cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Department field", department);
		
		Assert.assertEquals(purchaseordertest.addFrieghtValue(frieghtValue), true, "Oops! Frieght Value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Frieght Value field", frieghtValue);
		
		Assert.assertEquals(purchaseordertest.addInsurenceValue(insurenceValue), true, "Oops! Insurence Value cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Insurence Value field", insurenceValue);
		
		Assert.assertEquals(purchaseordertest.selectITCFrieghtcheckbox(itcforFrieght), true, "Oops! ITC for Frieght cannot be selected");
		test.log(LogStatus.PASS, "selecting value to ITC for Frieght field", itcforFrieght);
		
		// step:7
		Assert.assertEquals(purchaseordertest.selectforeclosecheckbox(foreclose), true, "Oops! foreclose checkbox cannot be selected.");
		test.log(LogStatus.PASS, "forclose value",foreclose);

		//Assert.assertEquals(purchaseordertest.itemDetailsfromFillgrid(PurchaseRequisition.getPRvlaue()), true,"Oops! Item Details cannot be filled from fillgrid OR Data is not available for Fillgrid.");
		test.log(LogStatus.PASS, "Adding Item Details from Fillgid");

	
		
	}
	*/
	
	
	
	
	
	
	

	
	
	@DataProvider(name = "PurchaseOrderData")
	public Object[][] getData() {
		Xls_Reader xls_reader1 = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader1, "PurchaseOrder");
	}


}
