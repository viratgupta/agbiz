/**
 * 
 */
package com.agilebiz.Manufacturing;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Manufacturing.ProductionMaterialIssue;
import com.agilebiz.Pages.Manufacturing.ProductionOrder;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class ProductionMateriaIIssueScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}

	@Test(dataProvider = "productionIssueMaterial",priority=1)
	public void productionIssueMaterial(String transno,
			String productodr,
			String fromlocation,
			String totalreqqty,
			String totalissueqty
			) throws Exception {
		
		
		test.getTest().setName("Creating Production Issue Materal " + transno);
		ProductionMaterialIssue PMI= new ProductionMaterialIssue();
		
		Assert.assertEquals(PMI.VerifyProductionMaterialIssuePage("Production Material Issue"), "Production Material Issue");
		test.log(LogStatus.PASS, "Verifying Production Material Issue page", "Production Material Issue");
		
		Assert.assertEquals(PMI.enterproductionIssueDate(), true, "Oops! date cannot enter.");
		test.log(LogStatus.PASS, "Adding value to production issue date field");
		
		Assert.assertEquals(PMI.addValueToProdOrderNoTextBox(productodr), true, "Oops! Product order number cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Product order number text field", productodr);
		
		Assert.assertEquals(PMI.addValueTolocationTextBox(fromlocation), true, "Oops! Location number cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Location text field", fromlocation);
		
		String itemsheetname = "PMI_ItemDetails";
		Assert.assertEquals(PMI.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");
		
		/*String stockvaluation = "PMI_stockValuation";
		Assert.assertEquals(PMI.fillStockValuation(xls_reader, stockvaluation, transno, "gridHd2"), true,"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");*/
		
		//SUMMARY VALIDATION
		getWebElement("PMI_summarytab").click();
		Assert.assertEquals(summaryvalidation("PMI_smytotreqqty"), totalreqqty,"Total Required Qty are not correct");
		test.log(LogStatus.PASS, "Validating Total Required Qty");
		
		Assert.assertEquals(summaryvalidation("PMI_smytotissueqty"), totalissueqty,"Total Issued Qty are not correct");
		test.log(LogStatus.PASS, "Validating Total Issued Qty");
		
		Assert.assertEquals(PMI.saveTstruct(), true, "Oops! Production Material Issue cannot be saved.");
		test.log(LogStatus.PASS, "Saving Production Material Issue");

		String successMessage = "Production Material Issue Saved";
		Assert.assertEquals(PMI.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "productionIssueMaterial")
	public Object[][] getData() {
		Xls_Reader xls_readerprodmatissue = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_readerprodmatissue, "ProdMaterialIssue");
	}

}
