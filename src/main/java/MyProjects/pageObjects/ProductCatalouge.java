package MyProjects.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MyProjects.AbstractComponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By waitFor = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		WaitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String ProductName) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addItemToCart(String ProductName) {
		
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		WaitForElementToAppear(waitFor);
		WaitForElementToDisappear(spinner);
		
	}
	
	public void clickOnCart() {
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		cart.click();

	}

	

}
