package com.qa.opencart.MyTests;
import com.qa.opencart.base.BaseTests;
import com.qa.opencart.errors.AppError;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

}
