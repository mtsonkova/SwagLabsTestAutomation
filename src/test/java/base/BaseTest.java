package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BaseTest {
protected static WebDriver driver;

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

    public static File getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destPath = System.getProperty("user.dir") +"\\src" + testCaseName + ".png";
        File file = new File(destPath);
       Files.copy(source.toPath(), file.toPath());
       return file;
    }
}
