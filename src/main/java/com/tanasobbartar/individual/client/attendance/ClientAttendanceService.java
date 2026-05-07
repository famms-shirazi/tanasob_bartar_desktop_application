package com.tanasobbartar.individual.client.attendance;

import java.time.LocalDate;
import java.util.List;

public interface ClientAttendanceService {

    ClientAttendanceDto findById(Long id);

    ClientAttendanceDto saveClientAttendance(NewClientAttendanceDto clientAttendanceDto) throws Exception;

    List<ClientAttendanceDto> findAllByClientAndCoach(Long clientId, Long coachId);

    List<ClientAttendanceDto> findClientAttendanceStatus(Long clientId, Long coachId, Long enrollmentId,
                                                         LocalDate startDate, LocalDate endDate);

    ClientAttendanceDto recordCheckOutTime(Long clientAttendanceId);

}
