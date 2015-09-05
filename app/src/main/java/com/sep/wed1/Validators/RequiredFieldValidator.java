package com.sep.wed1.Validators;

/**
 * Created by kavi on 9/5/2015.
 */
public class RequiredFieldValidator {
    public static boolean isEmpty(String text){
        return text.compareTo("")==0;
    }
}
