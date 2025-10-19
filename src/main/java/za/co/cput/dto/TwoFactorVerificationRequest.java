package za.co.cput.dto;

public class TwoFactorVerificationRequest {
    private String email;
    private int code;

    public String getEmail() {
        return email;
    }

    public int getCode() {
        return code;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
