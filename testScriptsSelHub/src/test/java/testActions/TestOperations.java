package testActions;

import org.openqa.selenium.WebDriver;

import pageObjects.DQAHomePage;
import superPackage.MasterObjects;

public class TestOperations extends MasterObjects {

	WebDriver driverO;
	DQAHomePage hm = new DQAHomePage(driverO);

	public void clickOnInteracation() {

	}

	public void clickOnDropable() {

	}

	public void clickOn() {

		String title = driverO.getTitle();
		System.out.println("Page Title ==> " + title);

	}

	public void clickOnEl() {

		String title = driverO.getTitle();
		System.out.println("Page Title ==> " + title);
	}
}