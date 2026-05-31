package org.naukriautomation;

import org.POM.LoginPageFactory;
import org.POM.MyNaukriPage;
import org.POM.MyProfilePage;
import org.listener.TestListeners;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.retryFailedTests.RetryAnalyzer;
import org.testng.annotations.*;
import org.utils.ExcelUtils;
import org.utils.PropertiesFileReader;
import org.webdrivermanager.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@Listeners(TestListeners.class)
public class NaukriTest {

    private LoginPageFactory loginPageFactory;
    private MyNaukriPage myNaukriPage;
    private MyProfilePage myProfilePage;

    @BeforeMethod
    public void setUp() throws IOException {
        WebDriver driver = DriverFactory.getDriver("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        String appUrl= PropertiesFileReader.getDataFromPropertiesFile("qa","app_url");
        driver.get(appUrl);

        loginPageFactory = new LoginPageFactory(driver);
        myNaukriPage = new MyNaukriPage(driver);
        myProfilePage = new MyProfilePage(driver);
    }

    @Test(dataProvider = "testData", retryAnalyzer = RetryAnalyzer.class)
    public void updateProfile(String username, String pwd) throws IOException {

        loginPageFactory.clickOnLgn();
        loginPageFactory.enterUserName(username);
        loginPageFactory.enterPwd(pwd);
        loginPageFactory.clickOnSubmitBtn();

        myNaukriPage.clickViewProfileBtn();

        myProfilePage.clickEditProfileBtn();
        myProfilePage.clickSaveProfileBtn();
        myProfilePage.checkProfileUpdateConfirmMsg();

        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver("Chrome");
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("target" + File.separator + "fileUpdated.jpg");
        FileHandler.copy(src, dest);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @DataProvider(name = "testData")
    public Object[][] getTestData() throws IOException {
        return ExcelUtils.getTestData("testdata", "testDataForLogin");
    }
}
