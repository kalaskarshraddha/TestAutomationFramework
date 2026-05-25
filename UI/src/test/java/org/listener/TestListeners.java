package org.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.webdrivermanager.DriverFactory;

import java.io.File;
import java.io.IOException;

public class TestListeners implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("target" + File.separator + "failedScrnShot.jpg");
        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
