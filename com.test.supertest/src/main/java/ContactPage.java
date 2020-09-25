import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage {
    private WebDriver driver;
    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    //@FindBy(xpath = "//a[contains(text(), 'Contact us')]")
    //private WebElement contactUsButton;

    public String getContactUrl(){
        return driver.getCurrentUrl();
    }
}
