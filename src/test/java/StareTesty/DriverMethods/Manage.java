package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.GregorianCalendar;

public class Manage {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000,500));
        driver.manage().window().setPosition(new Point(8,30));

        driver.navigate().to("https://www.wikipedia.pl");
    }

    @AfterEach
    public void closeAndQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void gettingAndDeletingCookies(){

        Cookie cookieSessionId  = driver.manage().getCookieNamed("session-id");
        driver.manage().deleteCookieNamed("session-id");
        Assertions.assertEquals(10 , driver.manage().getCookies().size(), "2.Number of cookies are not correctly");
        driver.manage().deleteAllCookies();
        Assertions.assertEquals(0,driver.manage().getCookies().size(), "3. Number of cookies are not correctly");
    }

    @Test
    public void addingAndDeletingCookies(){
        Cookie newCookie = new Cookie("test_cookie","test_value", "amazon.com", "/",
                new GregorianCalendar(2020,11,31).getTime(),true,true);
        driver.manage().addCookie(newCookie);
        Assertions.assertEquals(12 , driver.manage().getCookies().size(), "1.Number of cookies are not correctly");
        Cookie secondCookie = new Cookie("test_cookie2","test_value2");
        driver.manage().addCookie(secondCookie);
        Assertions.assertEquals(13 , driver.manage().getCookies().size(), "1.Number of cookies are not correctly");
        driver.manage().deleteCookie(newCookie);
        Assertions.assertEquals(12 , driver.manage().getCookies().size(), "1.Number of cookies are not correctly"); 
    }

    @Test
    public void windowSettings() throws InterruptedException {
        Point position = driver.manage().window().getPosition();
        Assertions.assertEquals(new Point(8,29), position, "Poition of the window is not not expected");
        Dimension size = driver.manage().window().getSize();
        Assertions.assertEquals(new Dimension(1003,503), size, "Poition of the window is not not expected");
        driver.manage().window().fullscreen();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
