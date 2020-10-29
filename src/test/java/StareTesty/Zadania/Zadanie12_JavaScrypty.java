package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Zadanie12_JavaScrypty {
    WebDriver driver;
    By demoStoreBar = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product/yoga-i-pilates-w-hiszpanii/");
        driver.findElement(demoStoreBar).click();
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void headerCartTest(){
        WebElement description = driver.findElement(By.cssSelector("div#tab-description"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", description);
        List<WebElement> headerCard = driver.findElements(By.cssSelector("section.storefront-sticky-add-to-cart--slideInDown"));
        Assertions.assertTrue(headerCard.size()==1);


    }

}
