package com.qms.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qms.utils.ConfigReader;
import com.qms.utils.DriverManager;
import com.qms.utils.ExtentReportManager;
import com.qms.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;

/**
 * BaseTest - Parent class for all test classes.
 * Handles driver init/quit, report lifecycle, and screenshot on failure.
 */
public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        // Ensure reports directory exists
        new File("reports/screenshots").mkdirs();
        ExtentReportManager.getExtentReports();
    }

    @BeforeMethod
    public void setUp(java.lang.reflect.Method method) {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();

        ExtentTest test = ExtentReportManager.getExtentReports()
                .createTest(method.getName());
        ExtentReportManager.setTest(test);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ExtentTest test = ExtentReportManager.getTest();

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.fail("Test FAILED: " + result.getThrowable().getMessage());
            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test PASSED");
        } else {
            test.log(Status.SKIP, "Test SKIPPED: " + result.getThrowable());
        }

        DriverManager.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentReportManager.flushReports();
        System.out.println("✅ All tests completed. Report generated in /reports/");
    }

    /** Helper: Login using credentials from config */
    protected void performLogin() {
        driver.get(ConfigReader.getLoginUrl());
        com.qms.pages.LoginPage loginPage = new com.qms.pages.LoginPage(driver);
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        com.qms.utils.WaitUtil.waitForPageLoad(driver);
    }
}
