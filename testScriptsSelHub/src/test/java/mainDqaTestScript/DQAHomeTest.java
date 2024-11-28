package mainDqaTestScript;

import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testActions.TestOperations;

public class DQAHomeTest extends TestOperations {

	public static final boolean enable = false;
	WebDriver driverT;
	Properties prT;

	DQAHomeTest() {

		super();
	}

	@BeforeMethod(enabled = true)
	public void lanuchInstance() {

		driverT = launchBrowser();
		prT = masterPropFileData();
	}

	@AfterMethod(enabled = true)
	public void closeInstance() {

		if (driverT != null) {

			driverT.quit();
		}
	}

	/* All Test Cases... */

	@Parameters({ "enabled" })
	@Test(priority = 1, groups = { "Func", "Regression", "Skip", "Non_Func", }, enabled = true)
	public void valiate_ClickOn_element() {

		System.out.println("");
		clickElements();
		masterPause();
		// SoftAssert sa = new SoftAssert();
		assertTrue(true, "QA Home");
	}

	@Test(priority = 2, groups = { "Func", "Regression", "Skip", "Non_Func" }, enabled = false)
	public void validat_Text_Box() {

		clickElements();
		assertTrue(false, null);
	}
}
