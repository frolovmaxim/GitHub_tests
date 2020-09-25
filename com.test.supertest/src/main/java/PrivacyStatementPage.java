import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivacyStatementPage {
    private WebDriver driver;
    public PrivacyStatementPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[contains(text(), 'GitHub Privacy Statement')]")
    private WebElement privacyStatementTitle;

    public String getPrivacyStatementTitleText(){
        return privacyStatementTitle.getText();
    }

}
