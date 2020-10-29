package StareTesty.DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitsExcercise {

    WebDriver driver;
    WebDriverWait wait;

    By cookieConsentBar = By.cssSelector("a[class*='dismiss-link']");
    By windSurfingGroup = By.cssSelector("a[href*='windsurfing']");
    By productEgipt = By.xpath(".//h2[text()='Egipt – El Gouna']");
    By addToCartButton = By.cssSelector("button[name='add-to-cart']");
    By goToCartButton = By.cssSelector("a.cart-contents");
    By addCoupon = By.cssSelector("button[name='apply_coupon']");
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
    public void validCouponTest() {
        applyCoupon(validCoupon);

    }

    private void applyCoupon(String validCoupon) {
        By couponCodeField = By.cssSelector("input[name='coupon_code']");
        By applyCouponButton = By.cssSelector("button[name='apply_coupon']");
        driver.findElement(couponCodeField).sendKeys(validCoupon);
        driver.findElement(applyCouponButton).click();

    }

    private void waitForProcessingToBeFinished (){

        By applyCouponButton = By.cssSelector("button[name='apply_coupon']");
        WebElement isButtonPresentInDOM =
                wait.until(ExpectedConditions.presenceOfElementLocated(applyCouponButton));

    }

    private String getCouponMessage() {
        return driver.findElement(By.cssSelector("woocommerce-message")).getText();

    }
}