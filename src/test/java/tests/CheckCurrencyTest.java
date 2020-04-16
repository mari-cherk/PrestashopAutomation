package tests;

import com.prestashop.page.HomePage;
import com.prestashop.page.Page;
import com.prestashop.provider.DriverManager;
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

    @Test
    public void checkProductCurrency() {
        HomePage home = new HomePage();
        System.out.println(topNav.getCurrency());
        home.productsCurrency.stream().forEach(x->Assert.assertTrue(x.getText().contains(topNav.getCurrency()),"Wrong currency"));
        System.out.println("Prices correspond to the established currency");
    }

    @AfterTest
    public void tearDown() {
        if (Page.driver != null) {
            Page.quitBrowser();
        }
    }

}
