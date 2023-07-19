package mainReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class MasterReports {

	public static void main(String[] args) throws IOException {

		ExtentReports masterReport = new ExtentReports();

		File fileReport = new File("./reports/demoQAReport.html");

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(fileReport);

		masterReport.attachReporter(sparkReport); // Attached report

		masterReport.setSystemInfo("Executed By...", "Raj Chavan");
		masterReport.setSystemInfo("Executed On...", "Window 10 OS");

		masterReport.flush();

		Desktop.getDesktop().browse(new File("fileReport").toURI());
	}

}
