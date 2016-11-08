package com.company.selectors.others;

import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * Created by anjalhussan on 10/22/16.
 */
public enum TagName implements Supplier<By>{

    A("a"),
    EM("em"),
    H1("h1"),
    H5("h5"),
    I("i"),
    P("p"),
    DIV("div"),
    IMG("img"),
    INPUT("input"),
    LI("li"),
    OPTION("option"),
    SPAN("span"),
    STRONG("strong"),
    TABLE("table"),
    TD("td"),
    TR("tr"),
    TH("th"),
    UL("ul");

    private final By tagname;

    TagName(String tagname) {
        this.tagname = By.tagName(tagname);
    }

    @Override
    public By get() {
        return tagname;
    }

    @Override
    public String toString() {
        return tagname.toString();
    }
}
