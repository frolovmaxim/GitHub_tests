import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MFPapp {
    AppiumDriver<MobileElement> driver;

    public MFPapp(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public LoginPage loginPage(){
        return new LoginPage(driver);
    }

    public WelcomePage welcomePage(){
        return new WelcomePage(driver);
    }

    public HomePage homePage(){
        return new HomePage(driver);
    }

    public ForgotPasswordPage forgotPasswordPage(){
        return new ForgotPasswordPage(driver);
    }

    public AccountCreatedPage accountCreatedPage(){
        return new AccountCreatedPage(driver);
    }

    public SignUpPage signUpPage(){
        return new SignUpPage(driver);
    }

    public UpsellScreen upsellScreen(){
        return new UpsellScreen(driver);
    }




}
