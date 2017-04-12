package com.taobao.ibalance.zhitongche;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class AddProductInPlan {

	public static void main(String[] args) {

		WebDriver driver = GetDriver.getChromeDriver();
		String url = "https://login.taobao.com/member/login.jhtml";
		Login.loginTaobao(driver, url);

		ThreadSleep.sleep(30000);
		driver.navigate().to("https://subway.simba.taobao.com");

		// new WebDriverWait(driver, 20).until(new
		// ExpectedCondition<WebElement>() {
		// public WebElement apply(WebDriver d) {
		// return d.findElement(By.className("dialog-ext-close"));
		// }
		// }).click();

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.linkText("半身裙"));
			}
		}).click();

		driver.navigate().to(
				"https://subway.simba.taobao.com/#!/campaigns/standards/adgroups/items/add-suggest2?campaignId=19749353");
		ThreadSleep.sleep(2000);
		for (int i = 0; i < 1000; ++i) {
			System.out.println("word completed:" + i);
			pushSingleProduct(driver);
		}

		driver.quit();
	}

	private static void pushSingleProduct(WebDriver driver) {

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.cssSelector(".search.fr.pr")).findElement(By.tagName("input"));
			}
		}).sendKeys("碧玉罗香");

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.cssSelector(".search.fr.pr")).findElement(By.tagName("a"));
			}
		}).click();

		ThreadSleep.sleep(5000);
		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.cssSelector(".bp-table.table-body")).findElement(By.tagName("tbody"));
			}
		}).findElements(By.tagName("tr")).get(0).click();

		ThreadSleep.sleep(5000);
		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElements(By.className("target")).get(2);
			}
		}).click();

		WebElement element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_J_vframe_template_title_0"));
			}
		});

		String name = element.getAttribute("value").trim().replace("碧玉罗香", "").replace("苏尼达", "").replace("旋美", "")
				.replace("诚品", "").replace("苏城上品", "").replace("九舞", "").replace("梦可", "").replace("XZ", "");
		name = convertStringArray(name, 20);
		element.clear();
		element.sendKeys(name);

		ThreadSleep.sleep(2000);
		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.cssSelector(".mt20.mb30"));
			}
		}).click();

		// element =
		// driver.findElement(By.cssSelector(".btn.btn-orange.btn-size30"));
		// element.click();

		ThreadSleep.sleep(2000);
		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.cssSelector(".pagination.sel-area")).findElement(By.tagName("a"));
			}
		}).click();

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.linkText("确定"));
			}
		}).click();

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.className("finish-popularize")).findElement(By.tagName("button"));
			}
		}).click();

		new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.linkText("新建宝贝推广"));
			}
		}).click();
	}

	private static String convertStringArray(String inputStr, int iInputLenth) {
		if (iInputLenth >= 1 && iInputLenth <= 32)// 分割长度
		{
			ArrayList<String> arrayList = new ArrayList<String>();
			String srcString = inputStr;
			if (srcString.length() > 0)// 不为空
			{
				char src[] = srcString.toCharArray();// 将字符串转成字符数组
				int len = src.length;// 字符数组长度
				StringBuilder sb = new StringBuilder();
				boolean isContinue = true;
				int ring = 1;// 用于清空计数
				for (int index = 0; isContinue; index++, ring++) {
					if (index == (len - 1))// 长度和Index相同时
					{
						sb.append(src[index]);
						int tmp_len = sb.length();
						for (; tmp_len < iInputLenth; tmp_len++) {
							sb.append("0");// 补零
						}
						arrayList.add(sb.toString());
						isContinue = false;// 不再继续
					} else {
						sb.append(src[index]);// 字符数组转成
						if (ring == iInputLenth)// 如果长度等于分割长度，则进行一次分割，将sb存入list，并清空sb，下一轮开始
						{
							ring = 0;
							arrayList.add(sb.toString());
							sb = new StringBuilder();
						}
					}
				}
			}
			if (arrayList.size() > 0) {
				return arrayList.get(0);
			}
		}
		return null;
	}
}
