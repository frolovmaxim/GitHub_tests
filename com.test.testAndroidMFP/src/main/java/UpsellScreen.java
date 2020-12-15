import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpsellScreen {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public UpsellScreen(AppiumDriver<MobileElement> webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 100);
        PageFactory.initElements(driver, this);
    }

    public WebElement waitElementToBeClickableByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 100);

        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static WebElement waitElementToBeVisibleByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'yearly-subscription-btn']")
    private WebElement yearlySKU;

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.android.vending:id/0_resource_name_obfuscated']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id = 'com.myfitnesspal.android:id/btnCloseOnboarding']")
    private WebElement closeOnboardingButton;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id = 'com.myfitnesspal.android:id/close_button']")
    private WebElement closeUpsellScreen;

    @FindBy(xpath = "//android.view.View")
    private WebElement homeActivity;

    public UpsellScreen clickYearlySkuButton(){
        waitElementToBeClickableByLocator(driver, yearlySKU).click();
        return this;
    }

    public UpsellScreen clickSubscribeButton(){
        waitElementToBeClickableByLocator(driver, subscribeButton).click();
        return this;
    }

    public UpsellScreen clickCloseOnboardingButton(){
        waitElementToBeClickableByLocator(driver, closeOnboardingButton).click();
        return this;
    }

    public UpsellScreen clickToClosePopup(){
        waitElementToBeClickableByLocator(driver, homeActivity).click();
        return this;
    }

    public boolean getHomeActivity(){
        return waitElementToBeClickableByLocator(driver, homeActivity).isDisplayed();
    }

    public Boolean closeUpsellButtonIsDisplayed(){
        return waitElementToBeVisibleByLocator(driver, closeUpsellScreen).isDisplayed();
    }

}
