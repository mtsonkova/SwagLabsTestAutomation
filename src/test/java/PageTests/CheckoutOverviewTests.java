package PageTests;

import base.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.List;

public class CheckoutOverviewTests extends BaseTest {
    private CheckoutInformationPage checkoutInformationPage;
    private CartPage cartPage;
    private ProductsPage productsPage;
    private JSONObject jsonObject = getJsonObject();
    JSONArray products = (JSONArray) jsonObject.get("purchasedProductsList");
    CheckoutOverviewPage checkoutOverviewPage;
    @BeforeMethod
    public void setTest() {

        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.validLogin();
        productsPage.purchaseMultipleProducts(products);
        cartPage = productsPage.clickOnTheShoppingCart();
        checkoutInformationPage = cartPage.clickOnCheckoutBtn();
        checkoutOverviewPage = checkoutInformationPage.clickOnContinueBtn();
    }

   @Test
    public void checkThatProductsArePresentInTheCheckoutOverviewPage() {
        //By container = checkoutOverviewPage.getCheckoutSummaryContainer();
        //waitForElementToAppear(container);
        List<WebElement> productsList = checkoutOverviewPage.getAllPurchasedProducts();
        Assert.assertFalse(productsList.isEmpty());
   }
   @Test
    public void clickOnCancelButton() {
        checkoutOverviewPage.clickCancelBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
   }

}
