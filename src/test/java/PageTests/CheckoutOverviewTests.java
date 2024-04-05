package PageTests;

import base.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

import java.time.Duration;
import java.util.List;

public class CheckoutOverviewTests extends BaseTest {
    private CheckoutInformationPage checkoutInformationPage;
    private CartPage cartPage;
    private ProductsPage productsPage;
    private JSONObject jsonObject = getJsonObject();
    JSONArray products = (JSONArray) jsonObject.get("purchasedProductsList");
    CheckoutOverviewPage checkoutOverviewPage;

    List<WebElement> purchasedProducts;
    @BeforeMethod
    public void setTest() {

        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.validLogin();
        productsPage.purchaseMultipleProducts(products);
        cartPage = productsPage.clickOnTheShoppingCart();
        checkoutInformationPage = cartPage.clickOnCheckoutBtn();
        checkoutInformationPage.enterInformationData("Samantha", "Jenkins", "2001");
        checkoutOverviewPage = checkoutInformationPage.clickOnContinueBtn();
    }

   @Test
    public void checkThatProductsArePresentInTheCheckoutOverviewPage() {
        List<WebElement> cartItems = checkoutOverviewPage.getCartItems();
        int size = cartItems.size();
        Assert.assertEquals(size, 4);
   }

   @Test
    public void checkThatTotalPriceIsGreaterThanZeroWhenProductsArePurchased() {
        Double totalPrice = checkoutOverviewPage.getTotalPrice();
        Assert.assertTrue(totalPrice > 0);
   }

   @Test
    public void clickOnCancelButton() {
        checkoutOverviewPage.clickOnCancelButton();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
   }

   @Test
    public void clickOnFinishButton() {
        checkoutOverviewPage.clickOnFinishButton();
       String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
       String actualUrl = driver.getCurrentUrl();
       Assert.assertEquals(actualUrl, expectedUrl);
   }


}
