package za.co.cput.factory;

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
