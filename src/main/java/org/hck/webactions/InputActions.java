package org.hck.webactions;

import com.microsoft.playwright.Locator;
import org.hck.webactions.options.InputOptions;

import java.util.Optional;

public interface InputActions {

     void type(Locator loc, String textToEnter, String name, Optional<InputOptions> inputOptions);

     default void type(Locator loc, String textToEnter, String name) {
        type(loc, textToEnter, name, Optional.empty());
    }


    void clearAndType(Locator loc, String textToEnter, String name, Optional<InputOptions> inputOptions);


    void typeCharByChar(Locator loc, String textToEnter, String name, Optional<InputOptions> inputOptions);


    void clear(Locator loc, String name, Optional<InputOptions> inputOptions);


}
