package com.parlonsdev.repository;

import com.parlonsdev.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailContaining(String email);
    Optional<Customer> findByPhoneContaining(String phone);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
