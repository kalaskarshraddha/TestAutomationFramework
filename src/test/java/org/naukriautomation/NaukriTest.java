package org.naukriautomation;

import org.POM.LoginPageFactory;
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
    private LoginPageFactory loginPageFactory;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.naukri.com/");

        loginPageFactory = new LoginPageFactory(driver);
    }

    @Test
    public void updateProfile() throws IOException {
        WebDriver driver = DriverFactory.getDriver();

        //click on login button and enter username and password
        //LoginPageSimple loginPageSimple = new LoginPageSimple();
        //loginPageSimple.clickLogin();

        loginPageFactory.clickOnLgn();
        loginPageFactory.enterUserName();
        loginPageFactory.enterPwd();
        loginPageFactory.clickOnSubmitBtn();

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
