package com.company.datepicker;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class DatePicker {


    private final Calendar calendar;
    private final CalendarPicker yearPicker;
    private final CalendarPicker monthPicker;
    private final DayPicker dayPicker;

    /**
     * Constructor of the Datepicker
     *
     * @param calendar    calendar
     * @param yearPicker  yearPicker
     * @param monthPicker monthPicker
     * @param dayPicker   dayPicker
     */
    public DatePicker(Calendar calendar,
                      CalendarPicker yearPicker,
                      CalendarPicker monthPicker,
                      DayPicker dayPicker) {
        this.calendar = calendar;
        this.yearPicker = yearPicker;
        this.monthPicker = monthPicker;
        this.dayPicker = dayPicker;
    }   //<5>

    /**
     * Pick a date by the given parameter.
     * for example,
     * datePicker.pick(Month.JULY, 31, 1999)
     *
     * @param month it need to be defined as an enum to make the code cleaner.
     * @param day   an integer representing the day appearing on the calendar
     * @param year  an integer representing the year appearing on the calendar
     */
    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month, day);
        calendar.show();
        yearPicker.pick(year);
        monthPicker.pick(month.ordinal());
        dayPicker.pick(day);
    }
}
