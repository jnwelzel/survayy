package com.jonwelzel.application.cli.util;

public final class NumberUtils {
    private NumberUtils() {}

    public static Integer stringToInteger(String value) {
        if (value == null || value.equals("")) {
            return null;
        }

        return Integer.valueOf(value);
    }

    public static Long stringToLong(String value) {
        if (value == null || value.equals("")) {
            return null;
        }

        return Long.parseLong(value);
    }
}
