package com.qms.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExtentReportManager - Manages HTML test report generation using Extent Reports.
 */
public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "reports/QMS_TestReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("QMS Deviation Module - Test Report");
            sparkReporter.config().setReportName("Supply Chain OS - New Deviation Page Test Results");
            sparkReporter.config().setTimelineEnabled(true);

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Application", "Supply Chain OS - QMS Module");
            extentReports.setSystemInfo("Environment", "QA");
            extentReports.setSystemInfo("Browser", ConfigReader.getBrowser());
            extentReports.setSystemInfo("Tester", "QA Automation Engineer");
            extentReports.setSystemInfo("Module Tested", "New Deviation Page");
        }
        return extentReports;
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
