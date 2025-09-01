package com.edapoc.appointmentquery.application.controller;

import com.edapoc.appointmentquery.application.dto.AppointmentDetailsDto;
import com.edapoc.appointmentquery.application.query.GetPetsAppointmentQuery;
import com.edapoc.appointmentquery.application.queryhandler.GetPetsAppointmentQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentQueryController {

  private final GetPetsAppointmentQueryHandler getPetsAppointmentQueryHandler;

  @GetMapping("/pet/{petId}")
  public ResponseEntity<List<AppointmentDetailsDto>> getAppointmentsByPetId(@PathVariable Long petId) {
    GetPetsAppointmentQuery query = new GetPetsAppointmentQuery(petId);
    List<AppointmentDetailsDto> appointments = getPetsAppointmentQueryHandler.handle(query);
    return ResponseEntity.ok(appointments);
  }
}

