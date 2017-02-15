package com.taobao.ibalance.basic;

import org.openqa.selenium.WebDriver;

import com.taobao.ibalance.util.GetDriver;
import com.taobao.ibalance.util.Login;
import com.taobao.ibalance.util.ThreadSleep;

public class SetProductBePublicService {

	public static void main(String[] args) {
		WebDriver driver = GetDriver.getChromeDriver();
		String url = "https://login.taobao.com/member/login.jhtml";
		Login.loginTaobao(driver, url);

		ThreadSleep.sleep(2000);
		driver.navigate().to("https://sell.taobao.com/auction/merchandise/auction_list.htm");
		ThreadSleep.sleep(2000);

	}

}
