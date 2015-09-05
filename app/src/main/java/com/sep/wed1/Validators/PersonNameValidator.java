package com.sep.wed1.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 9/5/2015.
 */
public class PersonNameValidator {

    public static boolean isNameValid(String name){
        String namePattern = "[a-zA-Z]*";
        Pattern pattern = Pattern.compile(namePattern);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
