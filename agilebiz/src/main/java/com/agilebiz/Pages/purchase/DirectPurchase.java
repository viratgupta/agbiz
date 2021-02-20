/**
 * 
 */
package com.agilebiz.Pages.purchase;

import java.text.Format;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import com.agilebiz.Utilities.TestBase;
import com.agilebiz.Utilities.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author virat
 *
 */

public class DirectPurchase extends TestBase {

	private final By savemessage_xpath = By
			.xpath("//*[@class='shortMessageWrapper shortMessageWrapperInApp animated pulse']//child::div");

	public String VerifyDirectPurchasePage(String PageName) throws Exception {
		try {
			entervalueinglobalsearch(PageName);
			String headerValue = getWebElement("PageHeader").getText();
			if (!headerValue.equals("Direct Purchase")) {
				throw new SkipException("Drect Purchase Page couldn't open.");
			}
		} catch (Exception ex) {

		}
		return getWebElement("DP_pageheader").getText();
	}

	//************************************Details*****************************************

	public boolean addValueToBranchTextBox(String branch) {
		try {
			WebElement branchTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_branch")));
			branchTextBoxWebEelement.clear();
			branchTextBoxWebEelement.sendKeys(branch);
			webElementWait(getWebElement("autcompletedd"));
			branchTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToStoreTextBox(String storelocation) {
		try {
			WebElement storeTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_store")));
			storeTextBoxWebEelement.clear();
			storeTextBoxWebEelement.sendKeys(storelocation);
			webElementWait(getWebElement("autcompletedd"));
			storeTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectbilldate() {
		try {
			WebElement billdateWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_billdate")));
			billdateWebEelement.sendKeys(Keys.ENTER);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToSupplierTextBox(String supplier) {
		try {
			WebElement supplierTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_supplier")));
			supplierTextBoxWebEelement.clear();
			supplierTextBoxWebEelement.sendKeys(supplier);
			webElementWait(getWebElement("autcompletedd"));
			supplierTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToDepartmentTextBox(String department) {
		try {
			WebElement departmentTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_department")));
			departmentTextBoxWebEelement.clear();
			departmentTextBoxWebEelement.sendKeys(department);
			webElementWait(getWebElement("autcompletedd"));
			departmentTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToInvoiceNoTextBox(String invoiceno) {
		try {
			WebElement invoicenoTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_invoiceno")));
			invoicenoTextBoxWebEelement.clear();
			invoicenoTextBoxWebEelement.sendKeys(randomnumber()+invoiceno);
			Thread.sleep(2000);

		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addValueToInvoicedateTextBox() {
		try {
			WebElement invoicedateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_invoicedate")));
			invoicedateTextBoxWebEelement.clear();
			invoicedateTextBoxWebEelement.sendKeys(Keys.ENTER);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	//====================================================EXPENSE DETAILS ===========================================================
	
	public boolean addFrieghtValue(String FrieghtValue) {
		try {
			WebElement frieghtTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_frieghtValue")));
			frieghtTextBoxWebEelement.clear();
			frieghtTextBoxWebEelement.sendKeys(FrieghtValue);
			frieghtTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addInsurenceValue(String InsurenceValue) {
		try {
			WebElement insurenceTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_insuranceValue")));
			insurenceTextBoxWebEelement.clear();
			insurenceTextBoxWebEelement.sendKeys(InsurenceValue);
			insurenceTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean selectITCFrieghtcheckbox(String ITCforFrieght) {
		try {
			WebElement itcfrieghtWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_itcforfrieght")));
			if (ITCforFrieght.equalsIgnoreCase("T")) {
				itcfrieghtWebEelement.click();
			} else if (ITCforFrieght.equalsIgnoreCase("F") || ITCforFrieght.equalsIgnoreCase("")) {
				return true;
			}

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addingFOBValue(String FOBValue) {
		try {
			WebElement fobTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_totalFOBValue")));
			fobTextBoxWebEelement.clear();
			fobTextBoxWebEelement.sendKeys(FOBValue);
			fobTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		

		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean addinginvoiceValue(String invoiceValue) {
		try {
			WebElement invoiceTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_invoiceamt")));
			invoiceTextBoxWebEelement.clear();
			invoiceTextBoxWebEelement.sendKeys(invoiceValue);
			invoiceTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);

		} catch (Exception ex) {

		}
		return true;
	}
	
	// ******************************* ITEM_PARTICULAR**************************************
	
/*	public boolean addItemNamevalue(String itemname) {
		try {
			getWebElement("DP_itemParticular").click();
			driverwait(2);
			getWebElement("DP_itemgridbtn").click();
			WebElement itemnameTextBoxWebEelement = getWebElement("DP_itemname");
			itemnameTextBoxWebEelement.clear();
			itemnameTextBoxWebEelement.sendKeys(itemname);
			driverwait(3);
			itemnameTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;
	}

	public boolean addBillQtyvalue(String billQty) {
		try {
			// getWebElement("DP_itemParticular").click();
			WebElement billqtyTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_billqty")));
			billqtyTextBoxWebEelement.clear();
			billqtyTextBoxWebEelement.sendKeys(billQty);
			driverwait(3);
			billqtyTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
			return true;
		} catch (Exception ex) {
			return false;

		}
		
	}


	public boolean itemHSN(String hsn) {
		try {
			WebElement hsnTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_hsn")));
			hsnTextBoxWebEelement.clear();
			hsnTextBoxWebEelement.sendKeys(hsn);
			driverwait(3);
			hsnTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;	

	}

	public String itemtaxcategory() {
		try {
			WebElement taxcategoryTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_taxcategory")));
			String taxcatg = taxcategoryTextBoxWebEelement.getText();
			driverwait(3);
			taxcategoryTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
			return taxcatg;
		} catch (Exception ex) {
			return null;
		}

	}
	public String directpurchaseUOM() {
		try {
			WebElement uomTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_uom")));
			String taxcatg = uomTextBoxWebEelement.getText();
			driverwait(3);
			uomTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
			return taxcatg;
		} catch (Exception ex) {
			return null;
		}

	}

	public boolean itemtfreeqty(String itemfreeqty) {
		try {
			WebElement freeqtyTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_freeqty")));
			freeqtyTextBoxWebEelement.clear();
			freeqtyTextBoxWebEelement.sendKeys(itemfreeqty);
			driverwait(3);
			freeqtyTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public boolean itemtrate(String itemrate) {
		try {
			WebElement rateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_item_rate")));
			// rateTextBoxWebEelement.clear();
			// rateTextBoxWebEelement.sendKeys(itemrate);
			driverwait(3);
			rateTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public boolean calcitemtgrossvalue(String itemgrossvalue) {
		try {
			WebElement grossvalTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_grossvalue")));
			
			driverwait(3);
			grossvalTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public boolean itemtdisctype(String disctype) {
		try {
			WebElement disctypeTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_disctype")));
			selectByText(getWebElement("DP_disctype"), disctype);
			driverwait(3);
			disctypeTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public boolean itemtdiscrate(String itemdiscrate) {
		try {
			WebElement discrateTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_discrate")));
			discrateTextBoxWebEelement.clear();
			discrateTextBoxWebEelement.sendKeys(itemdiscrate);
			driverwait(3);
			discrateTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public Boolean itemtdiscount() {
		try {
			WebElement discountTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_discount")));
			// discountTextBoxWebEelement.clear();
			// discountTextBoxWebEelement.sendKeys(itemdiscrate);
			driverwait(3);
			discountTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public Boolean itemttaxableqty() {
		try {
			WebElement discountTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_itemtaxableqty")));
			// discountTextBoxWebEelement.clear();
			// discountTextBoxWebEelement.sendKeys(itemdiscrate);
			driverwait(3);
			discountTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public Boolean itemtaxvalue() {
		try {
			WebElement discountTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_itemtaxvalue")));
			// discountTextBoxWebEelement.clear();
			// discountTextBoxWebEelement.sendKeys(itemdiscrate);
			driverwait(3);
			discountTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public Boolean itemnetvalue() {
		try {
			WebElement discountTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_itemnetvalue")));
			// discountTextBoxWebEelement.clear();
			// discountTextBoxWebEelement.sendKeys(itemdiscrate);
			driverwait(3);
			discountTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}
	
	public boolean ItemDetails(Xls_Reader xls_reader, String dcSheetname, String noOfRows) throws Exception {
		try {
			getWebElement("DP_itemParticular").click();
			driverwait(2);

			int rowCount = xls_reader.getRowCount(dcSheetname);
			rowCount = Integer.parseInt(noOfRows) + 1;
			int columnCount = xls_reader.getColumnCount(dcSheetname);

			// need to read the data starting from 1 row all columns
			for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
				getWebElement("DP_itemgridbtn").click();
				driverwait(2);
				for (int columnNum = 0; columnNum < columnCount; columnNum++) {
					String data = xls_reader.getCellData(dcSheetname, columnNum, rowNum);
					// xpath for grid row column::::
					// //table[@id='gridHd2']/tbody/tr[1]/td[@data-focus][2]/div/input
					System.out.println(data);
					String elempath = "//table[@id='gridHd3']/tbody/tr[" + (rowNum - 1) + "]/td[@data-focus]["
							+ (columnNum + 1) + "]/div";
					WebElement gridtable = driver.findElement(By.xpath(elempath));
					Actions action = new Actions(driver);
					if (gridtable.getAttribute("data-type").equalsIgnoreCase("select")) {
						WebElement selele = driver.findElement(By.xpath(elempath + "/select"));
						selectByText(selele, data);
						selele.sendKeys(Keys.TAB);
						driverwait(1);
					} else {
						action.moveToElement(gridtable);
						
						action.sendKeys(data).build().perform();
						driverwait(3);
					}
					if ((columnNum == columnCount - 1)) {
						break;
					}
					action.sendKeys(Keys.TAB).perform();
					driverwait(1);

				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean calcgrossvalue() throws Exception
	{
		String rate= getWebElement("").getAttribute("value");
		double rateval= Double.parseDouble(rate.replaceAll(",",""));
		String bill= getWebElement("").getAttribute("value");
		double billval= Double.parseDouble(bill.replaceAll(",",""));
		String gross= getWebElement("").getAttribute("value");
		double grossval= Double.parseDouble(gross.replaceAll(",",""));
		double calgross= rateval*billval;
		if(calgross!=grossval)
		{
			return false;
		}
		return false;
	}*/
	
	public boolean validateandsetFillgridItem(Xls_Reader xls_reader1, String sheetname, String transno,
			String tableid) {

		try {
			addrecordingrid(xls_reader1, sheetname, transno, tableid, "FALSE");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/* ************************ EXPENSE_DETAILS******************************************

	public boolean addExpense(String expense) {
		try {
			//getWebElement("DP_expensetab").click();
			getWebElement("DP_expensegridAddbtn").click();
			driverwait(2);
			WebElement expenseTextBoxWebEelement = getWebElement("DP_expensename");
			//expenseTextBoxWebEelement.clear();
			expenseTextBoxWebEelement.sendKeys(expense);
			driverwait(3);
			expenseTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public boolean addAmountInExpense(String expenseamount) {
		try {
			WebElement examountTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_expenseamount")));
			examountTextBoxWebEelement.clear();
			examountTextBoxWebEelement.sendKeys(expenseamount);
			driverwait(3);
			examountTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}

	public boolean expenseaccount(String expenseaccount) {
		try {
			WebElement examountTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_Expenseaccount")));
			examountTextBoxWebEelement.clear();
			examountTextBoxWebEelement.sendKeys(expenseaccount);
			driverwait(3);
			examountTextBoxWebEelement.sendKeys(Keys.TAB);
			driverwait(2);
		} catch (Exception ex) {

		}
		return true;

	}
	
	public boolean ExpenseDetails(Xls_Reader xls_reader, String dcSheetname, String transno) throws Exception {
		try {
			int rowCount = xls_reader.getRowCount(dcSheetname);
			//rowCount= Integer.parseInt(noOfRows)+1;
			int columnCount = xls_reader.getColumnCount(dcSheetname);
			
			getWebElement("DP_expensegridAddbtn").click();
			driverwait(2);

			// need to read the data starting from 1 row all columns
			for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			
					String data0 = xls_reader.getCellData(dcSheetname, 0, rowNum);
					String nexttransval= xls_reader.getCellData(dcSheetname, 0, rowNum+1);
					if(data0.equalsIgnoreCase(transno))
					{
						for (int columnNum = 1; columnNum < columnCount;columnNum++) {
							
						if(columnNum!=columnCount)
						{
							String data = xls_reader.getCellData(dcSheetname, columnNum, rowNum);
							// xpath for grid row column::::
							// //table[@id='gridHd2']/tbody/tr[1]/td[@data-focus][2]/div/input
							System.out.println(data);
							WebElement gridtable= driver.findElement(By.xpath("//table[@id='gridHd2']/tbody/tr[" + (rowNum - 1) + "]/td[@data-focus]["
									+ (columnNum) + "]/div"));
							Actions action= new Actions(driver);
							action.moveToElement(gridtable).click();
							action.sendKeys(data).build().perform();
							driverwait(3);
							if(!nexttransval.equalsIgnoreCase(data0)&&(columnNum==columnCount-1))
							{
								break;
							}
							action.sendKeys(Keys.TAB).perform();
							driverwait(2);
						}
						
					}
				
						
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

	public boolean expensegrid(Xls_Reader xls_reader, String dcSheetname, String transno, String gridaddbtnele,
			String gridtableid) throws Exception {
		try {
			addrecordingrid(xls_reader, dcSheetname, transno, gridaddbtnele, gridtableid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}*/
	
	//*********************************SUMMARY***************************************
	
	public String GettingFOBvalue() {
		try {
			WebElement examountTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_gettotalFOBValue")));
			return examountTextBoxWebEelement.getAttribute("value");
		} catch (Exception ex) {
			return null;
		}

	}
	public String GettingTotalTaxablevalue() {
		try {
			WebElement ttaxabelvalTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_sumtottaxablevalue")));
			return ttaxabelvalTextBoxWebEelement.getAttribute("value");
		} catch (Exception ex) {
			return null;
		}

	}
	public String GettingTotalTaxvalue() {
		try {
			WebElement ttaxvalTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_sumtottaxvalue")));
			return ttaxvalTextBoxWebEelement.getAttribute("value");
		} catch (Exception ex) {
			return null;
		}

	}
	public String GettingTotalExpenseCharge() {
		try {
			WebElement ttlexpcaargeTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_sumtotexpensecharges")));
			return ttlexpcaargeTextBoxWebEelement.getAttribute("value");
		} catch (Exception ex) {
			return null;
		}

	}
	public boolean verifyingBillAfterDisc()
	{
		try {
			getWebElement("DP_summary").click();
			WebElement totalFOBTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_gettotalFOBValue")));
			String FOBvalue = totalFOBTextBoxWebEelement.getAttribute("value").replaceAll(",", "").trim();
			String Ttaxvalue = getWebElement("DP_sumtottaxvalue").getAttribute("value").replaceAll(",", "").trim();
			String Billvalue = getWebElement("DP_gettotalbillvalue").getAttribute("value").replaceAll(",", "").trim();
			implicitWait(2);
			// now calculating the total bill value after discount
			double fobval= Double.parseDouble(FOBvalue); 
			double ttaxval= Double.parseDouble(Ttaxvalue);
			double sumofobtax= fobval+ttaxval;
			if(sumofobtax!=Double.parseDouble(Billvalue))
			{
				return false;
			}
			return true;
		} catch (Exception ex) {
			
			return false;

		}
	}
	
	/*public boolean verifyingDiscount()
	{
		try {
			String grossval= getWebElement("").getText().replaceAll(",","");
			double grossv= Double.parseDouble(grossval);
			String discrate= getWebElement("").getText().replaceAll(".", "");
			double discrateval= Double.parseDouble(discrate);
			double disccal= grossv-(grossv*discrateval)/100;
			String discstring= getWebElement("").getText().replaceAll(",", "");
			double discval= Double.parseDouble(discstring);
			if(discval==disccal)
			{
				return true;
			}
		} catch (NumberFormatException e) {
			
	}
	}*/
	
	public boolean verifyingBillAfterDiscandExpense()
	{
		try {
			getWebElement("DP_summary").click();
			WebElement totalFOBTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_gettotalFOBValue")));
			String FOBvalue = totalFOBTextBoxWebEelement.getAttribute("value").replaceAll(",", "").trim();
			String Ttaxvalue = getWebElement("DP_sumtottaxvalue").getAttribute("value").replaceAll(",", "").trim();
			String Expensechargevalue = getWebElement("DP_sumtotexpensecharges").getAttribute("value").replaceAll(",", "").trim();
			String itcvalue = getWebElement("DP_sumitctax").getAttribute("value").replaceAll(",", "").trim();
			String Billvalue = getWebElement("DP_gettotalbillvalue").getAttribute("value").replaceAll(",", "").trim();
			implicitWait(2);
			// now calculating the total bill value after discount & Expense
			double fobval= Double.parseDouble(FOBvalue); 
			double ttaxval= Double.parseDouble(Ttaxvalue);
			double expchaval= Double.parseDouble(Expensechargevalue);
			double itcval= Double.parseDouble(itcvalue);
			double sumofobtax= fobval+ttaxval+expchaval+itcval;
			if(sumofobtax!=Double.parseDouble(Billvalue))
			{
				return false;
			}
			return true;
		} catch (Exception ex) {
			
			return false;

		}
	}
	public boolean gettingITCtaxvalue() {
		try {
			//itcvalue=String.format("%0.2f", itcvalue);
			getWebElement("DP_summary").click();
			WebElement ITCtaxTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_sumitctax")));
			String itctaxval=ITCtaxTextBoxWebEelement.getAttribute("value").replaceAll(",", "");
			double itcvtval= Double.parseDouble(itctaxval);
			//navigate to expense
			getWebElement("DP_expensetab").click();
			getWebElement("DP_roweditex").click();
			
			String amtstrval= getWebElement("DP_expamtrow1").getAttribute("value").replaceAll(",", "");
			double examt= Double.parseDouble(amtstrval);
			double fiveper= (examt*5.00)/100;
			double twlper= (examt*12.00)/100;
			if(!(itcvtval==fiveper) || (itcvtval==twlper))
			{
				
			}
			return true;
			
		} catch (Exception ex) {
			return false;
			
		}
		

	}
	

	
	public boolean getandSetFOBandBillvalue() {
		try {
			getWebElement("DP_summary").click();
			WebElement totalFOBTextBoxWebEelement = new WebDriverWait(driver, 200)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_gettotalFOBValue")));
			String FOBvalue = totalFOBTextBoxWebEelement.getAttribute("value");
			String Billvalue = getWebElement("DP_gettotalbillvalue").getAttribute("value");
			implicitWait(2);

			// now entering the total FOB and Total Bill value

			getWebElement("DP_totalFOBValue").clear();
			getWebElement("DP_totalFOBValue").sendKeys(FOBvalue);
			implicitWait(2);
			getWebElement("DP_invoiceamt").clear();
			getWebElement("DP_invoiceamt").sendKeys(Billvalue);
		} catch (Exception ex) {

		}
		return true;
	}
	
	public boolean DirectPurchaseSummary(String fOBValue, String taxableVal, String totaltax, String tdsval, String totalExpcharge, String itcTax, String totalBillValue)
	{
		try {
			String fobval= getWebElement("DP_gettotalFOBValue").getAttribute("value").replaceAll(",", "");
			int indexfob= fobval.indexOf(".");
			String fobvalue= fobval.substring(0, indexfob);
			Assert.assertEquals(fobvalue, fOBValue,"FOB Values are not correct");
			
			String taxableval= getWebElement("DP_sumtottaxablevalue").getAttribute("value").replaceAll(",", "");
			int indextaxable= taxableval.indexOf(".");
			String taxablevalue= taxableval.substring(0, indextaxable);
			Assert.assertEquals(taxablevalue, taxableVal,"Total Taxable Values are not correct");
			
			String ttaxval= getWebElement("DP_sumtottaxvalue").getAttribute("value").replaceAll(",", "");
			int indexttax= ttaxval.indexOf(".");
			String ttaxvalue= ttaxval.substring(0, indexttax);
			Assert.assertEquals(ttaxvalue, totaltax,"Total Tax Values are not correct");
			
			String tdsvalval= getWebElement("DP_sumtottdsvalue").getAttribute("value").replaceAll(",", "");
			int indextdsval= tdsvalval.indexOf(".");
			String tdsvalue= tdsvalval.substring(0, indextdsval);
			Assert.assertEquals(tdsvalue, tdsval,"Total TDS Values are not correct");
			
			String totalexpval= getWebElement("DP_sumtotexpensecharges").getAttribute("value").replaceAll(",", "");
			int indextexpval= totalexpval.indexOf(".");
			String texpensevalue= totalexpval.substring(0, indextexpval);
			Assert.assertEquals(texpensevalue, totalExpcharge,"Total Expense Values are not correct");
			
			String itctaxval= getWebElement("DP_sumitctax").getAttribute("value").replaceAll(",", "");
			int indexitctax= itctaxval.indexOf(".");
			String itctaxvalue= itctaxval.substring(0, indexitctax);
			Assert.assertEquals(itctaxvalue, itcTax,"ITC Tax Values are not correct");
			
			String tbillvalval= getWebElement("DP_gettotalbillvalue").getAttribute("value").replaceAll(",", "");
			int indextbillval= tbillvalval.indexOf(".");
			String tbillvalvalue= tbillvalval.substring(0, indextbillval);
			Assert.assertEquals(tbillvalvalue, totalBillValue,"Total Bill Values are not correct");
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ************************************** SAVE_FORM*******************************************

	public boolean saveTstruct() {
		try {

			WebElement saveWebEelement = new WebDriverWait(driver, 100)
					.until(ExpectedConditions.visibilityOf(getWebElement("DP_savebtn")));
			saveWebEelement.click();
			driverwait(2);

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean getsavedMessage(String successMessage,String transno) {
		try {
			WebElement SaveWebEelement = new WebDriverWait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated(savemessage_xpath));

			String actualMsgFromApp = SaveWebEelement.getText();
			if (actualMsgFromApp.contains(successMessage)) {
				// Sales Delivery Challan Saved (SO No.-SOHSR19000456)
				int i1 = actualMsgFromApp.indexOf("-");
				int i2 = actualMsgFromApp.indexOf(")");
				String Salesreturn = actualMsgFromApp.substring(i1 + 1, i2);
				// write data in excel
				String transvalue = transno.substring(5);
				int transval = Integer.parseInt(transvalue);
				//xls_reader.setCellData("SalesReturn", "invoiceno", transval + 1, Salesreturn);
				driverwait(4);
				return true;
			}
			
			

		} catch (Exception ex) {
			

		}
		return false;

	}

}
