package com.springdatajpa.springdatajpademo.services;

import com.springdatajpa.springdatajpademo.domain.Mls;
import com.springdatajpa.springdatajpademo.domain.StatisticsReport;
import com.springdatajpa.springdatajpademo.domain.User;
import com.springdatajpa.springdatajpademo.dto.ReportDTO;
import com.springdatajpa.springdatajpademo.repository.MlsRepository;
import com.springdatajpa.springdatajpademo.repository.ReportRepository;
import com.springdatajpa.springdatajpademo.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    /*@Autowired
    private MlsRepository mlsRepository;*/

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Transactional
    public User assignReports(Long userId, List<ReportDTO> reportDTOs) {
        logger.info("Request Data: {}");

        List<Long> reportIds = reportDTOs.stream()
                .map(ReportDTO::getValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));



        if (reportIds.isEmpty()) {
            user.getReports().clear();
            logger.info("All reports unassigned");
        } else {
            List<StatisticsReport> reports = reportRepository.findAllById(reportIds);
            System.out.println("reports ---  "+ reports.size());

            if (reports.size() != reportIds.size()) {
                throw new RuntimeException("One or more report IDs do not exist");
            }

            /*Optional<Mls> mls = mlsRepository.findById(1L);
            mls.ifPresent(user::setMls);
            reports.stream().forEach(obj-> obj.setMls(mls.get()));*/

            user.setReports(new HashSet<>(reports));
            user.setLastName("datta");

            logger.info("Reports assigned: {}");
        }
        User u = userRepository.save(user);

        System.out.println("user.getMls().getId() ==  "+ u.getMls().getName());
        System.out.println("test 11");
        return u;
        // return user;
        // ****(imp) https://chatgpt.com/share/67f326e5-6f54-800d-bfb5-e914ae385660
    }
}

