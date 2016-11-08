package com.company.selenium;

import org.openqa.selenium.WebElement;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class Element extends DelegatingWebElement{
    public Element(WebElement delegate) {
        super(delegate);
    }

    @Override
    public String toString() {
        String tagName = delegate.getTagName();
        return "[Element: " + (tagName.equals("input") ?
                delegate.getAttribute("value") : tagName.equals("img") ?
                delegate.getAttribute("src") : delegate.getText()) + "] wrapping " + delegate;
    }

}
