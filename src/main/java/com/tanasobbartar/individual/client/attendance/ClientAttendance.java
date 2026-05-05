package com.tanasobbartar.individual.client.attendance;


import com.tanasobbartar.constants.AuditableEntity;
import com.tanasobbartar.enrollment.Enrollment;
import com.tanasobbartar.individual.client.Client;
import com.tanasobbartar.individual.coach.Coach;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "clientـattendance")
public class ClientAttendance extends AuditableEntity {

    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Coach coach;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Enrollment enrollment;

}
