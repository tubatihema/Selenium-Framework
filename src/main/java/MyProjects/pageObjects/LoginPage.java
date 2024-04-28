package MyProjects.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MyProjects.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
			//WebElement username = driver.findElement(By.id("userEmail"));
	//we can write the above line in this format also.
	@FindBy(id="userEmail")
	WebElement username;
			//WebElement userpass = driver.findElement(By.id("userPassword"));
	@FindBy(id = "userPassword")
	WebElement userpass;
			//WebElement submit = driver.findElement(By.id("login"));
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public void loginApp(String email,String password) {
		
		username.sendKeys(email);
		userpass.sendKeys(password);
		submit.click();
		
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		WaitForElementToAppear(errorMessage);
		return errorMessage.getText();
		
		
		
	}

}
