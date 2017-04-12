package com.taobao.ibalance.basic;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class AddFenxiaoProducts {

	// <option value="1094640">huayouwd</option>
	// <option value="648143">lujiexiecheng</option>
	// <option value="815950">tc服饰有限公司</option>
	// <option value="16793236" selected="">公獒旗舰店供应商</option>
	// <option value="3841080">我爱梦可</option>
	// <option value="787072">新公主时尚服装阁</option>
	// <option value="5310019">琪靓供应商</option>
	// <option value="12767773">苏尼达旗舰店供应商</option>
	// <option value="977324">袁奥祺</option>
	// <option value="16043291">隐形盾旗舰店供应商</option>

	public static void main(String[] args) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
		WebDriver driver = GetDriver.getChromeDriver();
		String url = "https://login.taobao.com/member/login.jhtml";
		Login.loginTaobao(driver, url);

		ThreadSleep.sleep(20000);
		driver.navigate().to("https://goods.gongxiao.tmall.com/distributor/product/my_product_list.htm");

		new Select(new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("supplierId"));
			}
		})).selectByValue("16793236");

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElements(By.className("input-text")).get(0);
			}
		}).sendKeys("帆布鞋");

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_FilterOptions")).findElement(By.tagName("button"));
			}
		}).click();

		List<String> pathList = new ArrayList<>();
		List<WebElement> weList = null;
		while (true) {
			weList = driver.findElements(By.className("title"));
			if (weList.size() > 0) {
				for (WebElement element : weList) {
					pathList.add(element.findElement(By.tagName("a")).getAttribute("href"));
				}

				new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.className("page-next"));
					}
				}).click();
			} else {
				break;
			}
		}

		for (String path : pathList) {
			driver.navigate().to(path);

			new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.className("publish-btn")).findElement(By.tagName("button"));
				}
			}).click();

			new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.linkText("查看对应宝贝"));
				}
			});
		}
	}
}
