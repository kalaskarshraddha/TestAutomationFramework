package org.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webdrivermanager.DriverFactory;

import java.time.Duration;

public class LoginPageSimple {
    private final WebDriver driver = DriverFactory.getDriver("Chrome");
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    private By lgnBtn = By.id("login_Layer");

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(lgnBtn)).click();
        //driver.findElement(lgnBtn).click();
    }
}
