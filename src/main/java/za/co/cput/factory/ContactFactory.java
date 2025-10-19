package za.co.cput.factory;

import za.co.cput.domain.Contact;
import za.co.cput.domain.User;

public class ContactFactory {
    public static Contact buildContact(Long id, String email, String phoneNumber, User user) {
        return new Contact.Builder()
                .setId(id)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUser(user)
                .build();
    }
}
