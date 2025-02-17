package ecommerce.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import ecommerce.TestComponents.BaseTest;
import ecommercepageobjects.CartPage;
import ecommercepageobjects.CheckoutPage;
import ecommercepageobjects.ConfirmationPage;
import ecommercepageobjects.LandingPage;
import ecommercepageobjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productcatalog;
	public CartPage cartpage;
	public CheckoutPage checkoutpage;
	public ConfirmationPage confirmationpage;
	
@Given ("I landed on Ecommerce Page")
public void landed_on_EcommercePage() throws IOException {
	landingPage=launchApplication();
}

@Given ("^Logged in with username (.+) and passeord (.+)$")
public void Logged_with_username_and_passeord(String username, String password) {
	productcatalog = landingPage.LoginApplication(username, password);
}

@When ("^I add product (.+) to cart$")
public void add_product_to_cart(String productName) throws InterruptedException {
	List<WebElement> products = productcatalog.getProductList();
	productcatalog.addProductToCart(productName);
}

@And ("^Checkout (.+) and submit the order$")
public void Checkout_and_submit_order(String productName)
{
	cartpage = productcatalog.goToCart();

	Boolean match = cartpage.verifyProductDisplayed(productName);
	Assert.assertTrue(match);
	checkoutpage= cartpage.goToCheckout();

	checkoutpage.selectCountry("India");
	 confirmationpage= checkoutpage.submitOrder();
}

@Then ("{string} message is displayed on ConfirmationPage")
public void Confirmation_message_displayed(String string) {
	String confirmationMessage = confirmationpage.getConfirmMessage();
	AssertJUnit.assertTrue(confirmationMessage.equalsIgnoreCase(string));
	driver.close();
}

@Then("{string} nessage is displayed")
public void incorrect_credentials_error_message_displayed(String message) {
	String Message=landingPage.getAlertMessage();
	AssertJUnit.assertEquals(Message, message);
	driver.close();
}

}
