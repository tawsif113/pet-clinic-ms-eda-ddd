package com.edapoc.appointmentquery.application.queryhandler;

import com.edapoc.appointmentquery.application.dto.AppointmentDetailsDto;
import com.edapoc.appointmentquery.application.query.GetPetsAppointmentQuery;
import com.edapoc.appointmentquery.domain.repository.AppointmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPetsAppointmentQueryHandler {
  private final AppointmentRepository appointmentRepository;

  @Transactional(readOnly = true)
  public List<AppointmentDetailsDto> handle(GetPetsAppointmentQuery query) {
    return appointmentRepository.findByPetId(query.petId())
        .stream()
        .map(AppointmentDetailsDto::fromEntity)
        .collect(Collectors.toList());
  }
}
