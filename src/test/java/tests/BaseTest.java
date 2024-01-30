package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    String url;
    public  BaseTest(String url){
        this.url =url;
    }

    // New instance for each test method.
    public BrowserContext context;
    public Page page;
    Playwright playwright;
    Browser browser;

    @BeforeClass
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false)
                .setArgs(List.of("--start-fullscreen")));
    }

    @AfterClass
    public void closeBrowser() {
        playwright.close();
    }

    private Properties readProperties() {
        Properties props = new Properties();
        try {
            props.load(BaseTest.class.getClassLoader().getResourceAsStream("config/testconfig.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load .properties from supplied path" + e);
        }
        return props;
    }

    @BeforeMethod
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        var props = readProperties();
        page.navigate(this.url);
    }

    @AfterMethod
    public void closeContext() {
        page.close();
        context.close();
    }


}
