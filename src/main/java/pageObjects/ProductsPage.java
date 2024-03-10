package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class ProductsPage {
    private WebDriver driver;
    private WebElement btnAdd;
    private WebElement btnRemove;

    @FindBy(className = "title")
    WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    WebElement sort;

    private Select productsSorter;
    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCart;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;
    @FindBy(className = "inventory_item_description")
    ArrayList<WebElement> inventoryItemsDescription;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductsPageTitle() {
        return pageTitle.getText();
    }

    public void sortProductsByNameFromAtoZ() {
        productsSorter = new Select(sort);
        productsSorter.selectByVisibleText("Name (A to Z)");
    }

    public void sortProductsByNameFromZtoA() {
        productsSorter = new Select(sort);
        productsSorter.selectByVisibleText("Name (Z to A)");
    }

    public void sortProductsByPriceLowToHigh() {
        productsSorter = new Select(sort);
        productsSorter.selectByVisibleText("Price (low to high)");
    }

    public void sortProductsByPriceHighToLow() {
        productsSorter = new Select(sort);
        productsSorter.selectByVisibleText("Price (high to low)");
    }

    public ArrayList<WebElement> getAllInventoryItemsDescription() {
        return inventoryItemsDescription;
    }

    private void clickOnAddBtn(WebElement item) {
        btnAdd = item.findElement(By.className("btn_inventory"));

        if (btnAdd.getText().equalsIgnoreCase("add to cart")) {
            btnAdd.click();
        }
    }

    private void clickOnRemoveBtn(WebElement item) {
        btnRemove = item.findElement(By.className("btn_inventory"));

        if (btnRemove.getText().equalsIgnoreCase("Remove")) {
            btnRemove.click();
        }
    }
    public void AddAllProductsToTheShoppingCart() {

        ArrayList<WebElement> allProducts = getAllInventoryItemsDescription();
        for (WebElement item : allProducts) {
            clickOnAddBtn(item);
        }
    }
    public void RemoveAllProductsFromTheShoppingCart() {

        for (WebElement item : inventoryItemsDescription) {
            clickOnRemoveBtn(item);
        }
    }

    public void AddProductInTheShoppingCartByName(String productName) {
        for (WebElement item : inventoryItemsDescription) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            if (itemName.equalsIgnoreCase(productName)) {
                clickOnAddBtn(item);
                break;
            }
        }
    }
    public void RemoveProductFromTheShoppingCartByName(String productName) {
        for (WebElement item : inventoryItemsDescription) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            if (itemName.equalsIgnoreCase(productName)) {
                clickOnRemoveBtn(item);
                break;
            }
        }
    }
    public void clickOnTheShoppingCart() {
        shoppingCart.click();
    }

}