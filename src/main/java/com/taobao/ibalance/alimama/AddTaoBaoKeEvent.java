package com.taobao.ibalance.alimama;

import java.util.ArrayList;
import java.util.List;

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

		ThreadSleep.sleep(30000);
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
		int num = 0;
		List<String> paths = new ArrayList<>();
		ThreadSleep.sleep(3000);
		for (WebElement we : elements) {
			paths.add(we.findElement(By.className("operation")).findElement(By.tagName("a")).getAttribute("href"));
		}
		for (String path : paths) {
			if (num > 10) {
				break;
			}
			driver.navigate().to(path);

			ThreadSleep.sleep(10000);
			for (WebElement we1 : driver.findElement(By.id("body")).findElement(By.id("inmain"))
					.findElement(By.id("vf-main")).findElement(By.className("step-wrap"))
					.findElement(By.id("vf-step-1")).findElement(By.className("featured-cnt"))
					.findElements(By.className("item"))) {
				we1.findElement(By.tagName("img")).click();
			}

			driver.findElement(By.linkText("下一步")).click();

			ThreadSleep.sleep(3000);
			element = driver.findElement(By.id("vf-main")).findElement(By.className("step-wrap"))
					.findElement(By.id("vf-step-2")).findElement(By.className("table-container"));

			ThreadSleep.sleep(3000);
			double min = Double.valueOf(element.findElement(By.id("commission_tables_body"))
					.findElements(By.tagName("tr")).get(0)
					.findElement(By.cssSelector(".input.magpie_bridge_commission_input")).getAttribute("data-minrate"));

			if (min <= 20) {
				ThreadSleep.sleep(3000);
				element.findElement(By.className("table-head-fix")).findElement(By.id("commission_tables_head"))
						.findElement(By.className("check-trigger")).click();
				element.findElement(By.className("table-head-fix")).findElement(By.id("J_commission_editSome")).click();
				ThreadSleep.sleep(3000);
				element.findElement(By.className("table-head-fix")).findElement(By.id("J_set_all_commission"))
						.sendKeys("20");
				element.findElement(By.className("table-head-fix")).findElement(By.linkText("确定")).click();
				element = driver.findElement(By.id("vf-main")).findElement(By.className("step-wrap"))
						.findElement(By.id("vf-step-2"))
						.findElement(By.cssSelector(".form-line.line-submit.clearfix.mt40"));
				element.findElement(By.linkText("完成")).click();
				// //
				// driver.findElement(By.id("vf-main")).findElement(By.className("step-wrap"))
				// //
				// .findElement(By.id("vf-step-3")).findElement(By.linkText("再报名一个")).click();
				num++;
			} else {
				continue;
			}
		}

	}

}
