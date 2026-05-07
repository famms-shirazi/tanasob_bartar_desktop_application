package com.tanasobbartar.individual.client.session;


import com.tanasobbartar.enrollment.Enrollment;

public interface SessionStatusService {

    SessionStatusDto save(NewSessionStatusDto sessionStatusDto);

    SessionStatus findSessionStatusById(Long id);

    SessionStatusDto findSessionStatusByCreditableClient(Long sessionId);

    SessionStatus findSessionStatusByEnrollment(Enrollment enrollment);

}
