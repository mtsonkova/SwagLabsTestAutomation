package PageTests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

public class ProductsPageTests extends BaseTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void setTest() {

        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.validLogin();
    }

    @Test
    public void checkProductsPageTitle() {
        String pageTitle = productsPage.getProductsPageTitle();
        Assert.assertEquals(pageTitle, "Products");
    }

    @Test
    public void addAllProductsInTheShoppingCart() {
        productsPage.AddAllProductsToTheShoppingCart();
        int cartBadgeValue = productsPage.getShoppingCartBadgeValue();
        Assert.assertEquals(cartBadgeValue, 6);
    }

}
