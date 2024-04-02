package PageTests;

import base.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class CheckoutInformationTests extends BaseTest {

    private CheckoutInformationPage checkoutInformationPage;
    private CartPage cartPage;
    private ProductsPage productsPage;

    private JSONObject jsonObject = getJsonObject();
    JSONArray products = (JSONArray) jsonObject.get("purchasedProductsList");

    private String firstName = jsonObject.get("firstName").toString();
    private String lastName = jsonObject.get("lastName").toString();
    private String postCode = jsonObject.get("postalCode").toString();


    @BeforeMethod
    public void setTest() {

        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.validLogin();
        productsPage.purchaseMultipleProducts(products);
        cartPage = productsPage.clickOnTheShoppingCart();
        checkoutInformationPage = cartPage.clickOnCheckoutBtn();
    }
    @Test
    public void provideValidUserInformation() {
        checkoutInformationPage.enterInformationData(firstName, lastName, postCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickOnContinueBtn();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }


}
