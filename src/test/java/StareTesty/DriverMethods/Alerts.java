package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Alerts{
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(4,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.MILLISECONDS);
        js = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver, 5);
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void alertBoxTest(){
        String javascript = "alert('Jestem alertem!')";
        js.executeScript(javascript);
    }
    @Test
    public void confirmBoxTest(){
        String javascript = "confirm('Wciśnij button:')";
        js.executeScript(javascript);
    }
    @Test
    public void promptBoxTest(){
        String javascript = "prompt('Możesz tutaj coś wpisać')";
        js.executeScript(javascript);
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Teeeeeest");
        driver.switchTo().alert().accept();
        js.executeScript(javascript);
        driver.switchTo().alert().dismiss();
    }

}
