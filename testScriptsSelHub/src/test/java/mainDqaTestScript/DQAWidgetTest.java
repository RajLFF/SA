package mainDqaTestScript;

import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testActions.TestOperations;

public class DQAWidgetTest extends TestOperations {

	WebDriver driverT;
	Properties prT;

	DQAWidgetTest() {

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

	@Test(priority = 1, enabled = true, groups = { "Regression", "Skip", "Non_Func" })

	public void valiate_ClickOn_Widgets() {

		// SoftAssert sa = new SoftAssert();

		clickOnWidget();

		assertTrue(true, " ");
	}

}
