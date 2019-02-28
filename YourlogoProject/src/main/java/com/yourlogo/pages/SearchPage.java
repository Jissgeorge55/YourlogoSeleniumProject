package com.yourlogo.pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yourlogo.testbase.TestBase;

public class SearchPage extends TestBase {

	JavascriptExecutor je = (JavascriptExecutor) driver;

	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line']")
	WebElement product1;

	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	WebElement addtocart;

	@FindBy(xpath = "//a[@class='btn btn-default button button-medium']")
	WebElement proceedTocheckout;

	@FindBy(id = "selectProductSort")
	WebElement SortDropdown;

	@FindBy(id = "search_query_top")
	WebElement searchbox;

	@FindBy(xpath = "//button[@class='btn btn-default button-search']")
	WebElement submit;

	@FindBy(xpath = "//span[contains(text(),'More')]")
	WebElement more;
	
	@FindBy(xpath = "//span[contains(text(),\"Quick view\")]")
	WebElement quickView;
	
	@FindBy(xpath ="//span[@class=\"heading-counter\"]")
	WebElement NumofProducts;
	
	

	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean sortByLowestPrice() {
		Select sort = new Select(SortDropdown);
		sort.selectByVisibleText("Price: Lowest first");

		List<WebElement> pricelist = driver.findElements(By.xpath("//span[@class='price product-price']"));

		int size = pricelist.size();
		System.out.println(size);

		// Array List to store price values
		List<String> priceRate = new ArrayList<String>();

		// storing each price text to Array list
		for (int i = 1; i < size; i++) {
			priceRate.add(pricelist.get(i).getText());
		}

		// remove unwanted texts, here unwanted element is showing as space
		for (int i = 1; i < size; i++) {
			priceRate.remove(new String());
		}

		System.out.println("Price from the page: " + priceRate);

		// making another copy of array list to compare
		List<String> sortedPrices = new ArrayList<String>(priceRate);

		// sorting sortedPrices in assending order
		Collections.sort(sortedPrices);

		System.out.println("Price Sorted " + sortedPrices);

		// true if the prices are sorted
		boolean result = sortedPrices.equals(priceRate);

		return result;

	}

	public CartPage addtocart1() throws InterruptedException {
		je.executeScript("arguments[0].scrollIntoView();", product1);
		Actions act = new Actions(driver);
		act.moveToElement(product1).build().perform();

		addtocart.click();
		// Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(proceedTocheckout));

		proceedTocheckout.click();

		return new CartPage();
	}

	public String searchbox() {
		searchbox.clear();
		searchbox.sendKeys("t-shirt");
		submit.click();

		String searchresult = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[1]")).getText();

		return searchresult;

	}

	public boolean moreButton()
	{
		je.executeScript("arguments[0].scrollIntoView();", product1);
		Actions act = new Actions(driver);
		act.moveToElement(product1).build().perform();
		
		more.click();
		
		//WebElement quantity= driver.findElement(By.id("quantity_wanted"));
		
		WebElement linkonPage = driver.findElement(By.xpath("//*[@id=\"columns\"]/div[2]/strong/a"));
		
		String text= linkonPage.getText();
		
		System.out.println(text);
		
		boolean result= linkonPage.isDisplayed();
		
		return result;	
		
	}
	
	public boolean quickView()
	{
		je.executeScript("arguments[0].scrollIntoView();", product1);
		Actions act = new Actions(driver);
		act.moveToElement(product1).build().perform();
		
		quickView.click();
		
		WebElement closeButton=driver.findElement(By.xpath("//a[@class=\"fancybox-item fancybox-close\"]"));
        boolean result= closeButton.isDisplayed();
		
		return result ;
	}
	
	
	public boolean productsPerPage(int num)
	{
		List<WebElement>allproducts= driver.findElements(By.xpath("//ul[@class=\"product_list row grid\"]"));
		
		int produtsperpage= allproducts.size();
		
		System.out.println(produtsperpage);
		
		if(num==produtsperpage)
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
		
		
	}
	
	
}
