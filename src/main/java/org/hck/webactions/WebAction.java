package org.hck.webactions;

import com.microsoft.playwright.Locator;
import org.hck.webactions.options.InputOptions;

import java.util.Optional;

public class WebAction implements WA {
    private static final TextInputActionsImpl INPUT_ACTIONS = new TextInputActionsImpl();


    @Override
    public void type(Locator loc, String textToEnter, String name, Optional<InputOptions> inputOptions) {
        inputOptions.ifPresent(opt -> opt.setTextToEnter(textToEnter));
        INPUT_ACTIONS.perform(loc, name, inputOptions);
    }

    @Override
    public void type(Locator loc, String textToEnter, String name) {
        WA.super.type(loc, textToEnter, name);
    }

    public void clearAndType(Locator loc, String textToEnter, String name) {
        clearAndType(loc, textToEnter, name,
                Optional.of(InputOptions.builder().setEnterSequentially(true).build()));
    }

    @Override
    public void clearAndType(Locator loc, String textToEnter, String name,
                             Optional<InputOptions> inputOptions) {
        inputOptions.ifPresent(opt -> {
            opt.setClearField(true);
            opt.setTextToEnter(textToEnter);
        });
        INPUT_ACTIONS.perform(loc, name, inputOptions);
    }

    public void typeCharByChar(Locator loc, String textToEnter, String name) {
        typeCharByChar(loc, textToEnter, name, Optional.of(
                InputOptions.builder().setEnterSequentially(true).build()));
    }

    @Override
    public void typeCharByChar(Locator loc, String textToEnter, String name, Optional<InputOptions> inputOptions) {
        inputOptions.ifPresent(opt -> {
            opt.setEnterSequentially(true);
            opt.setTextToEnter(textToEnter);
        });
        INPUT_ACTIONS.perform(loc, name, inputOptions);
    }


    public void clear(Locator loc, String name) {
        clear(loc, name, Optional.of(InputOptions.builder().setClearField(true).build()));
    }

    @Override
    public void clear(Locator loc, String name, Optional<InputOptions> inputOptions) {
        inputOptions.ifPresent(opt -> {
            opt.setClearField(true);
        });
        INPUT_ACTIONS.perform(loc, name, inputOptions);
    }
}

