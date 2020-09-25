import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(xpath = "//a[text()='Sign in']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[text()='Sign up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//input[@id='user[login]']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='user[email]']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='user[password]']")
    private WebElement passwordField;

    @FindBy(xpath = "//form/button[text()='Sign up for GitHub']")
    private WebElement signUpFormButton;

    @FindBy(xpath = "//div[@class='mb-1 ']")
    private WebElement errorUsernameMessage;

    @FindBy (xpath = "//auto-check[@src='/signup_check/email']//dd[contains(@id, 'input-check')]")
    private WebElement errorEmailMessage;

    @FindBy (xpath = "//password-strength//dd[contains(@id, 'input-check')]")
    private WebElement errorPasswordMessage;

    @FindBy (xpath = "//a[text() = 'Start a free trial']")
    private WebElement freeTrialButton;


    public LoginPage clickSignIn(){
        signInButton.click();
        return new LoginPage(driver);
    }

    public SignUpPage clickSignUp(){
        signUpButton.click();
        return new SignUpPage(driver);
    }

    public SignUpPage clickSignUpForm(){
        signUpFormButton.click();
        return new SignUpPage(driver);
    }

    public TrialPlanPage clickStartFreeTrial(){
        freeTrialButton.click();
        return new TrialPlanPage(driver);
    }

    public MainPage typeUserName(String username){
        userNameField.sendKeys(username);
        return this;
    }

    public MainPage typePassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public MainPage typeEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    public String getErrorUsernameText(){
        return errorUsernameMessage.getText();
    }

    public String getErrorEmailText(){
        return errorEmailMessage.getText();
    }

    public String getErrorPasswordText(){
        return errorPasswordMessage.getText();
    }




    public CreateAccountPage register(String username, String email, String password){
        this.typeUserName(username);
        this.typeEmail(email);
        this.typePassword(password);
        this.clickSignUpForm();
        return new CreateAccountPage(driver);
    }

    public MainPage inputUsername(String username){
        this.typeUserName(username);
        return this;
    }

    public MainPage inputEmail(String email){
        this.typeEmail(email);
        return this;
    }

    public MainPage inputPassword(String password){
        this.typePassword(password);
        return this;
    }
}
