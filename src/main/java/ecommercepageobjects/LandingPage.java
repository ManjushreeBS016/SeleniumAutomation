package ecommercepageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement login;

	@FindBy(xpath="//div[@role=\"alert\"]")
	WebElement alert;
	
	public ProductCatalog LoginApplication(String userEmail, String userPassword) {
		waitForWebelement(email);
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		login.click();
		ProductCatalog productcatalog= new ProductCatalog(driver);
		return productcatalog;
	}

	public void gotoUrl() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getAlertMessage() {
		waitForWebelement(alert);
		String alertMessage=alert.getText();
		return alertMessage;
	}

}