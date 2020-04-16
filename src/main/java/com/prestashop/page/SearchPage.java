package com.prestashop.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;


public class SearchPage extends Page {

    @FindBy(xpath = "//*[@class='col-md-6 hidden-sm-down total-products']//p")
    public WebElement numberSearchResults;

    @FindBy (css = ".price")
    public List<WebElement> foundProducts;

    @FindBy(css = ".material-icons.pull-xs-right")
    public WebElement sortDropdown;

    @FindBy(xpath = "//*[@class='dropdown-menu']/a[5]")
    public WebElement fromHighToLow;

    @FindBy(css = ".product-price-and-shipping")
    public List<WebElement> productBlocks;

    @FindBy(css = ".product-price-and-shipping")
    public List<WebElement> priceContainers;

    public SearchPage() {
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
        PageFactory.initElements(factory, this);
    }
    WebDriverWait wait = new WebDriverWait(driver, 10);

    public int getNumberSearchResults() {
        int numberResults = Integer.parseInt(numberSearchResults.getText().replaceAll("\\D+", ""));
        return numberResults;
    }

    public int getNumberFoundProducts() {
        int numberFoundProducts = foundProducts.size();
        return numberFoundProducts;
    }

    public void setIncreaseSort() {
        sortDropdown.click();
        fromHighToLow.click();
        waitFor(4000);
    }

    public List checkPrice() {
        List<Float> priceOfFoundProducts = new ArrayList<>();
        for (WebElement productBlock : productBlocks) {
            if (productBlock.findElements(By.className("regular-price")).size() > 0) {
                Float intPrice = Float.parseFloat(productBlock.findElement(By.className("regular-price")).getText().split(" ")[0].replaceAll(",", "."));
                priceOfFoundProducts.add(intPrice);
            } else {
                Float intPrice = Float.parseFloat(productBlock.findElement(By.className("price")).getText().split(" ")[0].replaceAll(",", "."));
                priceOfFoundProducts.add(intPrice);
            }
        }
        List<Boolean> checkOrder = new ArrayList<>();
        for (int i=1; i < priceOfFoundProducts.size(); i++) {
            if(priceOfFoundProducts.get(i) <= priceOfFoundProducts.get(i-1)) {
                checkOrder.add(true);
            } else {
                checkOrder.add(false);
            }
        }
        return checkOrder;
    }

    public List checkDiscount() {
        List<Boolean> checksList = new ArrayList<>();
        for (WebElement container: priceContainers) {
            if(container.findElements(By.className("regular-price")).size() > 0) {
                float regularPrice = Float.parseFloat(container.findElement(By.className("regular-price")).getText().split(" ")[0].replace(",", "."));
                float newPrice = Float.parseFloat(container.findElement(By.className("price")).getText().split(" ")[0].replace(",", "."));
                float percentage = Float.parseFloat(container.findElement(By.className("discount-percentage")).getText().replaceAll("\\D", ""));
                float culNewPrice = regularPrice-regularPrice*percentage/100;
                float roundNewPrice = Precision.round(culNewPrice, 2);
                if(roundNewPrice == newPrice) {
                    checksList.add(true);
                } else {
                    checksList.add(false);
                }
            }
        }
        return checksList;
    }


}
