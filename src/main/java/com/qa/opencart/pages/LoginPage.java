package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    //1.Page object : By locator startegy and all should be private in nature
    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgottenPassword = By.linkText("Forgotten Password");
    private By registerBtn = By.linkText("Register");

    //2. public constructor.. of the page
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3. page actions
    @Step("Get login page title")
    public String getLoginPageTitle() {
        String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Login page title is : " + title);
        return title;
    }
    public String getLoginPageUrl() {
        String Url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Login page url is : " + Url);
        return Url;
    }
    public boolean checkForgottenPasswordLinkExists() {
        boolean flag = driver.findElement(forgottenPassword).isDisplayed();
        System.out.println("Forgotten password link is displayed : " + flag);
        return flag;
    }
    public boolean isLoginPageDisplayed() {
       return eleUtil.doIsDisplayed(forgottenPassword);

    }
    @Step("Login to application with userName:{0} and password: {1}")
    public AccountsPage doLogin(String un, String pwd) throws InterruptedException {
        eleUtil.doSendKeys(emailId, un, TimeUtil.MEDIUM_TIME_OUT);
        eleUtil.doSendKeys(password, pwd, TimeUtil.MEDIUM_TIME_OUT);
        Thread.sleep(1000);
        eleUtil.doClick(loginBtn);
        return  new AccountsPage(driver);// Test data driven approch and also object is already created here
        // This is also termed as page chaining concept

    }
    @Step("Navigate to registration page")
    public RegistrationPage navigateToResgistrationPage() {
        eleUtil.doClick(registerBtn, TimeUtil.MEDIUM_TIME_OUT);
        return new RegistrationPage(driver);
    }
}
