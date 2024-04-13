package PageTests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductInfoPage;
import pageObjects.ProductsPage;

public class ProductInfoTest extends BaseTest {
    private ProductsPage productsPage;
    private ProductInfoPage productInfoPage;

    @BeforeMethod
    public void setTest() {

        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.validLogin();
        productInfoPage = clickOnProductName("Sauce Labs Backpack");
    }

    @Test
    public void checkThatProductHasName() {
        String productName = productInfoPage.getProductName();
        Assert.assertFalse(productName.isEmpty());
        Assert.assertEquals(productName, "Sauce Labs Backpack");
    }

    @Test
    public void checkThatProductHasDescription() {
        String productDescription = productInfoPage.getProductDescription();
        Assert.assertFalse(productDescription.isEmpty());
        Assert.assertEquals(productDescription, "carry.allTheThings() with the sleek," +
                " streamlined Sly Pack that melds uncompromising style with unequaled laptop " +
                "and tablet protection.");
    }

    @Test
    public void checkThatProductHasPrice() {
        double productPrice = productInfoPage.getProductPrice();
        Assert.assertTrue(productPrice > 0);
    }

    @Test
    public void checkThatProductHasImage() {
        WebElement productImage = productInfoPage.getImage();
        Assert.assertTrue(productImage.isDisplayed());
    }

    @Test
    public void clickOnBtnAndVerifyThatTheTextChangesWithEachClick() {
        productInfoPage.clickOnTheButton();
        String btnText = productInfoPage.getBtnText();
        Assert.assertEquals(btnText, "Remove");
        productInfoPage.clickOnTheButton();
        btnText = productInfoPage.getBtnText();
        Assert.assertEquals(btnText, "Add to cart");
    }




}
