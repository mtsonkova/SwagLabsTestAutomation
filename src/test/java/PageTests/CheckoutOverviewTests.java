package PageTests;

import base.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import pageObjects.*;

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
}
