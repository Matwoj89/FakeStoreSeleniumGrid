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

import java.util.concurrent.TimeUnit;

public class Zadanie13_Alerts {
    WebDriver driver;
    String runButton = "button[class='w3-button w3-bar-item w3-green w3-hover-white w3-hover-text-green']";
    //JavascriptExecutor js;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.w3schools.com/code/tryit.asp?filename=FZ9IOGP56P0O");
        driver.findElement(By.cssSelector("div[id='accept-choices']")).click();
        driver.findElement(By.cssSelector(runButton)).click();
        driver.switchTo().frame("iframeResult");
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void confirmTest() {
        driver.findElement(By.cssSelector("body > button:nth-child(2)")).click();
        driver.switchTo().alert().accept();
        String ExpectedConfirmationText = "Wybrana opcja to OK!";
        String ActualConfirmationText = driver.findElement(By.cssSelector("#demo")).getText();
        Assertions.assertEquals(ExpectedConfirmationText, ActualConfirmationText, "Something went wrong");
    }

    @Test
    public void cancelConfirmationTest() {
        driver.findElement(By.cssSelector("body > button:nth-child(2)")).click();
        driver.switchTo().alert().dismiss();
        String ExpectedConfirmationText = "Wybrana opcja to Cancel!";
        String ActualConfirmationText = driver.findElement(By.cssSelector("#demo")).getText();
        Assertions.assertEquals(ExpectedConfirmationText, ActualConfirmationText, "Something went wrong");
    }

    @Test
    public void confirmPromptTest() {
        driver.findElement(By.cssSelector("body > button:nth-child(6)")).click();
        String textToSend = "Mateunio";
        driver.switchTo().alert().sendKeys(textToSend);
        driver.switchTo().alert().accept();
        String ActualConfirmationText = driver.findElement(By.cssSelector("#prompt-demo")).getText();
        Assertions.assertEquals("Cześć " + textToSend + "! Jak leci?", ActualConfirmationText, "Something went wrong");
    }

    @Test
    public void cancelPromptTest() {
        driver.findElement(By.cssSelector("body > button:nth-child(6)")).click();
        driver.switchTo().alert().dismiss();
        String ExpectedConfirmationText = "Użytkownik anulował akcję.";
        String ActualConfirmationText = driver.findElement(By.cssSelector("#prompt-demo")).getText();
        Assertions.assertEquals(ExpectedConfirmationText, ActualConfirmationText, "Something went wrong");
    }

}
