package mainDqaTestScript;

import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testActions.TestOperations;

public class DQAHomeTest extends TestOperations {

	WebDriver driverT;
	Properties prT;

	DQAHomeTest() {

		super();
	}

	@BeforeMethod
	public void lanuchInstance() {

		driverT = launchBrowser();
		prT = masterPropFileData();
	}

	@AfterMethod
	public void closeInstance() {

		if (driverT != null) {

			driverT.quit();
		}
	}

	/* All Test Cases... */

	@Test(priority = 1, enabled = true, groups = { "Func", "Regression", "Skip", "Non_Func" })
	public void valiate_ClickOn_element() {

		clickElements();
		masterPause();
		// SoftAssert sa = new SoftAssert();
		assertTrue(true, "QA Home");
	}

	@Test(enabled = true, groups = { "Func", "Regression", "Skip", "Non_Func" })
	public void validat_Text_Box() {

		clickElements();
		assertTrue(false, null);
	}
}
