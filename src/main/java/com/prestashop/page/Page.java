package com.prestashop.page;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class Page {
    public static final String siteurl = "http://prestashop-automation.qatestlab.com.ua/ru/";
    public static WebDriver driver;
    public static TopNavigation topNav;

    public static void initConfiguration() {
        driver.get(siteurl);
        driver.manage().window().maximize();
        topNav = new TopNavigation(driver);
    }

    void waitFor(long mills) {
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void quitBrowser() {
        driver.quit();
    }
}
