package tests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ExcelUtils;
import pages.MFPapp;
import java.io.IOException;
import java.net.URL;

public class BaseClass {
    AppiumDriver<MobileElement> driver;
    private MFPapp mfpApp;
    ExcelUtils excelUtils;

    @BeforeMethod
    public void setup(){
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ONEPLUS A6000");
            caps.setCapability(MobileCapabilityType.UDID, "bc53b561");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            caps.setCapability("disableWindowAnimation", true);
            caps.setCapability("ignoreUnimportantViews", true);
            caps.setCapability("disableAndroidWatchers", true);
            caps.setCapability("resetKeyboard", true);
            caps.setCapability("unicodeKeyboard", true);
            caps.setCapability("appPackage", "com.myfitnesspal.android");
            caps.setCapability("appActivity", "com.myfitnesspal.android.login.Welcome");
            excelUtils = new ExcelUtils();
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
        String email = "stranger" + "%d" + "@mail.com";
        String username = "stranger" + "%d";
        int num = (int) (Math.random()*1000000);
        String improvedEmail = String.format(email, num);
        String improvedUsername = String.format(username, num);
        String improvedPassword = "1" + num;

        mfpApp.welcomePage().tapSignUpButton();
        mfpApp.signUpPage().tapSignUpWithEmailButton().tapMaintainWeightButton().tapLightlyActiveRadioButton();
        mfpApp.youPage().tapGenderMaleRadioButton()
                .tapBirthDateInputOption().tapConfirmButton()
                .tapZipCodeInputField("34563").tapNextButton()
                .tapHeightInputField().inputHeightEntryField("8")
                .tapOkButton().tapCurrentWeightInputField().inputWeightEntryField("200")
                .tapOkButton().tapNextButton().inputEmailInputField(improvedEmail)
                .inputPasswordInputField(improvedPassword).inputUserNameInputField(improvedUsername).tapSignUpButton();
        mfpApp.accountCreatedPage().tapAccountCreatedButton();

        excelUtils.setCellData(improvedUsername, improvedEmail, improvedPassword);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void teardown() throws IOException {
        Runtime.getRuntime().exec("adb shell am force-stop com.myfitnesspal.android");
        Runtime.getRuntime().exec("adb shell pm clear com.myfitnesspal.android");
    }
}
