package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DQABookStorePage {

	WebDriver driver;

	public DQABookStorePage(WebDriver drive) {

		PageFactory.initElements(drive, this);
		this.driver = drive;
	}

	// Locating all WebElements objects...

	@FindBy(xpath = (""))
	public WebElement login;

	@FindBy(xpath = (""))
	public WebElement bookStore;

	@FindBy(xpath = (""))
	public WebElement profile;

	@FindBy(xpath = (""))
	public WebElement bookApi;

	@FindBy(id = ("userName"))
	public WebElement userName;

	@FindBy(id = ("password"))
	public WebElement pass;

	@FindBy(id = ("login"))
	public WebElement loginBtn;

	@FindBy(id = ("newUser"))
	public WebElement newUser;
}
