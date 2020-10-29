package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionKeyboardExample {
    WebDriver driver;
    Actions actions;
    By demoStoreBar = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions = new Actions(driver);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        driver.findElement(demoStoreBar).click();

    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void sendKeysExample() {

        WebElement login = driver.findElement(By.cssSelector("#username"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",login);

        //actions.sendKeys(login,"mwdazn@gmail.com").build().perform();
        actions.sendKeys(login,Keys.SHIFT, "mwdazn@gmail.com").build().perform();
        actions.click(login).sendKeys(Keys.LEFT_ALT, "abc").build().perform();

    }

    @Test
    public void pressingKeysExample(){

        WebElement login = driver.findElement(By.cssSelector("#username"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",login);
        actions.keyUp(Keys.SHIFT).sendKeys(login, "abcdef").build().perform();

    }

    @Test
    public void allegroSearchTest(){
        driver.navigate().to("https://allegro.pl/");
        driver.findElement(By.cssSelector("[data-role='accept-consent']")).click();

        //actions.sendKeys(login,Keys.SHIFT, "mwdazn@gmail.com").build().perform();
        WebElement searchBar = driver.findElement(By.cssSelector("input[type='search']"));
        actions.sendKeys(searchBar,Keys.SHIFT, "lego").build().perform();
    }

    @Test
    public void pressingMultipleKeysExample(){
        driver.navigate().to("https://jqueryui.com/selectable/#default");

        driver.switchTo().frame(0);
        List<WebElement> listItems = driver.findElements(By.cssSelector("li.ui-selectee"));

        actions.keyDown(Keys.CONTROL).click(listItems.get(0)).click(listItems.get(1)).click(listItems.get(2)).keyUp(Keys.CONTROL).build().perform();

    }
}
