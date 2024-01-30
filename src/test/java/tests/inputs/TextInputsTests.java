package tests.inputs;

import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;
import tests.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TextInputsTests extends BaseTest {

    public TextInputsTests(String url) {
        super("https://www.lambdatest.com/selenium-playground/simple-form-demo");
    }

    @Test
    public void verifyTextInput() {
        page.getByPlaceholder("Please enter your Message")
                .fill("HARSHA");
        page.getByText("Get Checked Value").click();
        assertThat(page.locator("//p[@id='message']"))
                .containsText("HARSHA");
    }

    @Test
    public void verifyTextInputByEnteringCharByChar() {
        page.getByPlaceholder("Please enter your Message")
                .pressSequentially("HARSHA", new Locator.PressSequentiallyOptions().setDelay(100));
        page.getByText("Get Checked Value").click();
        assertThat(page.locator("//p[@id='message']"))
                .hasText("HARSHA");
    }


}
