package za.co.cput.factory;

import za.co.cput.domain.User;
import za.co.cput.domain.Contact;
import za.co.cput.domain.Address;

public class UserFactory {
    public static User buildUser(Long userId, String name,String password, User.Role role, Contact contact, Address address) {
        return new User.Builder()
                .setUserId(userId)
                .setName(name)
                .setPassword(password)
                .setRole(role)
                .setContact(contact)
                .setAddress(address)
                .build();
    }
}
