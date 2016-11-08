package com.company;

/**
 * Created by anjalhussan on 10/22/16.
 */
public enum Repository {


    /**
     * Browser settings
     */

    CHROME("chrome"),
    FIREFOX("firefox"),
    SAFARI("safari"),
    IE("ie"),
    EDGE("edge"),
    OPERA("opera"),
    PHANTOMJS("phantomjs"),
    HTML_UNIT("htmlunit"),

    /**
     * Environment settings
     */
    QA_ENVIRONMENT("https://qa.company.com/login"),


    /**
     * Properties file settings
     */
    LOCATION("src/test/resources/com.company/selenium.properties"),
    BROWSER("browser"),
    URL("url"),;



    /*
     **********************************************************************************
     */




    private String value;

    Repository(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
