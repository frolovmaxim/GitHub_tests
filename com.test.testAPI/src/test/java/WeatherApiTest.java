import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class WeatherApiTest {
    private WebDriver driver;
    private String baseUrl;
    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "/Users/maximfrolov/node_modules/geckodriver/bin/geckodriver");
        driver = new FirefoxDriver();
        baseUrl = "http://openweathermap.org/current";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void test() throws ClientProtocolException, IOException {
        driver.get(baseUrl);
        driver.navigate().to("http://api.openweathermap.org/data/2.5/weather?q=London&appid=fd06cd740848159706aec4b904ee227e");
        WebElement webElement=driver.findElement(By.tagName("pre"));
        WeatherApiResponse weatherApiResponse= new WeatherApiResponse();
        String ExpectedString=weatherApiResponse.GetResponse();
        Assert.assertTrue(webElement.getText().equals(ExpectedString));
    }

}
