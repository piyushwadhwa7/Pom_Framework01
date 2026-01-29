package com.qa.opencart.base;
import com.qa.opencart.factory.DriverManager;
import com.qa.opencart.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTests {
    protected static DriverManager df;
    protected static Properties prop;

    protected WebDriver driver;

    protected LoginPage loginPage;
    protected AccountsPage accountsPage;
    protected SearchResultPage searchResPage;
    protected ProductInfoPage productInfoPage;
    protected RegistrationPage regPage;
    protected SoftAssert softAssert;

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        df = new DriverManager();
        prop = df.initProp();   // ONLY ONCE for entire JVM
    }

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void classSetup(@Optional("chrome") String browserName) {

        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }

        driver = df.intiDriver(prop);  // ThreadLocal driver inside

        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
