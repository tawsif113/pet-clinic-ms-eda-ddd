package com.edapoc.appointmentquery.application.controller;

import com.edapoc.appointmentquery.application.dto.AppointmentDetailsDto;
import com.edapoc.appointmentquery.application.query.GetOwnerAppointmentsQuery;
import com.edapoc.appointmentquery.application.query.GetPetsAppointmentQuery;
import com.edapoc.appointmentquery.application.queryhandler.GetOwnerAppointmentsQueryHandler;
import com.edapoc.appointmentquery.application.queryhandler.GetPetsAppointmentQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentQueryController {

  private final GetPetsAppointmentQueryHandler getPetsAppointmentQueryHandler;
  private final GetOwnerAppointmentsQueryHandler getOwnerAppointmentsQueryHandler;

  @GetMapping("/pet")
  public ResponseEntity<List<AppointmentDetailsDto>> getAppointmentsByPetIds(@RequestParam List<Long> petIds) {
    GetPetsAppointmentQuery query = new GetPetsAppointmentQuery(petIds);
    List<AppointmentDetailsDto> appointments = getPetsAppointmentQueryHandler.handle(query);
    return ResponseEntity.ok(appointments);
  }

  @GetMapping("/owner/{ownerId}")
  public ResponseEntity<List<AppointmentDetailsDto>> getAppointmentsByOwnerId(@PathVariable Long ownerId) {
    GetOwnerAppointmentsQuery query = new GetOwnerAppointmentsQuery(ownerId);
    List<AppointmentDetailsDto> appointments = getOwnerAppointmentsQueryHandler.handle(query);
    return ResponseEntity.ok(appointments);
  }
}

