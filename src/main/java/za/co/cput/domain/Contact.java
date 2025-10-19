package za.co.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phoneNumber;

    @OneToOne
    @JsonBackReference
    private User user;

    private Contact(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.user = builder.user;
    }

    public Contact() {}

    public static class Builder {
        private Long id;
        private String email;
        private String phoneNumber;
        private User user;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder setUser(User user) { this.user = user; return this; }
        public Builder copy(Contact contact) {
            this.id = contact.id;
            this.email = contact.email;
            this.phoneNumber = contact.phoneNumber;
            this.user = contact.user;
            return this;
        }
        public Contact build() { return new Contact(this); }
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setUser(User user) { this.user = user; }
}
