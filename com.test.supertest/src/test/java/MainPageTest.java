import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.reporters.jq.Main;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private GitHubSite gitHubSite;
    //private MainPage mainPage;
    //private CreateAccountPage createAccountPage;
    //private TrialPlanPage trialPlanPage;

    @BeforeMethod (groups = {"firstGroup", "testSignUp"})
    @Parameters({"browser"})
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

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://github.com/");
        //driver.switchTo().frame(0);
        gitHubSite = new GitHubSite(driver);
        //mainPage = PageFactory.initElements(driver, MainPage.class);
        //createAccountPage = PageFactory.initElements(driver, CreateAccountPage.class);
        //trialPlanPage = PageFactory.initElements(driver, TrialPlanPage.class);
    }

    @Test (enabled = false)
    public void registerUser(){
        gitHubSite.mainPage().register("archfucker1", "dsffwefee@dfdsa.com", "101323Ax");
        String titleText = gitHubSite.signUpPage().getSignUpPageTitle();
        Assert.assertEquals(titleText, "Create your account");
    }

    @Test (groups = {"firstGroup"}, enabled = false)
    public void checkErrorUsernameMessage(){
        String errorText1 = gitHubSite.mainPage().inputUsername("ывапывп").getErrorUsernameText();
        Assert.assertEquals(errorText1, "Username may only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen.");
    }

    @Test (enabled = false)
    public void checkErrorEmailMessage(){
        String errorText2 = gitHubSite.mainPage().inputEmail("вапвапвпа").getErrorEmailText();
        Assert.assertEquals(errorText2, "Email is invalid or already taken");
    }

    @Test (enabled = false)
    public void checkErrorPasswordMessage(){
        String errorText3 = gitHubSite.mainPage().inputPassword("adsda").getErrorPasswordText();
        Assert.assertEquals(errorText3, "Password is too short (minimum is 8 characters), needs at least 1 number, and is in a list of passwords commonly used on other websites");
    }

    @Test (groups = {"firstGroup"}, enabled = false)
    public void checkLinkToPickTrialPlanPage(){
        gitHubSite.mainPage().clickStartFreeTrial();
        String pickYourTrialText = gitHubSite.trialPlanPage().getTrialTitleText();
        Assert.assertEquals(pickYourTrialText, "Pick your trial plan");
    }

    @Test (enabled = false)//(groups = {"testSignUp"})
    public void checkSignUpLink(){
        gitHubSite.mainPage().clickSignUp();
        String signUpPageTitle = gitHubSite.signUpPage().getSignUpPageTitle();
        Assert.assertEquals(signUpPageTitle, "Create your account");
    }

    @Test (enabled = false)//(groups = {"testSignUp"})
    public void checkSignInLink(){
        gitHubSite.mainPage().clickSignIn();
        String loginPageTitle = gitHubSite.loginPage().getLoginPageTitle();
        Assert.assertEquals(loginPageTitle, "Sign in to GitHub");
    }

    @Test (groups = {"testSignUp"}, enabled = false)
    public void goToActionsDropDownMenuTest(){
        gitHubSite.mainPage().goToWhyGitHubDropDownMenu();
        String actionTitleText = gitHubSite.actionsPage().getTitleActionsText();
        Assert.assertEquals(actionTitleText, "GitHub Actions");
    }

    @Test (enabled = false)
    public void siteMapLinkTest(){
        gitHubSite.mainPage().clickSiteMap();
        String siteMapTitleText = gitHubSite.siteMapPage().getSiteMapTitle();
        Assert.assertEquals(siteMapTitleText, "Site Map");
    }

    @Test
    public void searchResultTest(){
        gitHubSite.mainPage().typeTextSearchField("frolovmaxim");
        String searchResultPageTitle = gitHubSite.searchResultPage().getPageTitle();
        boolean result = searchResultPageTitle.equals("Search · frolovmaxim · GitHub") || searchResultPageTitle.equals("GitHub: Where the world builds software · GitHub");
        Assert.assertTrue(result);
    }

    @AfterMethod (groups = {"firstGroup", "testSignUp"})
    public void tearDown(){
        if (driver != null)
        driver.quit();
    }
}
