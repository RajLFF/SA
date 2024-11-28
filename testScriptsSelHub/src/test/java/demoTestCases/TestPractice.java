package demoTestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPractice {

	WebDriver driver;
	Properties pr;

	@Test(enabled = true)
	public void demoTestCase() {

		launchApp();
		driver.findElement(By.xpath("(//div[@class='category-cards']//div[@class='card-up'])[4]")).click();
		String title = driver.getTitle();
		System.out.println("Page Title ==> " + title);
		driver.quit();
	}

	@Test(dataProvider = "logindata")
	public void testDataFunc() {

	}

	@Test(enabled = true, groups = "Regression")
	public void calenderDate() {

		Properties prc = propFileData();

		// Lauching App
		launchApp();

		// Click on Widget
		driver.findElement(By.xpath("(//div[@class='category-cards']//div[@class='card-up'])[4]")).click();

		WebElement dateEle = driver.findElement(By.xpath("//span[normalize-space()='Date Picker']"));
		jseScroll(driver);
		dateEle.click();
		WebElement calEle = driver.findElement(By.id("datePickerMonthYearInput"));
		calEle.click();
		String actualMY = driver.findElement(By.className("react-datepicker__current-month--hasMonthDropdown"))
				.getText();

		String desiredDate = prc.getProperty("userDate"); // Input date
		System.out.println("User input date is  ==> " + desiredDate);

		Calendar masterDate = Calendar.getInstance(); // Create current date instance
		SimpleDateFormat properDateFormat = new SimpleDateFormat(); // Format string to date format

		try {

			properDateFormat.setLenient(false); // Check for valid date
			masterDate.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(desiredDate)); // Set input date
			System.out.println("Actual user  input date is ==> " + masterDate);
			// Split date into DD - MM - YYYY
			int expectedDay = masterDate.get(Calendar.DAY_OF_MONTH);
			int expectedMonth = masterDate.get(Calendar.MONTH);
			int expectedYear = masterDate.get(Calendar.YEAR);
			// Printing Day, Month and Year.
			System.out.println("Expected Day is ==> " + expectedDay);
			System.out.println("Expected Month is ==> " + expectedMonth);
			System.out.println("Expected Year is ==> " + expectedYear);

			// Select actual Date
			String path = "//div[@class='react-datepicker__month']//div[contains(@class,'react-datepicker__week')]//div[not(contains(@class,'react-datepicker__day--outside-month'))and normalize-space()="
					+ expectedDay + "]";

			masterDate.setTime(new SimpleDateFormat("MMM yyyy").parse(actualMY));

			int acutalYear = masterDate.get(Calendar.YEAR);
			int actualMonth = masterDate.get(Calendar.MONTH);
			System.out.println("Actual Year ==> " + acutalYear);

			if (expectedYear > acutalYear) {

				while (expectedMonth > actualMonth || expectedYear > acutalYear) {

					driver.findElement(By.xpath("//button[normalize-space()='Next Month']")).click();
					String actualMYear = actualMY;
					masterDate.setTime(new SimpleDateFormat("MMM yyyy").parse(actualMYear));
					acutalYear = masterDate.get(Calendar.YEAR);
					actualMonth = masterDate.get(Calendar.MONTH);

					/*
					 * if (inputMonth == actualMonth || inputYear == acutalYear) break;
					 */
				}
			}

			else
				while (expectedMonth < actualMonth || expectedYear < acutalYear) {

					driver.findElement(By.xpath("//button[normalize-space()='Next Month']")).click();
					String actualMYear = actualMY;
					masterDate.setTime(new SimpleDateFormat("MMM yyyy").parse(actualMYear));
					acutalYear = masterDate.get(Calendar.YEAR);
					actualMonth = masterDate.get(Calendar.MONTH);
					/*
					 * if (inputMonth == actualMonth || inputYear == acutalYear) break;
					 */
				}
			driver.findElement(By.xpath(path)).click();

		} // Try Block

		catch (ParseException e) {

			System.err.println("Invalid Date is entered... Please check input date.");
			System.out.println(e.getMessage());

			try {

				throw new Exception("Invalid Date is entered... Please check input date.");
			}

			catch (Exception e1) {
				e1.printStackTrace();
			}
		} // Parent catch...
	}

	@Test
	public void webTableTest() {

		launchApp();
		driver.findElement(By.xpath("(//div[@class='category-cards']//div[@class='card-up'])[4]")).click();
		// driver.findElement(By.xpath(null)).click();
		// driver.findElement(By.xpath(null)).click();
		List<WebElement> webTableCell = driver.findElements(
				By.xpath("//div[contains(@class,'rt-table')]//div[@class='rt-tr-group']//div[@class='rt-td']"));

		for (WebElement tableElement : webTableCell) {
			System.out.println("All Table Cell Content ==> " + tableElement);

			String lastName = pr.getProperty("lName");
			if (tableElement.getText().equals(lastName)) {
				tableElement.click();
				System.out.println("Selected Element ==> " + tableElement);
			}
		}

		// Waiter wait = new Waiter();
	}

	public XSSFWorkbook retrieveXlFile() {

		String path = System.getProperty("user.dir");
		File filePath = new File(path + "/src/main/java/repository/Expense.xlsx");
		System.out.println("User dir path ==> " + path);
		XSSFWorkbook workbook = null;
		FileInputStream fis = null;

		try {

			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
		}

		catch (IOException e) {
			System.err.println("File not found...");
			System.out.println(e.getMessage());
		}

		try {

			fis.close();
		}

		catch (IOException e) {
			e.getMessage();
		}
		return workbook;
	}

	@Test
	public void xlDataFileHandling() {

		XSSFWorkbook workbook = retrieveXlFile();

		XSSFSheet sheet = workbook.getSheet("Expense");
		String monthData = sheet.getRow(2).getCell(2).getStringCellValue();
		CellAddress activeCl = sheet.getActiveCell();
		System.out.println("Active Cell ==> " + activeCl);
		double xlData = sheet.getRow(5).getCell(4).getNumericCellValue();
		System.out.println("Given Month ==> " + monthData + " Another cell value is ==>" + xlData);

		int actualRow = sheet.getPhysicalNumberOfRows();
		System.out.println("Actual physical row is ==> " + actualRow);

		XSSFRow row;
		XSSFCell cell;

		for (int i = 0; i < actualRow; i++) {

			row = sheet.getRow(i);
			int actualCell = row.getPhysicalNumberOfCells();

			// System.out.println("Actual physical cell is ==> " + actualCell);

			for (int j = 0; j < actualCell; j++) {

			} // 1st loop

		} // 2nd loop

		try {

			workbook.close();
		}

		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public String getXlPhysicalCellValue(XSSFCell cell) {

		switch (cell.getCellType()) {

		case NUMERIC:
			System.out.println("String...");
			return String.valueOf(cell.getNumericCellValue());

		case STRING:
			return String.valueOf(cell.getStringCellValue());

		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());

		case FORMULA:
			return String.valueOf(cell.getCellFormula());

		default:
			return String.valueOf(cell.getStringCellValue());
		}
	}

	public Properties propFileData() {

		pr = new Properties();

		String Path = System.getProperty("user.dir");

		try {
			File filepath = new File(Path + "/src/main/java/repository/inputDataFile.properties");

			FileInputStream fis = new FileInputStream(filepath);
			pr.load(fis);
			fis.close();
		}

		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return pr;
	}

	public void pause() {

		try {

			Thread.sleep(3000);
		}

		catch (InterruptedException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void jseScroll(WebDriver drive) {

		JavascriptExecutor jse = (JavascriptExecutor) drive;
		jse.executeScript("window.scrollBy(100, 350)"); // X and Y axis...
	}

	public void launchApp() {

		ChromeOptions co = new ChromeOptions();

		// co.addArguments("-headless");

		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://demoqa.com/");
		System.out.println("Application Launched Successfully...");
	}

	@DataProvider(name = "loginData")
	public void dataProvideTest() {

		Object[][] loginData = new Object[2][2];
		loginData[0][0] = pr.getProperty("name");
	}

	@Test
	public void arrayNumPatternPrint() {

		// Scanner sc = new Scanner(System.in);

		int[] a = { 1, 2, 3, 4, 5 };
		int an = a.length;
		System.out.println("Lenght of an Array is ==> " + an);
		for (int i = 0; i < an; i++) {

			int num = a[i];
			if (a[i] == num) {

				for (int j = 0; j < a[i]; j++) {

					System.out.println("Pattern of given no is ==> " + a[i]);
				} // J Loop
				System.out.println();
			} // Condition
		} // I Loop
	}

	@Test
	public void arrayDupNumRemove() {

		// take all no's, take no and store, compare with list, if not repeat then print
		int a[] = { 1, 2, 2, 4, 1 }, an = a.length, count = 0, n = 0, num = 0;

		System.out.println("Lenght of an Array is ==> " + an);

		for (int i = 0; i < an; i++) {

			System.out.println("Array is ==>" + a[i]);
			num = a[i];
			for (int j = 1; j < a.length; j++) {

				if (num == a[j]) {

					count++;
					if (count >= 2) {

						System.out.println("Number is ==> " + a[j]);
					}
				}
			}
		}
	}

}
