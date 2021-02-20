/**
 * 
 */
package com.agilebiz.Manufacturing;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Manufacturing.ProductionMaterialReturn;
import com.agilebiz.Pages.Manufacturing.ProductionOrder;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class ProductionMaterialReturnScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}

	@Test(dataProvider = "productionOrder",priority=1)
	public void productionMaterialReturn(String transno,
			String prodOrderno,
			String totalretqty
			) throws Exception {
		
		
		test.getTest().setName("Creating Production Order " + transno);
		ProductionMaterialReturn pmr= new ProductionMaterialReturn();
		
		Assert.assertEquals(pmr.VerifyProductionMaterialReturnPage("Production Material Return"), "Production Material Return");
		test.log(LogStatus.PASS, "Verifying the Production Material Return page", "Production Material Return");
		
		Assert.assertEquals(pmr.enterproductionReturnDate(), true, "Oops! return date cannot enter.");
		test.log(LogStatus.PASS, "Adding value to return date field");
		
		Assert.assertEquals(pmr.addValueToProdOrderNoTextBox(prodOrderno), true, "Oops! Production order number cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Production order number text field", prodOrderno);
		
		String itemsheetname = "PMR_itemdetails";
		Assert.assertEquals(pmr.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");

		// SUMMARY VALIDATION
		getWebElement("PMR_summarytab").click();
		Assert.assertEquals(summaryvalidation("PMR_totreturnqty"), totalretqty, "Total Return Qty are not correct");
		test.log(LogStatus.PASS, "Validating Total Return Qty");
		
		Assert.assertEquals(pmr.saveTstruct(), true, "Oops! Production Material Return cannot be saved.");
		test.log(LogStatus.PASS, "Saving Production Material Return");
		
		String successMessage = "Production Material Return Saved";
		Assert.assertEquals(pmr.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "productionOrder")
	public Object[][] getData() {
		Xls_Reader xls_reader_pmr = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_pmr, "ProdMaterailReturn");
	}


}
