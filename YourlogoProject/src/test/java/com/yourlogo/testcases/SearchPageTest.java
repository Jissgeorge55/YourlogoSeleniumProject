package com.yourlogo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yourlogo.pages.HomePage;
import com.yourlogo.pages.SearchPage;
import com.yourlogo.testbase.TestBase;

public class SearchPageTest extends TestBase {

	HomePage homepage;
	SearchPage searchpage;

	public SearchPageTest() {
		super();

	}

	@BeforeMethod
	public void setup() {
		initialization();

		homepage = new HomePage();
		searchpage = homepage.SearchProduct();

	}

	@Test(enabled = false, priority = 2)
	public void SortByLowestPriceTest() {
		boolean result = searchpage.sortByLowestPrice();

		Assert.assertEquals(result, true);
	}

	@Test(enabled = false, priority = 1)
	public void keywordFromSearchPageTest() {

		String ActualResult = searchpage.searchbox();

		String ExpectedResult = "\"T-SHIRT\"";
		Assert.assertEquals(ActualResult, ExpectedResult);
	}

	@Test(enabled = false)
	public void MoreButtonTest() {
		boolean ActualResult = searchpage.moreButton();

		Assert.assertEquals(true, ActualResult);

	}
	
	@Test(enabled = false)
	public void QuickViewLinkTest()
	{
		boolean ActualResult = searchpage.quickView();
		
		Assert.assertEquals(true, ActualResult);
		
	}
	
	@Test
	public void ProductsPerPageTest()
	{
		boolean ActualResult = searchpage.productsPerPage(7);
		Assert.assertEquals(true, ActualResult);
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
