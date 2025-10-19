package za.co.cput.dto;
/**
 * Hlumelo Madlingozi
 * 222648120
 * 5 August 2025
 */
public class TwoFactorSetupResponse {
    private String secretKey;
    private String qrCodeUrl;
    private String qrCodeImage;
    private String message;

    public TwoFactorSetupResponse() {}

    public TwoFactorSetupResponse(String secretKey, String qrCodeUrl, String qrCodeImage) {
        this.secretKey = secretKey;
        this.qrCodeUrl = qrCodeUrl;
        this.qrCodeImage = qrCodeImage;
        this.message = "2FA setup initiated. Scan QR code with authenticator app.";
    }

    public TwoFactorSetupResponse(String message) {
        this.message = message;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(String qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}