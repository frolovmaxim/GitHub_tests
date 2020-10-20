import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactSalesPage {
    WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;
    public ContactSalesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    public String getTitleContactSalesPage(){
        return driver.getTitle();
    }


}
