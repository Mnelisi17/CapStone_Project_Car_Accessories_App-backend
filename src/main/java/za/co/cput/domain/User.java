package za.co.cput.domain;

import za.co.cput.util.EncryptedStringConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    public enum Role {
        ADMIN, BUYER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;


    @Convert(converter = EncryptedStringConverter.class)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Contact contact;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Password reset fields
    protected String resetToken;
    protected LocalDateTime resetTokenExpiry;
    protected Integer resetAttempts = 0;
    protected LocalDateTime lastResetRequest;

    // User management fields
    protected boolean isActive = true;
    protected LocalDateTime lastLogin;

    // Default constructor
    public User() {}

    // Private constructor for Builder
    private User(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.password = builder.password;
        this.contact = builder.contact;
        this.address = builder.address;
        this.role = builder.role;
    }

    // ===== Builder Pattern =====
    public static class Builder {
        private Long userId;
        private String name;
        private String password;
        private Contact contact;
        private Address address;
        private Role role;

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }


        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder copy(User user) {
            this.userId = user.userId;
            this.name = user.name;
            this.password = user.password;
            this.contact = user.contact;
            this.address = user.address;
            this.role = user.role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    // ===== Getters and Setters =====
    public Long getUserId() { return userId; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public Contact getContact() { return contact; }
    public Address getAddress() { return address; }
    public Role getRole() { return role; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setContact(Contact contact) { this.contact = contact; }
    public void setAddress(Address address) { this.address = address; }
    public void setRole(Role role) { this.role = role; }


    public String getResetToken() {
        return resetToken;
    }
    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
    public LocalDateTime getResetTokenExpiry() {
        return resetTokenExpiry;
    }
    public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }
    public Integer getResetAttempts() {
        return resetAttempts;
    }
    public void setResetAttempts(Integer resetAttempts) {
        this.resetAttempts = resetAttempts;
    }
    public LocalDateTime getLastResetRequest() {
        return lastResetRequest;
    }
    public void setLastResetRequest(LocalDateTime lastResetRequest) {
        this.lastResetRequest = lastResetRequest;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    // ===== toString =====
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", contact=" + contact +
                ", address=" + address +
                ", role=" + role +
                '}';
    }
}
//