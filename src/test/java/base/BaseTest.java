package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
protected WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String baseUrl = "https://www.saucedemo.com/";
        driver.get(baseUrl);
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public void ReadPropertiesFile()
}
