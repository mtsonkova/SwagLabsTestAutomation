package Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        //LocalDateTime dateTimeNow = LocalDateTime.now();
        String path = System.getProperty("user.dir") + "\\src\\TestNGReports\\index.html";
        //String path = System.getProperty("user.dir") +  "reportIndex" + dateTimeNow + ".html";
        ExtentSparkReporter  sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("Web Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tester", "Milena Tsonkova");

        return extentReports;
    }
}
