package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public LoginPage(AppiumDriver<MobileElement> webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/email_address_edit']")
    private WebElement usernameField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/password_edit']")
    private WebElement passwordField;

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/login_button']")
    private WebElement loginButton;

    @FindBy(xpath = "//android.widget.TextView[@resource-id = 'com.myfitnesspal.android:id/forgot_password_button']")
    private WebElement forgotPasswordLink;



    public WebElement waitElementToBeClickableByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public LoginPage inputUsername(String username){
        waitElementToBeClickableByLocator(driver, usernameField).sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String username){
        waitElementToBeClickableByLocator(driver, passwordField).sendKeys(username);
        return this;
    }

    public ForgotPasswordPage tapForgotPasswordLink(){
        waitElementToBeClickableByLocator(driver, forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }

    public HomePage tapLoginButton(){
        waitElementToBeClickableByLocator(driver, loginButton).click();
        return new HomePage(driver);
    }

    public LoginPage clearUsernameField(){
        waitElementToBeClickableByLocator(driver, usernameField).clear();
        return this;
    }


}
