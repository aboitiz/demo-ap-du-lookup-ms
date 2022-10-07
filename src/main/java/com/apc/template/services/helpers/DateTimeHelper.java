package com.apc.template.services.helpers;

import com.apc.template.commons.enums.ExceptionCode;
import com.apc.template.exceptions.APException;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHelper {

    private DateTimeHelper() {
    }

    // default
    private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("Asia/Manila");

    public static String formatDateToTimestamp(Date date, String format, TimeZone timezone) throws APException {
        String timestamp = "";
        if (format.isEmpty()) {
            format = DEFAULT_DATE_FORMAT;
        }
        if (timezone == null) {
            timezone = DEFAULT_TIMEZONE;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(timezone);

            timestamp = sdf.format(date); // or if you want to save it in String str
        } catch (Exception e) {
            throw new APException(ExceptionCode.UNEXPECTED_EXCEPTION.getCode(),
                    "Error @ convertDateToCalendar:String :: date=" + date,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return timestamp;
    }
}
