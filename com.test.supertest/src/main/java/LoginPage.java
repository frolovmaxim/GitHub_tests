import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id = 'login_field']")
    private WebElement userNameLoginPageField;

    @FindBy(xpath = "//input[@id = 'password']")
    private WebElement passwordLoginPageField;

    @FindBy(xpath = "//input[@type = 'submit']")
    private WebElement signInLoginPageButton;

    @FindBy(xpath = "//h1[contains(text(),'Sign in to GitHub')]")
    private WebElement loginPageTitle;

    @FindBy(xpath = "//a[text()='Create an account']")
    private WebElement createAccountLink;

    @FindBy(xpath = "//a[text()='Forgot password?']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//a[contains(text(), 'Terms')]")
    private WebElement termsLink;

    @FindBy(xpath = "//a[contains(text(), 'Privacy')]")
    private WebElement privacyLink;

    @FindBy(xpath = "//a[contains(text(), 'Security')]")
    private WebElement securityLink;

    @FindBy(xpath = "//a[contains(text(), 'Contact GitHub')]")
    private WebElement contactLink;

    public LoginPage typeUserNameLoginPage(String username){
        userNameLoginPageField.sendKeys(username);
        return this;
    }

    public LoginPage typePasswordLoginPage(String password){
        passwordLoginPageField.sendKeys(password);
        return this;
    }

    public AccountPage clickSignInButtonLoginPage(){
        signInLoginPageButton.click();
        return new AccountPage(driver);
    }

    public CreateAccountPage clickCreateAccountLink(){
        createAccountLink.click();
        return new CreateAccountPage(driver);
    }

    public ResetYourPasswordPage clickForgotPasswordLink(){
        forgotPasswordLink.click();
        return new ResetYourPasswordPage(driver);
    }

    public TermsOfServicePage clickTermsLink(){
        termsLink.click();
        return new TermsOfServicePage(driver);
    }

    public PrivacyStatementPage clickPrivacyLink(){
        privacyLink.click();
        return new PrivacyStatementPage(driver);
    }

    public SecurityPage clickSecurityLink(){
        securityLink.click();
        return new SecurityPage(driver);
    }

    public ContactPage clickContactLink(){
        contactLink.click();
        return new ContactPage(driver);
    }



    public AccountPage loginToAccount(String username, String password){
        this.typeUserNameLoginPage(username);
        this.typePasswordLoginPage(password);
        this.clickSignInButtonLoginPage();
        return new AccountPage(driver);
    }

    public LoginPage getLoginPageTitle(){
        loginPageTitle.getText();
        return this;
    }

}
