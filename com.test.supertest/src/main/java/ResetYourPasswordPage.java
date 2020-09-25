import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetYourPasswordPage {
    private WebDriver driver;
    public ResetYourPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[text()='Reset your password']")
    private WebElement resetYourPasswordPageTitle;

    public String getResetYourPasswordPageTitle(){
        return resetYourPasswordPageTitle.getText();

    }

}
