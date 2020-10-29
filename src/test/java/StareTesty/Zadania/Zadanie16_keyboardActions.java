package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Zadanie16_keyboardActions {
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
        driver.navigate().to("https://fakestore.testelka.pl/actions/");
        driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']")).click();


    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void exercise1() {
        WebElement menu = driver.findElement(By.cssSelector("[id='menu-link']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",menu);
        actions.contextClick(menu).build().perform();
        List<WebElement> menuOptions = driver.findElements(By.cssSelector("div[id='div-context-menu']>ul>li[class*=menu]"));
        WebElement basketButton = menuOptions.get(2);
        actions.click(basketButton).build().perform();
        String basketAssertionText = driver.findElement(By.cssSelector("p.cart-empty")).getText();
        Assertions.assertEquals(basketAssertionText, "Twój koszyk jest pusty.");
    }
    @Test
    public void exercise2(){
        WebElement Box = driver.findElement(By.cssSelector("[id='double-click']"));
        actions.doubleClick(Box).build().perform();
        String boxColor = Box.getCssValue("background-color");
        String hex = Color.fromString(boxColor).asHex();
        Assertions.assertEquals(hex, "#f55d7a");
    }

    @Test
    public void exercise3(){
        WebElement textbox = driver.findElement(By.cssSelector("[id='input']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",textbox);
        String input = "Monika";
        textbox.sendKeys(input);
        WebElement confirmButton = driver.findElement(By.cssSelector("p>button"));
        actions.click(confirmButton).build().perform();
        WebElement output = driver.findElement(By.cssSelector("#output"));
        Assertions.assertEquals(output.getText(),"Wprowadzony tekst: "+input);

    }

    @Test
    public void exercise4(){
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol>li"));
        actions.keyDown(Keys.CONTROL).click(numbers.get(0)).click(numbers.get(1)).click(numbers.get(2)).keyUp(Keys.CONTROL).build().perform();
        Assertions.assertAll(
                () ->Assertions.assertTrue(numbers.get(0).getAttribute("class").contains("ui-selected"), "item with id=0 was not selected"),
                () ->Assertions.assertTrue(numbers.get(1).getAttribute("class").contains("ui-selected"), "item with id=1 was not selected"),
                () ->Assertions.assertTrue(numbers.get(2).getAttribute("class").contains("ui-selected"), "item with id=2 was not selected")
        );

    }
}


