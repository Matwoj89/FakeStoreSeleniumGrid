package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Zadanie14_WindowHandles {

    WebDriver driver;
    WebDriverWait wait;
    By demoStoreDismiss = By.cssSelector("a[class*='dismiss-link']");
    String yogaPilatesTileCssSelector = "img[alt='Yoga i pilates']";
    String toskaniaTile = "img[src='https://fakestore.testelka.pl/wp-content/uploads/2018/08/jared-rice-388260-unsplash-324x324.jpg']+h2";
    String addToWishList = "a[data-product-id='64']";
    String wishListButton = "li>a[rel='noopener noreferrer']";
    By removeFromWishList = By.cssSelector("a[class='remove remove_from_wishlist']");
    By emptyWishListCssSelector = By.cssSelector("td.wishlist-empty");


    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");
        wait = new WebDriverWait(driver, 5);
        driver.findElement(demoStoreDismiss).click();

    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void windowHandlesTest() {

        driver.findElement(By.cssSelector(yogaPilatesTileCssSelector)).click();
        driver.findElement(By.cssSelector(toskaniaTile)).click();
        driver.findElement(By.cssSelector(addToWishList)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(addToWishList)));
        driver.findElement(By.cssSelector(wishListButton)).click();

        Set<String> windows = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();
        windows.remove(parentWindow);
        String secondWindow = windows.iterator().next();
        driver.switchTo().window(secondWindow);

        driver.findElement(removeFromWishList).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyWishListCssSelector));
        String emptyBasketAcceptanceText = driver.findElement(emptyWishListCssSelector).getText();
        Assertions.assertEquals(emptyBasketAcceptanceText, "No products added to the wishlist", "Texts are not equals");
    }
}

