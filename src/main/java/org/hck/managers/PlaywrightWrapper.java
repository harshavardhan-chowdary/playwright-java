package org.hck.managers;

import com.microsoft.playwright.*;

import java.util.HashMap;
import java.util.Map;

public class PlaywrightWrapper {
    private static final ThreadLocal<Map<String, Page>> pageThreadLocalMap = ThreadLocal.withInitial(HashMap::new);
    private static final String DEFAULT_KEY = "default";

    public static Page getPageInstance(BrowserName browserName) {
        return getPageInstance(DEFAULT_KEY, DEFAULT_KEY, DEFAULT_KEY, browserName, null, null);
    }
    public static Page getPageInstance(BrowserName browserName, BrowserType.LaunchOptions launchOptions) {
        return getPageInstance(DEFAULT_KEY, DEFAULT_KEY, DEFAULT_KEY, browserName, launchOptions, null);
    }

    public static Page getPageInstance(String pageKey,
                                       String browserKey,
                                       String contextKey,
                                       BrowserName browserName,
                                       BrowserType.LaunchOptions launchOptions,
                                       Browser.NewContextOptions newContextOptions) {
        return PageManager.getPageInstance(pageKey, browserKey, contextKey, browserName, launchOptions, newContextOptions);
    }

    public static BrowserContext getBrowserContextInstance(String browserKey, String contextKey, BrowserName browserName, BrowserType.LaunchOptions launchOptions, Browser.NewContextOptions newContextOptions) {
        return BrowserContextManager.getBrowserContextInstance(browserKey, contextKey, browserName, launchOptions, newContextOptions);
    }

    public static Browser getBrowserInstance(String browserKey, BrowserName browserType, BrowserType.LaunchOptions launchOptions) {
        return BrowserManager.getBrowserInstance(browserKey, browserType, launchOptions);
    }

    public static void closePages(String pageKey) {
        PageManager.closePages(pageKey);
    }

    public static void closeDefaultPages() {
        PageManager.closeDefaultPages();
    }

    public static void closeBrowserContexts(String contextKey) {
        BrowserContextManager.closeBrowserContexts(contextKey);
    }

    public static void closeDefaultBrowserContexts() {
        BrowserContextManager.closeDefaultBrowserContexts();
    }

    public static void closeBrowsers(String browserKey) {
        BrowserManager.closeBrowsers(browserKey);
    }

    public static void closeDefaultBrowsers() {
        BrowserManager.closeDefaultBrowsers();
    }
}
