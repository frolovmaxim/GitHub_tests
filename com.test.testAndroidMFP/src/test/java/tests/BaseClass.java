package tests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MFPapp;
import java.io.IOException;
import java.net.URL;

public class BaseClass {
    AppiumDriver<MobileElement> driver;
    private MFPapp mfpApp;

    @BeforeMethod
    public void setup(){
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ONEPLUS A6000");
            caps.setCapability(MobileCapabilityType.UDID, "bc53b561");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            caps.setCapability("appPackage", "com.myfitnesspal.android");
            caps.setCapability("appActivity", "com.myfitnesspal.android.login.Welcome");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver<MobileElement>(url, caps);
            mfpApp = new MFPapp(driver);

        }   catch (Exception exp){
            System.out.println("Cause is : " + exp.getCause());
            System.out.println("Message is : " + exp.getMessage());
            exp.printStackTrace();
        }

    }

    @Test(enabled = false)
    public void sampleTest() {
        mfpApp.welcomePage().clickLoginButton();
        mfpApp.loginPage().tapForgotPasswordLink();
        String text = mfpApp.forgotPasswordPage().getButtonText();
        System.out.println(text);
        Assert.assertEquals(text, "Submit");
    }

    @Test(enabled = false)
    public void loginTest(){
        mfpApp.welcomePage().clickLoginButton();
        mfpApp.loginPage().clearUsernameField().inputUsername("akaza400").inputPassword("111111").tapLoginButton();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void signUpTest(){
        mfpApp.welcomePage().tapSignUpButton();
        mfpApp.signUpPage().tapSignUpWithEmailButton();
        mfpApp.goalPage().tapMaintainWeightButton();
        mfpApp.activityLevelPage().tapLightlyActiveRadioButton();
        mfpApp.youPage().tapGenderMaleRadioButton().tapBirthDateInputOption().tapOkButton().tapZipCodeInputField("34563").tapNextButton().tapHeightInputField().inputHeightEntryField("8").tapOkButton().tapCurrentWeightInputField().inputWeightEntryField("200").tapOkButton().tapNextButton().inputEmailInputField("gdfgdfgdfg@dfgdfgfdfg.dfgdf").inputPasswordInputField("gyhfhdfhd").inputUserNameInputField("fargo1231231231").tapSignUpButton();
        mfpApp.accountCreatedPage().tapAccountCreatedButton();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void teardown() throws IOException {
        Runtime.getRuntime().exec("adb shell am force-stop com.myfitnesspal.android");

    }
}
