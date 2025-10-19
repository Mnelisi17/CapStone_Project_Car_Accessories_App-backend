package za.co.cput.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
// import com.warrenstrange.googleauth.GoogleAuthenticator;
// import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
// import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class TwoFactorService {

    // private final GoogleAuthenticator googleAuthenticator;

    // public TwoFactorService() {
    //     this.googleAuthenticator = new GoogleAuthenticator();
    // }

    // public String generateSecretKey() {
    //     GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
    //     return key.getKey();
    // }

    // public String generateQRCodeUrl(String email, String secretKey) {
    //     return GoogleAuthenticatorQRGenerator.getOtpAuthURL(
    //             "Car Accessories App",
    //             email,
    //             googleAuthenticator.createCredentials(secretKey)
    //     );
    // }

    public String generateQRCodeImage(String qrCodeUrl) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrCodeUrl, BarcodeFormat.QR_CODE, 200, 200);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        byte[] qrCodeBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(qrCodeBytes);
    }

    // public boolean verifyCode(String secretKey, int code) {
    //     return googleAuthenticator.authorize(secretKey, code);
    // }

    // public boolean isCodeValid(String secretKey, String code) {
    //     try {
    //         int codeInt = Integer.parseInt(code);
    //         return verifyCode(secretKey, codeInt);
    //     } catch (NumberFormatException e) {
    //         return false;
    //     }
    // }
}