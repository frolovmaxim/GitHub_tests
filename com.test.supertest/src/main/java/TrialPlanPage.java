import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrialPlanPage {
    private WebDriver driver;
    public TrialPlanPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[text() = 'Pick your trial plan']")
    private WebElement pickYourTrialPlanTitle;

    public String getTrialTitleText(){
        return pickYourTrialPlanTitle.getText();
    }
}
