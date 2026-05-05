package ir.fathi.individual.client.attendance;

import ir.fathi.constants.AuditableEntity;
import ir.fathi.enrollment.Enrollment;
import ir.fathi.individual.client.Client;
import ir.fathi.individual.coach.Coach;
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
