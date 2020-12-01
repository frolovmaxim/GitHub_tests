package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActivityLevelPage {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public ActivityLevelPage(AppiumDriver<MobileElement> webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.TextView[@resource-id = 'com.myfitnesspal.android:id/prominent_action_item']")
    private WebElement nextButton;

    @FindBy(xpath = "//android.widget.RadioButton[@resource-id = 'com.myfitnesspal.android:id/radioLightActive']")
    private WebElement lightlyActiveRadioButton;

    public static WebElement waitElementToBeClickableByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public YouPage tapLightlyActiveRadioButton() {
        waitElementToBeClickableByLocator(driver, lightlyActiveRadioButton).click();
        return new YouPage(driver);
    }
}
