package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ZadanieRozmiarOkna {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000,500));
        driver.manage().window().setPosition(new Point(8,30));
        driver.navigate().to("https://wikipedia.org");

    }

    @AfterEach
    public void closeAndQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void setSize(){
        Dimension size = new Dimension(600, 400);
        driver.manage().window().setSize(size);
        Point position = new Point(44, 30);
        driver.manage().window().setPosition(position);

        Assertions.assertEquals(size, driver.manage().window().getSize(), "Size window is wrong");

        Assertions.assertEquals(position, driver.manage().window().getPosition(), "Wrong position");

        driver.manage().window().maximize();

        driver.manage().window().fullscreen();

    }


}
