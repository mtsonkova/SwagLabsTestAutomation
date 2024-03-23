package PageTests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;


public class LoginPageTest extends BaseTest {


    LoginPage loginPage;
   @BeforeMethod
   public void setTest() {
      loginPage = new LoginPage(driver);
   }
    @Test
    public void LoginWithValidUserNameAndPasswordAndCheckPageTitle() {
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

   /*
   @Test
    public void LoginWithValidUserNameAndPasswordAndCheckPageTitle() {
       loginPage.enterUserName("standard_user");
       loginPage.enterPassword("secret_sauce");
       loginPage.clickLoginButton();
       String expectedUrl = "https://www.saucedemo.com/inventory.html";
       String actualUrl = driver.getCurrentUrl();
       Assert.assertEquals(actualUrl, expectedUrl);
    }
*/
    @Test(enabled = true)
    public void LoginWithWrongUserNameAndValidPasswordAndCheckErrorMessage() {
        loginPage.enterUserName("wrong_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void LoginWithLockedOutUserAndValidPasswordAndCheckErrorMessage() {
        loginPage.enterUserName("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        String expectedError = "Epic sadface: Sorry, this user has been locked out.";
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void LoginWithEmptyUserNameAndValidPasswordAndCheckErrorMessage(){
        loginPage.enterUserName("");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        String expectedError = "Epic sadface: Username is required";
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }
    @Test
    public void LoginWithValidUserNameAndEmptyPasswordAndCheckErrorMessage(){
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        String expectedError = "Epic sadface: Password is required";
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }

}
