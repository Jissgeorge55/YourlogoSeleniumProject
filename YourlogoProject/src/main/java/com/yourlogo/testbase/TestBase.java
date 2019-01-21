package com.yourlogo.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.yourlogo.utilities.TestUtil;
import com.yourlogo.utilities.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver event_driver;
	public static WebEventListener eventListener;
	
	
	public TestBase() 
	
	{
		try {
			
		prop=new Properties();
		InputStream ip = getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(ip);
		
//		URL url = getClass().getClassLoader().getResource("config.properties");
//		System.out.println(url.getPath());
			if (prop == null) {
				System.out.println("Prop is null");
			}
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
	}

	
	
   public static void  initialization() 
   
    {
	   String directory = System.getProperty("user.dir");

	String browsername=prop.getProperty("browser");
		
		  if (browsername.equals("chrome"))
		    {
			    // System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver_win32\\chromedriver.exe");
			     System.setProperty("webdriver.chrome.driver",directory +"\\src\\main\\resources\\driver\\chromedriver.exe");
			     
		    	 driver=new ChromeDriver();
		     }
		  
		  else if(browsername.equals("FF")) 
		  {
			  //System.setProperty("webdriver.gecko.driver","E:\\Selenium\\geckodriver-v0.21.0-win32\\geckodriver.exe");
			  System.setProperty("webdriver.gecko.driver",directory +"\\src\\main\\resources\\driver\\geckodriver.exe");
		    	 driver=new FirefoxDriver();
		  }
		  
		 
		 /* To run the Event listener class to get the action log in 
		  * console */
		  
		  event_driver=new EventFiringWebDriver(driver);
		  eventListener= new WebEventListener();
		  event_driver.register(eventListener);
		  driver=event_driver;
		  
		  
		  
		  driver.manage().window().maximize();
		  driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); 
		  
		  driver.get(prop.getProperty("url"));
		  
		  Actions act=new Actions(driver);
	
		  
	}
   
   public static void takescreenshot(String filename) throws IOException
   {
	   File file= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   
	   FileUtils.copyFile(file, new File("C:\\Users\\hp\\eclipse-workspace\\YourlogoProject"+
	   "\\test-output\\screenshots\\"+filename+".jpg"));
	   
	   
	 
   }
   
}

