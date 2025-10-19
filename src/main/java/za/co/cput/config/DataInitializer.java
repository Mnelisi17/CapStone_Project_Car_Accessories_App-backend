package za.co.cput.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.cput.domain.User;
import za.co.cput.repository.UserRepository;
import za.co.cput.util.PasswordUtil;
import za.co.cput.domain.Contact;
import za.co.cput.domain.Address;
import za.co.cput.repository.ContactRepository;
import za.co.cput.repository.AddressRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only create admin if not present
        boolean adminExists = userRepository.findAll().stream()
            .anyMatch(u -> u.getContact() != null && "admin@caraccessories.com".equals(u.getContact().getEmail()));
        if (!adminExists) {
            Contact contact = new Contact.Builder()
                .setEmail("admin@caraccessories.com")
                .setPhoneNumber("+1234567890")
                .build();

            Address address = new Address.Builder()
                .setStreet("123 Main St")
                .setCity("Cape Town")
                .setState("Western Cape")
                .setZipCode("8000")
                .build();

            User admin = new User.Builder()
                .setName("Super Admin")
                .setPassword(PasswordUtil.hashPassword("admin123"))
                .setRole(User.Role.ADMIN)
                .setAddress(address)
                .setContact(contact)
                .build();
            User savedAdmin = userRepository.save(admin);
            contact.setUser(savedAdmin);
            address.setUser(savedAdmin);
            contactRepository.save(contact);
            addressRepository.save(address);
            System.out.println("Default admin user created: admin@caraccessories.com / admin123");
        }
    }
}
