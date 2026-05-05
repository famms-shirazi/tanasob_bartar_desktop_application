package com.tanasobbartar.individual.client.session;

import com.tanasobbartar.constants.AuditableEntity;
import com.tanasobbartar.enrollment.Enrollment;
import com.tanasobbartar.individual.client.Client;
import com.tanasobbartar.individual.coach.Coach;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
