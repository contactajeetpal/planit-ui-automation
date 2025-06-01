package com.planit.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.Logger;
import java.util.*;
import com.planit.tests.base.BaseTest;
import com.planit.tests.utilities.Utils;

public class ShoppingCartPage extends BaseTest{

	private static final Logger logger = Utils.getLogger(ShoppingCartPage.class);


	public void buyProduct(String productName, int quantity) {
		for (int i = 0; i < quantity; i++) {
			By buyButton = By.xpath("//h4[text()='"+productName+"']/following::a[contains(text(),'Buy')][1]");
			Utils.waitForClickable(buyButton, 5).click();
			logger.info("Name of product the: '" + productName + "' Price is $" + buyButton);

		}
	}

	public double getUnitPrice(String productName) {
		By priceLocator = By.xpath("//h4[text()='"+productName +"']/following-sibling::p/span");
		String priceText = Utils.waitForElementVisible(priceLocator, 5).getText().replace("$", "").trim();
		logger.info("price of product: "+priceText);
		return Double.parseDouble(priceText);


	} 

	public double buyProductAndGetPrice(String productName, int quantity) {
		double unitPrice = getUnitPrice(productName);
		buyProduct(productName, quantity);
		logger.info("price of product: "+productName+" : unitPrice");
		return unitPrice;
	}


	public void navigateToShop() {
		Utils.waitForClickable(getBy("headerShop"), 10).click();
		logger.info("user action on shop ");
	}


	public void navigateCartPage() {
		Utils.waitForClickable(getBy("headerCart"), 10).click();
		logger.info("user action on cartPage");
	}


	public boolean isProductPriceAndSubtotalCorrect(String productName, double expectedUnitPrice) {
		List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='cart-item ng-scope']"));

		for (WebElement row : rows) {
			String nameofProduct = row.findElement(By.xpath("./td[1]")).getText().trim();

			if (nameofProduct.equalsIgnoreCase(productName)) {
				double actualProductPrice = Double.parseDouble(row.findElement(By.xpath("./td[2]")).getText().replace("$", ""));
				int actualQty = Integer.parseInt(row.findElement(By.xpath("./td[3]/input")).getAttribute("value").trim());
				double actualSubtotal = Double.parseDouble(row.findElement(By.xpath("./td[4]")).getText().replace("$", ""));
				logger.info("web sub total value: "+actualSubtotal);
				double expectedSubtotal = Math.round(expectedUnitPrice * actualQty * 100.0) / 100.0;
				logger.info("Product Price on web "+actualProductPrice);

				return actualProductPrice == expectedUnitPrice && actualSubtotal == expectedSubtotal;
			}
		}
		return false;
	}

	public double getSumOfAllSubtotals() {
		Utils.waitForElementVisible(getBy("cartSubtotals"), 10);
		double sum = 0.0;
		List<WebElement> subtotals = driver.findElements(getBy("cartSubtotals"));
		for (WebElement sub : subtotals) {
			sum = sum+Double.parseDouble(sub.getText().replace("$", "").trim());

		}
		logger.info(" web total of all product:"+sum);
		return Math.round(sum * 100.0) / 100.0;
	}

	public double getTotalAmount() {
		Utils.waitForElementVisible(getBy("cartSubtotals"), 10);
		WebElement totalElement = driver.findElement(getBy("carActualtotals"));
		String totalText = totalElement.getText().replace("Total:", "").trim();
		logger.info("get the value of total from web nav bar"+totalText);
		return Double.parseDouble(totalText);
	}


	// Reads product name and quantity from data.properties and adds the product
	public void buyFromProperty(String productKey, String quantityKey) {
		String productName = BaseTest.getData(productKey);
		int quantity = Integer.parseInt(BaseTest.getData(quantityKey));
		logger.info("Buying product from property: " + productName + " with quantity: " + quantity);
		buyProduct(productName, quantity);
	}

}
