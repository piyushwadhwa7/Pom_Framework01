package com.qa.opencart.utils;

public class StringUtils {

    public static String getRandomEmailId(){
        String email = "userAuto" + System.currentTimeMillis() + "@gmail.com";
        System.out.println("User email is : " + email);
        return email;
    }
}
//delete user from user where emailId like"userAuto%";- we can use this query to delete the user from DB ths we are printing