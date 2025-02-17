package ecommercepageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkout;

	public Boolean verifyProductDisplayed(String productName) {
		waitForElementClickable(checkout);
		Boolean result = cartProducts.stream().anyMatch(producttext -> producttext.getText().equalsIgnoreCase(productName));
		return result;
	}

	public CheckoutPage goToCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
		
	}
}
