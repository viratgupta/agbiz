package com.agilebiz.Pages.LoginToApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.distribution.WeibullDistribution;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.agilebiz.Utilities.Xls_Reader;

class Testingclass {

	static Xls_Reader xls_reader = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/agilebiz/Data/TestData.xlsx");

	public static void main(String arg[]) {
		
		swap("virat", "gupta");
		
		pancard("KKjhs8876R");
		
		int[] arrno= {432,45,4321,7654,999999,99999,98,999998};
		gethighestvalue(arrno);
		
		
		
	}
	
	//swap two string without using third variable.
	public static String swap(String a, String b){
		
		System.out.println("Before swap: a="+a+" b="+b);
		a=a+b;  //viratgupta
		b= a.substring(0, a.length()-b.length());
		a= a.substring(b.length(), a.length());
		
		System.out.println("after swap: a="+a+" b="+b);
		return a+" "+b;
		
	}
	 
	
	//validating PAN number 
	public static boolean pancard(String pan){
		
		/*String first5= pan.substring(0, 5);
		String last= pan.substring(pan.length()-1);
		String numbers= pan.substring(5, 9);
		
		if(first5.matches("^[a-zA-Z]+$")& last.matches("^[a-zA-Z]+$") & numbers.matches("^[0-9]+$")){
			
			System.out.println("PAN Card no= "+first5+numbers+last);
			
			return true;	
		}*/
		if(pan.matches("^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}+$")){
			
			System.out.println("PAN Number verified");
			return true;
		}
		else{
			System.out.println("PAN Number not correct");
			return false;
		}
		
		
	}
	
	// finding largest number usign array
	public static int gethighestvalue(int[] sal){
		
		int num=0;
		 //ArrayList<Integer> s = new ArrayList<Integer>();
		 for(int i=0;i<sal.length;i++){
			 if(sal[i]>num){
				 num=sal[i];
			 }
			 
		 }
		 System.out.println("largest no is :"+num);
		 return num;
		
	}
	
	public static int getlargestval(int findhighest){
		//int[] arr={2345,65,98765,98778,9879685};
		 ArrayList<Integer> alist = new ArrayList<Integer>(Arrays.asList(findhighest));
		System.out.println(alist.size());
		return alist.size();
		
	}
	public static void ExplicitWait(WebElement element){
		WebDriver driver= new ChromeDriver();
		WebElement header= new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(element));
	}
	public static void scrollupdown(){
		WebDriver driver= new ChromeDriver();
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(100,0)");	
	}
	public static void getAllLinks(){
		WebDriver driver= new ChromeDriver();
		List<WebElement> liste= driver.findElements(By.tagName("a"));
		for(WebElement webel: liste){
			System.out.println(webel);
		}
		
	}
	public static void getwindowhandle(){
		WebDriver driver= new ChromeDriver();
		String main=driver.getWindowHandle();
		driver.findElement(By.id("test")).click();
		Set<String> set= driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childwindow= itr.next();
			if(!main.equals(childwindow)){
				driver.switchTo().window(childwindow);
			}
		}
		driver.switchTo().window(main);
	}
	
	
	
	
	
}
