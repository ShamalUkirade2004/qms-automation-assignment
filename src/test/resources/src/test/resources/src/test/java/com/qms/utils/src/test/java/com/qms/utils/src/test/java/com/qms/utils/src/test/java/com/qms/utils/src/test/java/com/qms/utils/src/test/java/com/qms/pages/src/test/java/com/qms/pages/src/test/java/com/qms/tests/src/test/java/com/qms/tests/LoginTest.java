package com.qms.tests;

import com.aventstack.extentreports.Status;
import com.qms.pages.LoginPage;
import com.qms.utils.ConfigReader;
import com.qms.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest - All test cases for the Login Page.
 *
 * TC_LOGIN_001 - Valid login with correct credentials
 * TC_LOGIN_002 - Login with invalid email
 * TC_LOGIN_003 - Login with invalid password
 * TC_LOGIN_004 - Login with empty email
 * TC_LOGIN_005 - Login with empty password
 * TC_LOGIN_006 - Login with empty credentials
 * TC_LOGIN_007 - Password field should be masked
 * TC_LOGIN_008 - UI elements present on login page
 * TC_LOGIN_009 - Page title verification
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "TC_LOGIN_001 - Valid login with correct credentials")
    public void testValidLogin() {
        ExtentReportManager.getTest().log(Status.INFO, "Navigating to login URL");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.getLoginUrl());

        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/login"),
                "After valid login, should redirect away from /login. Current URL: " + currentUrl);
        ExtentReportManager.getTest().log(Status.PASS, "Valid login successful, redirected to: " + currentUrl);
    }

    @Test(priority = 2, description = "TC_LOGIN_002 - Login with invalid email")
    public void testLoginWithInvalidEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.getLoginUrl());
        loginPage.login("invalidemail@fake.com", ConfigReader.getPassword());

        boolean onLoginPage = driver.getCurrentUrl().contains("/login");
        Assert.assertTrue(onLoginPage || loginPage.isErrorDisplayed(),
                "Should show error or stay on login page for invalid email");
        ExtentReportManager.getTest().log(Status.INFO, "Invalid email rejected correctly");
    }

    @Test(priority = 3, description = "TC_LOGIN_003 - Login with wrong password")
    public void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.getLoginUrl());
        loginPage.login(ConfigReader.getUsername(), "wrongpassword");

        boolean staysOnLogin = driver.getCurrentUrl().contains("/login");
        Assert.assertTrue(staysOnLogin || loginPage.isErrorDisplayed(),
                "Should show error or stay on login page for wrong password");
        ExtentReportManager.getTest().log(Status.INFO, "Invalid password rejected correctly");
    }

    @Test(priority = 4, description = "TC_LOGIN_004 - Login with empty email field")
    public void testLoginWithEmptyEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.getLoginUrl());
        loginPage.enterPassword(ConfigReader.getPassword());
        loginPage.clickLoginButton();

        boolean validationShown = loginPage.isErrorDisplayed() || driver.getCurrentUrl().contains("/login");
        Assert.assertTrue(validationShown, "Should not allow login with empty email");
        ExtentReportManager.getTest().log(Status.INFO, "Empty email validation working");
    }

    @Test(priority = 5, description = "TC_LOGIN_005 - Login with empty password field")
    public void testLoginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.getLoginUrl());
        loginPage.enterEmail(ConfigReader.getUsername());
        loginPage.clickLoginButton();

        boolean validationShown = loginPage.isErrorDisplayed() || driver.getCurrentUrl().contains("/login");
        Assert.assertTrue(validationShown, "Should not allow login with empty password");
        ExtentReportManager.getTest().log(Status.INFO, "Empty password validation working");
    }

    @Test(priority = 6, description = "TC_LOGIN_006 - Login with both fields empty")
    public void testLoginWithBothFieldsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.getLoginUrl());
        loginPage.clickLoginButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "Should stay on login page when both fields are empty");
        ExtentReportManager.getTest().log(Status.INFO, "Empty form submission handled correctly");
    }

    @Test(priority = 7, description = "TC_LOGIN_007 - Password field should be masked")
    public void testPasswordFieldIsMasked() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.getLoginUrl());
        Assert.assertTrue(loginPage.isPasswordMasked(),
                "Password field should be of type 'password' (masked)");
        ExtentReportManager.getTest().log(Status.INFO, "Password masking verified");
    }

    @Test(priority = 8, description = "TC_LOGIN_008 - All UI elements present on login page")
    public void testLoginPageUIElements() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.getLoginUrl());

        Assert.assertTrue(loginPage.isEmailFieldPresent(),    "Email field should be pres
