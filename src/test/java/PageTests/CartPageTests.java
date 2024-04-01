package PageTests;

import base.BaseTest;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

import java.util.List;

public class CartPageTests extends BaseTest {
    private CartPage cartPage;
    private ProductsPage productsPage;

    private JSONObject jsonObject = getJsonObject();
    private String productName = "Sauce Labs Fleece Jacket";

    @BeforeMethod
    public void setTest() {

        LoginPage loginPage = new LoginPage(driver);
        productsPage.AddProductInTheShoppingCartByName(productName);
        cartPage = productsPage.clickOnTheShoppingCart();
    }

    @Test
    public void clickOnContinueShoppingBtn() {
        cartPage.clickOnContinueShoppingBtn();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = jsonObject.get("productsPageUrl").toString();
        Assert.assertEquals(currentUrl, expectedUrl);
    }

    @Test(dependsOnMethods = "purchaseMultipleProductsFromShoppingList")
    public void removeProductfromTheShoppingCart() {
        List<WebElement> cartProducts = cartPage.getAllProductsFromCart();
        int size = cartProducts.size();
        WebElement currentProduct = cartProducts.get(0);
        cartPage.clickOnRemoveBtn(currentProduct);
        int currentSize = size - 1;
        Assert.assertEquals(cartProducts.size(), currentSize);


    }

}

