package org.hck.managers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class SimpleBrowserManager {
    private static Browser browser;

    public static Browser getBrowserInstance(BrowserName browserType, BrowserType.LaunchOptions launchOptions) {
        if (browser == null || !browser.isConnected()) {
            browser = createBrowserInstance(browserType, launchOptions);
        }
        return browser;
    }

    private static Browser createBrowserInstance(BrowserName browserType, BrowserType.LaunchOptions launchOptions) {
        try {
            Playwright playwright = PlaywrightManager.getPlaywrightInstance();
            return switch (browserType) {
                case CHROMIUM -> playwright.chromium().launch(launchOptions);
                // Add cases for other browsers if needed
                default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            };
        } catch (Exception e) {
            throw new RuntimeException("Failed to launch the browser.", e);
        }
    }

    public static void closeBrowsers() {
        if (browser != null && browser.isConnected()) {
            browser.close();
        }
    }
}
