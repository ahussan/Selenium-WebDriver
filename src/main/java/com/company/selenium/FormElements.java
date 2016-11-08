package com.company.selenium;

import com.company.algorithm.Retry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static com.company.selectors.others.TagName.OPTION;
import static com.company.selenium.Browser.log;
/**
 * Created by anjalhussan on 10/22/16.
 */
public interface FormElements extends ExplicitWait {
    default void textInput(Supplier<By> by, String value) {
        Retry retry = new Retry(5, 1, TimeUnit.SECONDS);
        try {
            retry.attempt(
                    () -> {
                        Element element = findElement(by);
                        element.clear();
                        element.sendKeys(value);
//                      assert value.equals(element.getAttribute("value"));
                    }
            );
        } catch (Exception e) {
            log.info("Failed to set text {}", value);
        }
    }

    default void textInput(WebElement element, String value) {
        Retry retry = new Retry(5, 1, TimeUnit.SECONDS);
        try {
            retry.attempt(
                    () -> {
                        element.clear();
                        element.sendKeys(value);
//                      assert value.equals(element.getAttribute("value"));
                    }
            );
        } catch (Exception e) {
            log.info("Failed to set text {}", value);
        }
    }

    default String getInputText(Supplier<By> by) {
        return await(by).getValue();
    }

    default void setCheckboxValue(Supplier<By> by, boolean value) {
        Element checkbox = await(by);
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    default boolean isChecked(Supplier<By> by) {
        return await(by).isSelected();
    }

    default void setRadio(Supplier<By> by, Object value) {
        Optional<Element> radio =
                findElements(by)
                        .filter(e -> e.getValue().equals(value.toString()))
                        .findFirst();
        if (radio.isPresent()) {
            radio.get().click();
        } else {
            throw new IllegalArgumentException(
                    "unable to find element with value " + value);
        }

    }

    default String getRadio(Supplier<By> by) {
        Optional<Element> radio =
                findElements(by)
                        .filter(e -> Boolean.valueOf(e.getAttribute("checked")))
                        .findFirst();

        return radio.isPresent() ? radio.get().getValue() : null;

    }

    default Select getSelect(Supplier<By> by) {
        Element element = await(by);
        await((SearchScope driver) -> {
            element.click();
            return element.findElements(OPTION).count() != 0;
        });
        return new Select(element);
    }

    default void select(Supplier<By> by, Object select) {
        getSelect(by).selectByVisibleText(select.toString());
        try {
            if (!getSelect(by)
                    .getFirstSelectedOption()
                    .getText()
                    .equals(select.toString())) {
                getSelect(by)
                        .getOptions()
                        .stream()
                        .filter(
                                (WebElement e) ->
                                        e.getText().equals(select.toString()))
                        .findFirst()
                        .get()
                        .click();
            }
        } catch (Exception e) {
            //Don't need to handle it.
        }
    }
}
