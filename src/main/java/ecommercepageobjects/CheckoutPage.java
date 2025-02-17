package ecommercepageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder=\"Select Country\"]")
	WebElement country;

	@FindBy(css = ".ta-results")
	WebElement searchResults;

	@FindBy(xpath = "(//button[contains(@class,\"ta-item\")])[2]/span")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	public void selectCountry(String countryName) {
		Actions act = new Actions(driver);
		act.sendKeys(country, countryName).build().perform();
		waitForWebelement(searchResults);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationpage=new ConfirmationPage(driver);
		return confirmationpage;
	}
}