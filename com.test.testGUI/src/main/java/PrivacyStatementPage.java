import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrivacyStatementPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PrivacyStatementPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(), 'GitHub Privacy Statement')]")
    private WebElement privacyStatementTitle;

    public String getPrivacyStatementTitleText(){
        return privacyStatementTitle.getText();
    }

}
