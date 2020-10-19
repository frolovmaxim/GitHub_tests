import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdvancedSearchPage {
    WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;

    public AdvancedSearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    @FindBy(xpath = "//h1[text()='Advanced search']")
    private WebElement advancedSearchPageTitle;

    public boolean isAdvancedSearchOptionExist(){
        return this.exists(advancedSearchPageTitle);
    }

    public boolean exists(WebElement element){
        if(element == null){
            return false;
        } else {
            return true;
        }
    }


}
