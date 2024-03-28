package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformation {
    private WebDriver driver;

    public CheckoutInformation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstNamePlaceholder;

    @FindBy(id = "last-name")
    WebElement lastNamePlaceholder;

    @FindBy(id = "postal-code")
    WebElement postalCodePlaceholder;

    @FindBy(css = "div#error-message-container h3")
    WebElement errorMsgContainer;

    @FindBy(id = "cancel")
    WebElement btnCancel;

    @FindBy(id = "continue")
    WebElement btnContinue;

    public void enterFirstName(String firstName) {
        firstNamePlaceholder.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNamePlaceholder.sendKeys(lastName);
    }

    public void enterPostCode(String postCode) {
        postalCodePlaceholder.sendKeys(postCode);
    }

    public void clickOnCancelBtn() {
        btnCancel.click();
    }

    public void clickOnContinueBtn() {
        btnContinue.click();
    }

    public String getErrMsgText() {
        return errorMsgContainer.getText();
    }

}
