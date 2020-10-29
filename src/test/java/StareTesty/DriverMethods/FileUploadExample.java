package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FileUploadExample {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 500));
        driver.manage().window().setPosition(new Point(8, 30) );

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS);
        driver.navigate().to("https://gofile.io/?t=uploadFiles");
    }
    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void fileUploadTest(){
        WebElement uploadFileInput = driver.findElement(By.cssSelector("input[type='file']"));
        String expectedFilename = "IronGit.png";
        String path = "C:\\Users\\mwojciechowski2\\Pictures\\" + expectedFilename;
        uploadFileInput.sendKeys(path);

        String actualFileNameElement = driver.findElement(By.cssSelector("tr>td[class='sorting_1']")).getText();

        Assertions.assertEquals(expectedFilename,actualFileNameElement,"Name of uploaded file is different then expecten one");
    }
}
