package com.company.pages;

import com.company.selenium.Browser;
import org.openqa.selenium.WebDriver;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class LogInPage {

    private Browser driver;
    public LogInPage(WebDriver driver){
        this.driver = new Browser(driver);
    }
}
