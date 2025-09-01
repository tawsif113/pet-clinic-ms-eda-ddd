package com.edapoc.appointmentquery.application.queryhandler;

import com.edapoc.appointmentquery.application.dto.AppointmentDetailsDto;
import com.edapoc.appointmentquery.application.mapper.AppointmentMapper;
import com.edapoc.appointmentquery.application.query.GetOwnerAppointmentsQuery;
import com.edapoc.appointmentquery.domain.entity.Appointment;
import com.edapoc.appointmentquery.domain.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOwnerAppointmentsQueryHandler {

  private final AppointmentRepository appointmentRepository;
  private final AppointmentMapper appointmentMapper;

  @Transactional(readOnly = true)
  public List<AppointmentDetailsDto> handle(GetOwnerAppointmentsQuery query) {
    List<Appointment> appointments = appointmentRepository.findByOwnerId(query.ownerId());
    return appointmentMapper.toDtoList(appointments);
  }
}
