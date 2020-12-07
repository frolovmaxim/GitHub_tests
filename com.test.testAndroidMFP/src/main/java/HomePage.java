import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public HomePage(AppiumDriver<MobileElement> webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }
}
