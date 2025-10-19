package za.co.cput.dto;

import za.co.cput.domain.User;
import za.co.cput.domain.Contact;
import za.co.cput.domain.Address;

public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private User.Role role;
    private ContactDTO contact;
    private AddressDTO address;

    private UserDTO(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.email = builder.email;
        this.role = builder.role;
        this.contact = builder.contact;
        this.address = builder.address;
    }

    public static class Builder {
        private Long userId;
        private String name;
        private String email;
        private User.Role role;
        private ContactDTO contact;
        private AddressDTO address;

        public Builder setUserId(Long userId) { this.userId = userId; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setRole(User.Role role) { this.role = role; return this; }
        public Builder setContact(ContactDTO contact) { this.contact = contact; return this; }
        public Builder setAddress(AddressDTO address) { this.address = address; return this; }
        public UserDTO build() { return new UserDTO(this); }
    }

    // Getters
    public Long getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public User.Role getRole() { return role; }
    public ContactDTO getContact() { return contact; }
    public AddressDTO getAddress() { return address; }
}///
