package com.tanasobbartar.individual.client.session;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class NewSessionStatusDto {

    Long clientId;
    Long coachId;
    Long enrollmentId;
    LocalDateTime date;
    Integer remainingSessions;

}
