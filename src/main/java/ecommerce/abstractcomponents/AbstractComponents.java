package ecommerce.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommercepageobjects.CartPage;
import ecommercepageobjects.OrderPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement order;
	

	public void waitForWebelement(WebElement waitElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(waitElement));
	}

	public void waitForInvisibilityofWebElement(WebElement waitElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(waitElement));
	}
	
	public void waitForElementClickable(WebElement waitElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(waitElement));
	}
	
	public CartPage goToCart() {
		waitForElementClickable(cart);
		cart.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	public OrderPage goToOrdersPage() {
		waitForWebelement(order);
		order.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
}
