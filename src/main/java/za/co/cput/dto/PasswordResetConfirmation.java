package za.co.cput.dto;

public class PasswordResetConfirmation {
    private String token;
    private String newPassword;
    private String confirmPassword;

    public PasswordResetConfirmation() {}

    public PasswordResetConfirmation(String token, String newPassword, String confirmPassword) {
        this.token = token;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "PasswordResetConfirmation{" +
                "token='" + token + '\'' +
                ", newPassword='******'" +
                ", confirmPassword='******'" +
                '}';
    }
}

