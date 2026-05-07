package com.tanasobbartar.individual.client.ledger;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;

@RestController
@AllArgsConstructor
@RequestMapping("/client-ledgers")
public class ClientLedgerController {

    private final ClientLedgerService clientPaymentService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientLedgerDto> getClientPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(clientPaymentService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ClientLedgerDto> addClientAttendance(@RequestBody NewClientLedgerDto clientLedgerDto) {
        ClientLedgerDto savedClientLedgerDto = clientPaymentService.saveClientPaymentDto(clientLedgerDto);
        return ResponseEntity.created(URI.create("/client-payments/" + savedClientLedgerDto.getId())).body(savedClientLedgerDto);
    }

    @PostMapping("/report")
    public void generateExcelReport(@RequestBody NewClientLedgerReportDto clientLedgerReportDto) throws JRException, SQLException {
        clientPaymentService.generateExcelReport(clientLedgerReportDto);
    }

}
