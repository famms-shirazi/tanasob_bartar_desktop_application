package com.tanasobbartar.individual.coach;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;

    @GetMapping("/{id}")
    public ResponseEntity<CoachDto> getCoachById(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<CoachDto> addCoach(@RequestBody NewCoachDto newCoachDto) {
        CoachDto savedCoach = coachService.saveCoach(newCoachDto);
        return ResponseEntity.created(URI.create("/coaches/" + savedCoach.getId())).body(savedCoach);
    }

}


