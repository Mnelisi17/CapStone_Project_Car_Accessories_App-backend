package za.co.cput.service;
//Kholiwe Faith Mafenuka
//221686584
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.frontend.url:http://localhost:3001}")
    private String frontendUrl;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public boolean sendPasswordResetEmail(String toEmail, String userName, String resetToken) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Debug check for fromEmail value
            System.out.println("DEBUG From Email: '" + fromEmail + "'");

            helper.setFrom(fromEmail.trim()); // Ensure no stray characters
            helper.setTo(toEmail);
            helper.setSubject("Password Reset Request - Car Accessories App");

            String resetUrl = frontendUrl + "/reset-password?token=" + resetToken + "&email=" + toEmail;

            // Escape ampersands for HTML safety
            String safeResetUrl = resetUrl.replace("&", "&amp;");

            String htmlContent = createPasswordResetEmailTemplate(userName, safeResetUrl);

            helper.setText(htmlContent, true);

            mailSender.send(message);
            System.out.println("Password reset email sent successfully to: " + toEmail);
            return true;

        } catch (MessagingException e) {
            System.err.println("Failed to send password reset email to " + toEmail + ": " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected error sending email to " + toEmail + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Builds HTML email template for password reset
     */
    private String createPasswordResetEmailTemplate(String userName, String resetUrl) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Password Reset</title>" +
                "    <style>" +
                "        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; max-width: 600px; margin: 0 auto; padding: 20px; }" +
                "        .header { background-color: #007bff; color: white; padding: 20px; text-align: center; border-radius: 5px 5px 0 0; }" +
                "        .content { background-color: #f8f9fa; padding: 30px; border-radius: 0 0 5px 5px; }" +
                "        .button { display: inline-block; background-color: #007bff; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; margin: 20px 0; }" +
                "        .button:hover { background-color: #0056b3; }" +
                "        .warning { background-color: #fff3cd; border: 1px solid #ffeaa7; padding: 15px; border-radius: 5px; margin: 20px 0; }" +
                "        .footer { text-align: center; margin-top: 30px; font-size: 12px; color: #666; }" +
                "        .token-info { background-color: #e9ecef; padding: 15px; border-radius: 5px; margin: 15px 0; font-family: monospace; word-break: break-all; }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"header\">" +
                "        <h1>🚗 Car Accessories App</h1>" +
                "        <h2>Password Reset Request</h2>" +
                "    </div>" +
                "    <div class=\"content\">" +
                "        <p>Hello " + userName + ",</p>" +
                "        <p>We received a request to reset your password. If you made this request, click the button below:</p>" +
                "        <div style=\"text-align: center;\">" +
                "            <a href=\"" + resetUrl + "\" class=\"button\">Reset My Password</a>" +
                "        </div>" +
                "        <div class=\"warning\">" +
                "            <strong>⚠️ Important:</strong>" +
                "            <ul>" +
                "                <li>This link will expire in <strong>15 minutes</strong>.</li>" +
                "                <li>You can only use it once.</li>" +
                "                <li>If you didn’t request this, ignore this email.</li>" +
                "            </ul>" +
                "        </div>" +
                "        <p>If the button doesn’t work, paste this link into your browser:</p>" +
                "        <div class=\"token-info\">" +
                "            <a href=\"" + resetUrl + "\">" + resetUrl + "</a>" +
                "        </div>" +
                "        <p>Thanks,<br>The Car Accessories App Team</p>" +
                "    </div>" +
                "    <div class=\"footer\">" +
                "        <p>This is an automated message. Please do not reply.</p>" +
                "        <p>© 2024 Car Accessories App</p>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
}

