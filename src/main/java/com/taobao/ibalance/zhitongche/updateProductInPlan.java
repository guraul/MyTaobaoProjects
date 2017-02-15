package com.taobao.ibalance.zhitongche;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class updateProductInPlan {

	public static void main(String[] args) {

		WebDriver driver = GetDriver.getChromeDriver();
		String url = "https://login.taobao.com/member/login.jhtml";
		Login.loginTaobao(driver, url);

		ThreadSleep.sleep(3000);
		driver.navigate().to("https://subway.simba.taobao.com");
		ThreadSleep.sleep(3000);
		WebElement element = driver.findElement(By.className("dialog-ext-close"));
		element.click();

		ThreadSleep.sleep(2000);
		element = driver.findElement(By.linkText("苏尼达"));
		element.click();

		ThreadSleep.sleep(2000);
		element = driver.findElement(By.id("right"));
		ThreadSleep.sleep(2000);
		element = element.findElement(By.id("magix_vf_65522"));
		ThreadSleep.sleep(2000);
		List<WebElement> elements = element.findElements(By.className("tbExpand-parent"));
		System.out.println(elements.size());

		for (WebElement we : elements) {
			element = we.findElement(By.className("title"));
			element = element.findElement(By.tagName("a"));
			element.click();
			ThreadSleep.sleep(2000);
			element = element.findElement(By.id("J_magix_vf_main_bidwordsNum"));
			if (Integer.valueOf(element.getText()) == 200) {
				continue;
			} else {

			}
		}

		// driver.quit();
	}

}
