package za.co.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.domain.Contact;
import za.co.cput.repository.ContactRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }
}
