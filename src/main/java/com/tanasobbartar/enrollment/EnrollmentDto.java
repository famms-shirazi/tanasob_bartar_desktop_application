package com.tanasobbartar.enrollment;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
public class EnrollmentDto {

    Long id;
    Integer sessionCount;
    LocalDate startDate;
    Integer registrationFee;
    Integer coachPercentage;
    Integer centerPercentage;
    LocalDateTime createdAt;
    LocalDateTime disabledAt;
    Long clientId;
    Long coachId;

}
