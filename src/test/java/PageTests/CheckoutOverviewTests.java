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
        WebElement cartList = checkoutOverviewPage.getCartList();
        waitForElementToAppear(cartList);
        List<WebElement> productsList = checkoutOverviewPage.getAllPurchasedProducts();
        Assert.assertFalse(productsList.isEmpty());
   }
   @Test
   //This test is left to fail intentionally because when you click the Cancel btn
   //you are redirected to Products Page and not inside the shopping cart.
    public void clickOnCancelButton() {
        checkoutOverviewPage.clickCancelBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
   }

   @Test
    public void findCheckoutSummaryContainerElement() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement container = driver.findElement(By.id("checkout_summary_container"));
        Assert.assertTrue(container.isDisplayed());
   }

   @Test
    public void clickOnFinishButton() {
        checkoutOverviewPage.clickFinishBtn();
        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
   }

}
