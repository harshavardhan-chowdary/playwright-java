package tests.inputs;

import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckboxInputTests extends BaseTest {

    public CheckboxInputTests(String url) {
        super("https://www.lambdatest.com/selenium-playground/checkbox-demo");
    }

    @Test
    public void verifySelectionOfCheckBox() {
        assertThat(page.locator("id=isAgeSelected"))
                .not().isChecked();
        page.locator("id=isAgeSelected")
                .check();
        assertThat(page.locator("id=isAgeSelected"))
                .isChecked();
    }


    @Test
    public void verifyListOfCheckBoxOptions() {
        Locator checkBoxLabels =page.locator("//div[text()='Multiple Checkbox Demo']/following-sibling::div//label");

        // Get the count of elements present
        System.out.println(checkBoxLabels.count());
        assertThat(checkBoxLabels).hasCount(4);

        // Nth content filter
        String textContent = checkBoxLabels.nth(1).textContent();
        System.out.println(textContent);
        assertThat(checkBoxLabels.nth(1)).hasText("Option 2");

        // Display a list of texts
        List<String> allLabels = checkBoxLabels.allTextContents();
        System.out.println(allLabels);
        assertThat(checkBoxLabels).hasText(new String[] {"Option 1", "Option 2", "Option 3", "Option 4"});

        checkBoxLabels.nth(2).check();
        assertThat(checkBoxLabels.nth(2))
                .isChecked();
    }

}
