package com.example.gesstudynotes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static final String DATE_FORMAT = "MMM dd, yyyy";
    private static final String TIME_FORMAT = "hh:mm a";
    private static final String DATETIME_FORMAT = "MMM dd, yyyy hh:mm a";

    public static String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public static String formatTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public static String formatDateTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public static String getRelativeTime(long timestamp) {
        long now = System.currentTimeMillis();
        long diffMs = now - timestamp;
        long diffSeconds = diffMs / 1000;
        long diffMinutes = diffSeconds / 60;
        long diffHours = diffMinutes / 60;
        long diffDays = diffHours / 24;

        if (diffSeconds < 60) {
            return "just now";
        } else if (diffMinutes < 60) {
            return diffMinutes + "m ago";
        } else if (diffHours < 24) {
            return diffHours + "h ago";
        } else if (diffDays < 7) {
            return diffDays + "d ago";
        } else {
            return formatDate(timestamp);
        }
    }
}
