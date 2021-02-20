/**
 * 
 */
package com.agilebiz.Manufacturing;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Pages.Manufacturing.ProductionEntry;
import com.agilebiz.Pages.Manufacturing.ProductionOrder;
import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.TestUtil;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */
public class ProductionEntryScripts extends TestBase {
	
	Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	@Test(priority=0)
	public void LoginTest() throws Exception {
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(),true);
	}

	@Test(dataProvider = "productionEntryData",priority=1)
	public void productionEntry(String transno,
			String productionOrder,
			String workcentre,
			String prodhrs,
			String totalexpamt,
			String rawmatcost,
			String rateperitem,
			String totworkcost,
			String totprodcost
			) throws Exception {
		
		
		test.getTest().setName("Creating Production Entry " + transno);
		ProductionEntry PE= new ProductionEntry();
		
		Assert.assertEquals(PE.VerifyProductionEntryPage("Production Entry"), "Production Entry");
		test.log(LogStatus.PASS, "Verifying the Production Entry page", "Production Entry");
		
		Assert.assertEquals(PE.enterproductionEntryDate(), true, "Oops! date cannot enter.");
		test.log(LogStatus.PASS, "Adding value to production entry date field");
		
		Assert.assertEquals(PE.addValueToProdOrderNoTextBox(productionOrder), true, "Oops! Production Order cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Production Order text field", productionOrder);
		
		Assert.assertEquals(PE.addValueToWorkCenterTextBox(workcentre), true, "Oops! Work Centre Name cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Work Centre Name text field", workcentre);
		
		Assert.assertEquals(PE.addValueToProdHrTextBox(prodhrs), true, "Oops! Production hours cannot enter.");
		test.log(LogStatus.PASS, "Adding value to Production Hours text field", prodhrs);
		
		Assert.assertEquals(PE.itemDetailsfromFillgrid(), true, "Oops! Item details cannot be filled.");
		test.log(LogStatus.PASS, "Adding item details using Fillgrid");
		
		String itemsheetname = "PE_InputItemDetails";
		Assert.assertEquals(PE.itemDetailsGrid(xls_reader, itemsheetname, transno, "gridHd2"), true,"Oops! Item Details cannot be entered.");
		test.log(LogStatus.PASS, "Adding Item Details and Validating the values");
		
		// SUMMARY VALIDATION
		getWebElement("PE_summarytab").click();
		Assert.assertEquals(summaryvalidation("PE_smyexpcharges"), totalexpamt, "Total Expense Amount are not correct");
		test.log(LogStatus.PASS, "Validating Total Expense Amount");
		
		Assert.assertEquals(summaryvalidation("PE_smyrawmatcost"), rawmatcost, "Total Raw Amount Cost are not correct");
		test.log(LogStatus.PASS, "Validating Total Raw Amount Cost");
		
		Assert.assertEquals(summaryvalidation("PE_smyrateperitm"), rateperitem, "Total rate per item is not correct");
		test.log(LogStatus.PASS, "Validating Total Rate Per Item");
		
		Assert.assertEquals(summaryvalidation("PE_smytotworlcentcost"), totworkcost, "Total Work Center Cost is not correct");
		test.log(LogStatus.PASS, "Validating Total Work Center Cost");
		
		Assert.assertEquals(summaryvalidation("PE_smytotprodcost"), totprodcost, "Total production Cost is not correct");
		test.log(LogStatus.PASS, "Validating Production Cost");
		
		Assert.assertEquals(PE.saveTstruct(), true, "Oops! Production Entry cannot be saved.");
		test.log(LogStatus.PASS, "Saving Production Entry");

		String successMessage = "Production Entry Saved";
		Assert.assertEquals(PE.getsavedMessage(successMessage, transno), true);
		test.log(LogStatus.PASS, "Success message after save");

	}
	

	@DataProvider(name = "productionEntryData")
	public Object[][] getData() {
		Xls_Reader xls_reader_pes = new Xls_Reader(
				System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");
		return TestUtil.getData(xls_reader_pes, "ProductionEntry");
	}


}
