package ecommerce.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ecommerce.TestComponents.BaseTest;
import ecommerce.TestComponents.Retry;
import ecommercepageobjects.CartPage;
import ecommercepageobjects.CheckoutPage;
import ecommercepageobjects.ConfirmationPage;
import ecommercepageobjects.OrderPage;
import ecommercepageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test (dataProvider = "getData", retryAnalyzer = Retry.class)
	public void purchaseOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{		
		
		ProductCatalog productcatalog = landingPage.LoginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(input.get("product"));
		CartPage cartpage = productcatalog.goToCart();

		Boolean match = cartpage.verifyProductDisplayed(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();

		checkoutpage.selectCountry("India");
		 ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		
		String confirmationMessage = confirmationpage.getConfirmMessage();
		AssertJUnit.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"purchaseOrder"}, retryAnalyzer = Retry.class)
	public void OrderHistory()
	{
		ProductCatalog productcatalog = landingPage.LoginApplication("Scarletrose@gmail.com", "India@123");
		OrderPage OrdersPage = productcatalog.goToOrdersPage();
		Boolean match=OrdersPage.validateProductName(productName);
		Assert.assertTrue(match);
	}
	
	@DataProvider 
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/ecommerce/data/SubmitOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
