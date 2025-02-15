package com.ziine.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlUtil {

    /**
     * join with "/". Remove trailing or leading "/" if exists. But, first path's leading "/" is not removed. Also, last
     * path's trailing "/" is not removed.
     */
    public static String join(String... paths) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            path = removeLeadingSlashIfNotFirst(path, i);
            path = removeTrailingSlashIfNotLast(path, i, paths.length);
            sb.append(path);
            if (i < paths.length - 1) {
                sb.append("/");
            }
        }

        return sb.toString();
    }

    private static String removeLeadingSlashIfNotFirst(
        String path,
        int index
    ) {
        if (index > 0 && path.startsWith("/")) {
            return path.substring(1);
        }
        return path;
    }

    private static String removeTrailingSlashIfNotLast(
        String path,
        int index,
        int totalCount
    ) {
        if (index < totalCount - 1 && path.endsWith("/")) {
            return path.substring(0, path.length() - 1);
        }
        return path;
    }

}
