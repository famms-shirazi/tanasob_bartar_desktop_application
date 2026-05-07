package com.tanasobbartar.individual.client.ledger;

import com.tanasobbartar.enrollment.Enrollment;
import com.tanasobbartar.enrollment.EnrollmentRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientLedgerServiceImpl implements ClientLedgerService {

    private final ClientLedgerRepository clientLedgerRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public ClientLedgerDto findById(Long id) {
        return clientLedgerRepository.findById(id).map(this::toDto).orElseThrow();
    }

    @Override
    public ClientLedgerDto saveClientPaymentDto(NewClientLedgerDto clientLedgerDto) {
        Enrollment enrollment = findEnrollmentById(clientLedgerDto.getEnrollmentId());
        Long clientId = enrollment.getClient().getId();
        int entry = calculateEntry(clientId, clientLedgerDto.getAmount());
        ClientLedger clientLedger = clientLedgerRepository.save(toEntity(clientLedgerDto, enrollment, entry));
        return toDto(clientLedger);
    }

    private int calculatePercent(int value, int percent) {
        return (value * percent) / 100;
    }

    public int calculateEntry(long clientId, int amount) {
        Optional<ClientLedger> clientLedger = clientLedgerRepository.findEntryByClient(clientId);
        if (clientLedger.isPresent()) {
            Integer entry = clientLedger.get().getBalance();
            return entry + amount;
        }
        return amount;
    }

    private Enrollment findEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElseThrow();
    }

    private ClientLedger toEntity(NewClientLedgerDto clientLedgerDto, Enrollment enrollment, Integer entry) {
        ClientLedger clientLedger = new ClientLedger();
        clientLedger.setDepositDate(clientLedgerDto.getDepositDate());
        clientLedger.setAmount(clientLedgerDto.getAmount());
        clientLedger.setPaymentMethod(clientLedgerDto.getPaymentMethod());
        clientLedger.setDescription(clientLedgerDto.getDescription());
        clientLedger.setPaymentMethod(clientLedgerDto.getPaymentMethod());
        clientLedger.setEnrollment(enrollment);
        clientLedger.setBalance(entry);
        return clientLedger;
    }

    private ClientLedgerDto toDto(ClientLedger clientLedger) {
        Enrollment enrollment = findEnrollmentById(clientLedger.getEnrollment().getId());
        Integer registrationFee = enrollment.getRegistrationFee();
        int coachCommission = calculatePercent(registrationFee, enrollment.getCoachPercentage());
        int centerShare = calculatePercent(registrationFee, enrollment.getCenterPercentage());
        return ClientLedgerDto.builder()
                .id(clientLedger.getId())
                .depositDate(clientLedger.getDepositDate())
                .amount(clientLedger.getAmount())
                .paymentMethod(clientLedger.getPaymentMethod())
                .description(clientLedger.getDescription())
                .coachPercentage(enrollment.getCoachPercentage())
                .centerPercentage(enrollment.getCenterPercentage())
                .coachCommission(coachCommission)
                .centerShare(centerShare)
                .build();

    }

}
