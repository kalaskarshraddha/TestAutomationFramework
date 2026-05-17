package org.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageFactory {
    private WebDriverWait wait;

    //locate webelement using FindBy annotation
    @FindBy(id = "login_Layer")
    private WebElement lgnBtn;

    @FindBy(xpath = "//input[@placeholder=\"Enter your active Email ID / Username\"]")
    private WebElement usrName;

    @FindBy(xpath = "//*[@placeholder=\"Enter your password\"]")
    private WebElement pwd;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement clickBtn;

    //constructor
    public LoginPageFactory(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);

    }

    public void clickOnLgn() {
        wait.until(ExpectedConditions.elementToBeClickable(lgnBtn)).click();
    }

    public void enterUserName(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usrName)).sendKeys(username);

    }

    public void enterPwd(String password) {
        pwd.sendKeys(password);
    }

    public void clickOnSubmitBtn() {
        clickBtn.click();
    }
}
