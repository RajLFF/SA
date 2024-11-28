package c_TestActions;

import static org.testng.Assert.assertTrue;

import java.net.URL;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.deque.axe.AXE;

import a_MasterPackage.TendableMasterMethodPage;
import superObjects.TendableHomeObjPage;

public class TestOperations extends TendableMasterMethodPage {

	static WebDriver driverO = null;
	private WebElement eleO = null;
	private TendableHomeObjPage t = null;
	Properties prO = null;
	private static final URL appURL = TestOperations.class.getResource("/axe.min.js");

	public WebDriver launchBrowser() {

		prO = masterFileData();
		driverO = masterOpenBroweser();
		t = new TendableHomeObjPage(driverO);
		return driverO;
	}

	String commonMethods;

	public void quitBrowser() {

		if (driverO != null) {

			driverO.quit();
		}
	}

	String HomePage_Methods;

	public void clickTendable() {

		t.homeText.click();
	}

	public void clickOnContactUs() {

		t.contactUs.click();
	}

	public void enterEmailId() {

		t.email.sendKeys(prO.getProperty("mailId"));
	}

	public void enterFName() {

		t.fName.sendKeys(prO.getProperty("first_Name"));
	}

	public void enterLName() {

		t.lName.sendKeys(prO.getProperty("last_Name"));
	}

	public void enterCompName() {

		t.companyName.sendKeys(prO.getProperty("company_Name"));
	}

	public void enterMessage() {

		t.message.sendKeys(prO.getProperty("null_Message"));
	}

	public void selectMsgType() {

		String valueO = "1";
		masterPause();
		masterSelect(valueO);
	}

	public String clickOnSubmitButton() {

		t.submit.click();
		String errorTxt = t.contactUsError.getText();
		return errorTxt;
	}

	public void clickOnBookDemo() {

		eleO = t.bookDemo;
		boolean status = eleO.isEnabled();
		eleO.click();
		if (status == true) {

			System.out.println("Request a Demo button is enabled.");

		} else {

			System.out.println("Request a Demo button is disabled.");
		}
	}

	public boolean verifyEachPageBookDemoFunc() {

		System.out.println("Request Demo Button Verification Start...");
		System.out.println();
		masterPause();

		for (int i = 0; i < 6; i++) {

			int value = i;
			switch (value) {

			case 0:
				eleO = t.homeText;
				System.out.println("Home Page Request Demo Button verified successfully.");
				System.out.println();
				eleO.click();
				break;

			case 1:
				eleO = t.aboutUs;
				System.out.println(eleO.getText() + " Page Request Demo Button verified successfully");
				System.out.println();
				eleO.click();
				break;

			case 2:
				eleO = t.products;
				System.out.println(eleO.getText() + " Page Request Demo Button verified successfully...");
				System.out.println();
				eleO.click();
				break;

			case 3:
				eleO = t.sectors;
				System.out.println(eleO.getText() + " Page Request Demo Button verified successfully...");
				System.out.println();
				eleO.click();
				break;

			case 4:
				eleO = t.contentHub;

				System.out.println(eleO.getText() + " Page Request Demo Button verified successfully...");
				System.out.println();
				eleO.click();
				break;

			case 5:
				eleO = t.contactUs;

				System.out.println(eleO.getText() + " Page Request Demo Button verified successfully...");
				System.out.println();
				eleO.click();
				break;

			default:
				System.out.println("No matching value found....");
				break;
			}
			clickOnBookDemo();
		}
		return true;
	}

	public void checkAccessiblityViolation() {

		JSONObject jsonResponse = new AXE.Builder(driverO, appURL).analyze();
		JSONArray violationRule = jsonResponse.getJSONArray("violations");

		if (violationRule.length() == 0) {

			System.out.println("No accesiblity violations found in app...");
		}

		else {

			AXE.writeResults("Tendable App Acesibliities Testing Violations Report ==>", jsonResponse);
			assertTrue(false, AXE.report(violationRule)); // Assert report
		}
	}

}
