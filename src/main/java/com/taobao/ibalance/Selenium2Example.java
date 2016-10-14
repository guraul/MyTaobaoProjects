package com.taobao.ibalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example {

	public static void main(String[] args) {

		// DesiredCapabilities dcaps = new DesiredCapabilities();
		// dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		// "/Users/gubin/project/workspace/seleniumProject/MySel20Proj/dirver/phantomjs-2.1.1-macosx/bin/phantomjs");
		// WebDriver driver = new PhantomJSDriver(dcaps);

		System.setProperty("webdriver.chrome.driver",
				"/Users/gubin/Downloads/selenium/chromedriver");
		// ChromeOptions chromeOptions = new ChromeOptions();
		// chromeOptions.addArguments(Arrays.asList("--disable-logging"));
		WebDriver driver = new ChromeDriver(new ChromeDriverService.Builder().withSilent(true).build());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get("https://login.taobao.com/member/login.jhtml");
		sleep(2000);

		WebElement element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_Quick2Static"));
			}
		});
		element.click();

		element = new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("TPL_username"));
			}
		});
		element.clear();
		element.sendKeys("guraul");

		element = driver.findElement(By.name("TPL_password"));
		element.clear();
		element.sendKeys("");
		sleep(2000);

		element = driver.findElement(By.id("J_SubmitStatic"));
		element.submit();

		driver.navigate().to("http://zhitongche.taobao.com");
		String oriWin = driver.getWindowHandle();
		sleep(2000);

		driver.switchTo().frame("login_iframe");
		sleep(2000);
		element = driver.findElement(By.id("btn"));
		element.click();
		sleep(2000);

		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (oriWin.equals(handle)) {
				continue;
			} else {
				driver.switchTo().window(handle);
			}
		}

		for (WebElement we : driver.findElements(By.tagName("vframe"))) {
			if (we.getAttribute("id").equals("magix_vf_main")) {
				element = we;
				break;
			}
		}

		for (WebElement we : element.findElements(By.tagName("vframe"))) {
			if (we.getAttribute("id").equals("magix_vf_65530")) {
				element = we;
				break;
			}
		}

		for (WebElement we : element.findElements(By.className("cp"))) {
			sleep(2000);
			if ("其它".equals(we.getText())) {
				element = we;
				break;
			}
		}
		sleep(2000);
		element.click();

		driver.navigate()
				.to("https://subway.simba.taobao.com/#!/campaigns/standards/adgroups/items/add?campaignId=5495008");
		sleep(2000);
		for (int i = 0; i < 100; ++i) {
			System.out.println(i);
			pushSingleProduct(element, driver);
		}

		driver.quit();
	}

	private static void pushSingleProduct(WebElement element, WebDriver driver) {
		sleep(5000);
		element = driver.findElement(By.cssSelector(".search.fr.pr"));
		WebElement subElement = element.findElement(By.tagName("input"));
		subElement.sendKeys("九舞");
		subElement = element.findElement(By.tagName("a"));
		subElement.click();

		sleep(5000);
		element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.className("creation-items"));
			}
		});

		element = element.findElement(By.className("bp-table"));

		// subElement = element.findElement(By.tagName("thead"));
		// List<WebElement> thList = subElement.findElements(By.tagName("th"));
		// thList.get(1).click();
		// thList.get(1).click();

		sleep(5000);
		element = element.findElement(By.tagName("tbody"));
		List<WebElement> trList = element.findElements(By.tagName("tr"));
		trList.get(0).click();

		element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_J_vframe_template_title_0"));
			}
		});

		String name = convertStringArray(element.getAttribute("value").trim(), 20);
		element.clear();
		element.sendKeys(name);

		sleep(2000);
		element = driver.findElement(By.cssSelector(".btn.btn-orange.btn-size30"));
		element.click();

		element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.cssSelector(".pagination.sel-area"));
			}
		});
		element = element.findElement(By.tagName("a"));
		sleep(5000);
		element.click();

		sleep(5000);
		element = driver.findElement(By.cssSelector(".btn.btn-orange.btn-size30"));
		element.click();

		element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.linkText("推广宝贝"));
			}
		});
		element.click();
	}

	private static void sleep(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static String convertStringArray(String inputStr, int iInputLenth) {
		if (iInputLenth >= 1 && iInputLenth <= 32)// 分割长度
		{
			ArrayList<String> arrayList = new ArrayList<String>();
			String srcString = inputStr;
			if (srcString.length() > 0)// 不为空
			{
				char src[] = srcString.toCharArray();// 将字符串转成字符数组
				int len = src.length;// 字符数组长度
				StringBuilder sb = new StringBuilder();
				boolean isContinue = true;
				int ring = 1;// 用于清空计数
				for (int index = 0; isContinue; index++, ring++) {
					if (index == (len - 1))// 长度和Index相同时
					{
						sb.append(src[index]);
						int tmp_len = sb.length();
						for (; tmp_len < iInputLenth; tmp_len++) {
							sb.append("0");// 补零
						}
						arrayList.add(sb.toString());
						isContinue = false;// 不再继续
					} else {
						sb.append(src[index]);// 字符数组转成
						if (ring == iInputLenth)// 如果长度等于分割长度，则进行一次分割，将sb存入list，并清空sb，下一轮开始
						{
							ring = 0;
							arrayList.add(sb.toString());
							sb = new StringBuilder();
						}
					}
				}
			}
			if (arrayList.size() > 0) {
				return arrayList.get(0);
			}
		}
		return null;
	}
}
