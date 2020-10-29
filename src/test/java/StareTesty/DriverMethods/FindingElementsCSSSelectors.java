package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindingElementsCSSSelectors {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 500));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");

    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void getCurrentURLExample(){
        //Wyszukiwanie ID
        //#jakieś-id                      e.g #rememberme
        //[id='jakieś-id']                    [name='login'] [value='Zaloguj się'] [type='checkbox'][value='forever']
        //button[id='jakieś-id']              button[name='register'] button[name='login']
        //button#jakieś-id                    input#password

        //WYSZUKIWANIE KLASY
        // .jakaś-klasa                        .lost_password
        // [class='jakaś-klasa'] <---dokładnie jedna taka klasa  e.g. [aria-label='0 items in cart']
        // [class='pierwsza-klasa druga-klasa']  <---dwie lub wiecej klas e.g. [aria-label='0 items in cart'][tabindex='26']
        // button[class='jakaś-klasa'] other exaample  .product-imzagev[height='200px'][data-a-hires='https://images-na.ssl-images-amazon.com/images/I/81-80FPGX0L._AC_SY400_.jpg']
        // button.jakaś-klasa

            WebElement welcomeText = driver.findElement(By.cssSelector("#main-page-welcome"));
            Assertions.assertEquals("Witaj w Wikipedii,", welcomeText.getText(), "Texts are not equals");
        }
    }

