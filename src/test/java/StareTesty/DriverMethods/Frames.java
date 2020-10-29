package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Frames {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 500));
        driver.manage().window().setPosition(new Point(8, 30) );
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        driver.navigate().to("http://seleniumui.moderntester.pl/iframes.php?fbclid=IwAR2S9--nLetvp1RGX1l_uNTMrPXmLAgOx5SwosmqEMAb2rro6ZY4v1JRXgo");

        wait = new WebDriverWait(driver, 10);

    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void switchInsideFrame(){
      //  driver.switchTo().frame("iframe1");
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("[id='inputFirstName3']")).sendKeys("Mateusz");
        driver.switchTo().defaultContent();
        

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
