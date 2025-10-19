package za.co.cput.util;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

public class Helper {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^[+]?[0-9]{10,15}$"
    );

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNullOrBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isValidPhoneNumber(String phone) {
        if (isNullOrEmpty(phone)) {
            return false;
        }

        String cleanPhone = phone.replaceAll("[\\s()-]", "");
        return PHONE_PATTERN.matcher(cleanPhone).matches();
    }

    public static boolean isNullOrInvalidDate(LocalDate date) {
        if (date == null) {
            return true;
        }

        LocalDate now = LocalDate.now();
        LocalDate minDate = LocalDate.of(1900, 1, 1); // reasonable minimum date

        return date.isBefore(minDate) || date.isAfter(now.plusYears(100));
    }

    public static boolean isFutureDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    public static boolean isPastDate(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }

    public static boolean isNegativeOrZero(double value) {
        return value <= 0;
    }

    public static boolean isPositive(double value) {
        return value > 0;
    }

    public static boolean isNegative(double value) {
        return value < 0;
    }

    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    public static String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
    public static boolean isValidRating(int rating) {
        return rating >= 1 && rating <= 5;
    }

    public static String capitalize(String s) {
        if (isNullOrEmpty(s)) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}