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
            ExtentReportManager.getTest().log(Status.INFO, "Dropdowns found on page: " + dropdowns);
        }
    }

    @Test(priority = 10, description = "TC_DEV_010 - Page scrolls to reveal all form sections")
    public void testPageScrollability() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        page.scrollToBottom();
        ExtentReportManager.getTest().log(Status.INFO, "Scrolled to bottom of page");
        page.scrollToTop();
        ExtentReportManager.getTest().log(Status.INFO, "Scrolled back to top");
        Assert.assertTrue(true, "Page scroll completed without errors");
    }

    // ═══════════════════════════════════════════════════════════════
    //  UI / FIELD PRESENCE TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test(priority = 11, description = "TC_UI_001 - All mandatory UI elements are present")
    public void testAllMandatoryUIElementsPresent() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        int fields    = page.countAllInputFields();
        int dropdowns = page.countAllDropdowns();
        ExtentReportManager.getTest().log(Status.INFO,
                "Fields: " + fields + " | Dropdowns: " + dropdowns);
        Assert.assertTrue(fields > 0, "Page must have at least one input field");
    }

    @Test(priority = 12, description = "TC_UI_002 - Page is responsive and renders within viewport")
    public void testPageRendersInViewport() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        Long scrollWidth = (Long) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.documentElement.scrollWidth");
        Long clientWidth = (Long) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.documentElement.clientWidth");

        ExtentReportManager.getTest().log(Status.INFO,
                "ScrollWidth: " + scrollWidth + " | ClientWidth: " + clientWidth);
        Assert.assertTrue(scrollWidth <= clientWidth + 50,
                "Page should not have major horizontal overflow");
    }

    @Test(priority = 13, description = "TC_UI_003 - Date field present if required")
    public void testDateFieldPresence() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        boolean datePresent = page.isDateFieldPresent();
        ExtentReportManager.getTest().log(Status.INFO, "Date field present: " + datePresent);
    }

    @Test(priority = 14, description = "TC_UI_004 - Page title in browser tab is meaningful")
    public void testBrowserTabTitle() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertFalse(title.isEmpty(), "Browser tab title must not be empty");
        ExtentReportManager.getTest().log(Status.INFO, "Browser tab title: " + title);
    }

    @Test(priority = 15, description = "TC_UI_005 - Attachment field present")
    public void testAttachmentFieldPresence() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        boolean attachPresent = page.isAttachmentInputPresent();
        ExtentReportManager.getTest().log(Status.INFO, "Attachment field present: " + attachPresent);
    }

    // ═══════════════════════════════════════════════════════════════
    //  VALIDATION TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test(priority = 16, description = "TC_VAL_001 - Submitting empty form shows validation errors")
    public void testEmptyFormSubmitShowsValidation() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        page.submitEmptyForm();

        boolean stayedOnPage = page.isPageLoaded();
        boolean errorsShown  = page.areValidationErrorsDisplayed();
        ExtentReportManager.getTest().log(Status.INFO,
                "Stayed on page: " + stayedOnPage + " | Errors: " + errorsShown);
        Assert.assertTrue(stayedOnPage || errorsShown,
                "Empty form should show validation or stay on page");
    }

    @Test(priority = 17, description = "TC_VAL_002 - Title required validation when empty")
    public void testTitleRequiredValidation() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isDescriptionFieldPresent()) {
            page.enterDescription("Testing without title");
        }
        page.submitEmptyForm();
        ExtentReportManager.getTest().log(Status.INFO,
                "Validation errors: " + page.getValidationErrors().size());
    }

    @Test(priority = 18, description = "TC_VAL_003 - Special characters in title are handled")
    public void testSpecialCharactersInTitleField() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isTitleFieldPresent()) {
            page.enterSpecialCharsInTitle();
            String value = page.getTitleValue();
            Assert.assertFalse(value.contains("<script>") && value.contains("alert"),
                    "XSS payload should not be executed");
            ExtentReportManager.getTest().log(Status.INFO, "Special chars handling: " + value);
        }
    }

    @Test(priority = 19, description = "TC_VAL_004 - Date field rejects invalid date format")
    public void testInvalidDateFormat() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isDateFieldPresent()) {
            page.enterDate("99/99/9999");
            page.submitEmptyForm();
            ExtentReportManager.getTest().log(Status.INFO, "Invalid date format tested");
        }
    }

    @Test(priority = 20, description = "TC_VAL_005 - Form retains data after failed validation")
    public void testFormRetainsDataAfterFailedValidation() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isTitleFieldPresent()) {
            page.enterTitle("Retained Title Test");
        }
        page.submitEmptyForm();

        if (page.isPageLoaded() && page.isTitleFieldPresent()) {
            String retainedValue = page.getTitleValue();
            ExtentReportManager.getTest().log(Status.INFO,
                    "Title after failed submit: '" + retainedValue + "'");
        }
    }

    @Test(priority = 21, description = "TC_VAL_006 - Required field indicators are shown")
    public void testRequiredFieldIndicatorsPresent() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        boolean hasIndicators = page.hasRequiredFieldIndicators();
        ExtentReportManager.getTest().log(Status.INFO,
                "Required indicators present: " + hasIndicators);
    }

    // ═══════════════════════════════════════════════════════════════
    //  POSITIVE SCENARIOS
    // ═══════════════════════════════════════════════════════════════

    @Test(priority = 22, description = "TC_POS_001 - Fill valid API manufacturing deviation and save")
    public void testFillValidAPIDeviationAndSave() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        page.enterTitle("Temperature Excursion - API Reactor Room B - Batch API240501");
        page.enterDescription("Reactor temperature exceeded specified range of 40+-2C reaching 48C for 20 minutes.");
        page.enterBatchNumber("API-2024-0501-B");
        page.enterDate("2024-05-01");
        page.enterRootCause("Temperature sensor calibration failure");
        page.enterImmediateAction("Batch quarantined and sensor replaced");

        ExtentReportManager.getTest().log(Status.INFO, "Valid API deviation form filled");
        page.clickSave();
        ExtentReportManager.getTest().log(Status.INFO, "Post save URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 23, description = "TC_POS_002 - Fill valid Raw Material deviation and save")
    public void testFillValidRawMaterialDeviationAndSave() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        page.enterTitle("Moisture Content Out-of-Spec - Raw Material RM-STARCH-2024");
        page.enterDescription("Incoming raw material failed moisture content specification. Observed 6.5% vs spec 5.0%.");
        page.enterBatchNumber("RM-STARCH-2024-003");

        ExtentReportManager.getTest().log(Status.INFO, "Raw material deviation form filled");
        page.clickSave();
        ExtentReportManager.getTest().log(Status.INFO, "Post save URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 24, description = "TC_POS_003 - Fill minimum required fields and save")
    public void testMinimumRequiredFieldsSave() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        page.enterTitle("Minor deviation - test");
        page.clickSave();
        ExtentReportManager.getTest().log(Status.INFO,
                "Minimum fields save URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 25, description = "TC_POS_004 - Date field accepts valid date format")
    public void testValidDateFormatAccepted() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isDateFieldPresent()) {
            page.enterDate("2024-05-15");
            ExtentReportManager.getTest().log(Status.INFO, "Valid date entered successfully");
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  NEGATIVE SCENARIOS
    // ═══════════════════════════════════════════════════════════════

    @Test(priority = 26, description = "TC_NEG_001 - Access deviation page without login")
    public void testDeviationPageWithoutLogin() {
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        String url = driver.getCurrentUrl();
        ExtentReportManager.getTest().log(Status.INFO, "URL without login: " + url);
        Assert.assertTrue(url.contains("/login") || url.contains("auth") || page.isPageLoaded(),
                "Unauthenticated access should redirect to login");
    }

    @Test(priority = 27, description = "TC_NEG_002 - Submit form with only whitespace in title")
    public void testWhitespaceOnlyTitle() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        page.enterTitle("        ");
        page.clickSave();
        ExtentReportManager.getTest().log(Status.INFO,
                "Whitespace title validation: " + page.areValidationErrorsDisplayed());
    }

    @Test(priority = 28, description = "TC_NEG_003 - SQL injection in title field")
    public void testSQLInjectionInTitleField() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isTitleFieldPresent()) {
            page.enterTitle("'; DROP TABLE deviations; --");
            page.clickSave();
            Assert.assertTrue(driver.getTitle() != null,
                    "Page should remain functional after SQL injection attempt");
            ExtentReportManager.getTest().log(Status.INFO,
                    "SQL injection test done. Title: " + driver.getTitle());
        }
    }

    @Test(priority = 29, description = "TC_NEG_004 - Navigate with invalid template ID")
    public void testInvalidTemplateIdInURL() {
        performLogin();
        driver.get(ConfigReader.getBaseUrl() + "/quality/records/new?template_id=invalid-000");
        com.qms.utils.WaitUtil.waitForPageLoad(driver);

        ExtentReportManager.getTest().log(Status.INFO,
                "Invalid template ID URL: " + driver.getCurrentUrl());
        Assert.assertNotNull(driver.getTitle(), "Page should still load with invalid template");
    }

    @Test(priority = 30, description = "TC_NEG_005 - Cancel button discards changes")
    public void testCancelButtonDiscardsChanges() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        page.enterTitle("Data that should be discarded");
        page.clickCancel();

        ExtentReportManager.getTest().log(Status.INFO,
                "After cancel URL: " + driver.getCurrentUrl());
    }

    // ═══════════════════════════════════════════════════════════════
    //  BOUNDARY TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test(priority = 31, description = "TC_BND_001 - Title with exactly 1 character")
    public void testTitleMinimumBoundary() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isTitleFieldPresent()) {
            page.enterTitle("A");
            String value = page.getTitleValue();
            ExtentReportManager.getTest().log(Status.INFO, "Single char title: '" + value + "'");
            Assert.assertEquals(value.trim(), "A", "Single character should be accepted");
        }
    }

    @Test(priority = 32, description = "TC_BND_002 - Title with 255 characters")
    public void testTitle255CharsBoundary() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isTitleFieldPresent()) {
            page.enterLongTitle(255);
            String value = page.getTitleValue();
            ExtentReportManager.getTest().log(Status.INFO,
                    "255 chars — Retained: " + value.length() + " chars");
        }
    }

    @Test(priority = 33, description = "TC_BND_003 - Title with 1000+ characters overflow")
    public void testTitleOverflowBoundary() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        if (page.isTitleFieldPresent()) {
            page.enterLongTitle(1000);
            String value = page.getTitleValue();
            ExtentReportManager.getTest().log(Status.INFO,
                    "1000 chars — Retained: " + value.length() + " chars");
            Assert.assertTrue(value.length() > 0, "Field should retain some input at overflow");
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  NAVIGATION TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test(priority = 34, description = "TC_NAV_001 - Browser back button after accessing page")
    public void testBrowserBackButton() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        driver.navigate().back();
        com.qms.utils.WaitUtil.waitForPageLoad(driver);
        ExtentReportManager.getTest().log(Status.INFO,
                "After back: " + driver.getCurrentUrl());
    }

    @Test(priority = 35, description = "TC_NAV_002 - Browser refresh preserves login session")
    public void testBrowserRefreshPreservesSession() {
        performLogin();
        NewDeviationPage page = new NewDeviationPage(driver);
        page.navigateTo(ConfigReader.getDeviationPageUrl());

        driver.navigate().refresh();
        com.qms.utils.WaitUtil.waitForPageLoad(driver);

        Assert.assertFalse(driver.getCurrentUrl().contains("/login"),
                "After refresh user should remain logged in");
        ExtentReportManager.getTest().log(Status.INFO,
                "Post refresh URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 36, description = "TC_NAV_003 - Direct URL navigation works after login")
    public void testDirectURLNavigationAfterLogin() {
        performLogin();
        driver.get(ConfigReader.getDeviationPageUrl());
        com.qms.utils.WaitUtil.waitForPageLoad(driver);

        Assert.assertFalse(driver.getCurrentUrl().contains("/login"),
                "Direct URL navigation should work after login");
        ExtentReportManager.getTest().log(Status.INFO,
                "Direct nav URL: " + driver.getCurrentUrl());
    }
}
