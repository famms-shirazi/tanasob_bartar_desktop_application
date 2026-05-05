package com.tanasobbartar.individual.client.ledger;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class NewClientLedgerDto {

    LocalDateTime depositDate;
    Integer amount;
    PaymentMethod paymentMethod;
    PaymentType paymentType;
    String description;
    Long enrollmentId;

}
