package com.springdatajpa.springdatajpademo.repository;

import com.springdatajpa.springdatajpademo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
