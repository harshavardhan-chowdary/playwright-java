package org.hck.managers;

import com.microsoft.playwright.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PlaywrightManager {
    private static final ThreadLocal<Playwright> playwrightThreadLocal = ThreadLocal.withInitial(() -> {
        synchronized (PlaywrightManager.class) {
            return Playwright.create();
        }
    });

    public static Playwright getPlaywrightInstance() {
        return playwrightThreadLocal.get();
    }

    public static void closePlaywright() {
        Playwright playwright = playwrightThreadLocal.get();
        if (playwright != null) {
            playwright.close();
            playwrightThreadLocal.remove();
        }
    }
}