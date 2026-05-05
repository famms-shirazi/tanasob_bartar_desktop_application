package com.tanasobbartar.individual.client.attendance;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
@Jacksonized
public class NewClientAttendanceDto {

    AttendanceStatus status;
    Long clientId;
    Long coachId;
    Long enrollmentId;
    LocalDate date;
    LocalTime checkOutTime;

}
