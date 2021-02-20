/**
 * 
 */
package com.agilebiz.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeSuite;

/**
 * @author virat
 *
 */
public class TestBase extends GenericMethods {

	InputStream stream;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	// public static Properties Repository= new Properties();

	@BeforeSuite
	public void LoadRepository_and_OpenBrowser() throws Exception
	{
		LoadRepositories();
		selectBrowser(Repository.getProperty("browser"));
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		implicitWait(10);
		driver.get(Repository.getProperty("url"));
	}


	public void LoadRepositories() throws IOException {
		
		stream = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Properties/Login.properties"));
		Repository.load(stream);
		
		stream = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Properties/purchase.properties"));
		Repository.load(stream);
		
		stream = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Properties/sales.properties"));
		Repository.load(stream);
		
		stream = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Properties/manufacturing.properties"));
		Repository.load(stream);
		
		stream = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Properties/fixedAssets.properties"));
		Repository.load(stream);
		
		stream = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Properties/accounts.properties"));
		Repository.load(stream);
		
		stream = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/DataSheet.properties"));
		Repository.load(stream);
	

	}

	/*
	 * @AfterClass public void closeBrowser() throws Exception{
	 * //LoginToApplication lt= new LoginToApplication(); //lt.LogoutfromApp();
	 * driver.quit(); }
	 */

}
