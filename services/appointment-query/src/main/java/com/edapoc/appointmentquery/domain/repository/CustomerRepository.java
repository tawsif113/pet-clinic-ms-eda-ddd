package com.edapoc.appointmentquery.domain.repository;

import com.edapoc.appointmentquery.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

