package com.taobao.ibalance.alimama;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.ThreadSleep;

public class AddTaoBaoKeEvent {

	public static void main(String[] args) {
		WebDriver driver = GetDriver.getChromeDriver();
		String url = "http://www.alimama.com/member/login.htm";

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.switchTo().frame("taobaoLoginIfr");

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
		element.sendKeys("");

		element = driver.findElement(By.name("TPL_password"));
		element.clear();
		element.sendKeys("");
		ThreadSleep.sleep(1000);

		element = driver.findElement(By.id("J_SubmitStatic"));
		element.submit();

		ThreadSleep.sleep(1000);
		driver.navigate().to("http://ad.alimama.com/myunion.htm#!/promotion/magpie_bridge/active/");

		element = new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.className("message-panel")).findElement(By.linkText("隐藏"));
			}
		});
		element.click();

		element = new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("magix_vf_items_query")).findElement(By.linkText("15%-20%"));
			}
		});
		element.click();

		ThreadSleep.sleep(3000);
		element = driver.findElement(By.className("table-container"));
		element.findElement(By.cssSelector(".checkbox.mr8")).click();

		ThreadSleep.sleep(3000);
		List<WebElement> elements = element.findElements(By.tagName("table"));
		element = elements.get(1);
		elements = element.findElements(By.tagName("tr"));
		String oriWin = driver.getWindowHandle();

		for (WebElement we : elements) {
			List<WebElement> wes = we.findElements(By.tagName("td"));
			element = wes.get(8).findElement(By.linkText("立即报名"));
			element.click();

			ThreadSleep.sleep(3000);
			element = driver.findElement(By.className("dialog-contentbox")).findElement(By.id("vf-dialog"))
					.findElements(By.tagName("p")).get(1).findElement(By.linkText("知道了"));
			element.click();

			ThreadSleep.sleep(3000);
			gotoNewWin(oriWin, driver);
			element = driver.findElement(By.id("body")).findElement(By.id("inmain"));
			element = element.findElement(By.id("vf-main")).findElement(By.xpath("//*[@class='normal-form featured']"));

			// .findElement(By.className("step-wrap"))
			// .findElement(By.id("vf-step-1")).findElement(By.id("brix_447")).findElement(By.id("featured"))
			// .findElement(By.className("featured-source"));
			// element =
			// element.findElement(By.cssSelector(".normal-form.featured"));
			// wes= element.findElements(By.tagName("li"));
			// System.out.println(wes.size());
			break;

		}

	}

	private static void gotoNewWin(String oriWin, WebDriver driver) {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (oriWin.equals(handle)) {
				continue;
			} else {
				driver.switchTo().window(handle);
			}
		}
	}

}
