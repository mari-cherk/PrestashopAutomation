package com.prestashop.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class TopNavigation {

    WebDriver driver;

    @FindBy(css = ".expand-more._gray-darker.hidden-sm-down")
    private WebElement currency;

    @FindBy(xpath = "//*[@class='currency-selector dropdown js-dropdown open']//*[@class='material-icons expand-more']")
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
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }
    public String getCurrency() {
        String currentCurrency = currency.getText().split(" ")[1];
        return currentCurrency;
    }

}
