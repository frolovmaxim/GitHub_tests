import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteMapPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;

    public SiteMapPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    @FindBy(xpath = "//h1[text() = 'Site Map']")
    private WebElement siteMapTitle;

    public String getSiteMapTitle(){
        return siteMapTitle.getText();
    }
}
