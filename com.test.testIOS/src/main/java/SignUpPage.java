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

    public static WebElement waitElementToBeVisibleByLocator(
            AppiumDriver<MobileElement> driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/login_button']")
    private WebElement sighUpWithEmailButton;

    @FindBy(xpath = "//android.widget.TextView[@resource-id = 'com.myfitnesspal.android:id/prominent_action_item']")
    private WebElement nextButton;

    @FindBy(xpath = "//android.widget.RadioButton[@resource-id = 'com.myfitnesspal.android:id/maintain']")
    private WebElement maintainWeightRadioButton;

    @FindBy(xpath = "//android.widget.RadioButton[@resource-id = 'com.myfitnesspal.android:id/radioLightActive']")
    private WebElement lightlyActiveRadioButton;


    @FindBy(xpath = "//android.widget.RadioButton[@resource-id = 'com.myfitnesspal.android:id/male']")
    private WebElement genderMaleRadioButton;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/birthdate']")
    private WebElement birthDateInputOption;

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'android:id/button1']")
    private WebElement okButton;

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/confirm_button']")
    private WebElement confirmButton;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/zipcode']")
    private WebElement zipCodeInputField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/height']")
    private WebElement heightInputField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/entry_one']")
    private WebElement heightAndWeightEntryField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/current_weight']")
    private WebElement currentWeightInputField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/email_edit']")
    private WebElement emailInputField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/password_edit']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.myfitnesspal.android:id/username_edit']")
    private WebElement userNameInputField;

    @FindBy(xpath = "//android.widget.Button[@resource-id = 'com.myfitnesspal.android:id/sign_up']")
    private WebElement signUpButton;




    public SignUpPage tapSignUpWithEmailButton() {
        waitElementToBeClickableByLocator(driver, sighUpWithEmailButton).click();
        return this;
    }

    public SignUpPage tapMaintainWeightButton() {
        waitElementToBeClickableByLocator(driver, maintainWeightRadioButton).click();
        return this;
    }

    public SignUpPage tapLightlyActiveRadioButton() {
        waitElementToBeClickableByLocator(driver, lightlyActiveRadioButton).click();
        return this;
    }




    public SignUpPage tapGenderMaleRadioButton() {
        waitElementToBeClickableByLocator(driver, genderMaleRadioButton).click();
        return this;
    }

    public SignUpPage tapBirthDateInputOption() {
        waitElementToBeClickableByLocator(driver, birthDateInputOption).click();
        return this;
    }

    public SignUpPage tapOkButton() {
        waitElementToBeClickableByLocator(driver, okButton).click();
        return this;
    }

    public SignUpPage tapConfirmButton() {
        waitElementToBeClickableByLocator(driver, confirmButton).click();
        return this;
    }


    public SignUpPage tapZipCodeInputField(String number) {
        waitElementToBeClickableByLocator(driver, zipCodeInputField).sendKeys(number);
        return this;
    }

    public SignUpPage tapNextButton() {
        waitElementToBeClickableByLocator(driver, nextButton).click();
        return this;
    }

    public SignUpPage tapHeightInputField() {
        waitElementToBeClickableByLocator(driver, heightInputField).click();
        return this;
    }

    public SignUpPage inputHeightEntryField(String height) {
        waitElementToBeClickableByLocator(driver, heightAndWeightEntryField).sendKeys(height);
        return this;
    }

    public SignUpPage tapCurrentWeightInputField() {
        waitElementToBeClickableByLocator(driver, currentWeightInputField).click();
        return this;
    }

    public SignUpPage inputWeightEntryField(String weight) {
        waitElementToBeClickableByLocator(driver, heightAndWeightEntryField).sendKeys(weight);
        return this;
    }

    public SignUpPage inputEmailInputField(String email) {
        waitElementToBeClickableByLocator(driver, emailInputField).sendKeys(email);
        return this;
    }

    public SignUpPage inputPasswordInputField(String password) {
        waitElementToBeClickableByLocator(driver, passwordInputField).sendKeys(password);
        return this;
    }

    public SignUpPage inputUserNameInputField(String username) {
        waitElementToBeClickableByLocator(driver, userNameInputField).sendKeys(username);
        return this;
    }

    public AccountCreatedPage tapSignUpButton() {
        waitElementToBeClickableByLocator(driver, signUpButton).click();
        return new AccountCreatedPage(driver);
    }
}
