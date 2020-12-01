package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomePage {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;
    private Actions builder;

    public WelcomePage(AppiumDriver<MobileElement> webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/btnSignIn']")
    private WebElement loginButton1;

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/btnNewAccount']")
    private WebElement sighUpButton;



    public static WebElement waitElementToBeClickableByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public LoginPage clickLoginButton(){
        waitElementToBeClickableByLocator(driver, loginButton1).click();
        return new LoginPage(driver);
    }

    public SignUpPage tapSignUpButton(){
        waitElementToBeClickableByLocator(driver, sighUpButton).click();
        return new SignUpPage(driver);
    }





}
