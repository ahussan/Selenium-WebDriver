package com.company.datepicker;


import com.company.selenium.Browser;

import java.util.function.Consumer;

/**
 * Created by anjalhussan on 10/22/16.
 *
 * A general purpose DatePicker can be used to pick a given date from
 * the calendar flyout provided by JavaScript framework.
 *
 */
public class Calendar {


    private final Browser browser;
    private final Consumer<Browser> trigger;

    /**
     * Constructor of the DatePicker which taking a Calendar interface.
     *
     * @param browser
     * @param trigger
     */
    public Calendar(Browser browser, Consumer<Browser> trigger) {
        this.browser = browser;
        this.trigger = trigger;
    }

    /**
     * Display the calendar
     */
    public void show() {
        trigger.accept(browser);
    }
}
