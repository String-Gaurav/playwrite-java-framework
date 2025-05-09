package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public static ExtentReports getInstance() {
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReport/ExtentReport.html");
            reporter.config().setReportName("Test Automation Report");
            reporter.config().setDocumentTitle("Execution Report");
            extentReports.attachReporter(reporter);
        return extentReports;
    }
}