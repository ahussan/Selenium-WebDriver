package com.company.selectors.others;

import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * Created by anjalhussan on 10/22/16.
 */
public enum ClassName implements Supplier<By>{
    ;

    private final By by;

    ClassName(String by) {
        this.by = By.className(by);
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
