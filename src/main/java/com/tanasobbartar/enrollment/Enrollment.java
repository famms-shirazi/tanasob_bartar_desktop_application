package com.tanasobbartar.enrollment;

import com.tanasobbartar.constants.AuditableEntity;
import com.tanasobbartar.individual.client.Client;
import com.tanasobbartar.individual.coach.Coach;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enrollment")
public class Enrollment extends AuditableEntity {

    private Integer sessionCount;
    private Integer registrationFee;
    private Integer coachPercentage;
    private Integer centerPercentage;
    private LocalDateTime disabledAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Coach coach;

}
