package com.tanasobbartar.individual.coach;

import com.tanasobbartar.individual.GenderType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CoachDto {

    Long id;
    String firstName;
    String lastName;
    String nationalCode;
    String username;
    GenderType genderType;
    LocalDate birthdate;

}
