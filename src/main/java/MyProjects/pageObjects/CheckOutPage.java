package MyProjects.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.github.dockerjava.api.model.Driver;

import MyProjects.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");

public void checkOut(String Country) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, Country).build().perform();
		WaitForElementToAppear(results);
		selectCountry.click();
		submit.click();
	}

}
