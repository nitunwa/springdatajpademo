package com.springdatajpa.springdatajpademo.repository;

import com.springdatajpa.springdatajpademo.domain.Mls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MlsRepository  extends JpaRepository<Mls, Long> {
}
