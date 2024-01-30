package org.hck.webactions;

import com.microsoft.playwright.Locator;
import org.hck.webactions.options.InputOptions;

import java.util.Objects;
import java.util.Optional;


public class TextInputActionsImpl implements PerformAction<WA> {

    @Override
    public WA perform(Locator loc, String text, Optional<?> options) {
        if (options.isPresent() && options.get() instanceof InputOptions inputOptions) {
            var textToEnter = inputOptions.getTextToEnter();

            if (textToEnter == null) {
                clearInputField(loc, inputOptions.getClearOptions());
            } else {
                if (inputOptions.isClearField()) {
                    clearInputField(loc, inputOptions.getClearOptions());
                }
                if (inputOptions.isEnterSequentially()) {
                    setTextCharByChar(loc, text, inputOptions.getPressSequentiallyOptions());
                } else {
                    setText(loc, text, inputOptions.getFillOptions());
                }
            }
        } else {
            setText(loc, text, null);
        }
        return null;
    }


    private void clearInputField(Locator loc, Locator.ClearOptions clearOptions) {
        if (Objects.nonNull(clearOptions)) {
            loc.clear(clearOptions);
        } else {
            loc.clear();
        }
    }

    private void setText(Locator loc, String textToEnter, Locator.FillOptions fillOptions) {
        if (Objects.nonNull(fillOptions)) {
            loc.fill(textToEnter, fillOptions);
        } else {
            loc.fill(textToEnter);
        }

    }

    private void setTextCharByChar(Locator loc, String textToEnter, Locator.PressSequentiallyOptions pressSequentiallyOptions) {
        if (Objects.nonNull(pressSequentiallyOptions)) {
            loc.pressSequentially(textToEnter, pressSequentiallyOptions);
        } else {
            loc.pressSequentially(textToEnter);
        }
    }

}


