package MyProjects.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import MyProjects.TestComponents.BaseTest;
import MyProjects.pageObjects.CartPage;
import MyProjects.pageObjects.ProductCatalouge;

public class ErrorValidation extends BaseTest{

	@Test(groups = {"ErrorHandling"},retryAnalyzer=MyProjects.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method 
		
		
		String userName = "tubatihema@gmail.com";
		String password = "Hemavarena@244";
		lp.loginApp(userName, password);
		Assert.assertEquals("Incorrect email password.",lp.getErrorMessage());
	}
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		
		lp.loginApp("tubatihema@gmail.com", "Hemavarenya@244");
		String ProductName = "ZARA COAT 3";
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		List<WebElement> products = productCatalouge.getProductList();
		//this will check all the products in the above list and gives the specific product which is equals to zara coat 3 
		productCatalouge.addItemToCart(ProductName);
		Thread.sleep(3000);
		productCatalouge.clickOnCart();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.checkItemsInCartPage("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}

