/**
 * 
 */
package com.agilebiz.purchase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.purchase.DirectPurchase;
import com.agilebiz.Pages.purchase.PurchaseRequisition;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class PurchaseRequisitionTestScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
	
	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}
	
	@Test(dataProvider = "PurchaseRequisitionData",priority=1)
	public void PurchaseRequisitionWithItemDetails(String transno,
			String branch,
			String location,
			String department
			) throws Exception {
		test.getTest().setName("Creating Purchase Requisition with Item Details) " + transno);
		PurchaseRequisition purchasereqtest = new PurchaseRequisition();
		// step:1
		Assert.assertEquals(purchasereqtest.VerifyPurchaseRequisitionPage("Purchase Requisition"), "Purchase Requisition");
		test.log(LogStatus.PASS, "Verifying the Purchase Requisition page", "Purchase Requisition");
		// step:2
		Assert.assertEquals(purchasereqtest.addValueToBranchTextBox(branch), true, "Oops! branch cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Branch field", branch);
		// step:3
		Assert.assertEquals(purchasereqtest.addValueToLocationTextBox(location), true,
				"Oops! Location cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Location field", location);
		// step:4
		Assert.assertEquals(purchasereqtest.addValueToDepartmentTextBox(department), true,
				"Oops! department cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Department field", department);
		// step:5
		/*Assert.assertEquals(purchasereqtest.addValueToInventoryTypeTextBox(inventoryType), true, "Oops! Inventory Type cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Inventory Type field", inventoryType);
		//step: 6
		Assert.assertEquals(purchasereqtest.selectforeclosecheckbox(foreclose), true, "Oops! Foreclose values is not selected");
		test.log(LogStatus.PASS, "Selecting foreclose checkbox",foreclose);*/

		String itemsheetname = "PR_ItemDetails";
		Assert.assertEquals(purchasereqtest.itemDetailsGrid(xls_reader, itemsheetname, transno, "PR_gridAddbtn", "gridHd2"), true,
				"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");

		Assert.assertEquals(purchasereqtest.saveTstruct(), true, "Oops! Purchase requisition is not saved.");
		test.log(LogStatus.PASS, "Saving Purchase Requisition");

		String successMessage = "Purchase Requisition Saved";
		Assert.assertEquals(purchasereqtest.getsavedMessage(successMessage,transno), true);
		test.log(LogStatus.PASS, "Success message after save",successMessage);

	}
	
	
	@DataProvider(name = "PurchaseRequisitionData")
	public Object[][] getData() {
		Xls_Reader xls_reader1 = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader1, "PurchaseRequisition");
	}

}
