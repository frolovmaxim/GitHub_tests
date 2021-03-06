import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountCreatedPage {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public AccountCreatedPage(AppiumDriver<MobileElement> webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/start_tracking']")
    private WebElement accountCreatedButton;

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'yearly-subscription-btn']")
    private WebElement annualSKUButton;

    public static WebElement waitElementToBeClickableByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static WebElement waitElementToBeVisibleByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public HomePage tapAccountCreatedButton() {
        waitElementToBeClickableByLocator(driver, accountCreatedButton).click();
        return new HomePage(driver);
    }

    public Boolean annualSKUButtonIsDisplayed(){
        return waitElementToBeVisibleByLocator(driver, annualSKUButton).isDisplayed();
    }


}
