package StareTesty.Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

    public class Zadania9 {

        WebDriver driver;
        WebDriverWait wait;

        By cookieConsentBar = By.cssSelector("a[class*='dismiss-link']");
        By windSurfingGroup = By.cssSelector("a[href*='windsurfing']");
        By productEgipt = By.xpath(".//h2[text()='Egipt – El Gouna']");
        By addToCartButton = By.cssSelector("button[name='add-to-cart']");
        By goToCartButton = By.cssSelector("a.cart-contents");
       // By addCoupon = By.cssSelector("button[name='apply_coupon']");
        String validCoupon = "10procent";
        String invalidCoupon = "test";

        @BeforeEach
        public void driverSetup() {

            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1295, 760));
            driver.manage().window().setPosition(new Point(10, 40));
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.navigate().to("https://fakestore.testelka.pl/");

            wait = new WebDriverWait(driver, 10);

            driver.findElement(cookieConsentBar).click();
            driver.findElement(windSurfingGroup).click();
            driver.findElement(productEgipt).click();
            driver.findElement(addToCartButton).click();
            driver.findElement(goToCartButton).click();

        }

        @AfterEach
        public void driverQuit() {
            driver.close();
            driver.quit();
        }

        @Test
        public void emptyCouponTest(){
            applyCoupon("");
            Assertions.assertEquals(getAllertText(),"Proszę wpisać kod kuponu.","Alert message was not what expected");
        }
        @Test
        public void incorrectCouponTest(){
            applyCoupon(invalidCoupon);
            Assertions.assertEquals(getAllertText(), "Kupon \"test\" nie istnieje!", "Alert message was not expected.");
        }
        @Test
        public void correctCouponTest(){
            applyCoupon(validCoupon);
            Assertions.assertEquals(getAllertText(), "Kupon został pomyślnie użyty", "Alert message was not expected.");
        }
        @Test
        public void addingCouponWhenAlreadyAppliedTest(){
            applyCoupon(validCoupon);
            waitForProcessingToBeFinished();
            applyCoupon(validCoupon);
            waitForProcessingToBeFinished();
            Assertions.assertEquals(getAllertText(),"Kupon został zastosowany!","Alert message was not expected.");
        }
        @Test
        public void removingCouponTest(){
            applyCoupon(validCoupon);
            waitForProcessingToBeFinished();
            By removeLink = By.cssSelector("a.woocommerce-remove-coupon");
            wait.until(ExpectedConditions.elementToBeClickable(removeLink)).click();
            waitForProcessingToBeFinished();
            Assertions.assertEquals(getAllertText(),"Kupon został usunięty.", "Alert message was not expected.");
        }

        private void applyCoupon(String coupon) {
            By couponCodeField = By.cssSelector("input[name='coupon_code']");
            By applyCouponButton = By.cssSelector("button[name='apply_coupon']");
            driver.findElement(couponCodeField).sendKeys(coupon);
            driver.findElement(applyCouponButton).click();

        }

        private void waitForProcessingToBeFinished (){

            By blockedUI = By.cssSelector("div.blockUI");
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(blockedUI, 0));
            wait.until(ExpectedConditions.numberOfElementsToBe(blockedUI, 0));
        }


        private String getAllertText(){
            By alert = By.cssSelector("[role='alert']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(alert)).getText();
        }
    }

