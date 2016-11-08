package com.company.browser;

import com.company.browser.config.IBrowserThreads;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class EdgeBrowser implements IBrowserThreads {

    @Override
    public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        capabilities.setCapability("requireWindowFocus", true);
        capabilities.setCapability("screenResolution", "1280x1024");
        return addProxySettings(capabilities, proxySettings);
    }

    @Override
    public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
        return new InternetExplorerDriver(capabilities);
    }

}
