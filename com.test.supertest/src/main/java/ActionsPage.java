import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsPage {
    WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;
    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    @FindBy (xpath = "//h3[text()= 'GitHub Actions']")
    private WebElement actionsPageTitle;

    public String getTitleActionsText(){
        return actionsPageTitle.getText();
    }
}
