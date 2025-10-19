package za.co.cput.dto;
/**
 * Hlumelo Madlingozi
 * 222648120
 * 5 August 2025
 */
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
