package za.co.cput.dto;

public class AuthResponse {
    private String message;
    private Long userId;
    private String email;
    private String role;
    private String token;
    private String name;
    private ContactDTO contact;
    private AddressDTO address;

    // Constructor with all user details
    public AuthResponse(String message, Long userId, String email, String name, String role, String token, ContactDTO contact, AddressDTO address) {
        this.message = message;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
        this.token = token;
        this.contact = contact;
        this.address = address;
    }

    // Constructor with User.Role
    public AuthResponse(String message, Long userId, String email, String name, za.co.cput.domain.User.Role role, String token, ContactDTO contact, AddressDTO address) {
        this.message = message;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role != null ? role.name() : null;
        this.token = token;
        this.contact = contact;
        this.address = address;
    }

    // Constructor without token (for responses without tokens)
    public AuthResponse(String message) {
        this.message = message;
    }

    // Getters & setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ContactDTO getContact() { return contact; }
    public void setContact(ContactDTO contact) { this.contact = contact; }
    public AddressDTO getAddress() { return address; }
    public void setAddress(AddressDTO address) { this.address = address; }
}///
