package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutOverviewPage {
    private WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_list")
    List<WebElement> purchasedProductsList;

    @FindBy(className = "summary_info_label")
    List<WebElement> orderInfoSummaryElements;

    @FindBy(className = "summary_subtotal_label")
    WebElement subTotalPrice;

    @FindBy(className = "summary_tax_label")
    WebElement taxPrice;

    @FindBy(className = "summary_total_label")
    WebElement totalPrice;

    @FindBy(id = "cancel")
    WebElement btnCancel;

    @FindBy(id = "finish")
    WebElement btnFinish;

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

    public void clickCancelBtn() {
        btnCancel.click();
    }

    public void clickFinishBtn() {
        btnFinish.click();
    }




}
