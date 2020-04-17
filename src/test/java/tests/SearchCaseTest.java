package tests;

import com.prestashop.page.HomePage;
import com.prestashop.page.Page;
import com.prestashop.page.SearchPage;
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

public class SearchCaseTest {

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser){
        Page.driver = DriverManager.getDriver("browser");
        Page.initConfiguration();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking search work on the site")
    @Story("Test search on the site")
    @Test
    public void SearchCase() {
        SearchPage search = new SearchPage();
        topNav.changeCurrency("USD");
        topNav.searchByWord("dress");
        Assert.assertEquals(search.getNumberFoundProducts(),search.getNumberSearchResults(), "Wrong number of products found");
        System.out.println("The number in the title shows correct number of products found");
        search.foundProducts.stream().forEach(x -> Assert.assertTrue(x.getText().contains(topNav.getCurrency()), "Wrong currency"));
        System.out.println("All prices are displayed in USD");
        search.setIncreaseSort();
        search.checkPrice().stream().forEach(x->Assert.assertEquals(x, true, "Wrong price"));
        System.out.println("The sort of products is from high to low");
        search.checkDiscount().stream().forEach(x->Assert.assertEquals(x, true, "Wrong discount"));
        System.out.println("The discount is calculated correctly");
    }

    @AfterTest
    public void tearDown() {
        if (Page.driver != null) {
            Page.quitBrowser();
        }
    }

}
