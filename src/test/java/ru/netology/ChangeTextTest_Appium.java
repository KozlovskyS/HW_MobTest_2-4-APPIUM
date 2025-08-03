package ru.netology;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeTextTest_Appium {

    private AndroidDriver driver;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    private String textToSet = "Netology";
    private String textToSetEmpty = " ";
    private String textToNewActivity = "New Activity";

    @Test
    public void TestEmptyText() {
        MobilePage mainPage = new MobilePage(driver);

        String expected = mainPage.textChanged.getText();
        mainPage.inputField.sendKeys(textToSetEmpty);
        mainPage.buttonChange.click();

        String result = mainPage.textChanged.getText();
        assertEquals(expected, result);
    }

    @Test
    public void TestTextOpenInNewActivity() {
        MobilePage mainPage = new MobilePage(driver);

        mainPage.inputField.sendKeys(textToNewActivity);
        mainPage.buttonActivity.click();

        String result = mainPage.activityText.getText();
        assertEquals(textToNewActivity, result);
        driver.navigate().back();
    }


}
