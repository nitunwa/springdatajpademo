package com.springdatajpa.springdatajpademo.services;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Service
public class SESExample {

    //private final AmazonSimpleEmailService amazonSimpleEmailService;

    // @Autowired
    /*public SESExample(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }*/

    public void sendEmail(String from, String to, String subject, String body) {
        try {
            // Create the SendEmailRequest
            SendEmailRequest request = new SendEmailRequest()
                    .withSource(from)
                    .withDestination(new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withSubject(new Content().withData(subject))
                            .withBody(new Body().withHtml(new Content().withData(body))));

            // Send the email using SES
            // SendEmailResult result = amazonSimpleEmailService.sendEmail(request);

            // Log the result
            // System.out.println("Email sent successfully. Message ID: " + result.getMessageId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
