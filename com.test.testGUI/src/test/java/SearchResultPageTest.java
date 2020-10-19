import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchResultPageTest {

    private WebDriver driver;
    private GitHubSite gitHubSite;

    @BeforeMethod(groups = {"firstGroup", "testSignUp"})
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
        driver.get("https://github.com/search");

        gitHubSite = new GitHubSite(driver);

    }

    @Test (enabled = false)//(groups = {"testSignUp"})
    public void checkIssuesBarOption(){
        gitHubSite.searchResultPage().sendSearchData("common");
        gitHubSite.searchResultPage().clickIssueBarOption();
        String searchResultPageTitle = gitHubSite.searchResultPage().getPageTitle();
        Assert.assertEquals(searchResultPageTitle, "Search · common · GitHub");
    }

    @Test (enabled = false)
    public void checkPagination(){
        gitHubSite.searchResultPage().sendSearchData("manual");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        gitHubSite.searchResultPage().clickPageThree();
        boolean isExist = gitHubSite.searchResultPage().pageTwoPagination();
        Assert.assertTrue(isExist);
    }

    @Test (enabled = false)
    public void checkAdvancedSearchLink(){
        gitHubSite.searchResultPage().sendSearchData("sdfsdf");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        gitHubSite.searchResultPage().clickAdvancedSearchLink();
        boolean result = gitHubSite.advancedSearchPage().isAdvancedSearchOptionExist();
        Assert.assertTrue(result);
    }

    @Test (enabled = false)
    public void checkCheatSheetLink(){
        gitHubSite.searchResultPage().sendSearchData("sdfsdf");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        gitHubSite.searchResultPage().clickCheatSheetLink();
        boolean result = gitHubSite.searchResultPage().isCheatSheetTitleExist();
        Assert.assertTrue(result);
    }

    @Test
    public void checkLogoReturning(){
        gitHubSite.searchResultPage().sendSearchData("common");
        gitHubSite.searchResultPage().clickLogo();
        String mainPageTitle = gitHubSite.mainPage().getMainPageTitle();
        Assert.assertEquals(mainPageTitle, "GitHub: Where the world builds software · GitHub");
    }



    @AfterMethod(groups = {"firstGroup", "testSignUp"})
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }
}
