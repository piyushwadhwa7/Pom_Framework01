package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    //1. By locators
    private By searchResults = By.cssSelector("div.product-thumb");

    //2. public constructor.. of the page
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public int getSearchResultCount() {
       List<WebElement> resultList=eleUtil.waitForVisiblityOfElementsLocated(searchResults, TimeUtil.MEDIUM_TIME_OUT);
       int resultCount=resultList.size();
        System.out.println("search result count is : "+resultCount);
       return resultCount;
    }

    public ProductInfoPage selectProduct(String productName) {
      eleUtil.doClick(By.linkText(productName), TimeUtil.MEDIUM_TIME_OUT);
      return new ProductInfoPage(driver);
    }
}
