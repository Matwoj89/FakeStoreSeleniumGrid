package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class Zadanie11_Ramki {
    WebDriver driver;
    By demoStoreBar = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-ramek/");
        driver.findElement(demoStoreBar).click();
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }
    @Test
    public void mainPageButtonDisabledTest(){
        driver.switchTo().frame("main-frame");
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[type='button']"));
        Assertions.assertFalse(mainPageButton.isEnabled(), "Main Page button is not disabled.");
    }

    @Test
    public void imageLinkTest(){
        driver.switchTo().frame("main-frame");
        driver.switchTo().frame("image");
        WebElement mainPageLink = driver.findElement(By.xpath(".//img[@alt='Wakacje']/.."));
        Assertions.assertEquals("https://fakestore.testelka.pl/", mainPageLink.getAttribute("href"),
                "There is no link to the main page on the image");
    }

    @Test
    public void mainPageButtonEnabledTest(){
        driver.switchTo().frame("main-frame")
                .switchTo().frame("image")
                .switchTo().frame(0);
        WebElement mainPageButton = driver.findElement(By.cssSelector("a.button"));
        Assertions.assertTrue(mainPageButton.isEnabled(),"Main Page button is not enabled");
    }

    @Test
    public void logoDisplayedTest(){
        driver.switchTo().frame("main-frame")
                .switchTo().frame("image")
                .switchTo().frame(0);
        WebElement mainPageButton = driver.findElement(By.cssSelector("a.button"));
        mainPageButton.click();
        driver.switchTo().parentFrame()
                .switchTo().parentFrame();
        WebElement climbingButton = driver.findElement(By.cssSelector("a[name='climbing']"));
        climbingButton.click();
        WebElement logo = driver.findElement(By.cssSelector("img.custom-logo"));
        Assertions.assertTrue(logo.isDisplayed(), "Logo is not displayed.");
    }
}
