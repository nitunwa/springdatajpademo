package com.springdatajpa.springdatajpademo.repository;

import com.springdatajpa.springdatajpademo.domain.StatisticsReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<StatisticsReport, Long> {}
