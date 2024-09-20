package com.bharat.sms.accounts.repository;

/*
 * @author Bharat V. <bindian0509@gmail.com>
 * @created Saturday, 21 September 2024
 */

import com.bharat.sms.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
}