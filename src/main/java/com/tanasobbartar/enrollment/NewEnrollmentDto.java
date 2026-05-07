package com.tanasobbartar.enrollment;

import lombok.Builder;
import lombok.Value;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

@Value
@Builder
@JsonDeserialize(builder = NewEnrollmentDto.NewEnrollmentDtoBuilder.class)
public class NewEnrollmentDto {

    Integer sessionCount;
    Long clientId;
    Long coachId;
    Integer registrationFee;
    Integer coachPercentage;
    Integer centerPercentage;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NewEnrollmentDtoBuilder {
    }

}
