import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;

    public SearchResultPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    @FindBy(xpath = "//input[@aria-label = 'Search GitHub']")
    private WebElement searchDataField;

    @FindBy(xpath = "//nav/a[@href = '/search?q=common&type=issues' and text()='Issues']")
    private WebElement barOptionIssues;

    @FindBy(xpath = "//a[@aria-label = 'Page 3' and text()='3']")
    private WebElement paginationPage3;

    @FindBy(xpath = "//a[@rel = 'prev' and @aria-label = 'Page 2']")
    private WebElement paginationPreviousPage2;

    public String getPageTitle (){
        return driver.getTitle();
    }

    public SearchResultPage sendSearchData (String text){
        searchDataField.click();
        searchDataField.sendKeys(text);
        searchDataField.submit();
        return this;
    }

    public SearchResultPage clickIssueBarOption(){
        barOptionIssues.click();
        return this;
    }

    public SearchResultPage clickPageThree(){
        paginationPage3.click();
        return this;
    }

    public boolean pageTwoPagination(){
        return this.exists(paginationPreviousPage2);
    }

    public boolean exists(WebElement element){
        if(element == null){
            return false;
        } else {
            return true;
        }
    }

}