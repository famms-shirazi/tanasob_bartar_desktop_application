package com.tanasobbartar.individual.client.attendance;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
@Jacksonized
@JsonDeserialize(builder = NewClientAttendanceDto.NewClientAttendanceBuilderDto.class)
public class NewClientAttendanceDto {

    AttendanceStatus status;
    Long clientId;
    Long coachId;
    Long enrollmentId;
    LocalDate date;
    LocalTime checkOutTime;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NewClientAttendanceBuilderDto{
    }

}
