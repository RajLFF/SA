package superPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MasterObjects {

	WebDriver driverM;
	Properties pr;
	Actions act;
	WebDriverWait wait;

	public WebDriver masterDriveInit() throws IOException {

		act = new Actions(driverM);
		wait = new WebDriverWait(driverM, Duration.ofSeconds(3));
		pr = new Properties();

		File file = new File("\\user.dir" + "\\src\\main\\java\\repository" + "\\inputDataFile");
		FileInputStream fis = new FileInputStream(file);
		pr.load(fis);
		String browserType = pr.getProperty("browser");

		switch (browserType) {

		case "FF":
			FirefoxOptions fo = new FirefoxOptions();
			fo.setHeadless(false);
			fo.addArguments(pr.getProperty("arg"));
			driverM = WebDriverManager.firefoxdriver().capabilities(fo).create();
			driverM.get(pr.getProperty("Url"));
			masterWinMax(driverM);
			masterPageTitle(driverM);
			masterImplyWait(driverM);

		case "Chrome":
			ChromeOptions co = new ChromeOptions();
			co.setHeadless(false);
			co.addArguments(pr.getProperty("arg"));
			driverM = WebDriverManager.chromedriver().capabilities(co).create();
			driverM.get(pr.getProperty("Url"));
			masterWinMax(driverM);
			masterPageTitle(driverM);
			masterImplyWait(driverM);

		case "Edge":
			EdgeOptions eo = new EdgeOptions();
			eo.setHeadless(false);
			eo.addArguments(pr.getProperty("arg"));
			driverM = WebDriverManager.edgedriver().capabilities(eo).create();
			driverM.get(pr.getProperty("Url"));
			masterWinMax(driverM);
			masterPageTitle(driverM);
			masterImplyWait(driverM);

		default:
			ChromeOptions co1 = new ChromeOptions();
			co1.setHeadless(false);
			co1.addArguments(pr.getProperty("arg"));
			driverM = WebDriverManager.chromedriver().capabilities(co1).create();
			driverM.get(pr.getProperty("Url"));
			masterWinMax(driverM);
			masterImplyWait(driverM);
		}
		return driverM;
	}

	public void masterWinMax(WebDriver drive) {

		drive.manage().window().maximize();
	}

	public void masterPageTitle(WebDriver drive) {

		drive.getTitle();
	}

	public void masterImplyWait(WebDriver drive) {

		drive.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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

	public String masterSS(WebDriver driver, String tsName) throws IOException {

		String ssPath = System.getProperty("user.dir") + "\\demoQaScreenshot\\" + tsName + ".png";
		File sShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sShot, new File(ssPath));
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
}
