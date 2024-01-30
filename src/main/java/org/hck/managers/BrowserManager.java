package org.hck.managers;

import com.microsoft.playwright.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BrowserManager {
    private static final ThreadLocal<Map<String, Browser>> browserThreadLocalMap = ThreadLocal.withInitial(HashMap::new);

    public static Browser getBrowserInstance(String key, BrowserName browserType, BrowserType.LaunchOptions launchOptions) {
        key = (key == null || key.isEmpty()) ? "default" : key;
        Map<String, Browser> browserMap = browserThreadLocalMap.get();

        synchronized (Collections.unmodifiableMap(browserMap)) {
            Browser existingBrowser = getExistingBrowser(key);

            if (existingBrowser != null && existingBrowser.isConnected()) {
                return existingBrowser;
            }

            return createNewBrowser(key, browserType, launchOptions);
        }
    }

    private static Browser getExistingBrowser(String key) {
        Map<String, Browser> browserMap = browserThreadLocalMap.get();
        return browserMap.get(key);
    }

    private static Browser createNewBrowser(String key, BrowserName browserType, BrowserType.LaunchOptions launchOptions) {
        try {
            Playwright playwright = PlaywrightManager.getPlaywrightInstance();

            System.out.println("Launching " + browserType + " browser...");
            Browser newBrowser = switch (browserType) {
                case CHROMIUM -> playwright.chromium().launch(launchOptions);
                case FIREFOX -> playwright.firefox().launch(launchOptions);
                case WEBKIT -> playwright.webkit().launch(launchOptions);
                default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            };

            System.out.println(browserType + " browser launched successfully!");
            browserThreadLocalMap.get().put(key, newBrowser);
            return newBrowser;

        } catch (Exception e) {
            throw new RuntimeException("Failed to launch the browser.", e);
        }
    }

    public static void closeBrowsers(String key) {
        Map<String, Browser> browserMap = browserThreadLocalMap.get();
        synchronized (Collections.unmodifiableMap(browserMap)) {
            Browser browser = browserMap.remove(key);

            if (browser != null && browser.isConnected()) {
                browser.close();
            }
        }
    }

    public static void closeDefaultBrowsers() {
        closeBrowsers("default");
    }
}
