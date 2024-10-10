package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductInfoPage {

    private WebDriver driver;
    ElementUtil eleUtil;

    //1. By locators
    private By productHeader = By.cssSelector("div#content h1");
    private By imagesCount = By.cssSelector("div#content a.thumbnail");

    //2. public constructor.. of the page
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3. page actions
    public String getProductHeader() {
       String header=eleUtil.doGetText(productHeader);
        System.out.println("product header is "+header);
        return header;
    }

    public int getImagesCount() {
       int imageCount=  eleUtil.waitForVisiblityOfElementsLocated(imagesCount, TimeUtil.MEDIUM_TIME_OUT).size();
        System.out.println("image count is "+imageCount);
        return imageCount;
    }
}
