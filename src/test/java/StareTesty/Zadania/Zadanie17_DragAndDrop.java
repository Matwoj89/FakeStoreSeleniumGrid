package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Zadanie17_DragAndDrop {
    WebDriver driver;
    Actions actions;
    WebElement dragElement;
    WebElement dropElement;
    String expectedMessage = "Dropped!";

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions = new Actions(driver);
        driver.navigate().to("https://fakestore.testelka.pl/actions/");
        dragElement = driver.findElement(By.cssSelector("[id='draggable']"));
        dropElement = driver.findElement(By.cssSelector("#droppable"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",dragElement);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void exercise1() {
        actions.dragAndDrop(dragElement,dropElement).build().perform();
        Assertions.assertEquals(expectedMessage,dropElement.getText(), "Element is not dropped correctly");
    }
    @Test
    public void exercise2() {
        actions.clickAndHold(dragElement).moveToElement(dropElement).moveByOffset(60,60).release().build().perform();
        Assertions.assertEquals(expectedMessage,dropElement.getText(), "Element is not dropped correctly");
    }

    @Test void exercise3(){
        actions.dragAndDropBy(dragElement,160,40).build().perform();
        Assertions.assertEquals(expectedMessage,dropElement.getText(),  "Was the element dropped correctly?");
    }
}