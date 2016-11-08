package com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class DelegatingSearchContext <T extends SearchContext>
                implements ExplicitWait{
    protected final T delegate;

    public DelegatingSearchContext(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.delegate.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.delegate.findElement(by);
    }
}
