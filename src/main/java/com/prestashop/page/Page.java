package com.prestashop.page;

import org.openqa.selenium.WebDriver;

public class Page {
    public static final String siteurl = "http://prestashop-automation.qatestlab.com.ua/ru/";
    public static WebDriver driver;

    public static void initConfiguration() {
        driver.get(siteurl);
        driver.manage().window().maximize();
    }

    public static void quitBrowser() {
        driver.quit();
    }
}
