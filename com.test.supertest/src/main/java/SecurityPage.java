import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecurityPage {
    private WebDriver driver;
    public SecurityPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[contains(text(), 'Security at GitHub')]")
    private WebElement gitHubSecurityTitle;

    public String getSecurityTitleText(){
        return gitHubSecurityTitle.getText();
    }
}
