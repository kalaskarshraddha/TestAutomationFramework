package org.naukriautomation;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.webdrivermanager.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class ParallelExecution {
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.manage().window().maximize();
    }


    @Test
    public void test1() throws IOException {
        System.out.println("test 1");
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.naukri.com/");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("target" + File.separator + "fileUpdated1.jpg");
        FileHandler.copy(src, dest);
    }

    @Test
    public void test2() throws IOException {
        System.out.println("test 2");
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.google.com/");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("target" + File.separator + "fileUpdated2.jpg");
        FileHandler.copy(src, dest);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
