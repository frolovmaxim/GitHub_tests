import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage {
    private WebDriver driver;
    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h2[text()='Learn Git and GitHub without any code!']")
    private WebElement entranceTitle;

    public boolean avatarDisplayed(){
        return entranceTitle.isDisplayed();
    }
}
