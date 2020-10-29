package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Zadanie18_WebStorage {
    ChromeDriver driver;
    String expectedKey = "wc_cart_created";

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product/fuerteventura-sotavento/");
        WebElement image = driver.findElement(By.cssSelector("img[role='presentation']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", image);

    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void exercise1() {
        LocalStorage local = driver.getLocalStorage();
        Set<String> keys = local.keySet();
        Assertions.assertTrue(keys.size() >= 1, "Na starcie nie ma żadnego klucza");
    }

    @Test
    public void exercise2() {
        SessionStorage session = driver.getSessionStorage();
        Set<String> keys = session.keySet();
        Assertions.assertTrue(keys.size() >= 2, "Na starcie nie ma żadnego klucza");
    }

    @Test
    public void exercise3() {
        SessionStorage session = driver.getSessionStorage();
        driver.findElement(By.cssSelector("button[name='add-to-cart']")).click();

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(a->session.size()==3);

        Set<String> keysActual = session.keySet();
        Assertions.assertTrue(keysActual.contains(expectedKey), "Sessions storage do not contain " + expectedKey);

        session.removeItem(expectedKey);
        Set<String> keysAfterRemove = session.keySet();
        Assertions.assertFalse(keysAfterRemove.contains(expectedKey), "Sessions storage still contain " + expectedKey);
    }

}

