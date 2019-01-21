package com.yourlogo.utilities;

import java.util.ArrayList;

public class TestUtil  {
	
	static ExcelUtil reader;
	
	public static long PAGE_TIME_OUT = 60;
	public static long IMPLYSIT_WAIT = 30;
	
	
	
	public static ArrayList<Object[]> getDataFromExcel()
	{
		ArrayList<Object[]> mydata= new ArrayList<Object[]>();
		try {
			
		reader =new ExcelUtil("C:\\Users\\hp\\eclipse-workspace\\YourlogoProject\\src\\main\\java\\com\\yourlogo\\testdata\\Test_Data.xlsx");
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int rowNum=2; rowNum <= reader.getRowCount("Customer"); rowNum++)
		{
			String firstname= reader.getCellData("Customer", "FirstName", rowNum);	
			String lastname= reader.getCellData("Customer", "LastName", rowNum);
			String email= reader.getCellData("Customer", "Email", rowNum);
			String password= reader.getCellData("Customer", "Passsword", rowNum);
			String addname1= reader.getCellData("Customer", "Addname1", rowNum);
			String addname2= reader.getCellData("Customer", "Addname2", rowNum);
			String address= reader.getCellData("Customer", "Address", rowNum);
			String city= reader.getCellData("Customer", "City", rowNum);
			String postcode= reader.getCellData("Customer", "State", rowNum);
			String state= reader.getCellData("Customer", "Postcode", rowNum);
			String phone= reader.getCellData("Customer", "Phone", rowNum);
			
			Object obj[] = {firstname,lastname,email,password,addname1,addname2,address,city,postcode,state,phone};
			
			mydata.add(obj);
			
		}
		
return mydata;
		
		
	}
	
	
}

