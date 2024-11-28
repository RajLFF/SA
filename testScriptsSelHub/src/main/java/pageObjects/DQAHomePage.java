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

	@FindBy(xpath = ("[src=\"/images/Toolsqa.jpg\"]"))
	public WebElement toolQAText;

	@FindBy(css = ("img[title='Ad.Plus Advertising']")) // xpath = *[local-name()='svg' and @viewBox='0 0 448 512']
	public WebElement elements;

	@FindBy(xpath = (""))
	public WebElement forms;

	@FindBy(xpath = (""))
	public WebElement alertFrame;

	@FindBy(xpath = ("//h5[normalize-space()='Widgets']"))
	public WebElement widgets;

	@FindBy(xpath = (""))
	public WebElement interaction;

	@FindBy(xpath = (""))
	public WebElement bookStore;

	@FindBy(xpath = (""))
	public WebElement certification;
}
