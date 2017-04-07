package com.taobao.ibalance.alimama;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.OptionUtils;
import com.taobao.ibalance.util.ThreadSleep;

public class AddTaoBaoKeEvent {

	public static void main(String[] args) {
		WebDriver driver = GetDriver.getChromeDriver();
		String url = "http://www.alimama.com/member/login.htm";
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.switchTo().frame("taobaoLoginIfr");

		OptionUtils.driverElement(driver, 20, OptionUtils.ById, "J_Quick2Static").click();
		OptionUtils.driverElement(driver, 20, OptionUtils.ByName, "TPL_username").sendKeys("guraul");

		ThreadSleep.sleep(20000);
		driver.navigate().to("http://ad.alimama.com/myunion.htm#!/promotion/magpie_bridge/active/");

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.className("message-panel")).findElement(By.linkText("隐藏"));
			}
		}).click();

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("magix_vf_items_query")).findElement(By.linkText("15%-20%"));
			}
		}).click();

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.className("table-container")).findElement(By.cssSelector(".checkbox.mr8"));
			}
		}).click();

		ThreadSleep.sleep(5000);
		List<WebElement> elements = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.className("table-container")).findElements(By.tagName("table")).get(1);
			}
		}).findElements(By.tagName("tr"));

		List<String> paths = new ArrayList<>();
		for (WebElement element : elements) {
			paths.add(element.findElements(By.tagName("td")).get(8).findElement(By.tagName("a")).getAttribute("href"));
		}

		int num = 0;
		for (String path : paths) {
			if (num > 10) {
				break;
			}
			driver.navigate().to(path);

			int canAdd = Integer
					.valueOf(OptionUtils.driverElement(driver, 20, OptionUtils.ByClassName, "featured-target")
							.findElement(By.className("count")).getText().replace("/", ""));
			System.out.println(canAdd);

			int i = 1;
			for (WebElement we1 : driver.findElement(By.id("body")).findElement(By.id("inmain"))
					.findElement(By.id("vf-main")).findElement(By.className("step-wrap"))
					.findElement(By.id("vf-step-1")).findElement(By.className("featured-cnt"))
					.findElements(By.className("item"))) {
				if (i > 10) {
					break;
				}
				we1.findElement(By.tagName("img")).click();
				i++;
			}

			driver.findElement(By.linkText("下一步")).click();

			ThreadSleep.sleep(3000);
			WebElement element = driver.findElement(By.id("vf-main")).findElement(By.className("step-wrap"))
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

				ThreadSleep.sleep(3000);
				new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.id("vf-main")).findElement(By.className("step-wrap"))
								.findElement(By.id("vf-step-2"))
								.findElement(By.cssSelector(".form-line.line-submit.clearfix.mt40"))
								.findElement(By.linkText("完成"));
					}
				}).click();

				num++;
			} else {
				continue;
			}
		}

	}

}
