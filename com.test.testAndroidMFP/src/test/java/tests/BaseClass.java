package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseClass {
    AppiumDriver<MobileElement> driver;
    MobileElement loginButton;
    MobileElement emailAddressInput;
    MobileElement passwordInput;
    MobileElement loginButtonSecond;
    MobileElement forgotPassword;
    MobileElement forgotPasswordText;

    public static WebElement waitElementToBeClickableByLocator(
            WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @BeforeTest
    public void setup(){
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            //caps.setCapability("platformName", "ANDROID");
            caps.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
            //caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ONEPLUS A6000");
            caps.setCapability(MobileCapabilityType.UDID, "bc53b561");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);

            caps.setCapability("appPackage", "com.myfitnesspal.android");
            caps.setCapability("appActivity", "com.myfitnesspal.android.login.Welcome");

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver<MobileElement>(url, caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            loginButton = driver.findElement(By.xpath("//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/btnSignIn']"));
            forgotPassword = driver.findElement(By.xpath("//android.widget.TextView[@resource-id = 'com.myfitnesspal.android:id/forgot_password_button']"));
            forgotPasswordText = driver.findElement(By.xpath("//android.widget.EditText[text() = 'Enter email']"));

        }   catch (Exception exp){
            System.out.println("Cause is : " + exp.getCause());
            System.out.println("Message is : " + exp.getMessage());
            exp.printStackTrace();
        }

    }

    @Test
    public void sampleTest() {
        loginButton.click();
        waitElementToBeClickableByLocator(driver, By.xpath("//android.widget.TextView[@resource-id = 'com.myfitnesspal.android:id/forgot_password_button']")).click();
        String text = waitElementToBeClickableByLocator(driver, By.xpath("//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/btnDone']")).getText();
        System.out.println(text);
        Assert.assertEquals(text, "Submit");
    }


    @AfterTest
    public void teardown(){
        driver.close();
        driver.quit();
    }
}
