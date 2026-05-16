package org.naukriautomation;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.webdrivermanager.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class NaukriTest {

    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void updateProfile() throws IOException {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.naukri.com/");
        driver.manage().window().maximize();

        //click on login button and enter username and password
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_Layer"))).click();
        driver.findElement(By.xpath("//input[@placeholder=\"Enter your active Email ID / Username\"]")).sendKeys("unmeshtemkar@zohomail.in");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"Enter your active Email ID / Username\"]"))).sendKeys("unmeshtemkar@zohomail.in");
        driver.findElement(By.xpath("//*[@placeholder=\"Enter your password\"]")).sendKeys("1995@umaaa");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        driver.findElement(By.xpath("//*[@class=\"view-profile-wrapper\"]/child::a")).click();
        driver.findElement(By.xpath("//em[@class=\"icon edit \"]")).click();
        driver.findElement(By.id("saveBasicDetailsBtn")).click();


        String confirmMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Profile updated successfully']"))).getText();
        Assert.assertEquals(confirmMsg, "Profile updated successfully");


        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("target" + File.separator + "fileUpdated.jpg");
        FileHandler.copy(src, dest);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
