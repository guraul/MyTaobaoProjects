package com.taobao.ibalance.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	public static void loginTaobao(WebDriver driver,String url) {

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(url);
		ThreadSleep.sleep(5000);

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
	}
}
