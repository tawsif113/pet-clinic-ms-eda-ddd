package com.edapoc.appointmentquery.application.queryhandler;

import com.edapoc.appointmentquery.application.dto.AppointmentDetailsDto;
import com.edapoc.appointmentquery.application.mapper.AppointmentMapper;
import com.edapoc.appointmentquery.application.query.GetPetsAppointmentQuery;
import com.edapoc.appointmentquery.domain.entity.Appointment;
import com.edapoc.appointmentquery.domain.repository.AppointmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPetsAppointmentQueryHandler {

  private final AppointmentRepository appointmentRepository;
  private final AppointmentMapper appointmentMapper;

  @Transactional(readOnly = true)
  public List<AppointmentDetailsDto> handle(GetPetsAppointmentQuery query) {
    List<Appointment> appointments = appointmentRepository.findByPetIdIn(query.petIds());
    return appointmentMapper.toDtoList(appointments);
  }
}
