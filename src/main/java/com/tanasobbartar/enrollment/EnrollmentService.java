package com.tanasobbartar.enrollment;

public interface EnrollmentService {

    EnrollmentDto findById(long id);

    EnrollmentDto addEnrollment(NewEnrollmentDto enrollmentDto);

    EnrollmentDto findActiveEnrollmentByClientId(Long clientId, Long coachId);

}
