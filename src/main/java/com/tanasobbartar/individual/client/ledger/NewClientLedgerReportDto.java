package com.tanasobbartar.individual.client.ledger;

import lombok.Builder;
import lombok.Value;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@Value
@Builder
@JsonDeserialize(builder = NewClientLedgerReportDto.NewClientLedgerReportDtoBuilder.class)
public class NewClientLedgerReportDto {

    String reportStoragePath;
    Long coachId;
    LocalDateTime startDate;
    LocalDateTime endDate;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NewClientLedgerReportDtoBuilder{
    }

}
