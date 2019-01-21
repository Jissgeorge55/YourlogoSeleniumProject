package com.yourlogo.pages;

import com.yourlogo.testbase.TestBase;

public class MyAccountPage extends TestBase {
	
	
	//---pageFactory--//
	
	
	
	public String pagetitle()
	{
		String mccnttitle= driver.getTitle();
		return mccnttitle;
	}

}