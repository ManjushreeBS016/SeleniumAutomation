package ecommercepageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[2]")
	List<WebElement> name;
	@FindBy(xpath="(//button[text()='View'])[1]")
	WebElement View;
	
	
	public Boolean validateProductName(String ProductName)
	{
		waitForElementClickable(View);
		Boolean match=name.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	

}
