package za.co.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import za.co.cput.domain.Address;
import za.co.cput.service.AddressService;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.save(address));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return addressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}//
////