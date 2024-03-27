package base;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

public class BaseTest {

    //public String jsonFilePath = "src/test/utilities/purchasedProducts.json";
    protected static WebDriver driver;
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();

    }

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\utilities\\globalConfig.properties");
        properties.load(input);

        String browser = properties.getProperty("browser");
        String baseUrl = properties.getProperty("baseUrl");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");

            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");

            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless");

            driver = new EdgeDriver();
        }

        driverThreadLocal.set(driver);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        return driver;
    }

    public static JSONObject readJSONFile(String filePath) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(filePath));

            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static File getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destPath = "D:\\Projects\\SwagLabsTestAutomation\\Screenshots\\" + testCaseName + ".png";
        File file = new File(destPath);
        Files.copy(source.toPath(), file.toPath());
        return file;
    }



}
