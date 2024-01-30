package org.hck.webactions.options;

import com.microsoft.playwright.Locator;
import lombok.*;


@Getter
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Setter(AccessLevel.PUBLIC)
public class InputOptions {
    String textToEnter;
    Locator.FillOptions fillOptions;
    Locator.ClearOptions clearOptions;
    Locator.PressSequentiallyOptions pressSequentiallyOptions;
    boolean clearField = true;
    boolean enterSequentially = false;

}
