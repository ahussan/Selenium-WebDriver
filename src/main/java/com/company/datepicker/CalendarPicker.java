package com.company.datepicker;

import com.company.selenium.Browser;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class CalendarPicker {

    private final Browser browser;
    private final Consumer<Browser> previous;
    private final Consumer<Browser> next;
    private final Function<Browser, Integer> displayValue;

    public CalendarPicker(Browser browser,
                          Consumer<Browser> previous,
                          Consumer<Browser> next,
                          Function<Browser, Integer> displayValue) {
        this.browser = browser;
        this.previous = previous;
        this.next = next;
        this.displayValue = displayValue;
    }

    /**
     * @param value the year or month to pick
     */
    void pick(int value) {
        int difference;
        while ((difference = displayValue.apply(browser) - value) != 0) {
            if (difference < 0) {
                for (int i = difference; i < 0; i++) {
                    next.accept(browser);
                }
            } else if (difference > 0) {
                for (int i = 0; i < difference; i++) {
                    previous.accept(browser);
                }
            }
            if (difference == displayValue.apply(browser) - value) {
                break;
            }
        }

    }
}
