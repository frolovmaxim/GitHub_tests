import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PlayStore {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public PlayStore(AppiumDriver<MobileElement> webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 60);
        PageFactory.initElements(driver, this);
    }

    public WebElement waitElementToBeClickableByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    @FindBy (xpath = "//android.widget.TextView[@content-desc='Play Store']")
    private WebElement playStore;

    @FindBy (xpath = "//android.widget.FrameLayout[@content-desc=\"Show navigation drawer\"]/android.widget.ImageView")
    private WebElement playStoreSandwich;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[3]")
    private WebElement subsMenuOption;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout")
    private WebElement specificSubs;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[2]")
    private WebElement specificSubs1;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[4]/android.widget.Button")
    private WebElement cancelSubscription;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[3]")
    private WebElement whyCancelRadio;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.Button")
    private WebElement continueButton;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.Button")
    private WebElement cancelSubscriptionSecond;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.Button")
    private WebElement resubscribe;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[5]/android.widget.Button")
    private WebElement cancelSubscriptionAnother;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.Button")
    private WebElement noThanksButton;
    //android.widget.RelativeLayout


    public PlayStore tapPlayStoreIcon(){
        waitElementToBeClickableByLocator(driver, playStore).click();
        return this;
    }

    public PlayStore tapPlayStoreSandwich(){
        waitElementToBeClickableByLocator(driver, playStoreSandwich).click();
        return this;
    }

    public PlayStore tapSubsOption(){
        waitElementToBeClickableByLocator(driver, subsMenuOption).click();
        return this;
    }

    public PlayStore tapSpecificSubs(){
       if(waitElementToBeClickableByLocator(driver, specificSubs).isDisplayed()) {
           waitElementToBeClickableByLocator(driver, specificSubs).click();
       }
       else if (waitElementToBeClickableByLocator(driver, specificSubs1).isDisplayed()) {
           waitElementToBeClickableByLocator(driver, specificSubs1).click();
           }

        return this;
    }

    public PlayStore tapCancelSubscription(){
        if (waitElementToBeClickableByLocator(driver, cancelSubscription).isDisplayed()){
        waitElementToBeClickableByLocator(driver, cancelSubscription).click();}
        else if (waitElementToBeClickableByLocator(driver, cancelSubscriptionAnother).isDisplayed()){
            waitElementToBeClickableByLocator(driver, cancelSubscriptionAnother).click();
            if (waitElementToBeClickableByLocator(driver, noThanksButton).isDisplayed()){
                waitElementToBeClickableByLocator(driver,noThanksButton).click();
            }
        }
        return this;
    }

    public PlayStore tapWhyCancelSubscription(){
        waitElementToBeClickableByLocator(driver, whyCancelRadio).click();
        return this;
    }

    public PlayStore tapContinueButton(){
        waitElementToBeClickableByLocator(driver, continueButton).click();
        return this;
    }

    public PlayStore tapCancelSubscriptionSecond(){
        waitElementToBeClickableByLocator(driver, cancelSubscriptionSecond).click();
        return this;
    }


}
