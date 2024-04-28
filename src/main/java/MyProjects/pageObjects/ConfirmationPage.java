package MyProjects.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.github.dockerjava.api.model.Driver;

import MyProjects.AbstractComponents.AbstractComponent;
import dev.failsafe.internal.util.Assert;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//String ConfirmMessage =  driver.findElement(By.cssSelector(".hero-primary")).getText();
	@FindBy(css=".hero-primary")
	WebElement cnfrmText;
	
	public String ConfirmMessage() {
		
		WaitForElementToAppear(cnfrmText);
		String ConfirmText = cnfrmText.getText();
		return ConfirmText;
	}


}
