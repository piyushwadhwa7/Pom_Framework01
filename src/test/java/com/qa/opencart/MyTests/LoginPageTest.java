package com.qa.opencart.MyTests;
import com.qa.opencart.base.BaseTests;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
@Epic(" EPIC 100: Desgin Open cart application with shopping workflow")
@Stories({ @Story("STORY 101: Design login page") })
public class LoginPageTest extends BaseTests {
    @Description("Login Page Title Test")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 1)
    @Owner("piyush")
    public void LoginPageTitileTest() {
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);

    }
    @Description("Login Page URL Test")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void LoginPageUrlTest() {
        String actualUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);

    }
    @Description("Login Page Forgotten Password Link Test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void checkForgottenPasswordLinkExists() {
        Assert.assertTrue(loginPage.checkForgottenPasswordLinkExists(),AppError.ELEMENT_NOT_FOUND);

    }
    @Description("Login Test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void LoginTest() throws InterruptedException {
        accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
    }
}
