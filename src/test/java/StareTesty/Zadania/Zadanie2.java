package StareTesty.Zadania;

//Napisz prosty test lokalizacji. Kroki:
//1.	Przejdź na stronę http://wikipedia.pl
//2.	Napisz trzy asercje:
//o	porównaj tytuł strony z oczekiwanym;
//o	porównaj URL strony z oczekiwanym;
//o	znajdź w konsoli deweloperskiej (F12) w zakładce Elements jakiś fragment źródła strony, który mówi o tym w jakiej wersji językowej jest strona; użyj tego fragmentu źródła do asercji.
//3.	Zmień język strony na hiszpański (By.cssSelector(“a[title=’hiszpański’]”)).
//4.	Napisz trzy asercje:
//o	porównaj tytuł strony z oczekiwanym;
//o	porównaj URL strony z oczekiwanym;
//o	znajdź w konsoli deweloperskiej (F12) w zakładce Elements jakiś fragment źródła strony, który mówi o tym w jakiej wersji językowej jest strona; użyj tego fragmentu źródła do asercji.
//Pamiętaj o inicjalizacji WebDrivera i zamknięciu sesji na koniec testu.

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie2 {

        WebDriver driver;

        @BeforeEach
        public void driverSetup(){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(800,500));
            driver.manage().window().setPosition(new Point(50,50));
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
            Assertions.assertTrue(driver.getTitle().contains("wolna encyklopedia"), "Page title does not contain 'wolna encyklopedia'");
            Assertions.assertTrue(driver.getPageSource().contains(Source), "Page do not contain " + Source);
            driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
            String Title2 = "Wikipedia, la enciclopedia libre";
            String URL2 = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
            String Source2 = "lang=\"es\"";
            Assertions.assertEquals(Title2, driver.getTitle(), "Current title is not: " + Title2);
            Assertions.assertEquals(URL2, driver.getCurrentUrl(), "Current URL is not: " + URL2);
            Assertions.assertTrue(driver.getPageSource().contains(Source2), "Page do not contain " + Source2);
        }
}
