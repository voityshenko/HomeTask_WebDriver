package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static double getFloatValue(String string) {
        double doubleValue = 0.0;
        Pattern pattern = Pattern.compile("([0-9\\.\\,])+");
        Matcher matcher = pattern.matcher(string.replace(",",""));
        if (matcher.find()) {
            doubleValue = Double.parseDouble(matcher.group());
        }
        return doubleValue;
    }
}
