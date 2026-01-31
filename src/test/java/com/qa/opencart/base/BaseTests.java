package com.qa.opencart.base;
import com.qa.opencart.factory.DriverManager;
import com.qa.opencart.pages.*;
import io.qameta.allure.Allure;
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
    private void addAllureEnvironmentLabels() {

        String browser = prop.getProperty("browser");
        String env = prop.getProperty("env");          // from config.properties
        String os = System.getProperty("os.name");

        Allure.label("browser", browser);
        Allure.label("env", env);
        Allure.label("os", os);
    }


    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void classSetup(@Optional("chrome") String browserName) {

        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }

        driver = df.intiDriver(prop);  // ThreadLocal driver inside
        addAllureEnvironmentLabels();

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
