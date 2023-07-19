package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DQAElementPage {

	WebDriver driver;

	public DQAElementPage(WebDriver drive) {

		PageFactory.initElements(drive, this);
		this.driver = drive;
	}

	// Locating all WebElements objects...

	@FindBy(xpath = (""))
	public WebElement text;

	@FindBy(xpath = (""))
	public WebElement chkBox;

	@FindBy(xpath = (""))
	public WebElement radioButton;

	@FindBy(xpath = (""))
	public WebElement webTables;

	@FindBy(xpath = (""))
	public WebElement buttons;

	@FindBy(xpath = (""))
	public WebElement links;

	@FindBy(xpath = (""))
	public WebElement brokenLink;

	@FindBy(xpath = (""))
	public WebElement uplAndDownLoad;

	@FindBy(xpath = (""))
	public WebElement dropable;

	@FindBy(xpath = (""))
	public WebElement dynamicProp;
}
