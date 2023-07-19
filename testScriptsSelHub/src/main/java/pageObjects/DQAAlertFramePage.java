package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DQAAlertFramePage {

	WebDriver driver;

	public DQAAlertFramePage(WebDriver drive) {

		PageFactory.initElements(drive, this);
		this.driver = drive;
	}

	// Locating all WebElements objects...

	@FindBy(xpath = ("//*[name()='path' and contains(@d,'M16 132h41')]"))
	public WebElement broseWindow;

	@FindBy(xpath = ("//span[@class=\"text\" and text()='Droppable']"))
	public WebElement alerts;

	@FindBy(xpath = (""))
	public WebElement frames;

	@FindBy(xpath = (""))
	public WebElement nestedFrames;

	@FindBy(xpath = (""))
	public WebElement modalDialog;
}
