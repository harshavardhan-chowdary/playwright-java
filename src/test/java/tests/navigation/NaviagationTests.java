package tests.navigation;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

public class NaviagationTests {


    /***
     * launch the browser in fullscreen through Browser context
     */
    @Test(alwaysRun = true)
    public void launchAppInFullScreenUsingScreenSize()   {
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var playwright = Playwright.create();
        var browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false));
        //  .setArgs(List.of("--start-fullscreen"))
        var context = browser.newContext(new Browser.NewContextOptions().setScreenSize(screenSize.width, screenSize.height));
        var page = context.newPage();
        page.navigate("https://demoqa.com/");
        System.out.println(page.title());
        context.close();
        playwright.close();
    }

    /***
     * launch the browser in fullscreen through args only for chromium
     */
    @Test(alwaysRun = true)
    public void launchAppInFullScreenUsingChromiumArgs()   {
        var playwright = Playwright.create();
        var browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false).setArgs(List.of("--start-fullscreen")));
        var context = browser.newContext();
        var page = context.newPage();
        // loads the page and waits for the web page to fire the load event.
        page.navigate("https://demoqa.com/");
        System.out.println(page.title());
        context.close();
        playwright.close();
    }

}
