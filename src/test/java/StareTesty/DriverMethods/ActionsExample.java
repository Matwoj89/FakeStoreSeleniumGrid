package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionsExample {
    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions = new Actions(driver);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void clickExample() {
        driver.navigate().to("https://jqueryui.com/selectable/#default");
        // actions.moveByOffset(726,519).click().build().perform();
        driver.switchTo().frame(0);
        List<WebElement> listElements = driver.findElements(By.cssSelector("#selectable>li"));
        WebElement firstElement = listElements.get(0);
        actions.click(firstElement).build().perform();
    }

    @Test
    public void doubleClickExample() {
        driver.navigate().to("https://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
        //actions.moveByOffset(400,145).doubleClick().perform();

        WebElement box = driver.findElement(By.cssSelector("div[id='box']"));
        actions.doubleClick(box).build().perform();
    }

    @Test
    public void contexClickExample(){
        driver.navigate().to("https://swisnl.github.io/jQuery-contextMenu/demo.html");
      //  actions.moveByOffset(448,202).contextClick().perform();

        WebElement editOption = driver.findElement(By.cssSelector(".context-menu-icon-edit"));
       // actions.contextClick(editOption).click().build().perform();
        actions.moveByOffset(448,202).contextClick().click(editOption).build().perform();
    }

}

