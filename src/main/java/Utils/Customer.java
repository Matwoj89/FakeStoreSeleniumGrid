package Utils;

import java.util.Properties;

public class Customer {

    private final String name;
    private final String lastname;

    public Customer(Properties properties) {
        name = properties.getProperty("customer.name");
        lastname = properties.getProperty("customer.lastName");
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

}
