package com.taobao.ibalance.alimama;

import java.util.List;
import java.util.Set;

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

		element = driver.findElement(By.name("TPL_password"));
		element.clear();
		element.sendKeys("raul20161003@tb");
		ThreadSleep.sleep(1000);

		element = driver.findElement(By.id("J_SubmitStatic"));
		element.submit();

		ThreadSleep.sleep(3000);
		driver.navigate().to("http://ad.alimama.com/myunion.htm");
		ThreadSleep.sleep(3000);
		driver.navigate().refresh();

		ThreadSleep.sleep(3000);
		driver.navigate().to("http://ad.alimama.com/myunion.htm#!/promotion/magpie_bridge/active/");

		element = driver.findElement(By.className("message-panel"));
		element = element.findElement(By.linkText("隐藏"));
		element.click();

		element = new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("magix_vf_items_query"));
			}
		});

		ThreadSleep.sleep(3000);
		element = element.findElement(By.linkText("15%-20%"));
		element.click();

		ThreadSleep.sleep(3000);
		element = driver.findElement(By.className("table-container"));
		element.findElement(By.cssSelector(".checkbox.mr8")).click();

		List<WebElement> elements = element.findElements(By.tagName("table"));
		element = elements.get(1);
		elements = element.findElements(By.tagName("tr"));

		for (WebElement we : elements) {
			List<WebElement> wes = we.findElements(By.tagName("td"));
			element = wes.get(8).findElement(By.linkText("立即报名"));
			element.click();

			ThreadSleep.sleep(3000);
			element = driver.findElement(By.className("dialog-contentbox")).findElement(By.id("vf-dialog"))
					.findElements(By.tagName("p")).get(1).findElement(By.linkText("知道了"));
			element.click();
			
			element = driver.findElement(By.cssSelector(".normal-form.featured"));
			element = element.findElement(By.className("featured-cnt"));
			wes= element.findElements(By.tagName("li"));
			System.out.println(wes.size());
			break;

		}

	}

}
