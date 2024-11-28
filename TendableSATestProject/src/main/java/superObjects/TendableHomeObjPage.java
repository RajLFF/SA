package superObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TendableHomeObjPage {

	WebDriver driver = null;

	public TendableHomeObjPage(WebDriver driverC) {

		this.driver = driverC;
		PageFactory.initElements(driverC, this);
	}

	@FindBy(xpath = "//div[@class='navbar7_container']/a/img[contains(@src,'https://cdn')]")
	public WebElement homeText;

	@FindBy(xpath = "//nav//a[contains(@class,'navbar') and normalize-space()='Contact']")
	public WebElement contactUs;

	@FindBy(xpath = "//div[contains(@class,'contact1_form-block w-form')]//input[@name='email']")
	public WebElement email;

	@FindBy(xpath = "//div[contains(@class,'contact1_form-block w-form')]//input[@name='firstname']")
	public WebElement fName;

	@FindBy(xpath = "//div[contains(@class,'contact1_form-block w-form')]//input[@name='lastname']")
	public WebElement lName;

	@FindBy(xpath = "//div[contains(@class,'contact1_form-block w-form')]//input[@name='company']")
	public WebElement companyName;

	@FindBy(xpath = "//div[contains(@class,'contact1_form-block w-form')]//textarea[@name='message']")
	public WebElement message;

	@FindBy(xpath = "//div[contains(@class,'contact1_form-block w-form')]//select[@name='message_type']")
	public WebElement messageType;

	@FindBy(xpath = "//div[contains(@class,'contact1_form-block w-form')]//button[@type='submit']")
	public WebElement submit;

	@FindBy(xpath = "//div[normalize-space()='Form Submission Failed']")
	public WebElement contactUsError;

	@FindBy(xpath = "//a[normalize-space()='Book a demo']")
	public WebElement bookDemo;

	@FindBy(xpath = "//a[normalize-space()='About' and @class='navbar7_link w-nav-link']")
	public WebElement aboutUs;

	@FindBy(xpath = "//a[normalize-space()='Products' and @class='navbar7_link w-nav-link']")
	public WebElement products;

	@FindBy(xpath = "//a[normalize-space()='Sectors' and @class='navbar7_link w-nav-link']")
	public WebElement sectors;

	@FindBy(xpath = "//a[normalize-space()='Content Hub' and @class='navbar7_link w-nav-link']")
	public WebElement contentHub;

	@FindBy(xpath = "//a[normalize-space()='Content Hub' and @class='navbar7_link w-nav-link']")
	public WebElement contact;
}
