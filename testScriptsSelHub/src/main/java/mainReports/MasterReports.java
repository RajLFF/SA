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

	public static void main(String[] args) throws IOException {

		fileData();

		ExtentReports masterReport = new ExtentReports();

		File fileReport = new File("./Reports/Demo_QA_Report.html");

		System.out.println("File Data ==> " + prR.getProperty("os"));

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(fileReport);

		masterReport.attachReporter(sparkReport); // Attached report

		masterReport.setSystemInfo("Executed By...", prR.getProperty("executeBy"));
		masterReport.setSystemInfo("Framework Designed By ==> ", prR.getProperty("developeBy"));
		masterReport.setSystemInfo("Executed O.S ==> ", prR.getProperty("os"));

		/*
		 * masterReport.flush(); Desktop.getDesktop().browse(new
		 * File("fileReport").toURI());
		 */
	}
}
