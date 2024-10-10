package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private WebDriver driver;
    ElementUtil eleUtil;

    private By logout = By.linkText("Logout");
    private By headers= By.cssSelector("div#content h2");
    private By search= By.xpath("//input[@name='search']");
    private By seachIcon= By.cssSelector("div#search button");

    //2. public constructor.. of the page
    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3. page actions
    public String getAccountsPageTitle() {
        String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Account page title is : " + title);
        return title;
    }
    public String getAccountsPageUrl() {
        String Url = eleUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Account page url is : " + Url);
        return Url;
    }

    public boolean isLogoutExists() {
        return eleUtil.doIsDisplayed(logout);

    }
    public List<String> getAccountsPageHeaders() {

        List<WebElement> headersList =eleUtil.waitForVisiblityOfElementsLocated(headers, TimeUtil.DEFAULT_TIME_OUT);
        List <String> headersValueList = new ArrayList<String>();
        for (WebElement e : headersList) {
            String text = e.getText();
            headersValueList.add(text);
        }
        return headersValueList;
    }

    public boolean isSearchExists() {
        return eleUtil.doIsDisplayed(search);
    }
    public SearchResultPage doSearch(String searchKey) throws InterruptedException {
        System.out.println("Searching for : " + searchKey);
        if (isSearchExists()) {
            eleUtil.doSendKeys(search, searchKey, TimeUtil.MEDIUM_TIME_OUT);
            eleUtil.doClick(seachIcon);
        return new SearchResultPage(driver);
    }else {
        System.out.println("Search field is not present");
        return null;
    }
}}
