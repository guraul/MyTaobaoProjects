package com.taobao.ibalance.basic;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class DeleteAllMobilePic {

	public static void main(String[] args) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
		WebDriver driver = GetDriver.getChromeDriver();
		String url = "https://login.taobao.com/member/login.jhtml";
		Login.loginTaobao(driver, url);

		ThreadSleep.sleep(20000);
		driver.navigate().to("https://tadget.taobao.com/redaction/manager.htm?#goto/1250915002126164201/1");

		while (true) {
			ThreadSleep.sleep(3000);
			deleteOnePage(driver);
		}
	}

	private static void deleteOnePage(WebDriver driver) {

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_SelectAll"));
			}
		}).click();

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_ControlBar")).findElement(By.className("delete"))
						.findElement(By.tagName("a"));
			}
		}).click();

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_ModalSure"));
			}
		}).click();
	}

}
