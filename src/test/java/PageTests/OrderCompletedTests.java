package PageTests;

import base.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class OrderCompletedTests extends BaseTest {

    private CheckoutInformationPage checkoutInformationPage;
    private CartPage cartPage;
    private ProductsPage productsPage;

    private JSONObject jsonObject = getJsonObject();
    private JSONArray products = (JSONArray) jsonObject.get("purchasedProductsList");
    private CheckoutOverviewPage checkoutOverviewPage;

    private OrderCompletedPage orderCompletedPage;

    @BeforeMethod
    public void setTest() {

        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.validLogin();
        productsPage.purchaseMultipleProducts(products);
        cartPage = productsPage.clickOnTheShoppingCart();
        checkoutInformationPage = cartPage.clickOnCheckoutBtn();
        checkoutInformationPage.enterInformationData("Samantha", "Jenkins", "2001");
        checkoutOverviewPage = checkoutInformationPage.clickOnContinueBtn();
        orderCompletedPage  = checkoutOverviewPage.clickOnFinishButton();
    }

    @Test
    public void checkTheHeadingText() {
        String headingText = orderCompletedPage.getHeaderText();
        Assert.assertEquals(headingText, "Thank you for your order!");
    }

    @Test
    public void checkTheParagraphText() {
        String paragraphText = orderCompletedPage.getPurchaseCompleteText();
        String expected = "Your order has been dispatched, and" +
                " will arrive just as fast as the pony can get there!";
        Assert.assertEquals(paragraphText, expected);
    }

    @Test
    public void clickOnBackHomeButton() {
        orderCompletedPage.clickOnBackHomeBtn();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
