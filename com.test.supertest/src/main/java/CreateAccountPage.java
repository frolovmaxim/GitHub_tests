import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CreateAccountPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text()='Create your account']")
    private WebElement createYourAccountTitle;

    public String getTitleText(){
        return createYourAccountTitle.getText();
    }

}
