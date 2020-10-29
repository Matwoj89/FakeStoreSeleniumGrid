package StareTesty.FinishCourseTestProject;

import Helpers.TestStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cart {

    ChromeDriver driver;
    WebDriverWait wait;
    Actions actions;
    By vacationElements = By.cssSelector(".woocommerce-loop-product__title");
    By vacationCategory = By.cssSelector(".woocommerce-loop-category__title");
    By addToBasket = By.cssSelector("button[name='add-to-cart']");
    By greenScreenConfirmation = By.cssSelector(".woocommerce-message");
    By addToBasketConfirmationText = By.cssSelector("div>a[class='button wc-forward']");
    By vacationsQuantity = By.cssSelector("input[type='number']");
    By quantityInBasket = By.cssSelector("tbody>tr:nth-child(1)>td>div>input[class='input-text qty text']");
    By removeVacationFromBasket = By.cssSelector("a[class='remove']");
    By emptyBasketConfirmation = By.cssSelector("p[class='cart-empty woocommerce-info']");
    @RegisterExtension
    TestStatus status = new TestStatus();

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        actions = new Actions(driver);
        driver.manage().window().setSize(new Dimension(1300, 800));
        driver.manage().window().setPosition(new Point(10, 30));

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,7);

        driver.navigate().to("https://fakestore.testelka.pl/");
        driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']")).click();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    //•	użytkownik ma możliwość dodania wybranej wycieczki do koszyka ze strony tej wycieczki,
    @Test
    public void moveToRandomVacationAndAddToBasket() {
        randomVacationSelection();
        addVacationToBasket();
    }

    //•	użytkownik ma możliwość dodania wybranej wycieczki do koszyka ze strony kategorii,
    @Test
    public void moveToRandomCattegoryAndAddToBasket(){
        randomCattegorySelection();
        randomVacationSelection();
        addVacationToBasket();
    }

    //•	użytkownik ma możliwość dodania co najmniej 10 wycieczek do koszyka (w sumie i w dowolnej kombinacji),
    //•	użytkownik ma możliwość dodania 10 różnych wycieczek do koszyka,
    @Test
    public void addMin10Vacation(){
        for (int i = 0; i < 10; i++) {
            randomVacationSelection();
            addVacationToBasket();
            driver.navigate().to("https://fakestore.testelka.pl/");
        }
    }

    //•	użytkownik ma możliwość wybrania ilości wycieczek, które chce zakupić, na stronie produktu (np. dla zamówienia dla kilku osób),
    @Test
    public void countVacationTest(){
        randomVacationSelection();
        driver.findElement(vacationsQuantity).clear();
        driver.findElement(vacationsQuantity).sendKeys("7");
        addVacationToBasket();
        Assertions.assertEquals("7", driver.findElement(vacationsQuantity).getAttribute("value"),
                "The number of ordered vacations in basket are not Equal to expected ");
    }

    //•	użytkownik ma możliwość zmiany ilości wybranej wycieczki (pojedynczej pozycji) na stronie koszyka,
    // @RepeatedTest(5)
    @Test
    public void changeQuantityOfVacationInBasket(){
        countVacationTest();
        goToBasketFromVacation();
        driver.findElement(quantityInBasket).clear();
        driver.findElement(quantityInBasket).sendKeys("12");
        Assertions.assertEquals("12", driver.findElement(quantityInBasket).getAttribute("value"),
                "The number of ordered vacations in basket are not Equal to expected ");
    }

  //  •	użytkownik ma możliwość usunięcia wycieczki na stronie koszyka (całej pozycji),
    @Test
    public void deleteVacationFromBasket(){
        countVacationTest();
        goToBasketFromVacation();
        driver.findElement(removeVacationFromBasket).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyBasketConfirmation));
        Assertions.assertEquals("Twój koszyk jest pusty.", driver.findElement(emptyBasketConfirmation).getText(),
                "Basket is not empty");
    }


    public void randomVacationSelection(){
        Random rand = new Random();
        List<WebElement> vacationElementsList = driver.findElements(vacationElements);
        int numberOfVacation = vacationElementsList.size();
        int randomVacationIndex = rand.nextInt(numberOfVacation);
        WebElement randomVacationElement = vacationElementsList.get(randomVacationIndex);
        randomVacationElement.click();
    }

    public void randomCattegorySelection(){
        Random rand = new Random();
        List<WebElement> vacationElementsList = driver.findElements(vacationCategory);
        int numberOfVacationCategory = vacationElementsList.size();
        int randomVacationCategoryIndex = rand.nextInt(numberOfVacationCategory);
        WebElement randomVacationCategoryElement = vacationElementsList.get(randomVacationCategoryIndex);
        randomVacationCategoryElement.click();
    }

    public void addVacationToBasket(){
        driver.findElement(addToBasket).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenScreenConfirmation));
        Assertions.assertEquals("Zobacz koszyk", driver.findElement(addToBasketConfirmationText).getText(), "Vacation was not added to basket");
    }

    public void goToBasketFromVacation(){
        driver.findElement(addToBasketConfirmationText).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInBasket));
    }
}
