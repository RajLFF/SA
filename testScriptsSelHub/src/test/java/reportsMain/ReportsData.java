package reportsMain;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportsData {

	public static void main(String[] args) throws IOException {

		ExtentReports extentreports = new ExtentReports();

		File fileReport = new File(".\\reports\\selHubReport.html");

		ExtentSparkReporter esr = new ExtentSparkReporter(fileReport);

		extentreports.attachReporter(esr); // Attached report

		extentreports.setSystemInfo("Executed By...", "Raj Chavan");
		extentreports.setSystemInfo("Executed On...", "Window 10 OS");

		extentreports.flush();

		Desktop.getDesktop().browse(new File("fileReport").toURI());
	}

}
