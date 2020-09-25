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
    private MainPage mainPage;
    private CreateAccountPage createAccountPage;
    private TrialPlanPage trialPlanPage;

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
        driver.get("https://github.com/");
        mainPage = PageFactory.initElements(driver, MainPage.class);
        createAccountPage = PageFactory.initElements(driver, CreateAccountPage.class);
        trialPlanPage = PageFactory.initElements(driver, TrialPlanPage.class);
    }

    @Test
    public void registerUser(){
        mainPage.register("archfucker1", "dsffwefee@dfdsa.com", "101323Ax");
        String titleText = createAccountPage.getTitleText();
        Assert.assertEquals(titleText, "Create your account");
    }

    @Test (groups = {"firstGroup"})
    public void checkErrorUsernameMessage(){
        MainPage inputRussianUsername = mainPage.inputUsername("ывапывп");
        String errorText1 = inputRussianUsername.getErrorUsernameText();
        Assert.assertEquals(errorText1, "Username may only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen.");
    }

    @Test
    public void checkErrorEmailMessage(){
        MainPage inputRussianEmail = mainPage.inputEmail("вапвапвпа");
        String errorText2 = inputRussianEmail.getErrorEmailText();
        Assert.assertEquals(errorText2, "Email is invalid or already taken");
    }

    @Test
    public void checkErrorPasswordMessage(){
        MainPage inputShortPassword = mainPage.inputPassword("adsda");
        String errorText3 = inputShortPassword.getErrorPasswordText();
        Assert.assertEquals(errorText3, "Password is too short (minimum is 8 characters), needs at least 1 number, and is in a list of passwords commonly used on other websites");
    }

    @Test (groups = {"firstGroup"})
    public void checkLinkToPickTrialPlanPage(){
        mainPage.clickStartFreeTrial();
        String pickYourTrialText = trialPlanPage.getTrialTitleText();
        Assert.assertEquals(pickYourTrialText, "Pick your trial plan");
    }

    @AfterMethod (groups = {"firstGroup"})
    public void tearDown(){
        driver.quit();
    }
}
