package com.tanasobbartar.individual.coach;

import ir.fathi.individual.GenderType;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@Builder
@Jacksonized
public class NewCoachDto {

    String firstName;
    String lastName;
    String nationalCode;
    String username;
    GenderType genderType;
    LocalDate birthdate;

}
