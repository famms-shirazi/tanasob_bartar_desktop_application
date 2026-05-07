package com.tanasobbartar.individual.client.session;

import lombok.Builder;
import lombok.Value;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@Value
@Builder
@JsonDeserialize(builder = NewSessionStatusDto.NewSessionStatusBuilderDto.class)
public class NewSessionStatusDto {

    Long clientId;
    Long coachId;
    Long enrollmentId;
    LocalDateTime date;
    Integer remainingSessions;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NewSessionStatusBuilderDto {
    }

}
