package com.taobao.ibalance.zhitongche;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class UpdateProductInPlan {

	private static Logger logger = Logger.getLogger(UpdateProductInPlan.class);

	public static void main(String[] args) {

		WebDriver driver = GetDriver.getChromeDriver();
		String url = "https://login.taobao.com/member/login.jhtml";
		Login.loginTaobao(driver, url);
		//
		ThreadSleep.sleep(20000);
		driver.navigate().to("https://subway.simba.taobao.com");
		ThreadSleep.sleep(3000);
		// WebElement element =
		// driver.findElement(By.className("dialog-ext-close"));
		// element.click();

		ThreadSleep.sleep(2000);

		List<String> paths = new ArrayList<>();
		// for (int i = 1; i <= 4; i++) {
		// driver.navigate()
		// .to("https://subway.simba.taobao.com/#!/campaigns/standards/adgroups/index?type=item&campaignId=19486324&page="
		// + i);
		//
		// ThreadSleep.sleep(6000);
		// element = driver.findElement(By.id("J_magix_vf_65522_tbody"));
		// ThreadSleep.sleep(2000);
		// List<WebElement> wes = element.findElements(By.className("photo"));
		// ThreadSleep.sleep(5000);
		// for (WebElement we : wes) {
		// paths.add(we.findElement(By.className("img60")).getAttribute("href"));
		// }
		// }

		for (int i = 1; i <= 3; i++) {
			driver.navigate()
					.to("https://subway.simba.taobao.com/#!/campaigns/standards/adgroups/index?type=item&campaignId=19749353&page="
							+ i);

			ThreadSleep.sleep(2000);
			List<WebElement> wes = driver.findElement(By.id("J_magix_vf_65522_tbody"))
					.findElements(By.className("photo"));
			ThreadSleep.sleep(5000);
			for (WebElement we : wes) {
				paths.add(we.findElement(By.className("img60")).getAttribute("href"));
			}
		}

		for (int i = 334; i < paths.size(); i++) {
			System.out.println("this row's number:" + i);
			driver.navigate().to(paths.get(i));

			ThreadSleep.sleep(5000);
			int keys = Integer.valueOf(driver.findElement(By.id("J_magix_vf_main_bidwordsNum")).getText());
//			String category = driver.findElement(By.className("adgroup-details-text")).findElements(By.tagName("h4"))
//					.get(1).findElement(By.tagName("span")).getText();
			if (keys < 150 
//					&& category.equals("女装/女士精品 > 半身裙")
					) {
				driver.findElement(By.cssSelector(".table-top-pannel.clearfix")).findElement(By.className("fl"))
						.findElements(By.tagName("span")).get(0).click();
				ThreadSleep.sleep(5000);
				driver.findElement(By.className("table-area")).findElement(By.tagName("input")).clear();
				driver.findElement(By.className("table-area")).findElement(By.tagName("input"))
						.sendKeys(KeywordsInital.shuffle());
				driver.findElement(By.className("table-area")).findElement(By.tagName("button")).click();
				ThreadSleep.sleep(5000);
				driver.findElement(By.cssSelector(".pagination.sel-area")).findElement(By.tagName("a")).click();
				ThreadSleep.sleep(5000);
				driver.findElement(By.linkText("确定")).click();

			}
		}

		System.out.println("done");
		driver.quit();
	}

}
