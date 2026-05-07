package com.tanasobbartar.individual.client.attendance;

import com.tanasobbartar.enrollment.Enrollment;
import com.tanasobbartar.enrollment.EnrollmentDto;
import com.tanasobbartar.enrollment.EnrollmentRepository;
import com.tanasobbartar.enrollment.EnrollmentService;
import com.tanasobbartar.individual.client.Client;
import com.tanasobbartar.individual.client.ClientRepository;
import com.tanasobbartar.individual.client.ClientService;
import com.tanasobbartar.individual.client.session.SessionStatus;
import com.tanasobbartar.individual.client.session.SessionStatusDto;
import com.tanasobbartar.individual.client.session.SessionStatusRepository;
import com.tanasobbartar.individual.client.session.SessionStatusService;
import com.tanasobbartar.individual.coach.Coach;
import com.tanasobbartar.individual.coach.CoachRepository;
import com.tanasobbartar.individual.coach.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientAttendanceServiceImpl implements ClientAttendanceService {

    private final ClientAttendanceRepository clientAttendanceRepository;
    private final ClientService clientService;
    private final CoachService coachService;
    private final EnrollmentService enrollmentService;
    private final SessionStatusService sessionStatusService;
    private final ClientRepository clientRepository;
    private final CoachRepository coachRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final SessionStatusRepository sessionStatusRepository;

    @Override
    public ClientAttendanceDto findById(Long id) {
        return clientAttendanceRepository.findById(id).map(this::toDto).orElseThrow();
    }

    @Override
    public ClientAttendanceDto saveClientAttendance(NewClientAttendanceDto clientAttendanceDto) throws Exception {
        checkPossibility(clientAttendanceDto);
        Client client = clientRepository.findById(clientAttendanceDto.getClientId()).orElseThrow();
        Coach coach = coachRepository.findById(clientAttendanceDto.getCoachId()).orElseThrow();
        Enrollment enrollment = enrollmentRepository.findById(clientAttendanceDto.getEnrollmentId()).orElseThrow();
        ClientAttendance clientAttendance = new ClientAttendance();
        clientAttendance.setCoach(coach);
        clientAttendance.setClient(client);
        clientAttendance.setEnrollment(enrollment);
        clientAttendance.setDate(clientAttendanceDto.getDate());
        clientAttendance.setCheckInTime(LocalTime.now());
        clientAttendance.setStatus(clientAttendanceDto.getStatus());
        remainingSessionCounter(enrollment.getId(), client.getId(), coach.getId());
        ClientAttendance savedClientAttendance = clientAttendanceRepository.save(clientAttendance);
        return toDto(savedClientAttendance);
    }

    private void remainingSessionCounter(Long enrollmentId, Long clientId, Long coachId) {
        SessionStatus sessionStatusByActiveEnrollment = sessionStatusRepository.findSessionStatusByActiveEnrollment(enrollmentId, clientId, coachId);
        Integer remainingSessions = sessionStatusByActiveEnrollment.getSessionsCount();
        sessionStatusByActiveEnrollment.setSessionsCount(remainingSessions + 1);
        sessionStatusRepository.save(sessionStatusByActiveEnrollment);
    }

    @Override
    public List<ClientAttendanceDto> findAllByClientAndCoach(Long clientId, Long coachId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Coach coach = coachRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Coach not found"));
        return clientAttendanceRepository.findAllByClientAndCoach(client, coach).stream().map(this::toDto).toList();
    }

    @Override
    public List<ClientAttendanceDto> findClientAttendanceStatus(Long clientId, Long coachId, Long enrollmentId,
                                                                LocalDate startDate, LocalDate endDate) {
        return clientAttendanceRepository.findClientAttendanceStatus(clientId, coachId, enrollmentId, startDate, endDate)
                .stream().map(this::toDto).toList();
    }

    private void checkPossibility(NewClientAttendanceDto clientAttendanceDto) throws Exception {
        Long clientId = clientAttendanceDto.getClientId();
        Long coachId = clientAttendanceDto.getCoachId();
        EnrollmentDto enrollmentDto = enrollmentService.findActiveEnrollmentByClientId(clientId, coachId);
        checkCreditStatus(enrollmentDto.getId());
    }

    private void checkCreditStatus(Long enrollmentId) throws Exception {
        SessionStatusDto sessionStatus = sessionStatusService.findSessionStatusByCreditableClient(enrollmentId);
        EnrollmentDto enrollment = enrollmentService.findById(enrollmentId);
        if (sessionStatus.getSessionsCount() >= enrollment.getSessionCount())
            throw new RuntimeException("number.of.sessions.has.expired");
    }

    private ClientAttendanceDto toDto(ClientAttendance clientAttendance) {
        return ClientAttendanceDto.builder().id(clientAttendance.getId()).date(clientAttendance.getDate()).status(clientAttendance.getStatus().toString()).clientFirstName(clientAttendance.getClient().getFirstName()).clientLastName(clientAttendance.getClient().getLastName()).coachFirstName(clientAttendance.getCoach().getFirstName()).coachLastName(clientAttendance.getCoach().getLastName()).build();
    }

    public ClientAttendanceDto recordCheckOutTime(Long clientAttendanceId) {
        ClientAttendance clientAttendance = clientAttendanceRepository.findById(clientAttendanceId).orElseThrow();
        clientAttendance.setCheckOutTime(LocalTime.now());
        clientAttendanceRepository.save(clientAttendance);
        return toDto(clientAttendance);
    }

}
