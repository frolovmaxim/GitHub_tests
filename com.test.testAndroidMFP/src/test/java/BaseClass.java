import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
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

    @BeforeMethod (groups = {"signUpPlusAPP", "signUpPlusMPP"})
    public void setup(){
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ONEPLUS A6000");
            caps.setCapability(MobileCapabilityType.UDID, "bc53b561");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
            caps.setCapability("disableWindowAnimation", true);
            caps.setCapability("ignoreUnimportantViews", true);
            caps.setCapability("disableAndroidWatchers", true);
            caps.setCapability("resetKeyboard", true);
            caps.setCapability("unicodeKeyboard", true);
            caps.setCapability("appPackage", "com.myfitnesspal.android");
            //caps.setCapability("appWaitActivity", "com.myfitnesspal.android.login.*");
            caps.setCapability("appActivity", "com.myfitnesspal.android.login.Welcome");
            account = new HelperMethods();
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

    @Test (groups = {"signUpPlusAPP", "signUpPlusMPP"}, priority = 1)
    public void signUpTest(){
        String improvedPassword = account.getAccountDetails().password;
        String improvedEmail = account.getAccountDetails().email;
        String improvedUsername = account.getAccountDetails().username;

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
                .tapOkButton().tapNextButton().inputEmailInputField(improvedEmail)
                .inputPasswordInputField(improvedPassword).inputUserNameInputField(improvedUsername).tapSignUpButton();
        mfpApp.accountCreatedPage().tapAccountCreatedButton();
        excelUtils.setCellData(improvedUsername, improvedEmail, improvedPassword);
        Assert.assertTrue(mfpApp.upsellScreen().closeUpsellButtonIsDisplayed());
    }

    @Test (dependsOnMethods = "signUpTest", groups = {"signUpPlusAPP"}, priority = 2)
    public void annualNonTrialPremiumPurchaseTest(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String password = excelUtils.getCellData().password;
        //String email = excelUtils.getCellData(1).email;
        String username = excelUtils.getCellData().username;

        mfpApp.welcomePage().clickLoginButton();
        mfpApp.loginPage().clearUsernameField().inputUsername(username).inputPassword(password).tapLoginButton();
        mfpApp.upsellScreen().clickYearlySkuButton().clickSubscribeButton().clickCloseOnboardingButton();
        excelUtils.deleteCellData();
        Assert.assertTrue(mfpApp.upsellScreen().clickToClosePopup().getHomeActivity());

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test (dependsOnMethods = "signUpTest", groups = {"signUpPlusMPP"}, priority = 2)
    public void monthlyNonTrialPremiumPurchaseTest() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String password = excelUtils.getCellData().password;
        //String email = excelUtils.getCellData(1).email;
        String username = excelUtils.getCellData().username;

        mfpApp.welcomePage().clickLoginButton();
        mfpApp.loginPage().clearUsernameField().inputUsername(username).inputPassword(password).tapLoginButton();
        mfpApp.upsellScreen().clickMonthlySkuButton().clickSubscribeButton().clickCloseOnboardingButton();
        excelUtils.deleteCellData();
        Assert.assertTrue(mfpApp.upsellScreen().clickToClosePopup().getHomeActivity());

      try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod(groups = {"signUpPlusAPP", "signUpPlusMPP"})
    public void teardown() throws IOException {
        Runtime.getRuntime().exec("adb shell am force-stop com.myfitnesspal.android");
        Runtime.getRuntime().exec("adb shell pm clear com.myfitnesspal.android");
    }
}
