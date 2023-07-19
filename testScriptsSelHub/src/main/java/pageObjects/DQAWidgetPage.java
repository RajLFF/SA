package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DQAWidgetPage {

	WebDriver driver;

	public DQAWidgetPage(WebDriver drive) {

		PageFactory.initElements(drive, this);
		this.driver = drive;
	}

	// Locating all WebElements objects...

	@FindBy(xpath = (""))
	public WebElement accodian;

	@FindBy(xpath = (""))
	public WebElement autoComplete;

	@FindBy(xpath = (""))
	public WebElement datePick;

	@FindBy(xpath = (""))
	public WebElement slider;

	@FindBy(xpath = (""))
	public WebElement progressBar;

	@FindBy(xpath = (""))
	public WebElement tab;

	@FindBy(xpath = (""))
	public WebElement toolTip;

	@FindBy(xpath = (""))
	public WebElement menu;

	@FindBy(xpath = (""))
	public WebElement selectMenu;
}
