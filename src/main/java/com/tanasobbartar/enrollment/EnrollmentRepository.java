package com.tanasobbartar.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("""
            SELECT e
            FROM Enrollment e
            JOIN FETCH e.coach coach
            JOIN FETCH e.client client
            WHERE coach.id = :coachId
            AND client.id = :clientId
            AND (e.disabledAt IS NULL OR e.disabledAt > CURRENT_TIMESTAMP)""")
    Optional<Enrollment> findActiveEnrollment(Long clientId, Long coachId);

}
