import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TermsOfServicePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public TermsOfServicePage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text() = 'GitHub Terms of Service']")
    private WebElement gitHubTermsOfServiceTitle;

    public String getGitHubTermsOfServiceTitle(){
        return gitHubTermsOfServiceTitle.getText();
    }
}
