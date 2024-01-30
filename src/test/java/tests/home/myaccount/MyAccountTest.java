package tests.home.myaccount;

import org.testng.annotations.Test;
import tests.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MyAccountTest extends BaseTest {

    public MyAccountTest() {
        super("https://ecommerce-playground.lambdatest.io/");
    }

    @Test
    public void verifyInvalidLogin() {
        page.locator("//a[contains(.,'My account')][@role='button']").hover();
        page.locator("//a[contains(.,'Login')]").click();
        assertThat(page).hasTitle("Account Login");

        page.getByPlaceholder("E-Mail Address").fill("Harshavardhan@gmail.com");
        page.getByPlaceholder("Password").fill("Test@1234");
        page.locator("//input[@value='Login']").click();
        assertThat(page.locator("//div[contains(@class,'alert-danger')]"))
                .containsText("Warning: No match for E-Mail Address and/or Password.");

    }
}
