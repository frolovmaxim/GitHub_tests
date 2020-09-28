import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecurityPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SecurityPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(), 'Security at GitHub')]")
    private WebElement gitHubSecurityTitle;

    public String getSecurityTitleText(){
        return gitHubSecurityTitle.getText();
    }
}
