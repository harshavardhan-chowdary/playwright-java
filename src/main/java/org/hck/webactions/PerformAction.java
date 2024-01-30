package org.hck.webactions;

import com.microsoft.playwright.Locator;

import java.util.Optional;

public interface PerformAction<T> {
    T perform(Locator loc, String text, Optional<?> options);
}