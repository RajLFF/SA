package d_TestScriptsSuit;

import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import c_TestActions.TestOperations;

public class TendableHomeTest extends TestOperations {

	public TendableHomeTest() {

		super();
	}

	static WebDriver driverT = null;
	Properties prT = null;

	@BeforeMethod(enabled = true)
	public void initBrower() {

		driverT = launchBrowser();
		prT = masterFileData();
	}

	@AfterMethod(enabled = true)
	public void closeBrowser() {

		quitBrowser();
	}

	@Test(enabled = true, priority = 2)
	public void tc1_ValidateContactUsFunctionality() {

		clickOnContactUs();
		enterEmailId();
		enterFName();
		enterLName();
		enterCompName();
		enterMessage();
		selectMsgType();
		String errMsg = clickOnSubmitButton();
		assertTrue((prT.getProperty("submitError").equals(errMsg)), "Test Case passed...");
	}

	@Test(enabled = true, priority = 1)
	public void tc2_VerifyHomePageMenuOptionsAccessiblity() {

		checkAccessiblityViolation();
	}

	@Test(priority = 3, enabled = true)
	public void tc3_validateBookDemoFuncOnAllPages() { // This Test Case cover functionality of all top level menus
														// also.

		boolean status = verifyEachPageBookDemoFunc();
		System.out
				.println("Apart from Request Demo, this Test Case cover functionality of all top level menus also.");
		assertTrue(status == true, "Test Case passed...");
	}

}
