package com.taobao.ibalance.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class DeleteProducts {

	public static void main(String[] args) {

		WebDriver driver = GetDriver.getChromeDriver();
		Login.loginTaobao(driver);

		WebElement element = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_SiteNavSeller"));
			}
		});

		element = element.findElement(By.tagName("a"));
		element.click();

		element = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.linkText("出售中的宝贝"));
			}
		});
		element.click();

		String sellerWin = driver.getWindowHandle();
		System.out.println("卖家中心：" + sellerWin);
		element = driver.findElement(By.linkText("图片空间"));
		element.click();

		Set<String> handles = driver.getWindowHandles();
		String pictureWin = null;
		if (!handles.isEmpty()) {
			Iterator<String> it = handles.iterator();
			while (it.hasNext()) {
				pictureWin = it.next();
				if (!pictureWin.equals(sellerWin)) {
					break;
				}
			}

			System.out.println("图片空间：" + pictureWin);
		}

		driver.switchTo().window(sellerWin);
		element = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("search-keyword"));
			}
		});
		element.clear();
		element.sendKeys("碧玉 短袖");
		ThreadSleep.sleep(2000);

		element = driver.findElement(By.className("search-btn"));
		element.submit();

		List<String> ids = null;
		List<WebElement> products = driver.findElements(By.className("goods-sid "));
		if (!products.isEmpty()) {
			ids = new ArrayList<>();
			for (WebElement product : products) {
				element = product.findElement(By.className("selector"));
				ids.add(element.getAttribute("value"));
			}
		}
		System.out.println(ids.size());

		driver.switchTo().window(pictureWin);
		for (String id : ids) {
			ThreadSleep.sleep(2000);
			WebElement searchElement = driver.findElement(By.className("search-form"));
			element = searchElement.findElement(By.cssSelector(".form-control.search-input"));
			element.clear();
			element.sendKeys(id);
			element = searchElement.findElement(By.className("search-btn"));
			element.click();

			ThreadSleep.sleep(10000);
			element = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.id("J_Picture"));
				}
			});

			element = driver.findElement(By.id("J_SelectAll"));
			element.click();
			ThreadSleep.sleep(2000);
		}

		//
		// driver.quit();
	}

}
