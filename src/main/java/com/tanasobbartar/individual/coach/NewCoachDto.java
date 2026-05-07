package com.tanasobbartar.individual.coach;

import com.tanasobbartar.individual.GenderType;
import lombok.Builder;
import lombok.Value;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;

@Value
@Builder
@JsonDeserialize(builder = NewCoachDto.NewCoachDtoBuilder.class)
public class NewCoachDto {

    String firstName;
    String lastName;
    String nationalCode;
    String username;
    GenderType genderType;
    LocalDate birthdate;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NewCoachDtoBuilder {
    }

}
