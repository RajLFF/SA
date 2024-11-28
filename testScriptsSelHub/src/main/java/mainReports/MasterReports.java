package mainReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class MasterReports {

	public static Properties prR;

	public static void fileData() {

		prR = new Properties();
		try {

			String fileDir = System.getProperty("user.dir");
			File path = new File(fileDir + "/src/main/java/repository/inputDataFile.properties");
			FileInputStream fis = new FileInputStream(path);
			prR.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
	}

	public static ExtentReports getDqaReport() {

		fileData();

		ExtentReports dqaReport = new ExtentReports();

		File reportPath = new File("./Reports/Demo_QA_Report.html");
		System.out.println("File Data ==> " + prR.getProperty("os"));

		// Creating Report & Config.
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		sparkReport.config().setReportName("DQA Test Summary Report");
		sparkReport.config().setDocumentTitle("Main DQA Document");
		dqaReport.attachReporter(sparkReport); // Attached report

		// Setting Information
		dqaReport.setSystemInfo("Test Cases Executed By...", "Raj Chavan");
		dqaReport.setSystemInfo("Framework Design & Develope By ==> ", prR.getProperty("developeBy"));
		dqaReport.setSystemInfo("Executed O.S ==> ", prR.getProperty("os"));
		dqaReport.setSystemInfo("Executed Browser ==> ", prR.getProperty("browser"));

		/*
		 * masterReport.flush(); Desktop.getDesktop().browse(new
		 * File("fileReport").toURI());
		 */
		return dqaReport;
	}
}
