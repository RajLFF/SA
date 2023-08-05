package testActions;

import org.openqa.selenium.WebDriver;

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
	DQAHomePage hm;
	DQAAlertFramePage alr;
	DQABookStorePage book;
	DQAElementPage ep;
	DQAInteractPage intr;
	DQAWidgetPage wid;
	DQAFormPage form;

	protected TestOperations() {

		/*
		 * String inst = ref;
		 * 
		 * switch (inst) {
		 * 
		 * case "alrt": alr = new DQAAlertFramePage(driverO); break;
		 * 
		 * case "bookR": book = new DQABookStorePage(driverO);
		 * 
		 * break; case "epR":
		 * 
		 * ep = new DQAElementPage(driverO);
		 * 
		 * break; case "intR": intr = new DQAInteractPage(driverO);
		 * 
		 * break; case "widR": wid = new DQAWidgetPage(driverO);
		 * 
		 * break; case "formR": form = new DQAFormPage(driverO);
		 * 
		 * break; default: hm = new DQAHomePage(driverO); break; }
		 */
	}

	public WebDriver launchBrowser() {

		return driverO = masterDriveInit();
	}

	// All Home Page Methods
	String DQ_Home_Page;

	public void clickElements() {
		hm = new DQAHomePage(driverO);
		hm.elements.click();
	}

	// All Home Element Page Methods
	String DQ_Element_Page;
}