package com.edapoc.appointmentcommand.domain.repository;

import com.edapoc.appointmentcommand.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
