package com.yourlogo.testcases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yourlogo.pages.CartPage;
import com.yourlogo.pages.HomePage;
import com.yourlogo.pages.LoginPage;
import com.yourlogo.pages.MyAccountPage;
import com.yourlogo.testbase.TestBase;

public class LoginPageTest extends TestBase
{
	HomePage homepage;
	LoginPage loginpage;
	MyAccountPage myaccountpage;
	CartPage cartpage;

	
	public LoginPageTest()
	{
		super();           //to call the constructor of the super class ie Base
	}
	
	@BeforeMethod
	public void setup()
	{
	initialization();
    homepage=new HomePage();
    
    loginpage=homepage.SignIn();
	}
	
	
	
	
//This test is to verify the title of the Login page
@Test (enabled = false)

public void VerifyLoginPagetitletest() 
	{
		
		String title= loginpage.title();
		Assert.assertEquals(title, "Login - My Store");
	}




//This test case is to verify whether user can login with valid credentials.
@Test (enabled = false)
public void ValidateLoginTest()
	{
		myaccountpage=loginpage.login(prop.getProperty("Username"),prop.getProperty("Password"));
		String MyAccountTitle=myaccountpage.pagetitle();
		String ExpectedTitle= "My account - My Store";
		Assert.assertEquals(MyAccountTitle, ExpectedTitle);
	}




//This test case is to verify whether user can successfully Logout.
@Test(enabled = false)
public void VerifySignOutTest()
	{
		boolean SignInButtonDisplay=loginpage.logout(prop.getProperty("Username"),prop.getProperty("Password"));
		
		Assert.assertEquals(SignInButtonDisplay, true);
	}




//This test case is to verify that user should not login with invalid credentials
@Test(enabled = false)
public void InvalidCredentialsTest()
	{
		String ErrorMessage = loginpage.invalidcredentials("ttesst@hhdj.com", "dscsdgcv");
		
		String ExpectedError="There is 1 error\r\n" + 
				"Authentication failed.";
		
		Assert.assertEquals(ErrorMessage, ExpectedError);
		}




//This test case is to verify that user should not able to enter invalid email 
//to create new customer
	
@Test(enabled = false)
public void validateInvalidCreateEmail() throws InterruptedException, IOException
	{
		String errorMsg=loginpage.CreateError();
		Assert.assertEquals(errorMsg, "Invalid email address.");
		takescreenshot ("login_error");
		
	}




//This test is to verify the cart link in Login page
	
@Test
public void VerifyCartLinkTest()
	{
		cartpage= loginpage.Cartlink();
		String PageTitle=driver.getTitle();
		
		String ExpectedTitle="Order - My Store";
		Assert.assertEquals(PageTitle, ExpectedTitle);
	}




//This test is to verify the search box in login page.
@Test
public void VerifySearchBox()
{
	loginpage.search("dresses");
	
	String ActualTitle=driver.getTitle();
	String ExpectedTitle="Search - My Store";
	
	Assert.assertEquals(ActualTitle, ExpectedTitle);
	
}


	
	
@AfterMethod 
public void teardown()
	{
		driver.quit();
	}
}
