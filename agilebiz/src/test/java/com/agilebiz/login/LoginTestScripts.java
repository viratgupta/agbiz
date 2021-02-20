package com.agilebiz.login;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilebiz.Pages.LoginToApplication.LoginToApplication;
import com.agilebiz.Utilities.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTestScripts extends TestBase {

	@Test(testName = "Login: Verify Agilebiz Login Successful.")
	public void LoginTest() throws Exception {
		test.getTest().setName("LogIn to Agilebiz Application");
		LoginToApplication loginPage = new LoginToApplication();
		Assert.assertEquals(loginPage.LoginAndValidateHomePage(), true);
		test.log(LogStatus.PASS, "Successfully Login to AgileBiz Application");

	}

}
