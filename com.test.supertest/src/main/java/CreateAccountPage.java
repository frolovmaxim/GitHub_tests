import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage {
    WebDriver driver;
    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[text()='Create your account']")
    private WebElement createYourAccountTitle;

    public String getTitleText(){
        return createYourAccountTitle.getText();
    }

}
