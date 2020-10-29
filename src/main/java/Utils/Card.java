package Utils;

import java.util.Properties;

public class Card {
    private final String cardNumber;
    private final String cardExpirationDate;
    private final String cardCvc;

    public Card(Properties properties) {
        cardNumber = properties.getProperty("card.number");
        cardExpirationDate = properties.getProperty("card.expirationDate");
        cardCvc = properties.getProperty("card.cvc");
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public String getCardCvc() {
        return cardCvc;
    }

}
