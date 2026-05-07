package com.tanasobbartar.individual.client.ledger;

public interface ClientLedgerService {

    ClientLedgerDto findById(Long id);

    ClientLedgerDto saveClientPaymentDto(NewClientLedgerDto clientLedgerDto);

    int calculateEntry(long clientId, int amount);

}
