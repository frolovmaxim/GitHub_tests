import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private WebDriver driver;
    private GitHubSite gitHubSite;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private CreateAccountPage createAccountPage;
    private ResetYourPasswordPage resetYourPasswordPage;
    private TermsOfServicePage termsOfServicePage;
    private PrivacyStatementPage privacyStatementPage;
    private SecurityPage securityPage;
    private ContactPage contactPage;
    //JavascriptExecutor js;

    @BeforeMethod (groups = {"firstGroup"})
    @Parameters("browser")
    public void setUp(String browser) throws Exception{
        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/Users/maximfrolov/node_modules/chromedriver/bin/chromedriver");
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "/Users/maximfrolov/node_modules/geckodriver/bin/geckodriver");
            driver = new FirefoxDriver();
        }
        else {
            throw new Exception("Browser is not correct");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://github.com/login");
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        gitHubSite = new GitHubSite(driver);

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        createAccountPage = PageFactory.initElements(driver, CreateAccountPage.class);
        resetYourPasswordPage = PageFactory.initElements(driver, ResetYourPasswordPage.class);
        termsOfServicePage = PageFactory.initElements(driver, TermsOfServicePage.class);
        privacyStatementPage = PageFactory.initElements(driver, PrivacyStatementPage.class);
        securityPage = PageFactory.initElements(driver, SecurityPage.class);
        contactPage = PageFactory.initElements(driver, ContactPage.class);
    }

    @Test (groups = {"firstGroup"})
    public void testLoginUser(){
        gitHubSite.loginPage().loginToAccount("frolovmaxim", "101323An");
        boolean result = gitHubSite.accountPage().avatarDisplayed();
        Assert.assertTrue(result);
    }

    @Test
    public void testCreateAccountLink(){
        gitHubSite.loginPage().clickCreateAccountLink();
        String createAccountPageTitleText = gitHubSite.createAccountPage().getTitleText();
        Assert.assertEquals(createAccountPageTitleText, "Create your account");
    }

    @Test
    public void testForgotPasswordLink(){
        gitHubSite.loginPage().clickForgotPasswordLink();
        String forgotPasswordPageTitleText = gitHubSite.resetYourPasswordPage().getResetYourPasswordPageTitle();
        Assert.assertEquals(forgotPasswordPageTitleText, "Reset your password");
    }

    @Test
    public void testTermsLink(){
        gitHubSite.loginPage().clickTermsLink();
        String gitHubTermsOfServiceText = gitHubSite.termsOfServicePage().getGitHubTermsOfServiceTitle();
        Assert.assertEquals(gitHubTermsOfServiceText, "GitHub Terms of Service");
    }

    @Test
    public void testPrivacyLink(){
        gitHubSite.loginPage().clickPrivacyLink();
        String gitHubPrivacyStatementText = gitHubSite.privacyStatementPage().getPrivacyStatementTitleText();
        Assert.assertEquals(gitHubPrivacyStatementText, "GitHub Privacy Statement");
    }

    @Test (groups = {"firstGroup"})
    public void testSecurityLink(){
        gitHubSite.loginPage().clickSecurityLink();
        String gitHubSecurityText = gitHubSite.securityPage().getSecurityTitleText();
        Assert.assertEquals(gitHubSecurityText, "Security at GitHub");
    }

    @Test
    public void testContactLink() {
        gitHubSite.loginPage().clickContactLink();
        //Thread.sleep(4000);
        //js.executeScript("window.scrollBy(0,10000)");
        String contactUsButtonText = gitHubSite.contactPage().getContactUrl();
        Assert.assertEquals(contactUsButtonText, "https://support.github.com/");
    }


    @AfterMethod (groups = {"firstGroup"})
    public void tearDown(){
        if (driver != null)
        driver.quit();
    }


}
