package com.taobao.ibalance;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetStockHistoryData_163 {

	public static void main(String[] args) {

		DesiredCapabilities dcaps = new DesiredCapabilities();
		dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"/Users/gubin/Downloads/selenium/phantomjs-2.1.1-macosx/bin/phantomjs");
		WebDriver driver = new PhantomJSDriver(dcaps);

		// System.setProperty("webdriver.chrome.driver",
		// "/Users/gubin/project/workspace/seleniumProject/MySel20Proj/dirver/chromedriver");
		// WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get("http://quotes.money.163.com/trade/lsjysj_002008.html");

		WebElement element = new WebDriverWait(driver, 60).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("year"));
			}
		});

		Select selectYear = new Select(element);
		int numYear = selectYear.getOptions().size();

		element = new WebDriverWait(driver, 60).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("season"));
			}
		});
		Select selectSeason = new Select(element);
		int numSeason = selectSeason.getOptions().size();

		for (int i = 0; i < numYear; i++) {
			for (int j = 0; j < numSeason; j++) {
				element = new WebDriverWait(driver, 60).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.name("year"));
					}
				});
				selectYear = new Select(element);
				selectYear.selectByIndex(i);

				element = new WebDriverWait(driver, 60).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.name("season"));
					}
				});
				selectSeason = new Select(element);
				selectSeason.selectByIndex(j);

				element = new WebDriverWait(driver, 60).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.className("search_btn"));
					}
				});
				element.submit();

				element = new WebDriverWait(driver, 60).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.className("table_bg001"));
					}
				});

				if (isContentAppeared(element, "tbody")) {
					List<WebElement> rows = element.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
					for (WebElement row : rows) {
						List<WebElement> cells = row.findElements(By.tagName("td"));
						for (WebElement cell : cells) {
							System.out.println(cell.getText());
						}
					}
				} else {
					continue;
				}
			}
		}

		driver.quit();

	}

	private static boolean isContentAppeared(WebElement element, String content) {
		boolean status = false;
		try {
			element.findElement(By.tagName("tbody"));
			status = true;
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

}
