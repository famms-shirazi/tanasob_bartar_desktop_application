package com.tanasobbartar.individual.client.ledger;

import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface ClientLedgerService {

    ClientLedgerDto findById(Long id);

    ClientLedgerDto saveClientPaymentDto(NewClientLedgerDto clientLedgerDto);

    int calculateEntry(long clientId, int amount);

    void generateExcelReport(NewClientLedgerReportDto clientLedgerReportDto) throws JRException, SQLException;

}
