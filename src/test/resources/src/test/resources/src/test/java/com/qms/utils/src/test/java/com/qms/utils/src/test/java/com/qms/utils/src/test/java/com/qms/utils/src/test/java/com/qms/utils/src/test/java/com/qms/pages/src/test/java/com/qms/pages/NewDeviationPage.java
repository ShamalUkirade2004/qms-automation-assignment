package com.qms.pages;

import com.qms.utils.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * NewDeviationPage - Page Object Model for the New Deviation / New Event page.
 * URL: /quality/records/new?template_id=ef20c1ca-208e-4162-b6bf-f9aa7cfb7464
 */
public class NewDeviationPage {

    private final WebDriver driver;

    // ── Page-level Locators ───────────────────────────────────────────────────
    private final By pageHeader         = By.xpath("//h1 | //h2 | //*[contains(@class,'page-title') or contains(@class,'header-title')]");
    private final By saveButton         = By.xpath("//button[contains(text(),'Save') or contains(@class,'save')]");
    private final By submitButton       = By.xpath("//button[contains(text(),'Submit') or contains(text(),'submit')]");
    private final By cancelButton       = By.xpath("//button[contains(text(),'Cancel') or contains(text(),'cancel')]");
    private final By resetButton        = By.xpath("//button[contains(text(),'Reset') or contains(text(),'Clear')]");
    private final By loadingSpinner     = By.xpath("//*[contains(@class,'loading') or contains(@class,'spinner')]");

    // ── Form Field Locators ───────────────────────────────────────────────────
    private final By titleField         = By.xpath("//input[contains(@placeholder,'Title') or contains(@name,'title') or contains(@id,'title')]");
    private final By descriptionField   = By.xpath("//textarea[contains(@placeholder,'Description') or contains(@name,'description') or contains(@id,'description')]");
    private final By eventDateField     = By.xpath("//input[@type='date' or contains(@name,'date') or contains(@placeholder,'Date')]");
    private final By severityDropdown   = By.xpath("//select[contains(@name,'severity') or contains(@id,'severity') or contains(@name,'priority')]");
    private final By batchNumberField   = By.xpath("//input[contains(@name,'batch') or contains(@placeholder,'Batch') or contains(@name,'lot') or contains(@placeholder,'Lot')]");
    private final By productField       = By.xpath("//input[contains(@name,'product') or contains(@placeholder,'Product')] | //select[contains(@name,'product')]");
    private final By departmentField    = By.xpath("//input[contains(@name,'department') or contains(@placeholder,'Department')] | //select[contains(@name,'department')]");
    private final By assignedToField    = By.xpath("//input[contains(@name,'assigned') or contains(@placeholder,'Assign')] | //select[contains(@name,'assigned')]");
    private final By rootCauseField     = By.xpath("//textarea[contains(@name,'root') or contains(@placeholder,'Root Cause')] | //input[contains(@name,'root')]");
    private final By immediateActionField = By.xpath("//textarea[contains(@name,'immediate') or contains(@placeholder,'Immediate')] | //input[contains(@name,'immediate')]");
    private final By attachmentInput    = By.xpath("//input[@type='file']");
    private final By validationErrors   = By.xpath("//*[contains(@class,'error') or contains(@class,'invalid') or contains(@class,'required') or contains(@class,'validation')]");
    private final By requiredIndicators = By.xpath("//*[contains(@class,'required') or contains(text(),'*')]");
    private final By successMessage     = By.xpath("//*[contains(@class,'success') or contains(@class,'toast') or contains(@class,'notification') or contains(@class,'alert-success')]");
    private final By allInputFields     = By.tagName("input");
    private final By allTextAreas       = By.tagName("textarea");
    private final By allDropdowns       = By.tagName("select");

    // ── Constructor ───────────────────────────────────────────────────────────
    public NewDeviationPage(WebDriver driver) {
        this.driver = driver;
    }

    // ── Navigation ────────────────────────────────────────────────────────────
    public void navigateTo(String url) {
        driver.get(url);
        WaitUtil.waitForPageLoad(driver);
        WaitUtil.waitForElementInvisible(driver, loadingSpinner);
    }

    // ── Page State Checks ─────────────────────────────────────────────────────
    public boolean isPageLoaded() {
        WaitUtil.waitForPageLoad(driver);
        return driver.getCurrentUrl().contains("/quality/records/new");
    }

    public String getPageHeader() {
        if (WaitUtil.isElementPresent(driver, pageHeader)) {
            return driver.findElement(pageHeader).getText().trim();
        }
        return "";
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // ── Field Interactions ────────────────────────────────────────────────────
    public void enterTitle(String title) {
        if (WaitUtil.isElementPresent(driver, titleField)) {
            WebElement el = WaitUtil.waitForElementVisible(driver, titleField);
            el.clear();
            el.sendKeys(title);
        }
    }

    public void enterDescription(String description) {
        if (WaitUtil.isElementPresent(driver, descriptionField)) {
            WebElement el = WaitUtil.waitForElementVisible(driver, descriptionField);
            el.clear();
            el.sendKeys(description);
        }
    }

    public void enterBatchNumber(String batch) {
        if
