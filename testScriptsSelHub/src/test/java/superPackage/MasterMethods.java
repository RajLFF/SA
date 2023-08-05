package superPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MasterMethods {

	WebDriver driverM;
	Properties prM;
	Actions act;
	WebDriverWait wait;

	public WebDriver masterDriveInit() {

		prM = masterPropFileData();
		System.out.println("URL from Prop File ==> " + prM.getProperty("url"));
		String browserType = prM.getProperty("browser");

		System.out.println("Launching Browser => " + browserType);
		System.out.println("Launch Option => " + prM.getProperty("launchOpt"));

		switch (browserType) {

		case "FF":

			if (prM.getProperty("launchOpt").equals("WDM")) {

				driverM = WebDriverManager.firefoxdriver().create();
			} else
				driverM = new FirefoxDriver();

			driverM.get(prM.getProperty("url"));
			break;

		case "Chrome":

			if (prM.getProperty("launchOpt").equals("WDM")) {

				driverM = WebDriverManager.chromedriver().create();
			} else
				driverM = new ChromeDriver();

			driverM.get(prM.getProperty("url"));
			break;

		case "Edge":

			if (prM.getProperty("launchOpt").equals("WDM")) {

				driverM = WebDriverManager.edgedriver().create();
			} else
				driverM = new EdgeDriver();

			driverM.get(prM.getProperty("url"));
			break;

		default:

			if (prM.getProperty("launchOpt").equals("WDM")) {

				driverM = WebDriverManager.chromedriver().create();
			} else
				driverM = new ChromeDriver();

			driverM.get(prM.getProperty("url"));
		}
		masterWinMax(driverM);
		masterPageTitle(driverM);
		masterImplWait(driverM);

		return driverM;
	}

	/* All Common Methods... */
	String Common_Methods;

	public Properties masterPropFileData() {

		prM = new Properties();

		try {

			String fileDir = System.getProperty("user.dir");
			File file = new File(fileDir + "/src/main/java/repository/inputDataFile.properties");
			FileInputStream fis = new FileInputStream(file);
			prM.load(fis);
		} catch (IOException e) {

			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return prM;
	}

	public void masterPause() {

		try {

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void masterWinMax(WebDriver drive) {

		drive.manage().window().maximize();
	}

	public void masterPageTitle(WebDriver drive) {

		String title = drive.getTitle();
		System.out.println("Page Title ==> " + title);
	}

	// Wait Methods...
	String All_Wait_Methods;

	public void masterImplWait(WebDriver drive) {

		drive.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	public void masterVisibleWait(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public void masterSelectWait(WebElement element) {

		wait.until(ExpectedConditions.elementToBeSelected(element));
		element.click();
	}

	public void masterClickWait(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void masterEnterText(WebElement element, String text) {

		element.click();
		element.clear();
		element.sendKeys(text);
	}

	public void masterMovTo(WebElement element) {

		act.moveToElement(element).perform();
	}

	public void masterRightClick(WebElement element) {

		act.contextClick(element).perform();
	}

	public void masterAlert(WebElement element) {

	}

	public String masterSS(WebDriver driver, String tsName) {

		String ssPath = System.getProperty("user.dir") + "/demoQaScreenshot/" + tsName + ".png";

		try {

			File sShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sShot, new File(ssPath));

		} catch (IOException e) {

			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return ssPath;
	}

	public void masterDropD(WebElement element) {
		Select sel = new Select(element);
		sel.selectByIndex(0);
	}

	public void masterJseScroll() {

		JavascriptExecutor jse = (JavascriptExecutor) driverM;
		jse.executeScript("window.scrollBy(0, 250)");
	}

	public void masterJseClick() {

		JavascriptExecutor jse = (JavascriptExecutor) driverM;
		jse.executeScript("arguments[0].click()");
	}

	public String masterWinHandles(WebDriver driveO, WebElement ele) {

		String parentW = driveO.getWindowHandle();
		System.out.println("Parent Window ==> " + parentW);

		ele.click();
		Set<String> allWin = driveO.getWindowHandles();
		Iterator<String> itr = allWin.iterator();

		while (itr.hasNext()) {

			String childW = itr.next();

			if (!childW.equals(parentW)) {

				driveO.switchTo().window(childW);
				System.out.println("Child Window ==> " + childW);
				masterWinMax(driveO);
				masterPageTitle(driveO);
			}
		}
		return parentW;
	}

	public void masterAlertHandle(WebElement ele, boolean stat) {

		ele.click();

		Alert alert = driverM.switchTo().alert();

		if (stat == true) {
			masterPause();
			alert.accept();

			System.out.println("Alert Accepted...");
		} else
			masterPause();
		alert.dismiss();
		System.out.println("Alert dismissed...");
	}
}
