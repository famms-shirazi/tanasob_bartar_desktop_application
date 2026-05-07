package com.tanasobbartar.individual.coach;

public interface CoachService {

    CoachDto findById(Long id);

    CoachDto saveCoach(NewCoachDto coachDto);

}
