package com.jonwelzel.application.cli.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FormatUtils {
    private FormatUtils() {}

    public static LocalDateTime submittedAtFormatter(String submittedAtString) {
        if (submittedAtString == null || submittedAtString.equals("")) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return LocalDateTime.parse(submittedAtString, formatter);
    }
}
