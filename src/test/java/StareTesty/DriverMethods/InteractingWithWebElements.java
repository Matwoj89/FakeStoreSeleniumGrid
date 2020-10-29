package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InteractingWithWebElements {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 500));
        driver.manage().window().setPosition(new Point(8, 30) );

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS);
        driver.navigate().to("https://www.zooniverse.org");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void testZooniverse(){

        driver.findElement(By.cssSelector("button[type*='button'][value*='sign-in'][class*='secret']")).click();

        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("Achaja8989");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("abcd1234");
        //driver.findElement(By.cssSelector("input[name='login']")).clear();  <----usuwa tekst z pola
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys(Keys.chord(Keys.CONTROL, "a")); // zaznacza test w podanym selektorze

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("button[type*='submit']")).click();
        //driver.findElement(By.cssSelector("form")).submit(); <---- akceptuje cały formularz (form)
        //driver.findElement(By.cssSelector("button[type*='submit']")).submit();  <---może być alternatywnie submit() bo to pole formularza

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("ACHAJA8989", driver.findElement(By.cssSelector("span[class='account-bar'] strong")).getText(),
                "Username displayed pn header is not correct. The user probably is not logged in");
    }
}
