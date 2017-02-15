package com.taobao.ibalance.zhitongche;

import java.util.ArrayList;
import java.util.List;

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

		ThreadSleep.sleep(2000);
		driver.navigate().to("https://subway.simba.taobao.com");
		ThreadSleep.sleep(2000);
		WebElement element = driver.findElement(By.className("dialog-ext-close"));
		element.click();

		// String oriWin = driver.getWindowHandle();
		// ThreadSleep.sleep(2000);
		//
		// driver.switchTo().frame("login_iframe");
		// ThreadSleep.sleep(2000);
		// WebElement element = driver.findElement(By.id("btn"));
		// element.click();
		// ThreadSleep.sleep(2000);
		//
		// Set<String> handles = driver.getWindowHandles();
		// for (String handle : handles) {
		// if (oriWin.equals(handle)) {
		// continue;
		// } else {
		// driver.switchTo().window(handle);
		// }
		// }
		//
		// for (WebElement we : driver.findElements(By.tagName("vframe"))) {
		// if (we.getAttribute("id").equals("magix_vf_main")) {
		// element = we;
		// break;
		// }
		// }
		//
		// for (WebElement we : element.findElements(By.tagName("vframe"))) {
		// if (we.getAttribute("id").equals("magix_vf_65530")) {
		// element = we;
		// break;
		// }
		// }
		//
		ThreadSleep.sleep(2000);
		element = driver.findElement(By.linkText("半身裙"));
		element.click();

		driver.navigate()
				.to("https://subway.simba.taobao.com/#!/campaigns/standards/adgroups/items/add?campaignId=19749353");
		ThreadSleep.sleep(2000);
		for (int i = 0; i < 1000; ++i) {
			System.out.println(i);
			pushSingleProduct(element, driver);
		}

		driver.quit();
	}

	private static void pushSingleProduct(WebElement element, WebDriver driver) {
		ThreadSleep.sleep(5000);
		element = driver.findElement(By.cssSelector(".search.fr.pr"));
		WebElement subElement = element.findElement(By.tagName("input"));
		subElement.sendKeys("碧玉罗香");
		subElement = element.findElement(By.tagName("a"));
		subElement.click();

		ThreadSleep.sleep(1000);
		element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.className("creation-items"));
			}
		});

		// WebElement ele =
		// element.findElement(By.cssSelector(".btn-group.fl"));
		// ele = ele.findElement(By.cssSelector(".btn.btn-size25.fl"));
		// ele.click();

		element = element.findElement(By.className("bp-table"));

		// subElement = element.findElement(By.tagName("thead"));
		// List<WebElement> thList = subElement.findElements(By.tagName("th"));
		// thList.get(1).click();
		// thList.get(1).click();

		ThreadSleep.sleep(5000);
		element = element.findElement(By.tagName("tbody"));
		List<WebElement> trList = element.findElements(By.tagName("tr"));
		trList.get(0).click();

		element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("J_J_vframe_template_title_0"));
			}
		});

		String name = element.getAttribute("value").trim();
		name = name.replace("碧玉罗香", "").replace("苏尼达", "").replace("旋美", "").replace("诚品", "").replace("苏城上品", "")
				.replace("九舞", "").replace("梦可", "").replace("稻草朵", "");
		name = convertStringArray(name, 20);
		element.clear();
		element.sendKeys(name);

		ThreadSleep.sleep(2000);
		element = driver.findElement(By.cssSelector(".btn.btn-orange.btn-size30"));
		element.click();

		element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.cssSelector(".pagination.sel-area"));
			}
		});
		element = element.findElement(By.tagName("a"));
		ThreadSleep.sleep(5000);
		element.click();

		ThreadSleep.sleep(5000);
		element = driver.findElement(By.cssSelector(".btn.btn-orange.btn-size30"));
		element.click();

		element = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.linkText("推广宝贝"));
			}
		});
		element.click();
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
