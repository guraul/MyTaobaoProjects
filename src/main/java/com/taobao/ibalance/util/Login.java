package com.taobao.ibalance.util;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	public static void loginTaobao(WebDriver driver, String url) {

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

//		element = new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
//			public WebElement apply(WebDriver d) {
//				return d.findElement(By.name("TPL_username"));
//			}
//		});
//		element.clear();
//		element.sendKeys("");
//
//		ThreadSleep.sleep(5000);
//		element = driver.findElement(By.name("TPL_password"));
//		element.clear();
//		element.sendKeys("");
//		ThreadSleep.sleep(1000);
//
//		element = driver.findElement(By.id("J_SubmitStatic"));
//		element.submit();
//
//		ThreadSleep.sleep(5000);
//		try {
//			Actions action = new Actions(driver);
//			WebElement source = driver.findElement(By.id("nc_1_n1z"));
//			action.clickAndHold(source).moveByOffset((int) (Math.random() * 200) + 80, 0);
//			ThreadSleep.sleep(2000);
//			// 拖动完释放鼠标
//			action.moveToElement(source).release();
//			// 组织完这些一系列的步骤，然后开始真实执行操作
//			Action actions = action.build();
//			actions.perform();
//
//			ThreadSleep.sleep(5000);
//			element = driver.findElement(By.name("TPL_password"));
//			element.sendKeys("figo20170215@mall");
//			ThreadSleep.sleep(1000);
//			element = driver.findElement(By.id("J_SubmitStatic"));
//			element.submit();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

	}
}
