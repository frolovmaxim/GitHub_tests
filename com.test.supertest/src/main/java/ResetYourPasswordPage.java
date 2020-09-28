import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResetYourPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ResetYourPasswordPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text()='Reset your password']")
    private WebElement resetYourPasswordPageTitle;

    public String getResetYourPasswordPageTitle(){
        return resetYourPasswordPageTitle.getText();

    }

}
