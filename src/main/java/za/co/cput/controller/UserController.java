package za.co.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.co.cput.domain.User;
import za.co.cput.dto.UserDTO;
import za.co.cput.dto.ContactDTO;
import za.co.cput.dto.AddressDTO;
import za.co.cput.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
    List<User> users = userService.findAll();
    List<UserDTO> dtoList = users.stream().map(user -> {
            ContactDTO contactDTO = null;
            if (user.getContact() != null) {
                contactDTO = new ContactDTO.Builder()
                        .setId(user.getContact().getId())
                        .setEmail(user.getContact().getEmail())
                        .setPhoneNumber(user.getContact().getPhoneNumber())
                        .build();
            }
            AddressDTO addressDTO = null;
            if (user.getAddress() != null) {
                addressDTO = new AddressDTO.Builder()
                        .setId(user.getAddress().getId())
                        .setStreet(user.getAddress().getStreet())
                        .setCity(user.getAddress().getCity())
                        .setState(user.getAddress().getState())
                        .setZipCode(user.getAddress().getZipCode())
                        .build();
            }
            return new UserDTO.Builder()
                    .setUserId(user.getUserId())
                    .setName(user.getName())
                    .setEmail(user.getContact() != null ? user.getContact().getEmail() : null)
                    .setRole(user.getRole())
                    .setContact(contactDTO)
                    .setAddress(addressDTO)
                    .build();
    }).collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @PreAuthorize("hasRole('ADMIN') or (principal instanceof T(za.co.cput.security.UserPrincipal) and #id == principal.userId)")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.findById(id).map(user -> {
            ContactDTO contactDTO = null;
            if (user.getContact() != null) {
                contactDTO = new ContactDTO.Builder()
                        .setId(user.getContact().getId())
                        .setEmail(user.getContact().getEmail())
                        .setPhoneNumber(user.getContact().getPhoneNumber())
                        .build();
            }
            AddressDTO addressDTO = null;
            if (user.getAddress() != null) {
                addressDTO = new AddressDTO.Builder()
                        .setId(user.getAddress().getId())
                        .setStreet(user.getAddress().getStreet())
                        .setCity(user.getAddress().getCity())
                        .setState(user.getAddress().getState())
                        .setZipCode(user.getAddress().getZipCode())
                        .build();
            }
            UserDTO dto = new UserDTO.Builder()
                    .setUserId(user.getUserId())
                    .setName(user.getName())
                    .setEmail(user.getContact() != null ? user.getContact().getEmail() : null)
                    .setRole(user.getRole())
                    .setContact(contactDTO)
                    .setAddress(addressDTO)
                    .build();
            return ResponseEntity.ok(dto);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
