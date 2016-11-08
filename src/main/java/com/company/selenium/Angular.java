package com.company.selenium;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by anjalhussan on 10/22/16.
 */
public class Angular {

    protected static final Map<String, String> functions = new HashMap<>();

    static {
        // in a Jar
        InputStream resourceAsStream = Angular.class.getResourceAsStream("/js/clientsidescripts.js");
        if (resourceAsStream == null) {
            try {
                resourceAsStream = new FileInputStream(new File("src/main/resources/js/clientsidescripts.js"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Could not find clientsidescripts.js in file system or in jar", e);
            }
        }
        String src = new Scanner(resourceAsStream, "UTF-8").useDelimiter("\\A").next();
        iterateOverJsFunctionsInSource(src);

        inlineUtilityFunctions();


    }

    private Angular() {
    }

    private static void iterateOverJsFunctionsInSource(String src) {
        Pattern ps = Pattern.compile("^function.* \\{$", Pattern.MULTILINE);
        Pattern pe = Pattern.compile("^\\}", Pattern.MULTILINE);
        Matcher m = ps.matcher(src);
        boolean more = true;
        while (more && m.find()) {
            src = src.substring(m.start());
            Matcher m2 = pe.matcher(src);
            if (m2.find()) {
                String body = src.substring(0, m2.start());
                storeJavaScriptFunction(body);
                src = src.substring(body.length());
                m = ps.matcher(src);
            } else {
                more = false;
            }
        }
    }

    private static void inlineUtilityFunctions() {
        Set<String> keys = functions.keySet();
        for (String key : keys) {
            String val = functions.get(key);
            if (!key.equals("repeaterMatch") && val.indexOf("repeaterMatch") > 0) {
                val = "var repeaterMatch = function(ngRepeat, repeater, exact) {" + functions.get("repeaterMatch") + "}\n" + val;
                functions.put(key, val);
            }
        }
    }

    private static void storeJavaScriptFunction(String body) {
        Pattern regFn = Pattern.compile("^function ([a-zA-Z]+)\\(", Pattern.MULTILINE);
        Matcher m = regFn.matcher(body);
        String fnName;
        if (m.find()) {
            fnName = m.group(1);
        } else {
            Pattern fnPro = Pattern.compile("^functions\\.([a-zA-Z0-9]+) ", Pattern.MULTILINE);
            Matcher m2 = fnPro.matcher(body);
            if (m2.find()) {
                fnName = m2.group(1);
            } else {
                throw new UnsupportedOperationException(body.substring(0, 40));
            }
        }
        functions.put(fnName, body.substring(body.indexOf("{") + 1));
    }

    protected abstract static class BaseBy extends By {

        private final JavascriptExecutor getJavascriptExecutor(SearchContext context) {
            JavascriptExecutor jse;
            if (context instanceof RemoteWebElement) {
                jse = (JavascriptExecutor) ((RemoteWebElement) context).getWrappedDriver();
            } else {
                jse = (JavascriptExecutor) context;
            }
            return jse;
        }

        protected final Object errorIfNull(Object o) {
            if (o == null || o instanceof List && ((List) o).size() == 0) {
                throw new org.openqa.selenium.NoSuchElementException(this + " didn't have any matching elements at this place in the DOM");
            }
            return o;
        }

        @Override
        public WebElement findElement(SearchContext context) {
            JavascriptExecutor javascriptExecutor = getJavascriptExecutor(context);
            if (context instanceof WebDriver) {
                context = null;
            }
            return ((List<WebElement>) errorIfNull(getObject(context, javascriptExecutor))).get(0);
        }

        protected abstract Object getObject(SearchContext context, JavascriptExecutor javascriptExecutor);

        @Override
        public List<WebElement> findElements(SearchContext context) {
            JavascriptExecutor javascriptExecutor = getJavascriptExecutor(context);
            if (context instanceof WebDriver) {
                context = null;
            }
            return (List<WebElement>) errorIfNull(getObject(context, javascriptExecutor));
        }
    }
}
