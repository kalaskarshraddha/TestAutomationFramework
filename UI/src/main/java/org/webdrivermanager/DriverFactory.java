package org.webdrivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> thDriver = new ThreadLocal<>();

    public static WebDriver getDriver(String browserName) {
        if (thDriver.get() == null)
            initDriver(browserName);
        return thDriver.get();
    }

    public static void initDriver(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            if (System.getProperty("headlessMode") != null) {
                if (System.getProperty("headlessMode").equalsIgnoreCase("enabled")) {
                    System.out.println("**********************************");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");                // run without UI
                    options.addArguments("--no-sandbox");              // required in CI
                    options.addArguments("--disable-dev-shm-usage");   // avoid /dev/shm issues
                    options.addArguments("--disable-gpu");             // optional, safer in headless
                    options.addArguments("--window-size=1920,1080");   // set a default size
                    thDriver.set(new ChromeDriver(options));
                }
            } else {
                thDriver.set(new ChromeDriver());
            }
        }
        if (browserName.equalsIgnoreCase("Edge")) {
            thDriver.set(new EdgeDriver());
        }
    }

    public static void quitDriver() {
        WebDriver driver = thDriver.get();
        if (driver != null) {
            driver.quit();
            thDriver.remove();
        }
    }
}
