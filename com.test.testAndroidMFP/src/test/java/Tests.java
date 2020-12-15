import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class Tests {
    AppiumDriver<MobileElement> driver;
    private MFPapp mfpApp;

    @BeforeMethod(groups = {"signUpTest"})
    public void setup() {
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
            caps.setCapability("appWaitPackage", "com.android.vending");

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver<MobileElement>(url, caps);
            mfpApp = new MFPapp(driver);

        } catch (Exception exp) {
            System.out.println("Cause is : " + exp.getCause());
            System.out.println("Message is : " + exp.getMessage());
            exp.printStackTrace();
        }
    }

        @Test
        public void cancelSubsTest(){
            mfpApp.playStore().tapPlayStoreIcon()
                    .tapPlayStoreSandwich()
                    .tapSubsOption()
                    .tapSpecificSubs()
                    .tapCancelSubscription()
                    .tapWhyCancelSubscription()
                    .tapContinueButton()
                    .tapCancelSubscriptionSecond();
        }

    @AfterMethod
    public void teardown() throws IOException {
        Runtime.getRuntime().exec("adb shell am force-stop com.android.vending");

    }

}