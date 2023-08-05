package masterListeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import superPackage.MasterMethods;

public class DqaListeners extends MasterMethods implements ITestListener {

	ExtentTest test;
	ExtentReports reportL;
	WebDriver driverL;

	public void screenShotL(ITestResult result) {

		String testName = result.getName();

		try {

			driverL = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driverT")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {

			System.out.println("Field not found...");
			e1.printStackTrace();
		}

		try {

			String screenshotPath = masterSS(driverL, testName);
			test.addScreenCaptureFromPath(screenshotPath, testName);
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("Test Case Started...");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		screenShotL(result);
		System.out.println("Test Case Passed...");
		test.log(Status.PASS, MarkupHelper.createLabel("Passed Test Cases ==> ", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {

		screenShotL(result);
		System.out.println("Test Case Failed...");
		test.log(Status.FAIL, MarkupHelper.createLabel("Failed Test Cases ==> ", ExtentColor.RED));
		test.getClass();
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("Test Case Skipped...");
		test.log(Status.SKIP, MarkupHelper.createLabel("Skipped Test cases ==> ", ExtentColor.CYAN));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {

		System.out.println("Main Execution Started...");
	}

	@Override
	public void onFinish(ITestContext context) {

		System.out.println("Main Execution Completed");
		reportL.flush();
	}
}
