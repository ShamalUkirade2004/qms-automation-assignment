package com.qms.pages;

import com.qms.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * LoginPage - Page Object Model for the QMS Login Page.
 * URL: http://216.48.184.249:5289/login
 */
public class LoginPage {

    private final WebDriver driver;

    // ── Locators ──────────────────────────────────────────────────────────────
    private final By emailField       = By.xpath("//input[@type='email' or @name='email' or @placeholder[contains(.,'email') or contains(.,'Email')]]");
    private final By passwordField    = By.xpath("//input[@type='password']");
    private final By loginButton      = By.xpath("//button[@type='submit' or contains(text(),'Login') or contains(text(),'Sign In')]");
    private final By errorMessage     = By.xpath("//*[contains(@class,'error') or contains(@class,'alert') or contains(@class,'invalid')]");
    private final By pageTitle        = By.tagName("h1");
    private final By forgotPassword   = By.xpath("//*[contains(text(),'Forgot') or contains(text(),'forgot')]");

    // ── Constructor ───────────────────────────────────────────────────────────
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    /** Navigate to login page */
    public void navigateTo(String url) {
        driver.get(url);
        WaitUtil.waitForPageLoad(driver);
    }

    /** Enter email/username */
    public void enterEmail(String email) {
        WebElement field = WaitUtil.waitForElementVisible(driver, emailField);
        field.clear();
        field.sendKeys(email);
    }

    /** Enter password */
    public void enterPassword(String password) {
        WebElement field = WaitUtil.waitForElementVisible(driver, passwordField);
        field.clear();
        field.sendKeys(password);
    }

    /** Click the Login / Sign In button */
    public void clickLoginButton() {
        WaitUtil.waitForElementClickable(driver, loginButton).click();
        WaitUtil.waitForPageLoad(driver);
    }

    /** Full login action */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    /** Check if an error message is displayed */
    public boolean isErrorDisplayed() {
        return WaitUtil.isElementPresent(driver, errorMessage);
    }

    /** Get error message text */
    public String getErrorMessage() {
        if (isErrorDisplayed()) {
            return driver.findElement(errorMessage).getText().trim();
        }
        return "";
    }

    /** Check if login page is displayed */
    public boolean isLoginPageDisplayed() {
        return driver.getCurrentUrl().contains("/login");
    }

    /** Check if email field is present */
    public boolean isEmailFieldPresent() {
        return WaitUtil.isElementPresent(driver, emailField);
    }

    /** Check if password field is present */
    public boolean isPasswordFieldPresent() {
        return WaitUtil.isElementPresent(driver, passwordField);
    }

    /** Check if login button is present */
    public boolean isLoginButtonPresent() {
        return WaitUtil.isElementPresent(driver, loginButton);
    }

    /** Check if Forgot Password link is present */
    public boolean isForgotPasswordPresent() {
        return WaitUtil.isElementPresent(driver, forgotPassword);
    }

    /** Get page title */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /** Check if password is masked */
    public boolean isPasswordMasked() {
        WebElement pwd = driver.findElement(passwordField);
        return "password".equals(pwd.getAttribute("type"));
    }
}
