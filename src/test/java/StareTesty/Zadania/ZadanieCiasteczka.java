package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ZadanieCiasteczka {
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
    public void getCookies(){
        driver.manage().getCookies();
        Assertions.assertEquals(3,driver.manage().getCookies().size(), "1. Number of cookies are not correctly");
        Cookie newCookie = new Cookie("nowe","ciastko");
        driver.manage().addCookie(newCookie);
        Assertions.assertEquals(4,driver.manage().getCookies().size(), "2. Number of cookies are not correctly");
        driver.manage().deleteCookie(newCookie);
       // Assertions.assertEquals(newCookie.getName(), driver.manage().getCookieNamed("nowe").getName());
        driver.manage().deleteCookieNamed("GeoIP");
        Assertions.assertEquals(2,driver.manage().getCookies().size(), "3. Number of cookies are not correctly");
        Cookie cookie = driver.manage().getCookieNamed("WMF-Last-Access");
        Assertions.assertEquals("www.wikipedia.org", cookie.getDomain(), "Cookie domain is not what expected");
        Assertions.assertEquals("/", cookie.getPath(), "Cookie domain is not what expected");
        Assertions.assertTrue(cookie.isHttpOnly(), "Flage not expected");
    }

}
