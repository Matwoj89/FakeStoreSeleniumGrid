package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationAndClosing {

    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280,720));
    }
    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    // testuje przekierowywanie
    @Test
    public void getCurrentURLExample(){
        String Url = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        driver.navigate().to("https://pl.wikipedia.org");
        String Title = "Wikipedia, wolna encyklopedia";
        String Source = "lang=\"pl\"";
        Assertions.assertEquals(Url, driver.getCurrentUrl(), "Current URL is not: "+ Url);
        Assertions.assertEquals(Title, driver.getTitle(), "Current title is not: " + Title);
        Assertions.assertTrue(driver.getPageSource().contains(Source), "Page do not contain " + Source);
        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        String Title2 = "Wikipedia, la enciclopedia libre";
        String URL2 = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        String Source2 = "lang=\"es\"";
        Assertions.assertEquals(Title2, driver.getTitle(), "Current title is not: " + Title2);
        Assertions.assertEquals(URL2, driver.getCurrentUrl(), "Current URL is not: " + URL2);
        Assertions.assertTrue(driver.getPageSource().contains(Source2), "Page do not contain " + Source2);
    }

    // sprawdza tytuł
   // @Test
   // public void getTitleExample(){
   //     String googleTitle = "Google";
    //     driver.navigate().to("https://google.pl");
    //    Assertions.assertEquals(googleTitle, driver.getTitle(), "Current Title is not: "+ googleTitle);
   // }

    //sprawdza element na stronie
  /*  @Test
    public void getPageSourceExample(){
        String googleSource = "/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
        driver.navigate().to("https://google.pl");
        Assertions.assertTrue(driver.getPageSource().contains(googleSource), "Page not contain String" + googleSource);
    } */
}
