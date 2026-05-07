package com.tanasobbartar.enrollment;

import com.tanasobbartar.individual.client.Client;
import com.tanasobbartar.individual.client.ClientRepository;
import com.tanasobbartar.individual.client.ledger.ClientLedger;
import com.tanasobbartar.individual.client.ledger.ClientLedgerRepository;
import com.tanasobbartar.individual.client.ledger.ClientLedgerService;
import com.tanasobbartar.individual.client.ledger.PaymentType;
import com.tanasobbartar.individual.client.session.NewSessionStatusDto;
import com.tanasobbartar.individual.client.session.SessionStatusService;
import com.tanasobbartar.individual.coach.Coach;
import com.tanasobbartar.individual.coach.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CoachRepository coachRepository;
    private final ClientRepository clientRepository;
    private final ClientLedgerRepository clientLedgerRepository;
    private final SessionStatusService sessionStatusService;
    private final ClientLedgerService clientLedgerService;

    @Override
    public EnrollmentDto findById(long id) {
        return enrollmentRepository.findById(id).map(this::toDto).orElse(null);
    }

    private int calculateEntry(long clientId, int amount) {
        return clientLedgerService.calculateEntry(clientId, amount);
    }

    private void saveClientLedger(Enrollment enrollment) {
        int entry = calculateEntry(enrollment.getClient().getId(), -enrollment.getRegistrationFee());
        ClientLedger clientLedger = new ClientLedger();
        clientLedger.setEnrollment(enrollment);
        clientLedger.setDepositDate(LocalDateTime.now());
        clientLedger.setAmount(0);
        clientLedger.setBalance(entry);
        clientLedger.setPaymentType(PaymentType.ENROLLMENT);
        clientLedgerRepository.save(clientLedger);
    }

    @Override
    public EnrollmentDto addEnrollment(NewEnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setSessionCount(enrollmentDto.getSessionCount());
        enrollment.setClient(findClientById(enrollmentDto.getClientId()));
        enrollment.setCoach(findCoachById(enrollmentDto.getCoachId()));
        enrollment.setCreatedAt(LocalDateTime.now());
        enrollment.setRegistrationFee(enrollmentDto.getRegistrationFee());
        enrollment.setCenterPercentage(enrollmentDto.getCenterPercentage());
        enrollment.setCoachPercentage(enrollmentDto.getCoachPercentage());
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        NewSessionStatusDto sessionStatusDto = generateSessionStatusDto(enrollmentDto, savedEnrollment.getId(), LocalDateTime.now(), 0);
        saveClientLedger(savedEnrollment);
        sessionStatusService.save(sessionStatusDto);
        return toDto(savedEnrollment);
    }

    private NewSessionStatusDto generateSessionStatusDto(NewEnrollmentDto enrollmentDto, Long enrollmentId, LocalDateTime date, Integer remainingSessions) {
        return NewSessionStatusDto.builder().clientId(enrollmentDto.getClientId()).coachId(enrollmentDto.getCoachId()).enrollmentId(enrollmentId).date(date).remainingSessions(remainingSessions).build();
    }

    @Override
    public EnrollmentDto findActiveEnrollmentByClientId(Long clientId, Long coachId) {
        Enrollment enrollment = enrollmentRepository.findActiveEnrollment(clientId, coachId).orElseThrow();
        return toDto(enrollment);
    }

    private Coach findCoachById(Long coachId) {
        return coachRepository.findById(coachId).orElseThrow();
    }

    private Client findClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow();
    }

    private EnrollmentDto toDto(Enrollment enrollment) {
        return EnrollmentDto.builder().id(enrollment.getId()).createdAt(enrollment.getCreatedAt()).disabledAt(enrollment.getDisabledAt()).clientId(enrollment.getClient().getId()).coachId(enrollment.getCoach().getId()).sessionCount(enrollment.getSessionCount()).registrationFee(enrollment.getRegistrationFee()).coachPercentage(enrollment.getCoachPercentage()).centerPercentage(enrollment.getCenterPercentage()).build();
    }

}
