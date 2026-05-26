package org.webdrivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> thDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (thDriver.get() == null)
            initDriver("Chrome");
        return thDriver.get();
    }

    public static void initDriver(String browserName) {
        if (System.getProperty("headlessMode") != null) {
            if (System.getProperty("headlessMode").equalsIgnoreCase("enabled")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");                // run without UI
                options.addArguments("--no-sandbox");              // required in CI
                options.addArguments("--disable-dev-shm-usage");   // avoid /dev/shm issues
                options.addArguments("--disable-gpu");             // optional, safer in headless
                options.addArguments("--window-size=1920,1080");   // set a default size

                if (browserName.equalsIgnoreCase("Chrome"))
                    thDriver.set(new ChromeDriver(options));
            }
        }
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
