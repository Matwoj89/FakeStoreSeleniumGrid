package StareTesty.DriverMethods;

import TestHelpers.TestStatus;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Screenshots {
    WebDriver driver;

    @RegisterExtension
    TestStatus status = new TestStatus();

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://www.zooniverse.org");
    }

    @AfterEach
    public void driverQuit(TestInfo info) throws IOException {
        if (status.isFailed){
            System.out.println("Test screenshot is available at: "+takeScreenshot(info));
        }
        driver.quit();
    }

    @Test
    public void screenshotExample() throws IOException {
        driver.findElement(By.cssSelector("button[value='sign-in']")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("hasłotestowe");
        driver.findElement(By.cssSelector("form")).submit();
        WebElement userName = driver.findElement(By.cssSelector("span>button>span[class='site-nav__link'] strong"));
        Assertions.assertEquals("MALAMI2", userName.getText(),
                "Username displayed on header is not correct. The user was probably not logged in.");
    }

    private String takeScreenshot(TestInfo info) throws IOException{
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy HH-mm-ss");
        String path = "C:\\Screenshoty do Selenium\\"
                + info.getDisplayName() + " " + formatter.format(timeNow) +".jpg";
        FileUtils.copyFile(screenshot, new File(path));
        return path;
    }

}
