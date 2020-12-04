package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public SignUpPage(AppiumDriver<MobileElement> webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public static WebElement waitElementToBeClickableByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/login_button']")
    private WebElement sighUpWithEmailButton;

    @FindBy(xpath = "//android.widget.TextView[@resource-id = 'com.myfitnesspal.android:id/prominent_action_item']")
    private WebElement nextButton;

    @FindBy(xpath = "//android.widget.RadioButton[@resource-id = 'com.myfitnesspal.android:id/maintain']")
    private WebElement maintainWeightRadioButton;

    @FindBy(xpath = "//android.widget.RadioButton[@resource-id = 'com.myfitnesspal.android:id/radioLightActive']")
    private WebElement lightlyActiveRadioButton;


    public SignUpPage tapSignUpWithEmailButton() {
        waitElementToBeClickableByLocator(driver, sighUpWithEmailButton).click();
        return this;
    }

    public SignUpPage tapMaintainWeightButton() {
        waitElementToBeClickableByLocator(driver, maintainWeightRadioButton).click();
        return this;
    }

    public YouPage tapLightlyActiveRadioButton() {
        waitElementToBeClickableByLocator(driver, lightlyActiveRadioButton).click();
        return new YouPage(driver);
    }
}
