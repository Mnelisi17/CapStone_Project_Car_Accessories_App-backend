package za.co.cput.dto;
//Kholiwe Mafenuka
//221686584
public class PasswordResetRequest {
    private String email;

    public PasswordResetRequest() {}

    public PasswordResetRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PasswordResetRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
