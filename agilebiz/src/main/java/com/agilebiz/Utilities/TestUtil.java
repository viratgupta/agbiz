package com.agilebiz.Utilities;


public class TestUtil {

	public static Object[][] getData(Xls_Reader xls, String testCase){
		//count the rows and columns from the sheet and store into integer
		int rowCount=xls.getRowCount(testCase);
		int columnCount=xls.getColumnCount(testCase);
		
		//create the object arr and pass the rows and column in it
		Object[][] dataArr=new Object[rowCount-1][columnCount];
		
		//need to read the data starting from 1 row all columns		
		for(int rowNum=2; rowNum<=rowCount; rowNum++){
			for(int columnNum=0; columnNum<columnCount; columnNum++){
				String data=xls.getCellData(testCase, columnNum, rowNum);
				System.out.println(data);
				dataArr[rowNum-2][columnNum]=data;
			}
		}return dataArr;
	
	}

}
