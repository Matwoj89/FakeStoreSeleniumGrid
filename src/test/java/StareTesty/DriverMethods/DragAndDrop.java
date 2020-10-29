package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class DragAndDrop {
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
    public void offsetExample() {
        driver.navigate().to("http://marcojakob.github.io/dart-dnd/detection_only/");
        WebElement dragPoint = driver.findElement(By.cssSelector(".draggable"));

        actions.dragAndDropBy(dragPoint, 200, 200).build().perform();
        actions.clickAndHold(dragPoint).moveByOffset(200,200).release().build().perform();
        actions.moveToElement(dragPoint).clickAndHold().moveByOffset(200,200).release().build().perform();
    }
    @Test
    public void toElementExample(){
        driver.navigate().to("https://marcojakob.github.io/dart-dnd/nested_dropzones/");
        WebElement dragPoint = driver.findElement(By.cssSelector(".draggable"));
        WebElement dropPoint = driver.findElement(By.cssSelector(".dropzone-inner"));
        actions.dragAndDrop(dragPoint,dropPoint).build().perform();
        actions.clickAndHold(dragPoint).moveByOffset(200,200).build().perform();
        actions.clickAndHold(dragPoint).moveToElement(dropPoint).release().build().perform();
        actions.clickAndHold(dragPoint).release(dragPoint).build().perform();


    }

}
