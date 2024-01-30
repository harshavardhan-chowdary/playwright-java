package tests;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import org.hck.managers.BrowserManager;
import org.hck.managers.BrowserName;
import org.hck.managers.PlaywrightWrapper;
import org.hck.webactions.WebAction;
import org.testng.annotations.Test;

public class MyTest {

    @Test
    public void runTest() {
        var launchOptions = new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false);
        Page page = PlaywrightWrapper.getPageInstance(BrowserName.CHROMIUM, launchOptions);

        // Create a new page
        page.navigate("https://www.lambdatest.com/selenium-playground/input-form-demo");
        System.out.println(page.title());

        var loc =page.locator("id=name");
        // Do other interactions with the page if needed
        loc.fill("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        new WebAction().clear(loc, "name");
        new WebAction().type(loc, "name","name");
        // Close the page
        page.close();

        // Close the browser
        BrowserManager.closeDefaultBrowsers();

    }


}
