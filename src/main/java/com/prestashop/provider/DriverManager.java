package com.prestashop.provider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class DriverManager {
    public static WebDriver getDriver (String browser) {
        switch (browser) {
            case "farefox":
                System.setProperty(
                        "webdriver.geco.driver",
                        new File(DriverManager.class.getResource("/gecodriver.exe").getFile()).getPath());
                return new FirefoxDriver();
            case "ie":
            case "internet explorer":
                System.setProperty("webdriver.ie.driver",
                        new File(DriverManager.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                return new InternetExplorerDriver();
            case "chrome":
             default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(DriverManager.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();

        }
    }
}
