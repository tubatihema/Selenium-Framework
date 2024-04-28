package MyProjects.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MyProjects.TestComponents.BaseTest;
import MyProjects.pageObjects.CartPage;
import MyProjects.pageObjects.CheckOutPage;
import MyProjects.pageObjects.ConfirmationPage;
import MyProjects.pageObjects.OrderPage;
import MyProjects.pageObjects.ProductCatalouge;


public class SubmitPage extends BaseTest{
	
	String ProductName = "ZARA COAT 3";
	String userName = "tubatihema@gmail.com";
	String password = "Hemavarenya@244";
	String ConfrimMessage = "THANKYOU FOR THE ORDER.";

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		//login to cart
		lp.loginApp(input.get("email"), input.get("password"));
		//add items to the cart
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		List<WebElement> products = productCatalouge.getProductList();
		//this will check all the products in the above list and gives the specific product which is equals to zara coat 3 
		productCatalouge.addItemToCart(input.get("product"));
		Thread.sleep(3000);
		productCatalouge.clickOnCart();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.checkItemsInCartPage(input.get("product"));
		Assert.assertTrue(match);
		cartPage.clickOnCheckOut();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.checkOut("india");
		ConfirmationPage confirmPage = new ConfirmationPage(driver);
		String ConfirmMessage = confirmPage.ConfirmMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(ConfrimMessage));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistortTest() {
		lp.loginApp(userName, password);
		OrderPage orderPage = lp.goToOrderPage();
		Assert.assertTrue(orderPage.checkItemsInOrderPage(ProductName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataMapper(System.getProperty("user.dir")+"//src//test//java//MyProjects//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "tubatihema@gmail.com");
//	map.put("password", "Hemavarenya@244");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "anshika@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
	
}

