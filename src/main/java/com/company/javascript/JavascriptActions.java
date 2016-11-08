package com.company.javascript;

import com.company.selenium.DelegatingWebDriver;
import com.company.selenium.ExplicitWait;
import com.company.selenium.SearchScope;
import org.openqa.selenium.*;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static org.slf4j.LoggerFactory.getLogger;
/**
 * Created by anjalhussan on 10/22/16.
 */
public class JavascriptActions extends DelegatingWebDriver
        implements ExplicitWait, SearchScope,JavascriptExecutor{

    public static final String NO_JQUERY_ERROR = "ReferenceError: $ is not defined";
    Logger logger = getLogger(JavascriptActions.class);
    private WebDriver driver;

    public JavascriptActions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeAsyncScript(script, args);
    }

    public void click(String cssSelector) {
        executeScript("$('" + cssSelector + "').click()");
    }

    public void click(WebElement element) {
        executeScript("$(arguments[0])[0].click()", element);
    }

    public void focus(String cssSelector) {
        executeScript("$('" + cssSelector + "').focus()");
    }

    public void focus(WebElement element) {
        executeScript("$(arguments[0]).focus()", element);
    }

    public Object execute(String script) {
        // Get rid of this wait
        try {
            Object value = executeScript("return " + script);
            Thread.sleep(1000);
            return value;
        } catch (InterruptedException e) {
            logger.info("execute", e, false);
            return null;
        } catch (UnsupportedOperationException e) {
            logger.info("execute", e, true);
            return null;
        }
    }

    public void mouseOver(WebElement element) {
        executeScript("$(arguments[0]).mouseenter()", element);
    }

    public boolean isElementInViewPort(WebElement element) {
        return (Boolean) executeScript(
                "return ($(window).scrollTop() + 60 < $(arguments[0]).offset().top) && ($(window).scrollTop() "
                        + "+ $(window).height() > $(arguments[0]).offset().top + $(arguments[0]).height() + 60)",
                element);
    }

    public void scrollToElement(Supplier<By> elementBy) {
        try {
            executeScript(
                    "var x = $(arguments[0]);" + "window.scroll(0,parseInt(x.offset().top - 250));",
                    driver.findElement(elementBy.get()));
        } catch (WebDriverException e) {
            if (e.getMessage().contains(NO_JQUERY_ERROR)) {
                logger.info("JSError", "JQuery is not defined", false);
            }
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            executeScript(
                    "var x = $(arguments[0]); " +
                            "window.scroll(0,parseInt(x.offset().top - 100));",
                    element
            );
        } catch (WebDriverException e) {
            if (e.getMessage().contains(NO_JQUERY_ERROR)) {
                logger.info("JSError", "JQuery is not defined", false);
            }
        }
    }

    public void scrollToElement(WebElement element, int offset) {
        try {
            executeScript(
                    "var x = $(arguments[0]);" +
                            "window.scroll(0,parseInt(x.offset().top - arguments[1]));",
                    element,
                    offset
            );
        } catch (WebDriverException e) {
            if (e.getMessage().contains(NO_JQUERY_ERROR)) {
                logger.info("JSError", "JQuery is not defined", false);
            }
        }
    }

    public void scrollElementIntoViewPort(WebElement element) {
        if (!isElementInViewPort(element)) {
            scrollToElement(element);
        }
    }
}


