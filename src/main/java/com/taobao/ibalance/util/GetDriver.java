package com.taobao.ibalance.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GetDriver {
	public static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/gubin/project/workspace/MyTaobaoProjects/driver/chromedriver");
		// ChromeOptions chromeOptions = new ChromeOptions();
		// chromeOptions.addArguments(Arrays.asList("--disable-logging"));
		WebDriver driver = new ChromeDriver(new ChromeDriverService.Builder().withSilent(true).build());

		return driver;
	}

	public static WebDriver getPhantomJSDriver() {
		DesiredCapabilities dcaps = new DesiredCapabilities();
		dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"/Users/gubin/project/workspace/seleniumProject/MySel20Proj/dirver/phantomjs-2.1.1-macosx/bin/phantomjs");
		WebDriver driver = new PhantomJSDriver(dcaps);

		return driver;
	}
}
