package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.IFactoryAnnotation;

public class RegistrationPage{
    private WebDriver driver;
    private ElementUtil eleUtil;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By pasword = By.id("input-password");
    private By confirmPasword = By.id("input-confirm");
    private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");
    private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0']");
    private By privacyPolicy = By.xpath("//label[@for='input-agree']");
    private By continueBtn = By.xpath("//input[@value='Continue']");
    private By successMsg = By.cssSelector("div#content h1");
    private By logout = By.linkText("Logout");
    private By registerBtn = By.linkText("Register");
    private By agreeCheckbox= By.xpath("//input[@type='checkbox']");


    public boolean userRegistration(String firstName, String lastName, String email, String telephone, String password, String subscribe) throws InterruptedException {
        eleUtil.doSendKeys(this.firstName, firstName, TimeUtil.MEDIUM_TIME_OUT);
        eleUtil.doSendKeys(this.lastName, lastName, TimeUtil.MEDIUM_TIME_OUT);
        eleUtil.doSendKeys(this.email, email, TimeUtil.MEDIUM_TIME_OUT);
        eleUtil.doSendKeys(this.telephone, telephone, TimeUtil.MEDIUM_TIME_OUT);
        eleUtil.doSendKeys(this.pasword, password, TimeUtil.MEDIUM_TIME_OUT);
        eleUtil.doSendKeys(this.confirmPasword, password, TimeUtil.MEDIUM_TIME_OUT);
        if (subscribe.equalsIgnoreCase("yes")) {
            eleUtil.doClick(subscribeYes, TimeUtil.MEDIUM_TIME_OUT);

        }else {
            eleUtil.doClick(subscribeNo, TimeUtil.MEDIUM_TIME_OUT);
        }
        Thread.sleep(2000);
        eleUtil.doClick(agreeCheckbox, TimeUtil.MEDIUM_TIME_OUT);
        eleUtil.doClick(continueBtn, TimeUtil.MEDIUM_TIME_OUT);

        String successmessage=eleUtil.waitForElementVisible(successMsg, TimeUtil.MEDIUM_TIME_OUT).getText();
        System.out.println("success message is : "+successmessage);
        if (successmessage.equalsIgnoreCase(AppConstants.USER_REGITRATION_SUCCESS_MASSAGE)) {
            eleUtil.doClick(logout);
            eleUtil.doClick(registerBtn);
            return true;
        }else {
            return false;
        }




    }
}
