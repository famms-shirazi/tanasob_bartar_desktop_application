package com.tanasobbartar.individual.client.ledger;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ClientLedgerDto {

    Long id;
    LocalDateTime depositDate;
    Integer amount;
    PaymentMethod paymentMethod;
    String description;
    Integer registrationFee;
    Integer coachPercentage;
    Integer centerPercentage;
    Integer coachCommission;
    Integer centerShare;
    Integer entry;

}
