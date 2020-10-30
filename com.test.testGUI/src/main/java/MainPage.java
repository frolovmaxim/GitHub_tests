import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;

    public MainPage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[contains(@href,'/join?ref') and contains(@class, 'HeaderMenu-link')]")
    private WebElement signUpButton;

    @FindBy(xpath = "//input[@id='user[login]']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='user[email]']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='user[password]']")
    private WebElement passwordField;

    @FindBy(xpath = "//form/button[text()='Sign up for GitHub']")
    private WebElement signUpFormButton;

    @FindBy(xpath = "//div[@class='mb-1 ']")
    private WebElement errorUsernameMessage;

    @FindBy (xpath = "//auto-check[@src='/signup_check/email']//dd[contains(@id, 'input-check')]")
    private WebElement errorEmailMessage;

    @FindBy (xpath = "//password-strength//dd[contains(@id, 'input-check')]")
    private WebElement errorPasswordMessage;

    @FindBy (xpath = "//a[text() = 'Start a free trial']")
    private WebElement freeTrialButton;

    @FindBy (xpath = "//summary[contains(text(),'Why GitHub?')]")
    private WebElement whyGitHubDropDownMenu;

    @FindBy(xpath = "//a[text()= 'Actions']")
    private WebElement actionsLink;

    @FindBy(xpath = "//a[text()='Site Map']")
    private WebElement siteMapLink;

    @FindBy(xpath = "//form[@class= 'js-site-search-form']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@aria-label = 'Search GitHub']")
    private WebElement searchFieldInput;

    @FindBy(xpath = "//a[text() = 'Contact Sales ']")
    private WebElement contactSalesLink;

    @FindBy(xpath = "//a[@href='/customer-stories/kris-nova']")
    private WebElement storyKrisNova;

    @FindBy(xpath = "//div[contains(@class,'jumbotron-codelines')]")
    private WebElement jumbotronMain;

    @FindBy(xpath = "//img[@alt = 'Slack']")
    private WebElement slackIcon;

    @FindBy(xpath = "//a[@class='no-underline Bump-link']")
    private WebElement browseGitHubMarketplaceLink;

 //   @FindBy(xpath = "//a[@aria-label= 'Homepage' and @href='https://github.com/']")
 //   private WebElement logoIcon;

    public LoginPage clickSignIn(){
        signInButton.click();
        return new LoginPage(driver);
    }

    public MainPage clickSlackIcon(){
        slackIcon.click();
        return this;
    }

    public ContactSalesPage clickContactSalesLink(){
        contactSalesLink.click();
        return new ContactSalesPage(driver);
    }

    public SearchResultPage typeTextSearchField(String text){
        searchField.click();
        searchFieldInput.sendKeys(text);
        searchField.submit();
        return new SearchResultPage(driver);
    }


    public SiteMapPage clickSiteMap(){
        siteMapLink.click();
        return new SiteMapPage(driver);
    }

    public CustomerStoryPage clickKrisNovaStory(){
        storyKrisNova.click();
        return new CustomerStoryPage(driver);
    }

    public SignUpPage clickSignUp(){
        signUpButton.click();
        return new SignUpPage(driver);
    }

    public SignUpPage clickSignUpForm(){
        signUpFormButton.click();
        return new SignUpPage(driver);
    }

    public TrialPlanPage clickStartFreeTrial(){
        freeTrialButton.click();
        return new TrialPlanPage(driver);
    }

    public MainPage typeUserName(String username){
        userNameField.sendKeys(username);
        return this;
    }

    public MainPage typePassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public MainPage typeEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    public String getErrorUsernameText(){
        return errorUsernameMessage.getText();
    }

    public String getErrorEmailText(){
        return errorEmailMessage.getText();
    }

    public String getErrorPasswordText(){
        return errorPasswordMessage.getText();
    }

    public String getMainPageTitle(){
        return driver.getTitle();
    }


    public SignUpPage register(String username, String email, String password){
        this.typeUserName(username);
        this.typeEmail(email);
        this.typePassword(password);
        this.clickSignUpForm();
        return new SignUpPage(driver);
    }

    public MainPage inputUsername(String username){
        this.typeUserName(username);
        return this;
    }

    public MainPage inputEmail(String email){
        this.typeEmail(email);
        return this;
    }

    public MainPage inputPassword(String password){
        this.typePassword(password);
        return this;
    }

    public ActionsPage goToWhyGitHubDropDownMenu(){
        //builder.moveToElement(whyGitHubDropDownMenu).click().perform();
        whyGitHubDropDownMenu.click();
        actionsLink.click();
        return new ActionsPage(driver);
    }

    public String getJumbotronColor(){
        return jumbotronMain.getCssValue("background-color");
    }

    public String getBrowseGitHubMarketplaceLinkColor(){
        return browseGitHubMarketplaceLink.getCssValue("color");
    }

    public MarketplacePage clickBrowseMarketplaceLink(){
        browseGitHubMarketplaceLink.click();
        return new MarketplacePage(driver);
    }


}
