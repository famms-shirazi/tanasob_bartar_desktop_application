package ir.fathi.enrollment;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<EnrollmentDto> addEnrollment(@RequestBody NewEnrollmentDto newEnrollmentDto) {
        EnrollmentDto savedEnrollment = enrollmentService.addEnrollment(newEnrollmentDto);
        return ResponseEntity.created(URI.create("/enrollments/" + savedEnrollment.getId())).body(savedEnrollment);
    }

}
