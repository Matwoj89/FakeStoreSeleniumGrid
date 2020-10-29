package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Zadanie15_DropdownList {

    WebDriver driver;
    WebDriverWait wait;
    By windsurfingTile = By.cssSelector("a>img[alt='Windsurfing']");
    By cookieAcceptButton = By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']");

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cookieAcceptButton));
        driver.findElement(cookieAcceptButton).click();

    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void aminPriceTest() {
        driver.findElement(windsurfingTile).click();
        WebElement sortingDownDropdownList = driver.findElements(By.cssSelector("select[name='orderby']")).get(0);
        Select windsurfingDropdownList = new Select(sortingDownDropdownList);
        windsurfingDropdownList.selectByIndex(4);
        WebElement minPrice = driver.findElements(By.cssSelector("span.price")).get(0);
        Assertions.assertEquals("2 900,00 zł",minPrice.getText(),"Texts are not equals");
    }

    @Test
    public void maxPriceTest(){
        driver.findElement(windsurfingTile).click();
        WebElement sortingDropdownList = driver.findElement(By.cssSelector("#main > div:nth-child(2) > form > select"));
        Select windsurfingDropdownList = new Select(sortingDropdownList);
        windsurfingDropdownList.selectByIndex(5);
        WebElement maxPrice = driver.findElements(By.cssSelector("span.price")).get(0);
        Assertions.assertEquals("5 399,00 zł",maxPrice.getText(),"Texts are not equals");
    }
}