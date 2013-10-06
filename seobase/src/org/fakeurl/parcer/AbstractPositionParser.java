package org.fakeurl.parcer;

import org.fakeurl.exeptions.CaptchaException;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 */
public abstract class AbstractPositionParser {

    public final String ENCODING = "utf-8";

    protected abstract Integer currentPosition(String domain, String word, String queryPattern) throws CaptchaException, IOException;
    protected abstract String getQueryPattern();

    public Integer currentPosition(String domain, String word) throws CaptchaException, IOException {
        return currentPosition(domain, word, getQueryPattern());
    }

    public Integer currentPosition(String domain, String word, Map<String, String> filter) throws CaptchaException, IOException {
        StringBuilder sb = new StringBuilder(getQueryPattern());
        for (Iterator<Map.Entry<String, String>> iterator = filter.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append("&").append(key).append("=").append(URLEncoder.encode(value, ENCODING));
        }
        return currentPosition(domain, word, sb.toString());
    }

}
