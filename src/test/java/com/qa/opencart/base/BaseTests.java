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
    DriverManager df;
    WebDriver driver;
    protected Properties prop;
    protected LoginPage loginPage;// This is excess modfier where we can use the child methods in another classes which have inherited it
    protected AccountsPage accountsPage;// This is excess modfier where we can use the child methods in another classes which have inherited it
    protected SearchResultPage searchResPage;// This is excess modfier where we can use the child methods in another classes which have inherited it
    protected ProductInfoPage productInfoPage;// This is excess modfier where we can use the child methods in another classes which have inherited it
    protected SoftAssert softAssert;// This is excess modfier where we can use the child methods in another classes which have inherited it
    protected RegistrationPage regPage;

    @Step("Steup for the test, initialize the browser: {0}")
    @Parameters({"browser"})
    @BeforeTest
    public void setup(@Optional("chrome")String browserName) {
        df = new DriverManager();
        prop=df.initProp();
        if(browserName!=null){
            prop.setProperty("browser", browserName);
        }
        driver=df.intiDriver(prop);// Now the driver is ThreadLocal driver
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }
    @Step("Close the browser")
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
