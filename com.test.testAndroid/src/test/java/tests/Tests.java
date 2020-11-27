package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class Tests extends BaseClass {

    @Test
    public void testOne(){
        driver.get("https://google.com");
        driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys("Automation");
        driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys(Keys.ENTER);
        System.out.println("Completed Test One");
    }

}
