package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteWebDriverExercise {
        WebDriver driver;

        @BeforeEach
        public void driverSetup() throws MalformedURLException {
            //WebDriverManager.chromedriver().setup();

            // 3 wymagane info:
            // 1 Jaka przeglądarka - domyslnie z Chrome option
            // 2 Trzeba podać wersje (numer)
            // 3 platfroma np Win 10 - domyślny system na jakim pracujesz

            ChromeOptions options = new ChromeOptions();
            options.setCapability(CapabilityType.VERSION, "85");
            options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);

          //  FirefoxOptions options2 = new FirefoxOptions();
          //  options2.setCapability(CapabilityType.VERSION,79);
          //  options2.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);

          //  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options2);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

            driver.manage().window().setSize(new Dimension(1000, 500));
            driver.manage().window().setPosition(new Point(8, 30));
            driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");

        }

        @AfterEach
        public void closeAndQuit() {
            driver.quit();
        }

        @Test
        public void findingElementById() {
            WebElement welcomeText = driver.findElement(By.cssSelector("#main-page-welcome"));
            Assertions.assertEquals("Witaj w Wikipedii,", welcomeText.getText(), "Texts are not equals");
        }

    }
