package StareTesty.FinishCourseTestProject;


       /* •	użytkownik jest informowany o błędach w formularzu na stronie płatności poprzez odpowiednie komunikaty,
        •       	użytkownik ma możliwość zalogowania się na stronie płatności i dokonać płatności jako zalogowany użytkownik,
        •       	użytkownik ma możliwość założenia konta na stronie płatności i dokonać jednocześnie płatności,
        •       	użytkownik ma możliwość dokonania zakupu bez zakładania konta,
        •	        użytkownik, który posiada konto może zobaczyć swoje zamówienia na swoim koncie,
        •       	użytkownik po dokonaniu zamówienia może zobaczyć podsumowanie, które zawiera numer zamówienia,
        poprawną datę, kwotę, metodę płatności, nazwę i ilość zakupionych produktów.*/


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Payment extends Cart {

    By goToPaymentsButton = By.cssSelector("a[class='checkout-button button alt wc-forward']");
    By buyAndPayButton = By.cssSelector("#place_order");
    By cardNumberField = By.cssSelector("[name='cardnumber']");

    By firstNameField = By.cssSelector("#billing_first_name");
    By lastNameField = By.cssSelector("#billing_last_name");
    By countryDropdownList = By.cssSelector("#billing_country");
    By street1 = By.cssSelector("#billing_address_1");
    By street2 = By.cssSelector("#billing_address_2");
    By postCode = By.cssSelector("#billing_postcode");
    By city = By.cssSelector("#billing_city");
    By phone = By.cssSelector("#billing_phone");
    By mail = By.cssSelector("#billing_email");
    By provinceDropdownList = By.cssSelector("#billing_state");
    By createAccountCheckBox = By.cssSelector("#createaccount");
    By newAccountPasswordField = By.cssSelector("#account_password");
    By myAccountButton = By.cssSelector("#menu-item-201");
    By myOrdersButton = By.cssSelector("ul>li[class*='woocommerce-MyAccount-navigation-link--orders']");
    By viewButton = By.cssSelector(".woocommerce-button");

    By expirationDateField = By.cssSelector("[name='exp-date']");
    By cvcField = By.cssSelector("[name='cvc']");

    String cardNumber = "4242424242424242";
    String cardExpirationDate = "1025";
    String cvcNumber = "111";
    String country = "Włochy";
    String province = "Rzym";
    String newAccountPassword = "Abracadabra2020!!";

    By loginButton = By.cssSelector("a[class='showlogin']");
    By loginUserNameField = By.cssSelector("#username");
    By loginUserPasswordField = By.cssSelector("#password");
    By loginButtonAcceptance = By.cssSelector("button[name='login']");
    By regulationCheckbox = By.cssSelector("#terms");
    By orderConfirmationField = By.cssSelector("h1[class='entry-title']");

    String permAccountMail = "Achaja2020";
    String permAccountPassword = "4pancernych!";


    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        actions = new Actions(driver);
        driver.manage().window().setSize(new Dimension(1300, 800));
        driver.manage().window().setPosition(new Point(10, 30));

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 13);

        driver.navigate().to("https://fakestore.testelka.pl/");
        driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']")).click();

    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    //•	użytkownik ma możliwość zalogowania się na stronie płatności i dokonać płatności jako zalogowany użytkownik,
    // DOKONCZYC

    @Test
    public void loginAndPayTest() {
        oneVacationOrder();
        loginOnPayments();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mail)).click();
        insertCardNumber(cardNumber);
        insertCardCvcCode(cvcNumber);
        insertCardExpirationDate(cardExpirationDate);
        driver.findElement(regulationCheckbox).click();

        driver.findElement(buyAndPayButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationField));
        Assertions.assertEquals("Zamówienie", driver.findElement(orderConfirmationField).getText(), "The order has not been placed");

    }

    //•	użytkownik ma możliwość dokonania zakupu bez zakładania konta,

    @Test
    public void buyAndPayWithoutAccountTest() {
        oneVacationOrder();
        inputUserData();
        driver.findElement(mail).sendKeys("Cannoli@gmail.com");

        insertCardNumber(cardNumber);
        insertCardCvcCode(cvcNumber);
        insertCardExpirationDate(cardExpirationDate);
        driver.findElement(regulationCheckbox).click();
        driver.findElement(buyAndPayButton).click();
        wait.until(ExpectedConditions.urlContains("/zamowienie/zamowienie-otrzymane/"));
        Assertions.assertEquals("Zamówienie otrzymane", driver.findElement(orderConfirmationField).getText(), "The order has not been placed");
    }

    //użytkownik ma możliwość założenia konta na stronie płatności i dokonać jednocześnie płatności,
    //użytkownik, który posiada konto może zobaczyć swoje zamówienia na swoim koncie,

    @Test
    public void buyAndPayWithNewAccountTest() {
        oneVacationOrder();
        inputUserData();
        driver.findElement(mail).sendKeys("NewAccount@gmail.com");
        insertCardNumber(cardNumber);
        insertCardCvcCode(cvcNumber);
        insertCardExpirationDate(cardExpirationDate);
        driver.findElement(regulationCheckbox).click();
        driver.findElement(createAccountCheckBox).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountPasswordField)).click();
        slowType(driver.findElement(newAccountPasswordField), newAccountPassword);
        driver.findElement(buyAndPayButton).click();

        wait.until(ExpectedConditions.urlContains("/zamowienie/zamowienie-otrzymane/"));
        driver.findElement(myAccountButton).click();
        driver.findElement(myOrdersButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(viewButton));

        int actualNumberOfOrders = driver.findElements(By.cssSelector("tr.order")).size();
        int expectedNumberOfOrders = 1;

        Assertions.assertEquals(expectedNumberOfOrders, actualNumberOfOrders,
                "Number of orders in my account is not correct. Expected: " + expectedNumberOfOrders +
                        " but was: " + actualNumberOfOrders);

        //deleting user after test
        driver.findElement(myAccountButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".delete-me"))).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Test
    public void orderSummary() {
        oneVacationOrder();
        inputUserData();
        driver.findElement(mail).sendKeys("Cannoli@gmail.com");

        insertCardNumber(cardNumber);
        insertCardCvcCode(cvcNumber);
        insertCardExpirationDate(cardExpirationDate);
        driver.findElement(regulationCheckbox).click();
        driver.findElement(buyAndPayButton).click();
        wait.until(ExpectedConditions.urlContains("/zamowienie/zamowienie-otrzymane/"));
        // numer zamówienia, poprawną datę, kwotę, metodę płatności, nazwę i ilość zakupionych produktów.*/

        int orderNumber = Integer.parseInt(driver.findElement(By.cssSelector(".order > strong")).getText());
        String orderAcceptationDate = driver.findElement(By.cssSelector("ul>.date")).getText();

        String actualData = getCurrentDate();

        WebElement paymentMethod = driver.findElement(By.cssSelector(".method > Strong"));
        WebElement quantityOfVacation = driver.findElement(By.cssSelector(".order_item>td>strong"));


        assertAll(
                () -> assertTrue(orderNumber > 0, "Wrong message"),
                () -> assertEquals(orderAcceptationDate, "DATA:" + "\n" + actualData, "Wrong date"),
                () -> assertEquals(paymentMethod.getText(), "Karta debetowa/kredytowa (Stripe)", "Wrong payment method"),
                () -> assertEquals(quantityOfVacation.getText(), "× 1", "Wrong ammount of vacation")

        );
    }

    // użytkownik jest informowany o błędach w formularzu na stronie płatności poprzez odpowiednie komunikaty,

    @Test
    public void emptyFieldsValidationTest(){
        oneVacationOrder();
        insertCardNumber(cardNumber);
        insertCardCvcCode(cvcNumber);
        insertCardExpirationDate(cardExpirationDate);
        String errorMessage = orderAndWaitForErrorMessage();
        assertAll(
                ()->assertTrue(errorMessage.contains("Imię płatnika jest wymaganym polem."),
                        "Error message doesn't contain lack of first name error."),
                ()->assertTrue(errorMessage.contains("Nazwisko płatnika jest wymaganym polem."),
                        "Error message doesn't contain lack of last name error."),
                ()->assertTrue(errorMessage.contains("Ulica płatnika jest wymaganym polem."),
                        "Error message doesn't contain lack of street name error."),
                ()->assertTrue(errorMessage.contains("Miasto płatnika jest wymaganym polem."),
                        "Error message doesn't contain lack of city name error."),
                ()->assertTrue(errorMessage.contains("Telefon płatnika jest wymaganym polem."),
                        "Error message doesn't contain lack of phone number error."),
                ()->assertTrue(errorMessage.contains("Adres email płatnika jest wymaganym polem."),
                        "Error message doesn't contain lack of email address error."),
                ()->assertTrue(errorMessage.contains("Proszę przeczytać i zaakceptować regulamin sklepu aby móc sfinalizować zamówienie"),
                        "Error message doesn't contain lack of terms acceptance error.")
        );
    }
    private String getCurrentDate() {

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime(); // pobranie daty z obiektu kalendarza
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM, yyyy");
        String currentDate = simpleDateFormat.format(date);
        return currentDate;
    }

    public void loginOnPayments() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginUserNameField)).sendKeys(permAccountMail);
        wait.until(ExpectedConditions.elementToBeClickable(loginUserPasswordField)); //.sendKeys(loginPassword);
        slowType(driver.findElement(loginUserPasswordField), permAccountPassword);
        driver.findElement(loginButtonAcceptance).click();
    }


    public void oneVacationOrder() {
        super.randomVacationSelection();
        addVacationToBasket();
        goToBasketFromVacation();
        driver.findElement(goToPaymentsButton).click();
    }


    public void inputUserData() {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).sendKeys("Mateusz");
        wait.until(ExpectedConditions.elementToBeClickable(lastNameField)).sendKeys("Wojciechowski");
        fillCountryDropdownList(country);
        wait.until(ExpectedConditions.elementToBeClickable(street1)).sendKeys("Piazza");
        wait.until(ExpectedConditions.elementToBeClickable(street2)).sendKeys("Navona");
        wait.until(ExpectedConditions.elementToBeClickable(postCode)).sendKeys("00-118");
        wait.until(ExpectedConditions.elementToBeClickable(city)).sendKeys("Rzym");
        fillProvinceDropdownList(province);
        wait.until(ExpectedConditions.elementToBeClickable(phone)).sendKeys("777-666-555");

    }

    public void insertCardNumber(String cardNumber) {
        driver.switchTo().frame(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberField)).sendKeys(cardNumber);
        driver.switchTo().defaultContent();
        return;
    }

    public void insertCardExpirationDate(String cardExpirationDate) {
        driver.switchTo().frame(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(expirationDateField)).sendKeys(cardExpirationDate);
        driver.switchTo().defaultContent();
        return;
    }

    public void insertCardCvcCode(String cvcNumber) {
        driver.switchTo().frame(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cvcField)).sendKeys(cvcNumber);
        driver.switchTo().defaultContent();
        return;
    }

    public void fillCountryDropdownList(String country) {
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdownList));
        WebElement countryDropdownListElement = driver.findElement(countryDropdownList);
        Select dropdown = new Select(countryDropdownListElement);
        dropdown.selectByVisibleText(country);
    }

    public void fillProvinceDropdownList(String province) {
        wait.until(ExpectedConditions.elementToBeClickable(provinceDropdownList));
        WebElement countryDropdownListElement = driver.findElement(provinceDropdownList);
        Select dropdown = new Select(countryDropdownListElement);
        dropdown.selectByVisibleText(province);
    }

    private void slowType(WebElement element, String text) {
        for (int i = 0; i < text.length(); i++) {
            element.sendKeys(Character.toString(text.charAt(i)));
        }
    }

    private String orderAndWaitForErrorMessage(){
        driver.findElement(buyAndPayButton).click();
        By errorList = By.cssSelector("ul.woocommerce-error");
        return wait.until(ExpectedConditions.presenceOfElementLocated(errorList)).getText();
    }

}
