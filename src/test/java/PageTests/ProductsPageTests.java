package PageTests;

import base.BaseTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

import java.util.*;

public class ProductsPageTests extends BaseTest {
    private ProductsPage productsPage;
    String productName = "";

    String jsonFilePath = "src/test/utilities/sortOptions.json";
    boolean isDisplayed = false;

    JSONObject jsonObject = readJSONFile(jsonFilePath);
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

    @Test(dependsOnMethods = "addAllProductsInTheShoppingCart")
    public void removeAllProductsFromTheShoppingCart_AllAvalilableProductsAddedToCart() {
       productsPage.RemoveAllProductsFromTheShoppingCart();
      boolean isBadgeDisplayed =  productsPage.checkIfShoppingCartBadgeIsDisplayed();
      Assert.assertFalse(isBadgeDisplayed);
    }

    @Test
    public void addProductByName() {
       productName = "Sauce Labs Onesie";
       productsPage.AddProductInTheShoppingCartByName(productName);
       int shoppingCartBadgeValue = productsPage.getShoppingCartBadgeValue();
       Assert.assertTrue(shoppingCartBadgeValue >= 1);
    }

    @Test(dependsOnMethods = "addProductByName")
    public void removeProductByName() {
        productName = "Sauce Labs Onesie";
        productsPage.RemoveProductFromTheShoppingCartByName(productName);
        isDisplayed = productsPage.CheckIfRemoveBtnChangesToAddForProductRemovedFromCart(productName);
        Assert.assertTrue(isDisplayed);
    }

    @Test
    public void sortAllProductsByNameAToZ() {
        productsPage.sortProductsByNameFromAtoZ();
        ArrayList<String> sortedProductNames = productsPage.getProductNames();
       var result = jsonObject.get("Name (A to Z)");

        Assert.assertEquals(sortedProductNames, result);
    }

    @Test
    public void sortAllProductsByNameZToA() {
        productsPage.sortProductsByNameFromZtoA();
        ArrayList sortedProductNames = productsPage.getProductNames();
        var result = jsonObject.get("Name (Z to A)");
        Assert.assertEquals(sortedProductNames, result);
    }
    @Test
    public void sortAllProductsByPriceLowToHigh() {
        productsPage.sortProductsByPriceLowToHigh();
        ArrayList sortedProductNames = productsPage.getProductNames();
        var result = jsonObject.get("Price (low to high)");
        Assert.assertEquals(sortedProductNames, result);
    }

    @Test
    public void sortAllProductsByPriceHighToLow() {
        productsPage.sortProductsByPriceHighToLow();
        ArrayList sortedProductNames = productsPage.getProductNames();
        var result = jsonObject.get("Price (high to low)");
        Assert.assertEquals(sortedProductNames, result);
    }


    @Test(dataProvider = "filterData")
    public void filterAllProductsByGivenCriteria(String criteria, List<String> result) {
        if(criteria.equalsIgnoreCase("Name (A to Z)")) {
            productsPage.sortProductsByNameFromAtoZ();
        } else if(criteria.equalsIgnoreCase("Name (Z to A)")) {
            productsPage.sortProductsByNameFromZtoA();
        } else if(criteria.equalsIgnoreCase("Price (low to high)")) {
            productsPage.sortProductsByPriceLowToHigh();
        } else if(criteria.equalsIgnoreCase("Price (high to low)")) {
            productsPage.sortProductsByPriceHighToLow();
        }

        ArrayList sortedProductNames = productsPage.getProductNames();
        Arrays.stream(result.toArray()).toList();
        Assert.assertEquals(sortedProductNames, result);
    }

    @Test
    public void clickOnShoppingCartIcon() {
        productsPage.clickOnTheShoppingCart();
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @DataProvider(name = "filterData")
        public Object[][] filterData() {
            // This method provides the test data
            return new Object[][] {
                    {"Name (A to Z)",
                            Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                            "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)")
                    },
                    {"Name (Z to A)",
                            Arrays.asList("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie", "Sauce Labs Fleece Jacket",
                                    "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light", "Sauce Labs Backpack")
                    },
                    {"Price (low to high)",
                            Arrays.asList("Sauce Labs Onesie", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                                    "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack", "Sauce Labs Fleece Jacket")
                    },
                    {"Price (high to low)",
                            Arrays.asList("Sauce Labs Fleece Jacket", "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt",
                                    "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light", "Sauce Labs Onesie")
                    }
            };
    }




    }
