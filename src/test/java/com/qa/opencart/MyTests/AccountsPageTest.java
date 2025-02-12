package com.qa.opencart.MyTests;

import com.qa.opencart.base.BaseTests;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsPageTest extends BaseTests {

    @BeforeClass
    public void accSetup() throws InterruptedException {
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void accountPageTitleTest() {
        String actualTitle = accountsPage.getAccountsPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
    }

    @Test
    public void accountPageUrlTest() {
        String actualUrl = accountsPage.getAccountsPageUrl();
        Assert.assertTrue(actualUrl.contains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
    }
    @Test
    public void accountPageHeaderTest() {{
        List<String> actualHeaders = accountsPage.getAccountsPageHeaders();
        Assert.assertEquals(actualHeaders, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST, AppError.HEADERS_NOT_FOUND);
    }}

    @DataProvider
    public Object[][] getSearchData() {
        return new Object[][]{
                {"MacBook", 3},
                {"iMac", 1},
                {"Samsung", 2},
                {"airtel", 0}
        };
    }

    @Test(dataProvider = "getSearchData")
    public void searchTest(String searchKey, int resultcount) throws InterruptedException {
        searchResPage=accountsPage.doSearch(searchKey);
        Assert.assertEquals(searchResPage.getSearchResultCount(), resultcount, AppError.Results_COUNT_MISMATCHED);
    }

}
