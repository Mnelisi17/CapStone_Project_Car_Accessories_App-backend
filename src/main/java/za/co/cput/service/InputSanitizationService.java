package za.co.cput.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class InputSanitizationService {

    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
            "(?i)(union|select|insert|update|delete|drop|create|alter|exec|execute|script|javascript|vbscript)",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern XSS_PATTERN = Pattern.compile(
            "(?i)<[^>]*script[^>]*>.*?</script>|javascript:|vbscript:|onload=|onerror=|onclick=",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]*>");

    public String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }


        String sanitized = input.trim();

        sanitized = removeHtmlTags(sanitized);

        sanitized = escapeSpecialCharacters(sanitized);

        sanitized = removeSqlInjectionPatterns(sanitized);

        sanitized = removeXssPatterns(sanitized);

        return sanitized;
    }

    public String sanitizeEmail(String email) {
        if (email == null) {
            return null;
        }

        String sanitized = email.trim().toLowerCase();

        sanitized = sanitized.replaceAll("[^a-zA-Z0-9@._-]", "");

        return sanitized;
    }

    public String sanitizePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }

        return phoneNumber.replaceAll("[^0-9+\\-\\s\\(\\)]", "");
    }

    public String sanitizeAlphanumeric(String input) {
        if (input == null) {
            return null;
        }

        return input.replaceAll("[^a-zA-Z0-9\\s]", "").trim();
    }

    public String sanitizeAddress(String address) {
        if (address == null) {
            return null;
        }

        String sanitized = removeHtmlTags(address);
        sanitized = removeXssPatterns(sanitized);

        sanitized = sanitized.replaceAll("[^a-zA-Z0-9\\s,.-]", "");

        return sanitized.trim();
    }

    public boolean containsSqlInjection(String input) {
        if (input == null) {
            return false;
        }
        return SQL_INJECTION_PATTERN.matcher(input).find();
    }

    public boolean containsXss(String input) {
        if (input == null) {
            return false;
        }
        return XSS_PATTERN.matcher(input).find();
    }

    public boolean isSafeInput(String input) {
        return !containsSqlInjection(input) && !containsXss(input);
    }

    private String removeHtmlTags(String input) {
        return HTML_TAG_PATTERN.matcher(input).replaceAll("");
    }

    private String escapeSpecialCharacters(String input) {
        return input
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;")
                .replace("/", "&#x2F;");
    }

    private String removeSqlInjectionPatterns(String input) {
        return SQL_INJECTION_PATTERN.matcher(input).replaceAll("");
    }

    private String removeXssPatterns(String input) {
        return XSS_PATTERN.matcher(input).replaceAll("");
    }

    public boolean isValidLength(String input, int maxLength) {
        return input != null && input.length() <= maxLength;
    }

    public boolean containsOnlyAllowedCharacters(String input, String allowedPattern) {
        if (input == null) {
            return true;
        }
        return Pattern.matches(allowedPattern, input);
    }
}