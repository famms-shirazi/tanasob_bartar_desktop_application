package ir.fathi.individual.coach;

import ir.fathi.individual.Individual;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "coach")
public class Coach extends Individual {

}
