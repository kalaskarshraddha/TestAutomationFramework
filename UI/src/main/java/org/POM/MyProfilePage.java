package org.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MyProfilePage {
    private WebDriverWait wait;

    @FindBy(xpath = "//em[@class=\"icon edit \"]")
    private WebElement editProfileBtn;

    @FindBy(id = "saveBasicDetailsBtn")
    private WebElement saveProfileBtn;

    @FindBy(xpath = "//*[text()='Profile updated successfully']")
    private WebElement profileUpdateConfirmMsg;

    public MyProfilePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void clickEditProfileBtn() {
        editProfileBtn.click();
    }

    public void clickSaveProfileBtn() {
        saveProfileBtn.click();
    }

    public void checkProfileUpdateConfirmMsg() {
        String confirmMsg = wait.until(ExpectedConditions.visibilityOf(profileUpdateConfirmMsg)).getText();
        Assert.assertEquals(confirmMsg, "Profile updated successfully");
    }
}
