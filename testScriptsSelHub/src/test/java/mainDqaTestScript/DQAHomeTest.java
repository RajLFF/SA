package mainDqaTestScript;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testActions.TestOperations;

public class DQAHomeTest extends TestOperations {

	WebDriver driverT;

	public DQAHomeTest() {

		super();
	}

	@BeforeMethod
	public void lanuchBrowser() throws IOException {

		driverT = masterDriveInit();
	}

	@AfterMethod

	public void closeInstance() {

		if (driverT != null) {

			driverT.quit();
		}
	}

	@Test(priority = 1, enabled = true)

	public void valiate_Drop_Functionality() {

		clickOn();
		assertTrue(true, "QA Home");
	}
}
