package org.hck.managers;

import com.microsoft.playwright.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PageManager {
    private static final ThreadLocal<Map<String, Page>> pageThreadLocalMap = ThreadLocal.withInitial(HashMap::new);

    public static Page getPageInstance(String pageKey, String browserKey, String contextKey, BrowserName browserName) {
        return getPageInstance(pageKey, browserKey, contextKey, browserName, null, null);
    }

    public static Page getPageInstance(String pageKey, String browserKey, String contextKey, BrowserName browserName, BrowserType.LaunchOptions launchOptions) {
        return getPageInstance(pageKey, browserKey, contextKey, browserName, launchOptions, null);
    }

    public static Page getPageInstance(String pageKey, String browserKey, String contextKey, BrowserName browserName, BrowserType.LaunchOptions launchOptions, Browser.NewContextOptions newContextOptions) {
        pageKey = (pageKey == null || pageKey.isEmpty()) ? "default" : pageKey;
        browserKey = (browserKey == null || browserKey.isEmpty()) ? "default" : browserKey;
        contextKey = (contextKey == null || contextKey.isEmpty()) ? "default" : contextKey;

        Map<String, Page> pageMap = pageThreadLocalMap.get();

        synchronized (Collections.unmodifiableMap(pageMap)) {
            Page existingPage = pageMap.get(pageKey);

            if (existingPage != null && existingPage.isClosed()) {
                // If the existing page is closed, remove it from the map
                pageMap.remove(pageKey);
                existingPage = null;
            }

            if (existingPage != null) {
                return existingPage;
            }

            return createNewPage(pageKey, browserKey, contextKey, browserName, launchOptions, newContextOptions);
        }
    }

    private static Page createNewPage(String pageKey, String browserKey, String contextKey, BrowserName browserName, BrowserType.LaunchOptions launchOptions, Browser.NewContextOptions newContextOptions) {
        try {
            BrowserContext browserContext = BrowserContextManager.getBrowserContextInstance(browserKey, contextKey, browserName, launchOptions, newContextOptions);
            Page newPage = browserContext.newPage();
            pageThreadLocalMap.get().put(pageKey, newPage);
            return newPage;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a new page.", e);
        }
    }

    public static void closePages(String pageKey) {
        Map<String, Page> pageMap = pageThreadLocalMap.get();
        synchronized (Collections.unmodifiableMap(pageMap)) {
            Page page = pageMap.remove(pageKey);

            if (page != null && !page.isClosed()) {
                page.close();
            }
        }
    }

    public static void closeDefaultPages() {
        closePages("default");
    }
}
