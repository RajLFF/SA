package masterListeners;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import mainReports.MasterReports;
import superPackage.MasterMethods;

public class DqaListeners extends MasterMethods implements ITestListener {

	ExtentTest eTest;
	WebDriver driverL;
	ExtentReports dqaReportL = MasterReports.getDqaReport();

	public static Logger log;

	public void screenShotL(ITestResult result) {
		/*
		 * eTest.fail(result.getThrowable());
		 */
		driverL = null;
		String testName = result.getName();

		try {

			driverL = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driverT")
					.get(result.getInstance());
			String screenshotPath = masterSS(driverL, testName);
			eTest.addScreenCaptureFromPath(screenshotPath, testName);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {

			System.out.println("Field not found...");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	public void onStart(ITestContext context) {

		// log = LogManager.getLogger(TestLogger.class);

		System.out.println("Main Execution Started...");
	}

	public void onFinish(ITestContext context) {

		dqaReportL.flush();
		System.out.println("Main Execution Completed");
	}

	public void onTestStart(ITestResult result) {

		eTest = dqaReportL.createTest(result.getName());
		System.out.println("Test Case Started...");
	}

	public void onTestSuccess(ITestResult result) {

		screenShotL(result);
		System.out.println("Test Case Passed...");
		eTest.log(Status.PASS, MarkupHelper.createLabel("Passed Test Cases ==> ", ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {

		screenShotL(result);
		System.out.println("Test Case Failed...");
		eTest.log(Status.FAIL, MarkupHelper.createLabel("Failed Test Cases ==> ", ExtentColor.RED));
		eTest.getClass();
	}

	public void onTestSkipped(ITestResult result) {

		System.out.println("Test Case Skipped...");
		eTest.log(Status.SKIP, MarkupHelper.createLabel("Skipped Test cases ==> ", ExtentColor.CYAN));
	}

	/*
	 * @Override public void onTestFailedButWithinSuccessPercentage(ITestResult
	 * result) { }
	 * 
	 * @Override public void onTestFailedWithTimeout(ITestResult result) { }
	 */
}
