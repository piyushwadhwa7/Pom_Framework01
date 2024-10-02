package com.qa.opencart.factory;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverManager {
    WebDriver driver;
    Properties prop;

    /**
     * This method is used to initialize the webdriver on the basis of given browser name
     *
     * @param prop
     */
    public WebDriver intiDriver(Properties prop) {
        {
            //cross browser logic
            String browserName = prop.getProperty("browser");
            System.out.println("browser name is " + browserName);
            switch (browserName.trim().toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println(" please pass the right browser" + browserName);
                    throw new BrowserExceptions(AppError.BROWSER_NOT_FOUND);
            }
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.get(prop.getProperty("url"));
            return driver;
        }
    }
        /**
         * This method is used to initialize the properties from config.properties file
         * @return Properties class object ( prop)
         */
        public Properties initProp () {
            //cross prop logic
            prop = new Properties();
            try {
                FileInputStream ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
                try {
                    prop.load(ip);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return prop;

        }
    }

