package Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "src/test/Reports/index.html";
        ExtentSparkReporter  reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReporter = new ExtentReports();
        extentReporter.attachReporter(reporter);
        extentReporter.setSystemInfo("Tester", "Milena Tsonkova");

        return extentReporter;
    }
}
