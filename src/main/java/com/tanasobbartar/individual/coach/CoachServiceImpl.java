package com.tanasobbartar.individual.coach;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;

    @Override
    public CoachDto saveCoach(NewCoachDto coachDto) {
        Coach coach = new Coach();
        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());
        coach.setUsername(coachDto.getUsername());
        coach.setBirthdate(coachDto.getBirthdate());
        coach.setNationalCode(coachDto.getNationalCode());
        coach.setGenderType(coachDto.getGenderType());
        coachRepository.save(coach);
        return toDto(coach);
    }

    @Override
    public CoachDto findById(Long id) {
        return coachRepository.findById(id).map(this::toDto).orElseThrow();
    }

    private CoachDto toDto(Coach coach) {
        return CoachDto.builder()
                .id(coach.getId())
                .firstName(coach.getFirstName())
                .lastName(coach.getLastName())
                .username(coach.getUsername())
                .birthdate(coach.getBirthdate())
                .nationalCode(coach.getNationalCode())
                .genderType(coach.getGenderType())
                .build();
    }

}
