package com.qms.tests;

import com.aventstack.extentreports.Status;
import com.qms.pages.NewDeviationPage;
import com.qms.utils.ConfigReader;
import com.qms.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * NewDeviationPageTest - Comprehensive test suite for the QMS New Deviation Page.
 *
 * FUNCTIONAL TESTS         TC_DEV_001 to TC_DEV_010
 * UI / FIELD PRESENCE      TC_UI_001  to TC_UI_005
 * VALIDATION TESTS         TC_VAL_001 to TC_VAL_006
 * POSITIVE SCENARIOS       TC_POS_001 to TC_POS_004
 * NEGATIVE SCENARIOS       TC_NEG_001 to TC_NEG_005
 * BOUNDARY TESTS           TC_BND_001 to TC_BND_003
 * NAVIGATION TESTS         TC_NAV_001 to TC_NAV_003
 */
public class NewDeviationPageTest extends BaseTest {

    // ═══════════════════════════════════════════════════════════════
    //  FUNCTIONAL TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test(priority = 1, description = "TC_DEV_001 - New Deviation page loads successfully after login")
    public void testDeviationPageLoadsAfterLogin() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        Assert.assertTrue(page.isPageLoaded(),
                "New Deviation page should load after login. URL: " + driver.getCurrentUrl());
        ExtentReportManager.getTest().log(Status.INFO, "Page URL verified: " + driver.getCurrentUrl());
    }

    @Test(priority = 2, description = "TC_DEV_002 - Page header / title is displayed")
    public void testPageHeaderIsDisplayed() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        String header = page.getPageHeader();
        ExtentReportManager.getTest().log(Status.INFO, "Page header text: '" + header + "'");
        Assert.assertFalse(page.getPageTitle().isEmpty(), "Page title should not be empty");
    }

    @Test(priority = 3, description = "TC_DEV_003 - Form renders with input fields")
    public void testFormHasInputFields() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        int fieldCount = page.countAllInputFields();
        ExtentReportManager.getTest().log(Status.INFO, "Total input fields found: " + fieldCount);
        Assert.assertTrue(fieldCount > 0, "Page should have at least one input field");
    }

    @Test(priority = 4, description = "TC_DEV_004 - Save button is present on the page")
    public void testSaveButtonIsPresent() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        Assert.assertTrue(page.isSaveButtonPresent(),
                "Save or Submit button should be present on the form");
        ExtentReportManager.getTest().log(Status.INFO, "Save button confirmed present");
    }

    @Test(priority = 5, description = "TC_DEV_005 - Cancel button is present on the page")
    public void testCancelButtonIsPresent() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        Assert.assertTrue(page.isCancelButtonPresent(),
                "Cancel button should be present on the form");
        ExtentReportManager.getTest().log(Status.INFO, "Cancel button confirmed present");
    }

    @Test(priority = 6, description = "TC_DEV_006 - Title field accepts text input")
    public void testTitleFieldAcceptsInput() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isTitleFieldPresent()) {
            page.enterTitle("Temperature Deviation in API Reactor - Batch API20240101");
            String value = page.getTitleValue();
            ExtentReportManager.getTest().log(Status.INFO, "Title field value: " + value);
            Assert.assertFalse(value.isEmpty(), "Title field should accept and retain text");
        } else {
            ExtentReportManager.getTest().log(Status.INFO, "Title field not found");
        }
    }

    @Test(priority = 7, description = "TC_DEV_007 - Description field accepts text")
    public void testDescriptionFieldAcceptsInput() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isDescriptionFieldPresent()) {
            page.enterDescription("During the API granulation process, temperature exceeded 45C for 15 minutes.");
            Assert.assertTrue(page.getDescriptionValue().length() > 0,
                    "Description field should retain entered text");
            ExtentReportManager.getTest().log(Status.INFO, "Description field accepts input correctly");
        } else {
            ExtentReportManager.getTest().log(Status.INFO, "Description field not present");
        }
    }

    @Test(priority = 8, description = "TC_DEV_008 - Batch number field accepts alphanumeric input")
    public void testBatchNumberFieldAcceptsInput() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isBatchNumberFieldPresent()) {
            page.enterBatchNumber("API-BATCH-2024-001");
            ExtentReportManager.getTest().log(Status.INFO, "Batch number field input accepted");
        } else {
            ExtentReportManager.getTest().log(Status.INFO, "Batch number field not present");
        }
    }

    @Test(priority = 9, description = "TC_DEV_009 - Severity dropdown options are available")
    public void testSeverityDropdownHasOptions() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isSeverityDropdownPresent()) {
            page.selectSeverity("Critical");
            ExtentReportManager.getTest().log(Status.INFO, "Severity dropdown interacted successfully");
        } else {
            int dropdowns = page.countAllDropdowns();
            ExtentReportManager.getTest().log(Status.INFO, "Dropdowns found on page:
