package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

    public static final String LOGIN_PAGE_TITLE = "Account Login";

    public static final String REGISTRATION_PAGE_TITLE = "Register Account";

    public static final String ACCOUNTS_PAGE_TITLE = "My Account";

    public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";

    public static final String ACCOUNTS_PAGE_FRACTION_URL = "route=account/account";

    public static final String CONFIG_FILE_PATH = "./src/test/resources/config/config.properties";

    public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
}
