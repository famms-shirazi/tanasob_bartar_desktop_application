package com.tanasobbartar.individual.client.session;

import com.tanasobbartar.enrollment.Enrollment;
import com.tanasobbartar.enrollment.EnrollmentRepository;
import com.tanasobbartar.individual.client.Client;
import com.tanasobbartar.individual.client.ClientRepository;
import com.tanasobbartar.individual.coach.Coach;
import com.tanasobbartar.individual.coach.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionStatusServiceImpl implements SessionStatusService {

    private final SessionStatusRepository sessionStatusRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CoachRepository coachRepository;
    private final ClientRepository clientRepository;

    @Override
    public SessionStatusDto save(NewSessionStatusDto sessionStatusDto) {
        Enrollment enrollment = findEnrollmentById(sessionStatusDto.getEnrollmentId());
        Coach coach = findCoachById(sessionStatusDto.getCoachId());
        Client client = findClientById(sessionStatusDto.getClientId());
        SessionStatus sessionStatus = toEntity(sessionStatusDto, enrollment, coach, client);
        SessionStatus savedSessionStatus = sessionStatusRepository.save(sessionStatus);
        return toDto(savedSessionStatus);
    }

    private Enrollment findEnrollmentById(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).orElseThrow();
    }

    private Coach findCoachById(Long enrollmentId) {
        return coachRepository.findById(enrollmentId).orElseThrow();
    }

    private Client findClientById(Long enrollmentId) {
        return clientRepository.findById(enrollmentId).orElseThrow();
    }

    @Override
    public SessionStatus findSessionStatusById(Long id) {
        return sessionStatusRepository.findById(id).orElseThrow();
    }

    @Override
    public SessionStatusDto findSessionStatusByCreditableClient(Long enrollmentId) {
        SessionStatus sessionStatusByCreditableClient = sessionStatusRepository.findSessionStatusByCreditableClient(enrollmentId);
        return toDto(sessionStatusByCreditableClient);
    }

    @Override
    public SessionStatus findSessionStatusByEnrollment(Enrollment enrollment) {
        return sessionStatusRepository.findSessionStatusByEnrollment(enrollment);
    }

    private SessionStatus toEntity(NewSessionStatusDto sessionStatusDto, Enrollment enrollment, Coach coach, Client client) {
        SessionStatus sessionStatus = new SessionStatus();
        sessionStatus.setDate(sessionStatusDto.getDate());
        sessionStatus.setClient(client);
        sessionStatus.setCoach(coach);
        sessionStatus.setSessionsCount(sessionStatusDto.getRemainingSessions());
        sessionStatus.setEnrollment(enrollment);
        return sessionStatus;
    }

    private SessionStatusDto toDto(SessionStatus sessionStatus) {
        return SessionStatusDto.builder()
                .id(sessionStatus.getId())
                .date(sessionStatus.getDate())
                .coachId(sessionStatus.getCoach().getId())
                .clientId(sessionStatus.getClient().getId())
                .sessionsCount(sessionStatus.getSessionsCount())
                .build();
    }

}
