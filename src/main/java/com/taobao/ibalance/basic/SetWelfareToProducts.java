package com.taobao.ibalance.basic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class SetWelfareToProducts {

	public static void main(String[] args) {
		WebDriver driver = GetDriver.getChromeDriver();
		String url = "https://login.taobao.com/member/login.jhtml";
		Login.loginTaobao(driver, url);

		ThreadSleep.sleep(20000);
		driver.navigate().to("https://sell.taobao.com/auction/merchandise/auction_list.htm?type=1");

		for (int i = 1; i <= 8; i++) {
			ThreadSleep.sleep(5000);
			List<WebElement> elements = driver.findElement(By.id("J_DataTable")).findElements(By.tagName("table"))
					.get(1).findElements(By.className("goods-sid"));

			if (elements.size() > 0) {
				String idsStr = "";
				for (WebElement e : elements) {
					idsStr = idsStr + e.findElement(By.tagName("input")).getAttribute("value") + ",";
				}
				driver.navigate().to("https://gy.taobao.com/list_charity.htm?is_from_my=true&auctionids=" + idsStr);

				new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.className("typeMainTab")).findElements(By.tagName("li")).get(0);
					}
				}).click();

				new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.cssSelector(".modeNavTab.bluetext")).findElements(By.tagName("li"))
								.get(1);
					}
				}).click();

				new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.className("modeMainTab")).findElements(By.className("itemBox")).get(1)
								.findElements(By.tagName("input")).get(0);
					}
				}).click();

				new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.cssSelector(".J_submit.submitBtn"));
					}
				}).click();

				ThreadSleep.sleep(2000);
				driver.navigate().to("https://sell.taobao.com/auction/merchandise/auction_list.htm?type=1");

				new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.id("all-select1"));
					}
				}).click();

				((JavascriptExecutor) driver).executeScript("upShelf()");

				ThreadSleep.sleep(2000);
				driver.navigate().to("https://sell.taobao.com/auction/merchandise/auction_list.htm?type=1");

			}
		}
	}
}
