package com.example.msntest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsRegex {
    public static boolean validateUserName(String name) {
        Matcher matcher = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(name);
        return matcher.find();
    }

    public static boolean validatePassword(String name) {
        Matcher matcher = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,}$").matcher(name);
        return matcher.find();
    }
}
