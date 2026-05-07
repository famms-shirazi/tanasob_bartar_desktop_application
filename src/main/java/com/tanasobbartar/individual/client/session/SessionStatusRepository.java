package com.tanasobbartar.individual.client.session;

import com.tanasobbartar.enrollment.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionStatusRepository extends JpaRepository<SessionStatus, Long> {

    Optional<SessionStatus> findSessionStatusById(Long id);

    @Query("""
            SELECT s FROM SessionStatus s
            JOIN s.enrollment e
            WHERE e.id = :enrollmentId
            AND s.sessionsCount >= 0""")
    SessionStatus findSessionStatusByCreditableClient(Long enrollmentId);

    @Query("""
            SELECT s FROM SessionStatus s
            JOIN FETCH s.enrollment e
            WHERE e.id = :enrollmentId
            AND s.client.id =:clientId
            AND s.coach.id =:coachId
            AND (e.disabledAt IS NULL OR e.disabledAt > CURRENT_TIMESTAMP)""")
    SessionStatus findSessionStatusByActiveEnrollment(Long enrollmentId, Long clientId, Long coachId);

    SessionStatus findSessionStatusByEnrollment(Enrollment enrollment);

}
