package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebStorageExamples {
    ChromeDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void localStorageExample() {
        driver.navigate().to("https://airly.eu/map/pl/#52.2602407344,21.0260836565");
       // LocalStorage local = driver.getLocalStorage();
       // String value = local.getItem("persist:map");
       // int size = local.size();
       // Set<String> keys = local.keySet();
       // String removedVallue = local.removeItem("persist:map");
       // local.setItem("spell", "Alohomora!");
       // local.clear();

        String key = "persist:map";
        int index = 1;

        String value = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.getItem(arguments[0]);", key);
        ((JavascriptExecutor) driver).executeScript("return localStorage.getItem(arguments[0]), arguments[1];", "spell", "Alohomora!");
        ((JavascriptExecutor) driver).executeScript("return localStorage.removeItem(arguments[0]);", key);
        String indexValue = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.key(arguments[0]);", index);
        long size = (long) ((JavascriptExecutor) driver).executeScript("return localStorage.length;");
        ((JavascriptExecutor) driver).executeScript("return localStorage.clear();");
    }

    @Test
    public void sessionStorageExample() {
        driver.navigate().to("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        SessionStorage session = driver.getSessionStorage();

        String value = session.getItem("yt-remote-session-app");
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(a->session.size()==5);
        int size = session.size();
        Set<String> keys = session.keySet();
        String removedVallue = session.removeItem("yt-remote-session-app");
        session.setItem("spell", "Alohomora!");
        session.clear();
    }

}
