package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformation {
    private WebDriver driver;

    public CheckoutInformation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
