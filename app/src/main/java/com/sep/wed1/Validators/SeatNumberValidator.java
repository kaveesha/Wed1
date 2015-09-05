package com.sep.wed1.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kavi on 9/5/2015.
 */
public class SeatNumberValidator {

    public static boolean isSeatNoValid(String seatNo) {
        String seatNoPattern = "[1-9][0-9]{0,2}";
        Pattern pattern = Pattern.compile(seatNoPattern);
        Matcher matcher = pattern.matcher(seatNo);
        return matcher.matches();
    }
}
