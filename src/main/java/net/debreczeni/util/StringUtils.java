package net.debreczeni.util;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
