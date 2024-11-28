package a_MasterPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.Select;

import superObjects.TendableHomeObjPage;

public class TendableMasterMethodPage {

	static WebDriver driver = null;
	Properties prM = null;

	public WebDriver masterOpenBroweser() {

		prM = masterFileData();
		String browserName = prM.getProperty("browser");
		System.out.println("Launched browser ==> " + browserName);

		switch (browserName) {

		case "Chrome":
			ChromeOptions co = new ChromeOptions();
			// co.addArguments("--headless");
			driver = new ChromeDriver(co);
			break;

		case "Edge":
			EdgeOptions eo = new EdgeOptions();
			// eo.setCapability("headless", false);
			driver = new EdgeDriver(eo);
			break;

		case "FF":
			FirefoxOptions fo = new FirefoxOptions();
			// fo.addArguments("--headless");
			driver = new FirefoxDriver(fo);
			break;

		case "Safari":
			SafariOptions so = new SafariOptions();
			// so.setCapability("headless", false);
			driver = new SafariDriver(so);
			break;

		default:
			System.out.println("Invalid browser... hence launching chome as default...");
			ChromeOptions co1 = new ChromeOptions();
			// co1.addArguments("--headless");
			driver = new ChromeDriver(co1);
			break;
		}

		driver.get(prM.getProperty("AppURL"));
		driver.manage().window().maximize();
		masterImplicitWait();
		return driver;
	}

	public Properties masterFileData() {

		prM = new Properties();
		try {

			File filePath = new File(System.getProperty("user.dir") + "/src/main/resources/pageRepo.properties");
			// System.out.println("File path is ==> " + filePath);
			FileInputStream fis = new FileInputStream(filePath);
			prM.load(fis);
		}

		catch (IOException e) {

			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		return prM;
	}

	public void masterImplicitWait() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	public void masterSelect(String value) {

		TendableHomeObjPage t = new TendableHomeObjPage(driver);
		Select s = new Select(t.messageType);

		List<WebElement> list = s.getAllSelectedOptions();
		System.out.println(list);

		switch (value) {

		case "1":
			masterPause();
			s.selectByValue("Marketing");
			break;

		case "2":
			s.selectByValue("Partnerships");
			break;

		case "3":
			s.selectByValue("Support");
			break;

		default:
			System.out.println("There is no matching value input...");
			break;
		}
	}

	public void masterPause() {

		try {

			Thread.sleep(3000);
		}

		catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}
	}
}
