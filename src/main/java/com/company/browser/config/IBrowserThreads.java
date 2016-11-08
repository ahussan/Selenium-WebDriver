package com.company.browser.config;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

import static org.openqa.selenium.remote.CapabilityType.PROXY;


/**
 * Created by anjalhussan on 10/22/16.
 */
public interface IBrowserThreads {


    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);

    default DesiredCapabilities addProxySettings(DesiredCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }
        return capabilities;
    }

    default List<String> applyPhantomJSProxySettings(List<String> cliArguments, Proxy proxySettings) {
        if (null == proxySettings) {
            cliArguments.add("--proxy-type=none");
        } else {
            cliArguments.add("--proxy-type=http");
            cliArguments.add("--proxy=" + proxySettings.getHttpProxy());
        }
        return cliArguments;
    }
}
