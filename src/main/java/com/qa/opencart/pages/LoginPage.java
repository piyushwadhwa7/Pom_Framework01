package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    //1.Page object : By locator startegy and all should be private in nature
    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgottenPassword = By.linkText("Forgotten Password");

    //2. public constructor.. of the page
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //3. page actions
    public String getLoginPageTitle() {
        String title = driver.getTitle();
        System.out.println("Login page title is : " + title);
        return title;
    }
    public String getLoginPageUrl() {
        String Url = driver.getCurrentUrl();
        System.out.println("Login page url is : " + Url);
        return Url;
    }
    public boolean checkForgottenPasswordLinkExists() {
        boolean flag = driver.findElement(forgottenPassword).isDisplayed();
        System.out.println("Forgotten password link is displayed : " + flag);
        return flag;
    }
    public boolean isLoginPageDisplayed() {
        return driver.findElement(emailId).isDisplayed();
    }
    public String doLogin(String un, String pwd) throws InterruptedException {
        ElementUtil elementUtil = new ElementUtil(driver);
        driver.findElement(emailId).sendKeys(un);
        driver.findElement(password).sendKeys(pwd);
        Thread.sleep(1000);
        elementUtil.waitForElementVisible(loginBtn, 60);
        elementUtil.clickWhenReady(loginBtn, 60);
        String title = driver.getTitle();
        System.out.println("Account  page title is : " + title);
        return title;
    }
}
