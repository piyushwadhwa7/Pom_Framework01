package com.qa.opencart.MyTests;
import com.qa.opencart.base.BaseTests;
import com.qa.opencart.errors.AppError;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ProductInfoPageTest extends BaseTests {
    /**
     * Here we can see the use of PageChaining concept
     *
     */

    @BeforeClass
    public void productInfoPageSetup() throws InterruptedException {
        accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getProductData() {
        return new Object[][]{
                {"MacBook", "MacBook Pro"},
                {"iMac", "iMac"},
                {"Samsung", "Samsung Galaxy Tab 10.1"},
                {"Apple Cinema ","Apple Cinema 30\""}
        };
    }

    @Test(dataProvider = "getProductData")
    public void productHeaderTest(String searchKey, String productName) throws InterruptedException {
       searchResPage =accountsPage.doSearch(searchKey);
       productInfoPage=searchResPage.selectProduct(productName);
        Assert.assertEquals(productInfoPage.getProductHeader(), productName, AppError.HEADER_NOT_FOUND);

    }

    @DataProvider
    public Object[][] getProductImgData() {
        return new Object[][]{
                {"MacBook", "MacBook Pro",4},
                {"iMac", "iMac",3},
                {"Samsung", "Samsung Galaxy Tab 10.1",7},
                {"Apple Cinema ","Apple Cinema 30\"",6}
        };
    }

    @Test(dataProvider = "getProductImgData")
    public void productImagesCountTest(String searchKey, String productName, int imgCount) throws InterruptedException {
        searchResPage =accountsPage.doSearch(searchKey);
        productInfoPage=searchResPage.selectProduct(productName);
        Assert.assertEquals(productInfoPage.getImagesCount(), imgCount, AppError.IMAGE_COUNT_MISMATCHED);
    }

    @Test
    public void productInfoTest() throws InterruptedException {
        searchResPage =accountsPage.doSearch("MacBook");
        productInfoPage=searchResPage.selectProduct("MacBook Pro");
        Map<String , String> productInfoMap=productInfoPage.getProductInfoMap();
        System.out.println(" ============Product Information===========");
        System.out.println(productInfoMap);
        softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
        softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
        softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
        softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
        softAssert.assertEquals(productInfoMap.get("productPrice"), "$2,000.00");
        softAssert.assertEquals(productInfoMap.get("extTraprice"), "$2,000.00");
        softAssert.assertAll();// Failure--information
    }
    // Hard assertion and soft assertion
    // Assert is hard assertion and soft is soft assertion

}
