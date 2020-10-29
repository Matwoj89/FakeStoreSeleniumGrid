package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;

public class Zadanie10 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/metody-na-elementach/");

        wait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void checkElementsStateTest(){
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement hiddenButton = driver.findElement(By.cssSelector("[class='button'][value='żeglarstwo']"));
        List<WebElement> yellowButtons = driver.findElements(By.cssSelector("a.button"));
        WebElement selecktedCheckbox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement notSelecktedCheckbox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement selecktedRadiobutton = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement notSelecktedRadiobutton = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));
        List<WebElement> elementsWithButtonClass = driver.findElements(By.cssSelector(".button"));

        for (WebElement element:elementsWithButtonClass){
            Assertions.assertEquals("a",element.getTagName(), "Element's tag is not 'a'.");
        }

        Assertions.assertAll("Checking properties of elements",
                ()->Assertions.assertFalse(mainPageButton.isEnabled(), "Main button is not disabled"),
                ()->Assertions.assertFalse(hiddenButton.isDisplayed(), "Button is not hidden"),
                ()->assertThatButtonsAreYellow(yellowButtons),
                ()->Assertions.assertTrue(selecktedCheckbox.isSelected(),"Selectbox is not selected"),
                ()->Assertions.assertTrue(selecktedRadiobutton.isSelected(), "Radiobutton is not selected"),
                ()->Assertions.assertFalse(notSelecktedCheckbox.isSelected(),"Selectbox is not selected"),
                ()->Assertions.assertFalse(notSelecktedRadiobutton.isSelected(), "Radiobutton is not selected")
                );

    }

    public void assertThatButtonsAreYellow(List<WebElement> buttons){
        for (WebElement button:buttons) {
            String color = button.getCssValue("background-color");
            Assertions.assertEquals("rgba(245, 233, 101, 1)", color, "This in not correct color.");
        }
    }
}
