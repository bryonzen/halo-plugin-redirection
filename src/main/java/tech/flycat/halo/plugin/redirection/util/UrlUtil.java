package tech.flycat.halo.plugin.redirection.util;

/**
 * @author <a href="mailto:zengbin@hltn.com">zengbin</a>
 * @since 2024/6/22
 */
public class UrlUtil {
    public static String trimEndSlash(String urlPath) {
        if (urlPath == null) {
            return null;
        }

        if (urlPath.length() > 1 && urlPath.endsWith("/")) {
            return urlPath.substring(0, urlPath.length() - 1);
        }

        return urlPath;
    }
}
