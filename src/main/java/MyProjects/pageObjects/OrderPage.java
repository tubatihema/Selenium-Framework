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

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts;
	
	
	
	public Boolean checkItemsInOrderPage(String ProductName) {
		
		
		Boolean match = orderedProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
		return match;
		
	}
	
}
