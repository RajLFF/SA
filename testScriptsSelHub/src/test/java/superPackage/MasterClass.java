package SelHubTestScripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class MainDrive {

	WebDriver driver;

	public Properties pr;

	public void initBrowsesr() {

		pr = new Properties();

		File f = new File("\\src\\main\\java\\repository\\inputDataFile");

		FileInputStream fis = new FileInputStream(f);

		pr.load(fis);

		String browser = pr.getProperty("browserType");

		switch (browser) {
		
		case ff:
			
			WebDriverManager

			break;
		case ch:

			break;

		case ed:

			break;

		default:
			break;
		}
	}

}
