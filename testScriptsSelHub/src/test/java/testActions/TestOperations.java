package testActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.DQAAlertFramePage;
import pageObjects.DQABookStorePage;
import pageObjects.DQAElementPage;
import pageObjects.DQAFormPage;
import pageObjects.DQAHomePage;
import pageObjects.DQAInteractPage;
import pageObjects.DQAWidgetPage;
import superPackage.MasterMethods;

public class TestOperations extends MasterMethods {

	WebDriver driverO;
	private WebElement ele;
	DQAHomePage hm;
	DQAAlertFramePage alr;
	DQABookStorePage book;
	DQAElementPage ep;
	DQAInteractPage intr;
	DQAWidgetPage wid;
	DQAFormPage form;

	public WebDriver launchBrowser() {

		hm = new DQAHomePage(driverO);
		wid = new DQAWidgetPage(driverO);

		return driverO = masterDriveInit();

	}

	// All Home Page Methods
	String DQ_Home_Page;

	public void clickElements() {

		ele = hm.elements;
		masterMovTo(ele);
		ele.click();
	}

	// All Home Widget Page Methods
	String DQ_Widger_Page;

	public void clickOnWidget() {

		hm.widgets.click();
	}

}