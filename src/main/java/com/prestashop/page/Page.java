package com.prestashop.page;

import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import java.util.concurrent.TimeUnit;


public class Page {
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static final String siteurl = "http://prestashop-automation.qatestlab.com.ua/ru/";
    public static WebDriver driver;
    public static TopNavigation topNav;

    public static void initConfiguration() {
        driver.get(siteurl);
        log.debug("Navigated to " + siteurl);
        Reporter.log("Navigated to " + siteurl);
        driver.manage().window().maximize();
        log.debug("The window is maximized");
        Reporter.log("Navigated to " + siteurl);
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
        log.debug("The browser is left");
        Reporter.log("The browser is left");
    }
}
