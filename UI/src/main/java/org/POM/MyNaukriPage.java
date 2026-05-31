package org.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyNaukriPage {

    @FindBy(xpath = "//*[@class=\"view-profile-wrapper\"]/child::a")
    private WebElement viewProfBtn;

//constructor

    public MyNaukriPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void clickViewProfileBtn(){
        viewProfBtn.click();
    }




}
