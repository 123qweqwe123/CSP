package com.lmbx.csp.core.mvc.formatter;

import org.springframework.format.datetime.DateFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Description:
 * 
 * <pre>
 *     类似 @SimpleDateFormat 标记，
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class MyDateTimeFormatter extends DateFormatter {

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        DateFormat format = null;
        String reg = "\\d{4}-\\d{2}-\\d{2}";
        if (text.matches(reg)) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return format.parse(text);
    }
}
