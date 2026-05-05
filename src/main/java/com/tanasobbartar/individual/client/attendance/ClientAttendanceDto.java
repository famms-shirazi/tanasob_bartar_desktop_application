package com.tanasobbartar.individual.client.attendance;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class ClientAttendanceDto {

    Long id;
    String status;
    String clientFirstName;
    String clientLastName;
    String coachFirstName;
    String coachLastName;
    LocalDate date;
    LocalTime checkInTime;
    LocalTime checkOutTime;

}
