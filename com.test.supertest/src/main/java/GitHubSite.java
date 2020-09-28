import org.openqa.selenium.WebDriver;

public class GitHubSite {
    WebDriver driver;

    public GitHubSite(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage mainPage(){
        return new MainPage(driver);
    }

    public AccountPage accountPage(){
        return new AccountPage(driver);
    }

    public ContactPage contactPage(){
        return new ContactPage(driver);
    }

    public CreateAccountPage createAccountPage(){
        return new CreateAccountPage(driver);
    }

    public LoginPage loginPage(){
        return new LoginPage(driver);
    }

    public PrivacyStatementPage privacyStatementPage(){
        return new PrivacyStatementPage(driver);
    }

    public ResetYourPasswordPage resetYourPasswordPage(){
        return new ResetYourPasswordPage(driver);
    }

    public SecurityPage securityPage(){
        return new SecurityPage(driver);
    }

    public SignUpPage signUpPage(){
        return new SignUpPage(driver);
    }

    public TermsOfServicePage termsOfServicePage(){
        return new TermsOfServicePage(driver);
    }

    public TrialPlanPage trialPlanPage(){
        return new TrialPlanPage(driver);
    }
}
