package com.bracits.customerquery;

import com.bracits.sharedevent.event.appointment.AppointmentCreatedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerQueryApplication {

  AppointmentCreatedEvent appointmentCreatedEvent;

    public static void main(String[] args) {
        SpringApplication.run(CustomerQueryApplication.class, args);
    }

}
