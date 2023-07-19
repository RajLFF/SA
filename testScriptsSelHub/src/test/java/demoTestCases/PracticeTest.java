package demoTestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeTest {

	public WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		PracticeTest ptc = new PracticeTest();
		ptc.demoTest();
	}

	public void demoTest() throws InterruptedException {

		initBrowser();

		WebElement element = driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M16 132h41')]"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 250)");
		element.click();

		String title = driver.getTitle();
		System.out.println("Page Title ==> " + title);
		Thread.sleep(5000);
	}

	public void initBrowser() throws InterruptedException {

		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		co.setHeadless(false);
		// co.wait(1000);
		driver = WebDriverManager.chromedriver().capabilities(co).create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://demoqa.com/");
		System.out.println("Browser Launched Successfully...");
	}
}
