package com.company.tests;

import com.company.TestBase;
import com.company.pages.HomePage;
import org.testng.annotations.Test;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class BasicTest extends TestBase {
    @Test
    public void googleSearch() throws Exception {
        HomePage page = pageFactory(HomePage.class);
        page.navigateToSite("https://www.google.com")
            .writeInTextBox()
                .goToLogInPage();

    }
}
