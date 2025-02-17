package ecommercepageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractcomponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class=\"card\"]")
	List<WebElement> products;

	@FindBy(css = ".mb-3")
	WebElement card;

	@FindBy(id = "toast-container")
	WebElement toastMessage;

	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy(css = ".card-body button:last-of-type")
	WebElement addProduct;
	

	By addtocart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> getProductList() throws InterruptedException {
		waitForWebelement(card);
		return products;
	}

	public WebElement getProductByName(String productName) throws InterruptedException {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		waitForElementClickable(addProduct);
		prod.findElement(addtocart).click();
		waitForWebelement(toastMessage);
		Thread.sleep(8);
		waitForInvisibilityofWebElement(spinner);
	}
}
