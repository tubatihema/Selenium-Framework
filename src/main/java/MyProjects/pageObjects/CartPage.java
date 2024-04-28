package MyProjects.pageObjects;

import java.util.List;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.github.dockerjava.api.model.Driver;

import MyProjects.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(xpath="//button[normalize-space()='Buy Now']")
	WebElement checkOut;
	
	
	public Boolean checkItemsInCartPage(String ProductName) {
		
		
		Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
		return match;
		
	}
	
	public void clickOnCheckOut() {
		
		WaitForElementToAppear(checkOut);
		checkOut.click();
		
	}
	
}
