package com.tanasobbartar.individual;

import com.tanasobbartar.constants.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "individual")
public class Individual extends AuditableEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "national_code", nullable = false)
    private String nationalCode;
    @Column(name = "username", nullable = false)
    private String username;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private GenderType genderType;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

}
