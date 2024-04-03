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

import java.util.List;

public class CheckoutOverviewTests extends BaseTest {
    private CheckoutInformationPage checkoutInformationPage;
    private CartPage cartPage;
    private ProductsPage productsPage;
    private JSONObject jsonObject = getJsonObject();
    JSONArray products = (JSONArray) jsonObject.get("purchasedProductsList");
    CheckoutOverviewPage checkoutOverviewPage = null;
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
    public void CheckThatProductsArePresentInTheOverviewPage() {
       int productsSize = checkoutOverviewPage.getPurchasedProductsListSize();
        Assert.assertTrue(productsSize > 0);
    }

    @Test
    public void CheckThatPaymentInformationFieldIsNotEmpty() {
        String paymentInformation = checkoutOverviewPage.getPaymentInformation();
        Assert.assertFalse(paymentInformation.isEmpty());
        Assert.assertEquals(paymentInformation, "SauceCard #31337");
    }

    @Test
    public void CheckThatShippingInformationFieldIsNotEmpty() {
        String shippingInformation = checkoutOverviewPage.getShippingInformation();
        Assert.assertFalse(shippingInformation.isEmpty());
        Assert.assertEquals(shippingInformation, "Free Pony Express Delivery!");
    }

    @Test
    public void CheckThatItemTotalPriceIsEqualToTheSumOFAllPurchasedProducts() {
        List<WebElement> products = checkoutOverviewPage.getPurchasedProductsList();
        double productsPrice = 0;
        for(WebElement item : products)  {
            String[] priceString = item.findElement(By.className("inventory_item_price")).toString().split("$");
           double price = Integer.parseInt(priceString[0]);
           productsPrice += price;
        };

        double expectedPrice = checkoutOverviewPage.getItemTotal();

        Assert.assertEquals(productsPrice, expectedPrice);
    }

}
