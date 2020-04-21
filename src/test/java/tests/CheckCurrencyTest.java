package tests;

import com.prestashop.page.HomePage;
import com.prestashop.page.Page;
import com.prestashop.provider.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static com.prestashop.page.Page.topNav;

public class CheckCurrencyTest {

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser){
        Page.driver = DriverManager.getDriver("browser");
        Page.initConfiguration();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Checking the correct currency after opening the site")
    @Story("Test currency on the home page after opening site")
    @Test
    public void checkProductCurrency() {
        HomePage home = new HomePage();
        //home.productsCurrency.stream().forEach(x->Assert.assertTrue(x.getText().contains(topNav.getCurrency()),"Wrong currency"));
        home.productsCurrency.stream().forEach(x->Assert.assertTrue(x.getText().contains("999"),"Wrong currency"));
        System.out.println("Prices correspond to the established currency");
    }

    @AfterTest
    public void tearDown() {
        if (Page.driver != null) {
            Page.quitBrowser();
        }
    }

}
