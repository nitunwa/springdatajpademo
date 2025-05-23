package com.springdatajpa.springdatajpademo.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.springdatajpa.springdatajpademo.domain.Mls;
import com.springdatajpa.springdatajpademo.domain.User;
import com.springdatajpa.springdatajpademo.dto.UserDTO;
import com.springdatajpa.springdatajpademo.repository.MlsRepository;
import com.springdatajpa.springdatajpademo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;


@Service
public class SESNotificationService {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private MlsRepository mlsRepository;
    private static final Logger logger = LoggerFactory.getLogger(SESNotificationService.class);

    @Autowired
    public SESNotificationService(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
        logger.info("TestB:::: " + amazonSimpleEmailService);
    }

    public void registerNewUser(UserDTO userDTO) {

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setStatus(User.UserStatus.valueOf(userDTO.getStatus().toUpperCase()));
        user.setOrganization(userDTO.getOrganization());
        user.setAdmin(userDTO.isAdmin());

        // Mls conversion
        Mls mls = mlsRepository.findById(Long.valueOf(userDTO.getMlsId()))
                .orElseThrow(() -> new RuntimeException("MLS not found with id: " + userDTO.getMlsId()));
        user.setMls(mls);
        // Save user to the database
        userRepository.save(user);
    }

    @PostConstruct
    public void init01() {
        System.out.println("TestB: " + amazonSimpleEmailService);
    }

    public void sendNewAccountNotification(String from, String to, UserDTO user) {
        System.out.println("user00-  "+user.getFirstName());
        try {

            /*AWSCredentials creds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
            AmazonSimpleEmailService sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(creds))
                    .withRegion(region)
                    .build();
            */

            // registerNewUser(user);

            // Generate the email body using user data
            String emailContent = generateEmailContent(user);

            // Create the SendEmailRequest
            SendEmailRequest request = new SendEmailRequest()
                    .withSource(from)
                    .withDestination(new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withSubject(new Content().withData("New CX Account"))
                            .withBody(new Body().withHtml(new Content().withData(emailContent))));

            // Send the email using SES
            // init01();
            SendEmailResult result = amazonSimpleEmailService.sendEmail(request);
            // SendEmailResult result = sesClient.sendEmail(request);

            // Log the result
            System.out.println("Email sent successfully. Message ID: " + result.getMessageId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateEmailContent(UserDTO user) {
        String phoneNumber = formatPhoneNumber(user.getFirstName());
        String fullName = user.getLastName();
        String email = user.getEmail();
        String searchUrl = "http://localhost:8000/admin/users?search=" + encodeURL(email);

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
