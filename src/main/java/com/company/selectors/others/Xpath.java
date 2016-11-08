package com.company.selectors.others;

import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * Created by anjalhussan on 10/22/16.
 */
public enum Xpath implements Supplier{
    ;

    private final By by;

    Xpath(String xpath) {
        this.by = By.xpath(xpath);
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
