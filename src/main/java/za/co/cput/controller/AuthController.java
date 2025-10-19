package za.co.cput.controller;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.cput.config.JwtTokenUtil;
import za.co.cput.domain.Contact;
import za.co.cput.domain.Address;

import za.co.cput.domain.User;
import za.co.cput.dto.AuthResponse;
import za.co.cput.dto.LoginRequest;
import za.co.cput.dto.ValidatedUserRegistrationRequest;
import za.co.cput.dto.ContactDTO;
import za.co.cput.dto.AddressDTO;
import za.co.cput.repository.UserRepository;
import za.co.cput.repository.ContactRepository;
import za.co.cput.util.PasswordUtil;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
        @Autowired
        private za.co.cput.repository.AddressRepository addressRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthController() {}

    // ============= LOGIN =============
    @PostMapping("/login")
    // No @PreAuthorize: login should be public
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Login attempt: " + loginRequest.getUsername());

            if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty() ||
                    loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new AuthResponse("Missing username or password"));
            }

            // Find user by contact email
            Optional<Contact> contactOptional = contactRepository.findByEmail(loginRequest.getUsername());
            if (contactOptional.isEmpty() || contactOptional.get().getUser() == null) {
                System.out.println("User not found: " + loginRequest.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthResponse("Invalid credentials"));
            }
            User user = contactOptional.get().getUser();


            boolean matches = PasswordUtil.verifyPassword(loginRequest.getPassword(), user.getPassword());
            System.out.println("Password matches: " + matches);

            if (!matches) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthResponse("Invalid credentials"));
            }

            String token = jwtTokenUtil.generateToken(user.getContact().getEmail(), user.getRole().name());
            System.out.println("Generated token: " + token);

            // Build ContactDTO
            ContactDTO contactDTO = null;
            if (user.getContact() != null) {
                contactDTO = new ContactDTO.Builder()
                        .setId(user.getContact().getId())
                        .setEmail(user.getContact().getEmail())
                        .setPhoneNumber(user.getContact().getPhoneNumber())
                        .build();
            }

            // Build AddressDTO
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

            return ResponseEntity.ok(
                new AuthResponse(
                    "Login successful",
                    user.getUserId(),
                    user.getContact() != null ? user.getContact().getEmail() : null,
                    user.getName(),
                    user.getRole().name(),
                    token,
                    contactDTO,
                    addressDTO
                )
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("Login failed: " + e.getMessage()));
        }
    }


    // ============= REGISTER =============
    @PostMapping("/register")
    // No @PreAuthorize: registration should be public
    public ResponseEntity<AuthResponse> register(@RequestBody ValidatedUserRegistrationRequest userRequest) {
        try {
            if (userRequest.getContact() == null || userRequest.getContact().getEmail() == null || userRequest.getContact().getEmail().isEmpty() ||
                userRequest.getName() == null || userRequest.getName().isEmpty() ||
                userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new AuthResponse("Missing required fields"));
            }

            if (contactRepository.findByEmail(userRequest.getContact().getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new AuthResponse("Email already exists"));
            }

            String requestedRole = userRequest.getRole() != null ? userRequest.getRole().toUpperCase() : "BUYER";
            User.Role role = "ADMIN".equals(requestedRole) ? User.Role.ADMIN : User.Role.BUYER;

            // Build Address from AddressDTO
            Address address = null;
            if (userRequest.getAddress() != null) {
                address = new Address.Builder()
                        .setStreet(userRequest.getAddress().getStreet())
                        .setCity(userRequest.getAddress().getCity())
                        .setState(userRequest.getAddress().getState())
                        .setZipCode(userRequest.getAddress().getZipCode())
                        .build();
            }

            // Build Contact from ContactDTO
            Contact contact = null;
            if (userRequest.getContact() != null) {
                contact = new Contact.Builder()
                        .setEmail(userRequest.getContact().getEmail())
                        .setPhoneNumber(userRequest.getContact().getPhoneNumber())
                        .build();
            }

            User user = new User.Builder()
                    .setName(userRequest.getName())
                    .setPassword(PasswordUtil.hashPassword(userRequest.getPassword()))
                    .setAddress(address)
                    .setContact(contact)
                    .setRole(role)
                    .build();

            User savedUser = userRepository.save(user);
            // Save contact with user reference
            if (contact != null) {
                contact.setUser(savedUser);
                contactRepository.save(contact);
            }
            // Save address with user reference
            if (address != null) {
                address.setUser(savedUser);
                addressRepository.save(address);
            }

        // Build ContactDTO
        ContactDTO contactDTO = null;
        if (savedUser.getContact() != null) {
            contactDTO = new ContactDTO.Builder()
                    .setId(savedUser.getContact().getId())
                    .setEmail(savedUser.getContact().getEmail())
                    .setPhoneNumber(savedUser.getContact().getPhoneNumber())
                    .build();
        }

        // Build AddressDTO
        AddressDTO addressDTO = null;
        if (savedUser.getAddress() != null) {
            addressDTO = new AddressDTO.Builder()
                    .setId(savedUser.getAddress().getId())
                    .setStreet(savedUser.getAddress().getStreet())
                    .setCity(savedUser.getAddress().getCity())
                    .setState(savedUser.getAddress().getState())
                    .setZipCode(savedUser.getAddress().getZipCode())
                    .build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new AuthResponse(
                "Registration successful",
                savedUser.getUserId(),
                savedUser.getContact() != null ? savedUser.getContact().getEmail() : null,
                savedUser.getName(),
                savedUser.getRole().name(),
                null,
                contactDTO,
                addressDTO
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("Registration failed: " + e.getMessage()));
        }
    }

    // ============= GET LOGGED-IN USER =============
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<AuthResponse> getLoggedUser() {
        try {
            // Retrieve authenticated principal set in JwtAuthenticationFilter
            Object principal = org.springframework.security.core.context.SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();

            User user = null;
            if (principal instanceof User) {
                user = (User) principal;
            } else if (principal instanceof za.co.cput.security.UserPrincipal) {
                za.co.cput.security.UserPrincipal up = (za.co.cput.security.UserPrincipal) principal;
                Optional<Contact> contactOptional = contactRepository.findByEmail(up.getEmail());
                if (contactOptional.isEmpty() || contactOptional.get().getUser() == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new AuthResponse("User not found"));
                }
                user = contactOptional.get().getUser();
            } else if (principal instanceof String) {
                // Principal may be the username (email). Try to lookup user by contact email
                Optional<Contact> contactOptional = contactRepository.findByEmail((String) principal);
                if (contactOptional.isEmpty() || contactOptional.get().getUser() == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new AuthResponse("User not found"));
                }
                user = contactOptional.get().getUser();
            }

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new AuthResponse("User not found"));
            }
            // Build ContactDTO
            ContactDTO contactDTO = null;
            if (user.getContact() != null) {
                contactDTO = new ContactDTO.Builder()
                        .setId(user.getContact().getId())
                        .setEmail(user.getContact().getEmail())
                        .setPhoneNumber(user.getContact().getPhoneNumber())
                        .build();
            }

            // Build AddressDTO
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

            return ResponseEntity.ok(new AuthResponse(
                "User fetched",
                user.getUserId(),
                user.getContact() != null ? user.getContact().getEmail() : null,
                user.getName(),
                user.getRole().name(),
                null,
                contactDTO,
                addressDTO
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("An unexpected error occurred"));
        }
    }
}
