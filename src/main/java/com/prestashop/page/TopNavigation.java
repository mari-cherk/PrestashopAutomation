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
        return currentCurrency;
    }
    public TopNavigation typeSearchWord(String word) {
        searchField.sendKeys(word);
        return new TopNavigation(driver);
    }
    public void changeCurrency(String currencyType) {
        currencyList.click();
        if (currencyType.toLowerCase().equals("eur")) {
            eurCurrency.click();
        }
        else if (currencyType.toLowerCase().equals("uah")) {
            uahCurrency.click();
        }
        else if (currencyType.toLowerCase().equals("usd")) {
            usdCurrency.click();
        } else {
            System.out.println("There is no such currency");
        }

    }
    public SearchPage searchByWord(String word) {
        this.typeSearchWord(word);
        searchButton.click();
        return new SearchPage();
    }

}
