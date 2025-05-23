package com.springdatajpa.springdatajpademo.controllers;


import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.springdatajpa.springdatajpademo.domain.User;
import com.springdatajpa.springdatajpademo.dto.ReportDTO;
import com.springdatajpa.springdatajpademo.dto.StatisticsReportDTO;
import com.springdatajpa.springdatajpademo.dto.UserDTO;
import com.springdatajpa.springdatajpademo.services.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private  UserService userService;

    /*@Autowired
    private EmailService emailService;*/

    /*@Autowired
    private TestEmailService testEmailService;*/

    // @Autowired
    // private SESExample sesExample;
    // private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    private  SESNotificationService sesNotificationService;

    /*@Autowired
    public UserController(UserService userService, SESNotificationService sesNotificationService) {
        this.userService = userService;

        this.sesNotificationService = sesNotificationService;

    }*/

    /*@PostMapping("/{userId}/assign-reports")
    public ResponseEntity<?> assignReports(@RequestBody List<ReportDTO> reportDTO, @PathVariable Long userId) {
        User updatedUser = userService.assignReports(userId, reportDTO);
        System.out.println("updatedUser.getEmail()--  "+ updatedUser.getEmail());
        return ResponseEntity.ok().body(updatedUser.getReports());
    }*/

    @PostConstruct
    public void init() {
        // System.out.println("TestB Controller: " + amazonSimpleEmailService);
    }

    @GetMapping("/test")
    public String getValue() {
        return "Testing ......";
    }

    @PostMapping("/{userId}/assign-reports")
    public ResponseEntity<List<StatisticsReportDTO>> assignReports(
            @RequestBody List<ReportDTO> reportDTO,
            @PathVariable Long userId) {

        User updatedUser = userService.assignReports(userId, reportDTO);

        List<StatisticsReportDTO> reportDTOs = updatedUser.getReports().stream().map(report -> new StatisticsReportDTO(
                report.getId(),
                report.getTitle(),
                report.getDescription(),
                report.getImage(),
                report.getSlug(),
                report.getType(),
                report.getReportDetailsDescription(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        )).toList();

        return ResponseEntity.ok().body(reportDTOs);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO user) {
        // sesExample.sendEmail("djzevenbergen@gmail.com", "nitun.2@gmail.com","Test Email from Spring Boot via SES","Hello, this is a test email!");
        init();
        sesNotificationService.sendNewAccountNotification("djzevenbergen@gmail.com", "nitun25@outlook.com", user);

        return "User registered and email sent";
    }
}
