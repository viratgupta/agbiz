/**
 * 
 */
package com.agilebiz.Pages.LoginToApplication;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import com.agilebiz.Utilities.*;

/**
 * @author virat
 *
 */
public class LoginToApplication extends TestBase {

	public void LoginToAgileBiz() throws Exception {

		getWebElement("username").sendKeys(Repository.getProperty("Email_id"));
		getWebElement("password").sendKeys(Repository.getProperty("password"));
		getWebElement("loginbutton").click();
		driverwait(2);
		try {
			driver.switchTo().frame(getWebElement("SecurityAlertFrame"));
			if (isElementPresent("SecurityAlertYES")) {
				getWebElement("SecurityAlertYES").click();
			}
		} catch (Exception e) {
			Reporter.log("Session Security Alert not executed");
		}
		driver.switchTo().frame(getWebElement("HomePageFrame"));
		if (!getWebElement("WelcomeHome").getText().substring(0, 6).equals("Welcome")) {
			throw new SkipException("Agilebiz Home Page couldn't open.");
		}
	}

	public void loginToAxpertX() throws Exception {

		getWebElement("SelectProject").sendKeys(Repository.getProperty("ProjectName"));
		getWebElement("AppUserName").sendKeys(Repository.getProperty("Username"));
		getWebElement("AppPassword").sendKeys(Repository.getProperty("Password"));
		getWebElement("LoginButton").click();
		implicitWait(3);
		if (getWebElement("AppTitle").isDisplayed()) {
			Reporter.log("Login is successful");
		} else {
			Reporter.log("Login FAIL");
		}
	}

	public void loginToAxpertXAfterChangePwd() throws Exception {

		getWebElement("SelectProject").sendKeys(Repository.getProperty("ProjectName"));
		getWebElement("AppUserName").sendKeys(Repository.getProperty("Username"));
		getWebElement("AppPassword").sendKeys(Repository.getProperty("NewPassword"));
		getWebElement("LoginButton").click();
		implicitWait(3);
		if (getWebElement("AppTitle").isDisplayed()) {
			Reporter.log("Login is successful");
		} else {
			Reporter.log("Login FAIL");
		}
	}

	public void InvalidLogin() throws Exception {
		getWebElement("SelectProject").clear();
		getWebElement("SelectProject").sendKeys(Repository.getProperty("ProjectName"));
		getWebElement("AppUserName").clear();
		getWebElement("AppUserName").sendKeys(Repository.getProperty("Username"));
		getWebElement("AppPassword").sendKeys(Repository.getProperty("Password"));
		getWebElement("LoginButton").click();
		implicitWait(3);
	}

	public void loginToAxpertX_userbased(String ProjectName, String UserName, String Password) throws Exception {

		getWebElement("SelectProject").clear();
		getWebElement("SelectProject").sendKeys(Repository.getProperty(ProjectName));
		getWebElement("AppUserName").sendKeys(Repository.getProperty(UserName));
		getWebElement("AppPassword").sendKeys(Repository.getProperty(Password));
		getWebElement("LoginButton").click();
		driverwait(2);
		try {
			// driver.switchTo().frame(getWebElement("SecurityAlertFrame"));
			if (isElementPresent("SecurityAlertYES")) {
				webElementWait(getWebElement("SecurityAlertYES")).click();;
				//getWebElement("SecurityAlertYES").click();
			}
		} catch (Exception e) {
			System.out.println("Session Security Alert not executed");
		}
		// driver.switchTo().frame(getWebElement("HomePageFrame"));

	}

	public String getSuccessfulMessage() throws Exception {
		// driver.switchTo().frame(getWebElement("HomePageFrame"));
		Reporter.log("Getting the welcome Message");
		try {
			if (!getWebElement("homepageheader").getText().substring(0, 7).equals("Welcome")) {
				throw new SkipException("Agilebiz Home Page couldn't open.");
			}
			System.out.println(getWebElement("WelcomeHome").getText().substring(0, 7));
			return getWebElement("WelcomeHome").getText().substring(0, 7);

		} catch (Exception ex) {
			return null;
		}

	}

	public boolean validateHomePage() throws Exception {
		// driver.switchTo().frame(getWebElement("HomePageFrame"));
		Reporter.log("Verify Home Page");
		try {
			if (!isElementPresent("homepageheader")) {
				throw new SkipException("Agilebiz Home Page couldn't open.");
			}
			driver.switchTo().frame(getWebElement("axpmiddleframe"));
			webElementWait(getWebElement("homepagemiddlediv"));
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public void LogoutfromApp() throws Exception {
		driver.switchTo().defaultContent();
		getWebElement("SettingMenu").click();
		implicitWait(2);
		getWebElement("Logout").click();
		getWebElement("LogoutConformButton").click();
		implicitWait(3);
		getWebElement("LoginAfterLogout").click();
	}
	
	public void UBloginToAxpert(String ProjectName, String UserName, String Password) throws Exception {

		getWebElement("SelectProject").clear();
		getWebElement("SelectProject").sendKeys(Repository.getProperty(ProjectName));
		getWebElement("AppUserName").sendKeys(Repository.getProperty(UserName));
		getWebElement("AppPassword").sendKeys(Repository.getProperty(Password));
		getWebElement("LoginButton").click();
		driverwait(2);
		/*try {
			// driver.switchTo().frame(getWebElement("SecurityAlertFrame"));
			if (isElementPresent("SecurityAlertYES")) {
				webElementWait(getWebElement("SecurityAlertYES")).click();;
				//getWebElement("SecurityAlertYES").click();
			}
		} catch (Exception e) {
			System.out.println("Session Security Alert not executed");
		}*/
		// driver.switchTo().frame(getWebElement("HomePageFrame"));

	}
	
	public boolean UBLogin() throws Exception {
		try {
			UBloginToAxpert("appdetails", "Email_id", "password");
			//validateHomePage();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean LoginAndValidateHomePage() throws Exception {
		try {
			loginToAxpertX_userbased("appdetails", "Email_id", "password");
			validateHomePage();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
