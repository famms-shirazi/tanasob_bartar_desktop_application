package com.tanasobbartar.individual.client.session;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class SessionStatusDto {

    Long id;
    LocalDateTime date;
    Integer sessionsCount;
    Long clientId;
    Long coachId;
}
