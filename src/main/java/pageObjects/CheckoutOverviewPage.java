package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class CheckoutOverviewPage {
    private WebDriver driver;

    @FindBy(id= "cart_item_label")
    List<WebElement> purchasedProducts;
    @FindBy(id = "cancel")
    WebElement btnCancel;

    @FindBy(id = "finish")
    WebElement btnFinish;

     public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllPurchasedProducts() {
       return purchasedProducts;

    }

    /*

   public String getPaymentInformation() {
        WebElement element = orderInfoSummaryElements.get(0).findElement(By.className("summary_value_label"));
        return element.getText();
    }

    public String getShippingInformation() {
        WebElement element = orderInfoSummaryElements.get(1).findElement(By.className("summary_value_label"));
        return element.getText();
    }

    public double getItemTotal() {
        String[] tokens = subTotalPrice.getText().split("$");
        double subTotalPrice = Double.parseDouble(tokens[1]);
        return subTotalPrice;
    }

    public double getTax() {
        String[] tokens = taxPrice.getText().split("$");
        double tax = Double.parseDouble(tokens[1]);
        return tax;
    }

    public double getTotalPrice() {
        String[] tokens = totalPrice.getText().split("$");
        double totalPrice = Double.parseDouble(tokens[1]);
        return totalPrice;
    }

    public WebElement getItemContainer(){
        return checkoutContainer;
    }


*/
    public void clickCancelBtn() {
        btnCancel.click();
    }

    public void clickFinishBtn() {
        btnFinish.click();
    }

    public By getCheckoutSummaryContainer() {
        return  By.id("checkout_summary_container");
    }
}
