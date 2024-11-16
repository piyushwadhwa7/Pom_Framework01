package com.qa.opencart.factory;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserExceptions;
import com.qa.opencart.exceptions.FrameException;
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
         * so with new mvn command what will happen is : we have configired plugins  as maven complier and surefireplugin
         * so after runnung the command mvn clean install -Denv="QA" maven complier will check POM.XML and surefireplugin will check testng.xml
         * @return Properties class object ( prop)
         */
        public Properties initProp () {
            //cross prop logic
            prop = new Properties();
            FileInputStream ip=null;
            //MVN clean install -Denv="QA"
            //mvn clean install
            String envName=System.getProperty("env");
            System.out.println("Runnign the test suit on env--->  " +envName);
            if (envName == null) {
                System.out.println("env name is null. so running the suit on QA env");
                try {
                    ip=new FileInputStream(AppConstants.CONFIG_FILE_PATH);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else {

                try {
                    switch (envName.trim().toLowerCase()) {
                        case "qa":
                            ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
                            break;
                        case "dev":
                            ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
                            break;
                        case "uat":
                            ip = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
                            break;
                        case "stage":
                            ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
                            break;
                        default:
                            System.out.println("please pass the right env name" + envName);
                            throw new FrameException("No such environment found");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            try {
                prop.load(ip);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return prop;

        }
    }

