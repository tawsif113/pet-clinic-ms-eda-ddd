package com.edapoc.appointmentquery.application.query;

import java.util.List;

public record GetPetsAppointmentQuery(List<Long> petIds) {}