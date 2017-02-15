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
		element.sendKeys("guraul");

		element = driver.findElement(By.name("TPL_password"));
		element.clear();
		element.sendKeys("");
		ThreadSleep.sleep(1000);

		element = driver.findElement(By.id("J_SubmitStatic"));
		element.submit();

		ThreadSleep.sleep(3000);
		driver.navigate().to("http://ad.alimama.com/myunion.htm");
		ThreadSleep.sleep(3000);
		driver.navigate().refresh();

		ThreadSleep.sleep(3000);
		driver.navigate().to("http://ad.alimama.com/myunion.htm#!/promotion/magpie_bridge/active/");

		element = new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("magix_vf_items_query"));
			}
		});

		ThreadSleep.sleep(3000);
		element = element.findElement(By.linkText("10%-15%"));
		element.click();

		ThreadSleep.sleep(1000);
		List<WebElement> elements = driver.findElements(By.linkText("查看活动详情>>"));
		String oriWin = driver.getWindowHandle();
		System.out.println("oriWin" + ":" + oriWin);
		for (int i = 0; i < 2; i++) {
			WebElement we = elements.get(i);
			we.click();
			System.out.println(i + ":" + driver.getWindowHandle());
			Set<String> handles = driver.getWindowHandles();
			ThreadSleep.sleep(2000);
			if (i != 0) {
				for (String handle : handles) {
					if (oriWin.equals(handle)) {
						continue;
					} else {
						driver.switchTo().window(handle);
						we = driver.findElement(By.cssSelector(".path.current"));
						break;
					}
				}
			}

			for (String handle : handles) {
				if (oriWin.equals(handle)) {
					continue;
				} else {
					driver.switchTo().window(handle);
					driver.close();
				}
			}
			driver.switchTo().window(oriWin);
		}

	}

}
