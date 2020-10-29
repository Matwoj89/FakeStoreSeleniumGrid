package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Zadanie6 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 500));
        driver.manage().window().setPosition(new Point(8, 30));

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void usernameLoginTest() {
        String login = "Achaja8989";
        String password = "xKp2L44A8hFhnyM9Ra9d";
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(login);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).sendKeys(Keys.chord(Keys.ENTER));

        String userDisplayedName = "achaja8989";
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p>strong")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayedName),
                "My Account page does not contain correct name. Expected name: " + userDisplayedName + " was not found in a string: " + myAccountContent);

        //Assertions.assertEquals("achaja8989", driver.findElement(By.cssSelector("#post-8>div>div>div>p:nth-child(2)>strong:nth-child(1)")).getText(),"Youre not logged correctly");
    }

    @Test
    public void userEmailTest() {
        String login = "Achaja8989@op.pl";
        String password = "xKp2L44A8hFhnyM9Ra9d";
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(login);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).sendKeys(Keys.chord(Keys.ENTER));

        String userDisplayedName = "achaja8989";
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p>strong")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayedName),
                "My Account page does not contain correct name. Expected name: " + userDisplayedName + " was not found in a string: " + myAccountContent);
    }
}