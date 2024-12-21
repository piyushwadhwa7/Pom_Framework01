package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("==========Running test in headless mode=======");
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            System.out.println("==========Running test in incognito mode=======");
            co.addArguments("--incognito");
        }
        return co;
    }

    public FirefoxOptions getFireFoxOptions() {
        fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("==========Running test in headless mode=======");
            fo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            System.out.println("==========Running test in incognito mode=======");
            fo.addArguments("--incognito");
        }
        return fo;
    }

    public EdgeOptions getEdgeOptions() {
        eo = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("==========Running test in headless mode=======");
            eo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            System.out.println("==========Running test in incognito mode=======");
            eo.addArguments("--inPrivate");
        }
        return eo;
    }
}
