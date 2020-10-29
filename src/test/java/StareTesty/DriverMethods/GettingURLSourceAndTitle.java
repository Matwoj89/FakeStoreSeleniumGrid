package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GettingURLSourceAndTitle {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(800,500));
        driver.manage().window().setPosition(new Point(50,50));
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void  gerCurrentURLExample(){
        String googleUrl = "https://www.google.pl/";
        driver.navigate().to("https://www.google.pl/");
        Assertions.assertEquals(googleUrl, driver.getCurrentUrl(), "Current URL is not: " + googleUrl);
    }

    @Test
    public void getTitleExample(){
        String googleTitle = "Google";
        driver.navigate().to("https://www.google.pl/");
        Assertions.assertEquals(googleTitle, driver.getTitle(), "Page title is not: " + googleTitle);
    }

    @Test
    public void getPageSourceExample(){
        String googleImg = "/images/branding/googleg/1x/googleg_standard_color_128dp.png";
        driver.navigate().to("https://www.google.pl/");
        Assertions.assertTrue(driver.getPageSource().contains(googleImg),"Page source does not include: " +googleImg);
    }
}
