package org.autosuggetsivedropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.webdrivermanager.DriverFactory;

import java.time.Duration;
import java.util.List;

public class DynamicDropDownTest {
    @Test
    public void dynamicDropDownTest(){
        WebDriver driver= DriverFactory.getDriver("Chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("//*[@aria-label=\"Search\"]")).sendKeys("india");

        List<WebElement> options=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@role=\"listbox\"]/li")));
        for(WebElement option: options){
            if(option.getText().equalsIgnoreCase("Indian Premier League")){
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                break;
            }

        }

    }
}
