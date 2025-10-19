package za.co.cput.domain;
/*
222293985
Lennox Komane
group 3F
 */
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @OneToOne
    @JsonBackReference
    private User user;

    private Address(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.city = builder.city;
        this.state = builder.state;
        this.zipCode = builder.zipCode;
        this.user = builder.user;
    }

    public Address() {}

    public static class Builder {
        private Long id;
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private User user;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setStreet(String street) { this.street = street; return this; }
        public Builder setCity(String city) { this.city = city; return this; }
        public Builder setState(String state) { this.state = state; return this; }
        public Builder setZipCode(String zipCode) { this.zipCode = zipCode; return this; }
        public Builder setUser(User user) { this.user = user; return this; }
        public Builder copy(Address address) {
            this.id = address.id;
            this.street = address.street;
            this.city = address.city;
            this.state = address.state;
            this.zipCode = address.zipCode;
            this.user = address.user;
            return this;
        }
        public Address build() { return new Address(this); }
    }

    public Long getId() { return id; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public void setUser(User user) { this.user = user; }
}
