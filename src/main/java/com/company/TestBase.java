package com.company;

import com.company.browser.config.BrowserThreads;
import com.company.properties.PropertiesUtil;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by anjalhussan on 10/22/16.
 */
public class TestBase {

    private static List<BrowserThreads> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<BrowserThreads> driverThread;


    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<BrowserThreads>() {
            @Override
            protected BrowserThreads initialValue() {
                BrowserThreads webDriverThread = new BrowserThreads();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    public static String getProperties(Repository repository) {
        return PropertiesUtil.create(Repository.LOCATION.getValue()).data(repository);
    }

    public static WebDriver driver() {
        return driverThread.get().getDriver();
    }

    public static WebDriver browserMobProxyEnabledDriver() {
        return driverThread.get().getBrowserMobProxyEnabledDriver();
    }

    public static BrowserMobProxy browserMobProxy() {
        return driverThread.get().getBrowserMobProxy();
    }

    public static <T> T pageFactory(Class<T> clazz) {
        return PageFactory.initElements(driver(), clazz);
    }


    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        try {
            TestBase.driver().manage().deleteAllCookies();
            TestBase.driver().navigate().refresh();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @AfterSuite(alwaysRun = true)
    public static void quitBrowser() {
        webDriverThreadPool.forEach(BrowserThreads::quitDriver);
    }


}
