package TestyPOM;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.OrderReceivedPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTests extends BaseTest {

    @Test
    public void buyWithoutAccountTest() {
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();
        CartPage cartPage = productPage.addToCart().viewCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        OrderReceivedPage orderReceivedPage = checkoutPage.typeName(testData.getCustomer().getName())
                .typeLastName(testData.getCustomer().getLastname())
                .chooseCountry(testData.getAddress().getCountryCode())
                .typeAddress(testData.getAddress().getStreet())
                .typePostalCode(testData.getAddress().getPostalCode())
                .typeCity(testData.getAddress().getCity())
                .typePhone(testData.getContact().getPhone())
                .typeEmail(testData.getContact().getEmail())
                .typeCardNumber(testData.getCard().getCardNumber())
                .typeCardExpirationDate(testData.getCard().getCardExpirationDate())
                .typeCvcCode(testData.getCard().getCardCvc())
                .selectAcceptTerms()
                .order();
        boolean isOrderSuccessful = orderReceivedPage.isOrderSuccessful();
        assertTrue(isOrderSuccessful, "No order successful message found.");

    }
}
