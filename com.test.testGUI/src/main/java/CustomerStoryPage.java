import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerStoryPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;

    public CustomerStoryPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    public String getKrisNovaStoryUrl(){
        return driver.getCurrentUrl();
    }


}
