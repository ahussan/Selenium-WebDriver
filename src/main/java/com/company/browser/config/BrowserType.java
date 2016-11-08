package com.company.browser.config;

import com.company.browser.*;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Created by anjalhussan on 10/22/16.
 */
public enum  BrowserType {
    FIREFOX(FirefoxBrowser::new),
    CHROME(ChromeBrowser::new),
    IE(IEBrowser::new),
    SAFARI(SafariBrowser::new),
    OPERA(OperaBrowser::new),
    PHANTOMJS(PhantomJSBrowser::new);

    public final IBrowserThreads browser;

    BrowserType(Supplier<IBrowserThreads> browser) {
        this.browser = requireNonNull(browser).get();
    }
}
