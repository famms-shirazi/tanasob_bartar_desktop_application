package ir.fathi.individual.client.attendance;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client-attendances")
public class ClientAttendanceController {

    private final ClientAttendanceService clientAttendanceService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientAttendanceDto> getClientAttendanceById(@PathVariable Long id) {
        return ResponseEntity.ok(clientAttendanceService.findById(id));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ClientAttendanceDto> getClientAttendanceById(@PathVariable Long id) {
//        return ResponseEntity.ok(clientAttendanceService.findById(id));
//    }

    @PostMapping("/add")
    public ResponseEntity<ClientAttendanceDto> addClientAttendance(@RequestBody NewClientAttendanceDto attendanceDto) throws Exception {
        ClientAttendanceDto savedClientAttendance = clientAttendanceService.saveClientAttendance(attendanceDto);
        return ResponseEntity.created(URI.create("/client-attendances/" + savedClientAttendance.getId())).body(savedClientAttendance);
    }

    @GetMapping("/status")
    public ResponseEntity<List<ClientAttendanceDto>> findClientAttendanceStatus(@RequestParam Long clientId,
                                                                                @RequestParam Long coachId,
                                                                                @RequestParam Long enrollmentId,
                                                                                @RequestParam LocalDate startDate,
                                                                                @RequestParam LocalDate endDate) {
        List<ClientAttendanceDto> clientAttendanceStatus =
                clientAttendanceService.findClientAttendanceStatus(clientId, coachId, enrollmentId, startDate, endDate);
        return ResponseEntity.ok(clientAttendanceStatus);
    }

    @PostMapping("/report")
    public void generateReport(@RequestBody NewClientAttendanceReportDto clientAttendanceReportDto) throws JRException, SQLException {
         clientAttendanceService.generateExcelReport(clientAttendanceReportDto);
    }

    @PostMapping("check-out-time/{clientAttendanceId}")
    public ClientAttendanceDto recordCheckOutTime(@PathVariable Long clientAttendanceId) {
        return clientAttendanceService.recordCheckOutTime(clientAttendanceId);
    }

}



