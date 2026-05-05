package ir.fathi.individual.client.session;

import ir.fathi.constants.AuditableEntity;
import ir.fathi.enrollment.Enrollment;
import ir.fathi.individual.client.Client;
import ir.fathi.individual.coach.Coach;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sessionـstatus")
public class SessionStatus extends AuditableEntity {

    private LocalDateTime date;
    private Integer sessionsCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Coach coach;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Enrollment enrollment;

}
