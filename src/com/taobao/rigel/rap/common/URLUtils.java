package com.taobao.rigel.rap.common;

/**
 * Created by mashengbo on 14-9-5.
 */
public class URLUtils {
    public static boolean isStaticUrl(String url) {
        if (url == null) {
            return false;
        }
        String [] extentions = new String[] {
                ".jpg", ".png", ".gif",
                ".js", ".css", ".font", ".woff"
        };
        for (String ex : extentions) {
            if (url.contains(ex)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLogUrl(String url) {
        if (url == null) {
            return false;
        }
        String [] keys = new String[] {
                "logData.action", "getUnreadNotificationList.action"
        };
        for (String key : keys) {
            if (url.contains(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMockServiceUrl(String url) {
        if (url == null) {
            return false;
        }
        String [] keys = new String[] {
                "/mockjs/", "/mock/", "/mockjsdata/"
        };
        for (String key : keys) {
            if (url.contains(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean shouldLog(String url) {
        return !(isStaticUrl(url) || isLogUrl(url));
    }

    public static boolean isRelativeUrlExactlyMatch(String url1, String url2) {
        if (url1 == url2) return true;
        if (url1 == null || url2 == null) return false;
        return getRelativeUrl(url1).equals(getRelativeUrl(url2));
    }

    public static String getDomain(String url) {
        if (url == null || !url.startsWith("http://")) {
            return "";
        }
        url = url.substring(7);
        if (url.indexOf("/") != -1) {
            url = url.substring(0, url.indexOf("/"));
        }
        return url;
    }

    private static String getRelativeUrl(String url) {
        if (url == null || url.isEmpty()) {
            return "";
        }
        if (url.contains("https://")) {
            url = url.substring(url.indexOf("/", 7));
        } else if (url.contains("http://")) {
            url = url.substring(url.indexOf("/", 8));
        }
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.charAt(0) != '/') {
            url = '/' + url;

        }
        return url;
    }

    public static String removeParamsInUrl(String url) {
        String result =  url.replaceAll("/:[^/]*", "");
        if (!result.startsWith("/")) {
            result = "/" + result;
        }
        return result;
    }

    public static String removeRealParamsInUrl(String url) {
        url =  url.replaceAll("/[0-9]*", "");
        String lastPart = url.substring(url.lastIndexOf("/") + 1);
        if (lastPart != null) {
            try {
                Integer num = Integer.parseInt(lastPart);
                if (num != null) {
                    url = url.substring(0, url.lastIndexOf("/"));
                }
            } catch (Exception ex) {

            }
        }

        String result = url;
        if (!result.startsWith("/")) {
            result = "/" + result;
        }
        return result;
    }

}
