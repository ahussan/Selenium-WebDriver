package com.company.selectors.id;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

/**
 * Created by anjalhussan on 10/22/16.
 */
public enum HomePageID implements Supplier<By>{
    TEXT_BOX("gs_htif0")
    ;

    private final By by;

    HomePageID(String ID) {
        this.by = id(ID);
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
