package MyProjects.Tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MyProjects.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method 
		
		String ProductName = "ZARA COAT 3";
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//login to cart
		driver.findElement(By.id("userEmail")).sendKeys("tubatihema@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hemavarenya@244");
		driver.findElement(By.id("login")).click();
		LoginPage lp = new LoginPage(driver);
		//add items to the cart
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		//this will check all the products in the above list and gives the specific product which is equals to zara coat 3 
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		WebElement product = null;
//		for(int i=0;i<products.size();i++) {
//			product = product.findElement(By.cssSelector("b"));
//			String produc = product.getText();
//			if(produc.equals("ZARA COAT 3")) {
//				WebElement prod = products.get(i);
//				prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//			}
//			else {
//			
//			}
//		
//		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		Actions a = new Actions(driver);
		
		driver.findElement(By.xpath("//button[normalize-space()='Buy Now']")).click();
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String ConfirmMessage =  driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
	}
			
		
		
	}

