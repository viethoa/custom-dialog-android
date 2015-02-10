package com.material.viethoa.main.CustomDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.net.URI;
import java.net.URL;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Locale;

public class StringUtils {

    private StringUtils() {

    }

    /**
     * check string value not null
     */
    public static boolean isNotNull(String string) {
        if (string == null || string.trim().isEmpty()
                || "null".equals(string.toLowerCase(Locale.US))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * check string value null
     */
    public static boolean isNull(String string) {
        if (string == null || string.trim().isEmpty()
                || "null".equals(string.toLowerCase(Locale.US))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check string value is a http url
     */
    public static boolean isHttp(String string) {
        if (string == null || string.trim().length() < 10
                || "null".equals(string.toLowerCase(Locale.US))) {
            return false;
        } else {
            return string.startsWith("http");
        }
    }

    /**
     * check string value is a valid email address
     */
    public static boolean isValidEmail(String string) {
        if (string == null || string.length() <= 0 || string.contains("@") == false)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(string).matches();
    }

    /**
     * Capitalize the first char.
     *
     * @param s the string to be capitalized
     * @return Capitalized first char of the given word.
     */
    public static String capitalize(String s) {
        if (s == null)
            return null;
        if (s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    /**
     * get String from ArrayList string with separator
     */
    public static String join(AbstractCollection<String> s, String delimiter) {
        if (s == null || s.isEmpty()) return "";
        Iterator<String> iter = s.iterator();
        StringBuilder builder = new StringBuilder(iter.next());
        while (iter.hasNext())
            builder.append(delimiter).append(iter.next());
        return builder.toString();
    }

    public static String getStringFromArrayList(AbstractCollection<String> s, String delimiter) {
        return join(s,delimiter);
    }

    public static String encodeUrl(final String originalUrl) {
        String encodedUrl = originalUrl;
        try {
            URL url = new URL(originalUrl);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            encodedUrl = uri.toURL().toString();
        } catch (Exception e) {

        }
        return encodedUrl;
    }

    /**
     * Convert a String to int, with error catching
     */
    public static int getIntegerValue(String string, int defaultValue) {
        if (string == null || string.trim().isEmpty() || "null".equals(string.toLowerCase(Locale.US)))
            return 0;

        int intValue;
        try {
            intValue = Integer.parseInt(string);
        } catch (Exception e) {
            intValue = defaultValue;
        }
        return intValue;
    }

    /**
     * Copy a string to default clipboard
     */
    public static void copyToClipboard(Context context, String string) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", string);
        clipboard.setPrimaryClip(clip);
    }

    /**
     * remove keyword: "," and " "
     */
    public static String replaceKeyword(String string, AbstractCollection<String> keywords) {
        if(isNull(string) || string.isEmpty())
            return "";

        for (String elem : keywords) {
            string = string.replace(elem, "");
        }

        return string;
    }

    /**
     * get String from two string with separator
     */
    public static String join(String arrString, String string, String delimiter) {
        if(isNull(string) || string.isEmpty())
            return arrString;

        StringBuilder builder = new StringBuilder(arrString);
        if(isNull(arrString))
            builder.append(string);
        else builder.append(delimiter).append(string);

        return builder.toString();
    }


}

