package com.company.selectors.others;

import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * Created by anjalhussan on 10/22/16.
 */
public enum CssSelector implements Supplier<By> {
    ;

    private final By by;

    CssSelector(String by) {
        this.by = By.cssSelector(by);
    }

    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}