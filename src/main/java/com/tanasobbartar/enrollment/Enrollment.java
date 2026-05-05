package ir.fathi.enrollment;

import ir.fathi.constants.AuditableEntity;
import ir.fathi.individual.client.Client;
import ir.fathi.individual.coach.Coach;
import jakarta.persistence.*;
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
