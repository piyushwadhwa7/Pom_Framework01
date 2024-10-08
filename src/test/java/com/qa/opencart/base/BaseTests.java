package com.qa.opencart.base;
import com.qa.opencart.factory.DriverManager;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTests {
    DriverManager df;
    WebDriver driver;
    protected Properties prop;
    protected LoginPage loginPage;// This is excess modfier where we can use the child methods in another classes which have inherited it
    protected AccountsPage accountsPage;// This is excess modfier where we can use the child methods in another classes which have inherited it
    @BeforeTest
    public void setup() {
        df = new DriverManager();
        prop=df.initProp();
        driver=df.intiDriver(prop);
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
