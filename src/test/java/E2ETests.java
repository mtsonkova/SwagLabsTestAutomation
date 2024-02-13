import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class E2ETests extends BaseTest {

    By storeItems = By.className("inventory_item_name");
    @Test
    public void PurchaseAllProductsAndCheckOrder() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement btn_login = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        btn_login.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(storeItems));

        List<WebElement> inventoryItemsLayout = driver.findElements(By.className("inventory_item_description"));

       // Add all elements to cart
       for(WebElement element : inventoryItemsLayout) {

          // WebElement priceBar = element.findElement(By.cssSelector(".pricebar"));
            WebElement btn_AddToCart = element.findElement(By.tagName("button"));
            btn_AddToCart.click();
        }
        WebElement shopingCartIcon = driver.findElement(By.className("shopping_cart_link"));
       shopingCartIcon.click();

       WebElement btn_Checkout = driver.findElement(By.id("checkout"));
       btn_Checkout.click();

       WebElement firstNameInput = driver.findElement(By.id("first-name"));
       WebElement lastNameInput = driver.findElement(By.id("last-name"));
       WebElement postalCodeInput = driver.findElement(By.id("postal-code"));

       firstNameInput.sendKeys("Samantha");
       lastNameInput.sendKeys("Green");
       postalCodeInput.sendKeys("1234");

       WebElement btn_Continue = driver.findElement(By.id("continue"));
       btn_Continue.click();

       WebElement btn_Finish = driver.findElement(By.id("finish"));
       btn_Finish.click();

       WebElement confirmationHeader = driver.findElement(By.tagName("h2"));
       String text = confirmationHeader.getText();

        Assert.assertTrue(text.contains("Thank you"));

        }
}
