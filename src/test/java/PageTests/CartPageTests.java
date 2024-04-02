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

public class CartPageTests extends BaseTest {
    private CartPage cartPage;
    private ProductsPage productsPage;

    private JSONObject jsonObject = getJsonObject();
    JSONArray products = (JSONArray) jsonObject.get("purchasedProductsList");


    @BeforeMethod
    public void setTest() {

        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.validLogin();
        productsPage.purchaseMultipleProducts(products);
        cartPage = productsPage.clickOnTheShoppingCart();
    }

    @Test
    public void clickOnContinueShoppingBtn() {
        cartPage.clickOnContinueShoppingBtn();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = jsonObject.get("productsPageUrl").toString();
        Assert.assertEquals(currentUrl, expectedUrl);
    }

    @Test
    public void removeProductfromTheShoppingCart() {
        List<WebElement> cartProducts = cartPage.getAllProductsFromCart();
        int size = cartProducts.size();
        WebElement currentProduct = cartProducts.get(0);
        WebElement btnRemove = currentProduct.findElement(By.className("cart_button"));
        btnRemove.click();

        int currentSize = size - 1;
        Assert.assertEquals(cartProducts.size(), currentSize);
    }

    @Test
    public void clickOnCheckoutButton(){
       CheckoutInformationPage checkoutInformationPage =  cartPage.clickOnCheckoutBtn();
       String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
       String actualUrl = driver.getCurrentUrl();
       Assert.assertEquals(actualUrl, expectedURL);
    }
}

