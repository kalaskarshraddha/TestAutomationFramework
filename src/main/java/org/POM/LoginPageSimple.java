package org.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.webdrivermanager.DriverFactory;

public class LoginPageSimple {
    private WebDriver driver = DriverFactory.getDriver();

    private By lgnBtn = By.id("login_Layer");

    public void clickLogin() {
        driver.findElement(lgnBtn).click();
    }
}
