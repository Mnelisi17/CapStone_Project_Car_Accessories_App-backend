package za.co.cput.dto;
/*
222293985
Lennox Komane
group 3F
 */
public class AddressDTO {
    public AddressDTO() {}
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    private AddressDTO(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.city = builder.city;
        this.state = builder.state;
        this.zipCode = builder.zipCode;
    }

    public static class Builder {
        private Long id;
        private String street;
        private String city;
        private String state;
        private String zipCode;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setStreet(String street) { this.street = street; return this; }
        public Builder setCity(String city) { this.city = city; return this; }
        public Builder setState(String state) { this.state = state; return this; }
        public Builder setZipCode(String zipCode) { this.zipCode = zipCode; return this; }
        public AddressDTO build() { return new AddressDTO(this); }
    }

    // Getters
    public Long getId() { return id; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
}
