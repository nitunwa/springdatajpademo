package com.springdatajpa.springdatajpademo.services;

import com.springdatajpa.springdatajpademo.domain.User;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;



public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    // private MailConfig mailConfig;  // <-- Inject your MailConfig class

    public void sendNewAccountEmail(String to, User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        String searchUrl = "http://localhost:8000/admin/users?search=" + encodeURL(fullName);

        String emailContent = generateEmailContent(user, searchUrl);

       /* try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("New CX Account");
            helper.setText(emailContent, true); // HTML content

            // Set FROM address and name
            helper.setFrom(mailConfig.getFromAddress(), mailConfig.getFromName());

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private String generateEmailContent(User user, String searchUrl) {
        String phoneNumber = formatPhoneNumber(user.getPhone());

        return "<html><body>"
                + "<h1>New CX Account</h1>"
                + "<p>The following user has registered for a reDataExport account. Please "
                + (searchUrl != null ? "<a href=\"" + searchUrl + "\">login to activate</a>"
                + " this account, or use the <a href=\"" + searchUrl + "\">link</a> if you are already logged in."
                : "<a href=\"/admin/users\">login to activate</a> this account, or use the link if you are already logged in.")
                + "</p>"
                + "<ul>"
                + "<li><strong>Name:</strong> " + user.getFirstName() + " " + user.getLastName() + "</li>"
                + "<li><strong>Organization:</strong> " + (user.getOrganization() != null ? user.getOrganization() : "N/A") + "</li>"
                + "<li><strong>Phone Number:</strong> " + phoneNumber + "</li>"
                + "<li><strong>Email:</strong> " + user.getEmail() + "</li>"
                + "</ul>"
                + "<p>Thank you for keeping track of new user registrations.</p>"
                + "</body></html>";
    }

    private String encodeURL(String value) {
        try {
            return java.net.URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return value;  // Return value as is if encoding fails
        }
    }

    private String formatPhoneNumber(String rawPhone) {
        if (rawPhone != null) {
            String cleanPhone = rawPhone.replaceAll("\\D", "");
            if (cleanPhone.length() == 10) {
                return String.format("(%s) %s-%s", cleanPhone.substring(0, 3), cleanPhone.substring(3, 6), cleanPhone.substring(6));
            }
        }
        return rawPhone != null ? rawPhone : "N/A";
    }
}
