package com.tanasobbartar.individual.client;

import com.tanasobbartar.individual.GenderType;
import lombok.Builder;
import lombok.Value;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;

@Value
@Builder
@JsonDeserialize(builder = NewClientDto.NewClientDtoBuilder.class)
public class NewClientDto {

    String firstName;
    String lastName;
    String nationalCode;
    String username;
    GenderType genderType;
    LocalDate birthdate;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NewClientDtoBuilder {
    }

}
