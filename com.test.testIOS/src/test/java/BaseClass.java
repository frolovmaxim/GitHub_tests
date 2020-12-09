import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.net.URL;

public class BaseClass {
    AppiumDriver<MobileElement> driver;
    private MFPapp mfpApp;
    ExcelUtils excelUtils;
    HelperMethods account;

    @BeforeMethod (groups = {"signUpTest"})
    public void setup(){
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(CapabilityType.PLATFORM_NAME, "iOS");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.5.1");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "SMiPhone7BI");
            caps.setCapability(CapabilityType.BROWSER_NAME, "safari");
            caps.setCapability(MobileCapabilityType.UDID, "aadb947229a3d926128de75b4dca6670670a6866");
            //caps.setCapability("app", "/Users/maximfrolov/Library/Developer/Xcode/DerivedData/WebDriverAgent-ciegwgvxzxdrqthilmrmczmqvrgu/Build/Products/Debug-iphonesimulator/IntegrationApp.app");
            caps.setCapability("xcodeOrgId","R8K597FG5R");
            caps.setCapability("xcodeSigningId","iPhone Developer");


            /*

            caps.setCapability(MobileCapabilityType.UDID, "F322F28A-6778-492E-9C53-94EE4EEE5D02");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            caps.setCapability("disableWindowAnimation", true);
            caps.setCapability("ignoreUnimportantViews", true);
            caps.setCapability("disableAndroidWatchers", true);
            caps.setCapability("resetKeyboard", true);
            caps.setCapability("unicodeKeyboard", true);
            caps.setCapability("appPackage", "com.myfitnesspal.android");
            caps.setCapability("appActivity", "com.myfitnesspal.android.login.Welcome");*/
            //account = new HelperMethods();
            //excelUtils = new ExcelUtils();
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            IOSDriver driver = new IOSDriver(url, caps);
            //mfpApp = new MFPapp(driver);

        }   catch (Exception exp){
            System.out.println("Cause is : " + exp.getCause());
            System.out.println("Message is : " + exp.getMessage());
            exp.printStackTrace();
        }
    }


    @Test
    public void helloWorld(){
        System.out.println("Hello");
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

    @Test (groups = {"signUpTest"}, enabled = false)
    public void signUpTest(){
        String email = account.getAccountDetails().email;
        String username = account.getAccountDetails().username;
        String password = account.getAccountDetails().password;
        mfpApp.welcomePage().tapSignUpButton();
        mfpApp.signUpPage()
                .tapSignUpWithEmailButton()
                .tapMaintainWeightButton()
                .tapLightlyActiveRadioButton()
                .tapGenderMaleRadioButton()
                .tapBirthDateInputOption().tapConfirmButton()
                .tapZipCodeInputField("34563").tapNextButton()
                .tapHeightInputField().inputHeightEntryField("8")
                .tapOkButton().tapCurrentWeightInputField().inputWeightEntryField("200")
                .tapOkButton().tapNextButton().inputEmailInputField(email)
                .inputPasswordInputField(password).inputUserNameInputField(username).tapSignUpButton();
        mfpApp.accountCreatedPage().tapAccountCreatedButton();
        excelUtils.setCellData(username, email, password);
        Assert.assertTrue(mfpApp.accountCreatedPage().annualSKUButtonIsDisplayed());
    }

    @AfterMethod (groups = {"signUpTest"}, enabled = false)
    public void teardown() throws IOException {
        Runtime.getRuntime().exec("adb shell am force-stop com.myfitnesspal.android");
        Runtime.getRuntime().exec("adb shell pm clear com.myfitnesspal.android");
    }
}
