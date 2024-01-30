package org.hck.managers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;

import java.util.HashMap;
import java.util.Map;

public class BrowserContextManager {
    private static final ThreadLocal<Map<String, BrowserContext>> contextThreadLocalMap = ThreadLocal.withInitial(HashMap::new);

    public static synchronized BrowserContext getBrowserContextInstance(String browserKey, String contextKey, BrowserName browserName, BrowserType.LaunchOptions launchOptions, Browser.NewContextOptions newContextOptions) {
        browserKey = (browserKey == null || browserKey.isEmpty()) ? "default" : browserKey;
        contextKey = (contextKey == null || contextKey.isEmpty()) ? "default" : contextKey;
        Map<String, BrowserContext> contextMap = contextThreadLocalMap.get();

        // Check if the context already exists in the map
        BrowserContext existingContext = contextMap.get(contextKey);
        if (existingContext != null && !isContextClosed(existingContext)) {
            return existingContext;
        }

        // Create a new context without try-with-resources
        BrowserContext browserContext = createBrowserContextInstance(browserKey, browserName, contextKey, launchOptions, newContextOptions);
        contextMap.put(contextKey, browserContext);
        return browserContext;
    }

    private static BrowserContext createBrowserContextInstance(String browserKey, BrowserName browserName, String contextKey, BrowserType.LaunchOptions launchOptions, Browser.NewContextOptions newContextOptions) {
        try {
            Playwright playwright = PlaywrightManager.getPlaywrightInstance();
            Browser browser = BrowserManager.getBrowserInstance(browserKey, browserName, launchOptions);

            // Apply the new context options if provided
            return (newContextOptions != null)
                    ? browser.newContext(newContextOptions)
                    : browser.newContext();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create browser context.", e);
        }
    }

    private static boolean isContextClosed(BrowserContext context) {
        try {
            // Attempt to create a new page to check if the context is closed
            context.newPage();
            return false;
        } catch (PlaywrightException e) {
            // If a PlaywrightException is thrown, it indicates that the context is closed
            return true;
        }
    }

    public static void closeBrowserContexts(String contextKey) {
        BrowserContext browserContext = contextThreadLocalMap.get().remove(contextKey);
        if (browserContext != null) {
            browserContext.close();
        }
    }

    public static void closeDefaultBrowserContexts() {
        closeBrowserContexts("default");
    }
}
