package com.edapoc.appointmentcommand.domain.event;

public record CommandFeedbackEvent(
    String traceId,
    String commandType,
    String status,
    String message,
    Long appointmentId
) {}
