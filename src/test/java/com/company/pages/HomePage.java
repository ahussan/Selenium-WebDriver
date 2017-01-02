package com.company.pages;

import com.company.selenium.Browser;
import org.openqa.selenium.WebDriver;

import static com.company.TestBase.pageFactory;
import static com.company.selectors.id.HomePageID.*;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class HomePage {

    private Browser driver;

    public HomePage(WebDriver driver) {
        this.driver = new Browser(driver);
    }

    public HomePage navigateToSite(String url){
        driver.navigate().to(url);
        driver.getJavascript().waitForPageToLoad();
        return pageFactory(HomePage.class);
    }

    public HomePage writeInTextBox(){
        driver.untilFound(TEXT_BOX);
        driver.findElement(TEXT_BOX).sendKeys("Test Text");
        return this;
    }

    public LogInPage goToLogInPage(){
        return pageFactory(LogInPage.class);
    }
}
