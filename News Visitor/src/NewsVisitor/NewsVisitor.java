package NewsVisitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NewsVisitor {
	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",path+"\\src\\Drivers\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		WebDriver driver = new ChromeDriver(options);

		String baseUrl = "https://www.google.com";
		driver.get(baseUrl);

		FileInputStream fs = new FileInputStream(path+"\\src\\NewsVisitor\\newsUrl.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);

		//for (int k = 1; k <= 2; k++) {
			for (int j = 0; j < 51; j++) {
				Row row = sheet.getRow(j + 1);
				Cell cell = row.getCell(0);
				String cellval = cell.getStringCellValue();
				System.out.println(cellval);
				((JavascriptExecutor) driver).executeScript("window.open()");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(j));
				driver.get(cellval);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				for (int i = 0; i < 2; i++)
					js.executeScript("window.scrollBy(0,2000)");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		//}
		workbook.close();
		driver.quit();
	}
}