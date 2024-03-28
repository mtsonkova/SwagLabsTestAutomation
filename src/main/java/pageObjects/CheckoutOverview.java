package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverview {
    private WebDriver driver;

    public CheckoutOverview(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
}
