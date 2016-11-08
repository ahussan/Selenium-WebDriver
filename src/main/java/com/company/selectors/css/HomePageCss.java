package com.company.selectors.css;

import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * Created by anjalhussan on 10/22/16.
 */
public enum HomePageCss implements Supplier<By> {
    samplecss("#sample > tbody > tr > td:nth-of-type(3)"),;


    private final By by;

    HomePageCss(String by) {
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
