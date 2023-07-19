package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DQAInteractPage {

	WebDriver driver;

	public DQAInteractPage(WebDriver drive) {

		PageFactory.initElements(drive, this);
		this.driver = drive;
	}

	// Locating all WebElements objects...

	@FindBy(xpath = (""))
	public WebElement drop;

	@FindBy(xpath = (""))
	public WebElement drag;

	@FindBy(xpath = (""))
	public WebElement selectable;

	@FindBy(xpath = (""))
	public WebElement resize;

	@FindBy(xpath = (""))
	public WebElement sortable;
}
