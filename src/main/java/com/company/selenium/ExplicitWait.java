package com.company.selenium;
import com.company.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by anjalhussan on 10/22/16.
 */
public interface ExplicitWait extends SearchScope{
    default Element await(Supplier<By> by) {
        return await((SearchScope e) -> e.findElement(by));
    }

    default void await(Predicate<SearchScope> predicate) {
        await((Function<SearchScope, Boolean>) predicate::test);
    }

    default <T> T await(Function<SearchScope, T> function) {
        return new FluentWait<>(this)
                .withTimeout(1, SECONDS)
                .pollingEvery(10, MILLISECONDS)
                .ignoring(Exception.class)
                .until(
                        (SearchScope where) -> function.apply(where)
                );
    }

    default String getText(Supplier<By> by) {
        return await(by).getText();
    }

    default String getUpperText(Supplier<By> by) {
        return await(by).getText().toUpperCase();
    }

    default void click(Supplier<By> by) {
        await(by).click();
    }

    default Element untilFound(Supplier<By> by) {
        waitForPageToLoad();
        return new FluentWait<>(this)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoring(Exception.class)
                .until((ExplicitWait e) -> e.findElement(by));
    }

    default Element untilFound(Supplier<By> by, int duration) {
        waitForPageToLoad();
        return new FluentWait<>(this)
                .withTimeout(duration, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoring(Exception.class)
                .until((ExplicitWait e) -> e.findElement(by));
    }

    default Wait<WebDriver> fluentWait() {
        return new FluentWait<>(TestBase.driver())
                .withTimeout(1, TimeUnit.MINUTES)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoreAll(new ArrayList<Class<? extends Throwable>>() {
                    {
                        add(StaleElementReferenceException.class);
                        add(NoSuchElementException.class);
                        add(TimeoutException.class);
                        add(InvalidElementStateException.class);
                        add(WebDriverException.class);
                    }
                }).withMessage("The message you will see in if a TimeoutException is thrown");
    }

    default void waitForPageToLoad() {
        waitForLoaderToComplete();
        waitForAjaxToComplete();
        waitForJavaScriptToComplete();
    }

    default void waitForLoaderToComplete() {
        Wait<WebDriver> wait = fluentWait();
        wait.until(loaderHasFinishProcessing());
    }

    default void waitForJavaScriptToComplete() {
        Wait<WebDriver> wait = fluentWait();
        wait.until(javaScriptHasFinishProcessing());
    }

    default void waitForAjaxToComplete() {
        Wait<WebDriver> wait = fluentWait();
        wait.until(jQuryHasFinishedProcessing());
    }

    default void waitForAngularToComplete() {
        Wait<WebDriver> wait = fluentWait();
        wait.until(angularHasFinishedProcessing());
    }

    default ExpectedCondition<Boolean> javaScriptHasFinishProcessing() {
        return driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete");
    }

    default ExpectedCondition<Boolean> loaderHasFinishProcessing() {
        return driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return (window.show===false) || (window.show===undefined);");
    }

    default ExpectedCondition<Boolean> jQuryHasFinishedProcessing() {
        return driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
    }

    default ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                .executeScript("return (window.angular !== undefined) &&" +
                        " (angular.element(document).injector() !== undefined) &&" +
                        " (angular.element(document).injector().get('$http')" +
                        ".pendingRequests.length === 0)").toString());
    }
}
