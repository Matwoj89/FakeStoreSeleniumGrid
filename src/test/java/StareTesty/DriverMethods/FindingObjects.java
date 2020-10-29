package StareTesty.DriverMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindingObjects {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        //Firefox driver automaticly actualization
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().setSize(new Dimension(1000, 500));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");

    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findingElementById() {
        driver.findElement(By.id("searchInput"));
        driver.findElement(By.name("search"));
        //  driver.findElement(By.className("cnotice-logo-container"));
        //   driver.findElement(By.className("sprite"));

        int numberOfImages = driver.findElements(By.tagName("img")).size();
    }

    @Test
    public void findingElementByLinkText() {
        driver.findElement(By.linkText("Wikisłownik"));
        driver.findElement(By.partialLinkText("cytaty"));
    }

}