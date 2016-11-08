package com.company.selenium;

import com.company.javascript.JavascriptActions;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class Browser extends DelegatingWebDriver implements FormElements {


    public static final Logger log = getLogger(Browser.class);
    private Action action;
    private JavascriptActions javascript;
    private MultiSelect select;

    public Browser(WebDriver driver) {
        super(driver);
        this.action = new Action(driver);
        this.javascript = new JavascriptActions(driver);
        this.select = new MultiSelect(driver);
    }

    public void delayFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public DatePicker getDate() {
        return new DatePicker();
    }

    public JavascriptActions getJavascript() {
        return javascript;
    }

    public Action getAction() {
        return action;
    }

    public MultiSelect getSelect() {
        return select;
    }

    public void waitForAngularRequestsToFinish() {
        javascript.executeAsyncScript("var callback = arguments[arguments.length - 1];\n" +
                "var rootSelector = 'body';\n" +
                "\n" +
                Angular.functions.get("waitForAngular"));
    }

    public WebElement waitForElementDisplayed(final Supplier<By> by,
                                              final int timeToWaitInSec) {
        Wait<Browser> wait = new FluentWait<>(this)
                .withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoring(WebDriverException.class);
        WebElement foo = wait.until((Function<Browser, WebElement>) (Browser driver) -> {
            try {
                Element element = driver.findElement(by);
                if (element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {

            }
            return null;
        });
        return foo;
    }
}
