package za.co.cput.factory;
/*
222293985
Lennox Komane
group 3F
 */
import za.co.cput.domain.Address;
import za.co.cput.domain.User;

public class AddressFactory {
    public static Address buildAddress(Long id, String street, String city, String state, String zipCode, User user) {
        return new Address.Builder()
                .setId(id)
                .setStreet(street)
                .setCity(city)
                .setState(state)
                .setZipCode(zipCode)
                .setUser(user)
                .build();
    }
}
