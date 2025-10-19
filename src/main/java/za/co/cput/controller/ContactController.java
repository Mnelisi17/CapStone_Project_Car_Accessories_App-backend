package za.co.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import za.co.cput.domain.Contact;
import za.co.cput.service.ContactService;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.save(contact));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return contactService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
