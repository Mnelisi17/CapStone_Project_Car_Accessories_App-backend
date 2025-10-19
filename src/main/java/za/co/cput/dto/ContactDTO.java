package za.co.cput.dto;
/*
222293985
Lennox Komane
group 3F
 */
public class ContactDTO {
    public ContactDTO() {}
    private Long id;
    private String email;
    private String phoneNumber;

    private ContactDTO(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
    }

    public static class Builder {
        private Long id;
        private String email;
        private String phoneNumber;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public ContactDTO build() { return new ContactDTO(this); }
    }

    // Getters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
}
