package com.planit.tests.testcases;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.planit.tests.base.BaseTest;
import com.planit.tests.pages.ShoppingCartPage;

public class ShoppingCartTest extends BaseTest{


	ShoppingCartPage shop = new ShoppingCartPage();
	SoftAssert softAssert = new SoftAssert();

	@Test(priority=3)
	public void testAddProductsToCart() {

		shop.navigateToShop();

	
		String product1 = BaseTest.getData("product1");
		int qty1 = Integer.parseInt(BaseTest.getData("product1Qty"));
		double price1 = shop.buyProductAndGetPrice(product1, qty1);

		String product2 = BaseTest.getData("product2");
		int qty2 = Integer.parseInt(BaseTest.getData("product2Qty"));
		double price2 = shop.buyProductAndGetPrice(product2, qty2);

		String product3 = BaseTest.getData("product3");
		int qty3 = Integer.parseInt(BaseTest.getData("product3Qty"));
		double price3 = shop.buyProductAndGetPrice(product3, qty3);
		
		shop.navigateCartPage();
		
		softAssert.assertTrue(shop.isProductPriceAndSubtotalCorrect(product1, price1));
		softAssert.assertTrue(shop.isProductPriceAndSubtotalCorrect(product2, price2));
		softAssert.assertTrue(shop.isProductPriceAndSubtotalCorrect(product3, price3));

		double expectedTotal = shop.getSumOfAllSubtotals();
		double actualTotal = shop.getTotalAmount();
		softAssert.assertEquals(actualTotal, expectedTotal, "Total mismatch with sum of subtotals");
	}


}
