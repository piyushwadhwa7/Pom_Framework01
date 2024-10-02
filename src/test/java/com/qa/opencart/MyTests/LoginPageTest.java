package com.qa.opencart.MyTests;
import com.qa.opencart.base.BaseTests;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTests {

    @Test(priority = 1)
    public void LoginPageTitileTest() {
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);

    }
    @Test(priority = 2)
    public void LoginPageUrlTest() {
        String actualUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);

    }

    @Test(priority = 3)
    public void checkForgottenPasswordLinkExists() {
        Assert.assertTrue(loginPage.checkForgottenPasswordLinkExists(),AppError.ELEMENT_NOT_FOUND);

    }

    @Test(priority = 4)
    public void LoginTest() throws InterruptedException {
        String accounttitle = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(accounttitle, AppConstants.ACCOUNT_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
    }
}
