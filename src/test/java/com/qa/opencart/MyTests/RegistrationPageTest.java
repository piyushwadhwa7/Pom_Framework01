package com.qa.opencart.MyTests;

import com.qa.opencart.base.BaseTests;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class RegistrationPageTest extends BaseTests {

    @BeforeClass
    public void regSetup() {
        regPage=loginPage.navigateToResgistrationPage();

    }
    @DataProvider
    public Object[][] getRegData() {
        return new Object[][]{
                {"John", "Doe","1234567890", "john123", "yes"},
                {"arti", "automation", "1234567890", "john123", "no"},
        };
    }
    @DataProvider
    public Object[][] registrationPageData() throws FileNotFoundException {
        return ExcelUtil.getTestData(AppConstants.REGISTRATION_SHEET_NAME);
    }
    @Test(dataProvider = "registrationPageData")
    public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) throws InterruptedException {
            Assert.assertTrue(regPage.userRegistration(firstName, lastName , StringUtils.getRandomEmailId(), telephone, password, subscribe), AppError.REGISTRATION_FAILED);
    }


}
