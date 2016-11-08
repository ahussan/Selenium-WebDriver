package com.company.browser;

import com.company.browser.config.IBrowserThreads;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class SafariBrowser implements IBrowserThreads{
    @Override
    public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
        DesiredCapabilities capabilities = DesiredCapabilities.safari();
        capabilities.setCapability("safari.cleanSession", true);
        capabilities.setCapability("screenResolution", "1280x1024");
        return addProxySettings(capabilities, proxySettings);
    }

    @Override
    public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
        return new SafariDriver(capabilities);
    }
}
