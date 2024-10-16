package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {

    private WebDriver driver;
    ElementUtil eleUtil;

    //1. By locators
    private By productHeader = By.cssSelector("div#content h1");
    private By imagesCount = By.cssSelector("div#content a.thumbnail");
    private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private By productPrice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
    private Map<String , String > productMap;


    //2. public constructor.. of the page
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3. page actions
    public String getProductHeader() {
        String header = eleUtil.doGetText(productHeader);
        System.out.println("product header is " + header);
        return header;
    }

    public int getImagesCount() {
        int imageCount = eleUtil.waitForVisiblityOfElementsLocated(imagesCount, TimeUtil.MEDIUM_TIME_OUT).size();
        System.out.println("image count is " + imageCount);
        return imageCount;
    }

    /**
     * This is the pure example of incapsulation where we can capsuate two methods inside a single one wihtin a class
     * here getProductInfoMap()= getProductMetaData() + getProductPrice()
     * Also in Test case we can call this method
     * //productMap=new HashMap<String , String >(); this will give us the data asp per the logic
     * productMap=new LinkedHashMap<String , String >(); this will give the data based on sequence in the ui
     * productMap=new TreeMap<String , String >(); this will store data based on alphabetic order ( sorted Map)
     * @return
     */
    public Map<String, String> getProductInfoMap() throws InterruptedException {
        productMap=new HashMap<String , String >();
        //productMap=new LinkedHashMap<String , String >();
        productMap.put("productname",getProductHeader());
        productMap.put("productimageCount",String.valueOf(getImagesCount()));
        getProductMetaData();
        getProductPrice();
        return productMap;
    }

//    Brand: Apple
//    Product Code: Product 18
//    Reward Points: 800
//    Availability: In Stock

    private void getProductMetaData() throws InterruptedException {
        productMap=new HashMap<String , String >();
        List< WebElement> mertaList = eleUtil.getElements(productMetaData);
        for (WebElement e : mertaList) {
            System.out.println(e.getText());
            String metaData= e.getText();
            String meta[] =metaData.split(":");
            String metaKey = meta[0].trim();
            String metaValue = meta[1].trim();
            Thread.sleep(1000);
            productMap.put(metaKey,metaValue);

        }
    }
    private void getProductPrice() {
        productMap=new HashMap<String , String >();
        List< WebElement> priceList = eleUtil.getElements(productPrice);
        System.out.println(priceList.get(0).getText());
        System.out.println(priceList.get(1).getText());
        String productPrice =priceList.get(0).getText();
        String extTraprice=priceList.get(1).getText().split(":")[1].trim();
        productMap.put("productPrice",productPrice);
        productMap.put("extTraprice",extTraprice);

    }
}
