package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Zadanie7 {
    WebDriver driver;
    String userName = "Achaja8989";
    String email = "Achaja8989@op.pl";
    String password = "xKp2L44A8hFhnyM9Ra9d";
    String wrongPassword = "wrong";
    String nonexistentUserName = "nonexistentUser";
    String nonexistentEmail = "nonexistentEmailAchaja8989@op.pl";
    String userFullName = "achaja8989";
    String myAccountContent;
    String errorMessageText;
    String expectedMessage;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 500));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void existentUsernameCorrectPasswordTest(){
        logIn(userName, password);
        myAccountContent = getDisplayedName();
        Assertions.assertTrue(myAccountContent.contains(userFullName),
                "My Account page does not contain correct name. Expected name:  " + userFullName + " was not found in a string: " + myAccountContent);
    }

    @Test
    public void existentEmailCorrectPasswordTest(){
        logIn(email, password);
        myAccountContent = getDisplayedName();
        Assertions.assertTrue(myAccountContent.contains(userFullName),
                "My Account page does not contain correct name,. Ecpected name: " + userFullName + " was not found in a string: " + myAccountContent);
    }

    @Test
    public void existentUsernameIncorrectPasswordTest(){
        logIn(userName, wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "Błąd: Wprowadzone hasło dla użytkownika " + userName + " jest niepoprawne. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void nonexistentUsernameIncorrectPasswordTest(){
        logIn(nonexistentUserName, wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "Nieznany użytkownik. Proszę spróbować ponownie lub użyć adresu email.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void existentEmailIncorrectPasswordTest(){
        logIn(nonexistentEmail, wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "Nieznany adres email. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void existentEmailNoPasswordTest(){
        logIn(email, "");
        errorMessageText = getErrorMessage();
        expectedMessage = "Błąd: Hasło jest puste.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void noUsernameDummyPasswordTest(){
        logIn("", wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "Błąd: Nazwa użytkownika jest wymagana.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error ,essage is not correct");
    }

    private void logIn(String userName, String password){
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).sendKeys(Keys.chord(Keys.ENTER));
    }

    private String getDisplayedName(){
        return driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
    }
    private String getErrorMessage(){
        return driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
    }
}

