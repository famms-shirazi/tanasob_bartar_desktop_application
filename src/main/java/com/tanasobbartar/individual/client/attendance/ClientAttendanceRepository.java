package ir.fathi.individual.client.attendance;

import ir.fathi.individual.client.Client;
import ir.fathi.individual.coach.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientAttendanceRepository extends JpaRepository<ClientAttendance, Long> {

    List<ClientAttendance> findAllByClientAndCoach(Client client, Coach coach);

    @Query("""
            FROM ClientAttendance ca
            JOIN FETCH ca.coach coach
            JOIN FETCH ca.client client
            JOIN FETCH ca.enrollment enrollment
            WHERE coach.id=:coachId
            AND enrollment.id=:enrollmentId
            AND client.id=:clientId
            AND ca.date BETWEEN :startDate AND :endDate""")
    List<ClientAttendance> findClientAttendanceStatus(Long clientId, Long coachId, Long enrollmentId,
                                                      LocalDate startDate, LocalDate endDate);

}
