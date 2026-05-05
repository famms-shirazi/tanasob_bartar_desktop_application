package com.tanasobbartar.enrollment;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class NewEnrollmentDto {

    Integer sessionCount;
    Long clientId;
    Long coachId;
    Integer registrationFee;
    Integer coachPercentage;
    Integer centerPercentage;

}
