package superPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.ScreenshotException;
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

			ChromeOptions co = new ChromeOptions();
			// String dpath = SeleniumManager.getInstance().getDriverPath(co,
			// true).driverPath;
			// System.out.println("Driver Path ===> " + dpath);
			// co.setBrowserVersion("115");
			// co.addArguments("--headless");
			// co.setBinary(prM.getProperty("cftPath")); // To run in dedicated CfT
			// browser...

			if (prM.getProperty("launchOpt").equals("WDM")) {

				driverM = WebDriverManager.chromedriver().capabilities(co).create();
			} else
				driverM = new ChromeDriver(co);
			break;

		case "Edge":

			if (prM.getProperty("launchOpt").equals("WDM")) {

				driverM = WebDriverManager.edgedriver().create();
			} else
				driverM = new EdgeDriver();
			break;

		default:

			if (prM.getProperty("launchOpt").equals("WDM")) {

				driverM = WebDriverManager.chromedriver().create();
			} else
				driverM = new ChromeDriver();
		}
		masterWinMax(driverM);
		driverM.get(prM.getProperty("url"));
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
		}

		catch (IOException e) {

			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		return prM;
	}

	public void masterPause() {

		try {

			Thread.sleep(3000);
		}

		catch (InterruptedException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
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

	public void masterAlert(WebElement element, String alertInput) {

		element.click();
		Alert eleAlert = driverM.switchTo().alert();
		System.out.println("Alert handle ==> " + eleAlert);

		switch (alertInput) {

		case "Accept":
			eleAlert.accept();
			break;

		case "Dismiss":
			eleAlert.dismiss();
			break;

		case "GetText":
			eleAlert.getText();
			break;

		case "InputText":
			eleAlert.sendKeys(prM.getProperty("alertText"));
			break;

		default:
			eleAlert.accept();
			break;
		}
	}

	public String masterSS(WebDriver driver, String tsName) {

		String ssPath = null;
		try {

			ssPath = System.getProperty("user.dir") + "/demoQaScreenshot/" + tsName + ".png";
			File sShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sShot, new File(ssPath));
		}

		catch (IOException | ScreenshotException e) {

			System.err.println("Screenshot can not be capture...");
			System.out.println(e.getMessage());
			System.err.println(e.getCause());
		}
		return ssPath;
	}

	public void masterDropD(WebElement element, String key) {

		Select sel = new Select(element);

		switch (key) {

		case "Index":
			int index = Integer.parseInt(prM.getProperty("drpDwnIndx"));
			sel.selectByIndex(index);
			break;

		case "Value":
			sel.selectByValue(prM.getProperty("drpDwnValue"));
			break;

		case "Text":
			sel.selectByVisibleText(prM.getProperty("drpDwnTxt"));
			break;

		default:
			sel.selectByVisibleText(prM.getProperty("drpDwnTxt"));
			break;
		}
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
		// driveO.switchTo().defaultContent();
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

	public void masterFrameSwitch(WebDriver driveM, String key, WebElement ele) {

		switch (key) {
		case "Name":
			driveM.switchTo().frame(prM.getProperty("frameName"));
			break;

		case "Index":
			driveM.switchTo().frame(prM.getProperty("frameIndex"));
			break;

		case "Element":
			driveM.switchTo().frame(ele);
			break;

		case "ParentFrame":
			driveM.switchTo().parentFrame();
			break;

		default:
			driveM.switchTo().defaultContent();
			break;
		}
	}

	public void masterDatePicker(WebElement calDate) {

		String date = prM.getProperty("userDate");
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
		dateformat.setLenient(false);

		try {

			Date parsedDate = dateformat.parse(date);
		}

		catch (ParseException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

}
