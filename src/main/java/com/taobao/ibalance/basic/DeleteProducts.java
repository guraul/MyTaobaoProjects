package com.taobao.ibalance.basic;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class DeleteProducts {

	public static void main(String[] args) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
		WebDriver driver = GetDriver.getChromeDriver();
		String url = "https://login.taobao.com/member/login.jhtml";
		Login.loginTaobao(driver, url);

		ThreadSleep.sleep(20000);
		driver.navigate().to("https://sell.taobao.com/auction/merchandise/auction_list.htm?type=1");
		List<WebElement> elements = driver.findElement(By.id("J_DataTable")).findElements(By.tagName("table")).get(1)
				.findElements(By.className("goods-sid"));

		List<String> ids = new ArrayList<>();
		for (WebElement e : elements) {
			ids.add(e.findElement(By.tagName("input")).getAttribute("value"));
		}

		driver.navigate().to("https://tadget.taobao.com/redaction/manager.htm");

		for (String id : ids) {
			ThreadSleep.sleep(10000);
			deleteById(driver, id);
		}
	}

	private static void deleteById(WebDriver driver, String id) {
		WebElement element = driver.findElement(By.className("search-form"));
		element.findElement(By.cssSelector(".form-control.search-input")).clear();
		element.findElement(By.cssSelector(".form-control.search-input")).sendKeys(id);
		element.findElement(By.className("search-btn")).click();

		ThreadSleep.sleep(10000);
		driver.findElement(By.id("J_SelectAll")).click();
		driver.findElement(By.id("J_ControlBar")).findElement(By.className("delete")).findElement(By.tagName("a"))
				.click();
		driver.findElement(By.id("J_ModalSure")).click();
	}

}
