package com.agilebiz.login;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Utilities.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class UBLoginTestScripts extends TestBase {

	
	public void LoginTest() throws Exception {
		//test.getTest().setName("LogIn to Agilebiz Application");
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.UBLogin(), true);
		//test.log(LogStatus.PASS, "Successfully Login to AgileBiz Application");
		
		getWebElement("logoutheader").click();
		getWebElement("signoutbtn").click();
		getWebElement("confirmpop").click();
		driverwait(2);
		getWebElement("loginsession").click();
		
	}

	@Test
	public void contrun() throws Exception{
		boolean run=true;
		while(run){
			LoginTest();
		}
	}
}
