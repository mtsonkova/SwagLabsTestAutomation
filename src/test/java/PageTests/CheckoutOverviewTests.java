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
        List<WebElement> products = checkoutOverviewPage.getPurchasedProductsList();
        Assert.assertFalse(products.isEmpty());
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


}
