package com.edapoc.appointmentcommand.domain.aggregate;

import com.edapoc.appointmentcommand.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
