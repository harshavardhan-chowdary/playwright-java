package tests.inputs;

import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.Test;
import tests.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SelectInputTests extends BaseTest {

    public SelectInputTests() {
        super("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
    }


    @Test
    public void verifySelectValueFromSingleSelect() {
        var selectInpLoc = page.locator("id=select-demo");
        var selectedValueDisplay = page.locator("p.selected-value");
        // Select by value
        selectInpLoc.selectOption(new SelectOption().setValue("Wednesday"));
        assertThat(selectedValueDisplay).containsText("Wednesday");

        selectInpLoc.selectOption("Monday");
        assertThat(selectedValueDisplay).containsText("Monday");

        // Select by label
        selectInpLoc.selectOption(new SelectOption().setLabel("Friday"));
        assertThat(selectedValueDisplay).containsText("Friday");

        // Select by Index
        selectInpLoc.selectOption(new SelectOption().setIndex(2));
        assertThat(selectedValueDisplay).containsText("Monday");

        // get Selected value
        selectInpLoc.selectOption(new SelectOption().setIndex(3));
        String selectedValue = selectInpLoc.evaluate("select => select.value").toString();
        System.out.println(selectedValue);

        // or
        assertThat(selectInpLoc).hasValue("Tuesday");


    }


    @Test
    public void verifySelectValueFromMultiSingleSelect() {
        var selectInpLoc = page.locator("id=multi-select");
        var selectedValueDisplay = page.locator("p.selected-value");

        // Select by value
        selectInpLoc.selectOption(new String[]{"Ohio", "New York", "California"});
        assertThat(selectInpLoc).hasValues(new String[]{"California", "New York", "Ohio"});

        // get All Options
        var options = selectInpLoc.locator("option");
        System.out.println(options.allInnerTexts());
    }

}

