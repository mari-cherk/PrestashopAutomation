package com.prestashop.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;


public class TopNavigation {
    public static Logger log = Logger.getLogger("devpinoyLogger");

    WebDriver driver;

    @FindBy(css = ".expand-more._gray-darker.hidden-sm-down")
    private WebElement currency;

    @FindBy(xpath = "//*[@id='_desktop_currency_selector']//*[@class='material-icons expand-more']")
    private WebElement currencyList;

    @FindBy(linkText = "EUR €")
    private WebElement eurCurrency;

    @FindBy(linkText = "UAH ₴")
    private WebElement uahCurrency;

    @FindBy(linkText = "USD $")
    private WebElement usdCurrency;

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    public TopNavigation(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
        PageFactory.initElements(factory, this);
    }
    public String getCurrency() {
        String currentCurrency = currency.getText().split(" ")[1];
        log.debug(currentCurrency + " is get as current currency");
        Reporter.log(currentCurrency + " is get as current currency");
        return currentCurrency;
    }
    public TopNavigation typeSearchWord(String word) {
        searchField.sendKeys(word);
        log.debug(word + " word is entered to the search field");
        Reporter.log(word + " word is entered to the search field");
        return new TopNavigation(driver);
    }
    public void changeCurrency(String currencyType) {
        currencyList.click();
        log.debug("The currency dropdown is expanded");
        Reporter.log("The currency dropdown is expanded");
        if (currencyType.toLowerCase().equals("eur")) {
            eurCurrency.click();
            log.debug("The EUR currency is chosen");
            Reporter.log("The EUR currency is chosen");
        }
        else if (currencyType.toLowerCase().equals("uah")) {
            uahCurrency.click();
            log.debug("The UAH currency is chosen");
            Reporter.log("The UAH currency is chosen");
        }
        else if (currencyType.toLowerCase().equals("usd")) {
            usdCurrency.click();
            log.debug("The USD currency is chosen");
            Reporter.log("The USD currency is chosen");
        } else {
            System.out.println("There is no such currency");
            log.debug("There is no such currency");
            Reporter.log("There is no such currency");
        }

    }
    public SearchPage searchByWord(String word) {
        this.typeSearchWord(word);
        searchButton.click();
        log.debug("The search by word is executed");
        Reporter.log("The search by word is executed");
        return new SearchPage();
    }

}
