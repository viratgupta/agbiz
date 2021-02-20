/**
 * 
 */
package com.agilebiz.Manufacturing;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Manufacturing.ProductionOrder;
import com.agilebiz.Pages.purchase.PurchaseOrder;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class ProductionOrderScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}

	@Test(dataProvider = "productionOrder",priority=1)
	public void productionOrder(String transno,
			String product,
			String bomid,
			String qty,
			String prodlocation,
			String fglocation
			) throws Exception {
		
		
		test.getTest().setName("Creating Production Order " + transno);
		ProductionOrder poddorder = new ProductionOrder();
		
		Assert.assertEquals(poddorder.VerifyProductionOrderPage("Production Order"), "Production Order");
		test.log(LogStatus.PASS, "Verifying the Production Order page", "Production Order");
		
		Assert.assertEquals(poddorder.enterproductionorderdate(), true, "Oops! date cannot enter.");
		test.log(LogStatus.PASS, "Adding value to production date field");
		
		Assert.assertEquals(poddorder.addValueToProductTextBox(product), true, "Oops! Product cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Product text field", product);
		
		Assert.assertEquals(poddorder.addValueToBOMIDTextBox(bomid), true, "Oops! BOM-ID cannot enter.");
		test.log(LogStatus.PASS, "Adding value to BOM-ID text field", bomid);
		
		Assert.assertEquals(poddorder.addProductionQty(qty), true, "Oops! Production Qty cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Production Qty text field", qty);
		
		Assert.assertEquals(poddorder.addValueToprodlocationTextBox(prodlocation), true, "Oops! Production Location cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Production Location text field", prodlocation);
		
		Assert.assertEquals(poddorder.addValueTofglocationTextBox(fglocation), true, "Oops! FG Location cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Production Location text field", fglocation);
		
		String itemsheetname = "ProdO_Itemdetails";
		Assert.assertEquals(poddorder.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");
		
		Assert.assertEquals(poddorder.saveTstruct(), true, "Oops! Production Order cannot be saved.");
		test.log(LogStatus.PASS, "Saving Production Order");
		
		String successMessage = "Production Order Saved";
		Assert.assertEquals(poddorder.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "productionOrder")
	public Object[][] getData() {
		Xls_Reader xls_readerprod = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_readerprod, "ProductionOrder");
	}

}
