package org.webdrivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> thDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (thDriver.get() == null)
            initDriver("Chrome");
        return thDriver.get();
    }

    public static void initDriver(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome"))
            thDriver.set(new ChromeDriver());
        if (browserName.equalsIgnoreCase("Edge"))
            thDriver.set(new EdgeDriver());
    }

    public static void quitDriver() {
        getDriver().quit();
        thDriver.remove();
    }
}
