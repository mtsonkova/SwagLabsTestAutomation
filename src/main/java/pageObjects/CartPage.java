package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement cartPageTitle;

    @FindBy(className = "cart_item")
    List<WebElement> productsInCart;

    @FindBy(id = "checkout")
    WebElement btnCheckout;

    @FindBy(id = "continue-shopping")
    WebElement btnContinueShopping;

    @FindBy(className = "cart_button")
    WebElement btnRemove;
    public String getCartPageTitle() {
        return cartPageTitle.getText();
    }

    public void clickOnContinueShoppingBtn() {
        btnContinueShopping.click();
    }

    public CheckoutInformationPage clickOnCheckoutBtn() {
        btnCheckout.click();
        return new CheckoutInformationPage(driver);
    }

    public void clickOnRemoveBtn(WebElement element) {
        //element.findElement(By.tagName("button")).click();
        btnRemove.click();
    }

    public WebElement getBtnRemove() {
        return btnRemove;
    }

    public List<WebElement> getAllProductsFromCart() {
        return productsInCart;
    }


}
