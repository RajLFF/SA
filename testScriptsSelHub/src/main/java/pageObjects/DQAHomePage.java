package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DQAHomePage {

	WebDriver driver;

	public DQAHomePage(WebDriver drive) {

		PageFactory.initElements(drive, this);
		this.driver = drive;
	}

	// Locating all WebElements objects...

	@FindBy(xpath = ("//img[@alt='Selenium Online Training']"))
	public WebElement joinNow;

	@FindBy(xpath = ("//span[@class=\"text\" and text()='Droppable']"))
	public WebElement homeText;

	@FindBy(css = ("img[title='Ad.Plus Advertising']"))
	public WebElement elements;

	@FindBy(xpath = (""))
	public WebElement forms;

	@FindBy(xpath = (""))
	public WebElement alertFrame;

	@FindBy(xpath = (""))
	public WebElement wodgets;

	@FindBy(xpath = (""))
	public WebElement interaction;

	@FindBy(xpath = (""))
	public WebElement bookStore;

	@FindBy(xpath = (""))
	public WebElement certification;
}
