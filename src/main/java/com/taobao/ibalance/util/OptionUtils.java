package com.taobao.ibalance.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OptionUtils {
	public static WebElement driverElement(WebDriver driver, int seconds, String by, String element) {
		return new WebDriverWait(driver, seconds).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				switch (by) {
				case ById:
					return d.findElement(By.id(element));
				case ByName:
					return d.findElement(By.name(element));
				case ByClassName:
					return d.findElement(By.className(element));
				case ByLinkText:
					return d.findElement(By.linkText(element));
				default:
					return null;
				}
			}
		});
	}

	public static final String ById = "ById";
	public static final String ByName = "ByName";
	public static final String ByClassName = "ByClassName";
	public static final String ByLinkText = "ByLinkText";
}
