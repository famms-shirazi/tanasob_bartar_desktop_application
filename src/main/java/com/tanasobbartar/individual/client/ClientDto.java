package com.tanasobbartar.individual.client;

import ir.fathi.individual.GenderType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ClientDto {

    Long id;
    String firstName;
    String lastName;
    String nationalCode;
    String username;
    GenderType genderType;
    LocalDate birthdate;

}
