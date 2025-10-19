
package za.co.cput.dto;
/*
222293985
Lennox Komane
group 3F
 */
public class ValidatedUserRegistrationRequest {
    private String name;
    private String password;
    private AddressDTO address;
    private ContactDTO contact;
    private String role = "USER";
    private String adminCode;

    public ValidatedUserRegistrationRequest() {}

    public ValidatedUserRegistrationRequest(String name, String password,
                                            AddressDTO address, ContactDTO contact, String role,
                                            String adminCode) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.role = role != null ? role : "USER";
        this.adminCode = adminCode;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public AddressDTO getAddress() { return address; }
    public void setAddress(AddressDTO address) { this.address = address; }

    public ContactDTO getContact() { return contact; }
    public void setContact(ContactDTO contact) { this.contact = contact; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAdminCode() { return adminCode; }
    public void setAdminCode(String adminCode) { this.adminCode = adminCode; }

    @Override
    public String toString() {
        return "ValidatedUserRegistrationRequest{" +
                "name='" + name + '\'' +
                ", password='******'" +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", role='" + role + '\'' +
                ", adminCode='" + adminCode + '\'' +
                '}';
    }
}
