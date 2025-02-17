package ecommerce.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ecommerce.TestComponents.BaseTest;
import ecommercepageobjects.CartPage;
import ecommercepageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(groups = "LoginError")
	public void UnsuccessLogin()
	{
		landingPage.LoginApplication("star@gmail.com", "password");
		String Message=landingPage.getAlertMessage();
		AssertJUnit.assertEquals(Message, "Incorrect email or password.");
	}
	
	@Test 
	public void productErrorTest() throws InterruptedException {
		ProductCatalog productcatalog = landingPage.LoginApplication("Scarletrose@gmail.com", "India@123");

		List<WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(productName);
		CartPage cartpage = productcatalog.goToCart();

		Boolean match = cartpage.verifyProductDisplayed("OnePlus");
		Assert.assertFalse(match);
	}
}
