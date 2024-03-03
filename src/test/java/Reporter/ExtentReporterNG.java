package Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") +"\\src" + "index.html";
        ExtentSparkReporter  sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("Web Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReporter = new ExtentReports();
        extentReporter.attachReporter(sparkReporter);
        extentReporter.setSystemInfo("Tester", "Milena Tsonkova");

        return extentReporter;
    }
}
