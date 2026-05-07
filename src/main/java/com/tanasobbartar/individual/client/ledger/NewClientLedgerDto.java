package com.tanasobbartar.individual.client.ledger;

import com.tanasobbartar.individual.client.attendance.NewClientAttendanceDto;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@Value
@Builder
@JsonDeserialize(builder = NewClientLedgerDto.NewClientLedgerDtoBuilder.class)
public class NewClientLedgerDto {

    LocalDateTime depositDate;
    Integer amount;
    PaymentMethod paymentMethod;
    PaymentType paymentType;
    String description;
    Long enrollmentId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NewClientLedgerDtoBuilder{
    }

}
