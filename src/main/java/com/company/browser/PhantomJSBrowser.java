package com.company.browser;

import com.company.browser.config.IBrowserThreads;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class PhantomJSBrowser implements IBrowserThreads{

    @Override
    public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        final List<String> cliArguments = new ArrayList<>();
        cliArguments.add("--web-security=false");
        cliArguments.add("--ssl-protocol=any");
        cliArguments.add("--ignore-ssl-errors=true");
        capabilities.setCapability("phantomjs.cli.args", applyPhantomJSProxySettings(cliArguments, proxySettings));
        capabilities.setCapability("takesScreenshot", true);
        capabilities.setCapability("screenResolution", "1280x1024");
        return capabilities;
    }

    @Override
    public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
        return new PhantomJSDriver(capabilities);
    }
}
