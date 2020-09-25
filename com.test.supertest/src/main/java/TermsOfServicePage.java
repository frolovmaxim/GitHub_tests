import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TermsOfServicePage {

    private WebDriver driver;
    public TermsOfServicePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[text() = 'GitHub Terms of Service']")
    private WebElement gitHubTermsOfServiceTitle;

    public String getGitHubTermsOfServiceTitle(){
        return gitHubTermsOfServiceTitle.getText();
    }
}
