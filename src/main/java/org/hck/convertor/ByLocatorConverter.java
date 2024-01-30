package org.hck.convertor;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.openqa.selenium.By;

public class ByLocatorConverter {

    public static Locator locator(Page page, By seleniumLocator) {
        String locatorString = seleniumLocator.toString();
        String strategy = seleniumLocator.getClass().getSimpleName();
        String value = locatorString.substring(locatorString.indexOf(":") + 2).trim();

        return switch (strategy) {
            case "ByClassName" -> page.locator("." + value);
            case "ByCssSelector" -> page.locator("css=" + value);
            case "ById" -> page.locator("#" + value);
            case "ByLinkText", "ByPartialLinkText" -> page.locator("text=" + value);
            case "ByName" -> page.locator("[name='" + value + "']");
            case "ByTagName" -> page.locator(value);
            case "ByXPath" -> page.locator("xpath=" + value);
            default -> throw new IllegalArgumentException("Unsupported Selenium locator: " + strategy);
        };
    }


}